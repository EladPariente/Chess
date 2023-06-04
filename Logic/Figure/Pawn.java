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


public class Pawn extends Piece
{
	private int Whight=100;
	public int estimate(Map<Integer, Piece> CBoard)
	{
		int [] [] score = 
		{
				 {90, 90, 90, 90, 90, 90, 90, 90},
				 {30, 30, 30, 40, 40, 30, 30, 30},
				 {20, 20, 20, 30, 30, 20, 20, 20},
				 {10, 10, 10, 20, 20, 10, 10, 10},
				 {0, 5, 10, 20, 20, 5, 5, 0},
				 {5, 0, 0, 5, 5, 0, 0, 5},
				 {0, 0, 0, -10, -10, 0, 0, 0},
				 {0, 0, 0, 0, 0, 0, 0, 0}
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
		Set<Integer> truePossibleMoves=this.TPossibleMoves(this,CBoard); 
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
			if(nextX/8==0)
			{
				C.pieces.remove(this.getKey());
	    		C.pieces.put(new Integer(nextX/8*Board.SIZE + nextX%8), new Queen(true, nextX/8, nextX%8));
	    		O.pieces.remove(nextX/8*Board.SIZE + nextX%8);
	    		B.putAll(C.pieces);
	    		B.putAll(O.pieces);
	    		Posslogboards.add(B);
	    		B=new HashMap<Integer, Piece>();
    			C=null;
    		  	try
    			{
    		  		C=current.clone();
    			}
    			catch (CloneNotSupportedException e)
    			{
    				
    				e.printStackTrace();
    			}
    			O=null;
    			try
    			{
    				O=opponent.clone();
    			}
    			catch (CloneNotSupportedException e)
    			{
    				
    				e.printStackTrace();
    			}
    			C.pieces.remove(this.getKey());
	    		C.pieces.put(new Integer(nextX/8*Board.SIZE + nextX%8), new Rook(true, nextX/8, nextX%8));
	    		O.pieces.remove(nextX/8*Board.SIZE + nextX%8);
	    		B.putAll(C.pieces);
	    		B.putAll(O.pieces);
	    		Posslogboards.add(B);
	    		B=new HashMap<Integer, Piece>();
    			C=null;
    		  	try
    			{
    		  		C=current.clone();
    			}
    			catch (CloneNotSupportedException e)
    			{
    				
    				e.printStackTrace();
    			}
    			O=null;
    			try
    			{
    				O=opponent.clone();
    			}
    			catch (CloneNotSupportedException e)
    			{
    				
    				e.printStackTrace();
    			}
    			C.pieces.remove(this.getKey());
	    		C.pieces.put(new Integer(nextX/8*Board.SIZE + nextX%8), new Knight(true, nextX/8, nextX%8));
	    		O.pieces.remove(nextX/8*Board.SIZE + nextX%8);
	    		B.putAll(C.pieces);
	    		B.putAll(O.pieces);
	    		Posslogboards.add(B);
	    		B=new HashMap<Integer, Piece>();
    			C=null;
    		  	try
    			{
    		  		C=current.clone();
    			}
    			catch (CloneNotSupportedException e)
    			{
    				
    				e.printStackTrace();
    			}
    			O=null;
    			try
    			{
    				O=opponent.clone();
    			}
    			catch (CloneNotSupportedException e)
    			{
    				
    				e.printStackTrace();
    			}
    			C.pieces.remove(this.getKey());
	    		C.pieces.put(new Integer(nextX/8*Board.SIZE + nextX%8), new Bishop(true, nextX/8, nextX%8));
	    		O.pieces.remove(nextX/8*Board.SIZE + nextX%8);
	    		B.putAll(C.pieces);
	    		B.putAll(O.pieces);
	    		Posslogboards.add(B);
			}
			if(nextX/8==7)
			{
				C.pieces.remove(this.getKey());
	    		C.pieces.put(new Integer(nextX/8*Board.SIZE + nextX%8), new Queen(true, nextX/8, nextX%8));
	    		O.pieces.remove(nextX/8*Board.SIZE + nextX%8);
	    		B.putAll(C.pieces);
	    		B.putAll(O.pieces);
	    		Posslogboards.add(B);
	    		B=new HashMap<Integer, Piece>();
    			C=null;
    		  	try
    			{
    		  		C=current.clone();
    			}
    			catch (CloneNotSupportedException e)
    			{
    				
    				e.printStackTrace();
    			}
    			O=null;
    			try
    			{
    				O=opponent.clone();
    			}
    			catch (CloneNotSupportedException e)
    			{
    				
    				e.printStackTrace();
    			}
    			C.pieces.remove(this.getKey());
	    		C.pieces.put(new Integer(nextX/8*Board.SIZE + nextX%8), new Rook(true, nextX/8, nextX%8));
	    		O.pieces.remove(nextX/8*Board.SIZE + nextX%8);
	    		B.putAll(C.pieces);
	    		B.putAll(O.pieces);
	    		Posslogboards.add(B);
	    		B=new HashMap<Integer, Piece>();
    			C=null;
    		  	try
    			{
    		  		C=current.clone();
    			}
    			catch (CloneNotSupportedException e)
    			{
    				
    				e.printStackTrace();
    			}
    			O=null;
    			try
    			{
    				O=opponent.clone();
    			}
    			catch (CloneNotSupportedException e)
    			{
    				
    				e.printStackTrace();
    			}
    			C.pieces.remove(this.getKey());
	    		C.pieces.put(new Integer(nextX/8*Board.SIZE + nextX%8), new Knight(true, nextX/8, nextX%8));
	    		O.pieces.remove(nextX/8*Board.SIZE + nextX%8);
	    		B.putAll(C.pieces);
	    		B.putAll(O.pieces);
	    		Posslogboards.add(B);
	    		B=new HashMap<Integer, Piece>();
    			C=null;
    		  	try
    			{
    		  		C=current.clone();
    			}
    			catch (CloneNotSupportedException e)
    			{
    				
    				e.printStackTrace();
    			}
    			O=null;
    			try
    			{
    				O=opponent.clone();
    			}
    			catch (CloneNotSupportedException e)
    			{
    				
    				e.printStackTrace();
    			}
    			C.pieces.remove(this.getKey());
	    		C.pieces.put(new Integer(nextX/8*Board.SIZE + nextX%8), new Bishop(true, nextX/8, nextX%8));
	    		O.pieces.remove(nextX/8*Board.SIZE + nextX%8);
	    		B.putAll(C.pieces);
	    		B.putAll(O.pieces);
	    		Posslogboards.add(B);
			}
			else
			{
				C.pieces.remove(this.getKey());

	    		C.pieces.put(new Integer(nextX/8*Board.SIZE + nextX%8),  new Pawn(this.isWhite(),nextX/8,nextX%8));
	    		O.pieces.remove(nextX/8*Board.SIZE + nextX%8);
	    		B.putAll(C.pieces);
	    		B.putAll(O.pieces);
	    		Posslogboards.add(B);
			}
	  	    
		}
		return Posslogboards;
	}
	
