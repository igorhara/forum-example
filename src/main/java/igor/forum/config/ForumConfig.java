package igor.forum.config;

import igor.forum.dto.CommentDTO;
import igor.forum.dto.PostDTO;
import igor.forum.model.Comment;
import igor.forum.model.Post;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by igorhara on 18/01/2018.
 */
@Configuration
public class ForumConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Post.class, PostDTO.class);
        modelMapper.createTypeMap(Comment.class,CommentDTO.class).addMapping(src->src.getPost().getId(),
                CommentDTO::setPostId);
        return modelMapper;
    }

}
