package rgba.SkillShare.control;

import rgba.SkillShare.model.Tutor;

import rgba.SkillShare.repository.TutorRepository;

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
 *  Classe que define os endpoints para tutor.
 *  @author Nicholas Roque
 */
@RestController
@CrossOrigin
@RequestMapping("/tutor")
public class TutorController {

    @Autowired 
    TutorRepository tRepository;

    /** 
    *  Endpoint para cadastro de tutor.
    * @param tutor
    * @author Nicholas Roque
    */
    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Tutor createTutor(@RequestBody Tutor tutor){
        return tRepository.save(tutor);
    }

    /** 
    *  Endpoint para listar todos os tutores.
    * @return Retorna uma lista do objeto Tutor com todos os tutores. 
    * @author Nicholas Roque
    */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Tutor> getAllTutores(){
        return tRepository.findAll();
    }

    /** 
    *  Endpoint para retornar os detalhes de um tutor.
    * @return Retorna objeto do tipo Tutor com os dados do tutor.
    * @param cpf -> cpf do tutor
    * @return Tutor
    * @author Nicholas Roque
    */
    @GetMapping("{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public Tutor getTutorByCpf(@PathVariable String cpf) {
        return tRepository
            .findById(cpf)
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário do tipo tutor não encontrado.")
            );
    }

    /** 
    *  Endpoint para deletar um tutor especificado pelo cpf.
    * @param cpf-> cpf do tutor a ser deletado
    * @author Nicholas Roque
    */
    @DeleteMapping("{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTutorByCpf(@PathVariable String cpf) {
        tRepository
            .findById(cpf)
            .map(t->{
                tRepository.delete(t);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Tutor não encontrado.")         
            );
    }

    /** 
    *  Endpoint para atualizar um tutor especificado pelo id.
    * @param cpf-> cpf do tutor a ser atualizado
    * @param tutor-> objeto do tutor a ser atualizado
    * @author Nicholas Roque
    */
    @PutMapping("{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTutorByCpf(@PathVariable String cpf,@RequestBody Tutor tutor) {
        tRepository
            .findById(cpf)
            .map(t->{
                tutor.setSenha(t.getSenha());
                tRepository.save(tutor);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Tutor não encontrado.")         
            );
    }
	
}
