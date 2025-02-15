package com.ads.arq.atv1arq;

import com.ads.arq.atv1arq.model.ProdutoModel;
import com.ads.arq.atv1arq.service.ProdutoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan("com.ads.arq.atv1arq")
public class Atv1arqApplication {

	public static void main(String[] args) {
		SpringApplication.run(Atv1arqApplication.class, args);
		ApplicationContext context = SpringApplication.run(Atv1arqApplication.class, args);
		ProdutoService produtoService = context.getBean(ProdutoService.class);
		Scanner scanner = new Scanner(System.in);
		int opcao = 0;

		do {
			System.out.println("----- Gerenciamento de Produtos -----");
			System.out.println("1 - Cadastrar Produto");
			System.out.println("2 - Listar Produtos");
			System.out.println("3 - Listar Produto pelo ID");
			System.out.println("4 - Atualizar Produto pelo ID");
			System.out.println("5 - Deletar Produto pelo ID");
			System.out.println("0 - Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine(); // Consumir a quebra de linha

			switch (opcao) {
				case 1:
					System.out.print("Digite o ID do produto: ");
					Long id = scanner.nextLong();
					scanner.nextLine(); // Consumir a quebra de linha
					System.out.print("Digite o nome do produto: ");
					String nome = scanner.nextLine();
					System.out.print("Digite o preço do produto: ");
					double preco = scanner.nextDouble();

					ProdutoModel produto = new ProdutoModel(id, nome, preco);
					produtoService.salvar(produto);
					System.out.println("✅ Produto cadastrado com sucesso!");
					break;

				case 2:
					List<ProdutoModel> produtos = produtoService.buscarTodos();
					System.out.println("----- Lista de Produtos -----");
					if (produtos.isEmpty()) {
						System.out.println("⚠ Nenhum produto cadastrado.");
					} else {
						for (ProdutoModel p : produtos) {
							System.out.println(p);
						}
					}
					break;

				case 3:
					System.out.print("Digite o ID do produto: ");
					Long idProduto = scanner.nextLong();
					scanner.nextLine();

					Optional<ProdutoModel> produtoPesquisado = produtoService.buscarPorId(idProduto);
					if (produtoPesquisado.isPresent()) {
						System.out.println("----- Produto Encontrado -----");
						System.out.println(produtoPesquisado.get());
					} else {
						System.out.println("⚠ Produto com ID " + idProduto + " não encontrado.");
					}
					break;

				case 4:
					System.out.print("Digite o ID do produto a ser atualizado: ");
					Long idAtualizar = scanner.nextLong();
					scanner.nextLine();

					Optional<ProdutoModel> produtoExistente = produtoService.buscarPorId(idAtualizar);
					if (produtoExistente.isPresent()) {
						System.out.print("Digite o novo nome do produto: ");
						String novoNome = scanner.nextLine();
						System.out.print("Digite o novo preço do produto: ");
						double novoPreco = scanner.nextDouble();

						ProdutoModel produtoAtualizado = produtoExistente.get();
						produtoAtualizado.setNome(novoNome);
						produtoAtualizado.setPreco(novoPreco);

						produtoService.atualizar(produtoAtualizado);
						System.out.println("✅ Produto atualizado com sucesso!");
					} else {
						System.out.println("⚠ Produto com ID " + idAtualizar + " não encontrado.");
					}
					break;

				case 5:
					System.out.print("Digite o ID do produto a ser deletado: ");
					Long idDeletar = scanner.nextLong();
					scanner.nextLine();

					if (produtoService.buscarPorId(idDeletar).isPresent()) {
						produtoService.deletar(idDeletar);
						System.out.println("✅ Produto deletado com sucesso!");
					} else {
						System.out.println("⚠ Produto com ID " + idDeletar + " não encontrado.");
					}
					break;

				case 0:
					System.out.println("🔴 Encerrando o sistema.");
					break;

				default:
					System.out.println("⚠ Opção inválida. Tente novamente.");
			}
			System.out.println();
		} while (opcao != 0);

		scanner.close();
	}
}


