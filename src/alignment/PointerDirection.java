package alignment;

public enum PointerDirection
{
	NORTH("N"),
	WEST("W"),
	NORTHWEST("NW");
	private final String shortDescription;
	
	private PointerDirection(String shortDescription_)
	{
		shortDescription = shortDescription_;
	}
	
	public String getShortDescription()
	{
		return shortDescription;
	}
}
