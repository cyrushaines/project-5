import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * Project #4
 * CS 2334, Section 012
 * APR 21, 2016
 * <P>
 * This class is the controller for the director data and contains
 * all associated methods.
 *   
 * </P>
 * @version 1.0
 */
public class DirectorController {

	
	private MediaModel model;
	
	private DirectorView directorView;
	
	public DirectorController(){
		//intentionally empty
	}
	
	
	/**
	 * 
	 * @param model
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
	/**
	 * 
	 * @param view
	 */
	public void setView(DirectorView view){
		directorView=view;
		
		directorView.addAddMovieButtonListener(new AddMovieListener());
		directorView.addAddEpisodeButtonListener(new AddEpisodeListener());
		directorView.addConfirmButtonListener(new ConfirmListener());
		directorView.addCancelButtonListener(new CancelListener());
		
	}
	
	/**
	 * 
	 * @return
	 */
	public DirectorView getInputView(){
		return directorView;
	}
	
	/**
	 * 
	 * 
	 *
	 */
	private class AddMovieListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (model==null){
				return;
			}
			
			//Opens a view to add a movie
			MovieView movieView= new MovieView();
			MovieController movieController= new MovieController();
			//MediaModel model= new MediaModel();
			movieController.setModel(model);
			movieController.setView(movieView);
			//Model and list of movies in the view should be updated somehow
		}
		
	}
	
	/**
	 * 
	 * 
	 *
	 */
	private class AddEpisodeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (model == null) return;
			//Opens a view to add an episode
			EpisodeAddView episodeView= new EpisodeAddView(model);
			EpisodeController episodeController= new EpisodeController();
			//MediaModel model= new MediaModel();
			episodeController.setModel(model);
			episodeController.setView(episodeView);
			
			//Model needs to be updated
			
		}
		
	}
	
	/**
	 * 
	 *
	 *
	 */
	public class ConfirmListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (model==null) return;
			
			ArrayList<Media> list= new ArrayList<Media>();
			list.addAll(directorView.movieList.getSelectedValuesList());
			list.addAll(directorView.episodeList.getSelectedValuesList());
			
			model.addDirector(directorView.firstNameField.getText(), list);
			//will add info to and update model
			directorView.dispose();
		}
		
	}
	
	/**
	 * 
	 * 
	 *
	 */
	private class CancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (model==null) return;
			
			directorView.clearInputFields();
			directorView.dispose();
		}
		
	}
}
