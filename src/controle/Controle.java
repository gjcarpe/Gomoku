package controle;

import modelo.Gomoku;
import modelo.ModoDeJogo;

public class Controle
{
	private Gomoku gomoku;

	public Controle()
	{
		// Alguns headers de métodos que terão de ser implementados
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
		//this.gomoku.encerrar(); // TODO
	}
	
	public void jogada(int x, int y)
	{
		this.gomoku.jogada(x, y);
	}

	public int getTurno() 
	{
		return this.gomoku.getTurno();
	}
}
