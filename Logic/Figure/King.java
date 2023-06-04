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


public class King extends Piece
{
	private int Whight=10000;
	public int estimate(Map<Integer, Piece> CBoard)
	{
		int [] [] score = 
		{
				 {0, 0, 0, 0, 0, 0, 0, 0},
				 {0, 0, 5, 5, 5, 5, 0, 0},
				 {0, 5, 5, 10, 10, 5, 5, 0},
				 {0, 5, 10, 20, 20, 10, 5, 0},
				 {0, 5, 10, 20, 20, 10, 5, 0},
				 {0, 0, 5, 10, 10, 5, 0, 0},
				 {0, 5, 5, -5, -5, 0, 5, 0},
				 {0, 0, 5, 0, -15, 0, 10, 0},
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
	public boolean moved=false;
	public List<Map<Integer, Piece>> PosslogboardsP(Map<Integer, Piece> CBoard,Player current,Player opponent)
	{
		Set<Integer> truePossibleMoves=this.TPossibleMoves( this,current, CBoard);
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
			if (nextX==61 && !this.isMoved()&& C.pieces.get(nextX/8*Board.SIZE + 7) instanceof Rook)
			{
				Piece castlingRook=C.pieces.get((nextX/8*Board.SIZE + 7));
				C.pieces.remove(castlingRook.getKey());

	    		C.pieces.put(new Integer(nextX/8*Board.SIZE + 4), new Rook(this.isWhite(),nextX/8,4));
			}
			if (nextX==57 && !this.isMoved()&& C.pieces.get(nextX/8*Board.SIZE + 0) instanceof Rook)
			{
				Piece castlingRook=C.pieces.get((nextX/8*Board.SIZE + 0));
				C.pieces.remove(castlingRook.getKey());
				castlingRook.row = nextX/8;
				castlingRook.col = 2;
	    		C.pieces.put(new Integer(nextX/8*Board.SIZE + 2), new Rook(this.isWhite(),nextX/8,2));
				
			}
			if (nextX==5 &&!this.isMoved() && C.pieces.get(nextX/8*Board.SIZE + 7) instanceof Rook)
			{
				Piece castlingRook=C.pieces.get((nextX/8*Board.SIZE + 7));
				C.pieces.remove(castlingRook.getKey());

	    		C.pieces.put(new Integer(nextX/8*Board.SIZE + 4),  new Rook(this.isWhite(),nextX/8,4));
			}
			if (nextX==1 &&!this.isMoved()&& C.pieces.get(nextX/8*Board.SIZE +0) instanceof Rook)
			{
				
				Piece castlingRook=C.pieces.get((nextX/8*Board.SIZE + 0));
				C.pieces.remove(castlingRook.getKey());

	    		C.pieces.put(new Integer(nextX/8*Board.SIZE + 2), new Rook(this.isWhite(),nextX/8,2));
				
			}
			C.pieces.remove(this.getKey());
    		C.pieces.put(new Integer(nextX/8*Board.SIZE + nextX%8),  new King(this.isWhite(),nextX/8,nextX%8));
    		O.pieces.remove(nextX/8*Board.SIZE + nextX%8);
    		this.setMoved(true);
    		B.putAll(C.pieces);
    		B.putAll(O.pieces);
    		Posslogboards.add(B);
		}
		return Posslogboards;
	}
	
	
	
	
	
	
	
	public King()
	{
		super();
	}

	public King (boolean isWhite,int row, int col)
	{
		super(isWhite,row,col);
		name="King";
		img = Toolkit.getDefaultToolkit().getImage("Images/" + (isWhite ? "wKing" : "bKing") + ".png");
    }
	

    
	public Set<Integer> possibleMoves()
	{
		Set<Integer> possibleMoves = new HashSet<Integer> ();
		int x = this.row;
		int y = this.col;

		if(x!=7)
			possibleMoves.add(calculateKey(x+1,y));
		if(x!=0)
			possibleMoves.add(calculateKey(x-1,y));
		if(x!=7 && y!=7)
			possibleMoves.add(calculateKey(x+1,y+1));
		if(x!=7 && y!=0)
			possibleMoves.add(calculateKey(x+1,y-1));
		if(y!=7)
			possibleMoves.add(calculateKey(x,y+1));
		if(y!=0)
			possibleMoves.add(calculateKey(x,y-1));
		if(x!=0 && y!=7)
			possibleMoves.add(calculateKey(x-1,y+1));
		if(x!=0 && y!=0)
			possibleMoves.add(calculateKey(x-1,y-1));
		Iterator<Integer> iterator = possibleMoves.iterator();
		Set<Integer> deleteKeys = new HashSet<Integer> ();
		
		while (iterator.hasNext())
		{
			Integer next = iterator.next();
			
			if (next<0 || next>63)
			{
				deleteKeys.add(next);
			}
		}
		
		possibleMoves.removeAll(deleteKeys);
		
		return possibleMoves;
		
		
	}
	public Set<Integer> TPossibleMoves(Piece pieceChoose,Player currentPlayer, Map<Integer, Piece> logBoard)
	{
		Set<Integer> truePossibleMoves=pieceChoose.possibleMoves();
		Iterator<Integer> iterator = truePossibleMoves.iterator();
		Set<Integer> deleteKeys = new HashSet<Integer> ();

		
		while (iterator.hasNext())
		{
			Integer next = iterator.next();
			
			if (currentPlayer.pieces.get(next) != null)
			{
				deleteKeys.add(next);

			}
		}
		
		truePossibleMoves.removeAll(deleteKeys);
		if(!((King)pieceChoose).isMoved())
		{
			if(currentPlayer.contain(((King)pieceChoose).row,7)instanceof Rook)
			{
				if(!((Rook)currentPlayer.contain(((King)pieceChoose).row,7)).isMoved())
				{
					if(!logBoard.containsKey(calculateKey(((King)pieceChoose).row, 4)) && !logBoard.containsKey(calculateKey(((King)pieceChoose).row, 5)) && !logBoard.containsKey(calculateKey(((King)pieceChoose).row, 6)))
					{
						truePossibleMoves.add(calculateKey(((King)pieceChoose).row, 5));
					}
				}
			}
			if(currentPlayer.contain(((King)pieceChoose).row,0)instanceof Rook)
			{
				if(!((Rook)currentPlayer.contain(((King)pieceChoose).row,0)).isMoved())
				{
					if(!logBoard.containsKey(calculateKey(((King)pieceChoose).row, 2)) && !logBoard.containsKey(calculateKey(((King)pieceChoose).row, 1)))
					{
						truePossibleMoves.add(calculateKey(((King)pieceChoose).row, 1));
					}
				}
			}
		}
//		Iterator<Integer> iterator1 = truePossibleMoves.iterator();
//		while (iterator.hasNext())
//		{
//			Integer next = iterator.next();
//		
//			
//		}
		
		return truePossibleMoves;
		
	}

	public boolean isMoved()
	{
		return this.moved;
	}

	public void setMoved(boolean moved)
	{
		this.moved = moved;
	}
	public Set<Integer> ischek(Piece pieceChoose,Map<Integer, Piece> logBoard,Player currentPlayer,Player opponentPlayer)
	{
		Set<Integer>chekMoves=new HashSet<Integer> ();
		Set<Integer>truePossibleMoves = ((King)pieceChoose).TPossibleMoves(pieceChoose, currentPlayer, logBoard);
		Iterator<Integer> iterator=truePossibleMoves.iterator();
		King P1 =new King(pieceChoose.isWhite, pieceChoose.getKey()%Board.SIZE, pieceChoose.getKey()/Board.SIZE);
		
		
		
		
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
			Player p1 = new Player();
			try
			{
				p1=currentPlayer.clone();
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
    		
    		p1.pieces.remove(pieceChoose.getKey());
			P1.setRow(next/Board.SIZE);
			P1.setCol(next%Board.SIZE);
			
    		p1.pieces.put(new Integer(next), P1);
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
	    	
	    	int CKKey=calculateKey(P1.row, P1.col);
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

	protected void copy(King p){	
		p.moved = this.moved;
		super.copy(p);		

	}
	public King clone() throws CloneNotSupportedException
	{		
		King p = new King();
		p.moved = this.moved;
		super.copy(p);
		p.img = Toolkit.getDefaultToolkit().getImage("Images/" + (isWhite ? "wKing" : "bKing") + ".png"); 
		return p;
	}
}