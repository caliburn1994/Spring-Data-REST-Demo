//package icu.kyakya.rest.jpa.controller;
//
//import org.springframework.data.rest.webmvc.RepositorySearchesResource;
//import org.springframework.data.rest.webmvc.support.PersistentEntityResourceProcessor;
//import org.springframework.hateoas.Link;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PersonSearchResourcesProcessor implements ResourceProcessor<RepositorySearchesResource> {
//
//    @Override
//    public RepositorySearchesResource process(RepositorySearchesResource repositorySearchesResource) {
//        final String search = repositorySearchesResource.getId().getHref();
//        final Link findFullTextFuzzy = new Link(search + "/findFullTextFuzzy{?q}").withRel("findFullTextFuzzy");
//        repositorySearchesResource.add(findFullTextFuzzy);
//
//        return repositorySearchesResource;
//    }
//}