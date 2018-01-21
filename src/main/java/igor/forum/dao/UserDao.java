package igor.forum.dao;

import igor.forum.model.UserForum;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by igorhara on 18/01/2018.
 */
public interface UserDao extends CrudRepository<UserForum,String> {

     UserForum getUserForumByUsername(String username);
}
