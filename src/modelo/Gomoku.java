package modelo;

import java.util.ArrayList;

import controle.Controle;

public class Gomoku 
{
	private Controle controle;
	private int turno;
	private boolean vitoria;
	private ModoDeJogo modoDeJogo;
	private Peca[][] tabuleiro;
	private Computador computador;
	
	private ArrayList<Sequencia> sequenciasUm;
	private ArrayList<Sequencia> sequenciasDois;
	private ArrayList<Sequencia> sequenciasTres;
	private ArrayList<Sequencia> sequenciasQuatro;
	private ArrayList<Sequencia> sequenciasTemporarias;
	
	private int valorUma;
	private int valorDupla;
	private int valorTripla;
	private int valorQuadra;
	private int valorQuintupla;
	
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
		this.vitoria = false;
		this.modoDeJogo = modo;
		if(modo.equals(ModoDeJogo.UM_JOGADOR))
			this.computador = new Computador(this);
	
		this.sequenciasUm = new ArrayList<Sequencia>();
		this.sequenciasDois = new ArrayList<Sequencia>();
		this.sequenciasTres = new ArrayList<Sequencia>();
		this.sequenciasQuatro = new ArrayList<Sequencia>();
		this.sequenciasTemporarias = new ArrayList<Sequencia>();
		
		// Valores arbitrários - TODO Verificar
		this.valorUma = 1;
		this.valorDupla = 10;
		this.valorTripla = 100;
		this.valorQuadra = 1000;
		this.valorQuintupla = Integer.MAX_VALUE;
		
