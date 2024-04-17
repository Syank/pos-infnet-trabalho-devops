package rgba.SkillShare.data;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import rgba.SkillShare.model.Turma;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class GetTurmasByAlunoSession {
	
	private String cpfAluno;  
	private Set<Turma> turmas = new HashSet<Turma>();

}
