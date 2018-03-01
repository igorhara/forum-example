package igor.forum.service;

import igor.forum.CheckCommentOwner;
import igor.forum.CheckIsLogged;
import igor.forum.CheckPostOwner;
import igor.forum.dao.CommentDao;
import igor.forum.dao.PostDao;
import igor.forum.model.Comment;
import igor.forum.model.Post;
import igor.forum.model.UserForum;
import org.springframework.beans.factory.annotation.Autowired;
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
    CommentDao commentDao;
    @Autowired
    UserService userService;

    @CheckIsLogged
    public Post createPost(Post post,String usernameOwner){
        post.setOwner(usernameOwner);
        return createPost(post);
    }

    public Post createPost(@Valid Post post){
       Post saved = dao.save(post);
       return post;
    }

    @CheckIsLogged
    public Comment createComment(Comment comment,String owner){
        comment.setOwner(owner);
        Comment c = createComment(comment);
        return comment;
    }
    public Comment createComment(@Valid Comment comment){
        Comment c = commentDao.save(comment);
        return comment;
    }

    @CheckCommentOwner
     public Comment getCommentForEdit(Long id){
         Comment comment = commentDao.findOne(id);
         return comment;
     }

    @CheckCommentOwner
    public Comment updateComment(Comment comment){
        Comment commentForEdit = getCommentForEdit(comment.getId());
        commentForEdit.setContent(comment.getContent());
        return createComment(commentForEdit);
    }

    @CheckPostOwner
    public Post updatePost(Post post){
        Post persistedPost = getPostForEdit(post.getId());
        persistedPost.setTitle(post.getTitle());
        persistedPost.setContent(post.getContent());
        persistedPost.setCategory(post.getCategory());
        return createPost(persistedPost);
    }

    @CheckPostOwner
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

    public boolean isPostOwner(Post post, String owner){
        return Optional.ofNullable(post).map(Post::getOwner).map(p->owner.equals(p)).orElse(false);
    }
    public boolean isCommentOwner(Comment comment, String owner){
        return Optional.ofNullable(comment).map(Comment::getOwner).map(p->owner.equals(p)).orElse(false);
    }
}
