package com.eleaning.paymentservice.Exceptions;

public class StripeException extends Exception{

    private final static String MESSAGE = "you have something wrong with your bank card";

    public StripeException(){
        super(MESSAGE);
    }
}
