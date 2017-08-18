package controle;

import modelo.Gomoku;
import modelo.ModoDeJogo;

public class Controle
{
	private static Gomoku gomoku = new Gomoku(ModoDeJogo.UM_JOGADOR);

	public Controle()
	{
		// Alguns headers de métodos que terão de ser implementados
	}
	
	public static void novoJogoUmJogador()
	{
		// TODO
		//this.gomoku = new Gomoku(ModoDeJogo.UM_JOGADOR);
		gomoku.encontreAdjacentes(10, 10);
	}
	
	public static void novoJogoDoisJogadores()
	{
		// TODO
	}
	
	public void encerrar()
	{
		// TODO
	}
	
	public void jogada(int x, int y)
	{
		// TODO
		gomoku.jogada(x, y);
	}
}
