/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Exception.InvalidSellerInfoException;

/**
 *
 * @author os
 */
public class Seller {
    private int id;
    private String name, phone, password;

    public Seller() {
    }

    public Seller(int id, String name, String phone, String password) throws InvalidSellerInfoException {
        name = name.trim();
        phone = phone.trim();
        password = password.trim();
        
        if(name.length() == 0)
            throw new InvalidSellerInfoException("Tên nhân viên không được để trống");
        if(phone.length() == 0)
            throw new InvalidSellerInfoException("Số điện thoại không được để trống");
        if(!phone.matches("[0-9]+"))
            throw new InvalidSellerInfoException("Số điện thoại chỉ được chứa số");
        if(password.length() == 0)
            throw new InvalidSellerInfoException("Mật khẩu không được để trống");
        
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
    
}
