package rgba.SkillShare.control;

import rgba.SkillShare.model.Contato;

import rgba.SkillShare.repository.ContatoRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import java.util.List;

/**
 *  Classe que define os endpoints para contato.
 *  @author Nicholas Roque
 */
@RestController
@CrossOrigin
@RequestMapping("/contato")
public class ContatoController {

    @Autowired 
    ContatoRepository cRepository;

    /** 
    *  Endpoint para listar todos os contatos.
    * @return Retorna uma lista do objeto Contato com todos os contatos. 
    * @author Nicholas Roque
    */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Contato> getAllContatos(){
        return cRepository.findAll();
    }

    /** 
    *  Endpoint para retornar os contatos de um usuário específico.
    * @return Retorna objeto do tipo Contato com os dados dos contatos do usuário.
    * @param cpf -> cpf do usuario
    * @return Contato
    * @author Nicholas Roque
    */
    @GetMapping("{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public Contato getContatoByCpf(@PathVariable String cpf) {
        return cRepository
            .findByCpf(cpf)
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário do tipo gestor não encontrado.")
            );

    }
}
