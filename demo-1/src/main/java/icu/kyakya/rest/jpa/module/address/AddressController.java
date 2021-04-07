package icu.kyakya.rest.jpa.module.address;

import icu.kyakya.rest.jpa.model.basic.Bulk;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.RootResourceInformation;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.alps.*;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.MediaTypes.ALPS_JSON_VALUE;
import static org.springframework.hateoas.mediatype.PropertyUtils.getExposedProperties;
import static org.springframework.hateoas.mediatype.alps.Alps.doc;
import static org.springframework.hateoas.mediatype.alps.Alps.ext;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@BasePathAwareController  // if base url exists, it needs to be added
@RepositoryRestController
@RequiredArgsConstructor
@ExposesResourceFor(Address.class)
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

    /**
     *
     * curl -i -X POST -H "Content-Type:application/json" -d  '{ "bulk": [ {"country" : "Japan" , "city" : "xxx1" }, {"country" : "Japan" , "city" : "xxx2" }]} '   http://localhost:8080/api/v1/address/testTransaction
     *
     * test transaction
     */
    @Transactional(rollbackFor = {Exception.class})
    @PostMapping("/address/testTransaction")
    public ResponseEntity<CollectionModel<EntityModel<Address>>> saveException(@RequestBody EntityModel<Bulk<Address>> bulk) throws SQLException {
        List<Address> data = Objects.requireNonNull(bulk.getContent()).getBulk();

        Address save = repo.save(data.get(0));

        if (save != null) {
            throw new SQLException();
        }

        Iterable<Address> addresses = repo.saveAll(data);

        ArrayList<EntityModel<Address>> models = new ArrayList<>();
        addresses.forEach(i -> {
            Link link = entityLinks.linkToItemResource(Address.class, i.getId()).withRel("self");
            models.add(EntityModel.of(i).add(link));
        });
        return new ResponseEntity<>(CollectionModel.of(models), HttpStatus.CREATED);
    }



    /**
     * something like /profile/address
     *
     * @see <a href="https://docs.spring.io/spring-hateoas/docs/current/reference/html/#reference">spring-hateoas</a>
     * @see org.springframework.data.rest.webmvc.alps.AlpsController#descriptor(RootResourceInformation)
     *
     */
    @RequestMapping(value = "/profile/address/add", method = GET,
            produces = { MediaType.ALL_VALUE, MediaTypes.ALPS_JSON_VALUE })
    public HttpEntity<?> docAdd() {
        Alps build = Alps.alps() //
                .doc(doc() //
                        .href("https://example.org/samples/full/doc.html") //
                        .value("value goes here") //
                        .format(Format.TEXT) //
                        .build()) //
                .descriptor(getExposedProperties(Address.class).stream() //
                        .map(property -> Descriptor.builder() //
                                .id("class field [" + property.getName() + "]") //
                                .name(property.getName()) //
                                .type(Type.SEMANTIC) //
                                .ext(Ext.builder() //
                                        .id("ext [" + property.getName() + "]") //
                                        .href("https://example.org/samples/ext/" + property.getName()) //
                                        .value("value goes here") //
                                        .build()) //
                                .rt("rt for [" + property.getName() + "]") //
                                .descriptor(Collections.singletonList(Descriptor.builder().id("embedded").build())) //
                                .build()) //
                        .collect(Collectors.toList()))
                .build();

        return new ResponseEntity<>(build, HttpStatus.OK);
    }


}
