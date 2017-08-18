package view;

import javax.swing.JButton;

public class Botao extends JButton
{
	private static final long serialVersionUID = -2727346001593779873L;
	private String descricao;
	
	public Botao(String texto)
	{
		this.descricao = "";
		this.setText(texto);
	}
	
	public String getDescricao()
	{
		return this.descricao;
	}
	
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
}
