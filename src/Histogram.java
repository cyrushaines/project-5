import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
* This class will show the Histogram data associated with the 
* searched name.
*/

public class Histogram extends JComponent { 

	private ArrayList<Media> movActingCredits = new ArrayList<Media>();
	private ArrayList<Media> movDirectingCredits = new ArrayList<Media>();
	private ArrayList<Media> movProducingCredits = new ArrayList<Media>();
	private ArrayList<Media> serActingCredits = new ArrayList<Media>();
	private ArrayList<Media> serDirectingCredits = new ArrayList<Media>();
	private ArrayList<Media> serProducingCredits = new ArrayList<Media>();
	private ArrayList<Media> actorLists;
	private ArrayList<Media> producerLists;
	private ArrayList<Media> directorLists;
	private String makerName;
	
	public Histogram(String makerName, ArrayList<Media> actorLists, ArrayList<Media> producerLists, ArrayList<Media> directorLists)
	{
		this.actorLists = actorLists;  
		this.producerLists = producerLists;
		this.directorLists = directorLists;
		this.makerName = makerName;
		
		if (actorLists != null)
		{
			for (int i = 0; i < actorLists.size(); i++)
			{
				if(actorLists.get(i).getClass().toString().equals("class Movie"))
				{
					movActingCredits.add(actorLists.get(i));
				}
				else
				{
					serActingCredits.add(actorLists.get(i));
				}
			}
		}
		
		if (producerLists != null)
		{
			for(int j = 0; j < producerLists.size(); j++)
			{
				if(producerLists.get(j).getClass().toString().equals("class Movie"))
				{
					movProducingCredits.add(producerLists.get(j));
				}
				else
				{
					serProducingCredits.add(producerLists.get(j));
				}
			}
		}
		
		if (directorLists != null)
		{
			for(int k = 0; k < directorLists.size(); k++)
			{
				if(directorLists.get(k).getClass().toString().equals("class Movie"))
				{
					movDirectingCredits.add(directorLists.get(k));
				}
				else
				{
					serDirectingCredits.add(directorLists.get(k));
				}
			}
		}
		
	}

	// Paints a histogram
	
	   public void paintComponent(Graphics g) { 
	      // Cast to Graphics2D
	      Graphics2D graphicsObj = (Graphics2D) g;
	     /* 
	      // Draw 1st bar
	      graphicsObj.drawString("Graph", 10, 80);
	      for(int i = 0; i < actorYear.size(); i++){
	      Rectangle binRectangle1 = new Rectangle(10, 10, 200, 50); 
	      Color binColor1 = new Color(128, 128, 0);
	      graphicsObj.setColor(binColor1);
	      graphicsObj.fill(binRectangle1);
	      //String yearString = Integer.toString(actorYear.get(i));
	      graphicsObj.drawString("Graph", 10, 10);
	      }
	      //int barHeight = (int)(((double)count[i] / (double)maxCount) * (height - 55));
	      
	     // g.drawRect(x, height - 45 - barHeight, individualWidth, barHeight);
	      
	      
	      // Draw 2nd bar
	      Rectangle binRectangle2 = new Rectangle(10, 75, 150, 50); 
	      Color binColor2 = new Color(0, 200, 200);
	      graphicsObj.setColor(binColor2);
	      graphicsObj.fill(binRectangle2);
	      */  
	      Color binColor1 = new Color(128, 128, 0);
	      Color binColor2 = new Color(0, 200, 200);
	      Color binColor3 = new Color(100, 100, 100);
	      Color binColor4 = new Color(0, 25, 25);
	      Color binColor5 = new Color(0, 80, 255);
	      Color binColor6 = new Color(155, 10, 0);
	      for(int i = 1950; i < 2035; i++)
	      {
	    	  int field1length = 0;
	    	  int field2length = 0;
	    	  int field3length = 0;
	    	  int field4length = 0;
	    	  int field5length = 0;
	    	  int field6length = 0;
	    	  for(int j = 0; j < movActingCredits.size(); j++)
	    	  {
	    		  if(movActingCredits.get(j).getYear()== i)
	    		  {
	    			  field1length++;
	    		  }
	    	  }
	    	  for(int j = 0; j < movDirectingCredits.size(); j++)
	    	  {
	    		  if(movDirectingCredits.get(j).getYear()==i)
	    		  {
	    			  field2length++;
	    		  }
	    	  }
	    	  for(int j = 0; j < movProducingCredits.size(); j++)
	    	  {
	    		  if(movProducingCredits.get(j).getYear()==i)
	    		  {
	    			  field3length++;
	    		  }
	    	  }
	    	  for(int j = 0; j < serActingCredits.size(); j++)
	    	  {
	    		  if(serActingCredits.get(j).getYear()==i)
	    		  {
	    			  field4length++;
	    		  }
	    	  }
	    	  for(int j = 0; j < serDirectingCredits.size(); j++)
	    	  {
	    		  if(serDirectingCredits.get(j).getYear()==i)
	    		  {
	    			  field5length++;
	    		  }
	    	  }
	    	  for(int j = 0; j < serProducingCredits.size(); j++)
	    	  {
	    		  if(serProducingCredits.get(j).getYear()==i)
	    		  {
	    			  field6length++;
	    		  }
	    	  }
	    	  Rectangle field1 = new Rectangle(10, ((i-1950) * 10) + 5, field1length, 10);
	    	  graphicsObj.setColor(binColor1);
		      graphicsObj.fill(field1);
	    	  Rectangle field2 = new Rectangle(10 + field1length, ((i-1950) * 10) + 5, field2length, 10);
	    	  graphicsObj.setColor(binColor2);
		      graphicsObj.fill(field2);
	    	  Rectangle field3 = new Rectangle(10 + field1length + field2length, ((i-1950) * 10) + 5, field3length, 10);
	    	  graphicsObj.setColor(binColor3);
		      graphicsObj.fill(field3);
	    	  Rectangle field4 = new Rectangle(10 + field1length + field2length + field3length, ((i-1950) * 10) + 5, field4length, 10);
	    	  graphicsObj.setColor(binColor4);
		      graphicsObj.fill(field4);
	    	  Rectangle field5 = new Rectangle(10 + field1length + field2length + field3length + field4length, ((i-1950) * 10) + 5, field5length, 10);
	    	  graphicsObj.setColor(binColor5);
		      graphicsObj.fill(field5);
	    	  Rectangle field6 = new Rectangle(10 + field1length + field2length + field3length + field4length + field5length, ((i-1950) * 10) + 5, field6length, 10);
	    	  graphicsObj.setColor(binColor6);
		      graphicsObj.fill(field6);
	      }
	   }
	      public void buildHistogram() {
	    	      JFrame view = new JFrame();
	    	      //Histogram histogram = new Histogram("Name", new ArrayList<Media>(), new ArrayList<Media>(), new ArrayList<Media>());

	    	      view.setSize(800, 650);
	    	      view.setTitle("Histogram");
	    	      view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	      
	    	      // Add the Histogram to the frame
	    	      view.add(this);
	    	      view.setVisible(true);

	    	      return;
	    	   }
		
	   }




