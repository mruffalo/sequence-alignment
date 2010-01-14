/**
 * 
 */
package alignment;

public class AlignmentElement
{
	public Integer score;
	public PointerDirection direction;
	
	public AlignmentElement(Integer score_)
	{
		score = score_;
	}
	
	public AlignmentElement(Integer score_, PointerDirection direction_)
	{
		score = score_;
		direction = direction_;
	}
}
