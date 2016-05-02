import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 
 * Project #4
 * CS 2334, Section 012
 * APR 21, 2016
 *
 *This is the model for the MDB. It contains MediaCollections for Media Objects and Linked 
 *HashMaps of Strings for The Actors, Directors and Producers. 
 *Methods for adding/removing single objects  or clearing entire lists or the whole model exist as well
 *An ArrayList of ActionListeners has been created for the when stuff is added.
 *
 *TODO: create methods for adding entire collections or LinkedHashMaps.
 */
public class MediaModel implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -4068716849382956209L;

	/**
	 * List to keep track of who is registered to listen for events from the SeriesModel.
	 */
	private ArrayList<ActionListener> actionListenerList;
	/** Contains the Movie Information*/
	private MediaCollection movDB = new MediaCollection();
	/** Contains the episode Information*/
	private MediaCollection epDB = new MediaCollection();
	/**Contains the Series information*/
	private MediaCollection serDB = new MediaCollection();
	/**Contains the Information of Actors */
	private LinkedHashMap<String, ArrayList<Media>> actorCreds = new LinkedHashMap<String, ArrayList<Media>>();
	/**Contains the Information of Producers */
	private LinkedHashMap<String, ArrayList<Media>> producerCreds= new LinkedHashMap<String, ArrayList<Media>>();
	/**Contains the Information of Directors */
	private LinkedHashMap<String, ArrayList<Media>> directorCreds= new LinkedHashMap<String, ArrayList<Media>>();

	/**
	 * This is the constructor for the model. It holds the data of all Media and Makers
	 */
	public MediaModel(){


	}

	/**
	 * This method returns an ArrayList of all Movies
	 * @return ArrayList of Movies
	 */
	public MediaCollection getMovies(){
		return this.movDB;
	}
	/**
	 * This sets the movie MediaCollection.
	 * @param movies
	 */
	public void setMovies(MediaCollection movies){
		this.movDB = movies;

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Movie Collection Added"));
	}
	/**
	 * This method returns an ArrayList of all Series
	 * @return ArrayList Of series
	 */
	public MediaCollection getSeries(){
		return this.serDB;
	}
	/**
	 * This sets the Series Collections
	 * @param series
	 */
	public void setSeries(MediaCollection series){
		this.serDB = series;

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Series Collection Added"));
	}
	/**
	 * This method returns an MediaCollection of all episodes
	 * @return ArrayList Of epsidoes
	 */
	public MediaCollection getEpisodes(){
		return this.epDB;
	}
	/**
	 * This methods sets the Episodes Collection
	 * @param episodes
	 */
	public void setEpisodes(MediaCollection episodes){
		this.epDB = episodes;

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Episodes Collection Added"));
	}
	/**
	 * This method returns an LinkedHashMap of all Actors
	 * @return ArrayList Of actors
	 */
	public LinkedHashMap<String, ArrayList<Media>> getActors(){
		return this.actorCreds;
	}
	/**
	 * This sets the actorCred hashmap
	 * @param actors
	 */
	public void setActors(LinkedHashMap<String, ArrayList<Media>> actors){
		this.actorCreds = actors;

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Actors HashMap Added"));

	}
	/**
	 * This method returns an ArrayList of all Director
	 * @return ArrayList Of series
	 */
	public LinkedHashMap<String, ArrayList<Media>> getDirectors(){
		return this.directorCreds;
	}

	public void setDirectors(LinkedHashMap<String, ArrayList<Media>> directors){
		this.directorCreds = directors;

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Directors HashMap Added"));
	}
	/**
	 * This method returns an LinkedHashMap of all PRoducers
	 * @return ArrayList Of Producers
	 */
	public LinkedHashMap<String, ArrayList<Media>> getProducers(){
		return this.producerCreds;
	}

	public void setProducers(LinkedHashMap<String, ArrayList<Media>> producers){
		this.producerCreds = producers;

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Producers HashMap Added"));
	}
	/**
	 * This method adds a Movie to the model
	 * @param movie
	 */
	public void addMovie(Movie movie){
		//TODO: figure out how to do this with the differend view stuff
		movDB.addMediaNoDuplicate(movie);

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add Movie"));
	}

	/**
	 * This method adds a Series to the model
	 * @param series
	 */
	public void addSeries(Series series){
		//TODO: figure out how to do this with the different view stuff
		serDB.addMediaNoDuplicate(series);

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add Series"));
	}

	/**
	 * This method adds an Episode to the model.
	 * @param episode
	 */
	public void addEpisode(Episodes episode){
		//TODO: figure out how to do this with the different view stuff
		epDB.addMediaNoDuplicate(episode);

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add Episode"));
	}

	/**
	 * This method will add an Actor to the actorCreds LinkedHashMap
	 * @param lineOfActor	a line of string that holds the info of an actor
	 */
	public void addActor(String lineOfActor, ArrayList<Media> selectedMedia){
		String actor =lineOfActor;
		ArrayList<Media> actingCredits = selectedMedia;

		//The below code is if the above problem is figured out.
		if(actorCreds.containsKey(actor)){
			ArrayList<Media> tempList = actorCreds.get(actor);
			actingCredits.addAll(tempList);
		}

		actorCreds.put(actor, actingCredits);

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add Actor"));

	}

	/**
	 * This method adds a Director to the model
	 * @param lineOfDirector
	 */
	public void addDirector(String lineOfDirector, ArrayList<Media> selectedMedia){
		String director = lineOfDirector;
		ArrayList<Media> directingCredits = selectedMedia;

		//TODO: Must find a way to get all the Credits of Stuff and add to the Director Credits list.
		//Continued: Not sure how to use MediaParser2 for this predicament

		//The below code is if the above problem is figured out.
		if(directorCreds.containsKey(director)){
			ArrayList<Media> tempList = directorCreds.get(director);
			directingCredits.addAll(tempList);
		}

		directorCreds.put(director, directingCredits);

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add Director"));

	}
	/**
	 * This method adds a producer to the model
	 * @param lineOfProducer
	 */
	public void addProducer(String lineOfProducer, ArrayList<Media> selectedMedia){
		String producer =lineOfProducer;
		ArrayList<Media> producerCredits = selectedMedia;

		//TODO: Must find a way to get all the Credits of Stuff and add to the Producer Credits list.
		//Continued: Not sure how to use MediaParser2 for this predicament

		//The below code is if the above problem is figured out.
		if(producerCreds.containsKey(producer)){
			ArrayList<Media> tempList = producerCreds.get(producer);
			producerCredits.addAll(tempList);
		}

		producerCreds.put(producer, producerCredits);

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add Producer"));

	}

	/**
	 * This method removes an episode from the Model
	 * @param epi
	 */
	public void removeEpisode(Episodes epi){
		epDB.removeMedia(epi);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Remove Episode"));
	}

	/**
	 * This Method removes a Series from the model
	 * @param series
	 */
	public void removeSeries(Series series){
		serDB.removeMedia(series);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Remove Series"));
	}
	/**
	 * This method removes a Movie from the model
	 * @param movie
	 */
	public void removeMovie(Movie movie){
		movDB.removeMedia(movie);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Remove Movie"));
	}
	/**
	 * This method removes an Actor from the model
	 * @param name
	 */
	public void removeActor(String name){
		actorCreds.remove(name);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Remove Actor"));
	}
	/**
	 * This method removes a Director from the model
	 * @param name
	 */
	public void removeDirector(String name){
		directorCreds.remove(name);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Remove Director"));
	}
	/**
	 * This method removes a Producer from the model
	 * @param name
	 */
	public void removeProducer(String name){
		producerCreds.remove(name);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Remove Producer"));
	}

	/**
	 * This method clears the Movie Media Collection.
	 * TODO: Determine whether processEvent needs to be called
	 */
	public void clearMovie(){
		movDB.clearAll();
	}
	/**
	 * This method clears the Series Media Collection.
	 * TODO: Determine whether processEvent needs to be called
	 */
	public void clearSeries(){
		serDB.clearAll();
	}
	/**
	 * This method clears the Episode Media Collection.
	 * TODO: Determine whether processEvent needs to be called
	 */
	public void clearEpisodes(){
		epDB.clearAll();
	}
	/**
	 * This method clears the ActorsCredit Hashmap.
	 * TODO: Determine whether processEvent needs to be called
	 */
	public void clearActors(){
		actorCreds.clear();
	}
	/**
	 * This method clears the ActorsCredit Hashmap.
	 * TODO: Determine whether processEvent needs to be called
	 */
	public void clearDirectors(){
		directorCreds.clear();
	}
	/**
	 * This method clears the ActorsCredit Hashmap.
	 * TODO: Determine whether processEvent needs to be called
	 */
	public void clearProducers(){
		producerCreds.clear();
	}


	/**
	 * This method clears Every list and map of all data
	 * 
	 * Note: Not sure how the actionListeners will work here. need further discussion to insure correctness
	 */
	public void clearAll(){
		movDB.clearAll();
		serDB.clearAll();
		epDB.clearAll();
		actorCreds.clear();
		directorCreds.clear();
		producerCreds.clear();

	}


	/**
	 * Register an ActionListener
	 * @param l
	 */
	public synchronized void addActionListener(ActionListener l) {
		if (actionListenerList == null)
			actionListenerList = new ArrayList<ActionListener>();
		actionListenerList.add(l);
	}

	/**
	 * Remove an action event listener.
	 * @param l
	 */
	public synchronized void removeActionListener(ActionListener l) {
		if (actionListenerList != null && actionListenerList.contains(l))
			actionListenerList.remove(l);
	}


	/**
	 * Fire event.
	 * @param e 
	 */
	private void processEvent(ActionEvent e) {
		ArrayList<ActionListener> list;

		synchronized (this) {
			if (actionListenerList == null) return;
			list = (ArrayList<ActionListener>)actionListenerList.clone();
		}

		for (int i = 0; i < list.size(); i++) {
			ActionListener listener = list.get(i);
			listener.actionPerformed(e);
		}
	}


	/**
	 * <p>This method creates a file through FileOutputStream and ObjectOutputStream.</p>
	 * 
	 * @param filename
	 * @param series
	 * @throws IOException 
	 */
	public static void writeModel(File filename, MediaModel model) throws IOException{

		FileOutputStream fileOutputStream = new FileOutputStream(filename);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				fileOutputStream);
		objectOutputStream.writeObject(model);
		objectOutputStream.close();

	}
	/**
	 * <p>Reads a file to create a MediaModel object</p>
	 * 
	 * @param filename
	 * @return an series
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static MediaModel readModel(File filename) throws IOException, ClassNotFoundException{

		FileInputStream fileInputStream = new FileInputStream(filename);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		MediaModel model = (MediaModel) objectInputStream.readObject();
		objectInputStream.close();
		return model;
	}
	/**
	 * This method parses a text file.
	 * @param fileName
	 * @throws IOException
	 */

	public void readTextMovie(File fileName) throws IOException{

		MDBParser parseMovies = new MDBParser(fileName, movDB);

		parseMovies.buildMovieCollection();

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add Movie From Text"));


	}


	/**
	 * This method reads in a text file for the Series and Episode database
	 * @param fileName
	 * @throws IOException
	 */
	public void readTextSeries(File fileName) throws IOException{

		MDBParser parseMovies = new MDBParser(fileName, serDB, epDB);

		parseMovies.buildSeriesCollection();

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add Series and Episodes From Text"));


	}

	/**
	 * This method reads in an actor file
	 * @param fileName
	 * @throws IOException
	 */
	public void readActorFile(File fileName) throws IOException{
		/*FileReader file = new FileReader(fileName);
		BufferedReader fileReader = new BufferedReader(file);

		String mediaMaker = "";
		ArrayList<Media> personsMedia = new ArrayList<Media>();
		while(fileReader.ready()){
			String line = fileReader.readLine();
			if(line.isEmpty()){
				continue;
			}
			else if(MediaParser2.containsMediaMaker(line)){
				mediaMaker = MediaParser2.getMediaMaker(line);
			}
			else{
				Media media = MediaParser2.parseMedia(line);
				personsMedia.add(media);
			}
		}
		actorCreds.put(mediaMaker, personsMedia);


		file.close();
		fileReader.close();*/
		
		MediaParser2.parseCreditsFile(fileName, actorCreds, movDB, epDB);

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add Actors From Text"));
	}
	/**
	 * This method reads in a Director File
	 * @param fileName
	 * @throws IOException
	 */
	public void readDirectorFile(File fileName) throws IOException{
		/*FileReader file = new FileReader(fileName);
		BufferedReader fileReader = new BufferedReader(file);

		String mediaMaker = "";
		ArrayList<Media> personsMedia = new ArrayList<Media>();
		while(fileReader.ready()){
			String line = fileReader.readLine();
				if(line.isEmpty()){
					continue;
				}
				if(MediaParser2.containsMediaMaker(line)){
					mediaMaker = MediaParser2.getMediaMaker(line);
				}
				else{
					Media media = MediaParser2.parseMedia(line);
					personsMedia.add(media);
				}
			}
			directorCreds.put(mediaMaker, personsMedia);
		

		file.close();
		fileReader.close();*/
		
		MediaParser2.parseCreditsFile(fileName, directorCreds, movDB, epDB);

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add Directors From Text"));
	}
	/**
	 * This method reads a producer file
	 * @param fileName
	 * @throws IOException
	 */
	public void readProducerFile(File fileName) throws IOException{
		/*FileReader file = new FileReader(fileName);
		BufferedReader fileReader = new BufferedReader(file);

		String mediaMaker = "";
		ArrayList<Media> personsMedia = new ArrayList<Media>();
		while(fileReader.ready()){
			String line = fileReader.readLine();
			while(!MediaParser2.isLineEmpty(line)){
				if(MediaParser2.containsMediaMaker(line)){
					mediaMaker = MediaParser2.getMediaMaker(line);
				}
				else{
					Media media = MediaParser2.parseMedia(line);
					personsMedia.add(media);
				}
			}
			producerCreds.put(mediaMaker, personsMedia);
		}

		file.close();
		fileReader.close();*/
		
		MediaParser2.parseCreditsFile(fileName, producerCreds, movDB, epDB);

		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add Producers From Text"));
	}

	//DONT FORGET ABOUT THIS DO STUFF 
	public boolean contains(String title) {
		// TODO Auto-generated method stub
		return false;
	}
}

