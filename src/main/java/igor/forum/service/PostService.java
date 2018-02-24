package igor.forum.service;

import igor.forum.CheckOwner;
import igor.forum.dao.PostDao;
import igor.forum.model.Post;
import igor.forum.model.UserForum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @CheckOwner
    public Post updatePost(Post post){
        Post persistedPost = getPostForEdit(post.getId());
        persistedPost.setTitle(post.getTitle());
        persistedPost.setContent(post.getContent());
        persistedPost.setCategory(post.getCategory());
        return createPost(persistedPost);
    }

    @CheckOwner
    public Post getPostForEdit(Long id){
        Post post = dao.findOne(id);
        post.setComments(null);
        return post;
    }
    public Post getPost(Long id){
        Post post = dao.findOne(id);
        post.getComments();
        return post;
    }

    public List<Post>  listPosts(){
        return dao.findAllByOrderByCreationDateDesc();
    }
    public Post getPostForView(Long id){
        return dao.findOne(id);
    }

    public boolean isOwner(Post post, String owner){
        return Optional.ofNullable(post).map(Post::getOwner).map(p->owner.equals(p)).orElse(false);
    }
}
