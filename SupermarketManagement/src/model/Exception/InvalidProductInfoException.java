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
public class InvalidProductInfoException extends Exception{

    public InvalidProductInfoException() {
    }

    public InvalidProductInfoException(String message) {
        super(message);
    }
    
}
