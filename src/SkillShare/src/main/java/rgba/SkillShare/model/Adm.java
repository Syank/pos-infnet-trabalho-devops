package rgba.SkillShare.model;

import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;


/**
 *   Classe que define os usuários do tipo administrador
 *  @author Nicholas Roque
 */
@Entity(name="adm")
@NoArgsConstructor @Getter @Setter @ToString
public class Adm extends Usuario{


    /** 
    *  Cria uma instância da classe Adm
    * @param cpf -> cpf do administrador
    * @param nome -> nome do administrador
    * @param email -> email do administrador
    * @param senha -> senha do administrador
    * @author Nicholas Roque
    */

    public Adm(String cpf,String nome,String email,String senha) { 
        super(cpf,nome,email,senha);
    }
    public Adm(String cpf,String nome,String email) { 
        super(cpf,nome,email);
    }
}