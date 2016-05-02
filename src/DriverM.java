import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
/**
 * Project #4
 * CS 2334, Section 012
 * APR 21 2016
 * <P>
 *This class drives the media function of the program. It will ask a series of questions
 *and perform the search on the information provided.
 * @version 1
 */
//
public class DriverM {

	public static void DriverMedia( MediaCollection movies, MediaCollection seriesC, MediaCollection episodesC, 
			Scanner sc) throws IOException 
	{
		//Enter loop for searching media
		int sent = 0;
		while(sent == 0)
		{
			String mediaTypes;
			String searchType;
			String nameSearchType = "";
			String includeEpisodes = "";
			String titleKey = "";
			String yearKey = "";
			String sortType;
			String shouldPrint;
			
			//--------GET SEARCH INFO--------
			
			//Ask what types of media to search
			System.out.println("Search (m)ovies, (s)eries, or (b)oth?");
			mediaTypes = checkValidInput("m", "s", "b");
			
			//Ask to search for the title or year
			System.out.println("Search (t)itle, (y)ear, or (b)oth?");
			searchType = checkValidInput("t", "y", "b");
			
			//Determine if title should be searched by exact or partial
			if(searchType.equals("t") || searchType.equals("b"))
			{
				System.out.println("Seach for (e)xact or (p)artial matches?");
				nameSearchType = checkValidInput("e", "p");
			}
			
			//Ask if user wants episodes included in search
			if((mediaTypes.equals("s") || mediaTypes.equalsIgnoreCase("b")) && (searchType.equals("t") || searchType.equals("b")))
			{
				System.out.println("Include episode titles in search and output? (y/n)");
				includeEpisodes = checkValidInput("y", "n");
			}
			
			//Get the title key to search for
			if(searchType.equals("t") || searchType.equals("b"))
			{
				System.out.println("Title?");
				titleKey = sc.nextLine();
			}
			
			//Get the year key to search for
			if(searchType.equals("y") || searchType.equals("b"))
			{
				System.out.println("Year(s)");
				yearKey = sc.nextLine();
			}
			
			//Ask user how to sort list
			System.out.println("Sort by (t)itle or (y)ear");
			sortType = checkValidInput("t", "y");
			
			//--------END GET SEARCH INFO--------
			
			//Clear the search list before performing a new search
			movies.clearSearchList();
			seriesC.clearSearchList();
			episodesC.clearSearchList();
			
			//--------SEARCH MEDIA LISTS FOR SEARCH INFO--------
			
			//Loop to search in movie list
			if(mediaTypes.equals("m") || mediaTypes.equals("b"))
			{
				//Search by name keyword
				if(searchType.equals("t") || searchType.equals("b"))
				{
					//Search by exact name
					if(nameSearchType.equals("e"))
					{
						movies.searchName(titleKey);
					}
					//Search by keyword
					else
					{
						movies.searchKeyword(titleKey);
					}
				}
				
				//Search by year
				if(searchType.equals("y") || searchType.equals("b"))
				{
					movies.searchYears(yearKey);
				}
			}
			
			//Loop to search in series list
			if(mediaTypes.equals("s") || mediaTypes.equals("b"))
			{
				//Search by name keyword
				if(searchType.equals("t") || searchType.equals("b"))
				{
					//Search by exact name
					if(nameSearchType.equals("e"))
					{
						seriesC.searchName(titleKey);
						if(includeEpisodes.equals("y"))
							episodesC.searchName(titleKey);
					}
					//Search by keyword
					else
					{
						seriesC.searchKeyword(titleKey);
						if(includeEpisodes.equals("y"))
							episodesC.searchKeyword(titleKey);
					}
				}
				
				//Search by year
				if(searchType.equals("y") || searchType.equals("b"))
				{
					seriesC.searchYears(yearKey);
					if(includeEpisodes.equals("y"))
						episodesC.searchYears(yearKey);
				}
			}
			
			//--------END SEARCH BY INFO--------
			
			//Sort searchList by year or title
			if(sortType.equals("t"))
			{
				//Sorts by default by-name
				movies.sort("seriesList", true);
				seriesC.sort("searchList", true);
				episodesC.sort("searchList", true);
			}else if(sortType.equals("y"))
			{
				//Sorts by year
				movies.sort("seriesList", false);
				seriesC.sort("searchList", false);
				episodesC.sort("searchList", false);
			}
			//Pass off MediaCollections to a printer method to output a file
			System.out.println("Do you want to save these search results to a file? (y/n)");
			shouldPrint = checkValidInput("y", "n");
			
			//-------------PRINT INFO------------------
			
			if(shouldPrint.equals("y"))
			{
				System.out.println("Enter the name of the file to export to.");
				String userFile = sc.nextLine();
				
				FileWriter outfile = new FileWriter(userFile);
				BufferedWriter bw = new BufferedWriter(outfile);
				
				//Begin writing to file
				//Write types of media searched
				if(mediaTypes.equals("m"))
				{
					bw.write("SEARCHED MOVIES");
					bw.newLine();
				}
				else if(mediaTypes.equals("s"))
				{
					if(includeEpisodes.equals("y"))
					{
						bw.write("SEARCHED TV SERIES and TV EPISODES");
						bw.newLine();
					} else
					{
						bw.write("SEARCHED TV SERIES");
						bw.newLine();
					}
				} else if(mediaTypes.equals("b"))
				{
					if(includeEpisodes.equals("y"))
					{
						bw.write("SEARCHED MOVIES, TV SERIES, and TV EPISODES");
						bw.newLine();
					} else
					{
						bw.write("SEARCHED MOVIES and TV SERIES");
						bw.newLine();
					}
				}
				//Write what search method was used
				if(searchType.equals("t"))
				{
					if(nameSearchType.equals("e"))
					{
						bw.write("EXACT TITLE: " + titleKey);
						bw.newLine();
						bw.write("YEARS: Any");
						bw.newLine();
					}
					else if(nameSearchType.equals("p"))
					{
						bw.write("PARTIAL TITLE: " + titleKey);
						bw.newLine();
						bw.write("YEARS: Any");
						bw.newLine();
					}
				}
				//if Year was searched
				else if(searchType.equals("y"))
				{
					bw.write("TITLE: Any");
					bw.newLine();
					bw.write("YEARS: " + yearKey);
					bw.newLine();
				}
				//If both year and name were searched
				else if(searchType.equals("b"))
				{
					bw.write("TITLE: " + titleKey);
					bw.newLine();
					bw.write("YEARS: " + yearKey);
					bw.newLine();
				}
				
				//Sorted by..
				if(sortType.equals("t"))
				{
					bw.write("SORTED BY TITLE");
					bw.newLine();
				}else
				{
					bw.write("SORTED BY YEARS");
					bw.newLine();
				}
				
				for(int i = 0; i < 80; i++)
				{
					bw.write("=");
				}
				bw.newLine();
				
				//Print out each movie info that matches the search criteria
				for(int i = 0; i < movies.searchLength(); i++)
				{
					bw.write(movies.searchResult(i).toString());
					bw.newLine();
				}
				
				//Print out each series info that matches the search criteria
				for(int i = 0; i < seriesC.searchLength(); i++)
				{
					bw.write(seriesC.searchResult(i).toString());
					bw.newLine();
				}
				
				//Print out episode info if the user asked for it
				if(includeEpisodes.equals("y"))
				{
					for(int i = 0; i < episodesC.searchLength(); i++)
					{
						bw.write(episodesC.searchResult(i).toString());
						bw.newLine();
					}
				}
				bw.close();
			}
			
			//-------END PRINT INFO--------------
			
			//Ask user if they want to continue
			/**System.out.println("Continue? (y)/(n)?");
			if(checkValidInput("y", "n").equals("n"))
				sent = 1;
				*/
			
		}//End while loop
		//sc.close();
		System.out.println("Thank you for using the MDB searcher.");
	}//End main

