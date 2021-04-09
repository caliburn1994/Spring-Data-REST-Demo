package icu.kyakya.rest.jpa.hateoas;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.ProfileResourceProcessor;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.stereotype.Component;

/**
 *
 * Example:
 *
 * $ curl 'http://localhost:8080/api/v1/' -i -X GET     -H 'Accept: application/hal+json'
 * HTTP/1.1 200
 * Vary: Origin
 * Vary: Access-Control-Request-Method
 * Vary: Access-Control-Request-Headers
 * Content-Type: application/hal+json
 * Transfer-Encoding: chunked
 * Date: Wed, 07 Apr 2021 04:09:26 GMT
 *
 * {
 *   "_links" : {
 *     "people" : {
 *       "href" : "http://localhost:8080/api/v1/people{?page,size,sort}",
 *       "templated" : true
 *     },
 *     "address" : {
 *       "href" : "http://localhost:8080/api/v1/address{?page,size,sort}",
 *       "templated" : true
 *     },
 *     "profile" : [ {
 *       "href" : "http://localhost:8080/api/v1/profile"
 *     }, {
 *       "href" : "http://localhost:8080/api/v1/profile"
 *     } ]
 *   }
 * }
 *
 * you can operate address,profile,profile
 *
 *
 *
 */
@Component
public class RepositoryLinksResourceProcessor extends ProfileResourceProcessor {

    /**
     * Creates a new {@link ProfileResourceProcessor} with the given {@link RepositoryRestConfiguration}.
     *
     * @param configuration must not be {@literal null}.
     */
    public RepositoryLinksResourceProcessor(RepositoryRestConfiguration configuration) {
        super(configuration);
    }

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        RepositoryLinksResource process = super.process(resource);

        // do something.

        return process;
    }

}