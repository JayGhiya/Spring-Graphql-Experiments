package com.graphql.demo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.graphql.ExecutionGraphQlResponse;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.graphql.support.DefaultExecutionGraphQlResponse;
import org.springframework.stereotype.Component;
import graphql.ExecutionResultImpl;

import graphql.ExecutionResult;
import graphql.GraphQLError;
import reactor.core.publisher.Mono;

@Component
public class CustomGraphQLWebInterceptor implements WebGraphQlInterceptor {

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {

        return chain.next(request).map(response -> {
            System.out.println("response is " + response);
            Map<String, Object> dataObjects = response.getData();
            boolean allValuesAreNull = dataObjects.values().stream().allMatch(Objects::isNull);
            System.out.println("check if the objects are null" + allValuesAreNull);
            
            if(allValuesAreNull)
            {

            List<GraphQLError> errors = response.getExecutionResult().getErrors();

            List<GraphQLError> graphQLErrors = errors;
            ExecutionResult executionResult = new ExecutionResultImpl(graphQLErrors);
            ExecutionGraphQlResponse executionGraphQlResponse = new DefaultExecutionGraphQlResponse(
                    response.getExecutionInput(), executionResult);

            return new WebGraphQlResponse(executionGraphQlResponse);
            }

            return response;

        });

    }
}
