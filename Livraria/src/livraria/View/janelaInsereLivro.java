package livraria.View;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import livraria.Model.ActionsBanco;
import static livraria.Model.ActionsBanco.comboboxAutor;
import static livraria.Model.ActionsBanco.comboboxEditora;
import livraria.Model.Authors;
import livraria.Model.Books;
import livraria.Model.Publishers;

/**
 * @author thaismor
 */
public class janelaInsereLivro extends JFrame{
    
    JLabel rotTitulo;
    JLabel rotISBN;
    JLabel rotEditora;
    JLabel rotPreco;
    JLabel rotAutor;
    JLabel rotAutor_id;
    JLabel rotEditora_id;
    
    JComboBox comboEditora;
    JComboBox comboAutor;
    JTextField campoTitulo;
    JTextField campoISBN;
    JTextField campoPreco;
    JTextField campoAutor_id;
    JTextField campoEditora_id;
    
    JButton cadastrar;
    JButton limpar;
    JButton voltar;
    
    public static ArrayList<String> listNome_autor = new ArrayList<String>();
    public static ArrayList<String> listId_autor = new ArrayList<String>();
    public static ArrayList<String> listNome_editora = new ArrayList<String>();
    public static ArrayList<String> listId_editora = new ArrayList<String>();
    
    ActionsBanco acao = new ActionsBanco();
    public static Authors ilaut = new Authors();
    public static Books ilbok = new Books();
    public static Publishers ilpub = new Publishers();
    
    
    public janelaInsereLivro() throws SQLException, ClassNotFoundException, ParseException{
        
        super("Sistema Livraria - Cadastro de Livros");
	Container tela = getContentPane();
	tela.setLayout(null);
        
        rotTitulo = new JLabel("Titulo:");
	rotISBN = new JLabel("ISBN:");
	rotEditora = new JLabel("Editora:");
	rotAutor = new JLabel("Autor:");
	rotPreco = new JLabel("Preco:");
        rotEditora_id = new JLabel("ID Editora:");
        rotAutor_id = new JLabel("ID Autor:");
	campoTitulo = new JTextField();
	campoISBN = new JTextField();
	campoPreco = new JTextField();
        campoAutor_id = new JTextField();
        campoEditora_id = new JTextField();
        campoAutor_id.setEditable(false);
        campoEditora_id.setEditable(false);
	
        
	cadastrar = new JButton("Cadastrar");
	limpar = new JButton("Limpar");
	voltar = new JButton("Voltar"); 
	
        comboEditora = new JComboBox();
	comboAutor = new JComboBox();
        
	comboboxAutor();
        comboboxEditora();
        
	DefaultComboBoxModel modAutor = new DefaultComboBoxModel(listNome_autor.toArray());
	DefaultComboBoxModel modEditora = new DefaultComboBoxModel(listNome_editora.toArray());
			
	comboEditora.setModel(modEditora);	
	comboAutor.setModel(modAutor);
		 
        rotTitulo.setBounds(20, 50, 50, 30);
	rotISBN.setBounds(20, 80, 50, 30);
	rotEditora.setBounds(20, 110, 80, 30);
	rotEditora_id.setBounds(20, 140, 90, 30);
	rotAutor.setBounds(20, 170, 50, 30);
        rotAutor_id.setBounds(20, 200, 80, 30);
        rotPreco.setBounds(20, 230, 50, 30);
        
        campoTitulo.setBounds(75, 55, 400, 20);
	campoISBN.setBounds(75, 85, 400, 20);
	comboEditora.setBounds(85, 115, 390, 20);
	campoEditora_id.setBounds(105, 145, 370, 20);
	comboAutor.setBounds(75, 175, 400, 20);
        campoAutor_id.setBounds(95, 205, 380, 20);
        campoPreco.setBounds(75, 235, 400, 20);
	
        cadastrar.setBounds(90, 280, 110, 20);
	limpar.setBounds(215, 280, 90, 20);		
	voltar.setBounds(320, 280, 100, 20);
		
		
	cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                                
                if (campoTitulo.getText().isEmpty() || campoISBN.getText().isEmpty() || campoPreco.getText().isEmpty() || comboAutor.getSelectedIndex() == 0 || comboEditora.getSelectedIndex() == 0){
                    
                    JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o livro, existem campos vazios!");
                }
                
                else {
                    
                    
                    try {
                        cadastraInformacao();
                        acao.insereLivro();
                        JOptionPane.showMessageDialog(null, "Livro Cadastrado com Sucesso!");
                    } 
                    
                    catch (SQLException ex) {
                        Logger.getLogger(janelaInsereLivro.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Ocorreu um Erro de SQL, verifique as logs do sistema!");
                    } 
                    
                    catch (ClassNotFoundException ex) {
                        Logger.getLogger(janelaInsereLivro.class.getName()).log(Level.SEVERE, null, ex);
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
                    janelaBuscaLivro jbuscalivro = new janelaBuscaLivro();
                    dispose();
                } 
                
                catch (ClassNotFoundException ex) {
                    Logger.getLogger(janelaInsereLivro.class.getName()).log(Level.SEVERE, null, ex);
                } 
                
                catch (SQLException ex) {
                    Logger.getLogger(janelaInsereLivro.class.getName()).log(Level.SEVERE, null, ex);
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
        tela.add(comboEditora);
	tela.add(comboAutor);
	tela.add(campoPreco);
        tela.add(campoEditora_id);
        tela.add(campoAutor_id);
	tela.add(cadastrar);
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
        campoISBN.setText(null);
        campoPreco.setText(null);
        campoAutor_id.setText(null);
        campoEditora_id.setText(null);
        comboAutor.setSelectedIndex(0);
        comboEditora.setSelectedIndex(0);
    }
    
    public void cadastraInformacao(){
        int value_autor = comboAutor.getSelectedIndex();
        int value_editora = comboEditora.getSelectedIndex();
        campoAutor_id.setText(listId_autor.get(value_autor));
        campoEditora_id.setText(listId_editora.get(value_editora));
        
        ilaut.setName(comboAutor.getSelectedItem().toString());
        ilaut.setAuthor_id(Integer.parseInt(campoAutor_id.getText()));
        ilpub.setName(comboEditora.getSelectedItem().toString());
        ilpub.setPublisher_id(Integer.parseInt(campoEditora_id.getText()));
        ilbok.setTitle(campoTitulo.getText());
        ilbok.setIsbn(campoISBN.getText());
        ilbok.setPrice(Double.parseDouble(campoPreco.getText().replaceAll(",", ".")));
        ilbok.setPublisher_id(campoEditora_id.getText());
    }
}
