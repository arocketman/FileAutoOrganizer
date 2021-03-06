package model;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import view.MainWindow;
public class MainModel implements Runnable{

	File directory;
	int nFiles;
	//List the names of the file that couldn't be moved.
	ArrayList<String> filesFailedMove;
	
	public MainModel(File f) {
		this.directory = f;
		filesFailedMove = new ArrayList<String>();
	}

	@Override
	public void run() {
		//Number of files moved.
		File files [] = directory.listFiles(new myFilter());
		
		//Setting up the progress bar as the number of files.
		MainWindow.window.progressBar.setMaximum(files.length);
		for(File currentFile : files){
			String extension = getExtension(currentFile);
			switch (extension) {
			case "zip": case "rar" : case "7z" : case "gz" : 
				move(currentFile,"archives");
				break;
			case "jpg": case "gif" : case "jpeg" : case "png" : case "tga" : case "psd" : case "bmp" : case "tif" : case "tiff" : case "yuv" :
				move(currentFile,"images");
				break;
			case "txt": case "doc" : case "rtf" : case "pdf" : case "odt" : case "docx" : case "tex" : case "nfo" : 
				move(currentFile,"documents");
				break;
			case "mp3": case "wma" : case "flac" : case "aac" : case "amr" : case "m4a" : case "wav":
				move(currentFile,"audio");
				break;
			case "avi": case "wmv" : case "mpeg" : case "mp4" : case "3gp" : case "divx" : case "mov" : case "rm":
				move(currentFile,"video");
				break;
			case "exe": case "bat" : case "jar" : case "com" : case "vb" : case "apk" : 
				move(currentFile,"executables");
				break;
			default:
				move(currentFile,"other");
				break;
			}

		}
		
		//Finished
		JOptionPane.showMessageDialog(MainWindow.window.frmFileautoorganizerV, "We are done! Moved files: " + nFiles,"Done",JOptionPane.INFORMATION_MESSAGE);
		if(!filesFailedMove.isEmpty())
			JOptionPane.showMessageDialog(MainWindow.window.frmFileautoorganizerV, "The following files couldn't be moved:\n" + getUnableToMoveFiles() ,"Done",JOptionPane.INFORMATION_MESSAGE);
		
		
	}
	
	private void move(File currentFile, String folderName) {
		File newDir = new File(currentFile.getParent() + "\\" + folderName);
		if(!newDir.exists()) 
			newDir.mkdir();
		boolean success = currentFile.renameTo(new File(currentFile.getParent() + "\\" + folderName + "\\" + currentFile.getName()));
		if(success) nFiles++;
		else filesFailedMove.add(currentFile.getName());
		MainWindow.window.progressBar.setValue(nFiles);

		
	}
	
	/*
	*Builds up the files that cannot be moved . 
	*/
	private String getUnableToMoveFiles(){
		String buildMeUp = "";
		for(String filename : filesFailedMove){
			buildMeUp += "-" + filename + "\n";
		}
		return buildMeUp;
	}

	private String getExtension(File currentFile){
		int dotLocation = currentFile.getName().lastIndexOf(".");
		String ext = "";
		if(dotLocation > 0)
			ext = currentFile.getName().substring(dotLocation+1);
		return ext;
	}
	
	private class myFilter implements FilenameFilter{

		@Override
		public boolean accept(File dir, String name) {
			File theFile = new File(dir.getAbsolutePath() + "\\" + name);
			return !theFile.isDirectory();
		}
		
	}

}
