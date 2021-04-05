package icu.kyakya.rest.jpa.module.address;

import icu.kyakya.rest.jpa.model.audit.AuditMetadata;
import icu.kyakya.rest.jpa.module.person.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Address extends AuditMetadata {
    @Id
    @SequenceGenerator(name = "address_seq",
            allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "address_seq")
    private Long id;


    private String country;
    private String city;
    private String PostalCode;


    @ManyToOne(cascade = CascadeType.ALL)
    private Person person;
}
