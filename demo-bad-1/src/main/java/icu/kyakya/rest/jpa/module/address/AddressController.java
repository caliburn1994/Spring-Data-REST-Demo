package icu.kyakya.rest.jpa.module.address;

import icu.kyakya.rest.jpa.model.basic.Bulk;
import icu.kyakya.rest.jpa.web.ApiVersion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@BasePathAwareController  // if base url exists, it needs to be added
@RepositoryRestController
@RequiredArgsConstructor
@ExposesResourceFor(Address.class)
@ApiVersion(1)
public class AddressController {

    private final AddressRepository repo;
    private final RepositoryEntityLinks entityLinks; //get link


    // todo  if failed, message too long

//    /**
//     *
//     * curl -I -X DELETE http://localhost:8080/api/address/delete/1
//     */
//    @DeleteMapping(value = "/address/delete/{id}")
//    public ResponseEntity<Long> delete(@PathVariable Long id) {
//        repo.deleteById(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }


//    /**
//     *
//     * curl -I -X DELETE http://localhost:8080/api/address/delete/deleteAllByCity?city=osaka
//     *
//     * @return  204 https://docs.spring.io/spring-data/rest/docs/current/reference/html/#repository-resources.default-status-codes
//     */
//    @DeleteMapping(value = "/address/delete/deleteAllByCity")
//    public ResponseEntity<Void> deleteAllByCity(@Param("city") String city) {
//        repo.deleteAllByCity(city);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }


    /**
     * curl -i -X POST -H "Content-Type:application/json" -d  '{ "bulk": [ {"country" : "Japan" , "city" : "Tokyo" }, {"country" : "Japan" , "city" : "Osaka" }]} '   http://localhost:8080/api/v1/address/saveAll
     *
     * @return 201 https://docs.spring.io/spring-data/rest/docs/current/reference/html/#repository-resources.default-status-codes
     */
    @ApiVersion({2, 3})
    @PostMapping("/address/saveAll")
    public ResponseEntity<CollectionModel<EntityModel<Address>>> saveAll(@RequestBody EntityModel<Bulk<Address>> bulk) {
        List<Address> data = Objects.requireNonNull(bulk.getContent()).getBulk();
        Iterable<Address> addresses = repo.saveAll(data);

        ArrayList<EntityModel<Address>> models = new ArrayList<>();
        addresses.forEach(i -> {
            Link link = entityLinks.linkToItemResource(Address.class, i.getId()).withRel("self");
            models.add(EntityModel.of(i).add(link));
        });
        return new ResponseEntity<>(CollectionModel.of(models), HttpStatus.CREATED);
    }


}
