package main.java.nopaynenogame.rebirthtracker;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class ImageTableModel extends DefaultTableModel {

	public ImageTableModel() {
		// TODO Auto-generated constructor stub
	}

	public ImageTableModel(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		// TODO Auto-generated constructor stub
	}

	public ImageTableModel(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
		// TODO Auto-generated constructor stub
	}

	public ImageTableModel(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		// TODO Auto-generated constructor stub
	}

	public ImageTableModel(Vector data, Vector columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub
	}

	public ImageTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Class getColumnClass(int column) {
		return ImageIcon.class;
	}

}
