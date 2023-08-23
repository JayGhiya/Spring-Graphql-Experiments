package com.graphql.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> books(DataFetchingEnvironment dataFetchingEnvironment)
    {
        try{
            return jdbcTemplate.query("SELECT * FROM public.books",(rs,rowNum) -> new Book(
             rs.getLong("id"),
             rs.getString("title"),
             rs.getString("author")
             ));
        }
        catch(Exception e )
        {
            System.out.println("Error in books");
            throw new CustomGraphQLException("An error occured while fetching books");
        }
    }
    


    
    
}
