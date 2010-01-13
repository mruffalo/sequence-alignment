package gui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;

public class SequenceAlignmentGui extends JFrame
{
	/**
	 * Generated by Eclipse
	 */
	private static final long serialVersionUID = 9035278001459282431L;
	
	private JTextField sequenceField1;
	private JTextField sequenceFiled2;
	private JSpinner matchSpinner;
	private JSpinner mismatchSpinner;
	private JSpinner gapStartSpinner;
	private JSpinner gapContinueSpinner;
	private ButtonModel nwButtonModel;
	private ButtonModel swButtonModel;
	private ButtonGroup alignmentTypeButtonGroup;
	private AlignmentTableModel tableModel;
	private JTable table;
	private String sequence1 = "ACTAGCATA";
	private String sequence2 = "TTACCGA";
	private JList rowHeader;
	
	public SequenceAlignmentGui()
	{
		super("Sequence Alignment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		Insets two = new Insets(2, 2, 2, 2);
		gbc.insets = two;
		gbc.weightx = 0.4;
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(getSequencesPanel(), gbc);
		
		gbc.gridx = 1;
		gbc.weightx = 0.6;
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(getScoringPanel(), gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 2;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		panel.add(getSettingsPanel(), gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 2;
		gbc.gridy = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = two;
		panel.add(getTable(), gbc);
		
		gbc = new GridBagConstraints();
		this.add(panel);
		this.pack();
		this.setVisible(true);
	}
	
	private JComponent getSequencesPanel()
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Sequences"));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		panel.add(Box.createRigidArea(new Dimension(0, 4)));
		
		JLabel seq1Label = new JLabel("Sequence 1");
		panel.add(seq1Label);
		
		panel.add(Box.createRigidArea(new Dimension(0, 4)));
		
		sequenceField1 = new JTextField(sequence1);
		panel.add(sequenceField1);
		
		JLabel seq2Label = new JLabel("Sequence 2");
		panel.add(seq2Label);
		
		panel.add(Box.createRigidArea(new Dimension(0, 4)));
		
		sequenceFiled2 = new JTextField(sequence2);
		panel.add(sequenceFiled2);
		
		return panel;
	}
	
	private JComponent getScoringPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Scoring"));
		
		Insets two = new Insets(2, 2, 2, 2);
		
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel matchLabel = new JLabel("Match");
		gbc.insets = two;
		gbc.weightx = 0.4;
		gbc.fill = GridBagConstraints.BOTH;
		matchLabel.setMinimumSize(new Dimension(150, 0));
		panel.add(matchLabel, gbc);
		
		SpinnerNumberModel matchModel = new SpinnerNumberModel();
		matchModel.setValue(2);
		matchSpinner = new JSpinner(matchModel);
		matchSpinner.setMinimumSize(new Dimension(50, 0));
		gbc.gridx = 1;
		gbc.weightx = 0.6;
		panel.add(matchSpinner, gbc);
		
		gbc = new GridBagConstraints();
		JLabel mismatchLabel = new JLabel("Mismatch");
		gbc.gridy = 1;
		gbc.insets = two;
		gbc.weightx = 0.4;
		gbc.fill = GridBagConstraints.BOTH;
		mismatchLabel.setMinimumSize(new Dimension(150, 0));
		panel.add(mismatchLabel, gbc);
		
		SpinnerNumberModel mismatchModel = new SpinnerNumberModel();
		mismatchModel.setValue(-1);
		mismatchSpinner = new JSpinner(mismatchModel);
		mismatchSpinner.setMinimumSize(new Dimension(50, 0));
		gbc.gridx = 1;
		gbc.weightx = 0.6;
		panel.add(mismatchSpinner, gbc);
		
		gbc = new GridBagConstraints();
		JLabel gapStartLabel = new JLabel("Gap Start");
		gbc.gridy = 2;
		gbc.insets = two;
		gbc.weightx = 0.4;
		gbc.fill = GridBagConstraints.BOTH;
		gapStartLabel.setMinimumSize(new Dimension(150, 0));
		panel.add(gapStartLabel, gbc);
		
		SpinnerNumberModel gapStartModel = new SpinnerNumberModel();
		gapStartModel.setValue(0);
		gapStartSpinner = new JSpinner(gapStartModel);
		gapStartSpinner.setMinimumSize(new Dimension(50, 0));
		gbc.gridx = 1;
		gbc.weightx = 0.6;
		panel.add(gapStartSpinner, gbc);
		gbc = new GridBagConstraints();
		
		JLabel gapContinueLabel = new JLabel("Gap Continue");
		gbc.gridy = 3;
		gbc.insets = two;
		gbc.weightx = 0.4;
		gbc.fill = GridBagConstraints.BOTH;
		gapContinueLabel.setMinimumSize(new Dimension(150, 0));
		panel.add(gapContinueLabel, gbc);
		
		SpinnerNumberModel gapContinueModel = new SpinnerNumberModel();
		gapContinueModel.setValue(-2);
		gapContinueSpinner = new JSpinner(gapContinueModel);
		gapContinueSpinner.setMinimumSize(new Dimension(50, 0));
		gbc.gridx = 1;
		gbc.weightx = 0.6;
		panel.add(gapContinueSpinner, gbc);
		
		return panel;
	}
	
	private JComponent getSettingsPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.add(Box.createHorizontalGlue());
		
		alignmentTypeButtonGroup = new ButtonGroup();
		JRadioButton nwButton = new JRadioButton();
		nwButtonModel = nwButton.getModel();
		nwButton.setSelected(true);
		alignmentTypeButtonGroup.add(nwButton);
		panel.add(nwButton);
		
		JLabel nwLabel = new JLabel("Needleman-Wunch");
		nwLabel.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0)
			{
			}
			
