package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Janela extends JFrame 
{
	private static final long serialVersionUID = -419158925384719190L;

	private JButton[][] botoesTabuleiro = new JButton[15][15];

	private Color corTexto;
	private Font fonte;


	private boolean bordasLigadas;
	private Container container;

	public static ImageIcon iconePosicaoSemPeca;


	public static ImageIcon iconePosicaoPecaBranca;


	public static ImageIcon iconePosicaoPecaPreta;

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
		
		this.criarIconesPecas();
		this.criarBotoesTabuleiro();

		setVisible(true);
	}

	private void criarIconesPecas() 
	{
		iconePosicaoSemPeca = new ImageIcon(getClass().getResource(
				"/imagens/PosicaoSemPeca.png"));
		iconePosicaoPecaBranca = new ImageIcon(getClass().getResource(
				"/imagens/PosicaoPecaBranca.png"));
		iconePosicaoPecaPreta = new ImageIcon(getClass().getResource(
				"/imagens/PosicaoPecaPreta.png"));
	}
	
	private void criarBotoesTabuleiro()
	{
		for (int i = 0; i < 15; i++) 
		{
			for (int j = 0; j < 15; j++) 
			{
				JButton botao = new JButton();
				botao.setName("" + i + j);
				botao.setBounds(300 + (j * 23), 89 + (i * 23), 23, 23);
				botao.setVisible(true);
				botao.setIcon(iconePosicaoSemPeca);
				botao.addMouseListener(new TratadorBotaoTabuleiro(botao));
				this.container.add(botao);
				this.botoesTabuleiro[i][j] = botao;
			}
		}
	}
	
	public JButton[][] getBotoesTabuleiro()
	{
		return this.botoesTabuleiro;
	}

}
