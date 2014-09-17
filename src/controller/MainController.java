package controller;

import java.io.File;

import javax.swing.JOptionPane;

import model.MainModel;
import view.MainWindow;

public class MainController {
	String directory;
	File f; 
	
	public MainController(String directory){
		this.directory = directory;
		if(createDirectoryFileFromString()){
			//Starting model thread.
			MainModel m = new MainModel(f);
			Thread t = new Thread(m);
			t.start();
		}
	}

	private boolean createDirectoryFileFromString() {
		if(directory.isEmpty()){
			JOptionPane.showMessageDialog(MainWindow.window.frmFileautoorganizerV, "Directory is empty","Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		f = new File(directory);
		if(!f.isDirectory()){
			JOptionPane.showMessageDialog(MainWindow.window.frmFileautoorganizerV, "The directory you selected is wrong.","Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
		
	}
	
	
}
