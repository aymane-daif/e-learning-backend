package com.elearning.certification_service.exceptions;

public class CertificateDoesntExist extends Exception {

    private final static String MESSAGE = "This certificate doens't exist";

    public CertificateDoesntExist(){
        super(MESSAGE);
    }
}
