package igor.forum.dao;

import igor.forum.model.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by igorhara on 18/01/2018.
 */
public interface CommentDao extends CrudRepository<Comment,Long> {
}
