import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Project #4
 * CS 2334, Section 012
 * APR 21, 2016
 * <P>
 * This class is the viewer for the series data and contains
 * all associated methods.
 *   
 * </P>
 * @version 1.0
 */
public class SeriesView extends JFrame implements ActionListener {

	private static final long serialVersionUID= 1L;
	
	private MediaModel model;
	
	protected JFrame frame= new JFrame();
	/**Creation of the pane*/
	private JOptionPane confirm = new JOptionPane();
	
	protected JTextField titleBox= new JTextField(15);
	
	protected JTextField startYear= new JTextField(15);
	
	protected JTextField endYear= new JTextField(15);
	
	private JButton confirmFinal= new JButton("Confirm");
	
	private JButton cancel= new JButton("Cancel");
	
	private JRadioButton suspendedYes= new JRadioButton("Yes");
	
	private JRadioButton suspendedNo= new JRadioButton("No");
	
	private ButtonGroup buttons= new ButtonGroup();
	
	private JLabel currentlySuspended= new JLabel("Currently Suspended?:");
	
	protected JLabel title= new JLabel();
	
	private JLabel startYr= new JLabel();
	
	private JLabel endYr= new JLabel();
	
	private GridBagConstraints layoutConst;
	
	public SeriesView(){
		
		frame.setSize(400, 250);
		frame.setTitle("Series Entry");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		title.setText("Title:");
		titleBox.setText("");
		titleBox.setEditable(true);
		
		startYr.setText("Start Year");
		startYear.setText("");
		startYear.setEditable(true);
		
		endYr.setText("End Year");
		endYear.setText("");
		endYear.setEditable(true);
		
		frame.setLayout(new GridBagLayout());
		
		//Copied from Zybooks Section 14.3
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=0;
		layoutConst.gridy=0;
		layoutConst.insets= new Insets(10,10,10,10);
		frame.add(title, layoutConst );
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=1;
		layoutConst.gridy=0;
		layoutConst.insets= new Insets(10,10,10,10);
		frame.add(titleBox, layoutConst);
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=0;
		layoutConst.gridy=1;
		layoutConst.insets= new Insets(10,10,10,10);
		frame.add(startYr, layoutConst);
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=1;
		layoutConst.gridy=1;
		layoutConst.insets= new Insets(10,10,10,10);
		frame.add(startYear, layoutConst);
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=0;
		layoutConst.gridy=2; 
		layoutConst.insets= new Insets(10,10,10,10);
		frame.add(endYr, layoutConst);
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=1;
		layoutConst.gridy=2;
		layoutConst.insets= new Insets(10,10,10,10);
		frame.add(endYear, layoutConst);
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=0;
		layoutConst.gridy=3;
		layoutConst.insets= new Insets(10,10,10,10);
		frame.add(currentlySuspended, layoutConst);
		
		buttons.add(suspendedYes);
		buttons.add(suspendedNo);
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=1;
		layoutConst.gridy=3;
		layoutConst.insets= new Insets(10,10,10,10);
		frame.add(suspendedYes, layoutConst);
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=2;
		layoutConst.gridy=3;
		layoutConst.insets=new Insets(10,10,10,10);
		frame.add(suspendedNo, layoutConst);
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=0;
		layoutConst.gridy=4;
		layoutConst.insets= new Insets(10,10,10,10);
		frame.add(confirmFinal, layoutConst);
		
		layoutConst= new GridBagConstraints();
		
		layoutConst.gridx=1;
		layoutConst.gridy=4;
		layoutConst.insets= new Insets(10,10,10,10);
		frame.add(cancel, layoutConst);
		
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	public void clearInputFields(){
		titleBox.setText("");
		startYear.setText("");
		endYear.setText("");
	}
	
	
	
	public void addAddSeriesListener(ActionListener addSeriesListener){
		//DO STUFF
		confirmFinal.addActionListener(addSeriesListener);
	}
	
	public void addCancelButtonListener(ActionListener cancelListener){
		//DO STUFF
		cancel.addActionListener(cancelListener);
		
		
	}
	
	public void setModel(MediaModel model){
		this.model=model;
		//populate some list?
		if (this.model != null){
			model.addActionListener(this);
		}
	}
	
	
	
	
	
	
	//not sure about this. get Rid of override?
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if(actionEvent.getActionCommand().equals("Add Series")){
			//specific to this class model? get selected series?
			pack();
			setVisible(true);
		}
		
		//maybe show the list of movies added
		
	}

}

