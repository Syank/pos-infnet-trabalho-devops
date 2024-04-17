package rgba.SkillShare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import rgba.SkillShare.model.Adm;
import rgba.SkillShare.model.Aluno;
import rgba.SkillShare.model.Gestor;
import rgba.SkillShare.model.Log;
import rgba.SkillShare.model.Tutor;
import rgba.SkillShare.repository.AdmRepository;
import rgba.SkillShare.repository.AlunoRepository;
import rgba.SkillShare.repository.BibliotecaRepository;
import rgba.SkillShare.repository.CursoRepository;
import rgba.SkillShare.repository.GestorRepository;
import rgba.SkillShare.repository.LogRepository;
import rgba.SkillShare.repository.TurmaRepository;
import rgba.SkillShare.repository.TutorRepository;

@SpringBootApplication
public class SkillShareApplication implements CommandLineRunner {
    
	@Autowired 
    AdmRepository admRepository;
	@Autowired
	AlunoRepository alunoRepository;
    @Autowired 
    GestorRepository gRepository;
    @Autowired 
    TutorRepository tRepository;
	@Autowired
	BibliotecaRepository bibliotecaRepository;
	@Autowired 
    CursoRepository cursoRepository;
	@Autowired 
    TurmaRepository turmaRepository;
    @Autowired 
    LogRepository logRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SkillShareApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Log log = new Log("Sistema", "Root", "Criação de um log para a inicialização do sistema");
		logRepository.save(log);
		
		//coloque algum codigo que precisa ser executado quando o servidor subir aqui
		if(admRepository.findAll().isEmpty()) {
			Adm adm = new Adm("12345678900", "Bárbara Port", "barbara.port@skillshare.com", "12345");
			admRepository.save(adm);
		}

		if(gRepository.findAll().isEmpty()) {
			Gestor gestor = new Gestor("11111111111", "Henrique dos Santos", "henrique.santos@skillshare.com", "12345");
			gRepository.save(gestor);
			
			Gestor nicholasGestor = new Gestor("01234567899", "Nicholas dos Santos", "nicholas.roque@skillshare.com", "12345");
			gRepository.save(nicholasGestor);
		}

//		Curso curso = new Curso("Curso de Informática Básica","Neste curso, serão apresentados os fundamentos da informática, além de como utilizar o pacote Office e como o computador funciona.");
//		curso.setGestor(nicholasGestor);
//
//		Curso curso2= new Curso("Curso de Manutenção de Computadores","Neste curso, será apresentada uma introdução sobre o hardware dos computadores.");
//		curso2.setGestor(nicholasGestor);
//
//		List<Curso> cursos= Arrays.asList(curso,curso2);

//		cursoRepository.saveAll(cursos);
		
		if(tRepository.findAll().isEmpty()) {
			Tutor tutor = new Tutor("22222222222", "Nícolas Rafael Pereira", "nicholas.pereira@skillshare.com", "12345");
			tRepository.save(tutor);
			
			Tutor nicholasTutor = new Tutor("92359235923","Nicholas Tutor dos Santos","nicholas.tutor@rgba.com.br","12345");
			tRepository.save(nicholasTutor);

		}
		
		if(alunoRepository.findAll().isEmpty()) {
			Aluno rafael = new Aluno("98765432100", "Rafael Furtado Rodrigues dos Santos", "rafael.furtado@rgba.com.br", "12345");
			Aluno nicholasAluno = new Aluno("50553650807", "Nicholas Gabriel dos Santos Roque", "nicholas.aluno@rgba.com.br", "12345");
			
//			Turma turma1 = new Turma();
//			turma1.setTutor(nicholasTutor);
//			turma1.setCurso(curso);
//
//			Turma turma2 = new Turma();
//			turma2.setTutor(nicholasTutor);
//			turma2.setCurso(curso2);

			alunoRepository.save(rafael);
			alunoRepository.save(nicholasAluno);


			//isso daqui adiciona 2 turmas e os alunos nelas ao iniciar o sistema,deixei comentado mas pode descomentar pra usar(tem que tirar o alunoRepository.save dos dois alunos acima pra isso)
			/* //add aluno to turma
			turma1.addAluno(rafael);
			turma1.addAluno(nicholasAluno);
			turma2.addAluno(nicholasAluno);

			//add turma to aluno
			rafael.addTurma(turma1);
			nicholasAluno.addTurma(turma1);
			nicholasAluno.addTurma(turma2);

			turmaRepository.save(turma1);
			turmaRepository.save(turma2); */



		}
		

		
	}
	
}