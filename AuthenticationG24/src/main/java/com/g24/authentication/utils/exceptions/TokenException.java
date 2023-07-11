package com.g24.authentication.utils.exceptions;

@SuppressWarnings("serial")
public class TokenException extends Exception 
{ 
    public TokenException(String errorMessage) 
    {
        super(errorMessage);
    }
}