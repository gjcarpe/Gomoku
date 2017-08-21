package modelo;

import java.util.ArrayList;
import java.util.HashMap;

import controle.Controle;

public class Gomoku 
{
	private Controle controle;
	private int turno;
	private ModoDeJogo modoDeJogo;
	private Peca[][] tabuleiro;
	private HashMap<String, Sequencia> sequencias;
	private int numSequenciasUm;
	private int numSequenciasDois;
	private int numSequenciasTres;
	private int numSequenciasQuatro;
	private int numSequenciasCinco;
	
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
	
	public Gomoku(Controle controle, ModoDeJogo modo)
	{
		this.controle = controle;
		this.turno = 0;
		this.modoDeJogo = modo;
		this.sequencias = new HashMap<>();
		this.numSequenciasUm = 0;
		this.numSequenciasDois = 0;
		this.numSequenciasTres = 0;
		this.numSequenciasQuatro = 0;
		this.numSequenciasCinco = 0;
		this.inicializarTabuleiro();
	}
	
	public ModoDeJogo getModoDeJogo()
	{
		return this.modoDeJogo;
	}
	
	public Peca[][] getTabuleiro()
	{
		return this.tabuleiro;
	}
	
	public int getTurno()
	{
		/*
		 * 	Observação sobre turno:
		 * 	o jogador 1 tem os turnos pares, começando em 0.
		 *  e jogador 2 tem os turnos ímpares.
		 *  Quando passa turno apenas incrementa 1.
		 */
		return this.turno;
	}	
	
	// Cor da peça determinada pelo valor de turno atual.
	public void jogada(int x, int y)
	{
		if (this.getTurno() % 2 == 0)
		{
			this.tabuleiro[x][y] = Peca.PECA_BRANCA;
			this.crieSequencia(x, y, Peca.PECA_BRANCA);
			
			if(this.verifiqueSeGanhou() == true)
				this.controle.fimDeJogo(0);
			else
				this.passeTurno();
		}
		else
		{
			this.tabuleiro[x][y] = Peca.PECA_PRETA;
			this.crieSequencia(x, y, Peca.PECA_PRETA);
			
			if(this.verifiqueSeGanhou() == true)	
				this.controle.fimDeJogo(1);
			else
				this.passeTurno();
		}
	}
	
	// Dado um par (x,y) da matriz, retorna em uma lista todos os pontos de MESMA cor vizinhos.
	public ArrayList<ParOrdenado> encontreAdjacentes(int x, int y, Peca corPeca)
	{
		ArrayList<ParOrdenado> listaDeAdjacentes = new ArrayList<ParOrdenado>();
		
		for (int i = x-1; i <= x+1 ; i++)
		{
			for (int j = y-1; j <= y+1; j++)
			{
				if(i >= 0 && j >= 0 && i < 15 && j < 15) // Se está no intervalo da matriz
				{
					if(x != i && y != j) // e não for o próprio ponto
					{
						if(this.tabuleiro[i][j] == corPeca)
						{
							listaDeAdjacentes.add(new ParOrdenado(i, j));
						}
					}
					else
					{
						if(x == i && y != j)
						{
							if(this.tabuleiro[i][j] == corPeca)
							{
								listaDeAdjacentes.add(new ParOrdenado(i, j));
							}
						}
						if(x != i && y == j)
						{
							if(this.tabuleiro[i][j] == corPeca)
							{
								listaDeAdjacentes.add(new ParOrdenado(i, j));
							}
						}
					}
				}	
			}
		}
		return listaDeAdjacentes;
	}
	
	public Orientacao determineOrientacao(ParOrdenado origem, ParOrdenado destino)
	{
		Orientacao resultado = Orientacao.SEM_ORIENTACAO;
		
		if(origem.getX() > destino.getX())
		{
			if(origem.getY() > destino.getY())
				resultado = Orientacao.NOROESTE;
			if(origem.getY() == destino.getY())
				resultado = Orientacao.OESTE;
			if(origem.getY() < destino.getY())
				resultado = Orientacao.SUDOESTE;
		}
		else
		{
			if(origem.getX() < destino.getX())
			{
				if(origem.getY() > destino.getY())
					resultado = Orientacao.NORDESTE;
				if(origem.getY() == destino.getY())
					resultado = Orientacao.LESTE;
				if(origem.getY() < destino.getY())
					resultado = Orientacao.SUDESTE;
			}
			else
			{
				if(origem.getX() == destino.getX())
				{
					if(origem.getY() > destino.getY())
						resultado = Orientacao.NORTE;
					if(origem.getY() < destino.getY())
						resultado = Orientacao.SUL;
				}
			}
		}
		return resultado;
	}
	
