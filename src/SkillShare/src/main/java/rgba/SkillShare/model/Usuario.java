package rgba.SkillShare.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

/**
 *  Classe abstrata que define os usuários
 *  @author Nicholas Roque
 */
@Entity(name = "usuario")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "idUsuario")
    private Set<Contato> contatos = new HashSet<Contato>();
   
    //NÃO RETIRAR
    public Usuario(String cpf, String nome, String email, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public Usuario(String cpf) {
        this.cpf = cpf;
    }

}
