package controle;

import modelo.Gomoku;
import modelo.ModoDeJogo;
import modelo.ParOrdenado;
import view.Janela;

public class Controle
{
	private Gomoku gomoku;
	private Janela janela;

	public Controle()
	{
		
	}
	
	public void iniciarJanela()
	{
		System.out.println("Iniciar...");
		this.janela = new Janela(this);
	}
	
	public Janela getJanela() // Usado apenas para testes
	{
		return this.janela;
	}
	
	public void novoJogoUmJogador()
	{
		this.gomoku = new Gomoku(this, ModoDeJogo.UM_JOGADOR);
	}
	
	public void novoJogoDoisJogadores()
	{
		this.gomoku = new Gomoku(this, ModoDeJogo.DOIS_JOGADORES);
	}
	
	public void encerrar()
	{
		this.gomoku = null;
	}
	
	public void jogada(int x, int y) throws Exception
	{
		this.gomoku.jogada(new ParOrdenado(x, y));
	}

	public int getTurno() throws Exception
	{
		return this.gomoku.getTurno();
	}
	
	public void fimDeJogo(int num)
	{
		// 0 = branco ganhou 
		// 1 = preto ganhou
		if(num == 0)
		{
			this.janela.vitoriaBranco();
			System.err.println("VITÓRIA BRANCO");
		}
		else
		{
			this.janela.vitoriaPreto();
			System.err.println("VITÓRIA PRETO");
		}
		this.encerrar();
		
	}
}
