package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableCellColorRenderer extends DefaultTableCellRenderer implements TableCellRenderer
{
	private static final long serialVersionUID = -8921218438503947521L;
	private static final Color DEFAULT_BACKGROUND = Color.WHITE;
	private final Color specialBackground;
	
	public TableCellColorRenderer(Color background_)
	{
		specialBackground = background_;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
		int row, int column)
	{
		JLabel template = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (row == 0 && column == 0)
		{
			template.setBackground(specialBackground);
		}
		else
		{
			template.setBackground(DEFAULT_BACKGROUND);
		}
		if (value != null)
		{
			template.setText(value.toString());
		}
		else
		{
			template.setText(null);
		}
		return template;
	}
}
