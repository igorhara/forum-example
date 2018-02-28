package igor.forum;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by igorhara on 24/02/2018.
 */

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('USER')")
@PostAuthorize("@postService.isPostOwner(returnObject, principal?.username)")
public @interface CheckPostOwner {
}
