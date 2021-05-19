/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author os
 */
public class PurchasesModel extends AbstractModel implements PurchasesModelInterface {
    
    private ArrayList<PurchasesObserver> observers = new ArrayList<>();
    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private String sql;
    
    public PurchasesModel() {
        connection = this.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
        }
    }
    
    @Override
    public void add(Customer customer, Product product, int sellerActiveID, int amount) {
        try {
            sql = "insert into bill values (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setInt(2, product.getId());
            preparedStatement.setInt(3, sellerActiveID);
            preparedStatement.setInt(4, amount);
            preparedStatement.setDouble(5, product.getPrice() * amount); 
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            if(ex.getErrorCode() == 1062) {//System.out.println("fsafdsdf");
                try {
                    int newAmount = 0;
                    double newCost = 0;
                    sql = "select amount, cost "
                            + "from bill "
                            + "where bill.customerID = " + customer.getId() + " and bill.productID = "  + product.getId();
                    resultSet = statement.executeQuery(sql);
                    while(resultSet.next()) {
                        newAmount = resultSet.getInt("amount") + amount;
                        newCost = resultSet.getInt("cost") + amount * product.getPrice();
                    }
                    sql = "update bill set amount = ?, cost = ? "
                            + "where bill.customerID = " + customer.getId() + " and bill.productID = "  + product.getId();;
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, newAmount);
                    preparedStatement.setDouble(2, newCost);
                    
                    preparedStatement.executeUpdate();
                } 
                catch (SQLException ex1) {
                }
            }
        }
        notifyObserver();
    }
    
    @Override
    public void deletePurchase(int customerID) {
        sql = "delete from bill where customerID = " + customerID;
        try {
            statement.executeUpdate(sql);
        }
        catch(SQLException ex) {
        }
        notifyObserver();
    }

    @Override
    public ResultSet sortByProductName() {
        return sortBy("product", "productName");
    }

    @Override
    public ResultSet sortByCustomerName() {
        return sortBy("customer", "customerName");
    }
    
    private ResultSet sortBy(String tableName, String columnName) {
        sql = "select customer.customerID, customer.customerName, product.productID, product.productName, product.price, bill.amount, bill.cost " +
                "from customer, product, bill " +
                "where customer.customerID = bill.customerID and product.productID = bill.productID " +
                "order by " + tableName + "." + columnName;
        try {
            return statement.executeQuery(sql);
        } 
        catch (SQLException ex) {
        }
        return null;
    }

    @Override
    public void registerObserver(PurchasesObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(PurchasesObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        observers.forEach(o -> {
            o.updatePurchases();
        });
    }

    @Override
    public ResultSet getPurchaseResultSet() {
        sql = "select customer.customerID, customer.customerName, product.productID, product.productName, product.price, bill.amount, bill.cost " +
                "from customer, product, bill " +
                "where customer.customerID = bill.customerID and product.productID = bill.productID";
        try {
            return statement.executeQuery(sql);
        } catch (SQLException ex) {
        }
        return null;
    }

    @Override
    public ResultSet getBoughtResultSet(int customerID) {
        sql = "select product.productID, product.productName, product.price, bill.amount, bill.cost " +
                "from product, bill " +
                "where product.productID = bill.productID and bill.customerID = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerID);
            return preparedStatement.executeQuery();
        } catch (SQLException ex) {
        }
        return null;
    }

    @Override
    public String getSumCost(int customerID) {
        sql = "select sum(cost) as sumCost " +
                "from bill " +
                "where bill.customerID = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String sum = resultSet.getString("sumCost");
                return sum;
            }
        } 
        catch (SQLException ex) {
        }
        return null;
    }

    @Override
    public ResultSet getCustomerBought() {
        sql = "select distinct customer.customerID, customer.customerName, customer.address, customer.phone " +
            "from customer, bill " +
            "where customer.customerID = bill.customerID";
        try {
            return statement.executeQuery(sql);
        } catch (SQLException ex) {
        }
        return null;
    }

    @Override
    public int getCustomerID(int rowIndex) {
        resultSet = getCustomerBought();
        try {
            resultSet.absolute(rowIndex + 1);
            return resultSet.getInt("customerID");
        } catch (SQLException ex) {
            Logger.getLogger(PurchasesModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }  
    
}
