package modelo;

public class Sequencia 
{
	private Peca corPeca;
	private ParOrdenado inicio;
	private ParOrdenado fim;
	private Orientacao orientacao;
	private int tamanho;
	
	public Sequencia(ParOrdenado inicio, ParOrdenado fim, Peca cor, Orientacao or, int tam)
	{
		this.inicio = inicio;
		this.fim = fim;
		this.corPeca = cor;
		this.orientacao = or;
		this.tamanho = tam;
	}

	public ParOrdenado getInicio() 
	{
		return this.inicio;
	}

	public ParOrdenado getFim() 
	{
		return this.fim;
	}
	
	public Peca getCorPeca()
	{
		return this.corPeca;
	}
	
	public Orientacao getOrientacao()
	{
		return this.orientacao;
	}

	public int getTamanho() 
	{
		return this.tamanho;
	}
}
