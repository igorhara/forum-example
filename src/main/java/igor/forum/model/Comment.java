package igor.forum.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Optional;

/**
 * Created by igorhara on 18/01/2018.
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Comment /*implements  Comparable<Comment>*/{

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

    /*@Override
    public int compareTo(Comment o) {
        if(o==null){return 1;}
        if(this.getCreationDate()==null && o.getCreationDate()!=null){return -1;}
        if(this.getCreationDate()!=null && o.getCreationDate()==null){return 1;}
        if(this.getCreationDate()==o.getCreationDate()){return 0;}
        return this.getCreationDate().compareTo(o.getCreationDate());

    }*/
}
