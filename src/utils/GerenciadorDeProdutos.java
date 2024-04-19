package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Produto;

public class GerenciadorDeProdutos {
	private static final String NOME_ARQUIVO = "produtos.txt";
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
	public void addProduto(Produto produto) {
		// Buffered ou FileWriter escreve em arquivos
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {
			bw.write(produto.toString());
			bw.newLine();
			System.out.println("Produto adicionado com sucesso!");
		} catch (IOException e) {
			System.out.println("Erro ao escrever no arquivo" + e.getMessage());
		}
	}
	public List<Produto> lerProdutos() {
		List<Produto> produtos = new ArrayList<Produto>();
		try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
			String linha;

			while ((linha = br.readLine()) != null) {
				String[] partes = linha.split(";"); // divide em trÊs espaços

				produtos.add(new Produto(Long.parseLong(partes[0]),partes[1], Double.parseDouble(partes[2]) , Integer.parseInt(partes[3])));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro ao ler arquivo" + e.getMessage());
		}
		return produtos;
	}
	public void deletarProduto(long id) {
		List<Produto> produtos = lerProdutos();
		if (produtos.removeIf(produto -> produto.getId() == id)) {
			reescreverArquivo(produtos);
			System.out.println("produto deletado com sucesso");
		} else {
			System.out.println("produto não detectado");
		}
	}
	public void reescreverArquivo(List<Produto> produtos) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
			for (Produto produto : produtos) {
				bw.write(produto.toString());
				bw.newLine();
			}
		} catch (Exception e) {
			System.out.println("ocorreu um erro ao reescrever o arquivo: " + e.getMessage());
		}
	}
	public void listarProdutos() {
		List<Produto> produto = lerProdutos();
		if (produto.isEmpty()) {
			System.out.println("nenhum produto cadastrado");
		} else {
			System.out.println("Lista de produtos");
			for (Produto produtos : produto) {
				System.out.println("ID" + produtos.getId() + ", Produto: " + "" + produtos.getNome() + ", preço: "
						+ produtos.getPreco()+""+", Quantidade"+produtos.getQuantidade());
			}
		}
	}
	public void editarProdutos(long id, String novoNome, double novoPreco,  int novaQuant) {
		List<Produto> produtos = lerProdutos();
		boolean encontrado = false;

		for (Produto produto : produtos) {
			if (produto.getId() == id) {
				produto.setNome(novoNome);
				produto.setPreco(novoPreco);
				produto.setQuantidade(novaQuant);
				encontrado = true;
				break;
			}
		}
		if (encontrado) {
			reescreverArquivo(produtos);
			System.out.println("Produto editado com sucesso!");
		} else {
			System.out.println("produto não encontrado");
		}

	}
	public void listarEspecifico(long id) {

		List<Produto> produtos = lerProdutos();
		for (Produto produto : produtos) {
			if (produto.getId() == id) {
				System.out.println("ID" + produto.getId() + ", Produto: " + "" + produto.getNome() + ", Senha: "
						+ produto.getPreco()+""+", Quantidade: "+produto.getQuantidade());
			}
		}
	}
	public void soma() {
		List<Produto> produtos = lerProdutos();
		 double soma = 0;
		for (Produto produto: produtos) {
			soma += produto.getPreco()*produto.getQuantidade();
			
		}
		System.out.println(soma);
	}
	public void somaQuant() {
		List<Produto> produtos = lerProdutos();
		double somaQuant = 0;
		for (Produto produto: produtos) {
			somaQuant += produto.getQuantidade();
		}
		System.out.println(somaQuant);
	}
}
	
