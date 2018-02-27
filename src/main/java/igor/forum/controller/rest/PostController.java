package igor.forum.controller.rest;

import igor.forum.dto.CommentDTO;
import igor.forum.dto.PostDTO;
import igor.forum.dto.PostSimpleDTO;
import igor.forum.model.Comment;
import igor.forum.model.Post;
import igor.forum.model.UserForum;
import igor.forum.service.PostService;
import igor.forum.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by igorhara on 20/01/2018.
 */
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService service;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserService userService;

    @GetMapping(path = "/all")
    public List<PostSimpleDTO> getAllPosts(){
       return service.listPosts().stream().map(this::convertToPostSimpleDto).collect
               (Collectors.toList());
    }

    @PostMapping
    public Post createPost(@RequestBody Post post){
        post.setOwner("first");
       return service.createPost(post);
    }
    @GetMapping(path = "/edit/{id}")
    public ResponseEntity<PostSimpleDTO> getPostForEdit(@PathVariable Long id){
        return Optional.ofNullable(service.getPostForEdit(id)).map(this::convertToPostSimpleDto).map
                (ResponseEntity::ok).orElse(new
                ResponseEntity<PostSimpleDTO>(HttpStatus.NOT_FOUND));
    }
    @GetMapping(path = "/view/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long id){
        return Optional.ofNullable(service.getPost(id)).map(this::convertoToPostDTO).map(ResponseEntity::ok).orElse(new
                ResponseEntity<PostDTO>(HttpStatus.NOT_FOUND));
    }
    @PutMapping
    public Post updatePost(@RequestBody Post post){
        return service.updatePost(post);
    }

    private PostSimpleDTO convertToPostSimpleDto(Post post) {

        PostSimpleDTO postDto = modelMapper.map(post, PostSimpleDTO.class);
        return postDto;
    }

    private PostDTO convertoToPostDTO(Post post){
        PostDTO map = modelMapper.map(post, PostDTO.class);
        return map;
    }

}
