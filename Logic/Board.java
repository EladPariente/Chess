package Logic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import GUI.BoardPanel;
import Logic.Figure.Bishop;
import Logic.Figure.King;
import Logic.Figure.Knight;
import Logic.Figure.Pawn;
import Logic.Figure.Piece;
import Logic.Figure.Queen;
import Logic.Figure.Rook;

public class Board
{
	public boolean isGameOver = false;
	public GameResult gameResult = new GameResult();
	
	public static int SIZE = 8; 
	private Player blackPlayer,whitePlayer;
	public static BoardPanel boardPanel;
	public boolean turn;
	private Piece pieceChoose;
	public boolean showButtons = false;
	public Map<Integer, Piece> logBoard;
	Thread t;
	
	
	public static Scanner reader=new Scanner(System.in);
	public Board(BoardPanel panel)
	{
		blackPlayer = new Player(0,1, false);
		whitePlayer = new Player(7,6, true);		
		boardPanel = panel;
		setWhiteTurn();
		logBoard = new HashMap<Integer, Piece>();
		logBoard.putAll(blackPlayer.pieces);
		logBoard.putAll(whitePlayer.pieces);
	}
	public void changLogBoard()
	{
		this.logBoard.clear();
		logBoard.putAll(blackPlayer.pieces);
		logBoard.putAll(whitePlayer.pieces);
		
	}
	
	public void paint(Graphics gr) {
		
		blackPlayer.paint(gr);
		whitePlayer.paint(gr);	
		if (pieceChoose != null){
			gr.setColor(Color.RED);
			Graphics2D g2 = (Graphics2D) gr;
			g2.setStroke(new BasicStroke(10));
			gr.drawRect(pieceChoose.col*(Piece.CellSize), pieceChoose.row*(Piece.CellSize), Piece.CellSize, Piece.CellSize);
		}
	}

