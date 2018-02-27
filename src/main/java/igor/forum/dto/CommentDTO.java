package igor.forum.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by igorhara on 27/02/2018.
 */
@Data
public class CommentDTO {

    Long id;


    String content;


    String owner;

    Timestamp creationDate;

    Long postId;
}
