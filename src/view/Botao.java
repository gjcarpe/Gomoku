package view;

import javax.swing.JButton;

public class Botao extends JButton
{
	private String descricao;
	
	public Botao()
	{
		this.descricao = "";
	}
	
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
}
