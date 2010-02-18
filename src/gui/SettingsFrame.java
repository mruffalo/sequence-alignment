package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettingsFrame extends JFrame
{
	private static final long serialVersionUID = -332845150875621802L;
	private static final String CHOOSE_BUTTON_TEXT = "Choose...";
	private final SequenceAlignmentGui sequenceAlignmentGui;
	private JPanel panel;
	private AlignmentDisplaySettings settings;
	private JLabel highlightColorLabel;
	
	public SettingsFrame(SequenceAlignmentGui sequenceAlignmentGui_)
	{
		super("Alignment Display Settings");
		sequenceAlignmentGui = sequenceAlignmentGui_;
		settings = sequenceAlignmentGui.getSettings().clone();
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		final Insets two = new Insets(2, 2, 2, 2);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = two;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 0.1;
		gbc.weightx = 0.8;
		JLabel highlightColorText = new JLabel("Highlight Color");
		panel.add(highlightColorText, gbc);
		
		gbc.gridx = 1;
		gbc.weightx = 0.2;
		highlightColorLabel = new JLabel();
		highlightColorLabel.setOpaque(true);
		highlightColorLabel.setBackground(settings.highlightColor);
		Dimension sixteen = new Dimension(16, 16);
		highlightColorLabel.setMinimumSize(sixteen);
		highlightColorLabel.setPreferredSize(sixteen);
		panel.add(highlightColorLabel, gbc);
		
		gbc.gridx = 2;
		gbc.fill = GridBagConstraints.NONE;
		JButton backgroundColorButton = new JButton(CHOOSE_BUTTON_TEXT);
		backgroundColorButton.addActionListener(new ChooseColorButtonActionListener());
		panel.add(backgroundColorButton, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(5, 2, 2, 2);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(createOkCancelButtonsPanel(), gbc);
		
		this.add(panel);
		this.setLocationRelativeTo(sequenceAlignmentGui);
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel createOkCancelButtonsPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.add(Box.createHorizontalGlue());
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new OkButtonActionListener());
		panel.add(okButton);
		
		panel.add(Box.createHorizontalStrut(5));
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelButtonActionListener());
		panel.add(cancelButton);
		
		return panel;
	}
	
	private void setColor(Color color)
	{
		settings.highlightColor = color;
		highlightColorLabel.setBackground(color);
	}
	
	private class OkButtonActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			sequenceAlignmentGui.setSettings(settings);
			SettingsFrame.this.dispose();
		}
	}
	
	private class CancelButtonActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			SettingsFrame.this.dispose();
		}
	}
	
	private class ChooseColorButtonActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			String title = "Choose Highlight Color";
			Color newColor = JColorChooser.showDialog(SettingsFrame.this, title, settings.highlightColor);
			if (newColor != null)
			{
				setColor(newColor);
			}
		}
	}
}
