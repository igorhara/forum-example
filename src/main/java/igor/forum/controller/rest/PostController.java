package igor.forum.controller.rest;

import igor.forum.model.Post;
import igor.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by igorhara on 20/01/2018.
 */
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService service;

    @GetMapping(path = "/all")
    public List<Post> getAllPosts(){
       return service.listPosts();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post){
       return service.createPost(post);
    }
}
