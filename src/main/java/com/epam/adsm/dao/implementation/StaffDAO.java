package com.epam.adsm.dao.implementation;

import com.epam.adsm.dao.Dao;
import com.epam.adsm.dao.DaoException;

import com.epam.adsm.model.Staff;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akmatleu on 11.05.17.
 */
public class StaffDAO extends Dao implements EntityDao<Staff>{

    private static final String INSERT_STAFF = "INSERT INTO public.staff(\n" +
            "            name, surname, phone_number, activity_status, password, \n" +
            "            role, delete_status)\n" +
            "    VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_BY_ID = "SELECT * FROM public.staff WHERE staff_id = ?";
    private static final String FIND_BY_PHONE_AND_PASSWORD = "SELECT * FROM public.staff WHERE phone_number = ? AND password = ?";

    private static final String UPDATE_STAFF = "UPDATE public.staff SET phone_number=?, activity_status=? WHERE staff_id=?";

    private static final String DELETE_STAFF_SOFT = "UPDATE public.staff SET  delete_status=true WHERE staff_id=?";

    private static final String GET_ALL_STAFF = "SELECT *  FROM public.staff WHERE delete_status = false ORDER BY surname ASC";

    public List<Staff> getAllStaff() throws DaoException {
        List<Staff> staffArrayList = new ArrayList<>();
        Staff staff = null;
        try(PreparedStatement statement = getConnection().prepareStatement(GET_ALL_STAFF)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                staff = pickStaffFromResultSet(staff,resultSet);
                staffArrayList.add(staff);
            }
            resultSet.close();
        }catch (SQLException e){
            throw new DaoException("Cannot get all staff from db",e);
        }
        return staffArrayList;
    }

    public Staff findByPhoneAndPassword(String phoneNumber,String password) throws DaoException {
        Staff staff = null;
        try(PreparedStatement statement = getConnection().prepareStatement(FIND_BY_PHONE_AND_PASSWORD)) {
            statement.setString(1,phoneNumber);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                staff = pickStaffFromResultSet(staff,resultSet);
            }
            resultSet.close();
        }catch (SQLException e){
            throw new DaoException("Cannot find staff by password and phone in db",e);
        }
        return staff;
    }
    @Override
    public Staff create(Staff staff) throws DaoException {
        try(PreparedStatement statement = getConnection().prepareStatement(INSERT_STAFF, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1,staff.getName());
            statement.setString(2,staff.getSurname());
            statement.setString(3,staff.getPhoneNumber());
            statement.setBoolean(4,staff.isActivity_status());
            statement.setString(5,staff.getPassword());
            statement.setString(6,staff.getRole());
            statement.setBoolean(7,staff.isDelete_status());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                staff.setId(id);
            }
            resultSet.close();
        }catch (SQLException e){
            throw new DaoException("Cannot create staff in db",e);
        }
        return staff;
    }

    @Override
    public Staff findById(int id) throws DaoException {
        Staff staff = new Staff();
        try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                staff = pickStaffFromResultSet(staff,resultSet);
            }
            resultSet.close();
        }catch (SQLException e) {
            throw new DaoException("Cannot find staff by id in db",e);
        }
        return staff;
    }

    @Override
    public void update(Staff staff) throws DaoException {
        try(PreparedStatement statement = getConnection().prepareStatement(UPDATE_STAFF)){
            statement.setString(1,staff.getPhoneNumber());
            statement.setBoolean(2,staff.isActivity_status());
            statement.setInt(3,staff.getId());
            statement.execute();
        }catch (SQLException e){
            throw new DaoException("Cannot update staff in db",e);
        }
    }

    @Override
    public void delete(Staff staff) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(DELETE_STAFF_SOFT)){
            statement.setInt(1,staff.getId());
            statement.execute();
        }catch (SQLException e){
            throw new DaoException("Cannot delete staff from db",e);
        }
    }

    private Staff pickStaffFromResultSet(Staff staff,ResultSet resultSet) throws DaoException {
        staff = new Staff();
        try{
            staff.setId(resultSet.getInt(1));
            staff.setName(resultSet.getString(2));
            staff.setSurname(resultSet.getString(3));
            staff.setPhoneNumber(resultSet.getString(4));
            staff.setActivity_status(resultSet.getBoolean(5));
            staff.setPassword(resultSet.getString(6));
            staff.setRole(resultSet.getString(7));
            staff.setDelete_status(resultSet.getBoolean(8));
        }catch (SQLException e){
            throw new DaoException("Cannot create staff from result set", e);
        }
        return staff;
    }
}
