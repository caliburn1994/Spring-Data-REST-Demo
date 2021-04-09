package icu.kyakya.rest.jpa.module.dict;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "dataDict", path = "dataDict")
public interface DataDictRepository extends PagingAndSortingRepository<DataDict, Long> {


    List<DataDict> getByNameAndAttribute(String name, String attribute);

    @Cacheable("dataDict")
    @Query("select d from DataDict d ")
    List<DataDict> getAll();
}
