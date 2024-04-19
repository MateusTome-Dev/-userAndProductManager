package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Usuario;

public class GerenciadorDeUsuarios {
	private static final String NOME_ARQUIVO = "usuarios.txt";
	Scanner sc=new Scanner(System.in);
	// Verfica a existencia do bd e criar caso não exista
	public void verificaECria(String nomeArquivo) {
		File arquivo = new File(nomeArquivo);
		if (arquivo.exists()) {
			System.out.println("Banco funcionandoo!");
		} else {
			try {
				arquivo.createNewFile();
				System.out.println("Arquivo criado com sucesso!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Erro ao criar o arquivo" + e.getMessage());
			}
		}
	}

	public void addUsuario(Usuario usuario) {
		// Buffered ou FileWriter escreve em arquivos
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {
			bw.write(usuario.toString());
			bw.newLine();
			System.out.println("Usuário adicionado com sucesso!");
		} catch (IOException e) {
			System.out.println("Erro ao escrever no arquivo" + e.getMessage());
		}
	}

	public List<Usuario> lerUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
			String linha;

			while ((linha = br.readLine()) != null) {
				String[] partes = linha.split(";"); // divide em trÊs espaços

				usuarios.add(new Usuario(Integer.parseInt(partes[0]), partes[1], partes[2]));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro ao ler arquivo" + e.getMessage());
		}
		return usuarios;
	}

	public void deletarUsuario(int id) {
		List<Usuario> usuarios = lerUsuarios();
		if (usuarios.removeIf(usuario -> usuario.getId() == id)) {
			reescreverArquivo(usuarios);
			System.out.println("usuario deletado com sucesso");
		} else {
			System.out.println("usuario não detectado");
		}
	}

	public void reescreverArquivo(List<Usuario> usuarios) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
			for (Usuario usuario : usuarios) {
				bw.write(usuario.toString());
				bw.newLine();
			}
		} catch (Exception e) {
			System.out.println("ocorreu um erro ao reescrever o arquivo: " + e.getMessage());
		}
	}

	public void listarUsuarios() {
		List<Usuario> usuario = lerUsuarios();
		if (usuario.isEmpty()) {
			System.out.println("nenhum usuario cadastrado");
		} else {
			System.out.println("Lista de usuarios");
			for (Usuario usuarios : usuario) {
				System.out.println("ID" + usuarios.getId() + ", Nome: " + "" + usuarios.getNome() + ", Senha: "
						+ usuarios.getSenha());
			}
		}
	}

	public void editarUsuarios(int id, String novoNome, String novaSenha) {
		List<Usuario> usuarios = lerUsuarios();
		boolean encontrado = false;

		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				usuario.setNome(novoNome);
				usuario.setSenha(novaSenha);
				encontrado = true;
				break;
			}
		}
		if (encontrado) {
			reescreverArquivo(usuarios);
			System.out.println("Usuario editado com sucesso!");
		} else {
			System.out.println("usuario não encontrado");
		}

	}

	public void listarEspecifico(int id) {

		List<Usuario> usuarios = lerUsuarios();
		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				System.out.println("ID" + usuario.getId() + ", Nome: " + "" + usuario.getNome() + ", Senha: "
						+ usuario.getSenha());
			}
		}
	}

	public void login(String nome, String senha) {
		List<Usuario> usuarios = lerUsuarios();
		boolean encontrado = false;
		for (Usuario usuario : usuarios) {
			if (nome.equals(usuario.getNome()) && senha.equals(usuario.getSenha())) {
				encontrado = true;
			}
		}
		if (encontrado = true) {
			System.out.println("voce logou");
		} else {
			System.out.println("senha ou nome errado");
		}
	}
	public void trocaSenha(int id,String nome, String senha) {
		List<Usuario> usuarios = lerUsuarios();
		boolean encontrado = false;
		for (Usuario usuario : usuarios) {
			if(usuario.getId()==id) {
				if (senha.equals(usuario.getSenha())) {
					System.out.println("digite a nova senha");
					String novaSenha=sc.next();
					usuario.setSenha(novaSenha);
					encontrado = true;
					if (encontrado = true) {
						System.out.println("senha trocada com sucesso");
						reescreverArquivo(usuarios);
					} else {
						System.out.println("senha ou nome errado");
					}
				}
				
			}
		}
		
	
	}
}
