package co.istad.content_service.feature.tag;

import co.istad.content_service.domain.Tags;
import co.istad.content_service.feature.tag.dto.TagResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;
//import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends MongoRepository<Tags, String> {
    Tags findByName(String java);

    List<TagResponse> findAllByOrderByNameAsc(); // A-Z

    List<TagResponse> findAllByOrderByNameDesc(); // Z-A

    List<Tags> findByNameIn(List<String> names);

//    Set<TagResponse> findAllLimit10();

}
