package modelo;

import java.util.ArrayList;

public class Computador 
{
	private Gomoku gomoku;
	
	public Computador(Gomoku gomoku)
	{
		this.gomoku = gomoku;
	}
	
	// *** Métodos da heurística *** // TODO EM IMPLEMENTAÇÃO
	
	public void jogadaComputador() 
	{
		ParOrdenado jogadaPC = this.miniMax();
		this.gomoku.jogada(jogadaPC);
	}
	
	// Dá um valor de pontuação para o tabuleiro simulando uma jogada.
	public int avalieJogada(ParOrdenado p)
	{
		int x = p.getX();
		int y = p.getY();
		int resultado = 0;
		
		// TODO Com base na lista de sequências 
		
		return resultado;
	}
	
	// Código semi-copiado do impl MiniMax do moodle
	@SuppressWarnings("unused")
	public ArrayList<ParOrdenado> gerarJogadas()
	{
		ArrayList<ParOrdenado> jogadas = new ArrayList<ParOrdenado>();
		//if (verifiqueSeGanhou())
		if (false)
			return jogadas;
	
		Peca[][] tabuleiro = this.gomoku.getTabuleiro();
		
		int limite = tabuleiro.length; // NOTA: cuidado ao usar tabuleiro.lenght -> = 15
		for (int i = 0; i < limite; i++) {
			for (int j = 0; j < limite; j++) {
				if (tabuleiro[i][j] == Peca.SEM_PECA)
					jogadas.add(new ParOrdenado(i, j));
			}
		}
		return jogadas;
		
	}
	
	// Código semi-copiado do impl MiniMax do moodle	
	public ParOrdenado miniMax() {
		ArrayList<ParOrdenado> jogadas = gerarJogadas();
		int limite = jogadas.size();
		int resultadoMelhor = Integer.MIN_VALUE;
		ParOrdenado jogadaMelhor = new ParOrdenado(8,8);
		int resultado;
		for (int i = 0; i < limite; i++){
			ParOrdenado jogada = jogadas.get(i);
			resultado = this.avalieJogada(jogada);
			if (resultado > resultadoMelhor)
				jogadaMelhor = jogada;
		}
		return jogadaMelhor;
	}
}
