package Logic.Algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Logic.GameResult;
import Logic.Player;
import Logic.Figure.Bishop;
import Logic.Figure.King;
import Logic.Figure.Knight;
import Logic.Figure.Pawn;
import Logic.Figure.Piece;
import Logic.Figure.Queen;
import Logic.Figure.Rook;

public class MiniMax
{

	private Map<Integer, Piece> logBoard;
	private boolean isWhite;
	private Player currentPlayer;
	private Player opponentPlayer;

	private Map<Integer, Piece> BestlogBoard;
	private int Depth=3;
	
	public MiniMax()
	{
		
	}
	

	public Map<Integer, Piece> getReply(Player p1,Player p2, Map<Integer, Piece>logboard1)
	{
		this.BestlogBoard=new HashMap<Integer, Piece>(); 
		this.logBoard=new HashMap<Integer, Piece>(); 
		this.logBoard.putAll(logboard1);
		try
		{
			this.currentPlayer=p1.clone();
		}
		catch (CloneNotSupportedException e)
		{
			
			e.printStackTrace();
		}
		try
		{
			this.opponentPlayer=p2.clone();
		}
		catch (CloneNotSupportedException e)
		{
			
			e.printStackTrace();
		}
		minimax(currentPlayer.getIsWhite(), Depth);
	
	  	Map<Integer, Piece> sentBoard=new HashMap<Integer, Piece>();
	  	
	  	for (Piece p : BestlogBoard.values())
		{
	  		
	  		sentBoard.put(p.getKey(), p);
		}
		return sentBoard;
	}
	
	
	
	public void minimax(boolean W, int depth)
	{
		this.isWhite = W;
		Map<Integer, Piece> CBoard=new HashMap<Integer, Piece>();
		CBoard.putAll(this.logBoard);
		int val = 0;
		if(this.isWhite)
			val = Max(depth, CBoard,-99999,99999); 
		else
			val = Min(depth, CBoard,-99999,99999); 
		
	}
	
