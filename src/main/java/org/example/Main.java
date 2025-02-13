package org.example;

import java.util.List;
import java.util.Scanner;
import org.example.model.ProdutoModel;
import org.example.service.ProdutoService;

public class Main {
    public static void main(String[] args) {
        ProdutoService produtoService = new ProdutoService();
        Scanner scanner = new Scanner(System.in); int opcao = 0;
        do {
            System.out.println("----- Gerenciamento de Produtos -----");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Listar Produto pelo ID");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID do produto: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha
                    System.out.print("Digite o nome do produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o preço do produto: ");
                    double preco = scanner.nextDouble();
                    ProdutoModel produto = new ProdutoModel(id, nome, preco);
                    produtoService.salvar(produto);
                    System.out.println("Produto cadastrado com sucesso.");
                    break;
                case 2:
                    List<ProdutoModel> produtos = produtoService.buscarTodos();
                    System.out.println("----- Lista de Produtos -----");
                    for (ProdutoModel p : produtos) {
                        System.out.println(p);
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID do produto: ");
                    Long idProduto = scanner.nextLong();
                    scanner.nextLine();
                    ProdutoModel produtoPesquisado = produtoService.buscarPorId(idProduto);
                    System.out.println("----- Produto Pesquisado -----");
                    System.out.println(produtoPesquisado);
                    break;

                case 0:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        } while (opcao != 0);
        scanner.close();
    }
}