import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Project #4
 * CS 2334 Section 12
 * APR 21, 2016
 * <p>
 * A dummy class used for loading and saving binary information for the media(maker) information.
 * </p>
 * @version 1.0
 */
public class MasterInput implements Serializable
{
	/** A UID for Serialization */
	private static final long serialVersionUID = -9027009641337547545L;

	private MediaCollection movDB;
	
	private MediaCollection epDB;
	
	private MediaCollection serDB;
	
	private LinkedHashMap<String, ArrayList<Media>> actorCreds;
	
	private LinkedHashMap<String, ArrayList<Media>> producerCreds;
	
	private LinkedHashMap<String, ArrayList<Media>> directorCreds;
	
	public MasterInput(MediaCollection movDB, MediaCollection serDB, MediaCollection epDB, LinkedHashMap<String, ArrayList<Media>> actorCreds,
			LinkedHashMap<String, ArrayList<Media>> producerCreds, LinkedHashMap<String, ArrayList<Media>> directorCreds)
			{
				this.movDB = movDB;
				this.epDB = epDB;
				this.serDB = serDB;
				
				this.actorCreds = actorCreds;
				this.directorCreds = directorCreds;
				this.producerCreds = producerCreds;
			}
	
	public MediaCollection getMovieCollection()
	{
		return this.movDB;
	}
	
	public MediaCollection getEpisodeCollection()
	{
		return this.epDB;
	}
	
	public MediaCollection getSeriesCollection()
	{
		return this.serDB;
	}
	
	public LinkedHashMap<String, ArrayList<Media>> getActorCredits()
	{
		return this.actorCreds;
	}
	
	public LinkedHashMap<String, ArrayList<Media>> getDirectorCredits()
	{
		return this.directorCreds;
	}
	
	public LinkedHashMap<String, ArrayList<Media>> getProducerCredits()
	{
		return this.producerCreds;
	}
}
