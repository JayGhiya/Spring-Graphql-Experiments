package com.graphql.demo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.kickstart.servlet.GraphQLConfiguration;
import graphql.kickstart.servlet.GraphQLHttpServlet;
import graphql.schema.GraphQLSchema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomGraphQLHttpServlet extends GraphQLHttpServlet {

    @Autowired
    private GraphQLSchema graphQLSchema;


    @Override
    protected GraphQLConfiguration getConfiguration() {
        // TODO Auto-generated method stub
        System.out.println("printing graphql schema:"+graphQLSchema.getQueryType().getName());
        return GraphQLConfiguration.with(graphQLSchema).build();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        CustomGraphQLResponseWrapper responseWrapper = new CustomGraphQLResponseWrapper(resp);
        super.doGet(req, responseWrapper);

        String responseBody = responseWrapper.getCapturedResponse();
        if (responseContainsError(responseBody)) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Or any other status code you deem appropriate
        }

        try {
            resp.getWriter().write(responseBody);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        CustomGraphQLResponseWrapper responseWrapper = new CustomGraphQLResponseWrapper(resp);
        super.doPost(req, responseWrapper);

        String responseBody = responseWrapper.getCapturedResponse();
        if (responseContainsError(responseBody)) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Or any other status code you deem appropriate
        }

        try {
            resp.getWriter().write(responseBody);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private boolean responseContainsError(String responseBody) {
        

        // Here, you can inspect the response and set the status code based on the GraphQL response.
        // For example, if you want to set a 400 status code for a specific error:
        return responseBody.contains("\"errors\"");


 
    }


    // ... other necessary overrides, if any
}
