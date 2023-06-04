package GUI;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JPanel;

import Logic.Board;
import Logic.GameResult;
import Logic.Algo.MiniMax;
import Logic.Figure.Piece;
import Logic.GameResult.RESULT;

public class BoardPanel extends JPanel
{
	Image img;

	public Board board;

	public BoardPanel()
	{
		board = new Board(this);
		img = Toolkit.getDefaultToolkit().getImage("Images/board.jpg");

		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				
				Click(e.getPoint());
				if(board.isBlackTurn())
				{
					
					MiniMax M=new MiniMax();
					Map<Integer, Piece> bestPiecesMap=M.getReply( board.getBlackPlayer(), board.getWhitePlayer(),board.getLogBoard());
					board.clearP();
				
				
					for (Piece p : bestPiecesMap.values())
					{
						
						
							try{
								Piece piece = p.clone();
							
						
							if(p.isWhite())
							{
								
								board.player2Pieces(piece);
							}
							else
							{
								board.player1Pieces(piece);
							}
							}catch(Exception ex){
								ex.printStackTrace();
								
							}
						
							
					} 
					board.changLogBoard();
					board.setWhiteTurn(); 
					GameResult r = board.IsGameOver(board.getLogBoard(), board.getBlackPlayer(), board.getWhitePlayer());
					GameOver(r);	
				}
			}
		});
	}

	private void Click(Point point)
	{
		GameResult result = board.Click(point);
		GameOver(result);
	}
	private void GameOver(GameResult result)
	{
		System.out.println(result.isGameOver());
		if (!result.isGameOver())
		{
			repaint();
		}
		else
		{
			if(result.getGameResult()==RESULT.WHITE)
			{
				img=Toolkit.getDefaultToolkit().getImage("Images/you win.jpg");
			}
			if(result.getGameResult()==RESULT.BLACK)
			{
				img=Toolkit.getDefaultToolkit().getImage("Images/i win.jpg");
			}
			if(result.getGameResult()==RESULT.DRAW)
			{
				img=Toolkit.getDefaultToolkit().getImage("Images/tie");
			}
			
			repaint(); 
		}
	}
	@Override
	public void paint(Graphics gr)
	{
		gr.drawImage(img, 0, 0, this.getWidth(), this.getHeight()-14, this);
		board.paint(gr);
	}
}
