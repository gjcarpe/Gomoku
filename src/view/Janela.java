package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controle.Controle;

public class Janela extends JFrame 
{
	private static final long serialVersionUID = -419158925384719190L;

	private Botao[][] botoesTabuleiro = new Botao[15][15];

	private Color corTexto;
	private Font fonte;
	private boolean bordasLigadas;
	private Container container;
	private Controle controle;

	private void criarIconesPecas() 
	{
		iconePosicaoSemPeca = new ImageIcon(
								getClass().getResource("/imagens/PosicaoSemPeca.png"));
		iconePosicaoPecaBranca = new ImageIcon(
								getClass().getResource("/imagens/PosicaoPecaBranca.png"));
		iconePosicaoPecaPreta = new ImageIcon(
								getClass().getResource("/imagens/PosicaoPecaPreta.png"));
	}
	
	private void criarBotoesTabuleiro()
	{
		for (int i = 0; i < 15; i++) 
		{
			for (int j = 0; j < 15; j++) 
			{
				Botao botao = new Botao("");
				botao.setName("[" + i + "][" + j + "]");
				botao.setBounds(300 + (j * 23), 89 + (i * 23), 23, 23);
				botao.setVisible(true);
				botao.setIcon(iconePosicaoSemPeca);
				botao.addMouseListener(new TratadorBotaoTabuleiro(this, botao, i, j));
				this.container.add(botao);
				this.botoesTabuleiro[i][j] = botao;
			}
		}
	}
	
	private Component encontreComponentePorNome(String nome)
	{
		Component resultado = null;
		Component[] componentes = this.container.getComponents();
		
		for (int i = 0; i < componentes.length; i++) 
		{
			if(componentes[i].getName().equals(nome))
			{
				resultado = componentes[i];
				break;
			}
		}
		
		return resultado;
	}
	
	public static ImageIcon iconePosicaoSemPeca;
	public static ImageIcon iconePosicaoPecaBranca;
	public static ImageIcon iconePosicaoPecaPreta;

	public Janela() 
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Gomoku");
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		this.controle = new Controle();
		this.corTexto = Color.RED;
		this.fonte = new Font("Arial", Font.BOLD, 25);
		this.bordasLigadas = true;

		this.container = this.getContentPane();
		this.container.setLayout(null);
		
		Botao info = new Botao("Info");
		info.setName("info");
		info.setFont(new Font("Arial", Font.PLAIN, 12));
		info.setForeground(this.corTexto);
		info.setBounds(300, 470, 350, 80);
		info.setFocusPainted(false);
		info.setMargin(new Insets(0, 0, 0, 0));
		info.setContentAreaFilled(false);
		info.setBorderPainted(this.bordasLigadas);
		info.setOpaque(false);
		info.addMouseListener(new TratadorMousePassou(info, info));
		info.setVisible(true);
		info.setEnabled(true);
		
		Botao novoJogo = new Botao("Novo Jogo");
		novoJogo.setName("novoJogo");
		novoJogo.setFont(this.fonte);
		novoJogo.setForeground(this.corTexto);
		novoJogo.setBounds(20, 20, 180, 80);
		novoJogo.setFocusPainted(false);
		novoJogo.setMargin(new Insets(0, 0, 0, 0));
		novoJogo.setContentAreaFilled(false);
		novoJogo.setBorderPainted(this.bordasLigadas);
		novoJogo.setOpaque(false);
		novoJogo.addActionListener(new TratadorNovoJogo(this));
		novoJogo.addMouseListener(new TratadorMousePassou(novoJogo, info));
		
		Botao encerrar = new Botao("Encerrar Jogo");
		encerrar.setName("encerrar");
		encerrar.setFont(this.fonte);
		encerrar.setForeground(this.corTexto);
		encerrar.setBounds(20, 20, 180, 80);
		encerrar.setFocusPainted(false);
		encerrar.setMargin(new Insets(0, 0, 0, 0));
		encerrar.setContentAreaFilled(false);
		encerrar.setBorderPainted(this.bordasLigadas);
		encerrar.setOpaque(false);
		encerrar.addActionListener(new TratadorEncerrar(this));
		encerrar.addMouseListener(new TratadorMousePassou(encerrar, info));
		encerrar.setVisible(false);
		encerrar.setEnabled(false);
		
		Botao umJogador = new Botao("1 Jogador");
		umJogador.setName("umJogador");
		umJogador.setFont(this.fonte);
		umJogador.setForeground(this.corTexto);
		umJogador.setBounds(20, 20, 180, 80);
		umJogador.setFocusPainted(false);
		umJogador.setMargin(new Insets(0, 0, 0, 0));
		umJogador.setContentAreaFilled(false);
		umJogador.setBorderPainted(this.bordasLigadas);
		umJogador.setOpaque(false);
		umJogador.addActionListener(new TratadorUmJogador(this));
		umJogador.addMouseListener(new TratadorMousePassou(umJogador, info));
		umJogador.setVisible(false);
		umJogador.setEnabled(false);
		
