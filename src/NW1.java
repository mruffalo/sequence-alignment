public class NW1
{
	public static final int gapscore = -2;
	public static final int matchscore = 2;
	public static final int mismatchscore = -1;
	private String x;
	private String y;
	private int xlen, ylen;
	private int[][] scoreArray;
	private String xalig, yalig;
	public NW1(String a, String b)
	{
		x = a;
		y = b;
		xlen = x.length();
		ylen = y.length();
		scoreArray = new int[ylen + 1][xlen + 1];
	}
	public void fillScoreArray()
	{
		int col, row;
		int northwest, north, west;
		int best;
		for (col = 0; col <= xlen; col++)
		{
			scoreArray[0][col] = gapscore * col;
		}
		for (row = 0; row <= ylen; row++)
		{
			scoreArray[row][0] = gapscore * row;
		}
		for (row = 1; row <= ylen; row++)
		{
			for (col = 1; col <= xlen; col++)
			{
				if (x.charAt(col - 1) == y.charAt(row - 1))
				{
					northwest = scoreArray[row - 1][col - 1] + matchscore;
				}
				else
				{
					northwest = scoreArray[row - 1][col - 1] + mismatchscore;
				}
				west = scoreArray[row][col - 1] + gapscore;
				north = scoreArray[row - 1][col] + gapscore;
				best = northwest;
				if (north > best)
				{
					best = north;
				}
				if (west > best)
				{
					best = west;
				}
				scoreArray[row][col] = best;
			}
		}
	}
	public void print3(int x)
	{
		String string = String.format("$%3d$ & ", x);
		if (string.length() == 8)
		{
			System.out.print(string);
		}
		else
		{
			System.out.print("***");
		}
	}
	public void printArray()
	{
		for (int row = 0; row < scoreArray.length; row++)
		{
			for (int col = 0; col < scoreArray[row].length; col++)
			{
				print3(scoreArray[row][col]);
			}
			System.out.println();
		}
	}
	public void setAlignment()
	{
		int row = ylen;
		int col = xlen;
		xalig = "";
		yalig = "";
		while ((col > 0) || (row > 0))
		{
			if ((row > 0) && (scoreArray[row][col] == scoreArray[row - 1][col] + gapscore))
			{
				xalig = "-" + xalig;
				yalig = y.charAt(row - 1) + yalig;
				row--;
			}
			else if ((col > 0) && (scoreArray[row][col] == scoreArray[row][col] + gapscore))
			{
				xalig = x.charAt(col - 1) + xalig;
				yalig = "-" + yalig;
				col--;
			}
			else
			{
				xalig = x.charAt(col - 1) + xalig;
				yalig = y.charAt(row - 1) + yalig;
				col--;
				row--;
			}
		}
	}
	public void printAlignment()
	{
		System.out.println(xalig);
		System.out.println(yalig);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		NW1 nw1 = new NW1(args[0], args[1]);
		nw1.fillScoreArray();
		nw1.printArray();
		nw1.setAlignment();
		nw1.printAlignment();
	}
}
