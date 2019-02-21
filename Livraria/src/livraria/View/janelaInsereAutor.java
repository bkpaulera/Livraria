package livraria.View;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import livraria.Model.ActionsBanco;
import livraria.Model.Authors;
import static livraria.Model.ActionsBanco.cadastraIdAutor;

/**
 * @author thaismor
 */
public class janelaInsereAutor extends JFrame {
    JLabel rotAutor_id;
    JLabel rotNome;
    JLabel rotSobrenome;
    public static JTextField campoAutor_id;
    JTextField campoNome;
    JTextField campoSobrenome;
    JButton inserir;
    JButton limpar;
    JButton voltar;
    
    ActionsBanco acao = new ActionsBanco();
    public static Authors iaut = new Authors();
    
    public janelaInsereAutor() throws SQLException, ClassNotFoundException{
        
        super("Sistema Livraria - Cadastro de Autores");
	Container tela = getContentPane();
	tela.setLayout(null);
        
        rotAutor_id = new JLabel("ID Autor:");
	rotNome = new JLabel("Nome:");
	rotSobrenome = new JLabel("Sobrenome:");
        
        campoAutor_id = new JTextField();
	campoNome = new JTextField();
	campoSobrenome = new JTextField();
        campoAutor_id.setEditable(false);
        
        inserir = new JButton("Cadastrar");
	limpar = new JButton("Limpar");
	voltar = new JButton("Voltar");
	
       	 
        rotAutor_id.setBounds(20, 50, 80, 30);
	rotNome.setBounds(20, 80, 50, 30);
	rotSobrenome.setBounds(20, 110, 100, 30);
	
        campoAutor_id.setBounds(95, 55, 380, 20);
	campoNome.setBounds(75, 85, 400, 20);
	campoSobrenome.setBounds(115, 115, 360, 20);
	
        inserir.setBounds(90, 170, 110, 20);
	limpar.setBounds(215, 170, 90, 20);		
	voltar.setBounds(320, 170, 100, 20);
		
		
	inserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                                
                if (campoNome.getText().isEmpty() || campoSobrenome.getText().isEmpty()){
                    
                    JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o autor, existem campos vazios!");
                }
                
                else {
                    
                    try {
                        cadastraInformacao();
                        cadastraIdAutor();
                        acao.insereAutor();
                        JOptionPane.showMessageDialog(null, "Autor Cadastrado com Sucesso!");
                    } 
                    
                    catch (SQLException ex) {
                        Logger.getLogger(janelaInsereAutor.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Ocorreu um Erro de SQL, verifique as logs do sistema!");
                    } 
                    
                    catch (ClassNotFoundException ex) {
                        Logger.getLogger(janelaInsereAutor.class.getName()).log(Level.SEVERE, null, ex);
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
                    janelaBuscaAutor jBuscaAutor = new janelaBuscaAutor();
                    dispose();
                } 
                
                catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(janelaInsereAutor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        tela.add(rotAutor_id);
	tela.add(rotNome);
	tela.add(rotSobrenome);
        tela.add(campoAutor_id);
        tela.add(campoNome);
        tela.add(campoSobrenome);
        tela.add(inserir);
	tela.add(limpar);
	tela.add(voltar);
	
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(500, 300);
	setResizable(false);
	setVisible(true);
	setLocationRelativeTo(null);
    }
    
    
    public void limpar(){
        campoAutor_id.setText(null);
        campoNome.setText(null);
        campoSobrenome.setText(null);
    }
    
    public void cadastraInformacao(){
        iaut.setFname(campoSobrenome.getText());
        iaut.setName(campoNome.getText());
    }

}
