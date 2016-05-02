
/**
 * Project #4
 * CS 2334, Section 012
 * APR 21, 2016
 * <P>
 * This class represents the series class and the methods associated with it.
 * </P>
 * @version 1.0
 */
public class Series extends Media{
	/** A UID for serialization */
	private static final long serialVersionUID = 7281035807081260296L;
	/** Stores the string value of the series name to a variable */
	private String seriesName;
	/** Stores the total years of the series broadcast */
	private String runningYears;
	
	/**
	 * This is the constructor method for series.
	 * This method takes in the three private variables above. 
	 * @param name			Title of the series to be initiated to seriesName.
	 * @param yearStart		Start ear of the series to be initialized to yearStart.
	 * @param runningYears	Set of years the series was broadcasted to be initialized to runningYears.
	 */
	public Series(String name, String runningYears)
	{
		this.seriesName = name;
		//this.yearStart = yearStart;
		runningYears = runningYears.replace("????", "UNSPECIFIED");
		this.runningYears = runningYears;
	}
	/**
	 * <P>
	 * return the name of the series.
	 */
	public String getName()
	{
		return seriesName;
	}
	/**
	 * <P>
	 * This method returns the year of the series start after its been parse.
	 * </P>
	 * @return the year the series started
	 */
	public int getYear()
	{
		return Integer.parseInt(runningYears.substring(0, 4));
	}
	/**
	 * 
	 * @return year
	 */
	public int getEndYear(){
		
		return Integer.parseInt(runningYears.substring(5, 9));
	}
	
	/**
	 * Method to get the name episode title, or "" if it's not an episode.
	 * @return the title of the episode.
	 */
	public String getEpisodeTitle()
	{
		return "";
	}

	/**
	 * return the the total broadcast years of the series.
	 * @return the running years of the series object.
	 */
	public String getTotalYears()
	{
		return runningYears;
	}
	/**
	 * prints the line of data to the console.
	 * @return the line of data in a readable format
	 */
	@Override public String toString()
	{
		String line = ("SERIES: " + this.seriesName + "  (" + this.runningYears + ")");
		return line;
	}
}

