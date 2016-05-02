import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Project #4
 * CS 2334, Section 012
 * APR 21, 2016
 * <P>
 * An abstract class to store a list of a specific type of media. It also contains
 * methods to search the list of media by name or year released.
 * - NEW FOR VER 1.2 - The searchList is now negatively constructed rather than added to based on search results.
 * This allows for multiple searches to be inclusive 'both' rather than 'either'.
 * </P>
 * @version 1.2
 */
public class MediaCollection implements Serializable
{
	
	/**A UID for serialization*/
	private static final long serialVersionUID = 4970625347473553499L;

	/** The primary list of the specific type of media*/
	private ArrayList<Media> mediaList;
	
	/** The temporary list that matches the user's search*/
	private ArrayList<Media> searchList;
	
	/**
	 * <P>
	 * Method to build two ArrayLists.
	 * One containing the media objects and the other
	 * to contains the search media to show the user.
	 */
	public MediaCollection()
	{
		this.mediaList = new ArrayList<Media>();
		this.searchList = new ArrayList<Media>();
	}
	
	/**
	 * <P>
	 * Acts as a shorthand to add a new media to the collection.
	 * @param 		The media to be added to the list
	 */
	public void addMedia(Media media)
	{
		this.mediaList.add(media);
	}
	/**
	 * <p>Acts as a shorthand to remove media from the collection
	 * @param media	The media to be removed from the list
	 */
	public void removeMedia(Media media)
	{
		this.mediaList.remove(media);
		this.searchList.remove(media);
	}
	
	public ArrayList<Media> getMedia(){
		return this.mediaList;
	}
	/**
	 * <P>
	 * Ensures that a duplicate piece of media is not added.
	 * It is more CPU intensive, so this method is avoided from use the first time
	 * but is necessary to use when parsing the movie maker files.
	 * </P>
	 * @param media		The media in question to add, if it is not already present in the collection.
	 */
	public void addMediaNoDuplicate(Media media)
	{
		boolean isDuplicate = false;
		
		for(int i = 0; i < mediaList.size(); i++)
		{
			if(mediaList.get(i).mEquals(media))
			{
				isDuplicate = true;
			}
		}
		
		if(!isDuplicate)
		{
			mediaList.add(media);
		}
	}
	
	/**
	 * <P>
	 * Acts as a shorthand to get the object at the index specified.
	 * @param 		The integer count.
	 */
	public Media listNumber(int count)
	{
		return this.mediaList.get(count);
	}
	
	/**
	 * <P>
	 * Will search the ArrayList of movies that contain the String parameter passed
	 * and store them in a temporary ArrayList that matches the criteria.
	 * </P>
	 * @param name The keyword that will be compared to the names of a movie.
	 * @return Returns true if at least one match is found, false otherwise.
	 */
	public boolean searchKeyword(String key)
	{
		if(key == null)
			key = "";
		//searchList.clear();
		int searchIndex = 0;
		
		//Iterates through entire movie list, stores matches into temporary searchList
		for(int i = 0; i < mediaList.size(); i++)
		{
			if(this.listNumber(i).getName().toLowerCase().contains(key.toLowerCase()))
			{
				searchList.add(this.listNumber(i));
				searchIndex += 1;
				System.out.println(searchIndex + ". " + searchList.get(searchIndex-1).getName());
			}
		}
		
		return this.anyResults();
	}
	
	/**
	 * <P>
	 * Searches the list of media to find media that has a title that matches the keyword. Stores
	 * matches in the searchList.
	 * </P>
	 * @param key		The keyword to compare to titles.
	 * @return			True if at least 1 match is found, false if no matches are found.
	 */
	public boolean searchName(String key)
	{
		if(key == null)
			key = "";
		//searchList.clear();
		/*
		int searchIndex = 0;
		
		//Iterates through entire movie list, stores matches into temporary searchList
		for(int i = 0; i < mediaList.size(); i++)
		{
			if(this.listNumber(i).getName().toLowerCase().equals(key.toLowerCase()))
			{
				searchList.add(this.listNumber(i));
				searchIndex += 1;
				System.out.println(searchIndex + ". " + searchList.get(searchIndex-1).getName());
			}
		}
		*/
		//Sorts by name, which is the default sort method.
		Collections.sort(mediaList);
		//Searches the medialist for some arbitrary media object that has the name key. It will return the index of the media in the medialist that has the exact title.
		int index = Collections.binarySearch(mediaList, new Movie(key, -1, null));
		searchList.add(mediaList.get(index));
		return this.anyResults();
	}
	
