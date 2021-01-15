package ru.napoleonit.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@Table(name = "polls")
@Builder(toBuilder = true)
@DynamicUpdate @DynamicInsert
@SelectBeforeUpdate
@Setter  @Getter
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Poll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pollname", nullable = false)
    private String pollName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_begin", nullable = false)
    private String dateBegin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_end", nullable = false)
    private String dateEnd;
	
    @Column(name = "active", nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference
    private List<Question> questions;

}
