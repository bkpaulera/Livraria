package livraria.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import livraria.Model.ActionsBanco;
import static livraria.Model.ActionsBanco.criaBuscaLivro;
import livraria.Model.Books;

public class janelaBuscaLivro extends JFrame {
    
  JPanel fundo;
  JPanel buscaPanel;
  
  JTextField txBusca;
  
  JButton btBusca;
  JButton btInserir;
  JButton btAlterar;
  JButton btExcluir;
  JButton btVoltar;
  
  JScrollPane scroolPanel;
  JTable table;
  public static DefaultTableModel modeloBuscaLivro;
  int row;
  int click;
  
  ActionsBanco acao = new ActionsBanco();
  public static Books livro = new Books();

  public janelaBuscaLivro() throws ClassNotFoundException, SQLException{
    super("Sistema Livraria - Buscar Livros");
    criaTabela();
    criaJanela();
  }

  private void criaJanela() throws ClassNotFoundException, SQLException{
    txBusca = new JTextField(40);
    
    btBusca = new JButton("Buscar");
    btInserir = new JButton("Inserir");
    btAlterar = new JButton("Alterar");
    btExcluir = new JButton("Excluir");
    btVoltar = new JButton("Voltar");
    btBusca.addActionListener(new BtBuscaHandler());
    
    buscaPanel = new JPanel();
    buscaPanel.add(txBusca);
    buscaPanel.add(btBusca);
    buscaPanel.add(btInserir);
    buscaPanel.add(btAlterar);
    buscaPanel.add(btExcluir);
    buscaPanel.add(btVoltar);
    
    table = new JTable(modeloBuscaLivro);
    scroolPanel = new JScrollPane(table);
        
    table.addMouseListener(new MouseInputAdapter() {
        public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() > 0) {
        row = table.getSelectedRow();
        click = e.getClickCount();
            }   
        }
    });
    
    btAlterar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (click==0){
                JOptionPane.showMessageDialog(null, "Selecione um livro para alterar!");
            }
            
        else{
                try {
                    janelaAlteraLivro jalteraLivro = new janelaAlteraLivro();
                    jalteraLivro.campoTitulo.setText((String) table.getValueAt(row, 0));
                    jalteraLivro.campoISBN.setText((String) table.getValueAt(row, 1));
                    jalteraLivro.campoEditora.setText((String) table.getValueAt(row, 3));
                    jalteraLivro.campoEditora_id.setText((String) table.getValueAt(row, 4));
                    jalteraLivro.campoAutor.setText((String) table.getValueAt(row, 5));
                    jalteraLivro.campoAutor_id.setText((String) table.getValueAt(row, 6));
                    jalteraLivro.campoPreco.setText((String) table.getValueAt(row, 2).toString());
                    dispose();
                } 
                    catch (SQLException ex) {
                        Logger.getLogger(janelaBuscaLivro.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Ocorreu um Erro de SQL, verifique as logs do sistema!");
                } 
                    catch (ClassNotFoundException ex) {
                        Logger.getLogger(janelaBuscaLivro.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Ocorreu um Erro interno, classe não encontrada!");
                }
            }
        }
    });
    
    btInserir.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                janelaInsereLivro jinsereLivro = new janelaInsereLivro();
                dispose();
            } 
            
            catch (SQLException ex) {
                Logger.getLogger(janelaBuscaLivro.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Ocorreu um Erro de SQL, verifique as logs do sistema!");
            } 
            
            catch (ClassNotFoundException ex) {
                Logger.getLogger(janelaBuscaLivro.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Ocorreu um Erro interno, classe não encontrada!");
            }
        }
    });
    
    
    btExcluir.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (click==0){
                JOptionPane.showMessageDialog(null, "Selecione um livro para excluir!");
            }
            
        else{
                cadastraexclusao();
                int resposta = JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir o Livro "+ livro.getTitle().replaceAll("  ", "") + "?", getTitle(), JOptionPane.YES_NO_OPTION);;
                if (resposta == JOptionPane.YES_OPTION) {
                    try {
                        acao.excluirLivro();
                        JOptionPane.showMessageDialog(null, "Livro excluido com sucesso!");
                    } 

                    catch (SQLException ex) {
                        Logger.getLogger(janelaBuscaLivro.class.getName()).log(Level.SEVERE, null, ex);
                    } 

                    catch (ClassNotFoundException ex) {
                        Logger.getLogger(janelaBuscaLivro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
               
                if(resposta == JOptionPane.NO_OPTION){
                    JOptionPane.showMessageDialog(null, "Nenhuma exclusão efetuada!");
                }
            }
        }
    });
    
    btVoltar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            janelaInicial jInicial = new janelaInicial();
            dispose();
        }
    });
    
    table.getColumnModel().getColumn(0).setPreferredWidth(370);
    table.getColumnModel().getColumn(1).setPreferredWidth(60);
    table.getColumnModel().getColumn(2).setPreferredWidth(5);
    table.getColumnModel().getColumn(3).setPreferredWidth(140);
    table.getColumnModel().getColumn(4).setPreferredWidth(50);
    table.getColumnModel().getColumn(5).setPreferredWidth(80);
    table.getColumnModel().getColumn(6).setPreferredWidth(20);
    
    fundo = new JPanel();
    fundo.add(buscaPanel);
    fundo.add(scroolPanel);
    fundo.setLayout(null);
    
    buscaPanel.setBounds(100, 10, 900, 40);
    scroolPanel.setBounds(0, 50, 1100, 600);   
    fundo.setBounds(0, 40, 800, 600);
    
    getContentPane().add(fundo);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(1100, 700);
    setResizable(false);
    setVisible(true);
    setLocationRelativeTo(null);
  }
  
  private void criaTabela(){
    modeloBuscaLivro = new DefaultTableModel();
    modeloBuscaLivro.addColumn("Titulo");
    modeloBuscaLivro.addColumn("ISBN");
    modeloBuscaLivro.addColumn("Preço");
    modeloBuscaLivro.addColumn("Editora");
    modeloBuscaLivro.addColumn("Editora Id");
    modeloBuscaLivro.addColumn("Autor");
    modeloBuscaLivro.addColumn("Autor Id");  
  }
  
  class BtBuscaHandler implements ActionListener{
    @Override public void actionPerformed(ActionEvent e){
        criaBuscaLivro(txBusca.getText());
    }
  }
  
  public void cadastraexclusao(){
      livro.setTitle((String) table.getValueAt(row, 0));
      livro.setIsbn((String) table.getValueAt(row, 1));
      livro.setPrice((Double) table.getValueAt(row, 2));
      livro.setPublisher_id((String) table.getValueAt(row, 3));
  }
}
