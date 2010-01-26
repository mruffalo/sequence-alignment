package alignment;

public class AlignmentCalculator
{
	private String x;
	private String y;
	private AlignmentElement[][] a;
	private AlignmentElement[][] n;
	private AlignmentElement[][] w;
	private String xalig, align, yalig;
	private boolean local;
	private AlignmentScoringSystem scoring;
	
	public AlignmentCalculator(String s1, String s2, AlignmentScoringSystem scoring_, boolean local_)
	{
		x = s1;
		y = s2;
		a = new AlignmentElement[y.length() + 1][x.length() + 1];
		for (int j = 0; j <= y.length(); j++)
		{
			for (int i = 0; i <= x.length(); i++)
			{
				a[j][i] = new AlignmentElement(null);
				n[j][i] = new AlignmentElement(null);
				w[j][i] = new AlignmentElement(null);
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
		a[0][0].score = 0;
		for (col = 1; col <= x.length(); col++)
		{
			a[0][col].score = localScore(scoring.gapContinue * col);
			a[0][col].direction = PointerDirection.WEST;
			// This isn't negative infinity, but it's close enough for our purposes
			n[0][col].score = Integer.MIN_VALUE;
		}
		for (row = 1; row <= y.length(); row++)
		{
			a[row][0].score = localScore(scoring.gapContinue * row);
			a[row][0].direction = PointerDirection.NORTH;
			// This isn't negative infinity, but it's close enough for our purposes
			w[row][0].score = Integer.MIN_VALUE;
		}
		for (row = 1; row <= y.length(); row++)
		{
			for (col = 1; col <= x.length(); col++)
			{
				// N
				
				// W
				
				// A
				
				if (x.charAt(col - 1) == y.charAt(row - 1))
				{
					northwest = localScore(a[row - 1][col - 1].score + scoring.match);
				}
				else
				{
					northwest = localScore(a[row - 1][col - 1].score + scoring.mismatch);
				}
				west = localScore(a[row][col - 1].score + scoring.gapContinue);
				north = localScore(a[row - 1][col].score + scoring.gapContinue);
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
				a[row][col].score = best;
				a[row][col].direction = dir;
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
		for (int row = 0; row < a.length; row++)
		{
			for (int col = 0; col < a[row].length; col++)
			{
				print3(a[row][col].score);
			}
			System.out.println();
		}
	}
	
	/**
	 * XXX: Fix this for local alignment
	 */
	public void setAlignment()
	{
		int row = y.length();
		int col = x.length();
		StringBuilder xb = new StringBuilder();
		StringBuilder ab = new StringBuilder();
		StringBuilder yb = new StringBuilder();
		while ((col > 0) || (row > 0))
		{
			PointerDirection dir = a[row][col].direction;
			switch (dir)
			{
				case NORTH:
					xb.insert(0, "-");
					ab.insert(0, "-");
					yb.insert(0, y.charAt(row - 1));
					row--;
					break;
				case WEST:
					xb.insert(0, x.charAt(col - 1));
					ab.insert(0, "-");
					yb.insert(0, "-");
					col--;
					break;
				case NORTHWEST:
					char xc = x.charAt(col - 1);
					char yc = y.charAt(row - 1);
					xb.insert(0, xc);
					ab.insert(0, (xc == yc) ? "|" : "X");
					yb.insert(0, yc);
					col--;
					row--;
					break;
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
	
	public String getAlignment()
	{
		StringBuilder sb = new StringBuilder();
		String newline = System.getProperty("line.separator");
		
		sb.append(xalig);
		sb.append(newline);
		sb.append(align);
		sb.append(newline);
		sb.append(yalig);
		
		return sb.toString();
	}
	
	public int getValue(int i, int j)
	{
		return a[j][i].score;
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
