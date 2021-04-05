# top level service
curl http://localhost:8080

# the people records
curl http://localhost:8080/api/v1/people

# create a record
curl -i -H "Content-Type:application/json" \
-d '{
"firstName": "Frodo",
"lastName": "Baggins",
}' \
http://localhost:8080/api/v1/people

curl -X PUT -H "Content-Type:application/json" \
-d '{"firstName": "Bilbo", "lastName": "Baggins"}' \
http://localhost:8080/api/v1/people/1


curl -i -H "Content-Type:application/json" \
-d '  { "country" : "Japan" }' \
http://localhost:8080/api/v1/address

curl -i -H "Content-Type:application/json" \
-d '  { "country" : "China" }' \
http://localhost:8080/api/v1/address


curl -i  -H "Content-Type:application/json" -d \
'{  "firstName" : "Frodo-2021-03-24.18:26",  "lastName" : "Baggins", "address":
["http://localhost:8080/api/v1/address/1",
"http://localhost:8080/api/v1/address/2"]
}' \
http://localhost:8080/api/v1/people
