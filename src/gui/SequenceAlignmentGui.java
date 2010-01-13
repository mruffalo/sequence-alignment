package gui;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.table.JTableHeader;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;

public class SequenceAlignmentGui extends JFrame
{
	private JTextField sequence1;
	private JTextField sequence2;
	private JSpinner matchSpinner;
	private JSpinner mismatchSpinner;
	private JSpinner gapStartSpinner;
	private JSpinner gapContinueSpinner;
	private JRadioButton nwButton;
	private JRadioButton swButton;
	private ButtonGroup alignmentTypeButtonGroup;
	
	public SequenceAlignmentGui()
	{
		setTitle("Sequence Alignment");
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
		gbc.weighty = 0.7;
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
		
		sequence1 = new JTextField("");
		panel.add(sequence1);
		
		JLabel seq2Label = new JLabel("Sequence 2");
		panel.add(seq2Label);
		
		panel.add(Box.createRigidArea(new Dimension(0, 4)));
		
		sequence2 = new JTextField("");
		panel.add(sequence2);
		
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
		gapStartModel.setValue(-2);
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
		gapStartModel.setValue(-2);
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
		nwButton = new JRadioButton();
		nwButton.setSelected(true);
		alignmentTypeButtonGroup.add(nwButton);
		panel.add(nwButton);
		
		JLabel nwLabel = new JLabel("Needleman-Wunch");
		panel.add(nwLabel);
		
		swButton = new JRadioButton();
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
				ButtonModel o = alignmentTypeButtonGroup.getSelection();
				for (Object o_ : o.getSelectedObjects())
				{
					System.out.println(o_);
				}
				System.out.println(o);
			}
		});
		panel.add(swLabel);
		
		panel.add(Box.createHorizontalGlue());
		return panel;
	}
	
	private JComponent getTable()
	{
		JTable table = new JTable();
		return table;
	}
	
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
			return string.substring(index, index + 1);
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
		new SequenceAlignmentGui();
	}
}
