import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Controller for the main view
 */
public class SelectionController
{
	
	private MediaModel model;
	private MediaView view;
	
	/**
	 * constructor
	 */
	public SelectionController()
	{
		
	}
	
	/**
	 * Updates the current model
	 * 
	 * @param m		A MediaModel object
	 */
	public void setModel(MediaModel m)
	{
		model = m;
	}
	
	/**
	 * Accessor for the current model
	 * 
	 * @return		The current model in this object
	 */
	public MediaModel getModel()
	{
		return model;
	}
	
	/**
	 * 
	 * @param view
	 */
	public void setView(MediaView view)
	{
		this.view = view;
		
		this.view.addJListListener(new DataListListener());
		
		
		this.view.addSaveFileListener(new SaveFileListener());
		this.view.addLoadFileListener(new LoadFileListener());
		this.view.addImportFileListener(new ImportFileListener());
		this.view.addExportFileListener(new ExportFileListener());
		
		this.view.addAddItemListener(new AddItemListener());
		this.view.addEditItemListener(new EditItemListener());
		this.view.addDeleteItemListener(new DeleteItemListener());
		this.view.addClearItemListener(new ClearItemListener());
		this.view.addClearAllItemListener(new ClearAllItemListener());
		
		this.view.addPieChartItemListener(new PieChartItemListener());
		this.view.addHistogramItemListener(new HistogramItemListener());
		
		this.view.addMediaButtonListener(new MediaButtonListener());
		this.view.addMovieButtonListener(new MovieButtonListener());
		this.view.addSeriesButtonListener(new SeriesButtonListener());
		this.view.addEpisodesButtonListener(new EpisodeButtonListener());
		this.view.addMakerButtonListener(new MakerButtonListener());
		this.view.addActorButtonListener(new ActorButtonListener());
		this.view.addDirectorButtonListener(new DirectorButtonListener());
		this.view.addProducerButtonListener(new ProducerButtonListener());
	}
	
	/**
	 * 
	 * Data listener class
	 *
	 */
	private class DataListListener implements ListSelectionListener
	{
		
		@Override
		/**
		 * An ActionListener
		 */
		public void valueChanged (ListSelectionEvent e)
		{
			if (!view.dataList.isSelectionEmpty() && (view.makerButton.isSelected()
														|| view.actorButton.isSelected()
															|| view.directorButton.isSelected()
																||view.producerButton.isSelected()))
			{
				view.pieChartItem.setEnabled(true);
				view.pieChartItem.setToolTipText("");
				view.histogramItem.setEnabled(true);
				view.histogramItem.setToolTipText("");
			}
			else
			{
				view.pieChartItem.setEnabled(false);
				view.pieChartItem.setToolTipText("No people selected");
				view.histogramItem.setEnabled(false);
				view.histogramItem.setToolTipText("No people selected");
			}
			
			if (!view.dataList.isSelectionEmpty())
			{
				view.saveItem.setEnabled(true);
				view.saveItem.setToolTipText("");
				view.exportItem.setEnabled(true);
				view.exportItem.setToolTipText("");
				view.editItem.setEnabled(true);
				view.editItem.setToolTipText("");
				view.deleteItem.setEnabled(true);
				view.deleteItem.setToolTipText("");
			}
			else
			{
				view.saveItem.setEnabled(false);
				view.saveItem.setToolTipText("No data selected");
				view.exportItem.setEnabled(false);
				view.exportItem.setToolTipText("No data selected");
				view.editItem.setEnabled(false);
				view.editItem.setToolTipText("No data selected");
				view.deleteItem.setEnabled(false);
				view.deleteItem.setToolTipText("No data selected");
			}
			
		}
		
	}
	
	
	/**
	 * 
	 * @return view, a mediaview
	 */
	public MediaView getView()
	{
		return view;
	}
	
	/**
	 * An ActionListener
	 */
	private class SaveFileListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showSaveDialog(view);
			
