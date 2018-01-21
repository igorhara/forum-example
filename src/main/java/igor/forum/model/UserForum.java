package igor.forum.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by igorhara on 18/01/2018.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode()
public class UserForum {

    @Id
    String username;

    @NotNull
    String password;
}
