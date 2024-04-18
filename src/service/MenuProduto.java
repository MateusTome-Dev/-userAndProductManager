package service;

import java.util.List;
import java.util.Scanner;

import models.Produto;
import utils.GerenciadorDeProdutos;

public class MenuProduto {
	Scanner sc=new Scanner(System.in);
	GerenciadorDeProdutos gp=new GerenciadorDeProdutos();
	public MenuProduto() {
		gp.verificaECria("usuarios.txt");
	}
	public void criar() {
		System.out.println("Digite o nome: ");
		String nome=sc.next();
		System.out.println("Digite o preço: ");
		double preco=sc.nextDouble();
		System.out.println("digite a quantidade de produtos: ");
		int quantidade=sc.nextInt();
		long id=getNextId();
		Produto p = new Produto(id, nome, preco, quantidade);
		gp.addProduto(p);
	}
	public void editar() {
		System.out.println("Digite o Id do produto: ");
		int id =sc.nextInt();
		System.out.println("Digite o novo nome do produto: ");
		String nome=sc.next();
		System.out.println("Digite o novo preço: ");
		double preco=sc.nextDouble();
		System.out.println("Digite a Quantidade");
		int quant=sc.nextInt();
		gp.editarProdutos(id,nome,preco,quant);
	}
	public void deletar() {
		System.out.println("Digite o id do produto a ser deletado: ");
		int id=sc.nextInt();
		gp.deletarProduto(id);
	}
	public void listar() {
		gp.listarProdutos();
	}
	public void listarEspecifico() {
		System.out.println("Digite o id do usuario a ser listado: ");
		int id=sc.nextInt();
		gp.listarEspecifico(id);
	}
	public long getNextId() {
		List<Produto>produtos=gp.lerProdutos();
		long maxId =0;
		for(Produto produto:produtos) {
			long id=produto.getId();
			if(id>maxId) {
				maxId=id;
			}
		}
		return maxId+1;
	}
}
