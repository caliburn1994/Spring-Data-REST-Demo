package icu.kyakya.rest.jpa.module.person;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.server.ExposesResourceFor;


/*
https://docs.spring.io/spring-data/rest/docs/current/reference/html/#customizing-sdr.overriding-sdr-response-handlers
 */
@BasePathAwareController  // if base url exists, it needs to be added
@RepositoryRestController
@RequiredArgsConstructor
@ExposesResourceFor(Person.class)
//@RequestMapping(path = {"/people/search"})
public class PersonController {

    private final PersonRepository repo;

//    @GetMapping(path = {"/people/search/test"})
//    public @ResponseBody ResponseEntity<?> getProducers() {
//        List<Person> person = new ArrayList<>();
//        repo.findAll().forEach(person::add);
//        List<String> firstNames = person.stream().map(Person::getFirstName).collect(Collectors.toList());
//
////         do some intermediate processing, logging, etc. with the person
//
//
//        CollectionModel<String> resources = CollectionModel.of(firstNames);
//        resources.add(linkTo(methodOn(PersonController.class).getProducers()).withSelfRel());
//
//        // add other links as needed
//
//        return ResponseEntity.ok(resources);
//    }
//
}
