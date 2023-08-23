package com.graphql.demo;

public class CustomGraphQLException extends RuntimeException {
    
    public CustomGraphQLException(String message)
    {
        super(message);
    }
}
