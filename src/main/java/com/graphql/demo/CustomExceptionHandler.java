package com.graphql.demo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;


import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;

import graphql.schema.DataFetchingEnvironment;


@Component
public class CustomExceptionHandler extends DataFetcherExceptionResolverAdapter {

@Override
  protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment handlerParameters) {
    Throwable exception = ex;
    GraphQLError error;

    switch (exception.getClass().getSimpleName()) {
        case "CustomGraphQLException":
            error = GraphqlErrorBuilder.newError(handlerParameters)
                .errorType(ErrorType.ExecutionAborted)
                .message(ex.getMessage())
                .extensions(Collections.singletonMap("DATABASE", "COULD NOT GET DATA"))
                .build();
                
            break;
        default:
            error = GraphqlErrorBuilder.newError(handlerParameters)
                .errorType(ErrorType.ExecutionAborted)
                .message(ex.getMessage())
                .extensions(Collections.singletonMap("DATABASE", "COULD NOT GET DATA"))
                .build();
            break;
    }
    System.out.println("inside data fetcher exception handler");
    return error;
}
}

