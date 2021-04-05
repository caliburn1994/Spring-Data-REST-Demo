package icu.kyakya.rest.jpa.model.audit;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass // let entity capture this
public class AuditMetadata {

    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updateAt;

/*
    or use this way to assign date automatically

    @PrePersist // Before create entity
    protected void prePersist() {
        var now = new Date();
        createdAt = (createdAt == null) ? now : createdAt;
        updateAt = (updateAt == null) ? now : updateAt;
    }

    @PreUpdate // Before update entity to db
    protected void preUpdate() {

    }
 */
}