	public GameResult Click(Point point)
	{

		int row = (point.y)/ Piece.CellSize;
		int col = point.x/ Piece.CellSize;
		if ( IsLegal(row,col)){
			Player currentPlayer = isBlackTurn() ? blackPlayer : whitePlayer;
			Player opponentPlayer = isWhiteTurn() ? blackPlayer : whitePlayer;
			System.out.println("curret location: x col"+col+",y row"+row);
			System.out.println(8*row+col);
			Piece piece = currentPlayer.contain(row,col);
		    if ( piece != null)
		    	pieceChoose = piece;				
		    else
		    {
		    	if(pieceChoose!= null)
		    	{
			    	
			    	Set<Integer> truePossibleMoves=new HashSet<Integer> ();
			    	Set<Integer> Rkeys=new HashSet<Integer> ();
			    	if (pieceChoose instanceof Queen)
					{
			    		truePossibleMoves= ((Queen)pieceChoose).TPossibleMoves(pieceChoose,logBoard,currentPlayer);
			    		Rkeys=((Queen)pieceChoose).ischek(pieceChoose, logBoard, currentPlayer, opponentPlayer);
			    		truePossibleMoves.removeAll(Rkeys);
		    		}
			    	if (pieceChoose instanceof Rook)
					{
			    		truePossibleMoves= ((Rook)pieceChoose).TPossibleMoves(pieceChoose,logBoard,currentPlayer);
			    		Rkeys=((Rook)pieceChoose).ischek(pieceChoose, logBoard, currentPlayer, opponentPlayer);
			    		truePossibleMoves.removeAll(Rkeys);
			    		((Rook)pieceChoose).setMoved(true);
		    		}
			    	if (pieceChoose instanceof Knight)
					{
			    		truePossibleMoves= ((Knight)pieceChoose).TPossibleMoves(pieceChoose,currentPlayer);
			    		Rkeys=((Knight)pieceChoose).ischek(pieceChoose, logBoard, currentPlayer, opponentPlayer);
			    		truePossibleMoves.removeAll(Rkeys);
		    		}
			    	if (pieceChoose instanceof Bishop)
					{
			    		truePossibleMoves= ((Bishop)pieceChoose).TPossibleMoves(pieceChoose,logBoard,currentPlayer);
			    		Rkeys=((Bishop)pieceChoose).ischek(pieceChoose, logBoard, currentPlayer, opponentPlayer);
			    		truePossibleMoves.removeAll(Rkeys);
		    		}
			    	if (pieceChoose instanceof Pawn)
					{
			    		truePossibleMoves=((Pawn)pieceChoose).TPossibleMoves(pieceChoose,logBoard); 
			    		Rkeys=((Pawn)pieceChoose).ischek(pieceChoose, logBoard, currentPlayer, opponentPlayer);
			    		truePossibleMoves.removeAll(Rkeys);
					}
			    	if (pieceChoose instanceof King)
					{
			    		truePossibleMoves=((King)pieceChoose).TPossibleMoves( pieceChoose,currentPlayer, logBoard);
			    		Rkeys=((King)pieceChoose).ischek(pieceChoose, logBoard, currentPlayer, opponentPlayer);
			    		truePossibleMoves.removeAll(Rkeys);
			    		
			    		
					}
			    	

			    	if(truePossibleMoves.contains(pieceChoose.calculateKey(row, col)))
			    	{
			    		
			    		if(pieceChoose instanceof Pawn && (row==7 || row==0))
			    		{
//			    			System.out.println("before showButtons");
//			    			showButtons = true;
//			    			t.notify();
//			    			System.out.println("after showButtons");
//			    			//yael
			    			System.out.println("choos: Q for queen, K for knight, R for rook and B for Bishop");
			    			char chosen = reader.next().charAt(0);
			    			if(chosen=='Q')
			    			{	
			    				
			    				currentPlayer.pieces.remove(pieceChoose.getKey());
				    			pieceChoose= new Queen(currentPlayer.isWhite, row, col);
				    			currentPlayer.pieces.put(new Integer(row*Board.SIZE + col), pieceChoose);
			    			}
			    			if(chosen=='K')
			    			{	
			    				currentPlayer.pieces.remove(pieceChoose.getKey());
				    			pieceChoose= new Knight(currentPlayer.isWhite, row, col);
				    			currentPlayer.pieces.put(new Integer(row*Board.SIZE + col), pieceChoose);
			    			}
			    			if(chosen=='B')
			    			{	
			    				currentPlayer.pieces.remove(pieceChoose.getKey());
				    			pieceChoose= new Bishop(currentPlayer.isWhite, row, col);
				    			currentPlayer.pieces.put(new Integer(row*Board.SIZE + col), pieceChoose);
			    			}
			    			if(chosen=='R')
			    			{	
			    				currentPlayer.pieces.remove(pieceChoose.getKey());
				    			pieceChoose= new Rook(currentPlayer.isWhite, row, col);
				    			currentPlayer.pieces.put(new Integer(row*Board.SIZE + col), pieceChoose);
			    			}
			    		}
			    		else
			    		{
			    			currentPlayer.pieces.remove(pieceChoose.getKey());
				    		pieceChoose.row = row;
				    		pieceChoose.col = col;
				    		currentPlayer.pieces.put(new Integer(row*Board.SIZE + col), pieceChoose);
				    		
			    		}
			    		
			    		
			    		
			    		if (pieceChoose instanceof King)
			    		{
			    			if(!pieceChoose.isWhite())
			    			{
			    				if (new Integer(row*Board.SIZE + col)==5 && !((King)pieceChoose).isMoved())
			    				{
			    					Piece castlingRook=currentPlayer.pieces.get((row*Board.SIZE + 7));
				    				currentPlayer.pieces.remove(castlingRook.getKey());
				    				castlingRook.row = row;
				    				castlingRook.col = 4;
						    		currentPlayer.pieces.put(new Integer(row*Board.SIZE + 4), castlingRook);
			    					
			    				}
			    				if (new Integer(row*Board.SIZE + col)==1 && !((King)pieceChoose).isMoved())
			    				{
			    					Piece castlingRook=currentPlayer.pieces.get((row*Board.SIZE + 0));
				    				currentPlayer.pieces.remove(castlingRook.getKey());
				    				castlingRook.row = row;
				    				castlingRook.col = 2;
						    		currentPlayer.pieces.put(new Integer(row*Board.SIZE + 2), castlingRook);
			    					
			    				}
			    			}
			    			if(pieceChoose.isWhite())
			    			{
			    				if (new Integer(row*Board.SIZE + col)==61 && !((King)pieceChoose).isMoved())
			    				{
			    					Piece castlingRook=currentPlayer.pieces.get((row*Board.SIZE + 7));
				    				currentPlayer.pieces.remove(castlingRook.getKey());
				    				castlingRook.row = row;
				    				castlingRook.col = 4;
						    		currentPlayer.pieces.put(new Integer(row*Board.SIZE + 4), castlingRook);
			    				}
			    				if (row*Board.SIZE + col==57 && !((King)pieceChoose).isMoved())
			    				{
			    					
			    					Piece castlingRook=currentPlayer.pieces.get((row*Board.SIZE + 0));
				    				currentPlayer.pieces.remove(castlingRook.getKey());
				    				castlingRook.row = row;
				    				castlingRook.col = 2;
						    		currentPlayer.pieces.put(new Integer(row*Board.SIZE + 2), castlingRook);
				    				
			    				}
			    			}
			    		}
			    		
			    		if(pieceChoose instanceof Rook)
						{
			    			((Rook)pieceChoose).setMoved(true);
						}
			    		if(pieceChoose instanceof King)
						{
			    			((King)pieceChoose).setMoved(true);
						}
			    		opponentPlayer.pieces.remove(row*Board.SIZE + col);
			    		
			    		pieceChoose=null;
			    		changLogBoard();
			    		gameResult= IsGameOver(logBoard,currentPlayer,opponentPlayer);
			    		turn= !turn;
			    		
			    	}
			    	
		    	}
		    }		    
		}
		
		return gameResult;
		
	}
	public GameResult IsGameOver(Map<Integer, Piece> logBoard1,Player currentPlayer,Player opponentPlayer)
	{
		Set<Integer> truePossibleMoves=new HashSet<Integer> ();
    	Set<Integer> Rkeys=new HashSet<Integer> ();
		Iterator<Piece> iterator=currentPlayer.pieces.values().iterator();
		Set<Integer> OPossibleMoves=new HashSet<Integer>();

		while (iterator.hasNext())
		{
			Piece next = iterator.next();
	    	if (next instanceof Queen)
			{
	    		truePossibleMoves= ((Queen)next).TPossibleMoves(next,logBoard1,currentPlayer);
	    		Rkeys=((Queen)next).ischek(next, logBoard1, currentPlayer, opponentPlayer);
	    		truePossibleMoves.removeAll(Rkeys);
	    		OPossibleMoves.addAll(truePossibleMoves);
    		}
	    	if (next instanceof Rook)
			{
	    		truePossibleMoves= ((Rook)next).TPossibleMoves(next,logBoard1,currentPlayer);
	    		Rkeys=((Rook)next).ischek(next, logBoard1, currentPlayer, opponentPlayer);
	    		truePossibleMoves.removeAll(Rkeys);
	    		OPossibleMoves.addAll(truePossibleMoves);
    		}
	    	if (next instanceof Knight)
			{
	    		truePossibleMoves= ((Knight)next).TPossibleMoves(next,currentPlayer);
	    		Rkeys=((Knight)next).ischek(next, logBoard1, currentPlayer, opponentPlayer);
	    		truePossibleMoves.removeAll(Rkeys);
	    		OPossibleMoves.addAll(truePossibleMoves);
    		}
	    	if (next instanceof Bishop)
			{
	    		truePossibleMoves= ((Bishop)next).TPossibleMoves(next,logBoard1,currentPlayer);
	    		Rkeys=((Bishop)next).ischek(next, logBoard1, currentPlayer, opponentPlayer);
	    		truePossibleMoves.removeAll(Rkeys);
	    		OPossibleMoves.addAll(truePossibleMoves);
    		}
	    	if (next instanceof Pawn)
			{
	    		truePossibleMoves=((Pawn)next).TPossibleMoves(next,logBoard1); 
	    		Rkeys=((Pawn)next).ischek(next, logBoard1, currentPlayer, opponentPlayer);
	    		truePossibleMoves.removeAll(Rkeys);
	    		OPossibleMoves.addAll(truePossibleMoves);
			}
	    	if (next instanceof King)
			{
	    		truePossibleMoves=((King)next).TPossibleMoves( next,currentPlayer, logBoard1);
	    		Rkeys=((King)next).ischek(next, logBoard1, currentPlayer, opponentPlayer);
	    		truePossibleMoves.removeAll(Rkeys);
	    		OPossibleMoves.addAll(truePossibleMoves);
	    		
			}
	    	
		}
		
		King CKing = null;
		Iterator<Piece> iteratorD=opponentPlayer.pieces.values().iterator();
		Set<Integer> CPossibleMoves=new HashSet<Integer>();
		
		while (iteratorD.hasNext())
		{
			Piece nextD = iteratorD.next();
	    	if (nextD instanceof Queen)
			{
	    		truePossibleMoves= ((Queen)nextD).TPossibleMoves(nextD,logBoard1,opponentPlayer);
	    		Rkeys=((Queen)nextD).ischek(nextD, logBoard1,currentPlayer, opponentPlayer);
	    		truePossibleMoves.removeAll(Rkeys);
	    		CPossibleMoves.addAll(truePossibleMoves);
    		}
	    	if (nextD instanceof Rook)
			{
	    		truePossibleMoves= ((Rook)nextD).TPossibleMoves(nextD,logBoard1,opponentPlayer);
	    		Rkeys=((Rook)nextD).ischek(nextD, logBoard1, opponentPlayer, currentPlayer);
	    		truePossibleMoves.removeAll(Rkeys);
	    		CPossibleMoves.addAll(truePossibleMoves);
    		}
	    	if (nextD instanceof Knight)
			{
	    		truePossibleMoves= ((Knight)nextD).TPossibleMoves(nextD,opponentPlayer);
	    		Rkeys=((Knight)nextD).ischek(nextD, logBoard1, opponentPlayer, currentPlayer);
	    		truePossibleMoves.removeAll(Rkeys);
	    		CPossibleMoves.addAll(truePossibleMoves);
    		}
	    	if (nextD instanceof Bishop)
			{
	    		truePossibleMoves= ((Bishop)nextD).TPossibleMoves(nextD,logBoard1,opponentPlayer);
	    		Rkeys=((Bishop)nextD).ischek(nextD, logBoard1, opponentPlayer, currentPlayer);
	    		truePossibleMoves.removeAll(Rkeys);
	    		CPossibleMoves.addAll(truePossibleMoves);
    		}
	    	if (nextD instanceof Pawn)
			{
	    		truePossibleMoves=((Pawn)nextD).TPossibleMoves(nextD,logBoard1); 
	    		Rkeys=((Pawn)nextD).ischek(nextD, logBoard1, opponentPlayer, currentPlayer);
	    		truePossibleMoves.removeAll(Rkeys);
	    		CPossibleMoves.addAll(truePossibleMoves);
			}
	    	if (nextD instanceof King)
			{
	    		truePossibleMoves=((King)nextD).TPossibleMoves( nextD,opponentPlayer, logBoard1);
	    		Rkeys=((King)nextD).ischek(nextD, logBoard1, opponentPlayer, currentPlayer);
	    		truePossibleMoves.removeAll(Rkeys);
	    		CPossibleMoves.addAll(truePossibleMoves);
	    		CKing=(King)nextD;
			}
	    	
		}

		if(CPossibleMoves.isEmpty() && !OPossibleMoves.contains(CKing.calculateKey(CKing.row, CKing.col)))
    	{
			gameResult.setGameOver(true);
			gameResult.setGameResult(GameResult.RESULT.DRAW);
    		
    		
    	}
		else
		{
			if(CPossibleMoves.isEmpty() && OPossibleMoves.contains(CKing.calculateKey(CKing.row, CKing.col)))
			{
				isGameOver = true;
				if(currentPlayer.isWhite)
				{
					gameResult.setGameOver(true);
	    			gameResult.setGameResult(GameResult.RESULT.WHITE);
		    		
				}
				else
				{
					gameResult.setGameOver(true);
	    			gameResult.setGameResult(GameResult.RESULT.BLACK);
				}
			}
		}
		return gameResult;
	}
	public void clearP()
	{
		blackPlayer.pieces.clear();
		whitePlayer.pieces.clear();
	}
	public void player1Pieces(Piece p)
	{
		blackPlayer.pieces.put(p.getKey(), p);
		
	}
	public void player2Pieces(Piece p)
	{
		whitePlayer.pieces.put(p.getKey(), p);
	}
	public static boolean IsLegal(int row, int col)
	{		
		return row>=0 && row<=7 && col >=0 && col <=7;
	}
	public boolean isTurn()
	{
		return turn;
	}
	
	public boolean isBlackTurn(){
		return turn;
	}
	
	public boolean isWhiteTurn(){
		return ! turn;
	}

	public void setBlackTurn()
	{
		turn = true;
	}
	
	public void setWhiteTurn()
	{
		turn = false;
	}
	public Map<Integer, Piece> getLogBoard()
	{
		return this.logBoard;
	}
	public void setLogBoard(Map<Integer, Piece> logBoard)
	{
		this.logBoard = logBoard;
	}
	public Player getBlackPlayer()
	{
		return this.blackPlayer;
	}
	public void setBlackPlayer(Player player1)
	{
		this.blackPlayer = player1;
	}
	public Player getWhitePlayer()
	{
		return this.whitePlayer;
	}
	public void setWhitePlayer(Player player2)
	{
		this.whitePlayer = player2;
	}
	
	
	
	public void setThread(Thread t){
		this.t = t;
	}
	
	
}
