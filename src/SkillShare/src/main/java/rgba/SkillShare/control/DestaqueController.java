package rgba.SkillShare.control;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import rgba.SkillShare.model.Arquivo;
import rgba.SkillShare.model.Destaque;
import rgba.SkillShare.model.Thumb;
import rgba.SkillShare.repository.DestaqueRepository;

/**
 *  Classe que define os endpoints para pílula
 *  @author Nicholas Roque
 */
@RestController
@CrossOrigin
@RequestMapping("/destaques")
public class DestaqueController {

    @Autowired 
    DestaqueRepository destaqueRepository;


    /** 
    *  Endpoint para cadastro de destaque.
    * @author Nicholas Roque
    * @param curso
    * @throws IOException
    */
    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Destaque createDestaqueNoticia(@RequestParam MultipartFile arq, Destaque destaque) throws IOException {
        Arquivo a = new Arquivo(arq.getOriginalFilename(),arq.getBytes(),arq.getContentType());
        Thumb t = new Thumb();
        t.setArquivo(a);
        destaque.setThumb(t);
		return destaqueRepository.save(destaque);
	}

    /** 
    *  Endpoint para retornar todos os destaques.
    * @return Retorna uma lista de objetos do tipo Destque.
    * @author Nicholas Roque
    */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Destaque> getDestaques() {
        return destaqueRepository.findAll();
    }

    /** 
    *  Endpoint para retornar um destaque especificado pelo id.
    * @return Retorna um objeto do tipo Destaque.
    * @author Nicholas Roque
    */
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Destaque getDestaqueById(@PathVariable Long id) {
        return destaqueRepository
            .findById(id)
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Destaque não encontrado.")         
            );
    }
    

	 /** 
    *  Endpoint para deletar um destaque especificado pelo id.
    * @param id-> id do destaque a ser deletado
    * @author Nicholas Roque
    */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDestaqueById(@PathVariable Long id) {
        destaqueRepository
            .findById(id)
            .map(d->{
                destaqueRepository.delete(d);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Destaque não encontrado.")         
            );
    }

    /** 
    *  Endpoint para retornar um destaque especificado pelo id.
    * @param id-> id do destaque a ser atualizado
    * @param destaque-> objeto do destaque a ser atualizado
    * @author Nicholas Roque
    */
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDestaqueById(@PathVariable Long id,@RequestBody Destaque destaque) {
        destaqueRepository
            .findById(id)
            .map(d->{
                destaque.setThumb(d.getThumb());
                destaque.setData(LocalDateTime.now());
                destaqueRepository.save(destaque);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Destaque não encontrado.")         
            );
    }
}