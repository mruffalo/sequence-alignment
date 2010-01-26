/**
 * 
 */
package alignment;

public class AlignmentElement
{
	public Double score;
	public PointerDirection direction;
	
	public AlignmentElement(Double score_)
	{
		score = score_;
	}
	
	public AlignmentElement(Double score_, PointerDirection direction_)
	{
		score = score_;
		direction = direction_;
	}
}
