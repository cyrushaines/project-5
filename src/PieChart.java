import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
/**
* This class will generate the pie chart associated with the
* searched name.
*/
public class PieChart extends JFrame{
	private int movActingCredits;
	private int movDirectingCredits;
	private int movProducingCredits;
	private int serActingCredits;
	private int serDirectingCredits;
	private int serProducingCredits;
	private String makerName;
	
    //static String[] legendLabels = new String[] {"Movie Acting Credits","Movie Directing Credits",
    		//"Movie Producing Credits","Series Acting Credits","Series Producing Credits"};
	
	public PieChart (String makerName, ArrayList<Media> actorLists, ArrayList<Media> producerLists, ArrayList<Media> directorLists)
	{
		int movieCount = 0;
		int episodeCount = 0;
		
		if (actorLists !=  null)
		{
			
			this.makerName = makerName;
			for(int i = 0; i < actorLists.size(); i++)
			{
				if(actorLists.get(i).getClass().toString().equals("class Movie"))
				{
					movieCount++;
				}
				else
				{
					episodeCount++;
				}
			}
			movActingCredits = movieCount;
			serActingCredits = episodeCount;
		}
		else
		{
			movActingCredits = 0;
			serActingCredits = 0;
		}
		
		movieCount = 0;
		episodeCount = 0;
		
		if (producerLists != null)
		{
			for(int j = 0; j < producerLists.size(); j++)
			{
				if(producerLists.get(j).getClass().toString().equals("class Movie"))
				{
					movieCount++;
				}
				else
				{
					episodeCount++;
				}
			}
			movProducingCredits = movieCount;
			serProducingCredits = episodeCount;
		}
		else
		{
			movProducingCredits = 0;
			serProducingCredits = 0;
		}
		
		movieCount = 0;
		episodeCount = 0;
		
		if (directorLists != null)
		{
			for(int k = 0; k < directorLists.size(); k++)
			{
				if(directorLists.get(k).getClass().toString().equals("class Movie"))
				{
					movieCount++;
				}
				else
				{
					episodeCount++;
				}
			}
			movDirectingCredits = movieCount;
			serDirectingCredits = episodeCount;
		}
		else
		{
			movDirectingCredits = 0;
			serDirectingCredits = 0;
		}
		
	}
	ArrayList<PieSlice>slices = new ArrayList<>();
	Rectangle2D rectangle;
	
	   private class PieSlice{
	   double value;
	   Color color;
	   
		   public PieSlice(double value, Color color){
		   
		   this.color = color;
		   this.value = value;
		   }
	   }
	   
	   public void addSlice(double value, Color color){
		   
		   slices.add(new PieSlice(value, color));
	   }
	   
	   public void createShape(){
		   this.setSize(800,800);
		   this.setVisible(true);
		   rectangle = new Rectangle(100, 100, 700, 650);
	   }
	   
	   public void drawChart(Graphics2D g, Rectangle2D area) {
		   double pieSize = 0;
		   for (PieSlice slice : slices) {
			   pieSize += slice.value;
		   }
		   double currentValue = 0;
		   int startAngle = 0;
		   int arcAngle = 0;
		   
		   for(PieSlice slice: slices){
			   startAngle = (int)(currentValue*360/pieSize);
			   arcAngle = (int)(slice.value*360/pieSize);
			   if (slice.equals(slices.get(slices.size()-1))) {
				   arcAngle = 360 - startAngle;
			   }
		   
		   g.setColor(slice.color);
		   
		   g.fillArc((int)area.getMinX(), (int)area.getMinY(), (int)area.getWidth(), 
				   (int)area.getHeight(), startAngle, arcAngle);
		   
		   currentValue += slice.value;
		   }
	   }
		
	   @Override
	   public void paint(Graphics g) {
	   drawChart((Graphics2D) this.getGraphics(), rectangle);
	   }
	   
	   public static void PieChartBuilder(PieChart chart) 
	   {
		  //PieChart chart = new PieChart("Hello", new ArrayList<Media>(), new ArrayList<Media>(), new ArrayList<Media>());
		  chart.createShape();
		  chart.addSlice(chart.movActingCredits, Color.RED);
		  chart.addSlice(chart.movDirectingCredits, Color.BLUE);
		  chart.addSlice(chart.movProducingCredits, Color.GREEN);
		  chart.addSlice(chart.serActingCredits, Color.ORANGE);
		  chart.addSlice(chart.serDirectingCredits, Color.CYAN);
		  chart.addSlice(chart.serProducingCredits,  Color.BLACK);
		  
		  
		 
	   }
		  
		  
		  
	    
	}


