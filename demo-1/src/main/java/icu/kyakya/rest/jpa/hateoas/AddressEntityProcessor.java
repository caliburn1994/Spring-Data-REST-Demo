package icu.kyakya.rest.jpa.hateoas;

import icu.kyakya.rest.jpa.module.address.Address;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * $ curl 'http://localhost:8080/api/v1/address' -i -X GET     -H 'Accept: application/hal+json'
 * HTTP/1.1 200
 * Vary: Origin
 * Vary: Access-Control-Request-Method
 * Vary: Access-Control-Request-Headers
 * Content-Type: application/hal+json
 * Transfer-Encoding: chunked
 * Date: Wed, 07 Apr 2021 05:05:07 GMT
 *
 * {
 *   "_embedded" : {
 *     "address" : [
 *
 *
 *
 *     {
 *       "createdAt" : "2021-04-06T09:12:50.908+00:00",
 *       "updateAt" : "2021-04-06T09:12:50.908+00:00",
 *       "country" : "Japan",
 *       "city" : "Tokyo",
 *       "postalCode" : null,
 *       "_links" : {
 *         "self" : {
 *           "href" : "http://localhost:8080/api/v1/address/302"
 *         },
 *         "address" : {
 *           "href" : "http://localhost:8080/api/v1/address/302"
 *         },
 *         "person" : {
 *           "href" : "http://localhost:8080/api/v1/address/302/person"
 *         }
 *       }
 *     },
 *
 *
 *
 *     {
 *       "createdAt" : "2021-04-07T05:03:58.443+00:00",
 *       "updateAt" : "2021-04-07T05:03:58.443+00:00",
 *       "country" : "Japan",
 *       "city" : "Osaka",
 *       "postalCode" : null,
 *       "_links" : {
 *         "self" : {
 *           "href" : "http://localhost:8080/api/v1/address/352"
 *         },
 *         "address" : {
 *           "href" : "http://localhost:8080/api/v1/address/352"
 *         },
 *         "person" : {
 *           "href" : "http://localhost:8080/api/v1/address/352/person"
 *         }
 *       }
 *     },
 *
 *
 *
 *     {
 *       "createdAt" : "2021-04-07T05:04:04.534+00:00",
 *       "updateAt" : "2021-04-07T05:04:04.534+00:00",
 *       "country" : "Japan",
 *       "city" : "Osaka",
 *       "postalCode" : null,
 *       "_links" : {
 *         "self" : {
 *           "href" : "http://localhost:8080/api/v1/address/353"
 *         },
 *         "address" : {
 *           "href" : "http://localhost:8080/api/v1/address/353"
 *         },
 *         "person" : {
 *           "href" : "http://localhost:8080/api/v1/address/353/person"
 *         }
 *       }
 *     }
 *
 *
 *
 *
 *     ]
 *   },
 *   "_links" : {
 *     "self" : {
 *       "href" : "http://localhost:8080/api/v1/address"
 *     },
 *     "profile" : {
 *       "href" : "http://localhost:8080/api/v1/profile/address"
 *     },
 *     "search" : {
 *       "href" : "http://localhost:8080/api/v1/address/search"
 *     }
 *   },
 *   "page" : {
 *     "size" : 20,
 *     "totalElements" : 3,
 *     "totalPages" : 1,
 *     "number" : 0
 *   }
 * }
 *
 *
 * operate object under _embedded.address, operate one by one
 */
@Component
public class AddressEntityProcessor implements RepresentationModelProcessor<EntityModel<Address>> {
    @Override
    public EntityModel<Address> process(@NonNull EntityModel<Address> model) {
        return model;
    }

}