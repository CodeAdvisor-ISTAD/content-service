package co.istad.content_service.domain;


import co.istad.content_service.config.mongo.Auditable;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "tags")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Tags extends Auditable<String> {

    @Id
    private String id;

    private String name;

    private String slug;

    private String description;

    private Boolean isDeleted;
}
