## Demos

[demo-1](demo-2-redis) is a RESTful Web Service , based on [Spring Data Rest](https://spring.io/projects/spring-data-rest), [Spring Rest Doc](https://spring.io/projects/spring-restdocs). And the database is Postgresql. The project exposes operations of Database.

## About Batch CRUD

### Solution-1

The solution-1 is to divide single and batch operation into two path like this:

```
GET /user/1
GET /user/search/byName?name=xxx

POST /user		-d '{"data": "value"}' 
POST /users		-d '{"data": "value"}' 

PUT  /user/1 -d '{"data": "value"}' 
POST /user/1 -d '{"data": "value"}' 
PUT  /users/search/... -d '{"data": "value"}' 
POST /users/search/byName?name=xxx -d '{"data": "value"}' 

DELETE /user/1
DELETE /users/search/byName?name=xxx
```

With this solution, we must create lots of handlers of controllers.

### Solution-2

```
GET /user/1
GET /user/search/byName?name=xxx

POST /user			-d '{"data": "value"}'   <<== redefine manually
POST /user/search	-d '{"data": "value"}' 

PUT  /user/1 -d '{"data": "value"}' 	<<== redefine manually
POST /user/1 -d '{"data": "value"}' 	<<== redefine manually
PUT  /user/search/byName?name=xxx -d '{"data": "value"}' 	
POST /user/search/byName?name=xxx -d '{"data": "value"}' 	

DELETE /user/1
DELETE /user/search/byName?name=xxx	
```

```
e.g:
put /user/search ==> [filter]"search"=="search" ==> redirect createUpdateDelete/user/
put /user/1      ==> [filter]     "1"!="search" ==> not do anything
```

1. set resources `exported=fasle` ,and then rewrite them.
2. add controllers

### Solution-3

```
GET /user/1
GET /user/search/byName?name=xxx

POST /user				-d '{"data": "value"}' 
POST /user/batchAdd		-d '{"data": "value"}' 

PUT  /user/1 -d '{"data": "value"}' 
POST /user/1 -d '{"data": "value"}' 
GET  /user/search/updateEmailByName?name=xxx&email=xxx
GET  /user/search/updateSeletiveEmailByName?name=xxx&email=xxx

DELETE /user/1
GET  /user/search/deleteByName?name=xxx
```

No need to do anything.

## TODO

questions:

- [ ] version  https://stackoverflow.com/questions/20198275/how-to-manage-rest-api-versioning-with-spring
- [ ] security 
- [ ] @CrossOrigin
- [ ] 404 page or others
