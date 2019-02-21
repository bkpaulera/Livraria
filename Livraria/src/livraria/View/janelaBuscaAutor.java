package livraria.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import livraria.Model.ActionsBanco;
import static livraria.Model.ActionsBanco.criaBuscaAutor;
import livraria.Model.Authors;

/**
 * @author thaismor
 */
public class janelaBuscaAutor extends JFrame {
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
  public static DefaultTableModel modeloBuscaAutor;
  int row;
  int click;
  
  ActionsBanco acao = new ActionsBanco();
  public static Authors autor = new Authors();
  
  public janelaBuscaAutor() throws ClassNotFoundException, SQLException{
    super("Sistema Livraria - Buscar Autores");
    criaTabela();
    criaJanela();
  }

  private void criaJanela() throws ClassNotFoundException, SQLException{
    txBusca = new JTextField(30);
    
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

    table = new JTable(modeloBuscaAutor);
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
                JOptionPane.showMessageDialog(null, "Selecione um autor para alterar!");
            }
            
            else{
                
                try {
                    janelaAlteraAutor jalteraAutor = new janelaAlteraAutor();
                    jalteraAutor.campoNome.setText((String) table.getValueAt(row, 1));
                    jalteraAutor.campoAutor_id.setText((String) table.getValueAt(row, 0));
                    jalteraAutor.campoSobrenome.setText((String) table.getValueAt(row, 2));
                    dispose();
                } 
                
                catch (SQLException ex) {
                    Logger.getLogger(janelaBuscaAutor.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Ocorreu um Erro de SQL, verifique as logs do sistema!");
                } 
                
                catch (ClassNotFoundException ex) {
                    Logger.getLogger(janelaBuscaAutor.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Ocorreu um Erro interno, classe não encontrada!");
                }
            }
        }
    });
    
    btInserir.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                janelaInsereAutor jInsereAutor = new janelaInsereAutor();
                dispose();
            } 
            
            catch (SQLException ex) {
                Logger.getLogger(janelaBuscaAutor.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Ocorreu um Erro de SQL, verifique as logs do sistema!");
            } 
            
            catch (ClassNotFoundException ex) {
                Logger.getLogger(janelaBuscaAutor.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Ocorreu um Erro interno, classe não encontrada!");
            }
            
        }
    });
    
    
    btExcluir.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (click==0){
                JOptionPane.showMessageDialog(null, "Selecione um autor para excluir!");
            }
            else{
                
                cadastraExclusao();
                int resposta = JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir o autor " + autor.getName().replaceAll(" ", "") + " " + autor.getFname().replaceAll(" ", "") + "?", getTitle(), JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    int resposta2 = JOptionPane.showConfirmDialog(null,"Ao excluir esse autor, você também excluirá todos os livros relacionados a ele, deseja mesmo continuar?", getTitle(), JOptionPane.YES_NO_OPTION);;
                    if (resposta2 == JOptionPane.YES_OPTION){
                        try {
                            acao.excluirAutor();
                            JOptionPane.showMessageDialog(null, "Autor excluido com sucesso!");
                        } 

                        catch (SQLException ex) {
                            Logger.getLogger(janelaBuscaEditora.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(null, "Ocorreu um Erro de SQL, verifique as logs do sistema!");
                        } 

                        catch (ClassNotFoundException ex) {
                            Logger.getLogger(janelaBuscaEditora.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(null, "Ocorreu um Erro interno, classe não encontrada!");
                        }
                    }
                    if(resposta2 == JOptionPane.NO_OPTION){
                        JOptionPane.showMessageDialog(null, "Nenhuma exclusão efetuada!");
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
    
    
    table.getColumnModel().getColumn(0).setPreferredWidth(20);
    table.getColumnModel().getColumn(1).setPreferredWidth(100);
    table.getColumnModel().getColumn(2).setPreferredWidth(100);
    
    fundo = new JPanel();
    fundo.add(buscaPanel);
    fundo.add(scroolPanel);
    fundo.setLayout(null);
    
    buscaPanel.setBounds(0, 10, 800, 40);
    scroolPanel.setBounds(0, 50, 800, 600);
    fundo.setBounds(0, 40, 800, 600);
    
    getContentPane().add(fundo);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(800, 700);
    setResizable(false);
    setVisible(true);
    setLocationRelativeTo(null);
    
  }
  
  private void criaTabela(){
    modeloBuscaAutor = new DefaultTableModel();
    modeloBuscaAutor.addColumn("ID Autor");
    modeloBuscaAutor.addColumn("Nome");
    modeloBuscaAutor.addColumn("Sobrenome");  
  }
  
  class BtBuscaHandler implements ActionListener{
    @Override public void actionPerformed(ActionEvent e){
        criaBuscaAutor(txBusca.getText());
    }
  }
  
  public void cadastraExclusao(){
      autor.setAuthor_id((Integer.parseInt((String) table.getValueAt(row, 0))));
      autor.setName((String) table.getValueAt(row, 1));
      autor.setFname((String) table.getValueAt(row, 2));
  }
}
