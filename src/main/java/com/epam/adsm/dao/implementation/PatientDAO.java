package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.Diagnosis;
import com.epam.adsm.model.Event;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Staff;
import org.joda.time.LocalDate;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akmatleu on 15.04.17.
 */
public class PatientDAO extends Dao implements EntityDao<Patient> {


    private static final String INSERT_PATIENT = "INSERT INTO public.patient(\n" +
            "            patient_id, date_of_birthday, initial, patient_sex, patient_phone, \n" +
            "            patient_email, password, confirmed, staff_id)\n" +
            "    VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";

    private static final String FIND_BY_ID = "SELECT * FROM public.patient WHERE patient_id = ?";
    private static final String FIND_BY_PHONE_AND_PASSWORD = "SELECT * FROM public.patient WHERE patient_phone = ? AND password = ?";
    private static final String DELETE_PATIENT_SOFT = "UPDATE public.patient SET confirmed=false WHERE patient_id=?";
    private static final String UPDATE_PATIENT = "UPDATE public.patient\n" +
            "   SET  patient_phone=? WHERE patient_id=?;";

    private static final String GET_ALL_PATIENTS = "SELECT * FROM public.patient WHERE confirmed = true ORDER BY patient_id ASC";
    private static final String GET_DOCTOR_SCHEDULE = "\n" +
            "SELECT patient.patient_id , patient.initial, patient.patient_phone,  event.event_date, event.event_id\n" +
            "FROM patient\n" +
            "    JOIN research\n" +
            "        ON research.patient_id = patient.patient_id\n" +
            "    JOIN event\n" +
            "        ON event.research_id = research.research_id\n" +
            "WHERE DATE(event.event_date)>=date(now()) AND patient.staff_id = ?\n" +
            "ORDER BY event.event_date ASC";

    private static final String GET_DOCTOR_PATIENTS = "SELECT patient.patient_id , patient.initial , patient.date_of_birthday , patient.patient_sex , patient.patient_phone, diagnosis.risk_factor, diagnosis.patient_type , diagnosis.clinical_form , diagnosis.mbt_status, diagnosis.dst_status , diagnosis.localization_disease , diagnosis.prevalence\n" +
            "FROM patient\n" +
            "JOIN diagnosis\n" +
            "ON diagnosis.patient_id = patient.patient_id\n" +
            "WHERE patient.staff_id = ? ORDER BY patient_id ASC";


    public List<Patient> getAllConfirmedPatients() throws DaoException {
        List<Patient> allPatients = new ArrayList<>();
        Patient patient = null;
        try (PreparedStatement statement = getConnection().prepareStatement(GET_ALL_PATIENTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                patient = pickPatientFromResultSet(patient, resultSet);
                allPatients.add(patient);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Cannot get all patient from db", e);
        }
        return allPatients;
    }

    public Patient findByPhoneAndPassword(String phone, String password) throws DaoException {
        Patient patient = null;
        try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_PHONE_AND_PASSWORD)) {
            statement.setString(1, phone);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                patient = pickPatientFromResultSet(patient, resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Cannot find patient by password and phone in db", e);
        }
        return patient;
    }

    public List<Patient> getDoctorSchedule(Staff doctor) throws DaoException {
        List<Patient> doctorPatients = new ArrayList<>();
        Patient patient;
        Event event;
        try (PreparedStatement statement = getConnection().prepareStatement(GET_DOCTOR_SCHEDULE)) {
            statement.setInt(1, doctor.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                patient = new Patient();
                event = new Event();
                patient.setPatientCode(resultSet.getString(1));
                patient.setInitial(resultSet.getString(2));
                patient.setPhoneNumber(resultSet.getString(3));
                event.setEventDate(resultSet.getDate(4).toLocalDate());
                event.setId(resultSet.getInt(5));
                patient.setEventDay(event);
                doctorPatients.add(patient);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Cannot get doctor -" + doctor.getId() + "schedule", e);
        }

        return doctorPatients;
    }

    public List<Patient> getDoctorPatients(Staff doctor) throws DaoException {

        List<Patient> patients = new ArrayList<>();
        Patient patient;
        Diagnosis diagnosis;
        try (PreparedStatement statement = getConnection().prepareStatement(GET_DOCTOR_PATIENTS)) {
            statement.setInt(1, doctor.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                patient = new Patient();
                diagnosis = new Diagnosis();
                patient.setPatientCode(resultSet.getString(1));
                patient.setInitial(resultSet.getString(2));
                patient.setDateOfBirthday(resultSet.getDate(3).toLocalDate());
                patient.setPatientSex(resultSet.getString(4));
                patient.setPhoneNumber(resultSet.getString(5));

                diagnosis.setRiskFactor(resultSet.getString(6));
                diagnosis.setPatientType(resultSet.getString(7));
                diagnosis.setClinicalForm(resultSet.getString(8));
                diagnosis.setMbtStatus(resultSet.getString(9));
                diagnosis.setDstStatus(resultSet.getString(10));
                diagnosis.setLocalizationDisease(resultSet.getString(11));
                diagnosis.setPrevalence(resultSet.getString(12));
                diagnosis.setPatient(patient);
                patient.setDiagnosis(diagnosis);
                patients.add(patient);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Cannot get doctor -" + doctor.getId() + "patients", e);
        }

        return patients;
    }

    @Override
    public Patient create(Patient patient) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT_PATIENT)) {
            statement.setString(1, patient.getPatientCode());
            statement.setDate(2, Date.valueOf(patient.getDateOfBirthday()));
            statement.setString(3, patient.getInitial());
            statement.setString(4, patient.getPatientSex());
            statement.setString(5, patient.getPhoneNumber());
            statement.setString(6, patient.getEmail());
            statement.setString(7, patient.getPassword());
            statement.setBoolean(8, patient.getConfirmed());
            statement.setInt(9, patient.getDoctor().getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot create patient in db", e);
        }
        return patient;
    }

    @Override
    public Patient findById(int id) throws DaoException {
        return null;
    }

    public Patient findByPatientCode(String id) throws DaoException {
        Patient patient = null;
        try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                patient = pickPatientFromResultSet(patient, resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Cannot find patient by id", e);
        }
        return patient;
    }

    @Override
    public void update(Patient patient) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_PATIENT)) {
            statement.setString(1, patient.getPhoneNumber());
            statement.setString(2, patient.getPatientCode());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot update patient from db", e);
        }
    }

    @Override
    public void delete(Patient patient) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(DELETE_PATIENT_SOFT)) {
            statement.setString(1, patient.getPatientCode());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot delete patient from db", e);
        }
    }

    private Patient pickPatientFromResultSet(Patient patient, ResultSet resultSet) throws DaoException {
        patient = new Patient();
        Staff staff = new Staff();
        try {
            patient.setPatientCode(resultSet.getString(1));
            patient.setDateOfBirthday(resultSet.getDate(2).toLocalDate());
            patient.setInitial(resultSet.getString(3));
            patient.setPatientSex(resultSet.getString(4));
            patient.setPhoneNumber(resultSet.getString(5));
            patient.setEmail(resultSet.getString(6));
            patient.setPassword(resultSet.getString(7));
            patient.setConfirmed(resultSet.getBoolean(8));
            staff.setId(resultSet.getInt(9));
            patient.setDoctor(staff);
        } catch (SQLException e) {
            throw new DaoException("Cannot create patient from resultSet", e);
        }
        return patient;
    }


}