			@Override
			public void mouseExited(MouseEvent arg0)
			{
			}
			
			@Override
			public void mousePressed(MouseEvent arg0)
			{
			}
			
			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				nwButtonModel.setSelected(true);
			}
		});
		panel.add(nwLabel);
		
		JRadioButton swButton = new JRadioButton();
		swButtonModel = swButton.getModel();
		alignmentTypeButtonGroup.add(swButton);
		panel.add(swButton);
		
		JLabel swLabel = new JLabel("Smith-Waterman");
		swLabel.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0)
			{
			}
			
			@Override
			public void mouseExited(MouseEvent arg0)
			{
			}
			
			@Override
			public void mousePressed(MouseEvent arg0)
			{
			}
			
			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				swButtonModel.setSelected(true);
			}
		});
		panel.add(swLabel);
		
		panel.add(Box.createHorizontalGlue());
		panel.add(Box.createRigidArea(new Dimension(20, 0)));
		JButton alignButton = new JButton("Align");
		alignButton.addActionListener(new AlignButtonActionListener(this));
		panel.add(alignButton);
		return panel;
	}
	
	private JComponent getTable()
	{
		tableModel = new AlignmentTableModel();
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		rowHeader = new JList(new Sequence2ListModel());
		rowHeader.setFixedCellWidth(25);
		rowHeader.setFixedCellHeight(table.getRowHeight());
		rowHeader.setCellRenderer(new RowHeaderRenderer(table));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setRowHeaderView(rowHeader);
		
		return scrollPane;
	}
	
	/**
	 * TODO: Finish this
	 */
	void alignStrings()
	{
		sequence1 = sequenceField1.getText();
		sequence2 = sequenceFiled2.getText();
		
		if (alignmentTypeButtonGroup.getSelection() == nwButtonModel)
		{
			System.out.println("Needleman-Wunch alignment selected");
		}
		else if (alignmentTypeButtonGroup.getSelection() == swButtonModel)
		{
			System.out.println("Smith-Waterman alignment selected");
		}
		
		rowHeader.repaint();
		tableModel.fireTableStructureChanged();
	}
	
	private class AlignmentTableModel extends AbstractTableModel
	{
		/**
		 * Generated by Eclipse
		 */
		private static final long serialVersionUID = 4053646991610823547L;
		
		@Override
		public int getColumnCount()
		{
			return sequence1.length() + 1;
		}
		
		@Override
		public int getRowCount()
		{
			return sequence2.length() + 1;
		}
		
		@Override
		public Object getValueAt(int arg0, int arg1)
		{
			// TODO this
			return "?";
		}
		
		@Override
		public String getColumnName(int columnIndex)
		{
			if (columnIndex == 0)
			{
				return "";
			}
			else
			{
				return sequence1.substring(columnIndex - 1, columnIndex);
			}
		}
	}
	
	private static class AlignButtonActionListener implements ActionListener
	{
		private SequenceAlignmentGui gui;
		
		public AlignButtonActionListener(SequenceAlignmentGui gui_)
		{
			gui = gui_;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			gui.alignStrings();
		}
	}
	
	/**
	 * This list model is used to display the row header, so it directly accesses sequence2.
	 * 
	 * @author mruffalo
	 */
	private class Sequence2ListModel extends AbstractListModel
	{
		/**
		 * Generated by Eclipse
		 */
		private static final long serialVersionUID = 4264549340186682194L;
		
		@Override
		public Object getElementAt(int index)
		{
			if (index == 0)
			{
				return "";
			}
			else
			{
				return sequence2.substring(index - 1, index);
			}
		}
		
		@Override
		public int getSize()
		{
			return sequence2.length() + 1;
		}
	}
	
	private static class RowHeaderRenderer extends JLabel implements ListCellRenderer
	{
		/**
		 * Generated by Eclipse
		 */
		private static final long serialVersionUID = 4530071154550302421L;
		
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
		new SequenceAlignmentGui();
	}
}
