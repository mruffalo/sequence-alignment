package alignment;

public class AlignmentCalculator
{
	private String x;
	private String y;
	private AlignmentElement[][] scoreArray;
	private String xalig, align, yalig;
	private boolean local;
	private AlignmentScoringSystem scoring;
	
	public AlignmentCalculator(String a, String b, AlignmentScoringSystem scoring_, boolean local_)
	{
		x = a;
		y = b;
		scoreArray = new AlignmentElement[y.length() + 1][x.length() + 1];
		for (int j = 0; j <= y.length(); j++)
		{
			for (int i = 0; i <= x.length(); i++)
			{
				scoreArray[j][i] = new AlignmentElement(null);
			}
		}
		local = local_;
		scoring = scoring_;
	}
	
	public void fillScoreArray()
	{
		int col, row;
		int northwest, north, west;
		int best;
		PointerDirection dir;
		scoreArray[0][0].score = 0;
		for (col = 1; col <= x.length(); col++)
		{
			scoreArray[0][col].score = localScore(scoring.gapContinue * col);
			scoreArray[0][col].direction = PointerDirection.WEST;
		}
		for (row = 1; row <= y.length(); row++)
		{
			scoreArray[row][0].score = localScore(scoring.gapContinue * row);
			scoreArray[row][0].direction = PointerDirection.NORTH;
		}
		for (row = 1; row <= y.length(); row++)
		{
			for (col = 1; col <= x.length(); col++)
			{
				if (x.charAt(col - 1) == y.charAt(row - 1))
				{
					northwest = localScore(scoreArray[row - 1][col - 1].score + scoring.match);
				}
				else
				{
					northwest = localScore(scoreArray[row - 1][col - 1].score + scoring.mismatch);
				}
				west = localScore(scoreArray[row][col - 1].score + scoring.gapContinue);
				north = localScore(scoreArray[row - 1][col].score + scoring.gapContinue);
				best = northwest;
				dir = PointerDirection.NORTHWEST;
				if (north > best)
				{
					dir = PointerDirection.NORTH;
					best = north;
				}
				if (west > best)
				{
					dir = PointerDirection.WEST;
					best = west;
				}
				scoreArray[row][col].score = best;
				scoreArray[row][col].direction = dir;
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
				print3(scoreArray[row][col].score);
			}
			System.out.println();
		}
	}
	
	public void setAlignment()
	{
		int row = y.length();
		int col = x.length();
		StringBuilder xb = new StringBuilder();
		StringBuilder ab = new StringBuilder();
		StringBuilder yb = new StringBuilder();
		while ((col > 0) || (row > 0))
		{
			PointerDirection dir = scoreArray[row][col].direction;
			switch (dir)
			{
				case NORTH:
					// TODO this
					break;
				case WEST:
					// TODO this
					break;
				case NORTHWEST:
					// TODO this
					break;
			}
			if ((row > 0) && (scoreArray[row][col].score == scoreArray[row - 1][col].score + scoring.gapContinue))
			{
				xb.insert(0, "-");
				ab.insert(0, "-");
				yb.insert(0, y.charAt(row - 1));
				row--;
			}
			else if ((col > 0) && (scoreArray[row][col].score == scoreArray[row][col].score + scoring.gapContinue))
			{
				xb.insert(0, x.charAt(col - 1));
				ab.insert(0, "-");
				yb.insert(0, "-");
				col--;
			}
			else
			{
				char xc = x.charAt(col - 1);
				char yc = y.charAt(row - 1);
				xb.insert(0, xc);
				ab.insert(0, (xc == yc) ? "|" : "X");
				yb.insert(0, yc);
				col--;
				row--;
			}
		}
		xalig = xb.toString();
		align = ab.toString();
		yalig = yb.toString();
	}
	
	public void printAlignment()
	{
		System.out.println(xalig);
		System.out.println(align);
		System.out.println(yalig);
		System.out.println();
	}
	
	public int getValue(int i, int j)
	{
		return scoreArray[j][i].score;
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
