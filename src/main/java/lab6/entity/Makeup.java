package lab6.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "makeups")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Makeup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "lesson_id")
	private Lesson lesson;

	private LocalDate makeupDate;
	private String comment;

}