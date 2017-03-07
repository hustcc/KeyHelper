/** 
 * Copyright 2013 HUST. 
 * Visit https://github.com/hustcc
 * All right reserved. 
 */ 
package cc.hust.keyhelper.frame;

import java.util.Map;

import javax.swing.table.AbstractTableModel;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2013-1-29 上午10:09:15
 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
 */
public class KeyTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	/**
	 * 对应某一方案的改键数据
	 */
	private String[][] data = null;
	private String[] columnName = {"原始按键", "改键"};
	/**
	 * 重新加载数据
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-1-29 上午10:29:06
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 * @param keyMap
	 */
	public void setModelData(Map<String, String> keyMap) {
		data = keyMap2Array(keyMap);
		fireTableDataChanged();
	}
	
	/**
	 * 使用keymap做构造器
	 * @param keyMap
	 */
	public KeyTableModel(Map<String, String> keyMap) {
		data = keyMap2Array(keyMap);
		fireTableDataChanged();
	}
	
	public int getRowCount() {
		return data.length;
	}

	public int getColumnCount() {
		return columnName.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	public String getColumnName(int column) {
		return columnName[column];
	}
	
	private String[][] keyMap2Array(Map<String, String> keyMap) {
		String[][] data = new String[keyMap.size()][2];
		Object[] objects = keyMap.keySet().toArray();
		for (int i = 0; i < objects.length; i++) {
			data[i][0] = keyMap.get(objects[i].toString());
			data[i][1] = objects[i].toString();
		}
		return data;
	}
}
