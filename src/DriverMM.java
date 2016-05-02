import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * Project #4
 * CS 2334, Section 012
 * APR, 21 2016
 * <P>
 * This class drives the media maker questions and performs the search
 * for the actor, producer or director.
 * @version 1
 */
public class DriverMM {
	
	public static void DriverMediaMaker(LinkedHashMap<String, ArrayList<Media>> actorCredits, 
			LinkedHashMap<String, ArrayList<Media>> directorCredits,
			LinkedHashMap<String, ArrayList<Media>> producerCredits, Scanner sc) throws IOException 
	{
		//Enter loop for searching media
		int sent = 0;
		while(sent == 0)
		{
			String input;
			String nameSearch = "";
			String searchType = "";
			Scanner keyboard = new Scanner(System.in);
			//--------GET SEARCH INFO--------
	
			//Ask whether to search exact or partial names
			System.out.println("Seach for (e)xact or (p)artial matches?");
			searchType = checkValidInput("e", "p");
			System.out.println("Enter the name to be searched.");
			input = keyboard.nextLine();
			ArrayList<Media>actorLists = new ArrayList<Media>();
			ArrayList<Media>producerLists = new ArrayList<Media>();
			ArrayList<Media>directorLists = new ArrayList<Media>();
			int count = 0;
				if(searchType.equalsIgnoreCase("e"))
				{
					//use name as key for hashmap
					String exactFind = input;
					actorLists = actorCredits.getOrDefault(exactFind, new ArrayList<Media>());
					producerLists = producerCredits.getOrDefault(exactFind, new ArrayList<Media>());
					directorLists = directorCredits.getOrDefault(exactFind, new ArrayList<Media>());
					
					count = 1;  
				}
				else if(searchType.equalsIgnoreCase("p"))
				{
					//use partial to search for several names in hashmap
					//containsValue
					//temporary arraylists to be sent to user
					count = 2;
					ArrayList<Media> tempActors = new ArrayList<Media>();
					ArrayList<Media> tempProducers = new ArrayList<Media>();
					ArrayList<Media> tempDirectors = new ArrayList<Media>();
					Set<String> actorSet = actorCredits.keySet();
					Set<String> producerSet = producerCredits.keySet();
					Set<String> directorSet = directorCredits.keySet();
					String[] actorList;
					String[] producerList;
					String[] directorList;
					actorList = actorSet.toArray(new String[1]);
					producerList = producerSet.toArray(new String[1]);
					directorList =  directorSet.toArray(new String[1]);
					String actor;
					String producer;
					String director;
					ArrayList<String> keyList = new ArrayList<String>();
					for(int i = 0; i < actorList.length; i++)
					{
						actor = actorList[i];
						if(actor.contains(input))
						{
							keyList.add(actor);
						}
					}
					for(int j = 0; j < producerList.length; j++)
					{
						producer = producerList[j];
						if(producer.contains(input))
						{
							keyList.add(producer);
						}
					}
					for(int k = 0; k < directorList.length; k++)
					{
						director = directorList[k];
						if(director.contains(input))
						{
							keyList.add(director);
						}
					}
					
					System.out.println("There are multiple names matching your inquiry.");
					for(int l = 0; l < keyList.size(); l++)
					{
						System.out.println(l + "" + keyList.get(l));
					}
					System.out.println("Please choose from the list above");
					int reply = keyboard.nextInt();
					keyboard.nextLine();
					String exactFind = keyList.get(reply);
				
					actorLists = actorCredits.getOrDefault(exactFind, new ArrayList<Media>());
					producerLists = producerCredits.getOrDefault(exactFind, new ArrayList<Media>());
					directorLists = directorCredits.getOrDefault(exactFind, new ArrayList<Media>());
				}
				else
				{
					System.out.println("Could not find the name entered.");
					//drop to bottom of loop and ask continue
				}
				
				if(count == 1 || count == 2)
				{
					System.out.println("Display (t)ext or (g)raph?");
					searchType = checkValidInput("t", "g");
					if(searchType.equalsIgnoreCase("t"))
					{
						
						//Begin writing to console
						System.out.println("Name: " + input);
						for(int i = 0; i < actorLists.size(); i++)
						{
							System.out.println(actorLists.get(i).toString());
							
						}
						for(int i = 0; i < directorLists.size(); i++)
						{
							System.out.println(directorLists.get(i).toString());
							
						}
						for(int i = 0; i < producerLists.size(); i++)
						{
							System.out.println(producerLists.get(i).toString());
							
						}
						
					}
					else if(searchType.equalsIgnoreCase("g"))
					{
						System.out.println("Display (p)ie chart or (h)istogram?");
						searchType = checkValidInput("p", "h");
						if(searchType.equalsIgnoreCase("p"))
						{
							//call pie chart GUI
							//new PieChart(input, actorLists, producerLists, directorLists);
							PieChart.PieChartBuilder(new PieChart(input, actorLists, producerLists, directorLists));
						}
						else if(searchType.equalsIgnoreCase("h"))
						{
							//call histogram GUI
							Histogram H1 =new Histogram(input, actorLists, producerLists, directorLists);
							H1.buildHistogram();
						}
					}
				}
			//Ask user if they want to continue
			System.out.println("Save? (y)/(n)?");
			searchType = checkValidInput("y", "n");
			if(searchType.equalsIgnoreCase("y"))
			{
				System.out.println("Write (t)ext or (b)inary data?");
				searchType = checkValidInput("t", "b");
				if(searchType.equalsIgnoreCase("t"))
				{
					System.out.println("Enter the name of the file to export to.");
					String userFile = sc.nextLine();
					
					FileWriter outfile = new FileWriter(userFile);
					BufferedWriter bw = new BufferedWriter(outfile);
					
					//Begin writing to file
					bw.write("Name: " + input);
					bw.newLine();
					for(int i = 0; i < actorLists.size(); i++)
					{
						bw.write(actorLists.get(i).toString());
						bw.newLine();
					}
					for(int i = 0; i < directorLists.size(); i++)
					{
						bw.write(directorLists.get(i).toString());
						bw.newLine();
					}
					for(int i = 0; i < producerLists.size(); i++)
					{
						bw.write(producerLists.get(i).toString());
						bw.newLine();
					}
					bw.close();
				}
				else if(searchType.equalsIgnoreCase("b"))
				{
					//Added in main driver
				}
			}
			else if(searchType.equalsIgnoreCase("n"))
			{
				sent = 1;
			}
		}//End while loop
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
}