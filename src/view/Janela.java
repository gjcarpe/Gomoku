package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controle.Controle;

public class Janela extends JFrame 
{
	private static final long serialVersionUID = -419158925384719190L;

	private boolean bordasLigadas;
	private Color corTexto;
	private Font fonte;
	private Container container;
	private Controle controle;
	private Botao[][] botoesTabuleiro;
	
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
		int x = 0;
		this.botoesTabuleiro = new Botao[15][15];
		for (int i = 0; i < 15; i++) 
		{
			for (int j = 0; j < 15; j++) 
			{
				Botao botao = new Botao("");
				botao.setName("" + x);
				botao.setBounds(300 + (j * 23), 89 + (i * 23), 23, 23);
				botao.setVisible(true);
				botao.setIcon(iconePosicaoSemPeca);
				botao.addMouseListener(new TratadorBotaoTabuleiro(this, botao, i, j));
				this.container.add(botao);
				this.botoesTabuleiro[i][j] = botao;
				x++;
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
	
	private void carregarImagemDeFundo()
	{
		try
		{
			this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/imagens/background.png")))));
		} 
		catch (Exception e) 
    	{
    		e.printStackTrace();
    	}
	}
	
	public static ImageIcon iconePosicaoSemPeca;
	public static ImageIcon iconePosicaoPecaBranca;
	public static ImageIcon iconePosicaoPecaPreta;

	public Janela(Controle controle) 
	{
		this.carregarImagemDeFundo();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Gomoku");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		// Parâmetros de controle
		
		this.controle = controle;
		this.corTexto = Color.CYAN;
		this.fonte = new Font("Arial", Font.BOLD, 25);
		this.bordasLigadas = false;
		this.container = this.getContentPane();
		this.container.setLayout(null);
		
		// Criação dos botões da interface
		
		Botao info = new Botao("");
		info.setName("info");
		info.setFont(new Font("Arial", Font.PLAIN, 15));
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
		
		Botao turno = new Botao("");
		turno.setName("turno");
		turno.setFont(new Font("Arial", Font.PLAIN, 12));
		turno.setForeground(Color.WHITE);
		turno.setBounds(300, 20, 350, 40);
		turno.setFocusPainted(false);
		turno.setMargin(new Insets(0, 0, 0, 0));
		turno.setContentAreaFilled(false);
		turno.setBorderPainted(this.bordasLigadas);
		turno.setOpaque(false);
		turno.addMouseListener(new TratadorMousePassou(turno, info));
		turno.setVisible(false);
		turno.setEnabled(false);
		
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
		
		descricao = "Encerrar a partida.";
		encerrar.setDescricao(descricao);
		
		descricao = "Iniciar partida com um Jogador.";
		umJogador.setDescricao(descricao);
		
		descricao = "Iniciar partida com dois Jogadores.";
		doisJogadores.setDescricao(descricao);
		
		descricao = "Mostra de quem é a vez de jogar.";
		turno.setDescricao(descricao);
		
		descricao = "Este é o campo de descrições.";
		info.setDescricao(descricao);
		
		descricao = "Sair do programa";
		sair.setDescricao(descricao);
		
		// Adiciona os botões da interface ao container
		
		container.add(novoJogo);
		container.add(turno);
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
		
		Botao turno = (Botao) this.encontreComponentePorNome("turno");
		turno.setForeground(Color.WHITE);
		turno.setText("JOGADOR BRANCO");
		turno.setVisible(true);
		turno.setEnabled(true);
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
		
		Botao turno = (Botao) this.encontreComponentePorNome("turno");
		turno.setForeground(Color.WHITE);
		turno.setText("JOGADOR BRANCO");
		turno.setVisible(true);
		turno.setEnabled(true);
	}
	
	public void encerrar()
	{
		int x = 0;
		Botao atual;
		for (int i = 0; i < 15; i++) 
		{
			for (int j = 0; j < 15; j++) 
			{
				atual = (Botao) this.encontreComponentePorNome("" + x);
				atual.setIcon(iconePosicaoSemPeca);
				x++;
			}
		}
		
		Component encerrar = this.encontreComponentePorNome("encerrar");
		encerrar.setVisible(false);
		encerrar.setEnabled(false);
		
		Component novoJogo = this.encontreComponentePorNome("novoJogo");
		novoJogo.setVisible(true);
		novoJogo.setEnabled(true);
		
		Botao turno = (Botao) this.encontreComponentePorNome("turno");
		turno.setText("");
		turno.setVisible(false);
		turno.setEnabled(false);
	}

	public void jogada(int x, int y) 
	{
		try 
		{
			this.atualizarTurno();
			this.controle.jogada(x, y);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("EXCEPTION JOGADA");
		}
	}
	
	public void atualizarTurno()
	{
		try 
		{
			Botao turno = (Botao) this.encontreComponentePorNome("turno");
			if(this.getTurno() % 2 == 0)
			{
				turno.setForeground(Color.BLACK);
				turno.setText("JOGADOR PRETO");
			}
			else
			{
				turno.setForeground(Color.WHITE);
				turno.setText("JOGADOR BRANCO");
			}
		} 
		catch (Exception e) 
		{
			System.out.println("EXCEPTION ATUALIZAR TURNO");
		}
	}
	
	// Atualiza o botão da interface com a jogada da IA.
	public void jogadaComputador(int x, int y)
	{
		try
		{
			int num = (x * 15) + y;
			Botao botao = (Botao) this.encontreComponentePorNome("" + num);
			botao.setIcon(Janela.iconePosicaoPecaPreta);
		} 
		catch (Exception e) 
		{
			System.out.println("EXCEPTION JOGADA COMPUTADOR");
		}
	}
	
	public int getTurno() 
	{
		int resultado = -1;
		try 
		{
			resultado = this.controle.getTurno();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("EXCEPTION TURNO");
		}
		return resultado;
	}

	public void vitoriaBranco() 
	{
		Botao turno = (Botao) this.encontreComponentePorNome("turno");
		turno.setForeground(Color.WHITE);
		turno.setText("VITÓRIA BRANCO");
	}

	public void vitoriaPreto() 
	{
		Botao turno = (Botao) this.encontreComponentePorNome("turno");
		turno.setForeground(Color.BLACK);
		turno.setText("VITÓRIA PRETO");
	}

}
