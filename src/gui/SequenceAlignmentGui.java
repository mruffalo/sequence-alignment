package gui;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.table.JTableHeader;
import java.awt.Component;
import java.awt.event.*;

public class SequenceAlignmentGui
{
	private JFrame frame;
	
	private static class StringListModel extends AbstractListModel
	{
		private String string;
		
		public StringListModel(String string_)
		{
			string = string_;
		}
		
		public void setString(String string_)
		{
			string = string_;
		}
		
		@Override
		public Object getElementAt(int index)
		{
			return string.substring(index, index);
		}
		
		@Override
		public int getSize()
		{
			return string.length();
		}
	}
	
	private static class RowHeaderRenderer extends JLabel implements ListCellRenderer
	{
		public RowHeaderRenderer(JTable table)
		{
			JTableHeader header = table.getTableHeader();
			setOpaque(true);
			setBorder(UIManager.getBorder("TableHeader.cellBorder"));
			setHorizontalAlignment(CENTER);
			setForeground(header.getForeground());
			setBackground(header.getBackground());
			setFont(header.getFont());
		}
		
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus)
		{
			setText((value == null) ? "" : value.toString());
			return this;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
	}
}
