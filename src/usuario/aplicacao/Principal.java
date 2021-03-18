package usuario.aplicacao;

import java.sql.SQLException;
import java.util.Scanner;

import usuario.conexao.Conexao;

public class Principal {
	public static Scanner sc = new Scanner(System.in);
	public static Scanner scInt = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {	
		
		System.out.println("\nBEM VINDO!");
		System.out.println("Digite o USERNAME do seu banco de dados (ex: root): ");
		Conexao.setUsername(sc.nextLine());
		System.out.println("Digite o PASSWORD do seu banco de dados: ");
		Conexao.setPassword(sc.nextLine());
		System.out.println("Digite o URL do seu banco de dados (ex: jdbc:mysql://localhost): ");
		Conexao.setUrl(sc.nextLine());
		
		if(Conexao.createConnectionToMySql() == null) {
			System.exit(0);
		}

		int continuar = 1;
		
		do {
			int r = menu();

			switch (r) {
			case 1:		
				Cadastrar c = new Cadastrar();
				c.novoCadastro();				
				continuar = pergunta("\nDeseja retornar ao MENU?", "1 - SIM", "2 - N�O");
				break;

			case 2:
				Procurar p = new Procurar();
				p.procurarCadastro();
				continuar = pergunta("\nDeseja retornar ao MENU?", "1 - SIM", "2 - N�O");
				break;
				
			case 3:
				Atualizar a = new Atualizar();
				a.atualizarCadastro();
				continuar = pergunta("\nDeseja retornar ao MENU?", "1 - SIM", "2 - N�O");
				break;
			
			case 4:
				Deletar d = new Deletar();
				d.deletarCadastro();		
				continuar = pergunta("\nDeseja retornar ao MENU?", "1 - SIM", "2 - N�O");
				break;
			}
				
			} while (continuar == 1);

		sc.close();
		scInt.close();

		System.out.println("\nPROGRAMA FINALIZADO!");

	}

	public static int menu() {
		int i;
		do {

			System.out.println("\nO que voc� deseja fazer?");
			System.out.println("1 - Cadastrar novo usu�rio");
			System.out.println("2 - Procurar um cadastro");
			System.out.println("3 - Atualizar um cadastro");
			System.out.println("4 - Deletar um cadastro");
			System.out.println("5 - Sair");
			i = scInt.nextInt();

			if (i != 1 && i != 2 && i != 3 && i != 4 && i != 5) {
				System.out.println("\nESCOLHA UMA OP��O V�LIDA!");
			}
		} while (i != 1 && i != 2 && i != 3 && i != 4 && i != 5);

		return i;
	}

	public static int pergunta(String frase1, String frase2, String frase3) {
		int i;
		do {
			System.out.println(frase1);
			System.out.println(frase2);
			System.out.println(frase3);
			i = scInt.nextInt();
			if (i != 1 && i != 2) {
				System.out.println("\nESCOLHA UMA OP��O V�LIDA!\n");
			}
		} while (i != 1 && i != 2);

		return i;
	}
}