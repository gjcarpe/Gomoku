package modelo;

import java.util.ArrayList;
import java.util.Random;

public class Computador 
{
	private Gomoku gomoku;
	
	public Computador(Gomoku gomoku)
	{
		this.gomoku = gomoku;
	}
	
	// *** Métodos da IA *** //
	
	public ParOrdenado jogadaComputador() 
	{
		ParOrdenado resultado = null;
		resultado = this.miniMax(0); // Testando nível zero por enquanto
		//resultado = this.jogadaAleatoria();
		return resultado;
	}
	
	public ParOrdenado jogadaAleatoria() // Método para testes
	{
		ArrayList<ParOrdenado> jogadasPossiveis = this.pegarJogadasPossiveis();
		ParOrdenado resultado = null;
		Random rand = new Random();
		int sorteado = rand.nextInt(jogadasPossiveis.size());
		resultado = jogadasPossiveis.get(sorteado);
		
		return resultado;
	}
	
	public ParOrdenado miniMax(int profundidade) // TODO minimax profundidade - recursividade
	{
		ParOrdenado resultado = null;
		
		if(profundidade % 2 == 0) // Verifique em seu turno
		{
			resultado = this.max();
		}
		else // Verifique no turno do oponente
		{
			resultado = this.min();
		}
		
		return resultado;
	}
	
	// Encontra a melhor jogada no seu turno
	public ParOrdenado max() // TODO MAX
	{
		ArrayList<ParOrdenado> jogadas = this.encontrePontosAdjacentesDeSequencias(Peca.PECA_PRETA);
		ParOrdenado resultado = null;
		ParOrdenado parAtual = null;
		int xAtual = 0;
		int yAtual = 0;
		
		int valorInicial = this.calculePontuacaoDoTabuleiro();
		int maiorValor = valorInicial; // Começa com a pontuação atual do tabuleiro
		int valorAtual = 0;
		
		if(jogadas.size() > 0) // Caso de início de jogo - Jogar em algum ponto do centro
			resultado = jogadas.get(0); // Caso base
		else // No caso de não existirem jogadas candidatas é o caso do tabuleiro vazio
			resultado = this.jogadaAleatoria(); // Realiza uma jogada qualquer
		
		
		for(int i = 0; i < jogadas.size(); i++)
		{
			parAtual = jogadas.get(i); // Pega um ponto candidato
			this.gomoku.crieSequenciasTemporarias(xAtual, yAtual, Peca.PECA_PRETA); // Realiza uma jogada
			valorAtual = this.calculePontuacaoDoTabuleiro(); // Calcula quanto vale agora
			if(valorAtual > maiorValor) // Se é melhor que o atual
			{
				maiorValor = valorAtual; // Substitui
				resultado = parAtual;
			}
			this.gomoku.removaSequenciasTemporarias(); // Restaura o tabuleiro
		}

		return resultado;
	}
	
	// Encontra a melhor jogada no turno oponente
	public ParOrdenado min() // TODO MIN
	{
		ParOrdenado resultado = null;
		//ArrayList<ParOrdenado> jogadas = this.encontrePontosAdjacentesDeSequencias(Peca.PECA_BRANCA);
		return resultado;
	}

	// Retorna uma lista com todos os espaços sem peça do tabuleiro
	public ArrayList<ParOrdenado> pegarJogadasPossiveis()
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
	
	// Dá um valor de pontuação para o tabuleiro - Na forma de "Vantagem"
	// Vantagem = suaPontuação - pontuaçãoOponente
	public int calculePontuacaoDoTabuleiro()
	{
		int resultado = 0;
		ArrayList<Sequencia> sequencias1 = this.gomoku.getSequenciasUm();
		ArrayList<Sequencia> sequencias2 = this.gomoku.getSequenciasDois();
		ArrayList<Sequencia> sequencias3 = this.gomoku.getSequenciasTres();
		ArrayList<Sequencia> sequencias4 = this.gomoku.getSequenciasQuatro();
		ArrayList<Sequencia> temporarias = this.gomoku.getSequenciasTemporarias();
		
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
		
		for (int i = 0; i < sequencias1.size(); i++)
		{
			if(sequencias1.get(i).getCorPeca().equals(Peca.PECA_PRETA))
				resultado += this.gomoku.getValorUma();
			else
			{
				if(sequencias1.get(i).getCorPeca().equals(Peca.PECA_BRANCA))
					resultado -= this.gomoku.getValorUma();
			}
		}
		
		for(int i = 0; i < temporarias.size(); i++)
		{
			int tam = 0;
			if(temporarias.get(i).getCorPeca().equals(Peca.PECA_PRETA))
			{
				tam = temporarias.get(i).getTamanho();
				if(tam == 5)
					resultado += this.gomoku.getValorQuintupla();
				if(tam == 4)
					resultado += this.gomoku.getValorQuatro();
				if(tam == 3)
					resultado += this.gomoku.getValorQuatro();
				if(tam == 2)
					resultado += this.gomoku.getValorQuatro();
				if(tam == 1)
					resultado += this.gomoku.getValorUma();
			}
		}
		
		return resultado;
	}
	
