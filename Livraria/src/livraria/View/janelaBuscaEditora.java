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
import static livraria.Model.ActionsBanco.criaBuscaEditora;
import livraria.Model.Publishers;

/**
 * @author thaismor
 */
public class janelaBuscaEditora extends JFrame{
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
  public static DefaultTableModel modeloBuscaEditora;
  int row;
  int click;
  
  public static Publishers edit = new Publishers();
  ActionsBanco acao = new ActionsBanco();
    
  public janelaBuscaEditora() throws ClassNotFoundException, SQLException{
    super("Sistema Livraria - Buscar Editoras");
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

    table = new JTable(modeloBuscaEditora);
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
                JOptionPane.showMessageDialog(null, "Selecione uma editora para alterar!");
            }
            
        else{
            
            try {
                janelaAlteraEditora jalteraEditora = new janelaAlteraEditora();
                jalteraEditora.campoNome.setText((String) table.getValueAt(row, 1));
                jalteraEditora.campoEditora_id.setText((String) table.getValueAt(row, 0));
                jalteraEditora.campoURL.setText((String) table.getValueAt(row, 2));
                dispose();
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
        }
    });
    
    btInserir.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                janelaInsereEditora jInsereEditora = new janelaInsereEditora();
                dispose();
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
    });
    
    
    btExcluir.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (click==0){
                JOptionPane.showMessageDialog(null, "Selecione uma editora para excluir!");
            }
            
        else{
            cadastraExclusao();
            int resposta = JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir a editora "+ edit.getName().replaceAll(" ", "") + "?", getTitle(), JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                int resposta2 = JOptionPane.showConfirmDialog(null,"Ao excluir essa editora, você também excluirá todos os livros relacionados a ela, deseja mesmo continuar?", getTitle(), JOptionPane.YES_NO_OPTION);
                if (resposta2 == JOptionPane.YES_OPTION){
                    try {
                        acao.excluirEditora();
                        JOptionPane.showMessageDialog(null, "Editora excluida com sucesso!");
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
    scroolPanel.setBounds(0, 50, 800, 300);
    fundo.setBounds(0, 40, 800, 600);
   
    getContentPane().add(fundo);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(800, 400);
    setResizable(false);
    setVisible(true);
    setLocationRelativeTo(null);
  }
  
  private void criaTabela(){
    modeloBuscaEditora = new DefaultTableModel();
    modeloBuscaEditora.addColumn("ID Editora");
    modeloBuscaEditora.addColumn("Nome");
    modeloBuscaEditora.addColumn("URL");
  }
  
  
  
  class BtBuscaHandler implements ActionListener{
    @Override public void actionPerformed(ActionEvent e){
        criaBuscaEditora(txBusca.getText());
    }
  }
  
  public void cadastraExclusao(){
      edit.setPublisher_id(Integer.parseInt( (String) table.getValueAt(row, 0)));
      edit.setName((String) table.getValueAt(row, 1));
      edit.setUrl((String) table.getValueAt(row, 2));
  }
}
