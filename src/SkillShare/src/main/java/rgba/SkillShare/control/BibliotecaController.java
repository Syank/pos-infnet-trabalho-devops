package rgba.SkillShare.control;

import java.io.IOException;
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
import rgba.SkillShare.model.Biblioteca;
import rgba.SkillShare.repository.BibliotecaRepository;


/**
 *  Classe que define os endpoints para biblioteca
 *  @author Nicholas Roque
 */
@RestController
@CrossOrigin
@RequestMapping("/biblioteca")
public class BibliotecaController {

    @Autowired 
    BibliotecaRepository bibliotecaRepository;

    /** 
    *  Endpoint para cadastro de material na biblioteca.
    * @author Nicholas Roque
    * @param Biblioteca
    * @throws IOException
    */
    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Biblioteca createBiblioteca(@RequestParam MultipartFile arq,Biblioteca b) throws IOException {
        Arquivo a = new Arquivo(arq.getOriginalFilename(),arq.getBytes(),arq.getContentType());
        b.setArquivo(a);
		return bibliotecaRepository.save(b);
	}
    /** 
    *  Endpoint para retornar todos os materiais presentes nas bibliotecas.
    * @return Retorna uma lista de objetos do tipo Biblioteca.
    * @author Nicholas Roque
    */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Biblioteca> getBiblioteca() {
        return bibliotecaRepository.findAll();
    }

    /** 
    *  Endpoint para retornar todos os materiais presentes nas bibliotecas.
    * @return Retorna uma lista de objetos do tipo Biblioteca.
    * @author Nicholas Roque
    */
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Biblioteca getBibliotecaById(@PathVariable Long id) {
        return bibliotecaRepository.findById(id)
            .orElseThrow(()->
               new ResponseStatusException(HttpStatus.NOT_FOUND,"Curso não encontrado.")
            );
    }

    /** 
    *  Endpoint para deletar uma biblioteca especificada pelo id.
    * @param id-> id da biblioteca a ser deletada
    * @author Nicholas Roque
    */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBibliotecaById(@PathVariable Long id) {
        bibliotecaRepository
            .findById(id)
            .map(b->{
                bibliotecaRepository.delete(b);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Biblioteca não encontrada.")         
            );
    }

    /** 
    *  Endpoint para atualizar uma biblioteca especificada pelo id.
    * @param id-> id da biblioteca a ser atualizada.
    * @param biblioteca-> objeto da biblioteca a ser atualizada.
    * @author Nicholas Roque
    */
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBibliotecaById(@PathVariable Long id,@RequestBody Biblioteca biblioteca) {
        bibliotecaRepository
            .findById(id)
            .map(b->{
                biblioteca.setArquivo(b.getArquivo());
                bibliotecaRepository.save(biblioteca);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Biblioteca não encontrado.")         
            );
    }

    
}
