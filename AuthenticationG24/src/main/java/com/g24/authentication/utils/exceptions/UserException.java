package com.g24.authentication.utils.exceptions;

@SuppressWarnings("serial")
public class UserException extends Exception 
{ 
    public UserException(String errorMessage) 
    {
        super(errorMessage);
    }
}
