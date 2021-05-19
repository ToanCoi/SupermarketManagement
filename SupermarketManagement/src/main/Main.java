/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.Controller;
import controller.ControllerInterface;
import model.CustomersModel;
import model.CustomersModelInterface;
import model.ProductsModel;
import model.ProductsModelInterface;
import model.PurchasesModel;
import model.PurchasesModelInterface;
import model.SellersModel;
import model.SellersModelInterface;

/**
 *
 * @author os
 */
public class Main {
    
    public static void main(String[] args) {
        SellersModelInterface sellersModel = new SellersModel();
        ProductsModelInterface productsModel = new ProductsModel();
        CustomersModelInterface customersModel = new CustomersModel();
        PurchasesModelInterface purchasesModel = new PurchasesModel();
        ControllerInterface controller = new Controller(productsModel, customersModel, purchasesModel, sellersModel);
    }
}
