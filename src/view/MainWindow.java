package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;

import controller.MainController;

import javax.swing.JProgressBar;

public class MainWindow {

	public JFrame frmFileautoorganizerV;
	private JTextField dirTextBox;
	private JFileChooser fileChooser;
	public static MainWindow window;
	public JProgressBar progressBar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainWindow();
					window.frmFileautoorganizerV.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFileautoorganizerV = new JFrame();
		frmFileautoorganizerV.setTitle("File-Auto-Organizer v1.0");
		frmFileautoorganizerV.setBounds(100, 100, 338, 113);
		frmFileautoorganizerV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFileautoorganizerV.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		frmFileautoorganizerV.getContentPane().add(panel);
		
		dirTextBox = new JTextField();
		panel.add(dirTextBox);
		dirTextBox.setColumns(20);
		
		JButton btnBrowse = new JButton("Browse");
		panel.add(btnBrowse);
		btnBrowse.addActionListener(new browseListener());
		
		JButton btnClean = new JButton("Clean");
		panel.add(btnClean);
		
		JPanel panel_1 = new JPanel();
		frmFileautoorganizerV.getContentPane().add(panel_1);
		
		progressBar = new JProgressBar();
		panel_1.add(progressBar);
		btnClean.addActionListener(new cleanListener());
		
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		frmFileautoorganizerV.pack();
	}
	
	private class browseListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			fileChooser.showOpenDialog(frmFileautoorganizerV);
			try{
			String directory = fileChooser.getSelectedFile().toString();
			if(!directory.isEmpty())
				dirTextBox.setText(directory);
			}catch(NullPointerException error){
				JOptionPane.showMessageDialog(MainWindow.window.frmFileautoorganizerV, "Choose a directory!","Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
	
	private class cleanListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new MainController(dirTextBox.getText());
			
		}
		
	}
	
	

}
