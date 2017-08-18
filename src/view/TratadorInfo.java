package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TratadorInfo implements MouseListener 
{
	private Janela pai;
	private Botao botao;
	
	public TratadorInfo(Janela pai, Botao botao) 
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
