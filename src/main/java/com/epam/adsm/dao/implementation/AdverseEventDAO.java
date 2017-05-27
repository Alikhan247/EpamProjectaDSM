package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;
import com.epam.adsm.model.AdverseEvent;
import com.epam.adsm.model.Patient;
import com.epam.adsm.model.Staff;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akmatleu on 13.05.17.
 */
public class AdverseEventDAO extends Dao implements EntityDao<AdverseEvent> {


    private static final String CREATE_ADVERSE_EVENT = "INSERT INTO public.adverse_event(\n" +
            "            adverse_event_name, adverse_event_date, adverse_event_comment, \n" +
            "            effect_of_alcohol, effect_of_drug, staff_id, patient_id, adverse_status)\n" +
            "    VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_ADVERSE_BY_ID = "SELECT * FROM public.adverse_event WHERE adverse_event_id = ?";
    private static final String UPDATE_ADVERSE_EVENT_STATUS = "UPDATE public.adverse_event SET adverse_status=? WHERE adverse_event_id=?";
    private static final String GET_DOCTOR_ADVERSE_EVENTS = "SELECT adverse_event.adverse_event_id ,adverse_event.adverse_event_name , adverse_event.adverse_event_date , adverse_event.adverse_event_comment ,adverse_event.effect_of_alcohol , adverse_event.effect_of_drug\n" +
            "FROM adverse_event\n" +
            "JOIN patient\n" +
            "ON patient.patient_id = adverse_event.patient_id\n" +
            "JOIN staff\n" +
            "ON staff.staff_id = patient.staff_id\n" +
            "WHERE adverse_event.adverse_status IS DISTINCT FROM 'Разрешено' AND staff.staff_id = ? \n" +
            "ORDER BY adverse_event.adverse_event_date";

    @Override
    public AdverseEvent create(AdverseEvent adverseEvent) throws DaoException {
       try(PreparedStatement statement = getConnection().prepareStatement(CREATE_ADVERSE_EVENT , PreparedStatement.RETURN_GENERATED_KEYS)) {
           statement.setString(1,adverseEvent.getAdverseName());
           statement.setDate(2,Date.valueOf(adverseEvent.getAdverseDate()));
           statement.setString(3,adverseEvent.getAdverseComment());
           statement.setBoolean(4,adverseEvent.getAdverseAlcohol());
           statement.setBoolean(5,adverseEvent.getAdverseDrug());
           statement.setInt(6,adverseEvent.getStaff().getId());
           statement.setString(7,adverseEvent.getPatient().getPatientCode());
           statement.setString(8,adverseEvent.getAdverseStatus());
           statement.executeUpdate();
           ResultSet resultSet = statement.getGeneratedKeys();
           while (resultSet.next()){
               int id = resultSet.getInt(1);
               adverseEvent.setId(id);
           }
            resultSet.close();
       }catch (SQLException e){
           throw  new DaoException("Cannot create adverse event in db",e);
       }
        return adverseEvent;
    }

    @Override
    public AdverseEvent findById(int id) throws DaoException {
        AdverseEvent adverseEvent = null;
        try(PreparedStatement statement = getConnection().prepareStatement(FIND_ADVERSE_BY_ID)){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                adverseEvent = pickAdverseEventFromResultSet(adverseEvent,resultSet);
            }
            resultSet.close();
        }catch (SQLException e){
            throw new DaoException("Cannot find adverse event from db",e);
        }
        return adverseEvent;
    }

    @Override
    public void update(AdverseEvent adverseEvent) throws DaoException {
        try(PreparedStatement statement = getConnection().prepareStatement(UPDATE_ADVERSE_EVENT_STATUS)){
            statement.setString(1,adverseEvent.getAdverseStatus());
            statement.setInt(2,adverseEvent.getId());
            statement.execute();
        }catch (SQLException e){
            throw new DaoException("Cannot update adverse event status in db",e);
        }
    }

    @Override
    public void delete(AdverseEvent adverseEvent) throws DaoException {

    }

    public List<AdverseEvent> getDoctorPatientsAdverseEvents(Staff doctor) throws DaoException {
        List<AdverseEvent> adverseEvents = new ArrayList<>();
        AdverseEvent adverseEvent = new AdverseEvent();
        try(PreparedStatement statement = getConnection().prepareStatement(GET_DOCTOR_ADVERSE_EVENTS)){
            statement.setInt(1,doctor.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                adverseEvent.setId(resultSet.getInt(1));
                adverseEvent.setAdverseName(resultSet.getString(2));
                adverseEvent.setAdverseDate(resultSet.getDate(3).toLocalDate());
                adverseEvent.setAdverseComment(resultSet.getString(4));
                adverseEvent.setAdverseAlcohol(resultSet.getBoolean(5));
                adverseEvent.setAdverseDrug(resultSet.getBoolean(6));
                adverseEvents.add(adverseEvent);
            }
            resultSet.close();
        }catch (SQLException e){
            throw  new DaoException("Cannot get adverse events for doctor"+doctor.getId(),e);
        }


        return adverseEvents;
    }

    private AdverseEvent pickAdverseEventFromResultSet(AdverseEvent adverseEvent,ResultSet resultSet) throws DaoException {
         adverseEvent = new AdverseEvent();
        try {
            Staff staff = new Staff();
            staff.setId(resultSet.getInt(7));
            Patient patient = new Patient();
            patient.setPatientCode(resultSet.getString(8));
            adverseEvent.setId(resultSet.getInt(1));
            adverseEvent.setAdverseName(resultSet.getString(2));
            adverseEvent.setAdverseDate(resultSet.getDate(3).toLocalDate());
            adverseEvent.setAdverseComment(resultSet.getString(4));
            adverseEvent.setAdverseAlcohol(resultSet.getBoolean(5));
            adverseEvent.setAdverseDrug(resultSet.getBoolean(6));
            adverseEvent.setStaff(staff);
            adverseEvent.setPatient(patient);
            adverseEvent.setAdverseStatus(resultSet.getString(9));
        } catch (SQLException e){
            throw  new DaoException("Cannot create adverse event by result set",e);
        }
        return adverseEvent;
    }
}
