package modelo;

import java.util.ArrayList;

public class Computador 
{
	private Gomoku gomoku;
	
	public Computador(Gomoku gomoku)
	{
		this.gomoku = gomoku;
	}
	
	// *** M�todos da heur�stica *** // TODO EM IMPLEMENTA��O
	
	public void jogadaComputador() 
	{
		int [] jogadaPC = this.miniMax();
		this.gomoku.jogada(jogadaPC[0], jogadaPC[1]);
	}
	
	// D� um valor de pontua��o para o tabuleiro simulando uma jogada.
	public int avalieJogada(int x, int y)
	{
		int resultado = 0;
		
		// TODO Com base na lista de sequ�ncias 
		
		return resultado;
	}
	
	// C�digo semi-copiado do impl MiniMax do moodle
	@SuppressWarnings("unused")
	public ArrayList<int[]> gerarJogadas()
	{
		ArrayList<int[]> jogadas = new ArrayList<int []>();
		//if (verifiqueSeGanhou())
		if (false)
			return jogadas;
	
		Peca[][] tabuleiro = this.gomoku.getTabuleiro();
		
		int limite = tabuleiro.length; // NOTA: cuidado ao usar tabuleiro.lenght -> = 15
		for (int i = 0; i < limite; i++) {
			for (int j = 0; j < limite; j++) {
				if (tabuleiro[i][j] == Peca.SEM_PECA)
					jogadas.add(new int[] {i, j});
			}
		}
		return jogadas;
		
	}
	
	// C�digo semi-copiado do impl MiniMax do moodle	
	public int[] miniMax() {
		ArrayList<int[]> jogadas = gerarJogadas();
		int limite = jogadas.size();
		int resultadoMelhor = Integer.MIN_VALUE;
		int[] jogadaMelhor = new int[] {8, 8};
		int resultado;
		for (int i = 0; i < limite; i++){
			int[] jogada = jogadas.get(i);
			resultado = this.avalieJogada(jogada[0], jogada[1]);
			if (resultado > resultadoMelhor)
				jogadaMelhor = jogada;
		}
		return jogadaMelhor;
	}
}
