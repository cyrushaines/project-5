import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class SeriesController {
	
	private MediaModel model;
	//some type of view? generic? Do I have to do all separately?
	//I'll just start with series
	
	private SeriesView seriesView;
	
	/**
	 * Creates a new series Controller
	 */
	public SeriesController(){
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
	private class AddSeriesListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (model == null){
				return;
				//if model is empty, its useless
			}
			
			String title=seriesView.titleBox.getText();
			String startYear= seriesView.startYear.getText();
			String endYear=seriesView.endYear.getText();
			
			if(model.contains(title)){
			//
			}
			else{
				//series not in collection
				Series newSeries= new Series(title, startYear + "-" + endYear);
				//Testing series
				System.out.println(newSeries);
				model.addSeries(newSeries);
				//testing model
				System.out.println(model.getSeries().getMedia().toString());
			}
			seriesView.frame.dispose();
		}
		
	}
	
	private class CancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (model== null) return;
			
			seriesView.clearInputFields();
			seriesView.frame.dispose();
			
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
	
	public void setView(SeriesView newSeriesView){
		
		seriesView=newSeriesView;
		
		//Register listeners
		seriesView.addCancelButtonListener(new CancelListener());
		seriesView.addAddSeriesListener(new AddSeriesListener());
		
		//testing
		System.out.println("listeners registered in controller for series view");
	}
	
	public SeriesView getInputWindow(){
		return seriesView;
	}
	
	
	

}

