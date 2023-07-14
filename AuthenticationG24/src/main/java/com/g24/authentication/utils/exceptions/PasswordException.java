package com.g24.authentication.utils.exceptions;

@SuppressWarnings("serial")
public class PasswordException extends Exception 
{ 
    public PasswordException(String errorMessage) 
    {
        super(errorMessage);
    }
}