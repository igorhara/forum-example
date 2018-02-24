package igor.forum.dao;

import igor.forum.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by igorhara on 18/01/2018.
 */
public interface PostDao extends CrudRepository<Post,Long> {

    List<Post> findAllByOrderByCreationDateDesc();

    Post getByIdAndOwner(Long id, String owner);
}
