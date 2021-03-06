package icu.kyakya.rest.jpa.module.address;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  {@code @RestResource} see: https://www.baeldung.com/spring-data-rest-customize-http-endpoints
 */
@RepositoryRestResource(collectionResourceRel = "address", path = "address")
public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {






    // find...

    @RestResource(path = "findByCity", rel = "findByCity")
    List<Address> findByCity(@Param("city") String name);

    /**
     * http://localhost:8080/api/v1/address/search/findByCityAndCountry?city=Tokyo&country=Japan
     */
    List<Address> findByCityAndCountry(String city, String country);

    // get...
    Address getAddressByCity(String city);

    // delete...
    // remove...



    /**
     *  delete with query style
     *  by condition:
     *      http://localhost:8080/api/address/search/removeAddressByCity?city=Guangzhou
     */
    @Transactional
    Long removeAddressByCity(String city);

    //  haven't tested it
    //  @Modifying
    //  @Query("delete ...")
    @Transactional
    @RestResource(description = @Description("delete all addresses based on the given city"))
    Long deleteAllByCity(String city);

    @RestResource(description = @Description("test delete")) // todo not work
    @Override
    void delete(@NonNull Address entity);

    /**
     * update with query style
     */
    @Transactional
    @Modifying
    @Query("update Address set PostalCode= :postalCode ")
    void updateAllPostalCode(@Param("postalCode") String postalCode);

}
