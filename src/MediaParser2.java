import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MediaParser2 
{
	
	/**
	 * <P>
	 * Determines if the file line is empty, ensures that
	 * parsing does not occur on this line.
	 * </P>
	 * @param theLine		The line being parsed
	 * @return		True if the line is empty, false if it contains something
	 */
	public static boolean isLineEmpty(String theLine)
	{
		if(theLine.equals("")){return true;}
		else{return false;}
	}
	
	/**
	 * <P>
	 * Checks to see if a line contains a media maker at the beginning or not.
	 * NOTE - This also removes excess whitespace if there is not media maker at the beginning
	 * allowing it to be passed into the parseMedia method.
	 * </P>
	 * @param theLine		The line being parsed
	 * @return		True if the line has a new media maker to be stored, false if not.
	 */
	public static boolean containsMediaMaker(String theLine)
	{
		//Checks to see if the first character is a tab or a space
		if(theLine.codePointAt(0) == 9 || theLine.codePointAt(0) == 32)
		{
			//theLine = theLine.trim();
			return false;
		}
		else {return true;}
	}
	
	/**
	 * <P>
	 * Parses out the media maker from the line
	 * </P>
	 * @param theLine		The line with a media maker to be parsed
	 * @return		The media maker's name, unformatted
	 */
	public static String getMediaMaker(String theLine)
	{
		
		//Take the first part of the line up to the first tab
		String mediaMaker = theLine.substring(0, theLine.indexOf('	'));
		mediaMaker = mediaMaker.trim();
		//Removes the media maker formatting from the line.
		theLine = theLine.replace(mediaMaker, "");
		theLine = theLine.trim();
		
		return mediaMaker;
		
	}
	
	/**
	 * <P>
	 * Parses a line after it has been handled for possibly being a blank line
	 * or containing a new media maker.
	 * </P>
	 * @param theLine		The line containing any media type (Media maker and tabs MUST be removed from line before parsing)
	 * @return		A media object
	 */
	public static Media parseMedia(String theLine)
	{
		String nextLine = theLine;
		//Get rid of {{SUSPENDED}} to ensure that it won't interrupt the media type determination
		nextLine = nextLine.replace("{{SUSPENDED}}", "");
		
		//Look for quotes at front of line after the tab, then its an episode entry, series alone are never given
		if(nextLine.charAt(0) == '"')
		{
			//Get the Series title
			int secondQuote;
			secondQuote = nextLine.indexOf('"', 1);
			String seriesTitle = nextLine.substring(1, secondQuote);
			
			//Remove the Series title from the line
			nextLine = nextLine.replace("\"" + seriesTitle + "\"", "");
			nextLine = nextLine.trim();
			
			//The next part is guaranteed to be the series date, so this will be parsed out
			String seriesYear = nextLine.substring(1, 5);
			nextLine = nextLine.replace(seriesYear, "");
			nextLine = nextLine.replace("()", "");
			nextLine = nextLine.trim();
			if(seriesYear.equals("????"))
				seriesYear = "-1";
			
			//There are now 3 different formats that could be parsed
			String episodeTitle;
			String episodeYear;
			if(nextLine.contains("#"))
			{
				episodeTitle = nextLine.substring(0, nextLine.indexOf('('));
				episodeTitle = episodeTitle.replace("{", "");
				episodeTitle = episodeTitle.trim();
				episodeYear = "-1";
			}
			//test for yyyy-mm-dd format
			else if(nextLine.length() < 10)
			{
				episodeTitle = "(No Title)";
				episodeYear = seriesYear;
			}
			else if(nextLine.charAt(6) == '-' && nextLine.charAt(9) == '-')
			{
				episodeTitle = nextLine.substring(2,12);
				episodeYear = nextLine.substring(2,6);
			}
			//Just a title is between the brackets
			else if (!nextLine.contains("}"))
			{
				episodeTitle = "(No Title)";
				episodeYear = seriesYear;
			}
			else
			{
				episodeTitle = nextLine.substring(1, nextLine.indexOf('}'));
				episodeTitle = episodeTitle.trim();
				episodeYear = "-1";
			}
			Episodes ep = new Episodes(seriesTitle, episodeTitle, Integer.parseInt(episodeYear));
			return ep;
		}
		else //If no quotes then its a movie
		{
			//Parse the movie title and remove it from the line
			String movieTitle;
			movieTitle = nextLine.substring(0, nextLine.indexOf('('));
			nextLine = nextLine.replace(movieTitle, "");
			nextLine = nextLine.trim();
			movieTitle = movieTitle.trim();
			
			//Parse the release year
			String releaseYear;
			releaseYear = nextLine.substring(1, 5);
			if(releaseYear.equals("????"))
				releaseYear = "-1";
			if(Character.isLetter(releaseYear.charAt(1)))
			{
				int yearException = nextLine.indexOf('(', 1);
				releaseYear = nextLine.substring(yearException+1, yearException+5);
			}
			
			//Find if the movie has a release type
			String releaseType;
			if(nextLine.contains("(TV)")) {releaseType = "Television Release";}
			else if(nextLine.contains("(V)")) {releaseType = "Direct Video Release";}
			else {releaseType = "Initial Theatre Release";}
			
			Movie mov = new Movie(movieTitle, Integer.parseInt(releaseYear), releaseType);
			return mov;
		}
	}
	
	public static void parseCreditsFile(File fileName, LinkedHashMap<String, ArrayList<Media>> typeOfCredits,
			MediaCollection movies, MediaCollection episodesC) throws IOException
	{
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String nextLine = br.readLine();
		while(nextLine != null)
		{
			String actorName = "NEVER PARSED";
			ArrayList<Media> mda = new ArrayList<Media>();
	
			while(nextLine != null && !nextLine.isEmpty())
			{
				if(MediaParser2.containsMediaMaker(nextLine))
				{
					actorName = MediaParser2.getMediaMaker(nextLine);
					nextLine = nextLine.replace(actorName, "");
				}
				nextLine = nextLine.trim();
				Media nextMedia = MediaParser2.parseMedia(nextLine);
				mda.add(nextMedia);
		
				//Add the media to the correct MediaCollection
				if(nextMedia.getClass().toString().equals("class Movie"))
				{
					movies.addMediaNoDuplicate(nextMedia);
				}
				else
				{
					episodesC.addMediaNoDuplicate(nextMedia);
				}
				nextLine = br.readLine();
				}
				if(!actorName.equals("NEVER PARSED")) //If the file had 2 blank lines in a row, there is no data to be added
				{
					typeOfCredits.put(actorName, mda);
				}
				nextLine = br.readLine();
		}
		//put method here to get the length of the arraylists for each key
		fr.close();
		br.close();
		
		
	}
}
