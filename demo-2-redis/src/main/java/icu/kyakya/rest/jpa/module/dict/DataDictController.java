package icu.kyakya.rest.jpa.module.dict;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@BasePathAwareController  // if base url exists, it needs to be added
@RepositoryRestController
@RequiredArgsConstructor
@ExposesResourceFor(DataDict.class)
public class DataDictController {

    private final DataDictRepository repo;
    private final RepositoryEntityLinks entityLinks; //get link
    private final CacheManager cacheManager;


    /**
     * if you just see SQL once, it means you get data from cache successfully.
     * you can check data in cacheManager.
     */
    @GetMapping("/new/dataDict/getAll")
    public HttpEntity<List<DataDict>> getAll() {
        List<DataDict> all = repo.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
