package co.istad.content_service.config.mongo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public abstract class Auditable<T> {

    @CreatedBy
    private T createdBy;

    @CreatedDate
    @Field("created_date")
    private LocalDateTime createdDate;

    @LastModifiedBy
    private T lastModifiedBy;

    @LastModifiedDate
    @Field("last_modified_date")
    private LocalDateTime lastModifiedDate;

}
