package co.istad.content_service.domain;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "article_content")
@Getter
@Setter
@NoArgsConstructor
public class Content {
    @Id
    private String id;

    private String title;

    private String content; // or content

    private String thumbnail;

    private String keyword;

    private String slug;

    private List<Tags> tags;

    private CommunityEngagement communityEngagement;

    private Boolean isDeleted;

    private Boolean isDraft;

    @CreatedBy
    @Field("author_id")
    private String authorId;

    @CreatedDate
    @Field("created_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Field("last_modified_date")
    private LocalDateTime lastModifiedDate;

}
