package com.graphql.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    private Long id;
    private String title;
    private String author;
    
}
