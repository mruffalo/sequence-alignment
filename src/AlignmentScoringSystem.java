public class AlignmentScoringSystem
{
	public final int gapStart;
	public final int gapContinue;
	public final int match;
	public final int mismatch;
	public AlignmentScoringSystem(int gapStart_, int gapContinue_, int match_, int mismatch_)
	{
		gapStart = gapStart_;
		gapContinue = gapContinue_;
		match = match_;
		mismatch = mismatch_;
	}
}
