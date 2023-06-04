package Logic;

public class GameResult
{
	private boolean isGameOver = false;
	
	public enum RESULT{
		  WHITE,
		  BLACK,
		  DRAW
		}

	private RESULT gameResult = RESULT.DRAW;
	
	public boolean isGameOver()
	{
		return this.isGameOver;
	}

	public void setGameOver(boolean isGameOver)
	{
		this.isGameOver = isGameOver;
	}

	public RESULT getGameResult()
	{
		return this.gameResult;
	}

	public void setGameResult(RESULT gameResult)
	{
		this.gameResult = gameResult;
	}
	
	
}
