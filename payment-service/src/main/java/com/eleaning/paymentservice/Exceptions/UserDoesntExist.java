package com.eleaning.paymentservice.Exceptions;

public class UserDoesntExist extends Exception{

    private final static String MESSAGE = "this user doesn't exists";

    public UserDoesntExist(){
        super(MESSAGE);
    }
}
