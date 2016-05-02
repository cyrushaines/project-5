import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
/**
 * Project #4
 * CS 2334, Section 012
 * April 21, 2016
 * This class creates the view for the actor data and contains
 * all associated methods.
 * 
 *@version 1.0
 */
public class ActorView extends JFrame implements Serializable
{
	/**	 */
	private static final long serialVersionUID = -8729546098734216666L;
	/** Stores a model */
	private MediaModel model;
	/** Stores a JPanel */
	protected JPanel actorPanel;
	
	/** Stores a first name prompt label */
	private JLabel firstNameLabel;
	/** Stores a text field for entering data */
	protected JTextField firstNameField;
	
	/** Stores a last name prompt label */
	private JLabel lastNameLabel;
	/** Stores a text field for entering data */
	protected JTextField lastNameField;
	
	/** Stores a media prompt label */
	private JLabel mediaLabel1;
	/**  */
	private JLabel mediaLabel2;
	/** Stores a scrolling pane that will store a text area */
	private JScrollPane movieScrollPane;
	/** Stores a text area */
	protected JList movieList;
	/**  */
	private JScrollPane episodeScrollPane;
	/** */
	protected JList episodeList;
	
	/** Stores a button for adding movies */
	private JButton addMovieButton;
	/** Stores a button for adding episodes */
	private JButton addEpisodeButton;
	/** Stores a button for confirming actions */
	private JButton confirmButton;
	/** Stores a button for canceling actions */
	private JButton cancelButton;
	
	GridBagConstraints layoutConst;
	
