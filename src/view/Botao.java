package view;

import javax.swing.JButton;

public class Botao extends JButton
{
	private String descricao;
	
	public Botao(String texto)
	{
		this.descricao = "";
		this.setText(texto);
	}
	
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
}
