package rgba.SkillShare.control;

import rgba.SkillShare.model.Adm;
import rgba.SkillShare.repository.AdmRepository;

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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *  Classe que define os endpoints para adm
 *  @author Nicholas Roque
 */
@RestController
@CrossOrigin
@RequestMapping("/adm")
public class AdmController {

    @Autowired 
    AdmRepository admRepository;

    /** 
    *  Endpoint para cadastro de administrador.
    * @param adm
    * @author Nicholas Roque
    */
    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Adm createAdm(@RequestBody Adm adm){
        System.out.println(adm.toString());
        return admRepository.save(adm);
    }
    /** 
    *  Endpoint para listar todos os administradores.
    * @return Retorna uma lista do objeto Adm com todos os administradores.
    * @author Nicholas Roque
    */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Adm> getAllAdm(){
        return admRepository.findAll();
    }

    /** 
    *  Endpoint para retornar os detalhes de um administrador.
    * @return Retorna objeto do tipo Adm com os dados do usuário.
    * @param cpf -> cpf do administrador
    * @return Adm
    * @author Nicholas Roque
    */
    @GetMapping("{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public Adm getAdmByCpf(@PathVariable String cpf) {
        return admRepository
            .findById(cpf)
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário do tipo adm não encontrado.")
            );
    }


    /** 
    *  Endpoint para deletar um administrador especificado pelo cpf.
    * @param cpf-> cpf do administrador a ser deletado
    * @author Nicholas Roque
    */
    @DeleteMapping("{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdmByCpf(@PathVariable String cpf) {
        admRepository
            .findById(cpf)
            .map(a->{
                admRepository.delete(a);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Adm não encontrado.")         
            );
    }

    /** 
    *  Endpoint para atualizar um adm especificado pelo id.
    * @param cpf-> cpf do administrador a ser atualizado
    * @param adm-> objeto do administrador a ser atualizado
    * @author Nicholas Roque
    */
    @PutMapping("{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAdmByCpf(@PathVariable String cpf,@RequestBody Adm adm) {
        admRepository
            .findById(cpf)
            .map(a->{
                adm.setSenha(a.getSenha());
                admRepository.save(adm);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Adm não encontrado.")         
            );
    }
	
}