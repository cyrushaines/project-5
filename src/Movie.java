
/**
* Project #4
* CS 2334, Section 12
* APR 21, 2016
* <P>
*A class to return different pieces of data related to a movie, including the title,
*release year, and type of movie, which includes theater, TV, or straight to video
*</P>
*@version 1.1
**/
public class Movie extends Media
{
	/** A UID for serialization */
	private static final long serialVersionUID = 3505139685830660976L;

	/**Stores the name of the movie to return to the MediaCollection class*/
	private String movieName;
	
	/**Stores the year the movie was released*/
	private int releaseYear;
	
	/**Stores the type of movie, IE: theater, TV, or straight to video*/
	private String movieType;
	
	/**
	 * <P>
	 * This class gives movieName the name of the movie, movieType the type of the
	 * movie, and releaseYear the release year of the movie
	 * @param name			Title of the movie to be initiated to movieName
	 * @param releaseYear	Year of the movie to be initialized to releaseYear
	 * @param type			Venue of movie to be initialized to movie type.
	 */
	public Movie(String name, int releaseYear, String type)
	{
		this.movieName = name;
		this.movieType = type;
		this.releaseYear = releaseYear;
	}
	/**
	 * <P>
	 * Used to return the name to the movieCollection class
	 * @return the name of the movie object.
	 */
	public String getName()
	{

		return movieName;
	}
	/**
	 * <P>
	 * gets the year of the movie's air date.
	 * @return the year of the movie object's release.
	 */
	public int getYear()
	{
	
		return releaseYear;
	}
	
	/**
	 * Method to get the name of the episode title, or "" if it's not an episode.
	 * @return the title of the episode
	 */
	public String getEpisodeTitle()
	{
		return "";
	}

	/**
	 * <P>
	 * Returns the type of movie
	 * @return the format of the movie.
	 */
	public String getType()
	{
		
		return movieType;
	}
	
	/**
	 * prints the line of data to the console.
	 * @return the line of data in a readable format
	 */
	public String toString()
	{
		String line;
		if(this.releaseYear == -1)
		{
			line = ("MOVIE: " + this.movieName + ") (" + this.movieType
				+ ") (" + "UNSPECIFIED");
		} else
		{
			line = ("MOVIE: " + this.movieName + "  (" + this.movieType
					+ ") (" + this.releaseYear + ")");
		}
		return line;
	}
}


