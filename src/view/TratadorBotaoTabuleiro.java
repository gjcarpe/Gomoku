package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import controle.Controle;

public class TratadorBotaoTabuleiro implements MouseListener 
{

	private JButton botao;
	private Controle controle;
	private int x;
	private int y;

	public TratadorBotaoTabuleiro(JButton botao, Controle controle, int x, int y)
	{
		this.botao = botao;
		this.controle = controle;
		this.x = x;
		this.y = y;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		botao.setIcon(Janela.iconePosicaoPecaPreta);
		controle.jogada(x, y);
	}

}