	// Com base em uma jogada vai criar e atualizar as sequências
	public void crieSequencia(int x, int y, Peca corPeca) // TODO Testar
	{
		ArrayList<ParOrdenado> adjacentes = this.encontreAdjacentes(x, y, corPeca);
		ParOrdenado pontoInicial = new ParOrdenado(x, y);
		ParOrdenado pontoFinal = null;
		Sequencia nova = null;
		
		if(adjacentes.size() == 0) // Se não tem pontos de mesma cor adjacentes
		{	
			// Cria uma sequência de 1 ponto
			pontoFinal = pontoInicial;
			nova = new Sequencia(pontoInicial, pontoFinal, corPeca, Orientacao.SEM_ORIENTACAO, 1);
			this.sequencias.put("UM#" + this.numSequenciasUm, nova); 
			this.numSequenciasUm++;
		}
		else
		{
			ParOrdenado atual = null;
			int xAtual = 0;
			int yAtual = 0;
			Orientacao orientacaoAtual = Orientacao.SEM_ORIENTACAO;
			int tam = 1; // tamanho da sequência
			for(int i = 0; i < adjacentes.size(); i++)
			{
				atual = adjacentes.get(i);
				orientacaoAtual = this.determineOrientacao(pontoInicial, atual);
				xAtual = atual.getX();
				yAtual = atual.getY();
				while(this.tabuleiro[xAtual][yAtual] == corPeca)
				{
					tam++;
					if(orientacaoAtual == Orientacao.NORTE)
					{
						yAtual--;
						if(yAtual < 0)
						{
							yAtual++;
							break;
						}
					}
					if(orientacaoAtual == Orientacao.SUL)
					{
						yAtual++;
						if(yAtual > 14)
						{
							yAtual--;
							break;
						}
					}
					if(orientacaoAtual == Orientacao.LESTE)
					{
						xAtual++;
						if(xAtual > 14)
						{
							xAtual--;
							break;
						}
					}
					if(orientacaoAtual == Orientacao.OESTE)
					{
						xAtual++;
						if(xAtual < 0)
						{
							xAtual--;
							break;
						}
					}
					if(orientacaoAtual == Orientacao.NORDESTE)
					{
						xAtual++;
						yAtual--;
						if(xAtual > 14 || yAtual < 0)
						{
							xAtual--;
							yAtual++;
							break;
						}
					}
					if(orientacaoAtual == Orientacao.NOROESTE)
					{
						xAtual--;
						yAtual--;
						if(xAtual < 0 || yAtual < 0)
						{
							xAtual++;
							yAtual++;
							break;
						}
					}
					if(orientacaoAtual == Orientacao.SUDOESTE)
					{
						xAtual--;
						yAtual++;
						if(xAtual < 0 || yAtual > 14)
						{
							xAtual++;
							yAtual--;
							break;
						}
					}
					if(orientacaoAtual == Orientacao.SUDESTE)
					{
						xAtual++;
						yAtual++;
						if(xAtual > 14 || yAtual > 14)
						{
							xAtual--;
							yAtual--;
							break;
						}
					}
				}
				
				pontoFinal = new ParOrdenado(xAtual, yAtual);
				nova = new Sequencia(pontoInicial, pontoFinal, corPeca, orientacaoAtual, tam);
				String id = "";
				if(tam == 2)
				{
					id = "DOIS#" + this.numSequenciasDois;
					System.out.println(id);
					this.numSequenciasDois++;
				}
				if(tam == 3)
				{
					id = "TRES#" + this.numSequenciasTres;
					System.out.println(id);
					this.numSequenciasTres++;
				}
				if(tam == 4)
				{
					id = "QUATRO#" + this.numSequenciasQuatro;
					System.out.println(id);
					this.numSequenciasQuatro++;
				}
				if(tam == 5)
				{
					id = "CINCO#" + this.numSequenciasCinco;
					System.out.println(id);
					this.numSequenciasCinco++;
				}
				
				this.sequencias.put(id, nova);
			}
		}
	}
	
	public boolean verifiqueSeGanhou()
	{
		boolean resultado = false;
		
		if(this.numSequenciasCinco > 0)
			resultado = true;

		return resultado;
	}
	
	public void passeTurno()
	{
		this.turno++;
	}

	// *** Métodos da heurística *** // TODO EM IMPLEMENTAÇÃO
	
	public void jogadaComputador() 
	{
		int [] jogadaPC = this.miniMax();
		jogada(jogadaPC[0], jogadaPC[1]);
	}
	
	// Dá um valor de pontuação para o tabuleiro simulando uma jogada.
	public int avalieJogada(int x, int y)
	{
		int resultado = 0;
		
		// TODO Com base na lista de sequências 
		
		return resultado;
	}
	
	// Código semi-copiado do impl MiniMax do moodle
	@SuppressWarnings("unused")
	public ArrayList<int[]> gerarJogadas()
	{
		ArrayList<int[]> jogadas = new ArrayList<int []>();
		//if (verifiqueSeGanhou())
		if (false)
			return jogadas;
	
		int limite = tabuleiro.length; // NOTA: cuidado ao usar tabuleiro.lenght -> = 15
		for (int i = 0; i < limite; i++) {
			for (int j = 0; j < limite; j++) {
				if (tabuleiro[i][j] == Peca.SEM_PECA)
					jogadas.add( new int[] {i, j});
			}
		}
		return jogadas;
		
	}
	
	// Código semi-copiado do impl MiniMax do moodle	
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