	/**
	 * <P>
	 * A method that first determines the type of year search the user is performing.
	 * Then it searches based on if the user put in 1 year, several years, or a range of years.
	 * </P>
	 * @param yearPhrase The user input for the year(s)
	 * @return	True if at least one search is found, False if no searches are found.
	 */
	public boolean searchYears(String yearPhrase)
	{
		//searchList.clear();
		int searchIndex = 0;
		
		//Check to see what year method the user is searching by
		
		//Between set of years
		if(yearPhrase.contains("-"))
		{
			int yearLow;
			int yearHigh;
			
			yearLow = Integer.parseInt(yearPhrase.substring(0,4));
			yearHigh = Integer.parseInt(yearPhrase.substring(5,9));
			System.out.println(yearLow);
			System.out.println(yearHigh);
			//Iterates through entire movie list, stores matches into temporary searchList
			for(int i = 0; i < mediaList.size(); i++)
			{
				if(this.listNumber(i).getYear() >= yearLow && this.listNumber(i).getYear() <= yearHigh)
				{
					searchList.add(this.listNumber(i));
					searchIndex += 1;
					System.out.println(searchIndex + ". " + searchList.get(searchIndex-1).getName());
				}
			}
			return this.anyResults();
		}
		
		//Search multiple given years separated by comma
		else if(yearPhrase.contains(","))
		{
			ArrayList<Integer> years = new ArrayList<Integer>();
			String stringYears[];
			stringYears = yearPhrase.split(",");
			
			for(int i = 0; i < stringYears.length; i++)
			{
				stringYears[i] = stringYears[i].trim();
				years.add(Integer.parseInt(stringYears[i]));
				//System.out.println(years.get(i));
			}
			
			//For every year specified to search by...
			for(int i = 0; i < years.size(); i++)
			{
				//Compare the search year to every media's release year in the media list.
				for(int j = 0; j < mediaList.size(); j++)
				{
					if(years.get(i).equals(this.listNumber(j).getYear()))
					{
						//System.out.println(this.listNumber(j).getYear() + " and " + years.get(i));
						searchList.add(this.listNumber(j));
						searchIndex += 1;
						System.out.println(searchIndex + ". " + searchList.get(searchIndex-1).getName());
					}
				}
			}
			
			return this.anyResults();
		}
		
		//Search for just 1 year
		else
		{	
			int year = Integer.parseInt(yearPhrase);
			//Iterates through entire movie list, stores matches into temporary searchList
			for(int i = 0; i < mediaList.size(); i++)
			{
				if(this.listNumber(i).getYear() == year)
				{
					searchList.add(this.listNumber(i));
					searchIndex += 1;
					System.out.println(searchIndex + ". " + searchList.get(searchIndex-1).getName());
				}
			}
			
			return this.anyResults();
		}
	}
	
	/**
	 * <P>
	 * A method called by all of the search methods at the end to
	 * check if the searchList has any results.
	 * </P>
	 * @return False if there are no search results, True if there is at least
	 * one search result.
	 */
	public boolean anyResults()
	{
		if(searchList.size() == 0)
		{
			System.out.println("There are no matches for this search.");
			return false;
		}
		else
			return true;
	}
	/**
	 * <P>
	 * Clears the search list in every time a new search is called in order to eliminate
	 * duplicates.
	 */
	public void clearSearchList()
	{
		searchList.clear();
	}
	
	/**
	 * <P>
	 * clears the Entirity of the Object So that when the MediaCollection needs to be emptyied,
	 * there is no information hanging in the object.
	 * </P>
	 * <p> Added for the model class to simplify code within it.</P>
	 */
	public void clearAll()
	{
		mediaList.clear();
		searchList.clear();
	}

	/**
	 * <P>
	 * Sorts the search list.
	 */
	public void sort(String listType, boolean isDefault)
	{
		//Sorts the full media collection
		if(listType.equals("mediaList"))
		{
			if(isDefault)
			{
				Collections.sort(mediaList);
			} else
			{
			Collections.sort(mediaList, Media.MEDIA_YEAR_COMPARATOR);
			}
		} else //Sorts the search List
		{
			if(isDefault)
			{
				Collections.sort(searchList);
			} else
			{
			Collections.sort(searchList, Media.MEDIA_YEAR_COMPARATOR);
			}
		}
	}
	
	/**
	 * <P>
	 * Gets the media object at the index of the search result
	 * </P>
	 * @param count the index of the media
	 * @return the media object at the index
	 */
	public Media searchResult(int count)
	{
		return searchList.get(count);
	}
	
	/**
	 * <P>
	 * Returns the length of the temporary search list
	 * @return The length of the search list
	 */
	public int searchLength()
	{
		return searchList.size();
	}
	
	/**
	 * returns the length of the media size
	 * @return the length of the media list
	 */
	public int mediaLength(){
		return mediaList.size();
	}
	
	
}
