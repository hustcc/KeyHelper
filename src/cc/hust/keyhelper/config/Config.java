/** 
 * Copyright 2013 HUST. 
 * Visit https://github.com/hustcc
 * All right reserved. 
 */ 
package cc.hust.keyhelper.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2013-1-29 上午10:38:45
 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
 */
public class Config {
	//=========================================
	public String lastKeySet = "";//上次选择的改键方案
	public int lastLocationX = 500;//上次窗口位置
	public int lastLocationY = 100;//上次窗口位置
	
	//=========================================
	private final static String CONFIG_FILE = "config.xml";
	private static Config instance = new Config();
	private Config() {
		loadFromXml();
	}
	public static Config instance() {
		return instance;
	}
	private void loadFromXml() {
		try {
			SAXReader reader = new SAXReader();
			File file = new File(CONFIG_FILE);
			Document document = reader.read(new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8")));// 读取XML文件
			Element xmlRoot = document.getRootElement();// 得到根节点

			lastKeySet = xmlRoot.elementText("lastKeySet");
			lastLocationX = Integer.valueOf(xmlRoot.elementText("lastLocationX"));
			lastLocationY = Integer.valueOf(xmlRoot.elementText("lastLocationY"));
		} catch (Exception e) {
			e.printStackTrace();
			lastKeySet = "";//上次选择的改键方案
			lastLocationX = 500;//上次窗口位置
			lastLocationY = 100;//上次窗口位置
		}
	}
	/**
	 * TODO 保存用户个性化配置，可以采用xml，也可以采用objectStream（推荐xml）
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-1-29 上午10:57:51
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 */
	public void saveToXml() {
		/** 建立document对象 */ 
        Document document = DocumentHelper.createDocument(); 
        /** 建立config根节点 */ 
        Element configElement = document.addElement("config");
        configElement.addElement("lastKeySet").setText(lastKeySet);
        configElement.addElement("lastLocationX").setText(lastLocationX + "");
        configElement.addElement("lastLocationY").setText(lastLocationY + "");
        try{ 
        	/* 将document中的内容写入文件中 */ 
        	OutputFormat format = new OutputFormat(); 
        	format.setEncoding("UTF-8");
        	XMLWriter writer = new XMLWriter(new FileOutputStream(new File(CONFIG_FILE)), format); 
        	writer.write(document); 
        	writer.close();             
        } catch(Exception ex) { 
        	ex.printStackTrace(); 
        } 
	}
}
