package igor.forum;

import igor.forum.model.CategoryPost;
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

			Post firstPost = new Post();
			firstPost.setTitle("First Post!");
			firstPost.setContent(" This is the first post created!");
			firstPost.setCategory(CategoryPost.SUGGESTION);
			firstPost.setOwner(user.getUsername());
			postService.createPost(firstPost);
			log.info("1st post created");

		};
	}
}
