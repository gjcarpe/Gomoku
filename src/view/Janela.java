package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Janela extends JFrame
{
	private static final long serialVersionUID = -419158925384719190L;
	
	private Color corTexto;
	private Font fonte;
	private boolean bordasLigadas;
	private Container container;
	
	public Janela()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Gomoku");
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		this.corTexto = Color.RED;
		this.fonte = new Font("Arial", Font.BOLD, 25);
		this.bordasLigadas = true;
		
		this.container = this.getContentPane();
		this.container.setLayout(null);

		JButton novoJogo = new JButton("Novo Jogo");
		novoJogo.setFont(this.fonte);
		novoJogo.setForeground(this.corTexto);
		novoJogo.setBounds(20, 20, 180, 80);
		novoJogo.setFocusPainted(false);
		novoJogo.setMargin(new Insets(0, 0, 0, 0));
		novoJogo.setContentAreaFilled(false);
		novoJogo.setBorderPainted(this.bordasLigadas);
		novoJogo.setOpaque(false);
		novoJogo.addActionListener(new TratadorNovoJogo());
		novoJogo.addMouseListener(new TratadorMousePassou(novoJogo));
        
		JButton saida = new JButton("Sair");
		saida.setFont(this.fonte);
		saida.setForeground(this.corTexto);
		saida.setBounds(20, 470, 180, 80);
		saida.setFocusPainted(false);
		saida.setMargin(new Insets(0, 0, 0, 0));
		saida.setContentAreaFilled(false);
		saida.setBorderPainted(this.bordasLigadas);
		saida.setOpaque(false);
		saida.addActionListener(new TratadorSair());
		saida.addMouseListener(new TratadorMousePassou(saida));
	
		container.add(novoJogo);
		container.add(saida);
        
		setVisible(true);
	}
	
}
