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
	
	public ParOrdenado jogadaComputador() 
	{
		ParOrdenado resultado = null;
		//ParOrdenado jogadaPC = this.miniMax();
		return resultado;
	}
	
	private ParOrdenado minimax(int profundidade) // TODO minimax profundidade
	{
		ParOrdenado resultado = null;
		ArrayList<ParOrdenado> jogadasPossiveis = this.pegarJogadasPossiveis();
		// Neste array contém todos os ramos de jogadas, agora basta simplificar.
		
		// Podar aqui?
		
		// max (profundidade = par)
		// calcule a melhor jogada deste turno
		
		// min (profundidade = ímpar)
		// calcule a melhor jogada do turno do oponente
		
		// decida o melhor caminho da árvore de estados -> avaliarVantagem()
		
		// Repita até profundidade = 0
		
		
		
		return resultado;
	}
	
	// Encontra a melhor jogada no seu turno
	public ParOrdenado max()
	{
		ParOrdenado resultado = null;
		ArrayList<ParOrdenado> jogadas = this.encontrePontosAdjacentesDeSequencias(Peca.PECA_PRETA);
		return resultado;
	}
	
	// Encontra a melhor jogada no turno oponente
	public ParOrdenado min()
	{
		ParOrdenado resultado = null;
		ArrayList<ParOrdenado> jogadas = this.encontrePontosAdjacentesDeSequencias(Peca.PECA_BRANCA);
		return resultado;
	}

	// Retorna uma lista com todos os espaços sem peça do tabuleiro
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
	
	// Dá um valor de pontuação para o tabuleiro - Na forma de "Vantagem"
	// Vantagem = suaPontuação - pontuaçãoOponente
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
	
	// Simula a jogada
	private void simularJogada(int x, int y, Peca corPeca) 
	{
		Peca[][] tabuleiro = this.gomoku.getTabuleiro();
		tabuleiro[x][y] = corPeca;
		this.gomoku.crieSequenciasTemporarias(x, y, corPeca);
	}
	
	// Anula a jogada anteriormente simulada
	private void anularJogada(int x, int y, Peca corPeca)
	{
		Peca[][] tabuleiro = this.gomoku.getTabuleiro();
		tabuleiro[x][y] = corPeca;
		this.gomoku.removaSequenciasTemporarias(x, y, corPeca);
	}
	
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
