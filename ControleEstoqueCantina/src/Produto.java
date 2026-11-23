public class Produto {
    private int codigo;
    private String nome;
    private double precoUnitario;
    private int quantidadeEstoque;

    public Produto(int codigo, String nome, double precoUnitario, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // Método auxiliar para calcular o valor total deste item específico
    public double calcularValorTotalItem() {
        return this.precoUnitario * this.quantidadeEstoque;
    }

    @Override
    public String toString() {
        return String.format("Código: %d | Produto: %s | Preço: R$ %.2f | Estoque: %d un.",
                codigo, nome, precoUnitario, quantidadeEstoque);
    }
}