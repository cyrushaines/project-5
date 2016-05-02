import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Project #4
 * CS 2334, Section 012
 * April 21, 2016
 * This class creates the view for the movie data and contains
 * all associated methods.
 * 
 *@version 1.0
 */
public class MovieView extends JFrame implements ActionListener{
	
	//is this class serializable
	
	
	
	//need model class for this error
	private MediaModel model;
	
	
	private DefaultListModel<MediaCollection> listModel= new DefaultListModel<MediaCollection>();
	
	protected JFrame frame= new JFrame();
	/** creation of the pane*/
	private JOptionPane confirm= new JOptionPane();
	
	protected JTextField titleBox= new JTextField(15);
	
	protected JTextField yearBox = new JTextField(15);
	
	protected JTextField formatBox= new JTextField(15);
	
	private JRadioButton suspendedYesButton= new JRadioButton("Yes");
	
	private JRadioButton suspendedNoButton= new JRadioButton("No");
	
	private ButtonGroup buttons= new ButtonGroup();
	
	private JLabel title= new JLabel();
	
	private JLabel year= new JLabel();
	
	private JLabel format= new JLabel();
	
	private JLabel currentlySuspended= new JLabel();
	
	private GridBagConstraints layoutConst;
	
	private JButton confirmFinal= new JButton("Confirm");
	
	private JButton cancel= new JButton("Cancel");
	
	
	
	
	public MovieView(){
		
		
		
		frame.setSize(400, 250);
		frame.setTitle("Movie Entry"); 
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		title.setText("Title:");
		titleBox.setText("");
		titleBox.setEditable(true);
		
		year.setText("Year:");
		yearBox.setText("");
		yearBox.setEditable(true);
		
		format.setText("Format:");
		formatBox.setText("");
		formatBox.setEditable(true);
		
		currentlySuspended.setText("Currently Suspended?");
		
		frame.setLayout(new GridBagLayout());
		
		//Copied from Zybooks Section 14.3
		
		layoutConst= new GridBagConstraints();
		layoutConst.gridx=0;
		layoutConst.gridy=0;
		layoutConst.insets= new Insets(10, 10, 10, 10);
		frame.add(title, layoutConst);
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=1;
		layoutConst.gridy=0;
		layoutConst.insets= new Insets(10,10,10,10);
		frame.add(titleBox, layoutConst);
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=0;
		layoutConst.gridy=1;
		layoutConst.insets= new Insets(10,10,10,10);
		frame.add(year, layoutConst);
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=1;
		layoutConst.gridy=1;
		layoutConst.insets=new Insets(10,10,10,10);
		frame.add(yearBox, layoutConst);
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=0;
		layoutConst.gridy=2;
		layoutConst.insets= new Insets(10,10,10,10);
		frame.add(format, layoutConst);
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=1;
		layoutConst.gridy=2;
		layoutConst.insets= new Insets(10,10,10,10);
		frame.add(formatBox, layoutConst);
		
		
	
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=0;
		layoutConst.gridy=3;
		layoutConst.insets= new Insets(10,10,10,10);
		frame.add(confirmFinal, layoutConst);
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=1;
		layoutConst.gridy=3;
		layoutConst.insets= new Insets(10,10,10,10);
		frame.add(cancel, layoutConst);
		
		frame.pack();
		frame.setVisible(true);
		
		
		
		
		
	}
	
	public void clearInputFields(){
		titleBox.setText("");
		yearBox.setText("");
		formatBox.setText("");
	}
	
	public void setModel(MediaModel model){
		this.model=model;
		if(this.model != null){
			model.addActionListener(this);
		}
	}
	
	public void errorMessage(){
		//DO STUFF HERE
	}

	
	public void addAddMovieListener(ActionListener addMovieListener){
		confirmFinal.addActionListener(addMovieListener);
		
	}
	
	
	public void addCancelButtonListener(ActionListener cancelListener){
		cancel.addActionListener(cancelListener);
		
	}
	
	
	
	//NOT SURE IF I NEED THIS YET

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		
		if(actionEvent.getActionCommand().equals("Add Movie")){
			// do something?? add a getter
			listModel.clear();
			listModel.addElement(model.getMovies());
			pack();
			setVisible(true);
		}
		
		
	}
	

	
	
}