		this.inicializarTabuleiro();
	}
	
	public ArrayList<Sequencia> getSequenciasUm()
	{
		return sequenciasUm;
	}

	public ArrayList<Sequencia> getSequenciasDois()
	{
		return sequenciasDois;
	}
	public ArrayList<Sequencia> getSequenciasTres()
	{
		return sequenciasTres;
	}
	
	public ArrayList<Sequencia> getSequenciasQuatro()
	{
		return sequenciasQuatro;
	}
	
	public ArrayList<Sequencia> getSequenciasTemporarias()
	{
		return this.sequenciasTemporarias;
	}
	
	public int getValorUma()
	{
		return this.valorUma;
	}
	
	public int getValorDupla()
	{
		return this.valorDupla;
	}
	
	public int getValorTripla()
	{
		return this.valorTripla;
	}
	
	public int getValorQuatro()
	{
		return this.valorQuadra;
	}
	
	public int getValorQuintupla()
	{
		return this.valorQuintupla;
	}
	
	public boolean vitoria()
	{
		return this.vitoria;
	}
	
	public Computador getComputador()
	{
		return this.computador;
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
		return this.turno;
	}	
	
	public void passeTurno()
	{
		this.turno++;
	}
	
	/*
	 * 	Observação sobre turno:
	 * 	o jogador 1 tem os turnos pares, começando em 0.
	 *  e jogador 2 tem os turnos ímpares.
	 *  Quando passa turno apenas incrementa 1.
	 */
	
	// Cor da peça determinada pelo valor de turno atual.
	public void jogada(ParOrdenado p)
	{
		int x = p.getX();
		int y = p.getY();
		ArrayList<Sequencia> jogadas = null;
		
		if(this.modoDeJogo.equals(ModoDeJogo.DOIS_JOGADORES))
		{
			if (this.getTurno() % 2 == 0)
			{
				this.tabuleiro[x][y] = Peca.PECA_BRANCA;
				jogadas = this.crieSequencias(x, y, Peca.PECA_BRANCA);
				for (int i = 0; i < jogadas.size(); i++) 	
					this.adicioneSequencia(jogadas.get(i));
				this.passeTurno();
			}
			else
			{
				this.tabuleiro[x][y] = Peca.PECA_PRETA;
				jogadas = this.crieSequencias(x, y, Peca.PECA_PRETA);
				for (int i = 0; i < jogadas.size(); i++) 	
					this.adicioneSequencia(jogadas.get(i));
				this.passeTurno();
			}
		}
		else // Modo de jogo individual
		{
			this.tabuleiro[x][y] = Peca.PECA_BRANCA;
			jogadas = this.crieSequencias(x, y, Peca.PECA_BRANCA);
			for (int i = 0; i < jogadas.size(); i++) 	
				this.adicioneSequencia(jogadas.get(i));
			this.passeTurno();
			
			if(this.vitoria == false)
			{
				ParOrdenado ponto = this.computador.jogadaComputador(); // Computador calcula sua jogada
				this.controle.jogadaComputador(ponto.getX(), ponto.getY()); // Atualiza interface
				this.tabuleiro[ponto.getX()][ponto.getY()] = Peca.PECA_PRETA;
				jogadas = this.crieSequencias(ponto.getX(), ponto.getY(), Peca.PECA_PRETA);
				for (int i = 0; i < jogadas.size(); i++) 	
					this.adicioneSequencia(jogadas.get(i));
				this.passeTurno();
			}
		}
	}
	
	public Orientacao determineOrientacao(ParOrdenado origem, ParOrdenado destino)
	{
		Orientacao resultado = Orientacao.SEM_ORIENTACAO;
		
		if(origem.getX() > destino.getX())
		{
			if(origem.getY() > destino.getY())
				resultado = Orientacao.NOROESTE;
			if(origem.getY() == destino.getY())
				resultado = Orientacao.NORTE;
			if(origem.getY() < destino.getY())
				resultado = Orientacao.NORDESTE;
		}
		else
		{
			if(origem.getX() < destino.getX())
			{
				if(origem.getY() > destino.getY())
					resultado = Orientacao.SUDOESTE;
				if(origem.getY() == destino.getY())
					resultado = Orientacao.SUL;
				if(origem.getY() < destino.getY())
					resultado = Orientacao.SUDESTE;
			}
			else
			{
				if(origem.getX() == destino.getX())
				{
					if(origem.getY() > destino.getY())
						resultado = Orientacao.OESTE;
					if(origem.getY() < destino.getY())
						resultado = Orientacao.LESTE;
				}
			}
		}
		return resultado;
	}
	
	public Orientacao orientacaoReversa(Orientacao or)
	{
		Orientacao resultado = Orientacao.SEM_ORIENTACAO;
		
		switch (or) 
		{
			case NORTE: resultado = Orientacao.SUL;
				break;
			case SUL: resultado = Orientacao.NORTE;
				break;
			case LESTE: resultado = Orientacao.OESTE;
				break;
			case OESTE: resultado = Orientacao.LESTE;
				break;
			case NORDESTE: resultado = Orientacao.SUDOESTE;
				break;
			case SUDOESTE: resultado = Orientacao.NORDESTE;
				break;
			case NOROESTE: resultado = Orientacao.SUDESTE;
				break;
			case SUDESTE: resultado = Orientacao.NOROESTE;
				break;
			default:
				break;
		}
		
		return resultado;
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
							listaDeAdjacentes.add(new ParOrdenado(i, j));
					}
					else
					{
						if(x == i && y != j) // Condição em que apenas x é igual
						{
							if(this.tabuleiro[i][j] == corPeca)
								listaDeAdjacentes.add(new ParOrdenado(i, j));	
						}
						if(x != i && y == j) // Condição em que apenas y é igual
						{
							if(this.tabuleiro[i][j] == corPeca)
								listaDeAdjacentes.add(new ParOrdenado(i, j));
						}
					}
				}	
			}
		}
		return listaDeAdjacentes;
	}
	
	// Este método remove os pontos adjacentes reversos repetidos, se existirem.
	public ArrayList<ParOrdenado> limparAdj(ArrayList<ParOrdenado> adjacentes, ParOrdenado central)
	{
		ArrayList<ParOrdenado> resultado = new ArrayList<ParOrdenado>();
		ParOrdenado atual = null;
		ParOrdenado seguinte = null;
		Orientacao orAtual = Orientacao.SEM_ORIENTACAO;
		Orientacao orSeguinte = Orientacao.SEM_ORIENTACAO;
		Orientacao reversa = Orientacao.SEM_ORIENTACAO;
		
		while(adjacentes.size() > 0)
		{
			atual = adjacentes.get(0);
			orAtual = this.determineOrientacao(central, atual);
			reversa = this.orientacaoReversa(orAtual);
			for(int i = 1; i < adjacentes.size(); i++)
			{
				seguinte = adjacentes.get(i);
				orSeguinte = this.determineOrientacao(central, seguinte);
				if(orSeguinte == reversa)
				{
					adjacentes.remove(seguinte);
					break;
				}
			}
			resultado.add(atual);
			adjacentes.remove(atual);
		}
		
		return resultado;
	}
	
	public void adicioneSequencia(Sequencia sequencia)
	{
		
		if(sequencia.getTamanho() == 1)
		{
			this.sequenciasUm.add(sequencia);
			System.out.println("Adicionada nova Sequência-1");
		}
		if(sequencia.getTamanho() == 2)
		{
			this.sequenciasDois.add(sequencia);
			System.out.println("Adicionada nova Sequência-2");
		}
		if(sequencia.getTamanho() == 3)
		{
			this.sequenciasTres.add(sequencia);
			System.out.println("Adicionada nova Sequência-3");
		}
		if(sequencia.getTamanho() == 4)
		{
			this.sequenciasQuatro.add(sequencia);
			System.out.println("Adicionada nova Sequência-4");
		}
		if(sequencia.getTamanho() == 5) // Caso detectada sequência 5 encerra o jogo
		{
			System.out.println("SEQUÊNCIA-5 CRIADA - FIM DE JOGO");
			this.vitoria = true;
			if(this.turno % 2 == 0)
				this.controle.fimDeJogo(0); // Zero para brancas
			else
				this.controle.fimDeJogo(1); // Um para pretas
		}
	}
	
	// Com base em uma jogada vai criar e atualizar as sequências
	public ArrayList<Sequencia> crieSequencias(int x, int y, Peca corPeca)
	{
		ArrayList<Sequencia> resultado = new ArrayList<Sequencia>();
		ArrayList<ParOrdenado> adjacentes = this.encontreAdjacentes(x, y, corPeca);
		ParOrdenado pontoInicial = new ParOrdenado(x, y);
		ParOrdenado pontoFinal = null;
		Sequencia nova = null;
		
		if(adjacentes.size() == 0) // Se não tem pontos de mesma cor adjacentes
		{	
			// Cria uma sequência de 1 ponto
			pontoFinal = pontoInicial;
			nova = new Sequencia(pontoInicial, pontoFinal, corPeca, Orientacao.SEM_ORIENTACAO, 1);
			resultado.add(nova);
		}
		else // Senão verifica os adjacentes
		{
			adjacentes = this.limparAdj(adjacentes, pontoInicial); // Remove as redundâncias
			ParOrdenado atual = null;
			int tam = 0; // tamanho da sequência
			int xAtual = 0;
			int yAtual = 0;
			Orientacao orientacaoAtual = Orientacao.SEM_ORIENTACAO;
			
			for(int i = 0; i < adjacentes.size(); i++)
			{
				tam = 0;
				atual = adjacentes.get(i);
				orientacaoAtual = this.determineOrientacao(pontoInicial, atual);
				xAtual = atual.getX();
				yAtual = atual.getY();
				
				for(int j = 0; j < 2; j++)
				{
					if(j == 1) // Verifica a outra potencial metade
					{
						orientacaoAtual = this.orientacaoReversa(orientacaoAtual); // Reverte a orientação
						xAtual = pontoInicial.getX(); // Restaura X
						yAtual = pontoInicial.getY(); // Restaura Y
					}
					
					while(this.tabuleiro[xAtual][yAtual] == corPeca)
					{
						tam++;
						if(orientacaoAtual == Orientacao.NORTE)
						{
							xAtual--;
							if(xAtual < 0)
							{
								xAtual++;
								break;
							}
						}
						if(orientacaoAtual == Orientacao.SUL)
						{
							xAtual++;
							if(xAtual > 14)
							{
								xAtual--;
								break;
							}
						}
						if(orientacaoAtual == Orientacao.LESTE)
						{
							yAtual++;
							if(yAtual > 14)
							{
								yAtual--;
								break;
							}
						}
						if(orientacaoAtual == Orientacao.OESTE)
						{
							yAtual--;
							if(yAtual < 0)
							{
								yAtual++;
								break;
							}
						}
						if(orientacaoAtual == Orientacao.NORDESTE)
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
							xAtual++;
							yAtual--;
							if(xAtual > 14 || yAtual < 0)
							{
								xAtual--;
								yAtual++;
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
				}
				
				pontoFinal = new ParOrdenado(xAtual, yAtual);
				nova = new Sequencia(pontoInicial, pontoFinal, corPeca, orientacaoAtual, tam);
				resultado.add(nova);
			}
		}
		
		return resultado;
	}

	// Funções usadas pela IA para simulação de jogadas

	public void crieSequenciasTemporarias(int x, int y, Peca corPeca) 
	{
		// TODO
	}

	public void removaSequenciasTemporarias() 
	{
		// TODO Auto-generated method stub
	}
	
}