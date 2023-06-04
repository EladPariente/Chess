package Logic.Figure;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Logic.Board;
import Logic.Player;

public class Knight extends Piece
{
	private int Whight=300;
	public int estimate(Map<Integer, Piece> CBoard)
	{
		int [] [] score = 
		{
				 {-5, 0, 0, 0, 0, 0, 0, -5},
				 {-5, 0, 0, 10, 10, 0, 0, -5},
				 {-5, 5, 20, 20, 20, 20, 5, -5},
				 {-5, 10, 20, 30, 30, 20, 10, -5},
				 {-5, 10, 20, 30, 30, 20, 10, -5},
				 {-5, 5, 20, 10, 10, 20, 5, -5},
				 {-5, 0, 0, 0, 0, 0, 0, -5},
				 {-5, -10, 0, 0, 0, 0, -10, -5}
		};


		if(this.isWhite)
		{
			return this.Whight+score[this.row][this.col];
		}
		else
		{
			return -this.Whight-score[Board.SIZE-1-this.row][Board.SIZE-1-this.col];
		}
	}
	public List<Map<Integer, Piece>> PosslogboardsP(Map<Integer, Piece> CBoard,Player current,Player opponent)
	{
		Set<Integer> truePossibleMoves= this.TPossibleMoves(this,current);
		Set<Integer> Rkeys=this.ischek(this, CBoard, current, opponent);
		truePossibleMoves.removeAll(Rkeys);
		Iterator<Integer> iteratorX=truePossibleMoves.iterator();
		List<Map<Integer, Piece>> Posslogboards=new ArrayList<Map<Integer, Piece>>();
		while (iteratorX.hasNext())
		{
			int nextX = iteratorX.next();
			Map<Integer, Piece> B=new HashMap<Integer, Piece>();
			Player C=null;
		  	try
			{
		  		C=current.clone();
			}
			catch (CloneNotSupportedException e)
			{
				
				e.printStackTrace();
			}
			Player O=null;
			try
			{
				O=opponent.clone();
			}
			catch (CloneNotSupportedException e)
			{
				
				e.printStackTrace();
			}
			C.pieces.remove(this.getKey());

    		C.pieces.put(new Integer(nextX/8*Board.SIZE + nextX%8), new Knight(this.isWhite(),nextX/8,nextX%8));
    		O.pieces.remove(nextX/8*Board.SIZE + nextX%8);
    		B.putAll(C.pieces);
    		B.putAll(O.pieces);
    		Posslogboards.add(B);
		}
		return Posslogboards;
	}
	
	public Knight()
	{
		super();

	}

	public Knight (boolean isWhite,int row, int col)
	{
		super(isWhite,row,col);
		name="Knight";
	 	img = Toolkit.getDefaultToolkit().getImage("Images/" + (isWhite ? "wKnight" : "bKnight") + ".png");
    }
    

	public Set<Integer> possibleMoves()
	{
		return null;
	}
	
