import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

/**
 * Project #4
 * CS 2334, Section 012
 * APR 21, 2016
 * This class creates the window to be viewed when the program initially runs.
 *@version 1.0
 */
//Do we want to implement actionlistener too?
public class MediaView extends JFrame implements Serializable
{
	/**	 */
	private static final long serialVersionUID = -5926201051461312047L;
	
	/** Store the current model */
	private MediaModel model;
	/** Stores a JPanel */
	private JPanel MDbPanel;
	/** Stores a scroll pane */
	private JScrollPane scrollPane;
	/** Stores a label for the scroll pane */
	private JLabel scrollPaneTitle;
	/** Store a text area */
	protected JList<Object> dataList;
	
	/** Stores a menu bar */
	private JMenuBar menuBar;
	
	/** Stores a file menu */
	protected JMenu fileMenu;
	/** Stores a save option */
	protected JMenuItem saveItem;
	/** Stores a load option */
	protected JMenuItem loadItem;
	/** Stores an import option */
	protected JMenuItem importItem;
	/** Stores an export option */
	protected JMenuItem exportItem;
	
	/** Store an edit menu */
	protected JMenuItem editMenu;
	/** Store an add option */
	protected JMenuItem addItem;
	/** Stores an edit option */
	protected JMenuItem editItem;
	/** Stores a delete option */
	protected JMenuItem deleteItem;
	/** Stores a clear option */
	protected JMenuItem clearItem;
	/** Stores a clear all option */
	protected JMenuItem clearAllItem;
	
	/** Stores a display menu */
	protected JMenu displayMenu;
	/** Stores a pie chart option */
	protected JMenuItem pieChartItem;
	/** Stores a histogram option */
	protected JMenuItem histogramItem;
	
