package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorSair implements ActionListener 
{
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.err.println("Encerrando...");
		System.exit(0);
	}
}
