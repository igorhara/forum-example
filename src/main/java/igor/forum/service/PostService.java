package igor.forum.service;

import igor.forum.dao.PostDao;
import igor.forum.model.Post;
import igor.forum.model.UserForum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by igorhara on 20/01/2018.
 */
@Service
@Transactional
public class PostService {

    @Autowired
    PostDao dao;
    @Autowired
    UserService userService;

    public Post createPost(Post post,String usernameOwner){
        UserForum userOwner = userService.getUserByUsername(usernameOwner);
        post.setOwner(userOwner.getUsername());
        return createPost(post);
    }

    public Post createPost(@Valid Post post){
       Post saved = dao.save(post);
       return post;
    }

    public List<Post>  listPosts(){
        return dao.findAllByOrderByCreationDateDesc();
    }
}
