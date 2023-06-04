package Logic.Figure;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Logic.Board;
import Logic.Player;


public abstract class Piece  {
	public static int CellSize = 75;
	protected boolean isWhite;
	public int row,col;
	public String name="";
	protected Image img;
	public int weight;
	public int[][] score;
	
	
	public Piece()
	{
		super();
	}

	Piece(boolean isWhite, int row,int col)
	{
		CellSize = 74; 
		this.isWhite = isWhite;
		this.row = row;
		this.col = col;
	}
	public abstract List<Map<Integer, Piece>> PosslogboardsP(Map<Integer, Piece> CBoard,Player current,Player opponent);
	public abstract int estimate(Map<Integer, Piece> CBoard);
	public abstract Set<Integer> possibleMoves();

	
	public void paint(Graphics gr){
		 gr.drawImage(img,col*(CellSize), row*(CellSize), CellSize, CellSize, Board.boardPanel);	
	}
 
	public boolean isWhite()
	{
		return this.isWhite;
	}
	
	public Integer getKey(){
		return  row*Board.SIZE + col;
	}
	
	public Integer calculateKey(int row, int col){
		return  row*Board.SIZE + col;
	}



	public int hashCode()
	{
		
		return super.hashCode();
	}

	public int getCol()
	{
		return this.col;
	}

	public void setCol(int col)
	{
		this.col = col;
	}

	public int getRow()
	{
		return this.row;
	}

	public void setRow(int row)
	{
		this.row = row;
	}
	
	
	public abstract Piece clone() throws CloneNotSupportedException;

	public void copy(Piece p){
	{
			
		p.col = this.col;
		
		p.isWhite = this.isWhite;
		p.name = this.name;
		p.row = this.row;		
	}
	
	
	}

	public boolean equals(Object p1)
	{
		Piece p = (Piece)p1;

		boolean isEqual=false;
		if(		this.row==p.row &&
				this.col==p.col&& 
				this.isWhite==p.isWhite &&
				this.name.equals(p.name))
		{
			isEqual=true;

		}
					

		return isEqual;
	}

	public String toString()
	{
		
		return 
		"name="+name+" isWite="+isWhite+" col="+col+" row="+row+"\n";
	}
	

	


}
