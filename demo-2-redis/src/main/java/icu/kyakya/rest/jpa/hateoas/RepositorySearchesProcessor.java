
package icu.kyakya.rest.jpa.hateoas;


import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class RepositorySearchesProcessor implements RepresentationModelProcessor<RepositorySearchesResource> {


    @Override
    public RepositorySearchesResource process( RepositorySearchesResource model) {
        System.out.println(model.getDomainType());
        model.add(Link.of(model.getRequiredLink("self").getHref() + "/findFullTextFuzzy{?q}").withRel("findFullTextFuzzy"));
        return model;
    }
}