	public Set<Integer> TPossibleMoves(Piece pieceChoose,Player currentPlayer)
	{
		Set<Integer> possibleMoves = new HashSet<Integer> ();
		int x = this.row;
		int y = this.col;
		if(x+2<=7 && y+1<=7)	
			possibleMoves.add(calculateKey(x+2,y+1));
		if(x+2<=7 && y-1>=0)	
			possibleMoves.add(calculateKey(x+2,y-1));
		if(x-2>=0 && y+1<=7)	
			possibleMoves.add(calculateKey(x-2,y+1));
		if(x-2>=0 && y-1>=0)	
			possibleMoves.add(calculateKey(x-2,y-1));
		if(x+1<=7 && y+2<=7)	
			possibleMoves.add(calculateKey(x+1,y+2));
		if(x-1>=0 && y+2<=7)	
			possibleMoves.add(calculateKey(x-1,y+2));
		if(x+1<=7 && y-2>=0)		
			possibleMoves.add(calculateKey(x+1,y-2));
		if(x-1>=0 && y-2>=0)	
			possibleMoves.add(calculateKey(x-1,y-2));
		Iterator<Integer> iterator = possibleMoves.iterator();
		Set<Integer> deleteKeys = new HashSet<Integer> ();
		
		while (iterator.hasNext())
		{
			Integer next = iterator.next();
			
			
			if (next<0 || next>63)
			{
				deleteKeys.add(next);
				
			}
			if(currentPlayer.getPieces().containsKey(next))
			{
				deleteKeys.add(next);
				
			}
		}
		possibleMoves.removeAll(deleteKeys);
		return possibleMoves; 
	}
	public Set<Integer> ischek(Piece pieceChoose,Map<Integer, Piece> logBoard,Player currentPlayer,Player opponentPlayer)
	{
		Set<Integer>chekMoves=new HashSet<Integer> ();
		Set<Integer>truePossibleMoves = ((Knight)pieceChoose).TPossibleMoves(pieceChoose, currentPlayer);
		Iterator<Integer> iterator=truePossibleMoves.iterator();
		Knight P1 =new Knight(pieceChoose.isWhite, pieceChoose.getKey()%Board.SIZE, pieceChoose.getKey()/Board.SIZE);
		
		
		
		
		while (iterator.hasNext())
		{
			
			int next = iterator.next();
			
			HashMap<Integer, Piece> logBoard1 = new HashMap<Integer, Piece>();
			logBoard1.clear();
			logBoard1.putAll(logBoard);
			Player p = new Player();
			try
			{
				p=opponentPlayer.clone();
			}
			catch (CloneNotSupportedException e)
			{

				e.printStackTrace();
			}
			
			if(logBoard1.containsKey(next))
    		{
    			logBoard1.remove(next);
    			p.pieces.remove(next);
    		}
			logBoard1.remove(pieceChoose.getKey());
			P1.setRow(next/Board.SIZE);
			P1.setCol(next%Board.SIZE);
			
    		logBoard1.put(new Integer(next), P1);
    		
    		
    		Set<Integer> PossibleAttacs=new HashSet<Integer> ();
	    	Iterator<Piece> iteratorA = p.pieces.values().iterator();
	    	while (iteratorA.hasNext())
			{
				Piece nextA = iteratorA.next();
				if(nextA instanceof Queen)
				{
					PossibleAttacs.addAll(((Queen)nextA).TPossibleMoves(nextA, logBoard1, p));
				}
				else if(nextA instanceof King)
				{
					PossibleAttacs.addAll(((King)nextA).TPossibleMoves(nextA, p, logBoard1));
				}
				
				else if(nextA instanceof Bishop)
				{
					PossibleAttacs.addAll(((Bishop)nextA).TPossibleMoves(nextA, logBoard1, p));
				}
				else if(nextA instanceof Rook)
				{
					PossibleAttacs.addAll(((Rook)nextA).TPossibleMoves(nextA, logBoard1, p));
				}
				else if(nextA instanceof Knight)
				{
					PossibleAttacs.addAll(((Knight)nextA).TPossibleMoves(nextA, p));
				}
				else if(nextA instanceof Pawn)
				{
					PossibleAttacs.addAll(((Pawn)nextA).possibleAttacks(nextA, p));
				}
				
				
			}
	    	
	    	King CKing = null;
	    	Iterator<Piece> iteratorB = currentPlayer.pieces.values().iterator();
	    	while (iteratorB.hasNext())
			{
				Piece nextB = iteratorB.next();
				
				if(nextB instanceof King)
				{
					CKing=new King(((King)nextB).isWhite,((King)nextB).row,((King)nextB).col);
					break;
				}
			}
	    	int CKKey=calculateKey(CKing.row, CKing.col);
	    	Iterator<Integer> iteratorC=PossibleAttacs.iterator();
	 
	    	while (iteratorC.hasNext())
			{
	    		
				Integer nextC = iteratorC.next();
				
				if(CKKey==nextC)
				{
					
					chekMoves.add(next);
					PossibleAttacs=null;
					break;
				}
			}
	    	PossibleAttacs=null;
		}
		
		return chekMoves;
	}
	
	public Knight clone() throws CloneNotSupportedException
	{		
		Knight p = new Knight();		
		super.copy(p);
		p.img = Toolkit.getDefaultToolkit().getImage("Images/" + (isWhite ? "wKnight" : "bKnight") + ".png");
		return p;
	}
}