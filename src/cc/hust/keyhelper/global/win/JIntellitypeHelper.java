package cc.hust.keyhelper.global.win;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cc.hust.keyhelper.config.KeySetConfig;
import cc.hust.keyhelper.frame.MainFrame;
import cc.hust.keyhelper.util.KeyCodeMap;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.IntellitypeListener;
import com.melloware.jintellitype.JIntellitype;

/**
 * 监听管理类
 * @author Xewee.Zhiwei.Wang
 * @version 2013-1-29 上午11:10:12
 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
 */
public class JIntellitypeHelper implements HotkeyListener, IntellitypeListener {
	public static int HOT_KEY_START = 0;
	private static int IDENTIFIER = 1;
	private Robot robot;
	private MainFrame parent;
	private static JIntellitypeHelper instance;
	
	private Map<Integer, KeySwitchBean> registedKey = new HashMap<Integer, KeySwitchBean>();
	
	private JIntellitypeHelper(MainFrame parent) {
		this.parent = parent;
	}
	/**
	 * 退出程序，清除资源
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-1-29 下午1:26:52
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 */
	public void cleanUp() {
		JIntellitype.getInstance().cleanUp();
	}
	/**
	 * 启动程序，初始化监控
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-1-29 下午1:27:05
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 */
	public void initJIntellitype() {
		try {
			JIntellitype.getInstance().addHotKeyListener(this);
			JIntellitype.getInstance().addIntellitypeListener(this);
			robot = new Robot();
			System.out.println("initial JIntellitype SUCCESS.");
			//注册监听切换按钮Alt + Z
			JIntellitype.getInstance().registerHotKey(HOT_KEY_START, JIntellitype.MOD_ALT, 'Z');
		} catch (RuntimeException e) {
			System.out.println("initial JIntellitype ERROR.");
		} catch (AWTException e) {
			System.out.println("initial JIntellitype ERROR.");
		}
	}
	/**
	 * 注册监听的按键
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-1-29 下午1:37:43
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 * @param keyMap
	 */
	public void registe(Map<String, String> keyMap) {
		registedKey.clear();
		//注册配置文件中的改键
		Iterator<String> keyIterator = keyMap.keySet().iterator();
		KeySwitchBean bean = null;
		while (keyIterator.hasNext()) {
			bean = new KeySwitchBean();
			bean.destKey = keyIterator.next();
			bean.srcKey = keyMap.get(bean.destKey);
			
			registedKey.put(IDENTIFIER, bean);
			JIntellitype.getInstance().registerHotKey(IDENTIFIER ++, bean.destKey);
		}
		System.out.println("JIntellitype registe.");
	}
	/**
	 * 注册单个按键
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-4-29 上午9:23:47
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 * @param src
	 * @param dest
	 */
	public void registe(String src, String dest) {
		KeySwitchBean bean = new KeySwitchBean(src, dest);
		registedKey.put(KeyCodeMap.getJavaKeyCode(bean.destKey), bean);
		JIntellitype.getInstance().registerHotKey(KeyCodeMap.getJavaKeyCode(bean.destKey), bean.destKey);
	}
	/**
	 * 取消按键注册
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-1-29 下午1:37:54
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 */
	public void unregisteAll() {
		if (registedKey != null) {
			Iterator<Integer> keyIterator = registedKey.keySet().iterator();
			while (keyIterator.hasNext()) {
				JIntellitype.getInstance().unregisterHotKey(keyIterator.next());
			}
		}
		System.out.println("JIntellitype unregiste.");
	}
	/**
	 * 取消监听按键
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-4-28 下午4:35:30
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 */
	public void unregiste(String key) {
		if (registedKey != null) {
			JIntellitype.getInstance().unregisterHotKey(KeyCodeMap.getJavaKeyCode(key));
			registedKey.remove(KeyCodeMap.getJavaKeyCode(key));
		}
		System.out.println("JIntellitype unregiste " + key + ".");
	}
	
	/**
	 * 获取已经存在的instance
	 * @author Xewee.Zhiwei.Wang
	 * @version 2013-1-29 上午11:22:01
	 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
	 * @return
	 */
	public static JIntellitypeHelper instance(MainFrame parent) {
		if (instance == null) {
			instance = new JIntellitypeHelper(parent);
		}
		return instance;
	}
	public void onHotKey(int aIdentifier) {
		if (aIdentifier == HOT_KEY_START) {
			parent.getStartButton().doClick();
			KeySetConfig.instance().saveToXml();
			return ;
		}
		
		KeySwitchBean bean = registedKey.get(aIdentifier);
		System.out.println(bean);
		//先取消注册，再触发原来的按键，然后注册监听，防止监听死循环
		JIntellitype.getInstance().unregisterHotKey(aIdentifier);
		//TODO 如果是全屏，并且鼠标状态不是输入状态，改键
		if (true) {
//			robot.keyPress(KeyCodeMap.getJavaKeyCode(bean.srcKey));
//			robot.delay(5);
//			robot.keyRelease(KeyCodeMap.getJavaKeyCode(bean.srcKey));
			String[] keys = KeyCombination.splitKeyCombination(bean.srcKey);
			for (int i = 0; i < keys.length; i++) {
				robot.keyPress(KeyCodeMap.getJavaKeyCode(keys[i]));
			}
			
			robot.delay(5);//睡眠指定的时间
			for (int i = 0; i < keys.length; i++) {
				robot.keyRelease(KeyCodeMap.getJavaKeyCode(keys[i]));
			}
		}
//		else {
//			//否则，触发原来的按键
//			robot.keyPress(KeyCodeMap.getJavaKeyCode(bean.destKey));
//			robot.delay(5);
//			robot.keyRelease(KeyCodeMap.getJavaKeyCode(bean.destKey));
//		}
		//重新注册
		JIntellitype.getInstance().registerHotKey(aIdentifier, bean.destKey);
	}
	public void onIntellitype(int aCommand) {
	}

}
