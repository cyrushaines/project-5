import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Project #4
 * CS 2334, Section 012
 * APR 21, 2016
 * <P>
 * Contains methods that are able to parse the contents of a specific media
 * file format and separate and store values of the name, year released, and release type
 * of media into different objects stored in a media collection. Adapted from previous project.
 * </P>
 * @version 1.2
 */
public class MDBParser
{
	/** Contains an aggregation of movies*/
	private MediaCollection movDB;
	
	/** Contains an aggregation of Series*/
	private MediaCollection sDB;
	
	/** Contains an aggregation of Episodes */
	private MediaCollection eDB;
	
	/** Reads the contents of a file*/
	private FileReader fr;
	
	/** Gives more efficient methods of parsing the file*/
	private BufferedReader br;
	
	/**
	 * <P>
	 * Creates a parser for the given file and stores all of the parsing into the
	 * Collection passed to the constructor.
	 * </P>
	 * @param file The file name to be parsed.
	 * @param dB Each line of the text will be stored in this collection of movies.
	 * @throws IOException Thrown when file cannot be found.
	 */
	public MDBParser(File file, MediaCollection mdB) throws IOException
	{
		this.movDB = mdB;
		this.fr = new FileReader(file);
		this.br = new BufferedReader(this.fr);
	}
	/**
	 * <P>
	 * Creates a parser for the given file and stores all of the parsing into the
	 * Collection passed to the constructor.
	 * </P>
	 * @param file The file name to be parsed.
	 * @param sDB Each line of text representing a series will be stored here
	 * @param eDB Each line of text representing an episode will be stored here
	 * @throws IOException Thrown when file cannot be found.
	 */
	public MDBParser(File file, MediaCollection sDB, MediaCollection eDB) throws IOException
	{
		this.sDB = sDB;
		this.eDB = eDB;
		
		this.fr = new FileReader(file);
		this.br = new BufferedReader(this.fr);
	}
	
	
	/**
	 * Parses the entire text file and stores it in the Collection it was created with.
	 * @throws IOException Thrown when the file cannot be found.
	 */
	public void buildSeriesCollection() throws IOException
	{
		String line;
		line = this.readNext();
		while(line != null)
		{
			//If a line contains a hyphen near the end of the line, then it is a series.
			if(line.substring(line.length()-6).contains("-"))
			{
				String name, runningYears;
				name = this.findName(line);
				runningYears = line.substring(line.length()-9, line.length());
				//firstYear = line.substring(line.length()-9, line.length()-5);
				Series series = new Series(name, runningYears);
				sDB.addMedia(series);
			}
			else //Otherwise the line contains an episode
			{
				String sName, eName;
				int year;
				
				sName = this.findName(line);
				eName = this.findEName(line);
				year = this.findYear(line);
				Episodes episode = new Episodes(sName, eName, year);
				eDB.addMedia(episode);
			}
			line = this.readNext();
		}
	}
	
	/**
	 * Parses the entire text file and stores it in the Collection it was created with.
	 * @throws IOException Thrown when the file cannot be found.
	 */
	public void buildMovieCollection() throws IOException
	{
		String line;
		line = this.readNext();
		while(line != null)
		{
			String name;
			int year;
			String releaseType;
			
			name = this.findMovieName(line);
			year = this.findMovieYear(line);
			releaseType = this.findMovieType(line);
			
			Movie movie = new Movie(name, year, releaseType);
			movDB.addMedia(movie);
			
			line = this.readNext();
		}
		
		movDB.sort("mediaList", true);
	}
	
	/**
	 * Reads the next line in the text file and passes it all as a String.
	 * @return A String containing the entire content of a single line in the text file.
	 * @throws IOException Thrown when it tries to read a line past the end of the text document.
	 */
	public String readNext() throws IOException
	{
		return br.readLine();
	}
	
	
	/**
	 * <P>
	 * Seperates the part of the line of text containing only the name of the series.
	 * </P>
	 * @param nextLine The line to parse.
	 * @return A section of the line of text containing the name.
	 */
	public String findName(String nextLine)
	{
		String title = nextLine.substring(1, nextLine.lastIndexOf('"'));
		return title;
	}
	
	/**
	 * <P>
	 * Seperates the part of the line of text containing only the name of the episode.
	 * </P>
	 * @param nextLine The line to parse.
	 * @return A section of the line of text containing the name.
	 */
	public String findEName(String nextLine)
	{
		String temp = nextLine;
		//Gets rid of suspended exception
		if(temp.contains("{{SUSPENDED}}"))
		{
			temp = temp.replace("{{SUSPENDED}}", "");
		}
		
		//Test to see if the line even contains an episode name, and also test to see if series information is present
		if(temp.contains("{") && (!temp.contains("#"))) //finds the episode name if no episode number
		{
			return temp.substring(temp.indexOf("{")+1, temp.lastIndexOf("}"));
		}
		else if(temp.contains("{") && (temp.contains("#"))) //finds the series name if there is an episode number
		{
			return temp.substring(temp.indexOf("{") + 1, temp.lastIndexOf("(")).trim();
		}else
		{
			return "(Not named)";
		}
	}
	
	/**
	 * <P>
	 * Seperates the part of the line of text containing the year the media was released.
	 * @param nextLine The line in the textfile to find the year.
	 * @return An int containing the year the media was released.
	 */
	public int findYear(String nextLine)
	{
		String yearString = nextLine.substring(nextLine.length() - 4, nextLine.length());
		if(yearString.equals("????"))
			yearString = "-1";
		int year = Integer.parseInt(yearString);
		return year;
	}

	
	/**
	 * <P>
	 * Separates the part of the line of text containing the year the movie was released.
	 * </P>
	 * @param nextLine The line in the textfile to find the year.
	 * @return An int containing the year the movie was released.
	 */
	public int findMovieYear(String nextLine)
	{
		String stringYear;
		int year;
		Integer integerYear;
		
		stringYear = nextLine.substring(nextLine.length()-4, nextLine.length());
		
		//prevents type mismatch error, and allows Movie class to store special release date as unknown
		if(stringYear.equals("????"))
			stringYear = "-1";
		
		integerYear = Integer.valueOf(stringYear);
		year = integerYear.intValue();
		return year;
	}
	
	/**
	 * <P>
	 * Parses the line for a specific set of characters to reveal how
	 * the movie was originally released.
	 * </P>
	 * @param nextLine The line in the textfile to find the release type.
	 * @return A String containing a neatly formatted description of how the movie was released.
	 */
	public String findMovieType(String nextLine)
	{
		if(nextLine.contains("(TV)"))
				return "Television Release";
		else if(nextLine.contains("(V)"))
			return "Direct Video Release";
		else
			return "Initial Theatre Release";
	}
	
	/**
	 * <P>
	 * Separates the part of the line of text containing only the name of the movie.
	 * </P>
	 * @param nextLine The line to parse.
	 * @return A section of the line of text containing the name.
	 */
	public String findMovieName(String nextLine)
	{
		String name;
		name = nextLine.substring(0, nextLine.length()-4);
		
		//Delete matching movie type
		name = name.replace("(TV)", "");
		name = name.replace("(V)", "");
		
		//Trim
		name = name.trim();
		
		//Get rid of the redundant year
		name = name.substring(0, name.length()-6);
		name = name.trim();
		
		return name;
	}
}
