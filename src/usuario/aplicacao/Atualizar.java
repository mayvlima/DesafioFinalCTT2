package usuario.aplicacao;

import java.util.List;
import java.util.Scanner;

import usuario.metodos.UsuarioMetodos;
import usuario.model.Usuario;

public class Atualizar {

	public static Scanner sc = new Scanner(System.in);
	public static Scanner scInt = new Scanner(System.in);
	public static Usuario usuario = new Usuario();
	public static UsuarioMetodos usuarioMetodos = new UsuarioMetodos();	
	
	public void atualizarCadastro() {
		
		if(usuarioMetodos.getUsuarios().isEmpty()) {
			
			System.out.println("\nA LISTA DE CADASTROS EST� VAZIA!");
			System.out.println("ADICIONE PELO MENOS UM USU�RIO!");
			
		}else {
			
			int a = pergunta("\nDeseja ver a lista de cadastros antes?", "1 - SIM", "2 - N�O");
			
			if(a == 1) {
				List<Usuario> usuarios = usuarioMetodos.getUsuarios();
				
				for (Usuario u : usuarios) {
					System.out.println("--------------------------------");
					System.out.println("ID: " + u.getId() + " - " + u.getNome());
				}
				
				atualizar();
				
			} else if(a == 2) {
				
				atualizar();
			}		
			
		}
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
	
	public static void metodosAtualizar() {
		int r = pergunta("\nDeseja atualizar o NOME?", "1 - SIM", "2 - N�O");
		if (r == 1) {
			receberDado("Nome");
			usuarioMetodos.update(usuario, "Nome");
		}

		r = pergunta("\nDeseja atualizar o EMAIL?", "1 - SIM", "2 - N�O");
		if (r == 1) {
			receberDado("Email");
			usuarioMetodos.update(usuario, "Email");
		}

		r = pergunta("\nDeseja atualizar o SENHA?", "1 - SIM", "2 - N�O");
		if (r == 1) {
			receberDado("Senha");
			usuarioMetodos.update(usuario, "Senha");
		}
	}
	
	public static void receberDado(String string) {
		String dado;
		do {
			System.out.println("\nDigite o " + string.toLowerCase() + ":");
			dado = sc.nextLine();
			if (dado.trim().equals("")) {
				System.out.println("DIGITE UM " + string.toUpperCase() + " V�LIDO!\n");
			} else {
				if (string.equals("Nome")) {
					usuario.setNome(dado);
				} else if (string.equals("Email")) {
					usuario.setEmail(dado);
				} else if (string.equals("Senha")) {
					usuario.setSenha(dado);
				}
			}

		} while (dado.trim().equals(""));
	}
	
	public static void atualizar() {
		boolean continuar = true;
		int b = 1;
		do {			
			usuario = new Usuario();
			System.out.println("\nDigite o ID do registro desejado:");
			usuario.setId(scInt.nextInt());

			usuario = usuarioMetodos.getUsuarioWithId(usuario);

			if (usuario != null) {
				System.out.println("\n--------------------------------");
				System.out.println(usuario);
				System.out.println("\n--------------------------------");
				
				b = pergunta("Esse � o cadastro desejado?", "1 - SIM", "2 - N�O");
				
				if(b == 1) {
					metodosAtualizar();							
					System.out.println("\nUSU�RIO ATUALIZADO COM SUCESSO!");
					System.out.println(usuario);
					System.out.println("\n--------------------------------");
					
					int c = pergunta("Deseja atualizar um novo usu�rio?", "1 - SIM", "2 - N�O");
					if(c == 2) {
					continuar = false;
					}
				
				} else {
					
					int c = pergunta("\nDigite o n�mero:", "1 - TENTAR NOVAMENTE", "2 - SAIR");
					if (c == 2) {
						continuar = false;
						System.out.println("\nATUALIZA��O DE CADASTRO CANCELADA!");
					}							
				}
								
			} else {						
				System.out.println("\nN�O H� CADASTRO COM ESSE N�MERO DE ID!");
				int d = pergunta("\nDigite:", "1 - TENTAR NOVAMENTE", "2 - SAIR");
				if (d == 2) {
					continuar = false;	
				}
			}					
			
		} while (continuar == true);
	}
}
