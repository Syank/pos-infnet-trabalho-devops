package rgba.SkillShare.control;

import jakarta.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import rgba.SkillShare.utils.SessionManager;

@Controller
public class PathController {

	@GetMapping(value = "/")
	public String index(HttpSession sessao) {
		if(sessao.isNew()) {
			JSONObject user = new JSONObject();
			
			user.put("type", "guest");
			user.put("userName", "guest");
			
			sessao.setAttribute("user", user);

		}
		
		return "home";
		
	}
	
	@GetMapping(value = "/administracao")
	public String administracao(HttpSession sessao) {
		boolean permitidoAdmin = SessionManager.checkPermission(sessao, "admin");
		boolean permitidoGestor = SessionManager.checkPermission(sessao, "gestor");
		
		if(permitidoAdmin || permitidoGestor) {
			return "administracao";
		}else {
			return "redirect:login";
		}
		
	}
	
	@GetMapping(value = "/tutoria")
	public String tutorPage(HttpSession sessao) {
		boolean permitido = SessionManager.checkPermission(sessao, "tutor");
			
		if(permitido) {
			return "tutorHomePage";
		}
		else {
			return "redirect:login";
		}
	
	}
	
	@GetMapping(value = "/alunoPage")
	public String alunoPage(HttpSession sessao) {
		boolean permitido = SessionManager.checkPermission(sessao, "aluno");
			
		if(permitido) {
			return "paginaAluno";
		}
		else {
			return "redirect:login";
		}
	
	}//
	
	@GetMapping(value = "/login")
	public String login(HttpSession sessao) {
		if(sessao.isNew()) {
			JSONObject user = new JSONObject();
			
			user.put("type", "guest");
			user.put("userName", "guest");
			sessao.setAttribute("user", user);
		}
		
		return "login";
	}
	
	@GetMapping(value = "/prova")
	public String fazerProva(HttpSession sessao) {
		return "prova";
	}
	
	@GetMapping(value = "/acervo")
	public String paginaBiblioteca(HttpSession sessao) {
		return "paginaBiblioteca";
	}
	
	@GetMapping(value = "/materialAcervo")
	public String materialDoAcervo() {
		return "materialAcervo";
	}
	
	@GetMapping(value = "/upload")
	public String uploadImagem() {
		return "upload";
	}
	
	@GetMapping(value = "/noticias")
	public String noticia() {
		return "noticias";
	}
	
	@GetMapping(value = "/verPilulas")
	public String verPilulas() {
		return "pilulas";
	}
	
	@GetMapping(value = "/meusCursosAluno")
	public String meusCursosAluno () {
		return "meusCursosAluno";
	}

	@GetMapping(value = "/scorm")
	public String scormTest () {
		return "scormTest";
	}

	@GetMapping(value = "/detalhesCurso{id}")
	public String detalhesCurso(@PathVariable String id) {
		return "detalhesCurso";
	}
	
	@GetMapping(value = "/tutoriaCurso{id}")
	public String detalhesCursoTutor(@PathVariable String id) {
		return "turmasTutorCurso";
	}

	@GetMapping(value = "/tutoriaAluno{id}")
	public String detalhesAlunosTutor(@PathVariable String id) {
		return "alunosTutor";
	}

	@GetMapping(value = "/certificado")
	public String certificado() {
		return "certificado";
	}
	
	@GetMapping(value = "/feedback{id}")
	public String feedbackAluno(@PathVariable String id) {
		return "avaliacaoAluno";
	}
	
	@GetMapping(value = "/feedbackAluno{id}")
	public String exibicaofeedbackAluno(@PathVariable String id) {
		return "feedback";
	}
	
	@GetMapping(value = "/meusFeedbacks")
	public String meusFeedbacks() {
		return "meusFeedbacks";
	}
	
	@GetMapping(value = "/minhaConta")
	public String minhaConta() {
		return "minhaConta";
	}
}
 