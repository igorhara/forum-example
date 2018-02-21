package igor.forum.controller.rest;

import igor.forum.model.Post;
import igor.forum.model.UserForum;
import igor.forum.service.PostService;
import igor.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by igorhara on 20/01/2018.
 */
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService service;

    @Autowired
    UserService userService;

    @GetMapping(path = "/all")
    public List<Post> getAllPosts(){
       return service.listPosts();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post){
        post.setOwner("first");
       return service.createPost(post);
    }
    @GetMapping(path = "/edit/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Post> getPostForEdit(@PathVariable Long id){
        return Optional.ofNullable(service.getPostForEdit(id)).map(ResponseEntity::ok).orElse(new
                ResponseEntity<Post>(HttpStatus.NOT_FOUND));
    }
    @PutMapping
    public Post updatePost(@RequestBody Post post){
        return service.updatePost(post);
    }
}
