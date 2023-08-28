package com.graphql.demo;

import graphql.schema.DataFetchingEnvironment;
import lombok.Data;

@Data
public class CustomGraphQLException extends RuntimeException {
    
    private DataFetchingEnvironment dataFetchingEnvironment;

    public CustomGraphQLException(String message,DataFetchingEnvironment dataFetchingEnvironment)
    {
        super(message);
        this.dataFetchingEnvironment = dataFetchingEnvironment;
        
    }
}
