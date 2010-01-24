package gui;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class JTextFieldUC extends JTextField
{
	private static final long serialVersionUID = -1518406484669619448L;
	
	public JTextFieldUC(String s, int col)
	{
		super(s, col);
	}
	
	public JTextFieldUC()
	{
		this(null, 0);
	}
	
	public JTextFieldUC(String s)
	{
		this(s, s.length());
	}
	
	public JTextFieldUC(int col)
	{
		this(null, col);
	}
	
	protected void processKeyEvent(KeyEvent e)
	{
		char ch = e.getKeyChar();
		if (Character.isLetter(ch))
		{
			e.setKeyChar(Character.toUpperCase(ch));
		}
		super.processKeyEvent(e);
	}
}
