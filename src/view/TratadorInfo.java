package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class TratadorInfo implements MouseListener 
{
	private Janela pai;
	private JButton botao;
	
	public TratadorInfo(Janela pai, JButton botao) 
	{
		this.pai = pai;
		this.botao = botao;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		this.botao.setText("");
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