		Botao doisJogadores = new Botao("2 Jogadores");
		doisJogadores.setName("doisJogadores");
		doisJogadores.setFont(this.fonte);
		doisJogadores.setForeground(this.corTexto);
		doisJogadores.setBounds(20, 120, 180, 80);
		doisJogadores.setFocusPainted(false);
		doisJogadores.setMargin(new Insets(0, 0, 0, 0));
		doisJogadores.setContentAreaFilled(false);
		doisJogadores.setBorderPainted(this.bordasLigadas);
		doisJogadores.setOpaque(false);
		doisJogadores.addActionListener(new TratadorDoisJogadores(this));
		doisJogadores.addMouseListener(new TratadorMousePassou(doisJogadores, info));
		doisJogadores.setVisible(false);
		doisJogadores.setEnabled(false);
		
		Botao sair = new Botao("Sair");
		sair.setName("sair");
		sair.setFont(this.fonte);
		sair.setForeground(this.corTexto);
		sair.setBounds(20, 470, 180, 80);
		sair.setFocusPainted(false);
		sair.setMargin(new Insets(0, 0, 0, 0));
		sair.setContentAreaFilled(false);
		sair.setBorderPainted(this.bordasLigadas);
		sair.setOpaque(false);
		sair.addActionListener(new TratadorSair());
		sair.addMouseListener(new TratadorMousePassou(sair, info));
		
		// Descrições dos botões
		
		String descricao;
		
		descricao = "Inicia um novo jogo.";
		novoJogo.setDescricao(descricao);
		descricao = "Iniciar partida com um Jogador.";
		umJogador.setDescricao(descricao);
		descricao = "Iniciar partida com dois Jogadores.";
		doisJogadores.setDescricao(descricao);
		descricao = "Este é o campo de descrições.";
		info.setDescricao(descricao);
		descricao = "Encerrar a partida.";
		encerrar.setDescricao(descricao);
		descricao = "Sair do programa";
		sair.setDescricao(descricao);
		
		container.add(novoJogo);
		container.add(umJogador);
		container.add(doisJogadores);
		container.add(info);
		container.add(encerrar);
		container.add(sair);
		
		this.criarIconesPecas();
		this.criarBotoesTabuleiro();

		setVisible(true);
	}
	
	public Botao[][] getBotoesTabuleiro()
	{
		return this.botoesTabuleiro;
	}
	
	public void novoJogo()
	{		
		Component novoJogo = this.encontreComponentePorNome("novoJogo");
		novoJogo.setVisible(false);
		novoJogo.setEnabled(false);
		
		Component umJogador = this.encontreComponentePorNome("umJogador");
		umJogador.setVisible(true);
		umJogador.setEnabled(true);
		
		Component doisJogadores = this.encontreComponentePorNome("doisJogadores");
		doisJogadores.setVisible(true);
		doisJogadores.setEnabled(true);
	}
	
	public void umJogador()
	{
		this.controle.novoJogoUmJogador();
		
		Component umJogador = this.encontreComponentePorNome("umJogador");
		umJogador.setVisible(false);
		umJogador.setEnabled(false);
		
		Component doisJogadores = this.encontreComponentePorNome("doisJogadores");
		doisJogadores.setVisible(false);
		doisJogadores.setEnabled(false);
		
		Component encerrar = this.encontreComponentePorNome("encerrar");
		encerrar.setVisible(true);
		encerrar.setEnabled(true);
	}
	
	public void doisJogadores()
	{
		this.controle.novoJogoDoisJogadores();
		
		Component umJogador = this.encontreComponentePorNome("umJogador");
		umJogador.setVisible(false);
		umJogador.setEnabled(false);
		
		Component doisJogadores = this.encontreComponentePorNome("doisJogadores");
		doisJogadores.setVisible(false);
		doisJogadores.setEnabled(false);
		
		Component encerrar = this.encontreComponentePorNome("encerrar");
		encerrar.setVisible(true);
		encerrar.setEnabled(true);
	}
	
	public void encerrar()
	{
		this.controle.encerrar();
		
		Component encerrar = this.encontreComponentePorNome("encerrar");
		encerrar.setVisible(false);
		encerrar.setEnabled(false);
		
		Component novoJogo = this.encontreComponentePorNome("novoJogo");
		novoJogo.setVisible(true);
		novoJogo.setEnabled(true);
	}

	public void jogada(int x, int y) 
	{
		this.controle.jogada(x, y);
	}
	
	public int getTurno() 
	{
		return this.controle.getTurno();
	}
}
