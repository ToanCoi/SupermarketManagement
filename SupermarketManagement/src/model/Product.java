/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Exception.InvalidProductInfoException;
import java.io.Serializable;

/**
 *
 * @author os
 */
public class Product implements Serializable {
    private int id;
    private String name, type;
    private double price;

    public Product() {
    }

    public Product(int id, String name, String type, double price) throws InvalidProductInfoException {
        name = name.trim();
        
        if(id > 9999)
            throw new InvalidProductInfoException ("Số lượng hàng hóa vượt quá giới hạn");
        if(name.length() == 0)
            throw new InvalidProductInfoException("Tên không được để trống");
        if(price <= 0)
            throw new InvalidProductInfoException("Giá bán phải lớn hơn 0");
        
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
    
    public boolean equals(Product product) {
        return this.id == product.id && this.name.equals(product.name) && this.type.equals(product.type) && this.price == product.price;
    }
    
}
