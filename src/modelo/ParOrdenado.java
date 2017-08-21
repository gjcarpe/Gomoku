package modelo;

public class ParOrdenado 
{
	private int x;
	private int y;
	
	public ParOrdenado(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX() 
	{
		return x;
	}

	public int getY() 
	{
		return y;
	}
	
	public boolean eIgual(ParOrdenado outro)
	{
		boolean resultado = false;
		if(this.x == outro.getX() && this.y == outro.getY())
			resultado = true;
		return resultado;
	}

}
