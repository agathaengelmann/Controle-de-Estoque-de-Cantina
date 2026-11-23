import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Lista para armazenar os produtos na memória
    private static ArrayList<Produto> estoque = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = 0;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    buscarProdutoPorCodigo();
                    break;
                case 4:
                    registrarVenda();
                    break;
                case 5:
                    exibirValorTotalEstoque();
                    break;
                case 6:
                    System.out.println("\nEncerrando o sistema da cantina. Até logo!");
                    break;
                default:
                    System.out.println("\n[ERRO] Opção inválida! Escolha um número entre 1 e 6.");
            }
        } while (opcao != 6);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=========================================");
        System.out.println("    CONTROLE DE ESTOQUE DA CANTINA       ");
        System.out.println("=========================================");
        System.out.println("1. Cadastrar um produto");
        System.out.println("2. Listar todos os produtos");
        System.out.println("3. Buscar um produto pelo código");
        System.out.println("4. Registrar uma venda");
        System.out.println("5. Exibir o valor total do estoque");
        System.out.println("6. Sair do programa");
        System.out.println("=========================================");
    }

    // 1. Cadastrar Produto
    private static void cadastrarProduto() {
        System.out.println("\n--- CADASTRAR NOVO PRODUTO ---");
        int codigo = lerInteiro("Digite o código único do produto: ");

        // Validação: Não permitir código repetido
        if (encontrarProduto(codigo) != null) {
            System.out.println("[ERRO] Já existe um produto cadastrado com o código " + codigo + ".");
            return;
        }

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine().trim();

        // Validação: Não permitir nome vazio
        if (nome.isEmpty()) {
            System.out.println("[ERRO] O nome do produto não pode ficar vazio.");
            return;
        }

        double preco = lerDouble("Digite o preço unitário (ex: 5,00 ou 5.00): ");
        // Validação: Preço maior que zero
        if (preco <= 0) {
            System.out.println("[ERRO] O preço deve ser maior que zero.");
            return;
        }

        int quantidade = lerInteiro("Digite a quantidade inicial em estoque: ");
        // Validação: Quantidade não pode ser negativa
        if (quantidade < 0) {
            System.out.println("[ERRO] A quantidade em estoque não pode ser negativa.");
            return;
        }

        Produto novoProduto = new Produto(codigo, nome, preco, quantidade);
        estoque.add(novoProduto);
        System.out.println("[SUCESSO] Produto '" + nome + "' cadastrado com sucesso!");
    }

    // 2. Listar Produtos
    private static void listarProdutos() {
        System.out.println("\n--- LISTA DE PRODUTOS EM ESTOQUE ---");
        if (estoque.isEmpty()) {
            System.out.println("Nenhum produto cadastrado no momento.");
            return;
        }

        for (Produto p : estoque) {
            System.out.println(p.toString());
        }
    }

    // 3. Buscar Produto
    private static void buscarProdutoPorCodigo() {
        System.out.println("\n--- BUSCAR PRODUTO ---");
        if (estoque.isEmpty()) {
            System.out.println("O estoque está vazio.");
            return;
        }

        int codigo = lerInteiro("Digite o código do produto: ");
        Produto p = encontrarProduto(codigo);

        if (p != null) {
            System.out.println("Produto encontrado: " + p.toString());
        } else {
            System.out.println("[AVISO] Nenhum produto encontrado com o código " + codigo + ".");
        }
    }

    // 4. Registrar Venda
    private static void registrarVenda() {
        System.out.println("\n--- REGISTRAR VENDA ---");
        if (estoque.isEmpty()) {
            System.out.println("Não há produtos cadastrados para vender.");
            return;
        }

        int codigo = lerInteiro("Digite o código do produto vendido: ");
        Produto p = encontrarProduto(codigo);

        if (p == null) {
            System.out.println("[ERRO] Produto com código " + codigo + " não encontrado.");
            return;
        }

        System.out.println("Produto selecionado: " + p.getNome() + " | Disponível: " + p.getQuantidadeEstoque() + " un.");
        int qtdVendida = lerInteiro("Digite a quantidade vendida: ");

        // Validação: Quantidade vendida maior que zero
        if (qtdVendida <= 0) {
            System.out.println("[ERRO] A quantidade vendida deve ser maior que zero. Venda cancelada.");
            return;
        }

        // Validação: Não vender mais do que tem no estoque
        if (qtdVendida > p.getQuantidadeEstoque()) {
            System.out.println("[ERRO] Estoque insuficiente! Disponível apenas: " + p.getQuantidadeEstoque() + " un. Venda recusada.");
            return;
        }

        // Atualiza o estoque
        int novoEstoque = p.getQuantidadeEstoque() - qtdVendida;
        p.setQuantidadeEstoque(novoEstoque);
        double valorVenda = qtdVendida * p.getPrecoUnitario();

        System.out.printf("[SUCESSO] Venda realizada! Valor total: R$ %.2f | Estoque restante: %d un.\n",
                valorVenda, novoEstoque);
    }

    // 5. Exibir Valor Total do Estoque
    private static void exibirValorTotalEstoque() {
        System.out.println("\n--- VALOR TOTAL DO ESTOQUE ---");
        if (estoque.isEmpty()) {
            System.out.println("O estoque está vazio. Valor total: R$ 0,00");
            return;
        }

        double valorTotal = 0.0;
        for (Produto p : estoque) {
            valorTotal += p.calcularValorTotalItem();
        }

        System.out.printf("O valor financeiro total de todos os produtos em estoque é: R$ %.2f\n", valorTotal);
    }

    // Método auxiliar de busca (reutilizado no cadastro, busca e venda)
    private static Produto encontrarProduto(int codigo) {
        for (Produto p : estoque) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    // Métodos auxiliares para evitar que o programa quebre se o usuário digitar texto em vez de número
    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Entrada inválida! Digite apenas números inteiros.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String entrada = scanner.nextLine().trim().replace(",", ".");
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Valor inválido! Digite um número válido (ex: 5.50).");
            }
        }
    }
}