package rgba.SkillShare.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="certificado")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Certificado {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column
    private String mensagem;
    
    @Column
    private String nomeCurso;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_arquivo", referencedColumnName = "id")
    private Arquivo imagemDeFundo;
    
}
