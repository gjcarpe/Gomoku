package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorDoisJogadores implements ActionListener 
{
	private Janela pai;

	public TratadorDoisJogadores(Janela pai) 
	{
		this.pai = pai;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		System.out.println("2 JOGADORES");
		this.pai.doisJogadores();
	}

}
