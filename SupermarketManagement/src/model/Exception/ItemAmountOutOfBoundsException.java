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
public class ItemAmountOutOfBoundsException extends Exception{

    public ItemAmountOutOfBoundsException() {
    }

    public ItemAmountOutOfBoundsException(String message) {
        super(message);
    }
    
}
