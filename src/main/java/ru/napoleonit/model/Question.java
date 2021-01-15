package ru.napoleonit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "questions")
@Builder(toBuilder = true)
@DynamicUpdate @DynamicInsert
@SelectBeforeUpdate
@Setter @Getter
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Question implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "questiontext", unique = true, nullable = false)
	private String questionText;

	@ManyToOne
	@JoinColumn(name = "poll_id")
	@JsonBackReference
	private Poll poll;

}
