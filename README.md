# Spring-Graphql-Experiments

https://github.com/spring-projects/spring-graphql/issues/568

- Compile and run this project with java17
- Then fire request with http://localhost:8080/graphql with Header-> Accept: application/graphql-response+json. Body should be
  ```
  {"query":"query {\n  books {\n    id\n    title\n    author\n  }\n}"}
  ```
- We will get no data field and errors but still get 2xx.
