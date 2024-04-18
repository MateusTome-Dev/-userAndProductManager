package sistema;

import java.util.Scanner;

import service.MenuProduto;

public class SistemaProduto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner (System.in);
		MenuProduto Mp =new MenuProduto();
		int opcao=0;
		do {
			System.out.print("1-Criar \n2 - Editar \n3 - Deletar \n4 - Listar \n5 - Listar usuario especifico \n6 - Sair\n");
			opcao=sc.nextInt();
			switch(opcao){
			case 1:{
				Mp.criar();
				break;
			}
			case 2:{
				Mp.editar();
				break;
			}
			case 3:{
				Mp.deletar();
				break;
			}
			case 4:{
				Mp.listar();
				break;
			}
			case 5:{
				Mp.listarEspecifico();
				break;
			}
			default:
				System.out.println("Opção Invalida");
				break;
			}
		}while(opcao!=9);
		sc.close();
	}

}
