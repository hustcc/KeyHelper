package cc.hust.keyhelper.global.win;

/**
 * get Key Combination by using modifiers
 * split Key Combination to single key
 * @author Michael.Jiahui.Mai
 * @version 2013-04-08 19:29:23
 * @Contract kafaimak@yahoo.cn  or https://github.com/hustcc
 */

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import cc.hust.keyhelper.util.KeyCodeMap;

public class KeyCombination {
	// 得到组合键字符串，用于表格对组合键的显示
	public static String getKeyCombination(KeyEvent e, boolean combination) {
		String presskey = KeyCodeMap.getKey(e.getKeyCode());
		int mod = e.getModifiersEx();
		if (! combination) {
			return presskey;
		}
		if ((mod & InputEvent.ALT_GRAPH_DOWN_MASK) != 0
				&& e.getKeyCode() != KeyEvent.VK_ALT_GRAPH) {
			presskey = "SHIFT+" + presskey;
		}
		if ((mod & InputEvent.META_DOWN_MASK) != 0
				&& e.getKeyCode() != KeyEvent.VK_META) {
			presskey = "SHIFT+" + presskey;
		}
		if ((mod & InputEvent.SHIFT_DOWN_MASK) != 0
				&& e.getKeyCode() != KeyEvent.VK_SHIFT) {
			presskey = "SHIFT+" + presskey;
		}
		if ((mod & InputEvent.ALT_DOWN_MASK) != 0
				&& e.getKeyCode() != KeyEvent.VK_ALT) {
			presskey = "ALT+" + presskey;
		}
		if ((mod & InputEvent.CTRL_DOWN_MASK) != 0
				&& e.getKeyCode() != KeyEvent.VK_CONTROL) {
			presskey = "CTRL+" + presskey;
		}
		return presskey;
	}
	// 得到组合键的各个单键
	public static String[] splitKeyCombination(String keyCombination) {
		return keyCombination.split("\\+");
	}
}
