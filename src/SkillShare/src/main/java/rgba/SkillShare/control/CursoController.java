package rgba.SkillShare.control;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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


import rgba.SkillShare.data.PutCurso;
import rgba.SkillShare.model.Arquivo;
import rgba.SkillShare.model.Certificado;
import rgba.SkillShare.model.Curso;
import rgba.SkillShare.model.Gestor;
import rgba.SkillShare.model.Prova;
import rgba.SkillShare.model.Thumb;
import rgba.SkillShare.repository.CursoRepository;
import rgba.SkillShare.repository.GestorRepository;
import rgba.SkillShare.repository.ProvaRepository;
import rgba.SkillShare.repository.TutorRepository;

/**
 *  Classe que define os endpoints para curso
 *  @author Nicholas Roque
 */
@RestController
@CrossOrigin
@RequestMapping("/cursos")
public class CursoController {

    @Autowired 
    CursoRepository cursoRepository;

    @Autowired 
    GestorRepository gestorRepository;

    @Autowired 
    TutorRepository tutorRepository;
    
    @Autowired 
    ProvaRepository provaRepository;

    /** 
    *  Endpoint para cadastro de curso.
    * @param curso
    * @author Nicholas Roque
     * @throws IOException
    */
    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Curso createCurso(@RequestParam MultipartFile th, Curso curso, String cpf) throws IOException{
        if(!gestorRepository.existsById(cpf)){
            new ResponseStatusException(HttpStatus.NOT_FOUND,"Gestor não encontrado para o cpf informado.");

        }
        
        Arquivo a = new Arquivo(th.getOriginalFilename(),th.getBytes(),th.getContentType());
        Thumb thumb = new Thumb();
        thumb.setArquivo(a);
        curso.setThumb(thumb);
        curso.setGestor(gestorRepository.findById(cpf).get());
        
        Certificado certificado = new Certificado();
		File file = new ClassPathResource("\\static\\images\\certificado.png").getFile();
		
	    byte[] content = Files.readAllBytes(Paths.get(file.toURI()));
	    
	    Arquivo certificadoImagem = new Arquivo("certificado.png", content, "image/png");
	    
        certificado.setImagemDeFundo(certificadoImagem);
        
        curso.setCertificado(certificado);
        
        return cursoRepository.save(curso);
    }

    /** 
    *  Endpoint para listar todos os cursos.
    * @return Retorna uma lista do objeto Curso com todos os cursos. 
    * @author Nicholas Roque
    */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Curso> getAllCursos(){
        return cursoRepository.findAll();
    }

    /** 
    *  Endpoint para retornar os detalhes de um curso.
    * @return Retorna objeto do tipo Curso com os dados do curso especificado.
    * @param id -> id do curso
    * @author Nicholas Roque
    */
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Curso getCursoById(@PathVariable Long id) {
        return cursoRepository
            .findById(id)
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Curso não encontrado.")
            );
    }

    /** 
    *  Endpoint para retornar os cursos com um determinado gestor.
    * @return Retorna uma lista de objetos do tipo Curso.
    * @param cpf -> cpf do gestor
    * @author Nicholas Roque
    */
    @GetMapping("/gestor/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public List<Curso> getCursosByGestor(@PathVariable String cpf) {
       return gestorRepository
           .findById(cpf).map(gestor->{
                return gestor.getCursos();
           })
           .orElseThrow(()->
               new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum curso encontrado.")
           );
       
    }

     /** 
    *  Endpoint para retornar os detalhes de um curso.
    * @return Retorna objeto do tipo Curso com os dados do curso especificado.
    * @param id -> id do curso
    * @author Nicholas Roque
    */
   @GetMapping("/{id}/gestor")
   @ResponseStatus(HttpStatus.OK)
   public Gestor getGestorByCursoId(@PathVariable Long id) {
       return cursoRepository
           .findById(id).map(curso->{
               return curso.getGestor();
           })
           .orElseThrow(()->
               new ResponseStatusException(HttpStatus.NOT_FOUND,"Curso não encontrado.")
           );
   }
 /** 
    *  Endpoint para atualizar um curso especificado pelo id.
    * @param Long id-> id do curso a ser atualizado
    * @param Curso curso-> objeto do curso a ser atualizado
    * @author Nicholas Roque
    */
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCursoById(@PathVariable Long id,@RequestBody PutCurso curso) {
        cursoRepository
            .findById(id)
            .map(c->{
                c.setTitulo(curso.getNome());
                c.setDescricao(curso.getDescricao());
                if(!c.getGestor().getCpf().equals(curso.getCpfGestor())){
                    gestorRepository
                    .findById(curso.getCpfGestor())
                    .ifPresentOrElse((g)->{
                        c.setGestor(g);
                    }, ()->{
                        new ResponseStatusException(HttpStatus.NOT_FOUND,"Gestor não encontrado para o cpf informado.");   
                    });
                }
                cursoRepository.save(c);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Curso não encontrado para o id informado.")         
            );
    }
    /** 
    *  Endpoint para deletar um curso especificado pelo id.
    * @param Long id-> id do curso a ser deletado
    * @author Nicholas Roque
    */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCursoById(@PathVariable  Long id) {
        cursoRepository
            .findById(id)
            .map(c->{
                c.getTurmas().forEach((t)->{
                    t.getAlunos().removeAll(t.getAlunos());
                });
                cursoRepository.delete(c);
                return ResponseEntity.noContent().build();
            })
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Curso não encontrada para o id informado.")
            );
    }
    
    /**
     * Método usado para adicionar uma prova ou atualizar por uma nova
     * 
     * @param idCurso
     * @param prova
     * @return
     */
    @PostMapping(value = "/setProva")
    public boolean setProva(Long idCurso, @RequestBody Prova prova) {
    	try {
        	Curso curso = cursoRepository.findById(idCurso).get();
        	
        	curso.setProva(prova);
        	
        	cursoRepository.save(curso);
        	
        	return true;
    	}catch (Exception e) {
    		e.printStackTrace();
    		
    		return false;
    	}

    }
    
    @GetMapping(value = "/getProva")
    public ResponseEntity<Prova> getProva(Long idCurso) {
    	try {
    		Curso curso = cursoRepository.findById(idCurso).get();
    		
    		Prova prova = curso.getProva();
    		
    		return new ResponseEntity<Prova>(prova, HttpStatus.OK);
    	}catch (Exception e) {
    		e.printStackTrace();
    		
    		return ResponseEntity.noContent().build();
    	}
    	
    }
    
}