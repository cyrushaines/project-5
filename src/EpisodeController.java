import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
/**
 * Project #4
 * CS 2334, Section 012
 * APR 21, 2016
 * <P>
 * This class is the controller for the episode data and contains
 * all associated methods.
 *   
 * </P>
 * @version 1.0
 */
public class EpisodeController {
	private MediaModel model;
	//some type of view? generic? Do I have to do all separately?
	//I'll just start with series
	
	private EpisodeAddView episodeView;
	
	/**
	 * Creates a new Media Controller
	 */
	public EpisodeController(){
		//intentionally empty, I think
	}
	
	/**
	 * This provides  way to create a new series (if i did this right)
	 * TODO: add it to the other series lists?
	 * 
	 *
	 */
	//RIGHT NOW ONLY PROGRAMMED FOR SERIES
	//should this be confirm listener? idk
	private class AddEpisodeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (model == null){
				return;
				//if model is empty, its useless
			}
			String series = (String) episodeView.selectSeries.getSelectedItem();
			String title= episodeView.titleBox.getText();
			String startYear= episodeView.yearBox.getText();
			String seasonNum = episodeView.seasonBox.getText();
			String episodeNum = episodeView.episodeBox.getText();
			boolean suspended = episodeView.isSuspended;
			
			MediaCollection episodes = model.getEpisodes();
			ArrayList<Media> episodesList = episodes.getMedia();
			ArrayList<String> episodesTitles = new ArrayList<String>();
			
			for(int index = 0; index < episodesList.size(); ++index){
				String temp = episodesList.get(index).getEpisodeTitle();
				episodesTitles.add(temp);
			}
			
			if(episodesTitles.contains(title)){
				//Ep is already there
				
				Object[] options= {"Cancel", "Replace"};
				int answer= JOptionPane.showOptionDialog(null, "Please choose one", "",
						JOptionPane.DEFAULT_OPTION, JOptionPane.CANCEL_OPTION, null, options, options[0]);
				
				int response=answer;
				
				
				if(response==0){
					return;
				}
				
				else if (response==1){
					//Call mutator
					
					
					//COULD BE AN ERROR CHECK THIS
					int releaseYear = Integer.getInteger(startYear);
					Episodes newEpisodes= new Episodes(series, title, releaseYear);
					model.addEpisode(newEpisodes);
					//model.replaceSeries(newSeries);
					//error bc it hasnt been created yet, do we
					//even want this?
				}
				else{
					//TODO: error case
				}
				
			}
			else{
				//episode not in collection
				int releaseYear = Integer.decode(startYear);
				Episodes newEpisode= new Episodes(series, title, releaseYear);
				model.addEpisode(newEpisode);
			}
			
			System.out.print(model.getEpisodes().getMedia().toString());
			episodeView.frame.dispose();
		}
		
		
	}
	
	private class CancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (model== null) return;
			
			episodeView.clearInputFields();
			//TODO: close window in this listener
			episodeView.frame.dispose();
		}
		
	}
	
	/*private class ConfirmListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	*/
	//TODO: create other classes
	
	/**
	 * 
	 * @param model
	 * 	to set the model
	 */
	public void setModel(MediaModel model){
		this.model= model;
	}
	
	/**
	 * 
	 * @return model
	 */
	public MediaModel getModel(){
		return model;
	}
	
	public void setView(EpisodeAddView newEpisodeAddView){
		
		episodeView=newEpisodeAddView;
		
		//Register listeners
		episodeView.addCancelButtonListener(new CancelListener());
		episodeView.addAddEpisodeButtonListener(new AddEpisodeListener());
		
		//testing
		System.out.println("listeners registered in controller for series view");
	}
	
	public EpisodeAddView getInputWindow(){
		return episodeView;
	}
	
	
	
}
