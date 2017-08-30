package modelo;

import java.util.ArrayList;

import controle.Controle;

public class Gomoku 
{
	private Controle controle;
	private int turno;
	private ModoDeJogo modoDeJogo;
	private Peca[][] tabuleiro;
	private Computador computador;
	
	private ArrayList<Sequencia> sequenciasUm;
	private ArrayList<Sequencia> sequenciasDois;
	private ArrayList<Sequencia> sequenciasTres;
	private ArrayList<Sequencia> sequenciasQuatro;
	
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
		this.modoDeJogo = modo;
		if(modo.equals(ModoDeJogo.UM_JOGADOR))
			this.computador = new Computador(this);
	
		this.sequenciasUm = new ArrayList<Sequencia>();
		this.sequenciasDois = new ArrayList<Sequencia>();
		this.sequenciasTres = new ArrayList<Sequencia>();
		this.sequenciasQuatro = new ArrayList<Sequencia>();
		
		// Valores arbitrários - TODO Verificar
		// Valor 1 peça = 1
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
		/*
		 * 	Observação sobre turno:
		 * 	o jogador 1 tem os turnos pares, começando em 0.
		 *  e jogador 2 tem os turnos ímpares.
		 *  Quando passa turno apenas incrementa 1.
		 */
		return this.turno;
	}	
	
	// Cor da peça determinada pelo valor de turno atual.
	public void jogada(ParOrdenado p)
	{
		int x = p.getX();
		int y = p.getY();
		
		if(this.modoDeJogo.equals(ModoDeJogo.DOIS_JOGADORES))
		{
			if (this.getTurno() % 2 == 0)
			{
				this.tabuleiro[x][y] = Peca.PECA_BRANCA;
				this.crieSequencia(x, y, Peca.PECA_BRANCA);
				this.passeTurno();
			}
			else
			{
				this.tabuleiro[x][y] = Peca.PECA_PRETA;
				this.crieSequencia(x, y, Peca.PECA_PRETA);
				this.passeTurno();
			}
		}
		else // Modo de jogo individual
		{
			this.tabuleiro[x][y] = Peca.PECA_BRANCA;
			this.crieSequencia(x, y, Peca.PECA_BRANCA);
			this.passeTurno();
			
			this.computador.jogadaComputador();
			this.passeTurno();
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
		Orientacao reversa = Orientacao.SEM_ORIENTACAO;
		Orientacao orSeguinte = Orientacao.SEM_ORIENTACAO;
		
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
	
	// Com base em uma jogada vai criar e atualizar as sequências
	public void crieSequencia(int x, int y, Peca corPeca)
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
			this.sequenciasUm.add(nova);
			System.out.println("Adicionada nova Sequência-1");
		}
		else // Senão verifica os adjacentes
		{
			adjacentes = this.limparAdj(adjacentes, pontoInicial); // Remove as redundancias
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
				
				// Verifica a primeira metade
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
						xAtual--;
						if(xAtual < 0)
						{
							xAtual++;
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
				
				// Verifica a outra potencial metade
				
				orientacaoAtual = this.orientacaoReversa(orientacaoAtual); // Reverte a orientação
				xAtual = pontoInicial.getX(); // Restaura X
				yAtual = pontoInicial.getY(); // Restaura Y
				
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
						xAtual--;
						if(xAtual < 0)
						{
							xAtual++;
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
				
				if(tam == 2)
				{
					this.sequenciasDois.add(nova);
					System.out.println("Adicionada nova Sequência-2");
				}
				if(tam == 3)
				{
					this.sequenciasTres.add(nova);
					System.out.println("Adicionada nova Sequência-3");
				}
				if(tam == 4)
				{
					this.sequenciasQuatro.add(nova);
					System.out.println("Adicionada nova Sequência-4");
				}
				if(tam == 5)
				{
					System.out.println("SEQUENCIA-5 ENCONTRADA");
					if(corPeca == Peca.PECA_BRANCA)
						this.controle.fimDeJogo(0); // Zero para brancas
					else
						this.controle.fimDeJogo(1); // Um para pretas
				}
			}
		}
	}
	
	public void passeTurno()
	{
		this.turno++;
	}
	
}
