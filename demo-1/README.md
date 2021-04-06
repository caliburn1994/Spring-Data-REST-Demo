Example from: https://spring.io/guides/gs/accessing-data-rest/


## what is JPA

## Default JPA provider

It's Hibernate. You can check [here](./src/main/resources/application.yaml) .

## project structure

This is discussion about recommended project
structure : https://stackoverflow.com/questions/40902280/what-is-the-recommended-project-structure-for-spring-boot-rest-projects


## How to define tables? 

- [ ] database

  - [ ] table
  - [x] field
    - [x]  updated at && created at
    - [x] how long? big large?
  - [ ] CRUD

    - [x] update
    - [ ] update if not found

- [ ] foreign key

  - [x] create
  - [ ] query

- [ ] SQL语句

  - [ ] join sentence
  - [ ] 翻页查询

- [ ] 事务

- [ ] ```
  _links
  ```

- [x] Show SQL in log file.

- [x] primary key? what's difference every type

- [ ] api version 1 version 2

- [ ] page

- [ ] batch insert by post

- [ ] batch update by put

- [] RepositorySearchController

### ID

```java
@GeneratedValue(strategy = GenerationType.AUTO)
```

- [ ] `allocationSize` how large should I set

## Annotation

**@Table**

Because no `@Table` annotation exists, it is assumed that this entity is mapped to a table named `Customer`

## Other wiki

### Hypermedia API && [HATEOAS](http://en.wikipedia.org/wiki/HATEOAS)

ref: http://www.ruanyifeng.com/blog/2014/05/restful_api.html

## Links

There are a lot of things need to improve in spring-data-rest.

- https://github.com/spring-projects/spring-data-rest/labels/type%3A%20enhancement

document:

- https://docs.spring.io/spring-hateoas/docs/current/reference/html
- https://docs.spring.io/spring-data/rest/docs/current/reference/html