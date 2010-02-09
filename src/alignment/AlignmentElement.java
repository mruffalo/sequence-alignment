/**
 * 
 */
package alignment;

public class AlignmentElement
{
	public final int row;
	public final int col;
	public Double score;
	public PointerDirection direction;
	public boolean isPartOfAlignment = false;
	
	public AlignmentElement(int row_, int col_, Double score_)
	{
		row = row_;
		col = col_;
		score = score_;
	}
}
