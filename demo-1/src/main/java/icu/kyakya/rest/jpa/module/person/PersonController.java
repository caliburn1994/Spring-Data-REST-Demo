package icu.kyakya.rest.jpa.module.person;

import icu.kyakya.rest.jpa.module.address.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


/*
https://docs.spring.io/spring-data/rest/docs/current/reference/html/#customizing-sdr.overriding-sdr-response-handlers
 */
@BasePathAwareController  // if base url exists, it needs to be added
@RepositoryRestController
@RequiredArgsConstructor
@ExposesResourceFor(Person.class)
public class PersonController {

    private final PersonRepository personRepo;
    private final AddressRepository addressRepo;


}
