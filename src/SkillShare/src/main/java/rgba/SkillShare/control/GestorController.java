package rgba.SkillShare.control;

import rgba.SkillShare.model.Gestor;

import rgba.SkillShare.repository.GestorRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 *  Classe que define os endpoints para gestor
 *  @author Nicholas Roque
 */
@CrossOrigin
@RestController
@RequestMapping("/gestor")
public class GestorController {

    @Autowired 
    GestorRepository gRepository;

    /** 
    *  Endpoint para cadastro de gestor.
    * @param gestor
    * @author Nicholas Roque
    */
    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Gestor createGestor(@RequestBody Gestor gestor){
        return gRepository.save(gestor);
    }

    /** 
    *  Endpoint para listar todos os gestores.
    * @return Retorna uma lista do objeto Gestor com todos os gestores. 
    * @author Nicholas Roque
    */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Gestor> getAllGestores(){
        return gRepository.findAll();
    }

    /** 
    *  Endpoint para retornar os detalhes de um gestor.
    * @return Retorna objeto do tipo Gestor com os dados do gestor.
    * @param cpf -> cpf do gestor
    * @return Gestor
    * @author Nicholas Roque
    */
    @GetMapping("{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public Gestor getGestorByCpf(@PathVariable String cpf) {
        return gRepository
            .findById(cpf)
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário do tipo gestor não encontrado.")
            );
    }

    /** 
    *  Endpoint para deletar um gestor especificado pelo cpf.
    * @param cpf-> cpf do gestor a ser deletado
    * @author Nicholas Roque
    */
    @DeleteMapping("{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGestorByCpf(@PathVariable String cpf) {
        gRepository
            .findById(cpf)
            .map(g->{
                gRepository.delete(g);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Gestor não encontrado.")         
            );
    }

    /** 
    *  Endpoint para atualizar um gestor especificado pelo id.
    * @param cpf-> cpf do gestor a ser atualizado
    * @param gestor-> objeto do gestor a ser atualizado
    * @author Nicholas Roque
    */
    @PutMapping("{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGestorByCpf(@PathVariable String cpf,@RequestBody Gestor gestor) {
        gRepository
            .findById(cpf)
            .map(g->{
                gestor.setSenha(g.getSenha());
                gRepository.save(gestor);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Gestor não encontrado.")         
            );
    }
	
}