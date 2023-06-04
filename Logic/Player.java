package Logic;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Logic.Figure.Bishop;
import Logic.Figure.King;
import Logic.Figure.Knight;
import Logic.Figure.Pawn;
import Logic.Figure.Piece;
import Logic.Figure.Queen;
import Logic.Figure.Rook;


public class Player
{
	public Map<Integer, Piece> pieces;
	boolean isWhite;
	int Row;
	
    public Player()
	{
		super();
		pieces = new HashMap<Integer, Piece>();
	}

	public Player(int row, int row_pawns, boolean W)
	{
    	pieces = new HashMap<Integer, Piece>();
    	for(int i=0; i< Board.SIZE; i++)
    		
    	    	pieces.put(new Integer(row_pawns*Board.SIZE + i), new Pawn(W, row_pawns, i));
    	
    
    	pieces.put(new Integer(row*Board.SIZE + 1), new Knight(W, row, 1));
    	pieces.put(new Integer(row*Board.SIZE + 6), new Knight(W, row, 6));
    	
    	pieces.put(new Integer(row*Board.SIZE + 3), new King(W, row, 3));
    	
    	pieces.put(new Integer(row*Board.SIZE + 2), new Bishop(W, row, 2));
    	pieces.put(new Integer(row*Board.SIZE + 5), new Bishop(W, row, 5));
    	
    	pieces.put(new Integer(row*Board.SIZE + 4), new Queen(W, row, 4));
    	
    	pieces.put(new Integer(row*Board.SIZE), new Rook(W, row, 0));
    	pieces.put(new Integer(row*Board.SIZE + 7), new Rook(W, row, 7));
    	isWhite=W;
    	Row=row;
    	
    	
    	
	}
    

    
    public void paint(Graphics gr) {
		for (Piece piece : pieces.values()) 
	    	piece.paint(gr);		
	}

	public int getRow()
	{
		return this.Row;
	}

	public Piece contain(int row, int col)
	{	
		Integer index = new Integer(row*Board.SIZE + col);
		
//		boolean b = pieces.containsKey(index);  
		return pieces.containsKey(index) ? pieces.get(index) : null;
	}
	public boolean getIsWhite()
	{
		return isWhite;
	}

	public Map<Integer, Piece> getPieces()
	{
		return this.pieces;
	}

	public Player clone() throws CloneNotSupportedException
	{
		

		Player player = new Player();
		player.isWhite = this.isWhite;
		player.Row = this.Row;
		player.pieces = new HashMap<Integer, Piece>();
		player.pieces.putAll(this.pieces);
		
		return player; 
	}
	
	public void printPeaces(){
		Set<Integer> keySet = this.pieces.keySet();
		
		StringBuffer sb = new StringBuffer();
		for (Integer key : keySet){
			sb.append(key);
			sb.append("=");
			sb.append(pieces.get(key).name);
			sb.append("; ");
			
		}
		
		
		
	}
	

	
	
	
	
	
}