	/**
	 * Constructor for ActorView
	 */
	public ActorView()
	{
		//Gets the dimensions of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//Stores a fraction of the width of the screen
		double width = screenSize.getWidth()/3;
		//Stores a fraction of the height of the screen
		double height = screenSize.getHeight()/2;
		
		//Sets the title of this viewing window
		this.setTitle("Actor");
		//Sets the dimensions of this viewing window
		this.setSize((int) width, (int) height);
		//Sets the window to exit when closed
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		actorPanel = new JPanel();
		
		firstNameLabel = new JLabel("First Name: ");
		//Initializes field to 20 columns wide (whatever that means)
		firstNameField = new JTextField(20);
		
		lastNameLabel = new JLabel("Last Name: ");
		//Initializes field to 20 columns wide
		lastNameField = new JTextField(20);
		
		mediaLabel1 = new JLabel("Select any movies actor has been in:");
		mediaLabel2 = new JLabel("Select any episodes actor has been in:");
		
		//Initializes the list area for diplaying movie titles
		movieList = new JList();										//model.getMovies().getMedia().toArray());
		//Allows for multiple elements to be selected simultaneously anywhere in the JList
		movieList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//Sets the elements to appear in only one column in the JList
		movieList.setLayoutOrientation(JList.VERTICAL);
		//Initializes a JScrollPane with a JList and sets the scroll bars to appear as needed
		movieScrollPane = new JScrollPane(movieList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//Sets the dimensions of the scroll pane
		movieScrollPane.setPreferredSize(new Dimension(200, 200));
		
		//Initializes the list area for displaying episode titles
		episodeList = new JList();										//model.getEpisodes().getMedia().toArray());
		//Allows for multiple elements to be selected simultaneously anywhere in the JList
		episodeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//Sets the elements to appear in only one column in the JList
		movieList.setLayoutOrientation(JList.VERTICAL);
		//Initializes a scroll pane with a JList and sets the scroll bars to appear as needed
		episodeScrollPane = new JScrollPane(episodeList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//Sets the dimensions of the scroll pane
		episodeScrollPane.setPreferredSize(new Dimension(200, 200));
		
		
		addMovieButton = new JButton("Add Movie");
		addEpisodeButton = new JButton("Add Episode");
		confirmButton = new JButton("Confirm");
		cancelButton = new JButton("Cancel");
		
		//Sets a GridBagLayout as the layout for the panel 
		actorPanel.setLayout(new GridBagLayout());
		
		//Creates a new GridBagConstraints
		layoutConst = new GridBagConstraints();
		//Sets the padding to 10 pixels above and below the item and 0 left and right
		layoutConst.insets = new Insets(10, 0, 10, 0);
		
		//Sets the grid position to the 0th column
		layoutConst.gridx = 0;
		//Sets the grid position to the 0th row
		layoutConst.gridy = 0;
		//Positions the item in this grid area to the right
		layoutConst.anchor = GridBagConstraints.EAST;
		//Adds the firstNameLabel component to the panel with the layout constraints
		actorPanel.add(firstNameLabel, layoutConst);
		
		//Sets the grid position to the 1st column
		layoutConst.gridx = 1;
		//Sets the grid position to the 0th row
		layoutConst.gridy = 0;
		//Positions the item in the grid area to the left
		layoutConst.anchor = GridBagConstraints.WEST;
		//adds the firstNameField component to the panel with the layout constraints
		actorPanel.add(firstNameField, layoutConst);
		
		//Sets the grid position to the 0th column
		layoutConst.gridx = 0;
		//Sets the grid position to the 1st row
		layoutConst.gridy = 1;
		//Positions the item in this grid area to the right
		layoutConst.anchor = GridBagConstraints.EAST;
		//adds the lastNameLabel component to the panel with the layout constraints
		actorPanel.add(lastNameLabel, layoutConst);
		
		//Sets the grid position to the 1st column
		layoutConst.gridx = 1;
		//Sets the grid position to the 1st row
		layoutConst.gridy = 1;
		//Positions the item in the grid area to the left
		layoutConst.anchor = GridBagConstraints.WEST;
		//adds the lastNameField component to the panel with the layout constraints
		actorPanel.add(lastNameField, layoutConst);
		
		//Sets the grid position to the 0th column
		layoutConst.gridx = 0;
		//Sets the grid position to the 2nd row
		layoutConst.gridy = 2;
		//Sets the padding to 10 pixels on all four sides
		layoutConst.insets = new Insets(10, 10, 10, 10);
		//Positions the item in this grid area to the right
		layoutConst.anchor = GridBagConstraints.EAST;
		//adds the mediaLabel1 component to the panel with the layout constraints
		actorPanel.add(mediaLabel1, layoutConst);
		
		//Sets the grid position to the 1st column while staying in the 2nd row
		layoutConst.gridx = 1;
		//adds the mediaLabel1 component to the panel with the layout constraints
		actorPanel.add(mediaLabel2, layoutConst);
		
		//Sets the grid position to the 0th column
		layoutConst.gridx = 0;
		//Sets the grid position to the 3rd row
		layoutConst.gridy = 3;
		//Sets the item to fill the grid area both horizontally and vertically
		layoutConst.fill = GridBagConstraints.BOTH;
		//Sets the item to take up 3 rows of grid space in its column
		layoutConst.gridheight = 3;
		//adds the movieScrollPane component to the panel with the layout constraints
		actorPanel.add(movieScrollPane, layoutConst);
		
		//Sets the grid position to the 1st column while staying in the 3rd row
		layoutConst.gridx = 1;
		//adds the episodeScrollPane component to the panel with the layout constraints
		actorPanel.add(episodeScrollPane, layoutConst);
		
		//Sets the grid position to the 0th column
		layoutConst.gridx = 0;
		//Sets the grid position to the 6th row
		layoutConst.gridy = 6;
		//Sets the item to take up 1 row of grid space in its column
		layoutConst.gridheight = 1;
		//Sets the item to fill the grid area horizontally
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		//adds the addMovieButton component to the panel with the layout constraints
		actorPanel.add(addMovieButton, layoutConst);
		
		//Sets the grid position to the 1st column
		layoutConst.gridx = 1;
		//Sets the grid position to the 6th row
		layoutConst.gridy = 6;
		//adds the addEpisodeButton component to the panel with the layout constraints
		actorPanel.add(addEpisodeButton, layoutConst);

		//Sets the grid position to the 0th column
		layoutConst.gridx = 0;
		//Sets the grid position to the 7th row
		layoutConst.gridy = 7;
		//Positions the item in this grid area to the right
		layoutConst.anchor = GridBagConstraints.EAST;
		//adds the confirmButton component to the panel with the layout constraints
		actorPanel.add(confirmButton, layoutConst);
		
		//Sets the grid position to the 1st column while staying in the 7th row
		layoutConst.gridx = 1;
		//Positions the item in the grid area to the left
		layoutConst.anchor = GridBagConstraints.WEST;
		//adds the cancelButton component to the panel with the layout constraints
		actorPanel.add(cancelButton, layoutConst);
		
		//Adds the panel component to this JFrame
		this.add(actorPanel);
		
		//Packs the frame, i.e. removes extra empty space
		this.pack();
		
		//Makes this view visible
		this.setVisible(true);
		
	}
	
	/**
	 * Updates the current model
	 * 
	 * @param m		A MediaModel object
	 */
	public void setModel(MediaModel m)
	{
		this.model = m;
	}
	
	/**
	 * Accessor for the current model
	 * 
	 * @return		The current model in this object
	 */
	public MediaModel getModel()
	{
		return model;
	}
	
	/**
	 * 
	 */
	public void clearInputFields()
	{
		firstNameField.setText("");
		lastNameField.setText("");
	}
	

	/**
	 * Adds a listener for an addMovieButton
	 * 
	 * @param l			An ActionListener object
	 */
	public void addAddMovieButtonListener(ActionListener l)
	{
		addMovieButton.setActionCommand("Add Movie");
		addMovieButton.addActionListener(l);
	}
	
	/**
	 * Adds a listener for an addEpisodeButton
	 * 
	 * @param l			An ActionListener object
	 */
	public void addAddEpisodeButtonListener(ActionListener l)
	{
		addEpisodeButton.setActionCommand("Add Episode");
		addEpisodeButton.addActionListener(l);
	}
	
	/**
	 * Adds a listener for an ConfirmButton
	 * 
	 * @param l			An ActionListener object
	 */
	public void addConfirmButtonListener(ActionListener l)
	{
		confirmButton.setActionCommand("Confirm");
		confirmButton.addActionListener(l);
	}
	
	/**
	 * Adds a listener for an CancelButton
	 * 
	 * @param l			An ActionListener object
	 */
	public void addCancelButtonListener(ActionListener l)
	{
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(l);
	}
	
	/**
	 * Responds to a particular action performed by an ActionEvent
	 * 
	 * @param e			An ActionEvent
	 */
	public void actionPerformed(ActionEvent e)
	{
		//TODO: fill in method
		//taking note of e.getActionCommand()
	}
}