	private int Max(int depth,Map<Integer, Piece> CBoard,int alpha, int beta) 
	{
		List<Map<Integer, Piece>> Posslogboards=new ArrayList<Map<Integer, Piece>>();
	  	int best = -10000;
	  	Player current= new Player(7,6,true);
	  	Player opponent= new Player(0,1,false);
	  	current.pieces.clear();
	  	opponent.pieces.clear();
		Iterator<Piece> iteratorY=CBoard.values().iterator();
		
		while (iteratorY.hasNext())
		{
	  		Piece nextY = iteratorY.next();
	  		Piece p=null;
	  		try
	  		{
	  			p =nextY.clone();
	  		}
	  		catch(CloneNotSupportedException e)
	  		{
	  			e.printStackTrace();
	  		}
	  		
	  		if(nextY.isWhite())
	  		{
	  			current.pieces.put(nextY.calculateKey(nextY.row, nextY.col), p);
	  		}
	  		else
	  		{
	  			opponent.pieces.put(nextY.calculateKey(nextY.row, nextY.col), p);
	  		}
		}
		GameResult gameResult=isGameOver(opponent,current , CBoard);
		if (gameResult.isGameOver())
		{
			if(gameResult.getGameResult()==GameResult.RESULT.DRAW)
			{
				return 0;
			}
			if(gameResult.getGameResult()==GameResult.RESULT.WHITE)
			{
				return 100000;
			}
			if(gameResult.getGameResult()==GameResult.RESULT.BLACK)
			{
				return -100000;
			}
		}
		if (depth == 0)
			return estimate(CBoard);
	  	Iterator<Piece> iterator=current.pieces.values().iterator();
    	
	  	while (iterator.hasNext())
		{
	  		Piece next = iterator.next();
	  		Posslogboards.addAll(next.PosslogboardsP(CBoard, current, opponent));
		}
	  	
	  	for(int i=0;i<Posslogboards.size();i++)
	  	{
	  		int val = Min(depth-1,Posslogboards.get(i),alpha,beta);
	  	    if (val>best) 
	  	    {
	  	    	best = val;
	  	    	if(alpha<val)
	  	    	{
	  	    		alpha=val;
	  	    	}
	  	    	
	  	    	if(beta<=alpha)
	  	    	{
	  	    		break;
	  	    	}
	  	    }
	  	}
	  	return best;
	}
	
	
	private int Min(int depth,Map<Integer, Piece> CBoard,int alpha, int beta) 
	{		
		List<Map<Integer, Piece>> Posslogboards=new ArrayList<Map<Integer, Piece>>();
	  	int best = 100000;
	  	Player current= new Player(0,1,false);
	  	Player opponent= new Player(7,6,true);
	  	current.pieces.clear();
	  	opponent.pieces.clear();
		Iterator<Piece> iteratorY=CBoard.values().iterator();
		
		while (iteratorY.hasNext())
		{
	  		Piece nextY = iteratorY.next();
	  		Piece p=null;
	  		try
	  		{
	  			p =nextY.clone();
	  		}
	  		catch(CloneNotSupportedException e)
	  		{
	  			e.printStackTrace();
	  		}
	  		
	  		if(!nextY.isWhite())
	  		{
	  			current.pieces.put(nextY.calculateKey(nextY.row, nextY.col), p);
	  		}
	  		else
	  		{
	  			opponent.pieces.put(nextY.calculateKey(nextY.row, nextY.col), p);
	  		}
		}
		GameResult gameResult=isGameOver(opponent,current , CBoard);
		if (gameResult.isGameOver())
		{
			if(gameResult.getGameResult()==GameResult.RESULT.DRAW)
			{
				return 0;
			}
			if(gameResult.getGameResult()==GameResult.RESULT.WHITE)
			{
				return 100000;
			}
			if(gameResult.getGameResult()==GameResult.RESULT.BLACK)
			{
				return -100000;
			}
		}
		if (depth == 0)
			return estimate(CBoard);
	  	Iterator<Piece> iterator=current.pieces.values().iterator();

	  	while (iterator.hasNext())
		{
	  		Piece next = iterator.next();
	  		Posslogboards.addAll(next.PosslogboardsP(CBoard, current, opponent));
		}
	  
	  	for(int i=0;i<Posslogboards.size();i++)
	  	{
	  		int val = Max(depth-1,Posslogboards.get(i),alpha,beta);
	  	    if (val<best) 
	  	    {
	  	    	best = val;
	  	    	if(beta>val)
	  	    	{
	  	    		beta=val;
	  	    	}
	  	    	if(depth==Depth)
	  	    	{
	  	    		BestlogBoard.clear();
	  	    		this.BestlogBoard.putAll(Posslogboards.get(i));
	  	    	}
	  	    		
	  	    	if(beta<=alpha)
	  	    	{
	  	    		break;
	  	    	}
	  	    }
	  	}
	  	return best;
	}
	
	
	public int estimate(Map<Integer, Piece> CBoard)
	{
		int estimateP=0;
		Iterator<Piece> iterator=CBoard.values().iterator();
		while (iterator.hasNext())
		{
			Piece next = iterator.next();

			estimateP+=next.estimate(CBoard);
		}
		return estimateP;
	}
	
