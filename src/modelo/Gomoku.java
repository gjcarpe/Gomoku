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
	 * 	Observação sobre turno:
	 * 	o jogador 1 tem os turnos pares 
	 *  e jogador 2 tem os turnos ímpares.
	 *  Quando passa turno apenas incrementa 1.
	 */
	
	public int getTurno()
	{
		return this.turno;
	}
	
	// Headers - sem ordem específica
	
	
	// Dado um par (x,y) da matriz, retorna em uma lista todos os pontos vizinhos.
	public List<ParOrdenado> encontreAdjacentes(int x, int y)
	{
		// Note que List é uma interface.
		ArrayList<ParOrdenado> listaDeAdjacentes = new ArrayList<ParOrdenado>();
		
		// TODO
		for (int i = x-1; i <= x+1 ; i++){
			for (int j = y-1; j <= y+1; j++){
				listaDeAdjacentes.add(new ParOrdenado(i, j));
			}
		}
		
		return listaDeAdjacentes;
	}
	
	public boolean verifiqueSeGanhou()
	{
		boolean resultado = false;
		
		// TODO
		//if (HashMap tem sequência de tamanho 5)
		// resultado = true;
		
		return resultado;
	}
	
	public void passeTurno()
	{
		// TODO
		// Verifica HashMap para colocar sequencias novas
		this.turno++;
	}
	
	// Headers - da heurística
	
	public void jogada(int x, int y)
	{
		// Cor da peça determinada pelo valor de turno atual.
		// TODO
		if (this.getTurno() % 2 == 0)
			this.tabuleiro[x][y] = Peca.PECA_BRANCA;
		else {
			this.tabuleiro[x][y] = Peca.PECA_PRETA;
			this.jogadaComputador(); // Computador executa uma jogada
		}
		this.passeTurno();
	}
	
	private void jogadaComputador() {
		int [] jogadaPC = this.miniMax();
		jogada(jogadaPC[0], jogadaPC[1]);
	}

	// Dá um valor de pontuação para o tabuleiro simulando uma jogada.
	public int avalieJogada(int x, int y)
	{
		int resultado = 0;
		
		// TODO
		
		return resultado;
	}
	
	@SuppressWarnings("unused")
	public ArrayList<int[]> gerarJogadas()
	{
		// TODO
		ArrayList<int[]> jogadas = new ArrayList<int []>();
		//if (verifiqueSeGanhou())
		if (false)
			return jogadas;
	
		int limite = tabuleiro.length;
		for (int i = 0; i < limite; i++) {
			for (int j = 0; j < limite; j++) {
				if (tabuleiro[i][j] == Peca.SEM_PECA)
					jogadas.add( new int[] {i, j});
			}
		}
		return jogadas;
		
	}
	
	public int[] miniMax() {
		ArrayList<int[]> jogadas = gerarJogadas();
		
	}
}





