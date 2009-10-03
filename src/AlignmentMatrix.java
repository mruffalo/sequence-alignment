public class AlignmentMatrix
{
	AlignmentElement[][] table;
	public AlignmentMatrix(int width, int height)
	{
		table = new AlignmentElement[width + 1][];
		for (int i = 0; i <= width; i++)
		{
			table[i] = new AlignmentElement[height + 1];
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
