/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import model.Exception.InvalidCustomerInfoException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author os
 */
public class CustomersModel extends AbstractModel implements CustomersModelInterface {
    
    private ArrayList<Customer> customers;
    private ArrayList<CustomersObserver> observers = new ArrayList<>();
    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private String sql = "";
    
    public CustomersModel() {
        connection = this.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
        }
        readData();
    }
    
    @Override
    public void readData() {
        customers = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("select * from customer");
            while(rs.next()) {
                int id = rs.getInt("customerID");
                String name = rs.getString("customerName");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Customer customer = new Customer(id, name, address, phone);
                customers.add(customer);
            }
        } catch (SQLException | InvalidCustomerInfoException ex) {

        }   
    }

    @Override
    public void add(int id, String name, String address, String phone) throws InvalidCustomerInfoException {
        Customer customer = new Customer(id, name, address, phone);
        customers.add(customer);
        try {
            sql = "insert into customer values(?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, phone);
            
            preparedStatement.executeUpdate();
        }
        catch(SQLException ex) {
            
        }
        notifyObserver();
    }
    
    @Override
    public void delete(int id) {
        this.delete(statement, "customer", id);
        readData();
        notifyObserver();
    }

    @Override
    public void update(int id, String name, String address, String phone) throws InvalidCustomerInfoException {
        Customer customer = new Customer(id, name, address, phone);
        try {
            sql = "update customer "
                    + "set customerName = '" + name + "', address = '" + address + "', phone = '" + phone + "' "
                    + "where customerID = " + id;
            statement.executeUpdate(sql);
        }
        catch(SQLException ex) {}
        readData();
        notifyObserver();
    }

    @Override
    public void registerObserver(CustomersObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(CustomersObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        observers.forEach(o -> {
            o.updateCustomers();
        });
    }
    

    @Override
    public int getID(int rowIndex) {
        return this.getID(preparedStatement, connection, "customer", rowIndex);
    }

    @Override
    public int getNextID() {
        return this.getNextID(preparedStatement, connection, "customerID", "customer");
    }

    @Override
    public List getCustomers() {
        return customers;
    }

    @Override
    public ResultSet getResultSet() {
        return this.getResultSet(statement, "customer");
    }

    @Override
    public String getCustomerName(int customerID) {
        sql = "select customerName "
                + "from customer "
                + "where customerID = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerID);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                return resultSet.getString("customerName");
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    @Override
    public String getCustomerPhone(int customerID) {
        sql = "select phone "
                + "from customer "
                + "where customerID = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerID);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                return resultSet.getString("phone");
            }
        } catch (SQLException ex) {
        }
        return null;
    }
    
}
