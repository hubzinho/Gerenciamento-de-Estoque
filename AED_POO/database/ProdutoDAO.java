package database;

import model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void inserir(Produto produto) {
        String sql = "INSERT INTO produtos(nome, categoria, quantidade, preco_compra, preco_venda) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = SQLiteConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setDouble(4, produto.getPrecoCompra());
            stmt.setDouble(5, produto.getPrecoVenda());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection conn = SQLiteConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                produtos.add(new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("categoria"),
                    rs.getInt("quantidade"),
                    rs.getDouble("preco_compra"),
                    rs.getDouble("preco_venda")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    // Atualizar produto
public void atualizar(Produto produto) {
    String sql = "UPDATE produtos SET nome = ?, categoria = ?, quantidade = ?, preco_compra = ?, preco_venda = ? WHERE id = ?";
    try (Connection conn = SQLiteConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, produto.getNome());
        stmt.setString(2, produto.getCategoria());
        stmt.setInt(3, produto.getQuantidade());
        stmt.setDouble(4, produto.getPrecoCompra());
        stmt.setDouble(5, produto.getPrecoVenda());
        stmt.setInt(6, produto.getId());
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Deletar produto
public void deletar(int id) {
    String sql = "DELETE FROM produtos WHERE id = ?";
    try (Connection conn = SQLiteConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Buscar produto por nome ou categoria
public List<Produto> buscar(String termo) {
    List<Produto> produtos = new ArrayList<>();
    String sql = "SELECT * FROM produtos WHERE nome LIKE ? OR categoria LIKE ?";
    try (Connection conn = SQLiteConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, "%" + termo + "%");
        stmt.setString(2, "%" + termo + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            produtos.add(new Produto(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("categoria"),
                rs.getInt("quantidade"),
                rs.getDouble("preco_compra"),
                rs.getDouble("preco_venda")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return produtos;
}
}