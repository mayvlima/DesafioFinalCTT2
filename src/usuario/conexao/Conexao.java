package usuario.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

	private static String username;
	private static String password;
	private static String url;
	
	

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		Conexao.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Conexao.password = password;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		Conexao.url = url;
	}

	public static Connection createConnectionToMySql() {
		Connection conn = null;
		try {
		
		conn = DriverManager.getConnection(url + "?user="+username+"&password="+password);
		
		Statement stmt = conn.createStatement();	
		
		stmt.execute("CREATE DATABASE IF NOT EXISTS desafiofinalctt");
		
		stmt = conn.createStatement();
		
		stmt.execute("USE desafiofinalctt");
		
		stmt = conn.createStatement();
		
		stmt.execute("CREATE TABLE IF NOT EXISTS usuarios(ID INT AUTO_INCREMENT PRIMARY KEY, Nome VARCHAR(60) NOT NULL, Email VARCHAR(130) NOT NULL, Senha VARCHAR(50) NOT NULL, Data_Cadastro DATETIME)");
			
		return conn;
		
		}catch(SQLException e){
			System.out.println("\nConexão com banco de dados falhou!");
			System.out.println("Verifique dados digitados!");
			System.out.println("Erro: " + e);						
			return conn;			
		}
		
		
	}
}