	// Retorna uma lista com todos os pontos adjacentes de sequências candidatos a jogada da cor parâmetro
	public ArrayList<ParOrdenado> encontrePontosAdjacentesDeSequencias(Peca corPeca) // TODO Testar
	{
		ArrayList<ParOrdenado> resultado = new ArrayList<ParOrdenado>();
		Peca[][] tabuleiro = this.gomoku.getTabuleiro();
		Peca candidata = null;
		
		Sequencia seqAtual = null;
		ParOrdenado atual = null;
		int xAtual = 0;
		int yAtual = 0;
		Orientacao orAtual = Orientacao.SEM_ORIENTACAO;
		
		for(int i = 0; i < this.gomoku.getSequenciasQuatro().size(); i++)
		{
			seqAtual = this.gomoku.getSequenciasQuatro().get(i);
			
			// Se é uma sequência da cor pedida
			if(seqAtual.getCorPeca().equals(corPeca))
			{
				orAtual = seqAtual.getOrientacao();
				
				for(int j = 0; j < 2; j++)
				{
					if(j == 1) // Depois pega o seguinte do fim
					{
						orAtual = this.gomoku.orientacaoReversa(orAtual);
						atual = seqAtual.getFim();
					}
					else
						atual = seqAtual.getInicio(); // Primeiro pega o seguinte do começo
					
					xAtual = atual.getX();
					yAtual = atual.getY();
					
					// Ajusta as coordenadas para as do ponto seguinte
					
					if(orAtual == Orientacao.NORTE)
					{
						xAtual--;
					}
					if(orAtual == Orientacao.SUL)
					{
						xAtual++;
					}
					if(orAtual == Orientacao.LESTE)
					{
						yAtual++;
					}
					if(orAtual == Orientacao.OESTE)
					{
						yAtual--;
					}
					if(orAtual == Orientacao.NORDESTE)
					{
						xAtual--;
						yAtual++;
					}
					if(orAtual == Orientacao.NOROESTE)
					{
						xAtual--;
						yAtual--;
					}
					if(orAtual == Orientacao.SUDOESTE)
					{
						xAtual++;
						yAtual--;
					}
					if(orAtual == Orientacao.SUDESTE)
					{
						xAtual++;
						yAtual++;
					}
					
					// Se está dentro dos limites do tabuleiro
					if(xAtual >= 0 && xAtual <= 14 && yAtual >= 0 && yAtual <= 14)
					{
						candidata = tabuleiro[xAtual][yAtual];
						if(candidata.equals(Peca.SEM_PECA)) // E é um espaço vago de jogada
							resultado.add(new ParOrdenado(xAtual, yAtual));
					}
				}
				
			}	
			
		}
		
		// Faça o mesmo para as de três
		for(int i = 0; i < this.gomoku.getSequenciasTres().size(); i++)
		{
			seqAtual = this.gomoku.getSequenciasTres().get(i);
			
			// Se é uma sequência da cor pedida
			if(seqAtual.getCorPeca().equals(corPeca))
			{
				orAtual = seqAtual.getOrientacao();
				
				for(int j = 0; j < 2; j++)
				{
					if(j == 1) // Depois pega o seguinte do fim
					{
						orAtual = this.gomoku.orientacaoReversa(orAtual);
						atual = seqAtual.getFim();
					}
					else
						atual = seqAtual.getInicio(); // Primeiro pega o seguinte do começo
					
					xAtual = atual.getX();
					yAtual = atual.getY();
					
					// Ajusta as coordenadas para as do ponto seguinte
					
					if(orAtual == Orientacao.NORTE)
					{
						xAtual--;
					}
					if(orAtual == Orientacao.SUL)
					{
						xAtual++;
					}
					if(orAtual == Orientacao.LESTE)
					{
						yAtual++;
					}
					if(orAtual == Orientacao.OESTE)
					{
						yAtual--;
					}
					if(orAtual == Orientacao.NORDESTE)
					{
						xAtual--;
						yAtual++;
					}
					if(orAtual == Orientacao.NOROESTE)
					{
						xAtual--;
						yAtual--;
					}
					if(orAtual == Orientacao.SUDOESTE)
					{
						xAtual++;
						yAtual--;
					}
					if(orAtual == Orientacao.SUDESTE)
					{
						xAtual++;
						yAtual++;
					}
					
					// Se está dentro dos limites do tabuleiro
					if(xAtual >= 0 && xAtual <= 14 && yAtual >= 0 && yAtual <= 14)
					{
						candidata = tabuleiro[xAtual][yAtual];
						if(candidata.equals(Peca.SEM_PECA)) // E é um espaço vago de jogada
							resultado.add(new ParOrdenado(xAtual, yAtual));
					}
				}
				
			}
		}
		
		// Faça o mesmo para as de dois
		for(int i = 0; i < this.gomoku.getSequenciasDois().size(); i++)
		{
			seqAtual = this.gomoku.getSequenciasDois().get(i);
			
			// Se é uma sequência da cor pedida
			if(seqAtual.getCorPeca().equals(corPeca))
			{
				orAtual = seqAtual.getOrientacao();
				
				for(int j = 0; j < 2; j++)
				{
					if(j == 1) // Depois pega o seguinte do fim
					{
						orAtual = this.gomoku.orientacaoReversa(orAtual);
						atual = seqAtual.getFim();
					}
					else
						atual = seqAtual.getInicio(); // Primeiro pega o seguinte do começo
					
					xAtual = atual.getX();
					yAtual = atual.getY();
					
					// Ajusta as coordenadas para as do ponto seguinte
					
					if(orAtual == Orientacao.NORTE)
					{
						xAtual--;
					}
					if(orAtual == Orientacao.SUL)
					{
						xAtual++;
					}
					if(orAtual == Orientacao.LESTE)
					{
						yAtual++;
					}
					if(orAtual == Orientacao.OESTE)
					{
						yAtual--;
					}
					if(orAtual == Orientacao.NORDESTE)
					{
						xAtual--;
						yAtual++;
					}
					if(orAtual == Orientacao.NOROESTE)
					{
						xAtual--;
						yAtual--;
					}
					if(orAtual == Orientacao.SUDOESTE)
					{
						xAtual++;
						yAtual--;
					}
					if(orAtual == Orientacao.SUDESTE)
					{
						xAtual++;
						yAtual++;
					}
					
					// Se está dentro dos limites do tabuleiro
					if(xAtual >= 0 && xAtual <= 14 && yAtual >= 0 && yAtual <= 14)
					{
						candidata = tabuleiro[xAtual][yAtual];
						if(candidata.equals(Peca.SEM_PECA)) // E é um espaço vago de jogada
							resultado.add(new ParOrdenado(xAtual, yAtual));
					}
				}
				
			}
		}
		
		// Pegar todos os adjacentes para as de 1
		for(int i = 0; i < this.gomoku.getSequenciasUm().size(); i++)
		{
			seqAtual = this.gomoku.getSequenciasUm().get(i);
			atual = seqAtual.getInicio(); // Como início = fim neste caso
			
			// Como é um ponto independente
			
			for(int j = 0; j < 8; j++) // Verifica nas 8 direções possíveis
			{
				xAtual = atual.getX();
				yAtual = atual.getY();
				
				if(j == 0)
				{
					xAtual--;
				}
				if(j == 1)
				{
					xAtual++;
				}
				if(j == 2)
				{
					yAtual++;
				}
				if(j == 3)
				{
					yAtual--;
				}
				if(j == 4)
				{
					xAtual--;
					yAtual++;
				}
				if(j == 5)
				{
					xAtual--;
					yAtual--;
				}
				if(j == 6)
				{
					xAtual++;
					yAtual--;
				}
				if(j == 7)
				{
					xAtual++;
					yAtual++;
				}
				
				// Se está dentro dos limites do tabuleiro
				if(xAtual >= 0 && xAtual <= 14 && yAtual >= 0 && yAtual <= 14)
				{
					candidata = tabuleiro[xAtual][yAtual];
					if(candidata.equals(Peca.SEM_PECA)) // E é um espaço vago de jogada
						resultado.add(new ParOrdenado(xAtual, yAtual));
				}
			}	
		}
		
		return resultado;
	}
}
