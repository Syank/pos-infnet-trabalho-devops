package rgba.SkillShare.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

/**
 *  Classe que define o arquivo
 *  @author Nicholas Roque
 */
@Entity(name="arquivo")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String nomeArquivo;

    @Lob
    @Column(nullable = false)
    private byte[] conteudo;

    @Column(nullable = false)
    private String tipoArquivo;

    /** 
    *  Cria uma instância da classe Arquivo.
    * @param nomeArquivo -> nome do arquivo
    * @param conteudo -> arquivo
    * @param tipoArquivo -> tipoArquivo
    * @author Nicholas Roque
    */
    public Arquivo(String nomeArquivo,byte[] conteudo,String tipoArquivo){
        this.nomeArquivo = nomeArquivo;
        this.conteudo = conteudo;
        this.tipoArquivo = tipoArquivo;
    }

}