	public Pawn()
	{
		super();

	}

	public Pawn (boolean isWhite,int row, int col)
	{
		super(isWhite,row,col);
		name="Pawn";
    	img = Toolkit.getDefaultToolkit().getImage("Images/" + (isWhite ? "wPawn" : "bPawn") + ".png");
    }
    


	public Set<Integer> possibleMoves()
	{
		Set<Integer> possibleMoves = new HashSet<Integer> ();
		int x = this.row;
		int y = this.col;
	
		if(!isWhite)
		{
			if(x!=7)
			{
				possibleMoves.add(calculateKey(x+1,y));
			}
			if(x==1)
			{
				possibleMoves.add(calculateKey(x+2,y));
			}
		}
		if( isWhite)
		{
			if(x!=0)
			{
				possibleMoves.add(calculateKey(x-1,y));
			}
			if(x==6)
			{
				possibleMoves.add(calculateKey(x-2,y));
			}
		}
		
		return possibleMoves; 
	}
	public Set<Integer> TPossibleMoves(Piece pieceChoose,Map<Integer, Piece> logBoard)
	{
		Set<Integer> possibleMoves = new HashSet<Integer> ();
		int y = pieceChoose.row;
		int x = pieceChoose.col;
		
		if(!pieceChoose.isWhite)
		{
			
			if(!logBoard.containsKey(calculateKey(y+1,x)))
			{
				
				if(y!=7)					
				{
					
					possibleMoves.add(calculateKey(y+1,x));
				}
				if(y==1 && !logBoard.containsKey(calculateKey(y+2,x)))					
				{
					
					possibleMoves.add(calculateKey(y+2,x));
				}
			}
			if(logBoard.containsKey(calculateKey(y+1,x+1)) && x!=7)
			{
				
				if(logBoard.get(calculateKey(y+1,x+1)).isWhite())
				{
					possibleMoves.add(calculateKey(y+1,x+1));
				}
				
			}
			if(logBoard.containsKey(calculateKey(y+1,x-1)) && x!=0)
			{
				
				if(logBoard.get(calculateKey(y+1,x-1)).isWhite())
				{
					possibleMoves.add(calculateKey(y+1,x-1));
				}	
			}
		}
		if( pieceChoose.isWhite)
		{
			if(!logBoard.containsKey(calculateKey(y-1,x)))
			{
				if(y!=0)
				{
					possibleMoves.add(calculateKey(y-1,x));
				}
				if(y==6 && !logBoard.containsKey(calculateKey(y-2,x)))
				{
					possibleMoves.add(calculateKey(y-2,x));
				}
			}
			if(logBoard.containsKey(calculateKey(y-1,x+1)))
			{
				
				if(!logBoard.get(calculateKey(y-1,x+1)).isWhite() && x!=7)
				{
					
					possibleMoves.add(calculateKey(y-1,x+1));
				}
			}
			
			if(logBoard.containsKey(calculateKey(y-1,x-1)))
			{
				
				if(!logBoard.get(calculateKey(y-1,x-1)).isWhite() && x!=0)
				{
					possibleMoves.add(calculateKey(y-1,x-1));
				}
			}	
			
		}
		return possibleMoves;
	}
	public Set<Integer> possibleAttacks(Piece pieceChoose,Player currentPlayer)
	{
		int y = pieceChoose.row;
		int x = pieceChoose.col;
		Set<Integer> possibleAttacks=new HashSet<Integer> ();
		if(!pieceChoose.isWhite)
		{
			if(x+1<=7)
			{
				if(!currentPlayer.getPieces().containsKey(calculateKey(y+1,x+1)))
				{
					possibleAttacks.add(calculateKey(y+1,x+1));
				}
			}
			if(x-1>=0)
			{
				if(!currentPlayer.getPieces().containsKey(calculateKey(y+1,x-1)))
				{
					possibleAttacks.add(calculateKey(y+1,x-1));
				}
			}
			
			
		}
		if(pieceChoose.isWhite)
		{
			if(x-1>=0)
			{
				if(!currentPlayer.getPieces().containsKey(calculateKey(y+1,x-1)))
				{
					possibleAttacks.add(calculateKey(y-1,x-1));
				}
			}
			
			if(x+1<=7)
			{
				if(!currentPlayer.getPieces().containsKey(calculateKey(y+1,x+1)))
				{
					possibleAttacks.add(calculateKey(y-1,x+1));
				}
			}
			
		}
		return possibleAttacks;
	}
	public Set<Integer> ischek(Piece pieceChoose,Map<Integer, Piece> logBoard,Player currentPlayer,Player opponentPlayer)
	{
		Set<Integer>chekMoves=new HashSet<Integer> ();
		Set<Integer>truePossibleMoves = ((Pawn)pieceChoose).TPossibleMoves(pieceChoose, logBoard);
		Iterator<Integer> iterator=truePossibleMoves.iterator();
		Pawn P1 =new Pawn(pieceChoose.isWhite, pieceChoose.getKey()%Board.SIZE, pieceChoose.getKey()/Board.SIZE);
		
		
		
		
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
	
	public Pawn clone() throws CloneNotSupportedException
	{		
		Pawn p = new Pawn();		
		super.copy(p);
		p.img = Toolkit.getDefaultToolkit().getImage("Images/" + (isWhite ? "wPawn" : "bPawn") + ".png");
		return p;
	}
	
}