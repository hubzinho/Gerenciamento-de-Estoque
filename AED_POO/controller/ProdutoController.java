package controller;

import database.ProdutoDAO;
import model.Produto;
import java.util.List;

public class ProdutoController {
    private ProdutoDAO produtoDAO;

    public ProdutoController() {
        produtoDAO = new ProdutoDAO();
    }

    public void adicionarProduto(String nome, String categoria, int quantidade, double precoCompra, double precoVenda) {
        Produto produto = new Produto(nome, categoria, quantidade, precoCompra, precoVenda);
        produtoDAO.inserir(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listar();
    }

    public void removerProduto(int id) {
        produtoDAO.deletar(id);
    }

    public void atualizarProduto(Produto produto) {
        produtoDAO.atualizar(produto);
    }
}