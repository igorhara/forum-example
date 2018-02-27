package igor.forum.config;

import igor.forum.dto.CommentDTO;
import igor.forum.dto.PostDTO;
import igor.forum.model.Comment;
import igor.forum.model.Post;
import igor.forum.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
