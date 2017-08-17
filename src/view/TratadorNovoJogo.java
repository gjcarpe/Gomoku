package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorNovoJogo implements ActionListener 
{

	private Janela pai;
	
	public TratadorNovoJogo(Janela pai) 
	{
		this.pai = pai;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		System.out.println("NOVO JOGO");
		this.pai.novoJogo();
		
		// TODO Controle - Novo Jogo
	}

}