	/** Stores a radio button */
	protected JRadioButton mediaButton;
	/** Stores a radio button */
	protected JRadioButton movieButton;
	/** Stores a radio button */
	protected JRadioButton seriesButton;
	/** Stores a radio button */
	protected JRadioButton episodesButton;
	/** Stores a radio button */
	protected JRadioButton makerButton;
	/** Stores a radio button */
	protected JRadioButton actorButton;
	/** Stores a radio button */
	protected JRadioButton directorButton;
	/** Stores a radio button */
	protected JRadioButton producerButton;

	
	/**
	 * Constructor for MediaView
	 */
	public MediaView()
	{
		
		//Gets the dimensions of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//Stores a fraction of the width of the screen
		double width = screenSize.getWidth()/3;
		//Stores a fraction of the height of the screen
		double height = screenSize.getHeight()/2;
		
		//Sets the title of this viewing window
		this.setTitle("MDb");
		//Sets the dimensions of this viewing window
		this.setSize((int) width, (int) height);
		//Sets the window to exit when closed
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagConstraints layoutConst = new GridBagConstraints();
		
		
		MDbPanel = new JPanel();
		
		//Sets the panel's layout to a new GridBagLayout
		MDbPanel.setLayout(new GridBagLayout());
		
		//Initializes a menu bar
		menuBar = new JMenuBar();
		
		//Initializes the "File" menu
		fileMenu = new JMenu("File");
		//Initializes the menu items in the File menu
		saveItem = new JMenuItem("Save");
		loadItem = new JMenuItem("Load");
		importItem = new JMenuItem("Import");
		exportItem = new JMenuItem("Export");
		
		//Disables the "Save" menu item
		saveItem.setEnabled(false);
		//Sets the "Save" menu item's tool tip to explain why it is disabled
		saveItem.setToolTipText("Cannot save data; no data present");
		//Disables the "Export" menu item
		exportItem.setEnabled(false);
		//Sets the "Export" menu item's tool tip to explain why it is disabled
		exportItem.setToolTipText("Cannot export data; no data present");
		
		//Initializes the "Edit" menu
		editMenu = new JMenu("Edit");
		//Initializes the menu items in the Edit menu
		addItem = new JMenuItem("Add");
		editItem = new JMenuItem("Edit");
		deleteItem = new JMenuItem("Delete");
		clearItem = new JMenuItem("Clear");
		clearAllItem = new JMenuItem("Clear All");
		
		//Disables the "Add" menu item
		addItem.setEnabled(false);
		//Sets the "Add" menu item's tool tip to explain why it is disabled
		addItem.setToolTipText("No radio button selected");
		//Disables the "Edit" menu item
		editItem.setEnabled(false);
		//Sets the "Edit" menu item's tool tip to explain why it is disabled
		editItem.setToolTipText("No radio button selected");
		//Disables the "Delete" menu item
		deleteItem.setEnabled(false);
		//Sets the "Delete" menu item's tool tip to explain why it is disabled
		deleteItem.setToolTipText("No radio button selected");
		//Disables the "Clear" menu item
		clearItem.setEnabled(false);
		//Sets the "Clear" menu item's tool tip to explain why it is disabled
		clearItem.setToolTipText("No radio button selected");
		//Disables the "Clear All" menu item
		clearAllItem.setEnabled(false);
		//Sets the "Clear All" menu item's tool tip to explain why it is disabled
		clearAllItem.setToolTipText("No radio button selected");
		
		//Initializes the "Display" menu
		displayMenu = new JMenu("Display");
		//Initializes the "Pie Chart" menu item
		pieChartItem = new JMenuItem("Pie Chart");
		//Initializes the "Histogram" menu item
		histogramItem = new JMenuItem("Histogram");
		
		//Disables the "Pie Chart" menu item
		pieChartItem.setEnabled(false);
		//Sets the "Pie Chart" menu item's tool tip to explain why it is disabled
		pieChartItem.setToolTipText("No people selected");
		//Disables the "Histogram" menu item
		histogramItem.setEnabled(false);
		//Sets the "Histogram" menu item's tool tip to explain why it is disabled
		histogramItem.setToolTipText("No people selected");
		
		//Adds menu items to the "File" menu
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		fileMenu.add(importItem);
		fileMenu.add(exportItem);
		
		//Adds menu items to the "Edit" menu
		editMenu.add(addItem);
		editMenu.add(editItem);
		editMenu.add(deleteItem);
		editMenu.add(clearItem);
		editMenu.add(clearAllItem);
		
		//Adds menu items to the "Display" menu
		displayMenu.add(pieChartItem);
		displayMenu.add(histogramItem);
		
		//Adds the menus to the menu bar
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(displayMenu);
		
		/* Sets menuBar as the JMenuBar for the window
		 * (this makes it appear as an actual bar under the title bar)
		 */
		this.setJMenuBar(menuBar);
		
		//Initializes a bunch of radio buttons
		mediaButton = new JRadioButton("Media");
		movieButton = new JRadioButton("Movie");
		seriesButton = new JRadioButton("Series");
		episodesButton = new JRadioButton("Episodes");
		actorButton = new JRadioButton("Actors");
		directorButton = new JRadioButton("Directors");
		producerButton = new JRadioButton("Producers");
		makerButton = new JRadioButton("Makers");
		
		//Initializes a title label meant to appear above a data text area
		scrollPaneTitle = new JLabel("[Title]");
		//Initializes a scroll pane
		scrollPane = new JScrollPane();
		//Initializes a text area
		dataList = new JList<Object>();
		//Allows for multiple elements to be selected simultaneously anywhere in the JList
		dataList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//Sets the elements to appear in only one column in the JList
		dataList.setLayoutOrientation(JList.VERTICAL);
		
		//Initializes a JScrollPane with a JList and sets the scroll bars to appear as needed
		scrollPane = new JScrollPane(dataList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//Sets the size of the scroll pane
		scrollPane.setPreferredSize(new Dimension(this.getWidth()/2, this.getHeight()/2));
		
		
		//Sets the grid position to the 0th column
		layoutConst.gridx = 0;
		//Sets the grid position to the 0th row
		layoutConst.gridy = 0;
		//Anchors the component(s) to the far left side of the grid 
		layoutConst.anchor = GridBagConstraints.WEST;
		//Sets the padding to 10 pixels around each grid area
		layoutConst.insets = new Insets(10, 10, 10, 10);
		//Adds the "Media" radio button to the MDbPanel
		MDbPanel.add(mediaButton, layoutConst);
		
		//Sets the grid position to the 1st row
		layoutConst.gridy = 1;
		//Adds the "Movie" radio button to the MDbPanel
		MDbPanel.add(movieButton, layoutConst);
		
		//Sets the grid position to the 2nd row
		layoutConst.gridy = 2;
		//Adds the "Series" radio button to the MDbPanel
		MDbPanel.add(seriesButton, layoutConst);
		
		//Sets the grid position to the 3rd row
		layoutConst.gridy = 3;
		//Adds the "Episodes" radio button to the MDbPanel
		MDbPanel.add(episodesButton, layoutConst);
		
		//Sets the grid position to the 4th row
		layoutConst.gridy = 4;
		//Adds the "Makers" radio button to the MDbPanel
		MDbPanel.add(makerButton, layoutConst);
		
		//Sets the grid position to the 5th row
		layoutConst.gridy = 5;
		//Adds the "Actors" radio button to the MDbPanel
		MDbPanel.add(actorButton, layoutConst);
		
		//Sets the grid position to the 6th row
		layoutConst.gridy = 6;
		//Adds the "Directors" radio button to the MDbPanel
		MDbPanel.add(directorButton, layoutConst);
		
		//Sets the grid position to the 7th row
		layoutConst.gridy = 7;
		//Adds the "Producers" radio button to the MDbPanel
		MDbPanel.add(producerButton, layoutConst);
		
		//Sets the grid position to the 1st column
		layoutConst.gridx = 1;		
		//Sets the grid position to the 1st row
		layoutConst.gridy = 0;
		//Sets the grid area to take up 6 rows
		layoutConst.gridheight = 8;
		//Fills the grid area both horizontally and vertically
		layoutConst.fill = GridBagConstraints.BOTH;
		//Adds the scroll pane to the MDbPanel
		MDbPanel.add(scrollPane, layoutConst);
		
		//Adds the MDbPanel to this JFrame
		this.add(MDbPanel);
		
		
		
		
		
		//Packs the frame removing the excess empty space
//		this.pack();
		
		//Sets this JFrame to be visible
		this.setVisible(true);
	}
	
	/**
	 * Updates the current model
	 * 
	 * @param m		A MediaModel object
	 */
	public void setModel(MediaModel m)
	{
		model = m;
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
	 * Responds to a particular action performed by an ActionEvent
	 * 
	 * @param e			An ActionEvent
	 */
	public void actionPerformed(ActionEvent e)
	{
		//TODO: fill in method
		//taking note of e.getActionCommand()
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addJListListener (ListSelectionListener l)
	{
		dataList.addListSelectionListener(l);
	}
		
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addSaveFileListener(ActionListener l)
	{
		saveItem.addActionListener(l);
	}

	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addLoadFileListener(ActionListener l)
	{
		loadItem.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addImportFileListener(ActionListener l)
	{
		importItem.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addExportFileListener(ActionListener l)
	{
		exportItem.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addPieChartItemListener(ActionListener l)
	{
		pieChartItem.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addHistogramItemListener(ActionListener l)
	{
		histogramItem.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addAddItemListener(ActionListener l)
	{
		addItem.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addEditItemListener(ActionListener l)
	{
		editItem.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addDeleteItemListener(ActionListener l)
	{
		deleteItem.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addClearItemListener(ActionListener l)
	{
		clearItem.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addClearAllItemListener(ActionListener l)
	{
		clearAllItem.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addMediaButtonListener(ActionListener l)
	{
		mediaButton.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addMovieButtonListener(ActionListener l)
	{
		movieButton.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addSeriesButtonListener(ActionListener l)
	{
		seriesButton.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addEpisodesButtonListener(ActionListener l)
	{
		episodesButton.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addMakerButtonListener(ActionListener l)
	{
		makerButton.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addActorButtonListener(ActionListener l)
	{
		actorButton.addActionListener(l);
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addDirectorButtonListener(ActionListener l)
	{
		directorButton.addActionListener(l);		
	}
	
	/**
	 * Adds an ActionListener
	 * 
	 * @param l		An ActionListener
	 */
	public void addProducerButtonListener(ActionListener l)
	{
		producerButton.addActionListener(l);
	}
}
