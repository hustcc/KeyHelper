package cc.hust.keyhelper.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class KeySetConfig {
	private final static String KEYSET_CONFIG_FILE = "key-config.xml";

	private static KeySetConfig instance = new KeySetConfig();
	//dest ---> src
	private Map<String, Map<String, String>> keySetMap = new HashMap<String, Map<String, String>>();

	/**
	 * 得到某一个改键方案的改键数据
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-1-29 上午10:24:01
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 * @param keySetId
	 * @return
	 */
	public Map<String, String> getKeyMap(Object keySetName) {
		return keySetMap.get(keySetName) == null ? new HashMap<String, String>() : keySetMap.get(keySetName);
	}

	/**
	 * 得到所有的改键方案
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-1-29 上午10:24:01
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 * @param keySetId
	 * @return
	 */
	public Object[] getKeySetArray() {
		return keySetMap.keySet().toArray();
	}

	/**
	 * 单例模式得到实例
	 * 
	 * @return
	 */
	public static KeySetConfig instance() {
		return instance;
	}

	/**
	 * 初始化改键数据，从xml中获得
	 */
	private KeySetConfig() {
		keySetMap = loadFromXml();
	}
	
	/**
	 * 从xml中读取改键数据
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-1-29 上午10:24:48
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Map<String, String>> loadFromXml() {
		try {
			// 从xml配置中解析改建
			HashMap<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();

			SAXReader reader = new SAXReader();
			File file = new File(KEYSET_CONFIG_FILE);
			Document document = reader.read(new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8")));// 读取XML文件
			Element xmlRoot = document.getRootElement();// 得到根节点

			List<Element> keys = xmlRoot.elements("keys");
			Map<String, String> keyMap = null;
			List<Element> keysetList = null;
			for (int i = 0; i < keys.size(); i++) {
				keyMap = new HashMap<String, String>();
				keysetList = keys.get(i).elements("key");
				for (int j = 0; j < keysetList.size(); j++) {
					keyMap.put(keysetList.get(j).elementText("dest"), keysetList
							.get(j).elementText("src"));
				}
				map.put(keys.get(i).attributeValue("name"), keyMap);
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "key-config.xml配置文件解析错误",
					"配置错误", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			return null;
		}
	}

	/**
	 * 保存到xml
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-4-27 上午10:41:56
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 */
	public void saveToXml() {
		/** 建立document对象 */
        Document document = DocumentHelper.createDocument(); 
        Element rootElement = document.addElement("keyset");
        Iterator<String> keyIterator = keySetMap.keySet().iterator();
        String keySetName = "";
        Element keysElement = null;
        Map<String, String> keysMap = null;
        Iterator<String> keysMapIterator = null;
        String keysKey = "";
        Element keyElement;
        while(keyIterator.hasNext()) {
        	keySetName = keyIterator.next();
        	keysElement = rootElement.addElement("keys").addAttribute("name", keySetName);
        	keysMap = this.keySetMap.get(keySetName);
        	keysMapIterator = keysMap.keySet().iterator();
        	while (keysMapIterator.hasNext()) {
        		keysKey =  keysMapIterator.next();
				keyElement = keysElement.addElement("key");//.addAttribute("id", keysKey);
				keyElement.addElement("dest").setText(keysKey);
				keyElement.addElement("src").setText(keysMap.get(keysKey));
			}
        }
        try{ 
        	/* 将document中的内容写入文件中 */ 
        	OutputFormat format = new OutputFormat();
        	format.setEncoding("UTF-8");
        	XMLWriter writer = new XMLWriter(new FileOutputStream(new File(KEYSET_CONFIG_FILE)),format); 
        	writer.write(document); 
        	writer.close();
        } catch(Exception ex) { 
        	ex.printStackTrace();
        }
	}
	
	/**
	 * 删除一个改键方案
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-4-22 下午4:55:01
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 * @param name
	 */
	public void delKeySet(Object name) {
		keySetMap.remove(name);
	}
	/**
	 * 是否存在方案
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-4-28 下午4:50:49
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 * @param keySetName
	 * @return
	 */
	public boolean existKeySet(String keySetName) {
		return keySetMap.get(keySetName.trim()) != null;
	}
	
	/**
	 * 添加一个改键方案
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-4-22 下午4:55:01
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 * @param name
	 */
	public void addKeySet(String name) {
		keySetMap.put(name, new HashMap<String, String>());
	}
	/**
	 * 存在改键
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-4-29 上午9:14:37
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 * @param keySetName
	 * @return
	 */
	public boolean existKey(Object keySetName, String src, String dest) {
		return keySetMap.get(keySetName).get(dest.trim()) != null;
	}
	
	public void addKey(Object keySetName, String src, String dest) {
		keySetMap.get(keySetName).put(dest.toUpperCase(), src.toUpperCase());
	}
	/**
	 * 删除改键方案下面的改键
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-4-28 下午3:29:39
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 * @param keySetName
	 * @param dest
	 */
	public void delKey(Object keySetName, String dest) {
		keySetMap.get(keySetName).remove(dest);
	}
}
