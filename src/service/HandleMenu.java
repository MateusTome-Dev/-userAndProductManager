package service;

import java.util.List;
import java.util.Scanner;

import models.Usuario;
import utils.GerenciadorDeUsuarios;

public class HandleMenu {
	Scanner sc=new Scanner(System.in);
	GerenciadorDeUsuarios gs=new GerenciadorDeUsuarios();
	public HandleMenu() {
		gs.verificaECria("usuarios.txt");
	}
	public void criar() {
		System.out.println("Digite o nome: ");
		String nome=sc.next();
		System.out.println("Digite a Senha: ");
		String senha=sc.next();
		
		int id=getNextId();
		Usuario u = new Usuario(id, nome, senha);
		gs.addUsuario(u);
	}
	public void editar() {
		System.out.println("Digite o Id do usuario: ");
		int id =sc.nextInt();
		System.out.println("Digite o novo nome: ");
		String nome=sc.next();
		System.out.println("Digite a nova senha: ");
		String senha=sc.next();
		gs.editarUsuarios(id,nome,senha);
	}
	public void deletar() {
		System.out.println("Digite o id do usuario a ser deletado: ");
		int id=sc.nextInt();
		gs.deletarUsuario(id);
	}
	public void listar() {
		gs.listarUsuarios();
	}
	public void listarEspecifico() {
		System.out.println("Digite o id do usuario a ser listado: ");
		int id=sc.nextInt();
		gs.listarEspecifico(id);
	}
	public void login(){
		System.out.println("digite seu nome: ");
		String nome=sc.next();
		System.out.println("digite sua senha: ");
		String senha=sc.next();
		gs.login(nome, senha);
	}
	public void trocaSenha(){
		System.out.println("digite o id do usuario");
		int id=sc.nextInt();
		System.out.println("digite o nome de usuario");
		String nome=sc.next();
		System.out.println("digite sua senha atual: ");
		String senha=sc.next();
		gs.trocaSenha(id, nome, senha);
	}
	public int getNextId() {
		List<Usuario>usuarios=gs.lerUsuarios();
		int maxId =0;
		for(Usuario usuario:usuarios) {
			int id=usuario.getId();
			if(id>maxId) {
				maxId=id;
			}
		}
		return maxId+1;
	}
}
