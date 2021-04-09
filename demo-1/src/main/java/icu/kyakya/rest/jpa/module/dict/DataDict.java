package icu.kyakya.rest.jpa.module.dict;

import icu.kyakya.rest.jpa.model.audit.AuditMetadata;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "data_dict",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "attribute"})
        })
@Data
public class DataDict extends AuditMetadata {

    @Id
    @SequenceGenerator(name = "dict_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "dict_seq")
    private Long id;
    private String name;
    private String attribute;
    private String value;
}
