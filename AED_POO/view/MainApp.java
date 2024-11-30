package view;

import controller.ProdutoController;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainApp extends JFrame {
    private JTextField txtNome, txtCategoria, txtQuantidade, txtPrecoCompra, txtPrecoVenda;
    private JTable table;
    private DefaultTableModel tableModel;
    private ProdutoController produtoController;

    public MainApp() {
        produtoController = new ProdutoController();

        setTitle("Gerenciamento de Estoque");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        formPanel.add(txtNome);

        formPanel.add(new JLabel("Categoria:"));
        txtCategoria = new JTextField();
        formPanel.add(txtCategoria);

        formPanel.add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField();
        formPanel.add(txtQuantidade);

        formPanel.add(new JLabel("Preço de Compra:"));
        txtPrecoCompra = new JTextField();
        formPanel.add(txtPrecoCompra);

        formPanel.add(new JLabel("Preço de Venda:"));
        txtPrecoVenda = new JTextField();
        formPanel.add(txtPrecoVenda);

        JButton btnCadastrar = new JButton("Cadastrar Produto");
        formPanel.add(btnCadastrar);

        add(formPanel, BorderLayout.NORTH);

        // Painel da tabela
        tableModel = new DefaultTableModel(new String[]{"ID", "Nome", "Categoria", "Quantidade", "Preço Compra", "Preço Venda"}, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Botões de ação
        JPanel btnPanel = new JPanel();
        JButton btnListar = new JButton("Listar Produtos");
        JButton btnEditar = new JButton("Editar Produto");
        JButton btnRemover = new JButton("Remover Produto");

        btnPanel.add(btnListar);
        btnPanel.add(btnEditar);
        btnPanel.add(btnRemover);

        add(btnPanel, BorderLayout.SOUTH);

        // Ações dos botões
        btnCadastrar.addActionListener(e -> cadastrarProduto());
        btnListar.addActionListener(e -> listarProdutos());
        btnRemover.addActionListener(e -> removerProduto());
        btnEditar.addActionListener(e -> editarProduto());
    }

    private void cadastrarProduto() {
        try {
            String nome = txtNome.getText();
            String categoria = txtCategoria.getText();
            int quantidade = Integer.parseInt(txtQuantidade.getText());
            double precoCompra = Double.parseDouble(txtPrecoCompra.getText());
            double precoVenda = Double.parseDouble(txtPrecoVenda.getText());

            produtoController.adicionarProduto(nome, categoria, quantidade, precoCompra, precoVenda);
            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");

            limparCampos();
            listarProdutos(); // Atualiza a tabela após cadastro
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar produto: " + ex.getMessage());
        }
    }

    private void listarProdutos() {
        tableModel.setRowCount(0);
        List<Produto> produtos = produtoController.listarProdutos();

        for (Produto p : produtos) {
            tableModel.addRow(new Object[]{p.getId(), p.getNome(), p.getCategoria(), p.getQuantidade(), p.getPrecoCompra(), p.getPrecoVenda()});
        }
    }

    private void removerProduto() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            produtoController.removerProduto(id);
            listarProdutos();
            JOptionPane.showMessageDialog(this, "Produto removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para remover.");
        }
    }

    private void editarProduto() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                String nomeAtual = (String) tableModel.getValueAt(selectedRow, 1);
                String categoriaAtual = (String) tableModel.getValueAt(selectedRow, 2);
                int quantidadeAtual = (int) tableModel.getValueAt(selectedRow, 3);
                double precoCompraAtual = (double) tableModel.getValueAt(selectedRow, 4);
                double precoVendaAtual = (double) tableModel.getValueAt(selectedRow, 5);
    
                // Se o campo estiver vazio, mantemos o valor atual
                String nome = txtNome.getText().isEmpty() ? nomeAtual : txtNome.getText();
                String categoria = txtCategoria.getText().isEmpty() ? categoriaAtual : txtCategoria.getText();
                int quantidade = txtQuantidade.getText().isEmpty() ? quantidadeAtual : Integer.parseInt(txtQuantidade.getText());
                double precoCompra = txtPrecoCompra.getText().isEmpty() ? precoCompraAtual : Double.parseDouble(txtPrecoCompra.getText());
                double precoVenda = txtPrecoVenda.getText().isEmpty() ? precoVendaAtual : Double.parseDouble(txtPrecoVenda.getText());
    
                Produto produtoAtualizado = new Produto(id, nome, categoria, quantidade, precoCompra, precoVenda);
                produtoController.atualizarProduto(produtoAtualizado);
    
                listarProdutos(); // Atualiza a tabela
                JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso!");
                limparCampos(); // Limpa os campos
    
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos para quantidade e preços.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para editar.");
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCategoria.setText("");
        txtQuantidade.setText("");
        txtPrecoCompra.setText("");
        txtPrecoVenda.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApp app = new MainApp();
            app.setVisible(true);
    });
    }
}