package rgba.SkillShare.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rgba.SkillShare.converters.JSONObjectConverter;

@Entity(name = "feedback")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String notaFinal;

	@Column(columnDefinition = "text")
	private String comentarioTutor;

	@Column
	private int compreendimento;
	
	@Column
	private String nomeCurso;

	@Column(columnDefinition = "text")
	@Convert(converter = JSONObjectConverter.class)
	private JSONObject acertosErrosProva;

	@OneToOne
	private Prova prova;

}

