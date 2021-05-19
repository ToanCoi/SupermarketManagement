/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Exception.InvalidCustomerInfoException;
import java.io.Serializable;

/**
 *
 * @author os
 */
public class Customer implements Serializable {
    private int id;
    private String name, address, phone;

    public Customer() {
    }

    public Customer(int id, String name, String address, String phone) throws InvalidCustomerInfoException {
        name = name.trim();
        address = address.trim();
        phone = phone.trim();
        
        if(name.length() == 0) 
            throw new InvalidCustomerInfoException("Tên khách hàng không được để trống");
        if(address.length() == 0)
            throw new InvalidCustomerInfoException("Địa chỉ không được để trống");
        if(phone.length() == 0)
            throw new InvalidCustomerInfoException("Số điện thoại không được để trống");
        if(!phone.matches("[0-9]+"))
            throw new InvalidCustomerInfoException("Số điện thoại chỉ được chứa số");
        
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
    
    public boolean equals(Customer customer) {
        return customer.id == this.id && this.name.equals(customer.name) && this.address.equals(customer.address) 
                && this.phone.equals(customer.phone);
    }
    
}
