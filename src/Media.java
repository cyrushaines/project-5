import java.io.Serializable;
import java.util.Comparator;
/**
* Project #4
* CS 2334, Section 12
* APR 21, 2016
* <P>
*An abstract class that allows the comparison of the media objects by names and years.
*</P>
*@version 1.1
**/
public abstract class Media implements Comparable<Media>, Serializable
{
	/** A UID for Serialization */
	private static final long serialVersionUID = 8152462723798503451L;
	/** This comparator allows searching by year */
	public static final Comparator<Media> MEDIA_YEAR_COMPARATOR = new MediaYearComparator();
	/** Allows access to get the name of the object */
	public abstract String getName();
	/** Allows access to get the year of the object */
	public abstract int getYear();
	/** Allows access to the episode name, or will return "" if it's not an episode */
	public abstract String getEpisodeTitle();
	/**
	 * <P>
	 * Method to compare the media object by names in order to sort them.
	 * </P>
	 * @param o the other media object to be compared to
	 * @return the comparison of the object's names.
	 */
	public int compareTo(Media o)
	{
		return this.getName().compareTo(o.getName());
	}
	
	/**
	 * <P>
	 * Compares two media objects by year instead of the default by-year
	 * </P>
	 */
	private static class MediaYearComparator implements Comparator<Media>
	{
		/**
		 * <P>
		 * Compares two media objects by year, and if the year matches, it compares by name
		 * </P>
		 * @param media The first media to compare to
		 * @param anotherMedia The other media to compare to
		 *@return the comparison of the object's name or years if they have the same name.
		 */
		public int compare(Media media, Media anotherMedia) {
			Integer year1 = media.getYear();
			Integer year2 = anotherMedia.getYear();

			if (!(year1.equals(year2))) 
				return year1.compareTo(year2);
			//If the years match, use default comparator
			else
			{
				return media.compareTo(anotherMedia);
			}
		}
	}
	
	/**
	 * <P>
	 * A method to test if two media types have identical data
	 * </P>
	 * @param o		The other media type to compare to
	 * @return		True if the media are the same, false if not.
	 */
	public boolean mEquals(Media o)
	{
		/*if(this.getEpisodeTitle().equals(o.getEpisodeTitle()) && this.getName().equals(o.getName()) && this.getYear() == o.getYear())
		{
			return true;
		}
		else
		{
			return false;
		}
		*/
		if(this.toString().equals(o.toString()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
