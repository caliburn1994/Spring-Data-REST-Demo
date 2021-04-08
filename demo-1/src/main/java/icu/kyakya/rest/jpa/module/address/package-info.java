/**
 *
 *
 */
package icu.kyakya.rest.jpa.module.address;

/*
GET
    curl 'http://localhost:8080/api/v1/' -i -X GET -H 'Accept: application/hal+json'
GET page
    curl 'http://localhost:8080/api/v1/address?page=0' -i -X GET -H 'Accept: application/hal+json'
    curl 'http://localhost:8080/api/v1/address?size=5' -i -X GET -H 'Accept: application/hal+json'
*/

/*
ADD
    curl -i -X POST  -H "Content-Type:application/json" -d '  { "country" : "Japan" , "city" : "Tokyo" }'   		http://localhost:8080/api/v1/address
    curl -i -X POST  -H "Content-Type:application/json" -d '  { "country" : "Japan" , "city" : "Osaka" }'   		http://localhost:8080/api/v1/address
    curl -i -X POST  -H "Content-Type:application/json" -d '  { "country" : "China" , "city" : "Guangzhou" }'    http://localhost:8080/api/v1/address

ADD BATCH
    curl -i -X POST  -H "Content-Type:application/json" -d '  [{ "country" : "Japan" , "city" : "Tokyo" },{ "country" : "Japan" , "city" : "Osaka" }]'   \
            http://localhost:8080/api/v1/address
*/

/*
DELETE
    curl -X DELETE http://localhost:8080/api/address/1
 */

/*
UPDATE
    curl -X PUT -H "Content-Type:application/json" -d '{"postalCode": "555235"}' http://localhost:8080/api/address/2
update selectively
    curl -X PUT -H "Content-Type:application/json" -d '{"postalCode": "555235", "city": "New York" , "country" : "America"}' http://localhost:8080/api/address/2
*/