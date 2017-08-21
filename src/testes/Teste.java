package testes;

import java.util.ArrayList;

import controle.Controle;
import modelo.Gomoku;
import modelo.ModoDeJogo;
import modelo.Orientacao;
import modelo.ParOrdenado;
import modelo.Peca;
import view.Janela;

@SuppressWarnings("unused")
public class Teste 
{
	
	Janela janela = new Janela();
	Controle controle = new Controle(janela);
	Gomoku gomoku = new Gomoku(controle, ModoDeJogo.DOIS_JOGADORES);
	Peca[][] tabuleiro = gomoku.getTabuleiro();
	
	public Teste()
	{
		this.teste();
	}
	
	private void teste() 
	{
		conteSemPeca();
		tabuleiro[0][0] = Peca.PECA_BRANCA;
		tabuleiro[1][0] = Peca.PECA_BRANCA;
		tabuleiro[2][0] = Peca.PECA_BRANCA;
		
		tabuleiro[0][1] = Peca.PECA_BRANCA;
		//tabuleiro[1][1] = Peca.PECA_BRANCA;
		tabuleiro[2][1] = Peca.PECA_BRANCA;
		
		tabuleiro[0][2] = Peca.PECA_BRANCA;
		tabuleiro[1][2] = Peca.PECA_BRANCA;
		tabuleiro[2][2] = Peca.PECA_BRANCA;
		
		conteSemPeca();
		int x = 1;
		int y = 1;
		ArrayList<ParOrdenado> adjacentes = gomoku.encontreAdjacentes(x, y, Peca.PECA_BRANCA);
		System.out.println("ADJACENTES SIZE = " + adjacentes.size());
		System.out.println("" + adjacentes.get(0).getX() + "|" + adjacentes.get(0).getY());
		System.out.println("" + adjacentes.get(1).getX() + "|" + adjacentes.get(1).getY());
		System.out.println("" + adjacentes.get(2).getX() + "|" + adjacentes.get(2).getY());
	}
	
	private void testeOrientacao()
	{
		ParOrdenado origem = new ParOrdenado(1, 1);
		ParOrdenado destino = new ParOrdenado(2, 0);
		Orientacao or = gomoku.determineOrientacao(origem, destino);
		System.out.println("ORIENTACAO = " + or.toString());
	}
	
	private void conteSemPeca()
	{
		int x = 0;
		for (int i = 0; i < tabuleiro.length; i++) 
		{
			for (int j = 0; j < tabuleiro.length; j++) 
			{
				if(tabuleiro[i][j] == Peca.SEM_PECA)
					x++;
			}
		}
		System.out.println("Número de casas do tabuleiro SEM_PECA = " + x);
	}
	
	/*
	 * Métodos ok:
	 *  - Encontre adjacentes
	 *  - Determine orientação
	 *  
	 * TODO Falta arrumar o criar Sequência
	 * 
	 */
	
}
