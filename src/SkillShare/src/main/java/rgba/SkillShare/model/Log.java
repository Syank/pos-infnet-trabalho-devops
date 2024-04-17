package rgba.SkillShare.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "log")
public class Log {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column
    String autor;
    
    @Column
    String nivelDeAcesso;
    
    @Column(columnDefinition = "text")
    String acao;
    
    @Column
    String data;
    
    
    
    public Log(String autor, String nivelDeAcesso, String acao) {
    	this.setAutor(autor);
    	this.setNivelDeAcesso(nivelDeAcesso);
    	this.setAcao(acao);
    	
    	String hoje = LocalDate.now().toString();
    	
    	this.setData(hoje);
    	
    }

}
