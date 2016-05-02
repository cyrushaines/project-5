import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
 * This class creates the view and all the text.
 * </P>
 * @version 1.0
 */
public class EpisodeAddView extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -290460102537650930L;
	
	private MediaModel model;

	protected JFrame frame = new JFrame();
	
	protected JComboBox<String> selectSeries = new JComboBox<String>();

	protected JTextField titleBox = new JTextField(15);

	protected JTextField yearBox=new JTextField(15);

	protected JTextField seasonBox=new JTextField(15);

	protected JTextField episodeBox=new JTextField(15);

	private JRadioButton suspendedYesButton = new JRadioButton("Yes");
	
	private ButtonGroup selectOneButton = new ButtonGroup();
	
	private JLabel series = new JLabel();

	private JLabel title = new JLabel();

	private JLabel year= new JLabel();

	private JLabel season= new JLabel();

	private JLabel episode= new JLabel();

	private JLabel suspended= new JLabel();
	
	private JButton confirm = new JButton("Confirm");
	
	private JButton cancel = new JButton("Cancel");

	private GridBagConstraints layoutConst;
	
	protected boolean isSuspended = false;


	public EpisodeAddView(MediaModel model){
		//empty for now
/*
		MediaCollection serDB = model.getSeries();
		
		ArrayList<Media> seriesList = serDB.getMedia();
		for(int index = 0; index < seriesList.size(); ++index){
			String tempTitle = seriesList.get(index).getName();
			selectSeries.addItem(tempTitle);
		}*/
		
		selectSeries.addItem("Debugging1");
		selectSeries.addItem("Debugging2");
		selectSeries.addItem("Debugging3");
		
		frame.setSize(400, 250);

		frame.setTitle("Episode: Add Entry");

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		series.setText("Select Series");
		//TODO: need to figure out how to make the model work within this individual view.
		
	
		//ArrayList<Media> listOfMedia = model.getSeries().getMedia();
		//ArrayList

		title.setText("Title");
		titleBox.setText("");
		titleBox.setEditable(true);

		year.setText("Year");
		yearBox.setText("");
		yearBox.setEditable(true);

		season.setText("Season");
		seasonBox.setText("");
		seasonBox.setEditable(true);

		episode.setText("Episode #");
		episodeBox.setText("");
		episodeBox.setEditable(true);

		suspended.setText("Confirm Suspended");

		frame.setLayout(new GridBagLayout());

		//Copied from Zybooks Section 14.3
		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 0;

		layoutConst.gridy = 0;

		layoutConst.insets = new Insets(10, 10, 10, 10);

		frame.add(title, layoutConst);

		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 1;

		layoutConst.gridy = 0;

		layoutConst.insets = new Insets(10, 10, 10, 10);

		frame.add(titleBox, layoutConst);

		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 0;

		layoutConst.gridy = 1;

		layoutConst.insets = new Insets(10, 10, 10, 10);

		frame.add(year, layoutConst);

		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 1;

		layoutConst.gridy = 1;

		layoutConst.insets = new Insets(10, 10, 10, 10);

		frame.add(yearBox, layoutConst);

		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 0;

		layoutConst.gridy = 2;

		layoutConst.insets = new Insets(10, 10, 10, 10);

		frame.add(season, layoutConst);

		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 1;

		layoutConst.gridy = 2;

		layoutConst.insets = new Insets(10, 10, 10, 10);

		frame.add(seasonBox, layoutConst);

		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 0;

		layoutConst.gridy = 3;

		layoutConst.insets = new Insets(10, 10, 10, 10);

		frame.add(episode, layoutConst);

		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 1;

		layoutConst.gridy = 3;

		layoutConst.insets = new Insets(10, 10, 10, 10);

		frame.add(episodeBox, layoutConst);
		
		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 0;

		layoutConst.gridy = 4;

		layoutConst.insets = new Insets(10, 10, 10, 10);

		frame.add(series, layoutConst);

		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 1;

		layoutConst.gridy = 4;

		layoutConst.insets = new Insets(10, 10, 10, 10);

		frame.add(selectSeries, layoutConst);
		
		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 0;

		layoutConst.gridy = 5;

		layoutConst.insets = new Insets(10, 10, 10, 10);

		frame.add(suspended, layoutConst);
		
		selectOneButton.add(suspendedYesButton);
		
		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 1;

		layoutConst.gridy = 5;

		layoutConst.insets = new Insets(10, 10, 10, 10);

		frame.add(suspendedYesButton, layoutConst);
		
		if(suspendedYesButton.isSelected()){
			isSuspended = true;
		}
		
		
		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 0;

		layoutConst.gridy = 6;

		layoutConst.insets = new Insets(10, 10, 10, 10);

		frame.add(confirm, layoutConst);
		
		layoutConst = new GridBagConstraints();

		layoutConst.gridx = 1;

		layoutConst.gridy = 6;

		layoutConst.insets = new Insets(10, 10, 10, 10);

		frame.add(cancel, layoutConst);
		
		frame.pack();

		frame.setVisible(true);


	}
	
	
	/**
	 * Adds a listener for an ConfirmButton
	 * 
	 * @param l			An ActionListener object
	 */
	public void addAddEpisodeButtonListener(ActionListener AddEpisodeButtonListener)
	{
		//TODO: fill in method
		
		confirm.addActionListener(AddEpisodeButtonListener);
	}
	
	/**
	 * Adds a listener for an CancelButton
	 * 
	 * @param l			An ActionListener object
	 */
	public void addCancelButtonListener(ActionListener CancelButtonListener)
	{
		//TODO: fill in method
		cancel.addActionListener(CancelButtonListener);
	}
	
	public void setModel(MediaModel model){
		this.model=model;
		//populate some list?
		if (this.model != null){
			model.addActionListener(this);
		}
	}
	public void clearInputFields(){
		titleBox.setText("");
		yearBox.setText("");
		seasonBox.setText("");
		episodeBox.setText("");
		suspendedYesButton.setSelected(false);
	
	}
	

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO Auto-generated method stub
		if(actionEvent.getActionCommand().equals("Add Episode")){
			pack();
			setVisible(true);
		}
	}

}
