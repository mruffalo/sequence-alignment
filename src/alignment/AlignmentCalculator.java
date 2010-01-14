package alignment;

public class AlignmentCalculator
{
	private String x;
	private String y;
	private int xlen, ylen;
	private int[][] scoreArray;
	private String xalig, yalig;
	private boolean local;
	private AlignmentScoringSystem scoring;
	
	public AlignmentCalculator(String a, String b, AlignmentScoringSystem scoring_, boolean local_)
	{
		x = a;
		y = b;
		xlen = x.length();
		ylen = y.length();
		scoreArray = new int[ylen + 1][xlen + 1];
		local = local_;
		scoring = scoring_;
	}
	
	public void fillScoreArray()
	{
		int col, row;
		int northwest, north, west;
		int best;
		for (col = 0; col <= xlen; col++)
		{
			scoreArray[0][col] = localScore(scoring.gapContinue * col);
		}
		for (row = 0; row <= ylen; row++)
		{
			scoreArray[row][0] = localScore(scoring.gapContinue * row);
		}
		for (row = 1; row <= ylen; row++)
		{
			for (col = 1; col <= xlen; col++)
			{
				if (x.charAt(col - 1) == y.charAt(row - 1))
				{
					northwest = localScore(scoreArray[row - 1][col - 1] + scoring.match);
				}
				else
				{
					northwest = localScore(scoreArray[row - 1][col - 1] + scoring.mismatch);
				}
				west = localScore(scoreArray[row][col - 1] + scoring.gapContinue);
				north = localScore(scoreArray[row - 1][col] + scoring.gapContinue);
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
			if ((row > 0) && (scoreArray[row][col] == scoreArray[row - 1][col] + scoring.gapContinue))
			{
				xalig = "-" + xalig;
				yalig = y.charAt(row - 1) + yalig;
				row--;
			}
			else if ((col > 0) && (scoreArray[row][col] == scoreArray[row][col] + scoring.gapContinue))
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
		System.out.println();
	}
	
	public int getValue(int i, int j)
	{
		return scoreArray[j][i];
	}
	
	private int localScore(int i)
	{
		return (local && i < 0) ? 0 : i;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		AlignmentScoringSystem scoring = new AlignmentScoringSystem(0, -1, 2, -1);
		AlignmentCalculator nw1 = new AlignmentCalculator(args[0], args[1], scoring, false);
		nw1.fillScoreArray();
		nw1.printArray();
		nw1.setAlignment();
		nw1.printAlignment();
	}
}
