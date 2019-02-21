package livraria.View;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author thaismor
 */
public class janelaInicial extends JFrame {
    
    JLabel inicial;
    JLabel mensagem;
    JLabel mensagem2;
    JLabel mensagem3;
    JLabel mensagem4;
    JLabel mensagem5;
    JLabel mensagem6;
    JButton livro;
    JButton autor;
    JButton editora;

    public janelaInicial() {
	
        super("Sistema Livraria");
	Container tela = getContentPane();
	tela.setLayout(null);
	livro = new JButton("Buscar Livros");
	autor = new JButton("Buscar Autores");
	editora = new JButton("Buscar Editoras");
	
        inicial = new JLabel("Bem Vindo ao Sistema Livraria");
        mensagem = new JLabel("Aqui você pode manipular os dados da livraria");
	mensagem2 = new JLabel("são eles: LIVROS, AUTORES e EDITORAS.");
	mensagem3 = new JLabel("- Você pode MODIFICAR itens já existentes;");
	mensagem4 = new JLabel("- Você pode INSERIR novos dados;");
	mensagem5 = new JLabel("- Por fim, voce pode EXCLUIR itens;");
	mensagem6 = new JLabel("Clique na busca que deseja realizar!");
	    
	inicial.setBounds(150, 20, 230, 30);
	mensagem.setBounds(100, 20, 475, 100);
	mensagem2.setBounds(120, 35, 475, 100);
	mensagem3.setBounds(110, 60, 475, 100);
	mensagem4.setBounds(110, 75, 475, 100);
	mensagem5.setBounds(110, 90, 475, 100);
	mensagem6.setBounds(120, 115, 475, 100);
	
        livro.setBounds(15, 200, 150, 20);
	autor.setBounds(175, 200, 150, 20);
	editora.setBounds(335, 200, 150, 20);
	
	livro.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    janelaBuscaLivro jBuscaLivro = new janelaBuscaLivro();
                    dispose();
		} 
                
                catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
		}				
            }
	});
		
	autor.addActionListener(new ActionListener() {		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
                    janelaBuscaAutor jBuscaAutor = new janelaBuscaAutor();
                    dispose();
		} 
                
                catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
		}		
            }
	});
		
	editora.addActionListener(new ActionListener() {
			
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
                    janelaBuscaEditora jBuscaEditora = new janelaBuscaEditora();
                    dispose();
		} 
                
                catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
		}			
            }
	});
	
	tela.add(livro);
	tela.add(autor);
	tela.add(editora);
	tela.add(mensagem);
	tela.add(inicial);
	tela.add(mensagem2);
	tela.add(mensagem3);
	tela.add(mensagem4);
	tela.add(mensagem5);
	tela.add(mensagem6);
	
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(500, 300);
	setResizable(false);
	setVisible(true);
	setLocationRelativeTo(null);	    
    }
}
