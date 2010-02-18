package gui;

import java.awt.Color;

public class AlignmentDisplaySettings implements Cloneable
{
	public Color highlightColor;
	
	public AlignmentDisplaySettings()
	{
		highlightColor = Color.GREEN;
	}
	
	@Override
	public AlignmentDisplaySettings clone()
	{
		try
		{
			return (AlignmentDisplaySettings) super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
