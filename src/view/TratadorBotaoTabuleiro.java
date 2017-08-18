package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class TratadorBotaoTabuleiro implements MouseListener 
{
	private Janela pai;
	private JButton botao;
	private int x;
	private int y;

	public TratadorBotaoTabuleiro(Janela pai, JButton botao, int x, int y)
	{
		this.pai = pai;
		this.botao = botao;
		this.x = x;
		this.y = y;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		System.out.println("CLICOU NO BOTÃO [" + this.x + "][" + this.y + "]");
		int turno = this.pai.getTurno();
		System.out.println("TURNO:" + turno);
		pai.jogada(x, y);
		if(turno % 2 == 0)
			botao.setIcon(Janela.iconePosicaoPecaBranca);
		else
			botao.setIcon(Janela.iconePosicaoPecaPreta);

	}

}