			if (returnVal == chooser.APPROVE_OPTION)
			{
				File file = chooser.getSelectedFile();
				
				try {
					model.writeModel(file, model);
				} catch (IOException e1) {
					System.err.println("file not found-binary");
					System.exit(0);
				}
			}
		}
	}
	
	/**
	 * An ActionListener
	 */
	private class LoadFileListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(view);
			
			if (returnVal == chooser.APPROVE_OPTION)
			{
				File file = chooser.getSelectedFile();
				//TODO: read binary file here
				
				try {
					
					
					FileInputStream fis = new FileInputStream(file);
					ObjectInputStream ois= new ObjectInputStream(fis);
					//Do I even want to make a new model?
					ois.close();
					
					
					
				} catch (FileNotFoundException e1) {
					System.err.println("file not found");
					System.exit(0);
				} catch (IOException e1) {
					System.err.println("IOexception");
					System.exit(0);
				}
				
			}
			
		}
	}
	
	/**
	 * An ActionListener(where we import the text file)
	 */
	private class ImportFileListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			//TODO: add a dialogue box or something to ask what type of object file is being imported
			//(e.g. movies, series/episodes, actors, etc.)
			
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(view);
			
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = chooser.getSelectedFile();
				//TODO: need read .txt file method in model or somewhere
				
				if(view.movieButton.isSelected()){
					try {
						
						model.readTextMovie(file);
						
						
					} catch (IOException e1) {
						System.err.println("movie file not found-view import");
						System.exit(0);
						
					}
				}
				/*
				 *Need to see how to add multiple files if multiple media is selected. 
				 * May not have time to figure out
				 */
				if (view.seriesButton.isSelected() || view.episodesButton.isSelected()){
					
					try {
						model.readTextSeries(file);
					} catch (IOException e1) {
						System.err.println("series file not found-view import");
						System.exit(0);
					}
				}
				
				if (view.actorButton.isSelected()){
					try {
						model.readActorFile(file);
					} catch (IOException e1) {
						System.err.println("actor file not found-view import");
						System.exit(0);
					}
				}
				
				if (view.producerButton.isSelected()){
					try {
						model.readProducerFile(file);
					} catch (IOException e1) {
						System.err.println("producer file not found-view import");
						System.exit(0);
					}
				}
				
				if(view.directorButton.isSelected()){
					try {
						model.readDirectorFile(file);
					} catch (IOException e1) {
						System.err.println("file not found-director file");
						System.exit(0);
					}
					
				}
			}
		}
	}
	
	/**
	 * An ActionListener (where we export the text file)
	 */
	private class ExportFileListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)return;
			
			//TODO: add a dialogue box or something to ask what type of object file is being imported
			//(e.g. movies, series/episodes, actors, etc.)
			Component frame;
			
			String[] options= {"Movie", "Series", "Episode", "Actor", "Producer", "Director","Cancel"};
			int response= JOptionPane.showOptionDialog(null,  "Which type of file is to be exported?",
					"Export", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			
			
			
			if(response==0){
				
				JFileChooser chooser = new JFileChooser();
				int returnVal=chooser.showOpenDialog(view);
				if(returnVal==JFileChooser.APPROVE_OPTION){
					File file=chooser.getSelectedFile();
					
					try {
						FileWriter outfile= new FileWriter(file);
						BufferedWriter bw= new BufferedWriter(outfile);
						//Actually writing the file
						MediaCollection movies= model.getMovies();
						for(int i=0; i< movies.mediaLength(); i++){
							bw.write(movies.listNumber(i).toString());
							bw.newLine();
							
						}
						bw.close();
					} catch (IOException e1) {
						System.err.println("File not found-export movie");
						System.exit(0);
					}
					
					
				}
			}
			if(response==1){
				JFileChooser chooser = new JFileChooser();
				int returnVal=chooser.showOpenDialog(view);
				if(returnVal==JFileChooser.APPROVE_OPTION){
					File file=chooser.getSelectedFile();
					
					try {
						FileWriter outfile= new FileWriter(file);
						BufferedWriter bw= new BufferedWriter(outfile);
						//Actually writing the file
						MediaCollection series= model.getSeries();
						for(int i=0; i< series.mediaLength(); i++){
							bw.write(series.listNumber(i).toString());
							bw.newLine();
							
						}
						bw.close();
					} catch (IOException e1) {
						System.err.println("File not found-export series");
						System.exit(0);
					}
					
					
				}
				
			}
			if(response==2){
				JFileChooser chooser = new JFileChooser();
				int returnVal=chooser.showOpenDialog(view);
				if(returnVal==JFileChooser.APPROVE_OPTION){
					File file=chooser.getSelectedFile();
					
					try {
						FileWriter outfile= new FileWriter(file);
						BufferedWriter bw= new BufferedWriter(outfile);
						//Actually writing the file
						MediaCollection episodes= model.getEpisodes();
						for(int i=0; i< episodes.mediaLength(); i++){
							bw.write(episodes.listNumber(i).toString());
							bw.newLine();
							
						}
						bw.close();
					} catch (IOException e1) {
						System.err.println("File not found-export episodes");
						System.exit(0);
					}
					
					
				}
				
			}
			if(response==3){
				JFileChooser chooser = new JFileChooser();
				int returnVal=chooser.showOpenDialog(view);
				if(returnVal==JFileChooser.APPROVE_OPTION){
					File file=chooser.getSelectedFile();
					
					try {
						FileWriter outfile= new FileWriter(file);
						BufferedWriter bw= new BufferedWriter(outfile);
						//Actually writing the file
						LinkedHashMap<String, ArrayList<Media>> actors= model.getActors();
						for(int i=0; i< actors.size(); i++){
							bw.write(actors.get(i).toString());
							bw.newLine();
							
						}
						bw.close();
					} catch (IOException e1) {
						System.err.println("File not found-export actor");
						System.exit(0);
					}
					catch(NullPointerException c){
						System.err.println("Issues writing file-actor list");
						System.exit(0);
					}
					
					
				}
				
			}
			if(response==4){
				JFileChooser chooser = new JFileChooser();
				int returnVal=chooser.showOpenDialog(view);
				if(returnVal==JFileChooser.APPROVE_OPTION){
					File file=chooser.getSelectedFile();
					
					try {
						FileWriter outfile= new FileWriter(file);
						BufferedWriter bw= new BufferedWriter(outfile);
						//Actually writing the file
						LinkedHashMap<String, ArrayList<Media>> producers= model.getProducers();
						for(int i=0; i< producers.size(); i++){
							bw.write(producers.get(i).toString());
							bw.newLine();
							
						}
						bw.close();
					} catch (IOException e1) {
						System.err.println("File not found-export producer");
						System.exit(0);
					}
					catch(NullPointerException c){
						System.err.println("Issues with making file-producerList");
						System.exit(0);
					}
					
					
				}
			}
			if(response==5){
				JFileChooser chooser = new JFileChooser();
				int returnVal=chooser.showOpenDialog(view);
				if(returnVal==JFileChooser.APPROVE_OPTION){
					File file=chooser.getSelectedFile();
					
					try {
						FileWriter outfile= new FileWriter(file);
						BufferedWriter bw= new BufferedWriter(outfile);
						//Actually writing the file
						LinkedHashMap<String, ArrayList<Media>> directors= model.getDirectors();
						for(int i=0; i< directors.size(); i++){
							bw.write(directors.get(i).toString());
							bw.newLine();
							
						}
						bw.close();
					} catch (IOException e1) {
						System.err.println("File not found-export director");
						System.exit(0);
					}
					catch(NullPointerException c){
						System.err.println("Issues with writing the director");
						System.exit(0);
					}
					
					
				}
			}
			if(response==6 || response==-1){
				return;
			}
			
		}
	}
	
	/**
	 * An ActionListener items added
	 */
	private class AddItemListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			if (view.movieButton.isSelected())
			{
				//new MovieView();
				MovieView movieView= new MovieView();
				MovieController movieController= new MovieController();
				//MediaModel model= new MediaModel();
				movieController.setModel(model);
				movieController.setView(movieView);
			}
			else if (view.seriesButton.isSelected())
			{
				//new SeriesView();
				SeriesView seriesView= new SeriesView();
				SeriesController seriesController= new SeriesController();
				//MediaModel model= new MediaModel();
				seriesController.setModel(model);
				seriesController.setView(seriesView);
				
			}
			else if (view.episodesButton.isSelected())
			{
				//new EpisodeAddView();
				EpisodeAddView episodeView= new EpisodeAddView(model);
				EpisodeController episodeController= new EpisodeController();
				//MediaModel model= new MediaModel();
				episodeController.setModel(model);
				episodeController.setView(episodeView);
			}
			else if (view.actorButton.isSelected())
			{
				//new ActorView();
				ActorView actorView= new ActorView();
				ActorController actorController= new ActorController();
				//MediaModel model= new MediaModel();
				actorController.setModel(model);
				actorController.setView(actorView);
			}
			else if (view.directorButton.isSelected())
			{
				//new DirectorView();
				DirectorView directorView= new DirectorView();
				DirectorController directorController= new DirectorController();
				//MediaModel model= new MediaModel();
				directorController.setModel(model);
				directorController.setView(directorView);
			}
			else if (view.producerButton.isSelected())
			{
				//new ProducerView();
				ProducerView producerView= new ProducerView();
				ProducerController producerController= new ProducerController();
				//MediaModel model= new MediaModel();
				producerController.setModel(model);
				producerController.setView(producerView);
			}
			
		}
	}
	
	/**
	 * An ActionListener items edited
	 */
	private class EditItemListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			ArrayList<Object> list = new ArrayList<Object>(view.dataList.getSelectedValuesList());
			
			for (Object o: list)
			{
				if (o instanceof Movie)
				{
					MovieView m = new MovieView();
					m.titleBox.setText(((Movie) o).getName());
					//TEMPORARY COMMENT OUT FIX LATER 
					
					m.yearBox.setText(((Integer)((Movie) o).getYear()).toString());
					m.formatBox.setText(((Movie) o).getType());
				}
				
				if (o instanceof Series)
				{
					SeriesView s = new SeriesView();
					s.titleBox.setText(((Series) o).getName());
					//TODO: Want to fill in startYear box
					s.startYear.setText(((Integer)((Series) o).getYear()).toString());
					//TODO: Want to fill in endYear box
					s.endYear.setText(((Integer)((Series) o).getEndYear()).toString());
				}
				
				if (o instanceof Episodes)
				{
					EpisodeAddView ea = new EpisodeAddView(model);
				}
			}
		}
	}
	
	/**
	 * An ActionListener items deleted
	 */
	private class DeleteItemListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			ArrayList<Object> list = new ArrayList<Object>(view.dataList.getSelectedValuesList());
			
			//TODO: check to make sure all data concerning the selected data is deleted
			for (Object o: list)
			{
				if (o instanceof Movie)
				{
					model.removeMovie((Movie) o);
				}
				if (o instanceof Series)
				{
					model.removeSeries((Series) o);
				}
				if (o instanceof Episodes)
				{
					model.removeEpisode((Episodes) o);
				}
				if (o instanceof LinkedHashMap)
				{
					if (view.makerButton.isSelected())
					{
						
					}
					if (view.actorButton.isSelected())
					{
						
					}
					if (view.directorButton.isSelected())
					{
						
					}
					if (view.producerButton.isSelected())
					{
						
					}
				}
			}
		}
	}
	
	/**
	 * An ActionListener items cleared
	 */
	private class ClearItemListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			//TODO: add confirm dialogue for whether to actually delete these
			
			if (view.mediaButton.isSelected())
			{
				model.clearMovie();
				model.clearSeries();
				model.clearEpisodes();
			}
			else if (view.movieButton.isSelected())
			{
				model.clearMovie();
			}
			else if (view.seriesButton.isSelected())
			{
				model.clearSeries();
			}
			else if (view.episodesButton.isSelected())
			{
				model.clearEpisodes();
			}
			else if (view.makerButton.isSelected())
			{
				model.clearActors();
				model.clearDirectors();
				model.clearProducers();
			}
			else if (view.actorButton.isSelected())
			{
				model.clearActors();
			}
			else if (view.directorButton.isSelected())
			{
				model.clearDirectors();
			}
			else if (view.producerButton.isSelected())
			{
				model.clearProducers();
			}
		}
	}
	
	/**
	 * An ActionListener clear all
	 */
	private class ClearAllItemListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			//TODO: add confirm dialogue for whether to actually delete EVERYTHING
			
			model.clearAll();
		}
	}
	
	/**
	 * An ActionListener
	 */
	private class PieChartItemListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			ArrayList<Object> list = new ArrayList<Object>(view.dataList.getSelectedValuesList());
			
			for (Object o: list)
			{
				PieChart p = new PieChart((String) o, model.getActors().get((String) o),
														model.getDirectors().get((String) o), 
															model.getProducers().get((String) o));
				PieChart.PieChartBuilder(p);
			}
		}
	}
	
	/**
	 * An ActionListener
	 */
	private class HistogramItemListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			ArrayList<Object> list = new ArrayList<Object>(view.dataList.getSelectedValuesList());
			
			//TODO: make sure charts update with up date of data
			//TODO: PieChart will need to account for the possibility that only a single type of person may be selected
			
				for (Object o: list)
				{
					Histogram h = new Histogram((String) o, model.getActors().get((String) o),
															model.getDirectors().get((String) o), 
																model.getProducers().get((String) o));
					
					h.buildHistogram();
				}
		}
	}
	
	/**
	 * An ActionListener
	 */
	private class MediaButtonListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			view.addItem.setToolTipText("Select a more specific data type");
			
			if (!model.getMovies().getMedia().isEmpty()
					|| !model.getSeries().getMedia().isEmpty()
						|| !model.getEpisodes().getMedia().isEmpty())
			{
				view.clearItem.setEnabled(true);
				view.clearItem.setToolTipText("");
				
				view.clearAllItem.setEnabled(true);
				view.clearAllItem.setToolTipText("");
			}
			else
			{
				view.clearItem.setEnabled(false);
				view.clearItem.setToolTipText("Cannot clear data; no data available");
				
				view.clearAllItem.setEnabled(false);
				view.clearAllItem.setToolTipText("Cannot clear data; no data available");
			}
			
			if (!view.dataList.getSelectedValuesList().isEmpty())
			{
				view.editItem.setEnabled(true);
				view.editItem.setToolTipText("");
				
				view.deleteItem.setEnabled(true);
				view.deleteItem.setToolTipText("");
			}
			else
			{
				view.editItem.setEnabled(false);
				view.editItem.setToolTipText("Cannot edit data; no data selected");
				
				view.deleteItem.setEnabled(false);
				view.deleteItem.setToolTipText("Cannot delete data; no data selected");
			}
			
			view.movieButton.setSelected(false);
			view.seriesButton.setSelected(false);
			view.episodesButton.setSelected(false);
			view.makerButton.setSelected(false);
			view.actorButton.setSelected(false);
			view.directorButton.setSelected(false);
			view.producerButton.setSelected(false);
			
			//TODO: check this
			ArrayList<Object> list = new ArrayList<Object>();
			list.addAll(model.getMovies().getMedia());
			list.addAll(model.getSeries().getMedia());
			list.addAll(model.getEpisodes().getMedia());
			view.dataList.setListData(list.toArray());
			
		}
	}
	
	/**
	 * An ActionListener
	 */
	private class MovieButtonListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			view.addItem.setEnabled(true);
			view.addItem.setToolTipText("");
			
			if (!model.getMovies().getMedia().isEmpty())
			{
				view.clearItem.setEnabled(true);
				view.clearItem.setToolTipText("");
				
				view.clearAllItem.setEnabled(true);
				view.clearAllItem.setToolTipText("");
			}
			else
			{
				view.clearItem.setEnabled(false);
				view.clearItem.setToolTipText("Cannot clear data; no data available");
				
				view.clearAllItem.setEnabled(false);
				view.clearAllItem.setToolTipText("Cannot clear data; no data available");
			}
			
			if (!view.dataList.getSelectedValuesList().isEmpty())
			{
				view.editItem.setEnabled(true);
				view.editItem.setToolTipText("");
				
				view.deleteItem.setEnabled(true);
				view.deleteItem.setToolTipText("");
			}
			else
			{
				view.editItem.setEnabled(false);
				view.editItem.setToolTipText("Cannot edit data; no data selected");
				
				view.deleteItem.setEnabled(false);
				view.deleteItem.setToolTipText("Cannot delete data; no data selected");
			}
			
			view.mediaButton.setSelected(false);
			view.seriesButton.setSelected(false);
			view.episodesButton.setSelected(false);
			view.makerButton.setSelected(false);
			view.actorButton.setSelected(false);
			view.directorButton.setSelected(false);
			view.producerButton.setSelected(false);
			
			//TODO: check this
			view.dataList.setListData(model.getMovies().getMedia().toArray());
			
		}
	}
	
	/**
	 * An ActionListener
	 */
	private class SeriesButtonListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			view.addItem.setEnabled(true);
			view.addItem.setToolTipText("");
			
			if (!model.getSeries().getMedia().isEmpty())
			{
				view.clearItem.setEnabled(true);
				view.clearItem.setToolTipText("");
				
				view.clearAllItem.setEnabled(true);
				view.clearAllItem.setToolTipText("");
			}
			else
			{
				view.clearItem.setEnabled(false);
				view.clearItem.setToolTipText("Cannot clear data; no data available");
				
				view.clearAllItem.setEnabled(false);
				view.clearAllItem.setToolTipText("Cannot clear data; no data available");
			}
			
			if (!view.dataList.getSelectedValuesList().isEmpty())
			{
				view.editItem.setEnabled(true);
				view.editItem.setToolTipText("");
				
				view.deleteItem.setEnabled(true);
				view.deleteItem.setToolTipText("");
			}
			else
			{
				view.editItem.setEnabled(false);
				view.editItem.setToolTipText("Cannot edit data; no data selected");
				
				view.deleteItem.setEnabled(false);
				view.deleteItem.setToolTipText("Cannot delete data; no data selected");
			}
			
			view.mediaButton.setSelected(false);
			view.movieButton.setSelected(false);
			view.episodesButton.setSelected(false);
			view.makerButton.setSelected(false);
			view.actorButton.setSelected(false);
			view.directorButton.setSelected(false);
			view.producerButton.setSelected(false);
			
			//TODO: check this
			
			view.dataList.setListData(model.getSeries().getMedia().toArray());
			
		}
	}
	
	/**
	 * An ActionListener
	 */
	private class EpisodeButtonListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			view.addItem.setEnabled(true);
			view.addItem.setToolTipText("");
			
			if (!model.getEpisodes().getMedia().isEmpty())
			{
				view.clearItem.setEnabled(true);
				view.clearItem.setToolTipText("");
				
				view.clearAllItem.setEnabled(true);
				view.clearAllItem.setToolTipText("");
			}
			else
			{
				view.clearItem.setEnabled(false);
				view.clearItem.setToolTipText("Cannot clear data; no data available");
				
				view.clearAllItem.setEnabled(false);
				view.clearAllItem.setToolTipText("Cannot clear data; no data available");
			}
			
			if (!view.dataList.getSelectedValuesList().isEmpty())
			{
				view.editItem.setEnabled(true);
				view.editItem.setToolTipText("");
				
				view.deleteItem.setEnabled(true);
				view.deleteItem.setToolTipText("");
			}
			else
			{
				view.editItem.setEnabled(false);
				view.editItem.setToolTipText("Cannot edit data; no data selected");
				
				view.deleteItem.setEnabled(false);
				view.deleteItem.setToolTipText("Cannot delete data; no data selected");
			}
			
			view.mediaButton.setSelected(false);
			view.movieButton.setSelected(false);
			view.seriesButton.setSelected(false);
			view.makerButton.setSelected(false);
			view.actorButton.setSelected(false);
			view.directorButton.setSelected(false);
			view.producerButton.setSelected(false);
			
			//TODO: check this
			view.dataList.setListData(model.getEpisodes().getMedia().toArray());
			
		}
	}
	
	/**
	 * An ActionListener
	 */
	private class MakerButtonListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			view.addItem.setToolTipText("Select a more specific data type to add");
			
			if (!model.getActors().isEmpty()
					|| !model.getDirectors().isEmpty()
						|| !model.getProducers().isEmpty())
			{
				view.clearItem.setEnabled(true);
				view.clearItem.setToolTipText("");
				
				view.clearAllItem.setEnabled(true);
				view.clearAllItem.setToolTipText("");
			}
			else
			{
				view.clearItem.setEnabled(false);
				view.clearItem.setToolTipText("Cannot clear data; no data available");
				
				view.clearAllItem.setEnabled(false);
				view.clearAllItem.setToolTipText("Cannot clear data; no data available");
			}
			
			if (!view.dataList.getSelectedValuesList().isEmpty())
			{
				view.editItem.setEnabled(true);
				view.editItem.setToolTipText("");
				
				view.deleteItem.setEnabled(true);
				view.deleteItem.setToolTipText("");
			}
			else
			{
				view.editItem.setEnabled(false);
				view.editItem.setToolTipText("Cannot edit data; no data selected");
				
				view.deleteItem.setEnabled(false);
				view.deleteItem.setToolTipText("Cannot delete data; no data selected");
			}
			
			view.mediaButton.setSelected(false);
			view.movieButton.setSelected(false);
			view.seriesButton.setSelected(false);
			view.episodesButton.setSelected(false);
			view.actorButton.setSelected(false);
			view.directorButton.setSelected(false);
			view.producerButton.setSelected(false);
			
			//TODO: check this
			ArrayList<Object> list = new ArrayList<Object>();
			list.addAll(Arrays.asList(model.getActors().keySet().toArray()));
			list.addAll(Arrays.asList(model.getDirectors().keySet().toArray()));
			list.addAll(Arrays.asList(model.getProducers().keySet().toArray()));
			view.dataList.setListData(list.toArray());
			
			
		}
	}
	
	/**
	 * An ActionListener
	 */
	private class ActorButtonListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			view.addItem.setEnabled(true);
			view.addItem.setToolTipText("");
			
			if (!model.getActors().isEmpty())
			{
				view.clearItem.setEnabled(true);
				view.clearItem.setToolTipText("");
				
				view.clearAllItem.setEnabled(true);
				view.clearAllItem.setToolTipText("");
			}
			else
			{
				view.clearItem.setEnabled(false);
				view.clearItem.setToolTipText("Cannot clear data; no data available");
				
				view.clearAllItem.setEnabled(false);
				view.clearAllItem.setToolTipText("Cannot clear data; no data available");
			}
			
			if (!view.dataList.getSelectedValuesList().isEmpty())
			{
				view.editItem.setEnabled(true);
				view.editItem.setToolTipText("");
				
				view.deleteItem.setEnabled(true);
				view.deleteItem.setToolTipText("");
			}
			else
			{
				view.editItem.setEnabled(false);
				view.editItem.setToolTipText("Cannot edit data; no data selected");
				
				view.deleteItem.setEnabled(false);
				view.deleteItem.setToolTipText("Cannot delete data; no data selected");
			}
			
			view.mediaButton.setSelected(false);
			view.movieButton.setSelected(false);
			view.seriesButton.setSelected(false);
			view.episodesButton.setSelected(false);
			view.makerButton.setSelected(false);
			view.directorButton.setSelected(false);
			view.producerButton.setSelected(false);
			
			//TODO: check this
			view.dataList.setListData(model.getActors().keySet().toArray());
		}
	}
	
	/**
	 * An ActionListener
	 */
	private class DirectorButtonListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			view.addItem.setEnabled(true);
			view.addItem.setToolTipText("");
			
			if (!model.getDirectors().isEmpty())
			{
				view.clearItem.setEnabled(true);
				view.clearItem.setToolTipText("");
				
				view.clearAllItem.setEnabled(true);
				view.clearAllItem.setToolTipText("");
			}
			else
			{
				view.clearItem.setEnabled(false);
				view.clearItem.setToolTipText("Cannot clear data; no data available");
				
				view.clearAllItem.setEnabled(false);
				view.clearAllItem.setToolTipText("Cannot clear data; no data available");
			}
			
			if (!view.dataList.getSelectedValuesList().isEmpty())
			{
				view.editItem.setEnabled(true);
				view.editItem.setToolTipText("");
				
				view.deleteItem.setEnabled(true);
				view.deleteItem.setToolTipText("");
			}
			else
			{
				view.editItem.setEnabled(false);
				view.editItem.setToolTipText("Cannot edit data; no data selected");
				
				view.deleteItem.setEnabled(false);
				view.deleteItem.setToolTipText("Cannot delete data; no data selected");
			}
			
			view.mediaButton.setSelected(false);
			view.movieButton.setSelected(false);
			view.seriesButton.setSelected(false);
			view.episodesButton.setSelected(false);
			view.makerButton.setSelected(false);
			view.actorButton.setSelected(false);
			view.producerButton.setSelected(false);
			
			//TODO: check this
			view.dataList.setListData(model.getDirectors().keySet().toArray());
		}
	}
	
	/**
	 * An ActionListener
	 */
	private class ProducerButtonListener implements ActionListener
	{
		@Override
		/**
		 * Performs an action based on ActionEvent
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (model == null)
				return;
			
			view.addItem.setEnabled(true);
			view.addItem.setToolTipText("");
			
			if (!model.getProducers().isEmpty())
			{
				view.clearItem.setEnabled(true);
				view.clearItem.setToolTipText("");
				
				view.clearAllItem.setEnabled(true);
				view.clearAllItem.setToolTipText("");
			}
			else
			{
				view.clearItem.setEnabled(false);
				view.clearItem.setToolTipText("Cannot clear data; no data available");
				
				view.clearAllItem.setEnabled(false);
				view.clearAllItem.setToolTipText("Cannot clear data; no data available");
			}
			
			if (!view.dataList.getSelectedValuesList().isEmpty())
			{
				view.editItem.setEnabled(true);
				view.editItem.setToolTipText("");
				
				view.deleteItem.setEnabled(true);
				view.deleteItem.setToolTipText("");
			}
			else
			{
				view.editItem.setEnabled(false);
				view.editItem.setToolTipText("Cannot edit data; no data selected");
				
				view.deleteItem.setEnabled(false);
				view.deleteItem.setToolTipText("Cannot delete data; no data selected");
			}
			
			view.mediaButton.setSelected(false);
			view.movieButton.setSelected(false);
			view.seriesButton.setSelected(false);
			view.episodesButton.setSelected(false);
			view.makerButton.setSelected(false);
			view.actorButton.setSelected(false);
			view.directorButton.setSelected(false);
			
			//TODO: check this
			view.dataList.setListData(model.getProducers().keySet().toArray());
			
		}
	}
}

