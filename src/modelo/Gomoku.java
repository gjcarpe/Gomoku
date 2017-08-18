package modelo;

import java.util.ArrayList;
import java.util.List;

public class Gomoku 
{
	private int turno;
	private boolean umJogador;
	private Peca[][] tabuleiro;
	
	private void inicializarTabuleiro()
	{
		this.tabuleiro = new Peca[15][15];
		for (int i = 0; i < tabuleiro.length; i++) 
		{
			for (int j = 0; j < tabuleiro.length; j++) 
			{
				this.tabuleiro[i][j] = Peca.SEM_PECA;
			}
		}
	}
	
	public Gomoku(ModoDeJogo modo)
	{
		this.turno = 0;
		if(modo == ModoDeJogo.UM_JOGADOR)
			this.umJogador = true;
		else
			this.umJogador = false;
		this.inicializarTabuleiro();
	}
	
	public boolean getUmJogador()
	{
		return this.umJogador;
	}
	
	public Peca[][] getTabuleiro()
	{
		return this.tabuleiro;
	}
	
	/*
	 * 	Observa��o sobre turno:
	 * 	o jogador 1 tem os turnos pares 
	 *  e jogador 2 tem os turnos �mpares.
	 *  Quando passa turno apenas incrementa 1.
	 */
	
	public int getTurno()
	{
		return this.turno;
	}
	
	// Headers - sem ordem espec�fica
	
	
	// Dado um par (x,y) da matriz, retorna em uma lista todos os pontos vizinhos.
	public List<ParOrdenado> encontreAdjacentes(int x, int y)
	{
		// Note que List � uma interface.
		ArrayList<ParOrdenado> listaDeAdjacentes = new ArrayList<ParOrdenado>();
		
		// TODO
		
		return listaDeAdjacentes;
	}
	
	public boolean verifiqueSeGanhou()
	{
		boolean resultado = false;
		
		// TODO
		
		return resultado;
	}
	
	public void passeTurno()
	{
		// TODO
	}
	
	// Headers - da heur�stica
	
	public void jogada(int x, int y)
	{
		// Cor da pe�a determinada pelo valor de turno atual.
		// TODO
	}
	
	// D� um valor de pontua��o para o tabuleiro simulando uma jogada.
	public int avalieJogada(int x, int y)
	{
		int resultado = 0;
		
		// TODO
		
		return resultado;
	}
	
	public void miniMax()
	{
		// TODO
	}
}





