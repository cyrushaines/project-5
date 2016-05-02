import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
/**
 * Project #4
 * CS 2334, Section 012
 * APR 21, 2016
 * <P>
 * This is the driver class which will implement all
 * parts of the project through method calls and functions.
 * </P>
 * @version 1.1
 */

public class mainDriver {

	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		
		
		//making the main controller
		MediaView main= new MediaView();
		MediaModel main2= new MediaModel();
		SelectionController main3= new SelectionController();
		main3.setModel(main2);
		main3.setView(main);
		/*
		SeriesView bleh= new SeriesView();
		SeriesController bleh2= new SeriesController();
		MediaModel bleh3= new MediaModel();
		bleh2.setModel(bleh3);
		bleh2.setView(bleh);
		*/
		/*
		MovieView ugh= new MovieView();
		MovieController ugh2= new MovieController();
		MediaModel ugh3= new MediaModel();
		ugh2.setModel(ugh3);
		ugh2.setView(ugh);
		*/
		//end of Heather Stuff for testing
		
		
		/*
		Scanner sc = new Scanner(System.in);

		MediaCollection movies = new MediaCollection();
		MediaCollection seriesC = new MediaCollection();
		MediaCollection episodesC = new MediaCollection();
		LinkedHashMap<String, ArrayList<Media>> actorCredits = new LinkedHashMap<String, ArrayList<Media>>();
		LinkedHashMap<String, ArrayList<Media>> directorCredits = new LinkedHashMap<String, ArrayList<Media>>();
		LinkedHashMap<String, ArrayList<Media>> producerCredits = new LinkedHashMap<String, ArrayList<Media>>();
		
		System.out.println("Read (t)ext or (b)inary?");
		String reply = sc.nextLine();
		int count = 0;
		
			if(reply.equalsIgnoreCase("t"))
			{
				ArrayList<String> fileNames = new ArrayList<String>();
				System.out.println("Please enter the filename where movie data is stored.");
				fileNames.add(sc.nextLine());
				System.out.println("Please enter the filename where series data is stored.");
				fileNames.add(sc.nextLine());
				System.out.println("Please enter the filename where actor data is stored.");
				fileNames.add(sc.nextLine());
				System.out.println("Please enter the filename where director data is stored.");
				fileNames.add(sc.nextLine());
				System.out.println("Please enther the filename where producer data is stored.");
				fileNames.add(sc.nextLine());
				for(int i = 0; i < fileNames.size(); i++)
				{
					count = 0;
					File f = new File(fileNames.get(i));
					while(!f.exists())
					{
						System.out.println("Please reenter the " + fileNames.get(i));
						count = count + 1;
						sc.nextLine();
						if(count>=2)
						{
							System.out.println("MBD will exit now.");
							System.exit(0);
						}
					}
					
				}
				
				
				
				//REMEMBER TO UNCOMMENT THE PARSER STUFF BACK IN
				
				
				
				//Build Movie Collection
				//MDBParser mPar = new MDBParser(fileNames.get(0), movies);
				//mPar.buildMovieCollection();
				movies.sort("mediaList", true);
		
				//Build Series and Episode Collection
				//MDBParser sPar = new MDBParser(fileNames.get(1), seriesC, episodesC);
				//sPar.buildSeriesCollection();
				
				//TODO - Construct a MakerCredits object for actor, producer, and director
				//Parse the actor credits file, adding the media objects and actors to both the MediaCollections and LinkedHashMaps
				/* Hello Friend
				 * while nextLine != null
				 * 		Get actor name
				 * 		keep getting new media
				 * 		hit an empty line, store new Hashmap entry with current mediaMakername and all the media up to that point
				 * 		while(line isnt empty)
				 * 			get mediaMaker
				 * 			keep adding media
				 * 			nextLine
				 * 		hit empty line, store
				 * 			if the name is blank, then the file had two empty lines and should end
				 * 		nextLine
				 */
				/*
				//Calls method below to parse the file and put everything in the specified LinkedHashMap and the movies and episodes files.
				mainDriver.parseCreditsFile(fileNames.get(2), actorCredits, movies, episodesC);
				mainDriver.parseCreditsFile(fileNames.get(3), producerCredits, movies, episodesC);
				mainDriver.parseCreditsFile(fileNames.get(4), directorCredits, movies, episodesC);
				
			}
			else if(reply.equalsIgnoreCase("b"))
			{
				System.out.println("Please enter the filename of the binary file to be read.");
				String binaryFile = sc.nextLine();
				FileInputStream fileInputStream = new FileInputStream(binaryFile);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				MasterInput input1 = (MasterInput) objectInputStream.readObject();
				objectInputStream.close();
				movies = input1.getMovieCollection();
				seriesC = input1.getSeriesCollection();
				episodesC = input1.getEpisodeCollection();
				actorCredits = input1.getActorCredits();
				directorCredits = input1.getDirectorCredits();
				producerCredits = input1.getProducerCredits();
			}
		//End Build Loop
			while(!reply.equalsIgnoreCase("quit"))
			{
		//We want this question to be looped when the user picks to "Search again", we don't want to build the collections again.
				while(!reply.equalsIgnoreCase("quit"))
				{
				System.out.println("Search (m)edia or (p)eople");
				reply = sc.nextLine();
					if(reply.equalsIgnoreCase("m"))
					{
						DriverM.DriverMedia(movies, seriesC, episodesC, sc);
					}
					else if (reply.equalsIgnoreCase("p"))
					{
						DriverMM.DriverMediaMaker(actorCredits, directorCredits, producerCredits, sc);
					}
				}
			
			}
		System.out.println("Export to binary? y/n");
		reply = sc.nextLine();
		if(reply.equalsIgnoreCase("y"))
		{
			MasterInput export = new MasterInput(movies, seriesC, episodesC, actorCredits, producerCredits, directorCredits);
			System.out.println("Enter the filename to export to.");
			reply  = sc.nextLine();
			FileOutputStream fileOutputStream = new FileOutputStream(reply);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
			fileOutputStream);
			objectOutputStream.writeObject(export);
			//IN MEDIA MODEL FIGURE OUT HOW TO COPY MODEL AND THEN CALL THAT METHOD HERE
			//something about reading it in in the exact same order
			//so master input something then media model something?
			//File file= new File();
			//MediaModel model= new MediaModel();
			//model.writeModel(File file, model);;
		}
		System.out.println("Continue? y/n");
		reply = sc.nextLine();
		if(reply.equalsIgnoreCase("n"))
		{
			System.out.println("Thank you for using MDBParser.");
			System.exit(0);
		}
	}//End Main Method
	
	/**
	 * <P>
	 * A shorthand method that will parse a movieMakerCredits file and store media
	 * in the media collections and a LinkedHashMap to associate the media with the actors.
	 * </p>
	 * @param fileName	File to be parsed
	 * @param typeOfCredits		The credit type hashmap (Actor, producer, director)
	 * @param movies		The movies collection
	 * @param episodesC		The episodes collection
	 * @throws IOException		When the fileName can't be found
	 */
		/*
	public static void parseCreditsFile(String fileName, LinkedHashMap<String, ArrayList<Media>> typeOfCredits, MediaCollection movies, MediaCollection episodesC) throws IOException
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
		
		*/
	}
	
}//End Main Driver

