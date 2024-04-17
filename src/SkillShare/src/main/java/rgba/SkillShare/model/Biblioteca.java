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
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

/**
 *  Classe que define o arquivo do tipo biblioteca 
 *  @author Nicholas Roque
 */
@Entity(name="biblioteca")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String autor;

    @Column(nullable = false)
    private String titulo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_arquivo",referencedColumnName = "id")
    private Arquivo arquivo;

    /** 
    *  Cria uma instância da classe Biblioteca.
    * @param tituloArquivo -> titulo do arquivo
    * @param conteudo -> arquivo
    * @param tipoArquivo -> tipoArquivo
    * @author Nicholas Roque
    */
    public Biblioteca(String titulo){
        this.titulo = titulo;
    }

    public Biblioteca(String titulo,String autor){
        this.titulo = titulo;
        this.autor = autor;
    }

}