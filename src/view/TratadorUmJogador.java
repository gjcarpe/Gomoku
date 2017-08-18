package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorUmJogador implements ActionListener 
{
	
	private Janela pai;

	public TratadorUmJogador(Janela pai) 
	{
		this.pai = pai;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("1 JOGADOR");
		this.pai.umJogador();
	}

}