	/**
	 * <P>
	 * Method to compare the value of the user's input
	 * to see if it is correct
	 * </P>
	 * @return the input of the user saying whether its valid or not.
	 * @throws IOException 
	 */
	private static String checkValidInput(String a, String b) throws IOException
	{
		boolean validInput = false;
		BufferedReader sc = new BufferedReader(
				new InputStreamReader( System.in ) );

		String userInput = "";
		
		while(!validInput)
		{
			userInput = sc.readLine();
			if(userInput.equals(a) || userInput.equals(b))
			{
				validInput = true;
			}
			else
			{
				System.out.println('"' + userInput + '"' + " is not a valid input. Please re-enter a command.");
			}
		}
		return userInput;
	}

	/**
	 * <P>
	 * Method to compare the value of the user's input
	 * to see if it is correct
	 * </P>
	 * @return the input of the user saying whether its valid or not.
	 * @throws IOException 
	 */
	private static String checkValidInput(String a, String b, String c) throws IOException
	{
		boolean validInput = false;
		BufferedReader sc = new BufferedReader(
				new InputStreamReader( System.in ) );

		String userInput = "";
		
		while(!validInput)
		{
			userInput = sc.readLine();
			if(userInput.equals(a) || userInput.equals(b) || userInput.equals(c))
			{
				validInput = true;
			}
			else
			{
				System.out.println('"' + userInput + '"' + " is not a valid input. Please re-enter a command.");
			}
		}
		return userInput;
	}
}
