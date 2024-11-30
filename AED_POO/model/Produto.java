package model;

public class Produto {
    private int id;
    private String nome;
    private String categoria;
    private int quantidade;
    private double precoCompra;
    private double precoVenda;

    // Construtor completo
    public Produto(int id, String nome, String categoria, int quantidade, double precoCompra, double precoVenda) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
    }

    // Construtor sem ID (para inserções)
    public Produto(String nome, String categoria, int quantidade, double precoCompra, double precoVenda) {
        this(0, nome, categoria, quantidade, precoCompra, precoVenda);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }
}