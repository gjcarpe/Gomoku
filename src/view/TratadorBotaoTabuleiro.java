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
		int turno = this.pai.getTurno();
		if(!this.botao.getIcon().equals(Janela.iconePosicaoSemPeca))
			System.out.println("INVÁLIDO");
		else
		{
			if(turno >= 0)
			{
				String cor = "";
				if(turno % 2 == 0)
				{
					botao.setIcon(Janela.iconePosicaoPecaBranca);
					cor = "BRANCO";
				}
				else
				{
					botao.setIcon(Janela.iconePosicaoPecaPreta);
					cor = "PRETO";
				}
				
				System.out.println("JOGADOR " + cor + " CLICOU NO BOTÃO [" + this.x + "][" + this.y + "]");
				System.out.println("TURNO:" + turno);
				pai.jogada(x, y);

			}
		}
	}
}
