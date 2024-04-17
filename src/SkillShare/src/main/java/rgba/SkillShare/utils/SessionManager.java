package rgba.SkillShare.utils;

import jakarta.servlet.http.HttpSession;

import org.json.JSONObject;

/**
 * Classe responsável por realizar alguns controles relacionados às sessões dos usuários no sistema
 *  
 * @author Rafael Furtado
 */
public class SessionManager {
	
	/**
	 * Verifica se para a instância da sessão fornecida o tipo do usuário dela é do mesmo tipo esperado passado como
	 * parâmetro para a mesma
	 * 
	 * @author Rafael Furtado
	 * @param sessao - Sessão do usuário
	 * @param expectedType - Tipo do usuário que se espera que a sessão seja, possiveis: guest, admin, gestor e aluno
	 * @return Boolean - true ou false de acordo com que se o usuário é do tipo esperado ou não 
	 */
	public static boolean checkPermission(HttpSession sessao, String expectedType) {
		try {
			JSONObject user = (JSONObject) sessao.getAttribute("user");
			String userType = user.getString("type");
			
			if(userType.equals(expectedType)) {
				return true;
			}else {
				return false;
			}
			
		}catch (Exception e) {
			return false;
		}
		
	}
	
	/**
	 * Verifica se para a sessão fornecida, existe um usuário logado no sistema ou não
	 * 
	 * @author Rafael Furtado
	 * @param sessao - Sessão do usuário
	 * @return Boolean - Caso haja um usuário logado na sessão, retorna true, se não, false
	 */
	public static boolean isLogged(HttpSession sessao) {
		try {
			JSONObject user = (JSONObject) sessao.getAttribute("user");
			String userType = user.getString("type");
			
			if(!userType.equals("guest")) {
				return true;
			}else {
				return false;
			}
			
		}catch (Exception e) {
			return false;
		}
		
	}
	
	public static String getUserName(HttpSession sessao) {
		String userName = "";
		
		try {
			JSONObject user = (JSONObject) sessao.getAttribute("user");
			userName = user.getString("userName");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return userName;
	}
	
	public static String getUserAccessLevel(HttpSession sessao) {
		String userAccessLevel = "";
		
		try {
			JSONObject user = (JSONObject) sessao.getAttribute("user");
			userAccessLevel = user.getString("type");
			
			switch (userAccessLevel) {
			case "admin":
				userAccessLevel = "Administrador";
				break;
				
			case "aluno":
				userAccessLevel = "Aluno";
				break;
				
			case "gestor":
				userAccessLevel = "Gestor";
				break;
				
			case "tutor":
				userAccessLevel = "Tutor";
				break;
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return userAccessLevel;
	}
	
	public static String getUserCpf(HttpSession sessao) {
		String userCpf = "";
		
		try {
			JSONObject user = (JSONObject) sessao.getAttribute("user");
			userCpf = user.getString("cpf");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return userCpf;
	}
	
}
