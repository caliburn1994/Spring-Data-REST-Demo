/**
 */
package icu.kyakya.rest.jpa.module.person;

/*
ADD

curl -i -X POST  -H "Content-Type:application/json"  \
-d '{
"firstName": "Frodo",
"lastName": "Baggins",
}' \
http://localhost:8080/api/v1/people


ADD person and foreign key

curl -i -X POST  -H "Content-Type:application/json" -d \
'{
"firstName" : "Frodo-2021-03-24.18:26",  "lastName" : "Baggins", "address":
["http://localhost:8080/api/v1/address/1",
"http://localhost:8080/api/v1/address/2"]
}' \
http://localhost:8080/api/v1/people


 */