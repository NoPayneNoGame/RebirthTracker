package main.java.nopaynenogame.rebirthtracker.windowbuilder;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import main.java.nopaynenogame.rebirthtracker.ImageTableModel;
import main.java.nopaynenogame.rebirthtracker.ResourceFile;
import main.java.nopaynenogame.rebirthtracker.SaveFile;

import javax.swing.JTextField;
import javax.swing.JButton;

public class RebirthTrackerGui {

	private JFrame frame;
	private JTable table;
	private JTextField fileLocTextField;
	
	private static String saveLocation;
	
	private static SaveFile save = null;
	
	private static String resourceLoc = "src/main/resources/";
	private static String achieveLoc = resourceLoc + "Achievements.txt";
	private static String itemLoc = resourceLoc + "Items.txt";
	private static String challengeLoc = resourceLoc + "Challenges.txt";
	
	private static ResourceFile achieveFile = new ResourceFile(achieveLoc);
	private static ResourceFile itemFile = new ResourceFile(itemLoc);
	private static ResourceFile challengeFile = new ResourceFile(challengeLoc);
	
	private static HashMap<String, String> achieveMap = achieveFile.toMap();
	private static HashMap<String, String> itemMap = itemFile.toMap();
	private static HashMap<String, String> challengeMap = challengeFile.toMap();
	
	private static Boolean running = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RebirthTrackerGui window = new RebirthTrackerGui();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RebirthTrackerGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(429, 11, 355, 549);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		table.setShowGrid(false);
		table.setModel(new ImageTableModel(
			populateItemTable(),
			new String[] {
				"", "", "", "", "", ""
			}
		));
		for (int i = 0; i < 56; i++) {
			table.setRowHeight(i, 32);
		}
		scrollPane.setViewportView(table);
		
		fileLocTextField = new JTextField();
		fileLocTextField.setBounds(10, 20, 181, 20);
		frame.getContentPane().add(fileLocTextField);
		fileLocTextField.setColumns(10);
		fileLocTextField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub	
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				saveLocation = fileLocTextField.getText();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		JButton btnBrowse = new JButton("Browse..");
		btnBrowse.setBounds(201, 19, 99, 23);
		btnBrowse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("dat files", "dat");
				chooser.setFileFilter(filter);
				int returnValue = chooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					fileLocTextField.setText(selectedFile.getPath());
				}
				
			}
		});
		frame.getContentPane().add(btnBrowse);
	}
	
	private Object[][] populateItemTable() {	
		int height = 50;
		int width = 6;
		Object[][] items = new Object[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++){
				ImageIcon img = new ImageIcon("src/main/resources/ItemSprites/"+ ((i+1)+j+(6*i)) + ".png");				
				items[i][j]  = img;
			}	
		}
		return items;
	}
}
