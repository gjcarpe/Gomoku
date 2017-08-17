package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorEncerrar implements ActionListener 
{
	private Janela pai;
	
	public TratadorEncerrar(Janela pai) 
	{
		this.pai = pai;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("ENCERRAR");
		this.pai.encerrar();
		// TODO - Controle - Encerrar
	}

}
