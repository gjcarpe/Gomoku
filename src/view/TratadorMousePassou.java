package view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class TratadorMousePassou implements MouseListener 
{	
	private JButton botao;
	private Color corPadrao;
	
	public TratadorMousePassou(JButton botao) 
	{
		this.botao = botao;
		this.corPadrao = botao.getForeground();
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		this.botao.setForeground(Color.YELLOW);
	}
	
	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		this.botao.setForeground(this.corPadrao);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}


	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
