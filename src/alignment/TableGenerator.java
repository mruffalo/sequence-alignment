package alignment;
public class TableGenerator
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		AlignmentScoringSystem system = new AlignmentScoringSystem(0, -2, 2, -1);
		AlignmentMatrix matrix = new AlignmentMatrix(system, "GAACTG", "GATAGCAATG");
		System.out.println(matrix);
	}
}
