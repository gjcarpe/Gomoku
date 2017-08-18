package controle;

import modelo.Gomoku;
import modelo.ModoDeJogo;

public class Controle
{
	private Gomoku gomoku;

	public Controle()
	{
		// Alguns headers de m�todos que ter�o de ser implementados
	}
	
	public void novoJogoUmJogador()
	{
		this.gomoku = new Gomoku(ModoDeJogo.UM_JOGADOR);
	}
	
	public void novoJogoDoisJogadores()
	{
		this.gomoku = new Gomoku(ModoDeJogo.DOIS_JOGADORES);
	}
	
	public void encerrar()
	{
		this.gomoku = null;
	}
	
	public void jogada(int x, int y) throws Exception
	{
		this.gomoku.jogada(x, y);
	}

	public int getTurno() throws Exception
	{
		return this.gomoku.getTurno();
	}
}
