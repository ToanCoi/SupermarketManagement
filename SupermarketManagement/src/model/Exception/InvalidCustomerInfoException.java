/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Exception;

/**
 *
 * @author os
 */
public class InvalidCustomerInfoException extends Exception {

    public InvalidCustomerInfoException() {
    }

    public InvalidCustomerInfoException(String message) {
        super(message);
    }
    
}
