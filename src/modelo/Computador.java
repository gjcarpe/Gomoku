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
	
	public ParOrdenado jogadaComputador() 
	{
		ParOrdenado jogadaPC = this.miniMax();
		return jogadaPC;
	}
	
	private ParOrdenado miniMax() // Por enquanto faz apenas 1 n�vel de profundidade
	{
		ArrayList<ParOrdenado> jogadasPossiveis = this.pegarJogadasPossiveis();
		int resultado = 0;
		int melhorResultado = Integer.MIN_VALUE;
		ParOrdenado melhorJogada = new ParOrdenado(7,7); // come�ando no ponto central
		for (int i = 0; i < jogadasPossiveis.size(); i++)
		{
			ParOrdenado jogada = jogadasPossiveis.get(i);
			this.simularJogada(jogada.getX(), jogada.getY());
			resultado = this.pontuacaoDoTabuleiro();
			if (resultado > melhorResultado)
				melhorJogada = jogada;
		}
		return melhorJogada;
	}
	
	private ParOrdenado minimax(int profundidade) // TODO minimax profundidade
	{
		ParOrdenado resultado = null;
		ArrayList<ParOrdenado> jogadasPossiveis = this.pegarJogadasPossiveis();
		// Neste array cont�m todos os ramos de jogadas, agora basta simplificar.
		
		// Podar aqui?
		
		// max (profundidade = par)
		// calcule a melhor jogada deste turno
		
		// min (profundidade = �mpar)
		// calcule a melhor jogada do turno do oponente
		
		// decida o melhor caminho da �rvore de estados -> avaliarVantagem()
		
		// Repita at� profundidade = 0
		
		
		
		return resultado;
	}

	// Retorna uma lista com todos os espa�os sem pe�a do tabuleiro
	private ArrayList<ParOrdenado> pegarJogadasPossiveis()
	{
		ArrayList<ParOrdenado> jogadas = new ArrayList<ParOrdenado>();
		Peca[][] tabuleiro = this.gomoku.getTabuleiro();
		
		int limite = tabuleiro.length;
		
		for (int i = 0; i < limite; i++) 
			for (int j = 0; j < limite; j++) 
				if (tabuleiro[i][j] == Peca.SEM_PECA)
					jogadas.add(new ParOrdenado(i, j));
			
		return jogadas;
	}
	
	// D� um valor de pontua��o para o tabuleiro - Na forma de "Vantagem"
	// Vantagem = suaPontua��o - pontua��oOponente
	private int pontuacaoDoTabuleiro()
	{
		int resultado = 0;
		
		ArrayList<Sequencia> sequencias4 = this.gomoku.getSequenciasQuatro();
		for (int i = 0; i < sequencias4.size(); i++)
		{
			if(sequencias4.get(i).getCorPeca().equals(Peca.PECA_PRETA))
				resultado += this.gomoku.getValorQuatro();
			else
			{
				if(sequencias4.get(i).getCorPeca().equals(Peca.PECA_BRANCA))
					resultado -= this.gomoku.getValorQuatro();
			}	
		}
		
		ArrayList<Sequencia> sequencias3 = this.gomoku.getSequenciasTres();
		for (int i = 0; i < sequencias3.size(); i++)
		{
			if(sequencias3.get(i).getCorPeca().equals(Peca.PECA_PRETA))
				resultado += this.gomoku.getValorTripla();
			 else
			 {
				 if(sequencias3.get(i).getCorPeca().equals(Peca.PECA_BRANCA))
					 resultado -= this.gomoku.getValorTripla();
			 }	
		}
		
		ArrayList<Sequencia> sequencias2 = this.gomoku.getSequenciasDois();
		for (int i = 0; i < sequencias2.size(); i++)
		{
			if(sequencias2.get(i).getCorPeca().equals(Peca.PECA_PRETA))
				resultado += this.gomoku.getValorDupla();
			else
			{
				if(sequencias2.get(i).getCorPeca().equals(Peca.PECA_BRANCA))
					resultado -= this.gomoku.getValorDupla();
			}	
		}
		
		ArrayList<Sequencia> sequencias1 = this.gomoku.getSequenciasUm();
		for (int i = 0; i < sequencias1.size(); i++)
		{
			if(sequencias1.get(i).getCorPeca().equals(Peca.PECA_PRETA))
				resultado += 1;
			else
			{
				if(sequencias1.get(i).getCorPeca().equals(Peca.PECA_BRANCA))
					resultado -= 1;
			}
		}
		
		return resultado;
	}
	
	
	// Simula a jogada (Realiza ela e depois reverte)
	private void simularJogada(int x, int y) 
	{
		Peca[][] tabuleiro = this.gomoku.getTabuleiro();
		tabuleiro[x][y] = Peca.PECA_PRETA;
		this.gomoku.crieSequencia(x, y, Peca.PECA_PRETA);
		// TODO anular a jogada
	}
}
