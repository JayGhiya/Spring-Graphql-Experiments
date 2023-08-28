package com.graphql.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import graphql.schema.DataFetchingEnvironment;

@Controller
public class BookController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @QueryMapping
    public List<Book> books(DataFetchingEnvironment dataFetchingEnvironment) {
        try{
            return jdbcTemplate.query("SELECT * FROM public.books",(rs,rowNum) -> new Book(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("author")
            ));
        }
        catch(Exception e)
        {
            
            throw new CustomGraphQLException("Could not connect to database",dataFetchingEnvironment);
        }
        
     
    }

    // @MutationMapping
    // public Book addBook(@Argument String title, @Argument String author) {
    //     // implementation to add a new book to the database
    // }
}
