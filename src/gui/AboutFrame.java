package gui;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import utils.VersionInfo;

public class AboutFrame extends JFrame
{
	private static final long serialVersionUID = -7549340270773164500L;
	
	public static final String GITHUB_URL = "http://github.com/mruffalo/sequence-alignment/";
	
	private final SequenceAlignmentGui sequenceAlignmentGui;
	private JPanel panel;
	
	public AboutFrame(SequenceAlignmentGui sequenceAlignmentGui_)
	{
		super("About");
		sequenceAlignmentGui = sequenceAlignmentGui_;
		
		panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(new JLabel("Sequence Alignment GUI"));
		panel.add(new JLabel(String.format("Version: %s", VersionInfo.SOFTWARE_VERSION)));
		panel.add(new JLabel("Author: Matthew Ruffalo <matthew.ruffalo@case.edu>"));
		panel.add(new JLabel("GitHub project page:"));
		JTextField github = new JTextField(GITHUB_URL);
		github.setEditable(false);
		panel.add(github);
		this.add(panel);
		
		this.setLocationRelativeTo(sequenceAlignmentGui);
		this.pack();
		this.setVisible(true);
	}
}
