package com.eleaning.paymentservice.Exceptions;

public class CourseDoesntExist extends Exception {

    private final static String MESSAGE = "this course doesn't exist";

    public CourseDoesntExist(){
        super(MESSAGE);
    }
}
