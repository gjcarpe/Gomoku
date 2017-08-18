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
	 * 	o jogador 1 (branco) tem os turnos pares 
	 *  e jogador 2 (preto) tem os turnos �mpares.
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
		
		for (int i = x-1; i <= x+1 ; i++)
			for (int j = y-1; j <= y+1; j++)
				listaDeAdjacentes.add(new ParOrdenado(i, j));		
		
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
		// Verifica HashMap para colocar sequencias novas
		this.turno++;
	}
	
	
	public void jogada(int x, int y)
	{
		// Cor da pe�a determinada pelo valor de turno atual.
		// TODO
		if (this.getTurno() % 2 == 0)
			this.tabuleiro[x][y] = Peca.PECA_BRANCA;
		else 
			this.tabuleiro[x][y] = Peca.PECA_PRETA;
		this.passeTurno();
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