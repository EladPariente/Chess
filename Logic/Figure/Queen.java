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

public class Queen extends Piece
{
	private int Whight=1000;
	public int estimate(Map<Integer, Piece> CBoard)
	{
		
		                          
		if(this.isWhite)
		{
			return this.Whight;
		}
		else
		{
			return -this.Whight;
		}
	}
	public List<Map<Integer, Piece>> PosslogboardsP(Map<Integer, Piece> CBoard,Player current,Player opponent)
	{
		Set<Integer> truePossibleMoves= this.TPossibleMoves(this,CBoard,current);
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

    		C.pieces.put(new Integer(nextX/8*Board.SIZE + nextX%8),  new Queen(this.isWhite(),nextX/8,nextX%8));
    		O.pieces.remove(nextX/8*Board.SIZE + nextX%8);
    		B.putAll(C.pieces);
    		B.putAll(O.pieces);
    		Posslogboards.add(B); 
		}
		return Posslogboards;
    		
	}
	
	public Queen()
	{
		super();

	}

	public Queen (boolean isWhite,int row, int col)
	{
		super(isWhite,row,col);
		name="Queen";
		img = Toolkit.getDefaultToolkit().getImage("Images/" + (isWhite ? "w" : "b") + "Queen.png");
    }  
    


	
	public Set<Integer> possibleMoves()
	{
		Set<Integer> possibleMoves = new HashSet<Integer> ();
		
		
		int x = this.row;
		int y = this.col;

		for(int i=x+1;i<=7&&i>=0;i++)
		{
			possibleMoves.add(calculateKey(i, y));
		}
		for(int i=x-1;i<=7&&i>=0;i--)
		{
			possibleMoves.add(calculateKey(i, y));
		}
		for(int j=y+1;j<=7&&j>=0;j++)
		{
			possibleMoves.add(calculateKey(x, j));
		}
		for(int j=y-1;j<=7&&j>=0;j--)
		{
			possibleMoves.add(calculateKey(x, j));
		}
		int j=y+1;
		for(int i=x+1;i<=7&&j<=7&&i>=0&&j>=0;i++)
		{
			
			possibleMoves.add(calculateKey(i, j));
			j++;
		}
		j=y+1;
		for(int i=x-1;i<=7&&j<=7&&i>=0&&j>=0;i--)
		{
			
			possibleMoves.add(calculateKey(i, j));
			j++;
		}
		j=y-1;
		for(int i=x-1;i<=7&&j<=7&&i>=0&&j>=0;i--)
		{
			
			possibleMoves.add(calculateKey(i, j));
			j--;
		}
		j=y-1;
		for(int i=x+1;i<=7&&j<=7&&i>=0&&j>=0;i++)
		{
			
			possibleMoves.add(calculateKey(i, j));
			j--;
		}
		
		
		return possibleMoves; 
	}
	
	public List<Integer> possMoveUp()
	{
		int y = this.row;
		int x = this.col;
		
		List<Integer> up=new ArrayList<Integer>();
		
		for(int j=y-1;j<=7&&j>=0;j--)
		{
			
			up.add(calculateKey(j, x));
		}
		return up;
	}
	
	public List<Integer> possMoveDown()
	{
		int y = this.row;
		int x = this.col;
		
		
		List<Integer> down=new ArrayList<Integer>();
		
		for(int j=y+1;j<=7&&j>=0;j++)
		{
			down.add(calculateKey(j, x));
		}
		return down;
	}
	
	public List<Integer> possMoveRight()
	{
		int y = this.row;
		int x = this.col;
		
		List<Integer> right=new ArrayList<Integer>();
		
		for(int i=x+1;i<=7&&i>=0;i++)
		{
			right.add(calculateKey(y,i));
		}
		return right;
	}
	
	public List<Integer> possMoveLeft()
	{
		int y = this.row;
		int x = this.col;
		
		List<Integer> left=new ArrayList<Integer>();
		
		for(int i=x-1;i<=7&&i>=0;i--)
		{
			left.add(calculateKey(y,i));
		}
		return left;
	}
	
	public List<Integer> possMoveUpRight()
	{
		int y = this.row;
		int x = this.col;
		
		List<Integer> upRight=new ArrayList<Integer>();
		
		int j=y-1;
		for(int i=x+1;i<=7&&j<=7&&i>=0&&j>=0;i++)
		{
			
			upRight.add(calculateKey(j,i));
			j--;
		}
		return upRight;
	}
	
	public List<Integer> possMoveDownRight()
	{
		int y = this.row;
		int x = this.col;
		
		List<Integer> downRight=new ArrayList<Integer>();
		
		int j=y+1;
		for(int i=x+1;i<=7&&j<=7&&i>=0&&j>=0;i++)
		{
			
			downRight.add(calculateKey(j,i));
			j++;
		}
		return downRight;
	}
	
	public List<Integer> possMoveDownLeft()
	{
		int y = this.row;
		int x = this.col;
		
		List<Integer> downLeft=new ArrayList<Integer>();
		
		int j=y+1;
		for(int i=x-1;i<=7&&j<=7&&i>=0&&j>=0;i--)
		{
			
			downLeft.add(calculateKey(j,i));
			j++;
		}
		return downLeft;
	}
	
	public List<Integer> possMoveUpLeft()
	{
		int y = this.row;
		int x = this.col;
		
		List<Integer> upLeft=new ArrayList<Integer>();
		
		int j=y-1;
		for(int i=x-1;i<=7&&j<=7&&i>=0&&j>=0;i--)
		{
			
			upLeft.add(calculateKey(j,i));
			j--;
		}
		return upLeft;
	}
	public Set<Integer> TPossibleMoves(Piece pieceChoose,Map<Integer, Piece> logBoard,Player currentPlayer)
	{

		
		Set<Integer> Rkeys=new HashSet<Integer> ();
		List<Integer> up=((Queen)pieceChoose).possMoveUp();
		Iterator<Integer> iteratorU = up.iterator();


		while (iteratorU.hasNext())
		{
			Integer next = iteratorU.next();
			
			
			if (logBoard.containsKey(next))
			{
				
				
				if(logBoard.get(next).isWhite()==currentPlayer.getIsWhite())
					Rkeys.addAll(up.subList(up.lastIndexOf(next),up.size()));
				else
					Rkeys.addAll(up.subList(up.lastIndexOf(next)+1,up.size()));
				break;
				
			}
		}
		List<Integer> down=((Queen)pieceChoose).possMoveDown();
		Iterator<Integer> iteratorD = down.iterator();
		while (iteratorD.hasNext())
		{
			Integer next = iteratorD.next();
			
			
			if (logBoard.containsKey(next))
			{
				if(logBoard.get(next).isWhite()==currentPlayer.getIsWhite())
					Rkeys.addAll(down.subList(down.lastIndexOf(next),down.size()));
				else
					Rkeys.addAll(down.subList(down.lastIndexOf(next)+1,down.size()));
				break;
			}
		}
		List<Integer> right=((Queen)pieceChoose).possMoveRight();
		Iterator<Integer> iteratorR = right.iterator();
		while (iteratorR.hasNext())
		{
			Integer next = iteratorR.next();
			
			
			if (logBoard.containsKey(next))
			{
				if(logBoard.get(next).isWhite()==currentPlayer.getIsWhite())
					Rkeys.addAll(right.subList(right.lastIndexOf(next),right.size()));
				else
					Rkeys.addAll(right.subList(right.lastIndexOf(next)+1,right.size()));
				break;
			}
		}
		List<Integer> left=((Queen)pieceChoose).possMoveLeft();
		Iterator<Integer> iteratorL = left.iterator();
		while (iteratorL.hasNext())
		{
			Integer next = iteratorL.next();
			
			
			if (logBoard.containsKey(next))
			{
				
			
				if(logBoard.get(next).isWhite()==currentPlayer.getIsWhite())
				{
					Rkeys.addAll(left.subList(left.lastIndexOf(next),left.size()));
//					List<Integer> p=left.subList(left.lastIndexOf(next),left.size());
					
				}
				else
					Rkeys.addAll(left.subList(left.lastIndexOf(next)+1,left.size()));
				break;
			}
		}
		List<Integer> upRight=((Queen)pieceChoose).possMoveUpRight();
		Iterator<Integer> iteratorUR = upRight.iterator();
		while (iteratorUR.hasNext())
		{
			Integer next = iteratorUR.next();
			
			
			if (logBoard.containsKey(next))
			{
				if(logBoard.get(next).isWhite()==currentPlayer.getIsWhite())
					Rkeys.addAll(upRight.subList(upRight.lastIndexOf(next),upRight.size()));
				else
					Rkeys.addAll(upRight.subList(upRight.lastIndexOf(next)+1,upRight.size()));
				break;
			}
		}
		List<Integer> downRight=((Queen)pieceChoose).possMoveDownRight();
		Iterator<Integer> iteratorDR = downRight.iterator();
		while (iteratorDR.hasNext())
		{
			Integer next = iteratorDR.next();
			
			
			if (logBoard.containsKey(next))
			{
				if(logBoard.get(next).isWhite()==currentPlayer.getIsWhite())
					Rkeys.addAll(downRight.subList(downRight.lastIndexOf(next),downRight.size()));
				else
					Rkeys.addAll(downRight.subList(downRight.lastIndexOf(next)+1,downRight.size()));
				break;
			}
		}
		List<Integer> downLeft=((Queen)pieceChoose).possMoveDownLeft();
		Iterator<Integer> iteratorDL = downLeft.iterator();
		while (iteratorDL.hasNext())
		{
			Integer next = iteratorDL.next();
			
			
			if (logBoard.containsKey(next))
			{
				if(logBoard.get(next).isWhite()==currentPlayer.getIsWhite())
					Rkeys.addAll(downLeft.subList(downLeft.lastIndexOf(next),downLeft.size()));
				else
					Rkeys.addAll(downLeft.subList(downLeft.lastIndexOf(next)+1,downLeft.size()));
				break;
			}
		}
		List<Integer> upLeft=((Queen)pieceChoose).possMoveUpLeft();
		Iterator<Integer> iteratorUL = upLeft.iterator();
		while (iteratorUL.hasNext())
		{
			Integer next = iteratorUL.next();
			
			
			if (logBoard.containsKey(next))
			{
				if(logBoard.get(next).isWhite()==currentPlayer.getIsWhite())
					Rkeys.addAll(upLeft.subList(upLeft.lastIndexOf(next),upLeft.size()));
				else
					Rkeys.addAll(upLeft.subList(upLeft.lastIndexOf(next)+1,upLeft.size()));
				break;
			}
		}
		Set<Integer> truePossibleMoves=((Queen)pieceChoose).possibleMoves();
		truePossibleMoves.removeAll(Rkeys);
		
		Iterator<Integer> iterator1 = truePossibleMoves.iterator();
		Set<Integer>m=new HashSet<Integer> ();
		while (iterator1.hasNext())
		{
			Integer next1 = iterator1.next();
			
			if(next1<0 || next1>63)
			{
				m.add(next1);
			}
			
		}
		
		
		truePossibleMoves.removeAll(m);
	
		return truePossibleMoves;
		
	}
	public Set<Integer> ischek(Piece pieceChoose,Map<Integer, Piece> logBoard,Player currentPlayer,Player opponentPlayer)
	{
		Set<Integer>chekMoves=new HashSet<Integer> ();
		Set<Integer>truePossibleMoves = ((Queen)pieceChoose).TPossibleMoves(pieceChoose, logBoard, currentPlayer);
		Iterator<Integer> iterator=truePossibleMoves.iterator();
		Queen P1 =new Queen(pieceChoose.isWhite, pieceChoose.getKey()%Board.SIZE, pieceChoose.getKey()/Board.SIZE);
		
		
		
		
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

	
	public Queen clone() throws CloneNotSupportedException
	{		
		Queen p = new Queen();		
		super.copy(p);
		p.img = Toolkit.getDefaultToolkit().getImage("Images/" + (isWhite ? "w" : "b") + "Queen.png");
		return p;
	}
	

}

