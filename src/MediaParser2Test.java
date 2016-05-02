

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;




public class MediaParser2Test {
/*
	@Test
	public void test() 
	{
		String zero = "";
		//System.out.println(zero.isEmpty());
		String one = "Jolie, Angelina		10 Years of Tomb Raider: A GameTap Retrospective (2008) (TV)  (archive footage)  [Herself]";
		
		//Test to see if the containsMediaMaker is working
		assertEquals(MediaParser2.containsMediaMaker(one), true);
		
		String maker = MediaParser2.getMediaMaker(one);
		one = one.replace(maker, "");
		one = one.trim();
		//System.out.println(one);
		
		Media parseMov = MediaParser2.parseMedia(one);
		Movie mov2 = new Movie("10 Years of Tomb Raider: A GameTap Retrospective", 2008, "Television Release");
		//System.out.println(mov2.getClass().toString());
		//Test to see if two movies are the same
		//System.out.println(parseMov);
		//System.out.println(mov2);
		assertEquals(parseMov.mEquals(mov2), true);
		
		//Test a case where the containsMediaMaker should be false
		String two = "			\"Charlie Rose\" (1991) {(2007-06-19)}  [Herself - Guest]";
		assertEquals(MediaParser2.containsMediaMaker(two), false);
		
		two = two.trim();
		
		Media parseMov2 = MediaParser2.parseMedia(two);
		Episodes ep2 = new Episodes("Charlie Rose", "2007-06-19", 2007);
		//System.out.println(ep2.getClass().toString());
		//Test to see if two episodes are the same
		//System.out.println(parseMov2);
		//System.out.println(ep2);
		assertEquals(parseMov2.mEquals(ep2), true);
		
		//Test a false case for mEquals
		assertEquals(parseMov2.mEquals(parseMov), false);
		
		String three = "\"Dateline NBC\" (1992) {On the Hunt/Wild Night/Meet Mrs. Smith (#12.46)}  [Herself (segment \"Meet Mrs. Smith\")]";
		Media parseMov3 = MediaParser2.parseMedia(three);
		Episodes ep3 = new Episodes("Dateline NBC", "On the Hunt/Wild Night/Meet Mrs. Smith", -1);
		
		//Test another format
		//System.out.println(parseMov3);
		//System.out.println(ep3);
		assertEquals(parseMov3.mEquals(ep3), true);
		
		String four = "\"Janela Indiscreta\" (2010) {(#1.25)}  [Herself]";
		Media parseMov4 = MediaParser2.parseMedia(four);
		Episodes ep4 = new Episodes("Janela Indiscreta", "", -1);
		//System.out.println(parseMov4);
		//System.out.println(ep4);
		assertEquals(parseMov4.mEquals(ep4), true);
	}
	
	@Test
	public void testBuildLoop() throws IOException
	{
		LinkedHashMap<String, ArrayList<Media>> actorCredits = new LinkedHashMap<String, ArrayList<Media>>();
		LinkedHashMap<String, ArrayList<Media>> directingCredits = new LinkedHashMap<String, ArrayList<Media>>();
		LinkedHashMap<String, ArrayList<Media>> producingCredits = new LinkedHashMap<String, ArrayList<Media>>();
		MediaCollection movies = new MediaCollection();
		MediaCollection episodes = new MediaCollection();
		MediaCollection series = new MediaCollection();
		
		mainDriver.parseCreditsFile("SomeActors.txt", actorCredits, movies, episodes);
		mainDriver.parseCreditsFile("SomeDirectors.txt", directingCredits, movies, episodes);
		mainDriver.parseCreditsFile("SomeProducers.txt", producingCredits, movies, episodes);
		
		ArrayList<Media> kubrick = actorCredits.get("Jolie, Angelina");
		
		for(int i = 0; i < kubrick.size(); i++)
		{
			System.out.println(kubrick.get(i));
		}
	}*/
	
	@Test
	public void EpisodeAddView(){
		MediaModel model= new MediaModel();
		EpisodeAddView testView = new EpisodeAddView(model);
	}
}

