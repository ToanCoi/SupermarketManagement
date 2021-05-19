/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author os
 */
public abstract class AbstractModel {
    
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "toan110720");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
    public ResultSet getResultSet(Statement statement, String tableName) {
        try {
            return statement.executeQuery("select * from " + tableName);
        } catch (SQLException ex) {
        }
        return null;
    }
    
    public void delete(Statement statement, String tableName, int id) {
        try {
            statement.executeUpdate("delete from " + tableName + " where " + tableName + "ID = " + id);
        }
        catch(SQLException ex) {System.out.println(ex.getErrorCode());}
    }
    
    public int getNextID(PreparedStatement preparedStatement, Connection connection, String columnName, String tableName) {
        try {
            String sql = "select " + columnName + " " +
                          "from " + tableName + " " +
                          "order by " + columnName + " desc limit 1";
            preparedStatement = connection.prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) 
                return rs.getInt(columnName) + 1;
        }
        catch(SQLException ex) {
        }
        return 0;
    }
    
    public int getID(PreparedStatement preparedStatement, Connection connection, String tableName, int rowIndex) {
        try {
            String sql = "select * from " + tableName + " limit ?, 1";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, rowIndex);
            
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) 
                return rs.getInt(tableName + "ID");
        }
        catch(SQLException ex) {
        }
        return 0;
    }
}
