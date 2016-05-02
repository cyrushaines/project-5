import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
/**
 * Project #4
 * CS 2334, Section 012
 * APR 21, 2016
 * <P>
 * This class is the controller for the actor data and contains
 * all associated methods.
 *   
 * </P>
 * @version 1.0
 */
public class ActorController
{
	/**  */
	private MediaModel model; // 
	/**  */
	private ActorView actorView;
	
	/**
	 * 
	 */
	public ActorController()
	{
		//intentionally empty
	}
	
	/**
	 * 
	 * @param model
	 */
	public void setModel(MediaModel model)
	{
		this.model= model;
	}
	
	/**
	 * 
	 * @return model
	 */
	public MediaModel getModel()
	{
		return model;
	}
	
	/**
	 * 
	 * @param view
	 */
	public void setView(ActorView view)
	{
		actorView = view;
		
		actorView.addAddMovieButtonListener(new AddMovieListener());
		actorView.addAddEpisodeButtonListener(new AddEpisodeListener());
		actorView.addConfirmButtonListener(new ConfirmListener());
		actorView.addCancelButtonListener(new CancelListener());
	}
	
	/**
	 * 
	 * @return
	 */
	public ActorView getInputView()
	{
		return actorView;
	}

	/** 
	 *
	 */
	private class AddMovieListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
			{
				return;
			}
			
			//Opens a view to add a movie
			//new MovieView();
			//System.out.println("ping");
			MovieView movieView= new MovieView();
			MovieController movieController= new MovieController();
			movieController.setModel(model);
			movieController.setView(movieView);
			//Model and list of movies in the actor view should be updated somehow
			
			//call an actionEvent?
		}
	}
	
	/**
	 * 
	 */
	private class AddEpisodeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
			{
				return;
			}
			
			//Opens a view to add an episode
			//new EpisodeAddView();
			EpisodeAddView episodeView= new EpisodeAddView(model);
			EpisodeController episodeController= new EpisodeController();
			//MediaModel model= new MediaModel();
			episodeController.setModel(model);
			episodeController.setView(episodeView);
			//Model and list of episodes in the actor view should be updated somehow
		}
		
	}
	
	private class ConfirmListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
			{
				return;
			}
			
			
			ArrayList<Media> list = new ArrayList<Media>();
			list.addAll(actorView.movieList.getSelectedValuesList());
			list.addAll(actorView.episodeList.getSelectedValuesList());
			
			model.addActor(actorView.firstNameField.getText(), list);
			//Will add info to and update model
			actorView.dispose();
		}
		
	}
	
	/**
	 * 
	 */
	private class CancelListener implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (model== null)return;
			
			actorView.clearInputFields();
			actorView.dispose();
		}
	
	}
}
