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
import static livraria.Model.ActionsBanco.cadastraIdEditora;
import livraria.Model.Publishers;

/**
 * @author thaismor
 */
public class janelaInsereEditora extends JFrame{
    JLabel rotEditora_id;
    JLabel rotNome;
    JLabel rotURL;
    public static JTextField campoEditora_id;
    JTextField campoNome;
    JTextField campoURL;
    JButton inserir;
    JButton limpar;
    JButton voltar;
    static public Publishers ipub = new Publishers();
    ActionsBanco acao = new ActionsBanco();
    
    public janelaInsereEditora() throws SQLException, ClassNotFoundException{
        
        super("Sistema Livraria - Cadastro de Editoras");
	Container tela = getContentPane();
	tela.setLayout(null);
        
        rotEditora_id = new JLabel("ID Editora:");
	rotNome = new JLabel("Nome:");
	rotURL = new JLabel("URL:");
        
        campoEditora_id = new JTextField();
        campoEditora_id.setEditable(false);
	campoNome = new JTextField();
	campoURL = new JTextField();
        
        inserir = new JButton("Cadastrar");
	limpar = new JButton("Limpar");
	voltar = new JButton("Voltar");
	
       	 
        rotEditora_id.setBounds(20, 50, 80, 30);
	rotNome.setBounds(20, 80, 50, 30);
	rotURL.setBounds(20, 110, 80, 30);
	
        campoEditora_id.setBounds(105, 55, 370, 20);
	campoNome.setBounds(75, 85, 400, 20);
	campoURL.setBounds(75, 115, 400, 20);
	
        inserir.setBounds(90, 170, 110, 20);
	limpar.setBounds(215, 170, 90, 20);		
	voltar.setBounds(320, 170, 100, 20);
		
		
	inserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    cadastraIdEditora();
                } 
                
                catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(janelaInsereEditora.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                                
                if (campoEditora_id.getText().isEmpty() || campoNome.getText().isEmpty() || campoURL.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o livro, existem campos vazios!");
                }
                
                else {
                    
                    cadastraInformacao();
                    
                    try {
                        acao.insereEditora();
                        JOptionPane.showMessageDialog(null, "Editora cadastrada com sucesso!");    
                    } 
                    
                    catch (SQLException ex) {
                        Logger.getLogger(janelaInsereEditora.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Ocorreu um Erro de SQL, verifique as logs do sistema!");
                    } 
                    
                    catch (ClassNotFoundException ex) {
                        Logger.getLogger(janelaInsereEditora.class.getName()).log(Level.SEVERE, null, ex);
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
                    janelaBuscaEditora jBuscaEditora = new janelaBuscaEditora();
                    dispose();
                } 
                
                catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(janelaInsereEditora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        tela.add(rotEditora_id);
	tela.add(rotNome);
	tela.add(rotURL);
        tela.add(campoEditora_id);
        tela.add(campoNome);
        tela.add(campoURL);
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
        campoEditora_id.setText(null);
        campoNome.setText(null);
        campoURL.setText(null);
        
    }
    
    public void cadastraInformacao(){
        ipub.setName(campoNome.getText());
        ipub.setUrl(campoURL.getText());
    }
    
}
