package igor.forum;

import igor.forum.model.CategoryPost;
import igor.forum.model.Comment;
import igor.forum.model.Post;
import igor.forum.model.UserForum;
import igor.forum.service.PostService;
import igor.forum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.stream.IntStream;

@SpringBootApplication
public class ForumApplication {

	private static final Logger log = LoggerFactory.getLogger(ForumApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

	@Bean
	@Order(1)
	public CommandLineRunner createUser(UserService userService) {
		return (args) -> {
			UserForum userForum = new UserForum();
			userForum.setUsername("first");
			userForum.setPassword("pass");
			userService.createUser(userForum);
			log.info("Created user: "+userForum.getUsername());
		};
	}
	@Bean
	@Order(2)
	public CommandLineRunner createSomePosts(UserService userService, PostService postService) {
		return (args) -> {
			UserForum user = userService.getUserByUsername("first");

			IntStream.range(1,11).forEach(i->{
				Post post = new Post();
				post.setTitle("Post number: "+i);
				post.setContent(" This is the "+i+ " post created!");
				post.setCategory(CategoryPost.values()[i%CategoryPost.values().length]);
				post.setOwner(user.getUsername());
				postService.createPost(post);
				log.info("1st post created");
			});
			IntStream.range(1,11).forEach(i->{

				Comment c = new Comment();
				c.setContent("Content number "+i);
				c.setPost(postService.getPost(Long.valueOf(i)));
				c.setOwner("sec");
				postService.createComment(c);
				log.info(i+"+comment created");
			});





		};
	}
}
