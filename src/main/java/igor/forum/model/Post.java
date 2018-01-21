package igor.forum.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.SortedSet;

/**
 * Created by igorhara on 18/01/2018.
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"comments"})
@Entity
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Basic
    @NotNull
    String title;


    @Basic
    @NotNull
    String content;

    @Enumerated(EnumType.STRING)
    @NotNull
    CategoryPost category;

    @NotNull
    String owner;

    @CreationTimestamp
    Timestamp creationDate;

    @OneToMany(mappedBy = "post")
    @OrderBy("creationDate asc")
    SortedSet<Comment> comments;

}
