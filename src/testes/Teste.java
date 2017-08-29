package testes;

import java.util.ArrayList;

import controle.Controle;
import modelo.Gomoku;
import modelo.ModoDeJogo;
import modelo.Orientacao;
import modelo.ParOrdenado;
import modelo.Peca;
import view.Janela;

public class Teste 
{
	Controle controle;
	Janela janela;
	Gomoku gomoku;
	Peca[][] tabuleiro;
	
	public Teste()
	{
		this.controle = new Controle();
		this.controle.iniciarJanela();
		this.janela = controle.getJanela();
		this.gomoku = new Gomoku(controle, ModoDeJogo.DOIS_JOGADORES);
		this.tabuleiro = gomoku.getTabuleiro();
	}
	
	public void teste() 
	{
		conteSemPeca();
		testeTabuleiro();
		testeOrientacao();
		testeOrientacaoReversa();
		testeRemocaoAdjacentes();
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
	
	private void testeTabuleiro()
	{
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
		for (int i = 0; i < adjacentes.size(); i++) 
		{
			System.out.println("" + adjacentes.get(i).getX() + "|" + adjacentes.get(i).getY());
		}
	}
	
	private void testeOrientacao()
	{
		ParOrdenado origem = new ParOrdenado(1, 1);
		ParOrdenado destino = new ParOrdenado(2, 0);
		Orientacao or = gomoku.determineOrientacao(origem, destino);
		System.out.println("ORIENTACAO = " + or.toString());
	}
	
	private void testeOrientacaoReversa()
	{
		System.out.println(gomoku.orientacaoReversa(Orientacao.NORTE).toString());
		System.out.println(gomoku.orientacaoReversa(Orientacao.SUL).toString());
		System.out.println(gomoku.orientacaoReversa(Orientacao.LESTE).toString());
		System.out.println(gomoku.orientacaoReversa(Orientacao.OESTE).toString());
		System.out.println(gomoku.orientacaoReversa(Orientacao.NORDESTE).toString());
		System.out.println(gomoku.orientacaoReversa(Orientacao.SUDOESTE).toString());
		System.out.println(gomoku.orientacaoReversa(Orientacao.NOROESTE).toString());
		System.out.println(gomoku.orientacaoReversa(Orientacao.SUDESTE).toString());
	}
	
	private void testeRemocaoAdjacentes() 
	{
		int x = 1;
		int y = 1;
		ArrayList<ParOrdenado> adjacentes = gomoku.encontreAdjacentes(x, y, Peca.PECA_BRANCA);
		ArrayList<ParOrdenado> resultado = gomoku.limparAdj(adjacentes, new ParOrdenado(x, y));
		System.out.println("TAMANHO RESULTADO = " + resultado + ".\n");
		for(int i = 0; i < resultado.size(); i++)
			System.out.println("[" + resultado.get(i).getX() +"]["+ resultado.get(i).getY() + "]");
		
	}
}
