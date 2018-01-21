package igor.forum.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by igorhara on 18/01/2018.
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Basic
    String content;

    @NotNull
    String owner;

    @CreationTimestamp
    Timestamp creationDate;

    @ManyToOne
    @NotNull
    Post post;
}
