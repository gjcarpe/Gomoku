package modelo;

public class Gomoku 
{
	private boolean umJogador;
	
	public Gomoku(ModoDeJogo modo)
	{
		if(modo == ModoDeJogo.UM_JOGADOR)
			this.umJogador = true;
		else
			this.umJogador = false;
	}
	
	public boolean getUmJogador()
	{
		return this.umJogador;
	}
}
