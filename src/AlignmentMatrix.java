public class AlignmentMatrix
{
	private String top;
	private String left;
	private AlignmentElement[][] table;
	private AlignmentScoringSystem scoringSystem;
	public AlignmentMatrix(AlignmentScoringSystem scoringSystem_, String top_, String left_)
	{
		scoringSystem = scoringSystem_;
		top = top_;
		left = left_;
		table = new AlignmentElement[top.length() + 1][];
		for (int i = 0; i <= top.length(); i++)
		{
			table[i] = new AlignmentElement[left.length() + 1];
		}
		fillTable();
	}
	private void fillTable()
	{
		table[0][0] = new AlignmentElement(0, null);
		table[1][0] = new AlignmentElement(scoringSystem.gapStart + scoringSystem.gapContinue, PointerDirection.LEFT);
		for (int i = 2; i <= top.length(); i++)
		{
			table[i][0] = new AlignmentElement(table[i - 1][0].score + scoringSystem.gapContinue, PointerDirection.LEFT);
		}
		table[0][1] = new AlignmentElement(scoringSystem.gapStart + scoringSystem.gapContinue, PointerDirection.UP);
		for (int j = 2; j <= left.length(); j++)
		{
			table[0][j] = new AlignmentElement(table[0][j - 1].score + scoringSystem.gapContinue, PointerDirection.UP);
		}
	}
	private class AlignmentElement
	{
		public final int score;
		public final PointerDirection direction;
		public AlignmentElement(int score_, PointerDirection direction_)
		{
			score = score_;
			direction = direction_;
		}
	}
}
