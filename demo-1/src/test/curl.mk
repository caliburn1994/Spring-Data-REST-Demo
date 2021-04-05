

top:
	curl http://localhost:8080

people:
	curl http://localhost:8080/api/v1/people

address:
	curl http://localhost:8080/api/v1/address

address_1:
	curl http://localhost:8080/api/v1/address/1

foreign-key_create:
	curl -i -H "Content-Type:application/json" -d '  { "country" : "Japan" , "city" : "tokyo" }'   		http://localhost:8080/api/v1/address
	curl -i -H "Content-Type:application/json" -d '  { "country" : "Japan" , "city" : "osaka" }'   		http://localhost:8080/api/v1/address
	curl -i -H "Content-Type:application/json" -d '  { "country" : "China" , "city" : "guangzhou" }'    http://localhost:8080/api/v1/address
	curl -i -H "Content-Type:application/json" \
	-d '  { "country" : "China" }' \
	http://localhost:8080/api/v1/address
	curl -i  -H "Content-Type:application/json" -d \
    '{  "firstName" : "Frodo ",  "lastName" : "Baggins", "address":  ["http://localhost:8080/api/v1/address/1", "http://localhost:8080/api/v1/address/2"]}' \
    http://localhost:8080/api/v1/people

foreign-key_update:
	curl -X PATCH -H "Content-Type:application/json" -d '{"firstName": "Bilbo Jr."}' http://localhost:8080/people/1