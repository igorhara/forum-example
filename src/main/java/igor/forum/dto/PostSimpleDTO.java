package igor.forum.dto;

import igor.forum.model.CategoryPost;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by igorhara on 27/02/2018.
 */
@Data
public class PostSimpleDTO {
    Long id;

    String title;

    String content;

    CategoryPost category;

    String owner;

    Timestamp creationDate;
}
