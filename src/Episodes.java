
/**
 * Project #4
 * CS 2334, Section 012
 * APR 21 2016
 * <P>
 * This class represents the episode class and the methods associated with it.
 * </P>
 * @version 1.0
 */
public class Episodes extends Media{
	/** A UID for serialization */
	private static final long serialVersionUID = 7204633764498525503L;
	/** Stores the string value of the episode's name to a variable */
	private String episodeName;
	/** Stores the the information of the year the episode released to the string variable */
	private int releaseYear;
	/** Stores the the title of the series to the string variable */
	private String seriesTitle;
	
	/**
	 * <P>
	 * This is the constructor method for episode. It takes in the 
	 * class variables above.
	 * </P>
	 * @param seriesTitle		Title of the series to be initiated to seriesTitle.
	 * @param name				Episode name to be initialized to the name.	
	 * @param releaseYear		Year of the episode's release initiated to releaseYear.
	 * 	
	 */
	public Episodes(String seriesTitle, String name, int releaseYear)
	{
		this.seriesTitle = seriesTitle;
		this.episodeName = name;
		this.releaseYear = releaseYear;
		
	}
	/**
	 * Method to get the name of the episode title.
	 * @return the title of the episode.
	 */
	public String getEpisodeTitle()
	{
		return episodeName;
	}
	/**
	 * Method to get the name of the episode.
	 * @return the name of the episode.
	 */
	public String getName()
	{
		return seriesTitle;
	}

	/**
	 * Method to get int value of episode year release
	 * @return the year of the episodes release
	 */
	public int getYear()
	{
		return releaseYear;
	}

	/**
	 * prints the line of data to the console.
	 * @return the line of data in a readable format
	 */
	@Override public String toString()
	{
		String line = ("EPISODE: " + seriesTitle + ": " + this.episodeName + " (" + this.releaseYear + ")");
		return line;
	}
}
