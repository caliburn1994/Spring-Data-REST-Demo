package icu.kyakya.rest.jpa.module.person;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	List<Person> findByLastName(@Param("name") String name);

	/**
	 *
	 * test with :
	 * 		 curl -I -X DELETE http://localhost:8080/api/v1/people/1
	 *
	 *
	 curl -i  -H "Content-Type:application/json" -d \
	 '{  "firstName" : "Frodo-2021-03-24.18:26",  "lastName" : "Baggins", "address":
	 ["http://localhost:8080/api/v1/address/352",
	 "http://localhost:8080/api/v1/address/353"]
	 }' \
	 http://localhost:8080/api/v1/people
	 *
	 */

}
