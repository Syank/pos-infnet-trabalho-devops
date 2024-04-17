package rgba.SkillShare.model;

import java.time.LocalDateTime;

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
 *  Classe que define as postagens de eventos.
 *  @author Nicholas Roque
 */
@Entity(name = "evento")
@NoArgsConstructor @Getter @Setter @AllArgsConstructor @ToString
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition="TEXT")
    private String sinopse;

    @Column(nullable = false, columnDefinition="TEXT")
    private String conteudo;

    @Column
    private LocalDateTime data = LocalDateTime.now();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_thumb",referencedColumnName = "id")
    private Thumb thumb;

    /** 
    *  Cria uma instância da classe Evento.
    * @param titulo -> titulo do evento.
    * @param sinopse -> sinopse do evento.
    * @param conteudo -> conteudo do evento.
    * @author Nicholas Roque
    */

    public Evento(String titulo,String sinopse,String conteudo) {
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.conteudo = conteudo;
    }

}