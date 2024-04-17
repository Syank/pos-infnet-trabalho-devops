package rgba.SkillShare.model;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



/**
 *  Classe que define os usuários do tipo aluno
 *  @author Nicholas Roque
 */
@Entity(name="aluno")
@NoArgsConstructor @Getter @Setter @ToString
public class Aluno extends Usuario{ 

	@OneToMany(cascade = CascadeType.ALL)
	private List<Certificado> certificados;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Feedback> feedbacks;
	
    @ManyToMany(mappedBy = "alunos",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Turma> turmas = new HashSet<Turma>();

    /** 
    *  Cria uma instância da classe Aluno
    * @param cpf -> cpf do aluno
    * @param nome -> nome do aluno
    * @param email -> email do aluno
    * @param senha -> senha do aluno
    * @author Nicholas Roque
    */
    
    public Aluno(String cpf,String nome,String email,String senha) { 
        super(cpf,nome,email,senha);
    }
    public Aluno(String cpf,String nome,String email) { 
        super(cpf,nome,email);
    }

    public void addTurma(Turma t){
        this.turmas.add(t);
        t.getAlunos().add(this);
    }

    public void removeTurma(Turma t){
        this.turmas.remove(t);
        t.getAlunos().remove(this);
    }

}
