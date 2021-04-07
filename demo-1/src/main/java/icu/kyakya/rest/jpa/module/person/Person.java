package icu.kyakya.rest.jpa.module.person;

import icu.kyakya.rest.jpa.model.audit.AuditMetadata;
import icu.kyakya.rest.jpa.module.address.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Person extends AuditMetadata {

    @Id
    @SequenceGenerator(name = "person_seq",
            allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "person_seq")
    private long id;
    private String firstName;
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // orphanRemoval=true :when remove person,address will also be removed
    private List<Address> address;

}
