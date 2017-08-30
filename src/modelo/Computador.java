package modelo;

import java.util.ArrayList;

public class Computador 
{
	private Gomoku gomoku;
	private Gomoku gomokuParcial;
	
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
		
		this.simulaJogada(x, y);
		
		// TODO Com base na lista de sequências 
		ArrayList<Sequencia> sequencias4 = this.gomokuParcial.getSequenciasQuatro();
		for (int i = 0; i < sequencias4.size(); i++){
			if (sequencias4.get(i).getCorPeca() == Peca.PECA_PRETA){
				resultado += this.gomokuParcial.getValorQuatro();
			} else{
				resultado -= this.gomokuParcial.getValorQuatro();
			}
		}
		
		ArrayList<Sequencia> sequencias3 = this.gomokuParcial.getSequenciasTres();
		for (int i = 0; i < sequencias3.size(); i++){
			if (sequencias3.get(i).getCorPeca() == Peca.PECA_PRETA){
				resultado += this.gomokuParcial.getValorTripla();
			} else{
				resultado -= this.gomokuParcial.getValorTripla();
			}
		}
		
		ArrayList<Sequencia> sequencias2 = this.gomokuParcial.getSequenciasDois();
		for (int i = 0; i < sequencias2.size(); i++){
			if (sequencias2.get(i).getCorPeca() == Peca.PECA_PRETA){
				resultado += this.gomokuParcial.getValorDupla();
			} else{
				resultado -= this.gomokuParcial.getValorDupla();
			}
		}
		
		ArrayList<Sequencia> sequencias1 = this.gomokuParcial.getSequenciasUm();
		for (int i = 0; i < sequencias1.size(); i++){
			if (sequencias1.get(i).getCorPeca() == Peca.PECA_PRETA){
				resultado += 1;
			} else{
				resultado -= 1;
			}
		}
		return resultado;
	}
	
	private void simulaJogada(int x, int y) {
		// TODO Auto-generated method stub
		
		this.gomokuParcial = this.gomoku;
		
		//Peca[][] tabuleiroParcial = this.gomokuParcial.getTabuleiro();
		//tabuleiroParcial[x][y] = Peca.PECA_PRETA;
		this.gomokuParcial.crieSequencia(x, y, Peca.PECA_PRETA);
		
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
