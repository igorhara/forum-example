package igor.forum.dto;

import igor.forum.model.CategoryPost;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by igorhara on 27/02/2018.
 */
@Data
public class PostDTO {
    Long id;

    String title;

    String content;

    CategoryPost category;

    String owner;

    Timestamp creationDate;

    List<CommentDTO> comments;
}
