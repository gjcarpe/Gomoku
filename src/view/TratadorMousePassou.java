package view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TratadorMousePassou implements MouseListener 
{	
	private Botao botao;
	private Botao info;
	private Color corPadrao; // TODO - Erro cor padrão durante passagem no turno preto
	
	public TratadorMousePassou(Botao botao, Botao info) 
	{
		this.botao = botao;
		this.info = info;
		this.corPadrao = botao.getForeground();
	}
	
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		if(!this.botao.equals(this.info))
			if(this.corPadrao != Color.WHITE && this.corPadrao != Color.BLACK)
				this.botao.setForeground(Color.YELLOW);
		
		Botao emissor = (Botao) e.getComponent();
		this.info.setText(emissor.getDescricao());
	}
	
	@Override
	public void mouseExited(MouseEvent e) 
	{
		this.botao.setForeground(this.corPadrao);
		this.info.setText("");
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}


	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
