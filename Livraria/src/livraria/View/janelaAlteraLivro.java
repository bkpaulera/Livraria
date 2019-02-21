package livraria.View;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import livraria.Model.ActionsBanco;
import livraria.Model.Authors;
import livraria.Model.Books;
import livraria.Model.Publishers;

/**
 * @author thaismor
 */
public class janelaAlteraLivro extends JFrame {
    
    JLabel rotTitulo;
    JLabel rotISBN;
    JLabel rotEditora;
    JLabel rotEditora_id;
    JLabel rotPreco;
    JLabel rotAutor;
    JLabel rotAutor_id;
    public JTextField campoTitulo;
    public JTextField campoISBN;
    public JTextField campoPreco;
    public JTextField campoAutor;
    public JTextField campoAutor_id;
    public JTextField campoEditora;
    public JTextField campoEditora_id;
    JButton alterar;
    JButton limpar;
    JButton voltar;
    
    ActionsBanco acao = new ActionsBanco();
    public static Authors alaut = new Authors();
    public static Books albok = new Books();
    public static Publishers alpub = new Publishers();
    
    public janelaAlteraLivro() throws SQLException, ClassNotFoundException{
        
        super("Sistema Livraria - Atualização de Livros");
	Container tela = getContentPane();
	tela.setLayout(null);
        
        rotTitulo = new JLabel("Titulo:");
	rotISBN = new JLabel("ISBN:");
	rotEditora = new JLabel("Editora:");
        rotEditora_id = new JLabel("ID Editora:");
	rotAutor = new JLabel("Autor:");
        rotAutor_id = new JLabel("ID Autor:");
	rotPreco = new JLabel("Preco:");
	
        campoTitulo = new JTextField();
	campoISBN = new JTextField();
        campoISBN.setEditable(false);
	campoPreco = new JTextField();
        campoAutor = new JTextField();
        campoAutor_id = new JTextField();
        campoEditora = new JTextField();
        campoEditora_id = new JTextField();
	
        alterar = new JButton("Atualizar");
	limpar = new JButton("Limpar");
	voltar = new JButton("Voltar");
	
       	 
        rotTitulo.setBounds(20, 50, 50, 30);
	rotISBN.setBounds(20, 80, 50, 30);
	rotEditora.setBounds(20, 110, 80, 30);
	rotEditora_id.setBounds(20, 140, 90, 30);
	rotAutor.setBounds(20, 170, 50, 30);
        rotAutor_id.setBounds(20, 200, 80, 30);
        rotPreco.setBounds(20, 230, 50, 30);
        campoTitulo.setBounds(75, 55, 400, 20);
	campoISBN.setBounds(75, 85, 400, 20);
	campoEditora.setBounds(85, 115, 390, 20);
	campoEditora_id.setBounds(105, 145, 370, 20);
	campoAutor.setBounds(75, 175, 400, 20);
        campoAutor_id.setBounds(95, 205, 380, 20);
        campoPreco.setBounds(75, 235, 400, 20);
	alterar.setBounds(90, 280, 110, 20);
	limpar.setBounds(215, 280, 90, 20);		
	voltar.setBounds(320, 280, 100, 20);
		
		
	alterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                                
                if (campoTitulo.getText().isEmpty() || campoISBN.getText().isEmpty() || campoPreco.getText().isEmpty() || campoAutor.getText().isEmpty() || campoEditora.getText().isEmpty() || campoAutor_id.getText().isEmpty() || campoEditora_id.getText().isEmpty()){
                    
                    JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o livro, existem campos vazios!");
                }
                
                else {
                    
                    try {
                        cadastraInformacao();
                        acao.atualizaLivro();
                        JOptionPane.showMessageDialog(null, "Livro Atualizado com Sucesso!");
                    } 
                    
                    catch (SQLException ex) {
                        Logger.getLogger(janelaAlteraLivro.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Ocorreu um Erro de SQL, verifique as logs do sistema!");
                    } 
                    
                    catch (ClassNotFoundException ex) {
                        Logger.getLogger(janelaAlteraLivro.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Ocorreu um Erro interno, classe não encontrada!");
                    }
                }
            }
        });
        
        limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpar();
            }
        });
        
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    janelaBuscaLivro jBuscaLivro = new janelaBuscaLivro();
                    dispose();
                } 
                
                catch (ClassNotFoundException ex) {
                    Logger.getLogger(janelaAlteraLivro.class.getName()).log(Level.SEVERE, null, ex);
                } 
                
                catch (SQLException ex) {
                    Logger.getLogger(janelaAlteraLivro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        tela.add(rotTitulo);
	tela.add(rotISBN);
	tela.add(rotEditora);
        tela.add(rotEditora_id);
	tela.add(rotAutor);
        tela.add(rotAutor_id);
	tela.add(rotPreco);
        tela.add(campoTitulo);
        tela.add(campoISBN);
        tela.add(campoPreco);
        tela.add(campoAutor);
        tela.add(campoAutor_id);
        tela.add(campoEditora);
        tela.add(campoEditora_id);
	tela.add(alterar);
	tela.add(limpar);
	tela.add(voltar);
	
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(500, 400);
	setResizable(false);
	setVisible(true);
	setLocationRelativeTo(null);
        
    }
    
    public void limpar(){
        campoTitulo.setText(null);
        campoPreco.setText(null);
        campoAutor.setText(null);
        campoAutor_id.setText(null);
        campoEditora.setText(null);
        campoEditora_id.setText(null);
    }
    
    public void cadastraInformacao(){
        albok.setTitle(campoTitulo.getText());
        albok.setIsbn(campoISBN.getText());
        albok.setPrice(Double.parseDouble(campoPreco.getText().replaceAll(",", ".")));
        albok.setPublisher_id(campoEditora_id.getText());
        alpub.setPublisher_id(Integer.parseInt(campoEditora_id.getText()));
        alpub.setName(campoEditora.getText());
        alaut.setAuthor_id(Integer.parseInt(campoAutor_id.getText()));
        alaut.setName(campoAutor.getText());
    }
}
