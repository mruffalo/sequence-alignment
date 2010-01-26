package alignment;

import utils.LicenseUtil;

public class AlignmentCalculator
{
	private String x;
	private String y;
	private AlignmentElement[][] a;
	private AlignmentElement[][] n;
	private AlignmentElement[][] w;
	/**
	 * Used for local alignment printing
	 */
	private AlignmentElement highest;
	private String xalig, align, yalig;
	private boolean local;
	private AlignmentScoringSystem scoring;
	
	public AlignmentCalculator(String s1, String s2, AlignmentScoringSystem scoring_, boolean local_)
	{
		x = s1;
		y = s2;
		a = new AlignmentElement[y.length() + 1][x.length() + 1];
		n = new AlignmentElement[y.length() + 1][x.length() + 1];
		w = new AlignmentElement[y.length() + 1][x.length() + 1];
		for (int j = 0; j <= y.length(); j++)
		{
			for (int i = 0; i <= x.length(); i++)
			{
				a[j][i] = new AlignmentElement(j, i, null);
				n[j][i] = new AlignmentElement(j, i, null);
				w[j][i] = new AlignmentElement(j, i, null);
			}
		}
		highest = a[0][0];
		local = local_;
		scoring = scoring_;
	}
	
	public void fillScoreArray()
	{
		int col, row;
		double northwest;
		double west;
		double north;
		double best;
		PointerDirection dir;
		a[0][0].score = 0.0;
		for (col = 1; col <= x.length(); col++)
		{
			a[0][col].score = localScore(scoring.gapContinue * col);
			a[0][col].direction = PointerDirection.WEST;
			n[0][col].score = Double.NEGATIVE_INFINITY;
		}
		for (row = 1; row <= y.length(); row++)
		{
			a[row][0].score = localScore(scoring.gapContinue * row);
			a[row][0].direction = PointerDirection.NORTH;
			w[row][0].score = Double.NEGATIVE_INFINITY;
		}
		for (row = 1; row <= y.length(); row++)
		{
			for (col = 1; col <= x.length(); col++)
			{
				// N
				double from_a = localScore(a[row - 1][col].score + scoring.gapStart + scoring.gapContinue);
				double from_n = localScore(n[row - 1][col].score + scoring.gapContinue);
				north = n[row][col].score = Math.max(from_a, from_n);
				// W
				from_a = localScore(a[row][col - 1].score + scoring.gapStart + scoring.gapContinue);
				double from_w = localScore(w[row][col - 1].score + scoring.gapContinue);
				west = w[row][col].score = Math.max(from_a, from_w);
				// A
				if (x.charAt(col - 1) == y.charAt(row - 1))
				{
					northwest = localScore(a[row - 1][col - 1].score + scoring.match);
				}
				else
				{
					northwest = localScore(a[row - 1][col - 1].score + scoring.mismatch);
				}
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
				
				if (a[row][col].score >= highest.score)
				{
					highest = a[row][col];
				}
			}
		}
	}
	
	public void print3(Double score)
	{
		String string = String.format("$%03.0f$ & ", score);
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
	
	public void setAlignment()
	{
		int row = local ? highest.row : y.length();
		int col = local ? highest.col : x.length();
		StringBuilder xb = new StringBuilder();
		xb.append(x.substring(col).toLowerCase());
		StringBuilder ab = new StringBuilder();
		StringBuilder yb = new StringBuilder();
		yb.append(y.substring(row).toLowerCase());
		char gapChar;
		char matchChar;
		char mismatchChar;
		boolean passedZero = false;
		while ((col > 0) || (row > 0))
		{
			PointerDirection dir = a[row][col].direction;
			if (a[row][col].score <= 0.0 && local)
			{
				passedZero = true;
			}
			gapChar = passedZero ? ' ' : '-';
			matchChar = passedZero ? ' ' : '|';
			mismatchChar = passedZero ? ' ' : 'X';
			switch (dir)
			{
				case NORTH:
					xb.insert(0, gapChar);
					ab.insert(0, gapChar);
					yb.insert(0, localChar(y.charAt(row - 1), passedZero, row, col));
					row--;
					break;
				case WEST:
					xb.insert(0, localChar(x.charAt(col - 1), passedZero, row, col));
					ab.insert(0, gapChar);
					yb.insert(0, gapChar);
					col--;
					break;
				case NORTHWEST:
					char xc = x.charAt(col - 1);
					char yc = y.charAt(row - 1);
					xb.insert(0, xc);
					ab.insert(0, (xc == yc) ? matchChar : mismatchChar);
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
		System.err.printf("Highest value: %.0f (row %d, col %d)%n", highest.score, highest.row, highest.col);
		System.err.flush();
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
	
	public double getValue(int i, int j)
	{
		return a[j][i].score;
	}
	
	private double localScore(double i)
	{
		return (local && i < 0) ? 0 : i;
	}
	
	private char localChar(char c, boolean passedZero, int row, int col)
	{
		return (local && (row > highest.row || col > highest.col || passedZero)) ? Character.toLowerCase(c) : c;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		LicenseUtil.printLicense();
		AlignmentScoringSystem scoring = new AlignmentScoringSystem(0, -1, 2, -1);
		AlignmentCalculator nw1 = new AlignmentCalculator(args[0], args[1], scoring, false);
		nw1.fillScoreArray();
		nw1.printArray();
		nw1.setAlignment();
		nw1.printAlignment();
	}
}
