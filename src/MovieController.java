import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *
 * Project #4
 * CS 2334, Section 012
 * APR 21, 2016
 * <P>
 * This class is the controller for the movie data and contains
 * all associated methods.
 *   
 * </P>
 * @version 1.0
 */
public class MovieController {


	private MediaModel model;

	private MovieView movieView;

	/**
	 * creates a new movie controller
	 */
	public MovieController(){
		//intentionally empty
	}

	/**
	 * 
	 * this provides a way to create a new movie
	 *
	 */
	private class AddMovieListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (model==null) return;
			//bc this means the model is empty and we dont need that in our lives

			String title=movieView.titleBox.getText();
			String yearString=movieView.yearBox.getText();
			String format=movieView.formatBox.getText();
			int year=Integer.parseInt(yearString);

			if(model.contains(title)){
				//something?
				
				
			}
			else{

				Movie newMovie= new Movie(title, year, format);
				//testing

				//System.out.println(newMovie);
				model.addMovie(newMovie);
				//more testing
				System.out.println(model.getMovies().getMedia().toString());
				//practice

			}

			movieView.frame.dispose();
		}

	}

	private class CancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (model==null) return;

			movieView.clearInputFields();
			movieView.frame.dispose();
			


		}

	}
/*
	private class exportBinary implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				//rename file
				MediaModel.writeModel("something", model);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	*/

	//TODO: create other classes?

	/**
	 * 
	 * @param model
	 */
	public void setModel(MediaModel model){
		this.model= model;
	}

	/**
	 * 
	 * @return
	 */
	public MediaModel getModel(){
		return model;
	}

	public void setView(MovieView newMovieView){
		movieView=newMovieView;

		//register listeners
		movieView.addCancelButtonListener(new CancelListener());
		movieView.addAddMovieListener(new AddMovieListener());
				

		
	}
}