	private GameResult isGameOver(Player current, Player opponent, Map<Integer, Piece> board)
	{
		GameResult gameResult = new GameResult();
		Set<Integer> truePossibleMoves=new HashSet<Integer> ();
    	Set<Integer> Rkeys=new HashSet<Integer> ();
		Iterator<Piece> iterator=current.pieces.values().iterator();
		Set<Integer> OPossibleMoves=new HashSet<Integer>();
//		Set<Integer> PossibleAttacks=new HashSet<Integer> ();
		while (iterator.hasNext())
		{
			Piece next = iterator.next();
	    	if (next instanceof Queen)
			{
	    		truePossibleMoves= ((Queen)next).TPossibleMoves(next,board,current);
	    		Rkeys=((Queen)next).ischek(next, board, current, opponent);
	    		truePossibleMoves.removeAll(Rkeys);
	    		OPossibleMoves.addAll(truePossibleMoves);
    		}
	    	if (next instanceof Rook)
			{
	    		truePossibleMoves= ((Rook)next).TPossibleMoves(next,board,current);
	    		Rkeys=((Rook)next).ischek(next, board, current, opponent);
	    		truePossibleMoves.removeAll(Rkeys);
	    		OPossibleMoves.addAll(truePossibleMoves);
    		}
	    	if (next instanceof Knight)
			{
	    		truePossibleMoves= ((Knight)next).TPossibleMoves(next,current);
	    		Rkeys=((Knight)next).ischek(next, board, current, opponent);
	    		truePossibleMoves.removeAll(Rkeys);
	    		OPossibleMoves.addAll(truePossibleMoves);
    		}
	    	if (next instanceof Bishop)
			{
	    		truePossibleMoves= ((Bishop)next).TPossibleMoves(next,board,current);
	    		Rkeys=((Bishop)next).ischek(next, board, current, opponent);
	    		truePossibleMoves.removeAll(Rkeys);
	    		OPossibleMoves.addAll(truePossibleMoves);
    		}
	    	if (next instanceof Pawn)
			{
	    		truePossibleMoves=((Pawn)next).TPossibleMoves(next,board); 
	    		Rkeys=((Pawn)next).ischek(next, board, current, opponent);
	    		truePossibleMoves.removeAll(Rkeys);
	    		OPossibleMoves.addAll(truePossibleMoves);
			}
	    	if (next instanceof King)
			{
	    		truePossibleMoves=((King)next).TPossibleMoves( next,current, board);
	    		Rkeys=((King)next).ischek(next, board, current, opponent);
	    		truePossibleMoves.removeAll(Rkeys);
	    		OPossibleMoves.addAll(truePossibleMoves);
	    		
			}
	    	
		}
		King CKing = null;
		Iterator<Piece> iteratorD=opponent.pieces.values().iterator();
		Set<Integer> CPossibleMoves=new HashSet<Integer>();
		
		while (iteratorD.hasNext())
		{
			Piece nextD = iteratorD.next();
	    	if (nextD instanceof Queen)
			{
	    		truePossibleMoves= ((Queen)nextD).TPossibleMoves(nextD,board,opponent);
	    		Rkeys=((Queen)nextD).ischek(nextD, board,current, opponent);
	    		truePossibleMoves.removeAll(Rkeys);
	    		CPossibleMoves.addAll(truePossibleMoves);
    		}
	    	if (nextD instanceof Rook)
			{
	    		truePossibleMoves= ((Rook)nextD).TPossibleMoves(nextD,board,opponent);
	    		Rkeys=((Rook)nextD).ischek(nextD, board, opponent, current);
	    		truePossibleMoves.removeAll(Rkeys);
	    		CPossibleMoves.addAll(truePossibleMoves);
    		}
	    	if (nextD instanceof Knight)
			{
	    		truePossibleMoves= ((Knight)nextD).TPossibleMoves(nextD,opponent);
	    		Rkeys=((Knight)nextD).ischek(nextD, board, opponent, current);
	    		truePossibleMoves.removeAll(Rkeys);
	    		CPossibleMoves.addAll(truePossibleMoves);
    		}
	    	if (nextD instanceof Bishop)
			{
	    		truePossibleMoves= ((Bishop)nextD).TPossibleMoves(nextD,board,opponent);
	    		Rkeys=((Bishop)nextD).ischek(nextD, board, opponent, current);
	    		truePossibleMoves.removeAll(Rkeys);
	    		CPossibleMoves.addAll(truePossibleMoves);
    		}
	    	if (nextD instanceof Pawn)
			{
	    		truePossibleMoves=((Pawn)nextD).TPossibleMoves(nextD,board); 
	    		Rkeys=((Pawn)nextD).ischek(nextD, board, opponent, current);
	    		truePossibleMoves.removeAll(Rkeys);
	    		CPossibleMoves.addAll(truePossibleMoves);
			}
	    	if (nextD instanceof King)
			{
	    		truePossibleMoves=((King)nextD).TPossibleMoves( nextD,opponent, board);
	    		Rkeys=((King)nextD).ischek(nextD, board, opponent, current);
	    		truePossibleMoves.removeAll(Rkeys);
	    		CPossibleMoves.addAll(truePossibleMoves);
	    		CKing=(King)nextD;
			}
	    	
		}

		if(CPossibleMoves.isEmpty() && !OPossibleMoves.contains(CKing.calculateKey(CKing.row, CKing.col)))
    	{
    		
			gameResult.setGameOver(true);
			gameResult.setGameResult(GameResult.RESULT.DRAW);
    		return gameResult;
    		
    	}
		else
		{
			if(CPossibleMoves.isEmpty() && OPossibleMoves.contains(CKing.calculateKey(CKing.row, CKing.col)))
			{
				
				if(current.getIsWhite())
				{
					gameResult.setGameOver(true);
	    			gameResult.setGameResult(GameResult.RESULT.WHITE);
	    			return gameResult;
				}
				else
				{
					gameResult.setGameOver(true);
	    			gameResult.setGameResult(GameResult.RESULT.BLACK);
	    			return gameResult;
				}
			}
			else
			{
				gameResult.setGameOver(false);
				gameResult.setGameResult(GameResult.RESULT.DRAW);
	    		return gameResult;
			}
		}
	}
	
}

