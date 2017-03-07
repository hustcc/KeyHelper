package cc.hust.keyhelper.util;

/******************Michael**********************/
/******************Start************************/

/**
 * @author Michael.Jiahui.Mai
 * @version 2013-04-05 22:20:39
 * @Contract kafaimak@yahoo.cn  or https://github.com/hustcc
 */

/**
 * 输入文本框编辑器时，Enter键要按Ctrl+Enter才能输入
 * 
 */

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;


public class KeyCodeMap {
	static Map<Integer, String> keycode2str = new HashMap<Integer, String>();//键值对应的字符串
	static Map<String, Integer> str2keycode = new HashMap<String, Integer>();//字符串对应的键值
	static Map<Integer, String> ShieldKeyCodeMap = new HashMap<Integer, String>();
	//java键盘键值
	static Integer[] javaKeyCode = {
			// 字母26
			KeyEvent.VK_A,
			KeyEvent.VK_B,
			KeyEvent.VK_C,
			KeyEvent.VK_D,
			KeyEvent.VK_E,
			KeyEvent.VK_F,
			KeyEvent.VK_G,
			KeyEvent.VK_H,
			KeyEvent.VK_I,
			KeyEvent.VK_J,
			KeyEvent.VK_K,
			KeyEvent.VK_L,
			KeyEvent.VK_M,
			KeyEvent.VK_N,
			KeyEvent.VK_O,
			KeyEvent.VK_P,
			KeyEvent.VK_Q,
			KeyEvent.VK_R,
			KeyEvent.VK_S,
			KeyEvent.VK_T,
			KeyEvent.VK_U,
			KeyEvent.VK_V,
			KeyEvent.VK_W,
			KeyEvent.VK_X,
			KeyEvent.VK_Y,
			KeyEvent.VK_Z,
			// 数字21
			KeyEvent.VK_0,
			KeyEvent.VK_1,
			KeyEvent.VK_2,
			KeyEvent.VK_3,
			KeyEvent.VK_4,
			KeyEvent.VK_5,
			KeyEvent.VK_6,
			KeyEvent.VK_7,
			KeyEvent.VK_8,
			KeyEvent.VK_9,
			KeyEvent.VK_NUMPAD0,
			KeyEvent.VK_NUMPAD1,
			KeyEvent.VK_NUMPAD2,
			KeyEvent.VK_NUMPAD3,
			KeyEvent.VK_NUMPAD4,
			KeyEvent.VK_NUMPAD5,
			KeyEvent.VK_NUMPAD6,
			KeyEvent.VK_NUMPAD7,
			KeyEvent.VK_NUMPAD8,
			KeyEvent.VK_NUMPAD9,
			KeyEvent.VK_NUM_LOCK,
			// 功能键F24
			KeyEvent.VK_F1,
			KeyEvent.VK_F2,
			KeyEvent.VK_F3,
			KeyEvent.VK_F4,
			KeyEvent.VK_F5,
			KeyEvent.VK_F6,
			KeyEvent.VK_F7,
			KeyEvent.VK_F8,
			KeyEvent.VK_F9,
			KeyEvent.VK_F10,
			KeyEvent.VK_F11,
			KeyEvent.VK_F12,
			KeyEvent.VK_F13,
			KeyEvent.VK_F14,
			KeyEvent.VK_F15,
			KeyEvent.VK_F16,
			KeyEvent.VK_F17,
			KeyEvent.VK_F18,
			KeyEvent.VK_F19,
			KeyEvent.VK_F20,
			KeyEvent.VK_F21,
			KeyEvent.VK_F22,
			KeyEvent.VK_F23,
			KeyEvent.VK_F24,
			// 方位键8
			KeyEvent.VK_LEFT,
			KeyEvent.VK_RIGHT,
			KeyEvent.VK_UP,
			KeyEvent.VK_DOWN,
			KeyEvent.VK_KP_LEFT,
			KeyEvent.VK_KP_RIGHT,
			KeyEvent.VK_KP_UP,
			KeyEvent.VK_KP_DOWN,// 数字键盘方向键
			// 常用键16
			KeyEvent.VK_PAGE_UP,
			KeyEvent.VK_PAGE_DOWN,
			KeyEvent.VK_HOME,
			KeyEvent.VK_END,
			KeyEvent.VK_TAB,
			KeyEvent.VK_CAPS_LOCK,
			KeyEvent.VK_SHIFT,
			KeyEvent.VK_ALT,
			KeyEvent.VK_CONTROL,
			KeyEvent.VK_SPACE,
			KeyEvent.VK_ENTER,
			KeyEvent.VK_WINDOWS,
			KeyEvent.VK_INSERT,
			KeyEvent.VK_DELETE,
			KeyEvent.VK_BACK_SPACE,
			KeyEvent.VK_ESCAPE,
			// 符号键19
			KeyEvent.VK_CLOSE_BRACKET,
			KeyEvent.VK_AT,
			KeyEvent.VK_BACK_SLASH,
			KeyEvent.VK_CIRCUMFLEX,
			KeyEvent.VK_COLON,
			KeyEvent.VK_COMMA,
			KeyEvent.VK_DOLLAR,
			KeyEvent.VK_EQUALS,
			KeyEvent.VK_EXCLAMATION_MARK,
			KeyEvent.VK_LEFT_PARENTHESIS,
			KeyEvent.VK_MINUS,
			KeyEvent.VK_NUMBER_SIGN,
			KeyEvent.VK_OPEN_BRACKET,
			KeyEvent.VK_PERIOD,
			KeyEvent.VK_PLUS,
			KeyEvent.VK_RIGHT_PARENTHESIS,
			KeyEvent.VK_SEMICOLON,
			KeyEvent.VK_SLASH,
			KeyEvent.VK_UNDERSCORE,

			// 其他字段值78
			KeyEvent.VK_ACCEPT, KeyEvent.VK_ADD, KeyEvent.VK_AGAIN,
			KeyEvent.VK_ALL_CANDIDATES, KeyEvent.VK_ALPHANUMERIC,
			KeyEvent.VK_ALT_GRAPH, KeyEvent.VK_AMPERSAND, KeyEvent.VK_ASTERISK,
			KeyEvent.VK_BACK_QUOTE, KeyEvent.VK_BEGIN, KeyEvent.VK_BRACELEFT,
			KeyEvent.VK_BRACERIGHT, KeyEvent.VK_CANCEL, KeyEvent.VK_CIRCUMFLEX,
			KeyEvent.VK_CLEAR, KeyEvent.VK_CODE_INPUT, KeyEvent.VK_COMPOSE,
			KeyEvent.VK_CONTEXT_MENU, KeyEvent.VK_CONVERT, KeyEvent.VK_COPY,
			KeyEvent.VK_CUT, KeyEvent.VK_DEAD_ABOVEDOT,
			KeyEvent.VK_DEAD_ABOVERING, KeyEvent.VK_DEAD_ACUTE,
			KeyEvent.VK_DEAD_BREVE, KeyEvent.VK_DEAD_CARON,
			KeyEvent.VK_DEAD_CEDILLA, KeyEvent.VK_DEAD_CIRCUMFLEX,
			KeyEvent.VK_DEAD_DIAERESIS, KeyEvent.VK_DEAD_DOUBLEACUTE,
			KeyEvent.VK_DEAD_GRAVE, KeyEvent.VK_DEAD_IOTA,
			KeyEvent.VK_DEAD_MACRON, KeyEvent.VK_DEAD_OGONEK,
			KeyEvent.VK_DEAD_SEMIVOICED_SOUND, KeyEvent.VK_DEAD_TILDE,
			KeyEvent.VK_DEAD_VOICED_SOUND, KeyEvent.VK_DECIMAL,
			KeyEvent.VK_DIVIDE, KeyEvent.VK_EURO_SIGN, KeyEvent.VK_FINAL,
			KeyEvent.VK_FIND, KeyEvent.VK_FULL_WIDTH, KeyEvent.VK_GREATER,
			KeyEvent.VK_HALF_WIDTH, KeyEvent.VK_HELP, KeyEvent.VK_HIRAGANA,
			KeyEvent.VK_INPUT_METHOD_ON_OFF,
			KeyEvent.VK_INVERTED_EXCLAMATION_MARK,
			KeyEvent.VK_JAPANESE_HIRAGANA, KeyEvent.VK_JAPANESE_KATAKANA,
			KeyEvent.VK_JAPANESE_ROMAN, KeyEvent.VK_KANA,
			KeyEvent.VK_KANA_LOCK, KeyEvent.VK_KANJI, KeyEvent.VK_KATAKANA,
			KeyEvent.VK_LEFT_PARENTHESIS, KeyEvent.VK_LESS, KeyEvent.VK_META,
			KeyEvent.VK_MODECHANGE, KeyEvent.VK_MULTIPLY,
			KeyEvent.VK_NONCONVERT, KeyEvent.VK_PASTE, KeyEvent.VK_PAUSE,
			KeyEvent.VK_PREVIOUS_CANDIDATE, KeyEvent.VK_PRINTSCREEN,
			KeyEvent.VK_PROPS, KeyEvent.VK_QUOTE, KeyEvent.VK_QUOTEDBL,
			KeyEvent.VK_ROMAN_CHARACTERS, KeyEvent.VK_SCROLL_LOCK,
			KeyEvent.VK_SEPARATER, KeyEvent.VK_SEPARATOR, KeyEvent.VK_STOP,
			KeyEvent.VK_SUBTRACT, KeyEvent.VK_UNDEFINED,
			KeyEvent.VK_UNDERSCORE, KeyEvent.VK_UNDO
	};
	//数组index为键值对应的键盘按键
	static String[] key = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7",
			"8",
			"9",
			"NUMPAD0",
			"NUMPAD1",
			"NUMPAD2",
			"NUMPAD3",
			"NUMPAD4",
			"NUMPAD5",
			"NUMPAD6",
			"NUMPAD7",
			"NUMPAD8",
			"NUMPAD9",
			"NUM_LOCK",
			"F1",
			"F2",
			"F3",
			"F4",
			"F5",
			"F6",
			"F7",
			"F8",
			"F9",
			"F10",
			"F11",
			"F12",
			"F13",
			"F14",
			"F15",
			"F16",
			"F17",
			"F18",
			"F19",
			"F20",
			"F21",
			"F22",
			"F23",
			"F24",
			"LEFT",
			"RIGHT",
			"UP",
			"DOWN",
			"KP_LEFT",
			"KP_RIGHT",
			"KP_UP",
			"KP_DOWN",
			"PAGE_UP",
			"PAGE_DOWN",
			"PAGE_HOME",
			"PAGE_END",
			"TAB",
			"CAPS_LOCK",
			"SHIFT",
			"ALT",
			"CTRL",
			"SPACE",
			"ENTER",
			"WINDOWS",
			"INSERT",
			"DELETE",
			"BACK_SPACE",
			"ESCAPE",

			// 符号键
			"CLOSE_BRACKET",
			"AT",
			"BACK_SLASH",
			"CIRCUMFLEX",
			"COLON",
			"COMMA",
			"DOLLAR",
			"EQUALS",
			"EXCLAMATION_MARK",
			"LEFT_PARENTHESIS",
			"MINUS",
			"NUMBER_SIGN",
			"OPEN_BRACKET",
			"PERIOD",
			"PLUS",
			"RIGHT_PARENTHESIS",
			"SEMICOLON",
			"SLASH",
			"UNDERSCORE",

			// "]","@","\\","`",":",",","$","=","!","(","-","#","[",".",
			// "+",")",";","/","_"

			// 其他字段值78
			"ACCEPT", "ADD", "AGAIN", "ALL_CANDIDATES", "ALPHANUMERIC",
			"ALT_GRAPH", "AMPERSAND", "ASTERISK", "BACK_QUOTE", "BEGIN",
			"BRACELEFT", "BRACERIGHT", "CANCEL", "CIRCUMFLEX", "CLEAR",
			"CODE_INPUT", "COMPOSE", "CONTEXT_MENU", "CONVERT", "COPY", "CUT",
			"DEAD_ABOVEDOT", "DEAD_ABOVERING", "DEAD_ACUTE", "DEAD_BREVE",
			"DEAD_CARON", "DEAD_CEDILLA", "DEAD_CIRCUMFLEX", "DEAD_DIAERESIS",
			"DEAD_DOUBLEACUTE", "DEAD_GRAVE", "DEAD_IOTA", "DEAD_MACRON",
			"DEAD_OGONEK", "DEAD_SEMIVOICED_SOUND", "DEAD_TILDE",
			"DEAD_VOICED_SOUND", "DECIMAL", "DIVIDE", "EURO_SIGN", "FINAL",
			"FIND", "FULL_WIDTH", "GREATER", "HALF_WIDTH", "HELP", "HIRAGANA",
			"INPUT_METHOD_ON_OFF", "INVERTED_EXCLAMATION_MARK",
			"JAPANESE_HIRAGANA", "JAPANESE_KATAKANA", "JAPANESE_ROMAN", "KANA",
			"KANA_LOCK", "KANJI", "KATAKANA", "LEFT_PARENTHESIS", "LESS",
			"META", "MODECHANGE", "MULTIPLY", "NONCONVERT", "PASTE", "PAUSE",
			"PREVIOUS_CANDIDATE", "PRINTSCREEN", "PROPS", "QUOTE", "QUOTEDBL",
			"ROMAN_CHARACTERS", "SCROLL_LOCK", "SEPARATER", "SEPARATOR",
			"STOP", "SUBTRACT", "UNDEFINED", "UNDERSCORE", "UNDO"

	};

	static Integer[] ShieldKeyCode = {
			// F功能键
			KeyEvent.VK_F1, KeyEvent.VK_F2, KeyEvent.VK_F3, KeyEvent.VK_F4,
			KeyEvent.VK_F5, KeyEvent.VK_F6,
			KeyEvent.VK_F7,
			KeyEvent.VK_F8,
			KeyEvent.VK_F9,
			KeyEvent.VK_F10,
			KeyEvent.VK_F11,
			KeyEvent.VK_F12,
			// 方位键
			KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP,
			KeyEvent.VK_DOWN,
			KeyEvent.VK_KP_LEFT,
			KeyEvent.VK_KP_RIGHT,
			KeyEvent.VK_KP_UP,
			KeyEvent.VK_KP_DOWN,
			// 常用键15
			KeyEvent.VK_PAGE_UP, KeyEvent.VK_PAGE_DOWN, KeyEvent.VK_HOME,
			KeyEvent.VK_END, KeyEvent.VK_TAB, KeyEvent.VK_CAPS_LOCK,
			KeyEvent.VK_SHIFT, KeyEvent.VK_ALT, KeyEvent.VK_CONTROL,
			KeyEvent.VK_SPACE, KeyEvent.VK_ENTER, KeyEvent.VK_INSERT,
			KeyEvent.VK_DELETE, KeyEvent.VK_BACK_SPACE, KeyEvent.VK_ESCAPE,

	};

	// 初始化哈希表
	static {
		for (int i = 0; i < javaKeyCode.length; i++) {
			keycode2str.put(javaKeyCode[i], key[i]);
		}
		for (int i = 0; i < key.length; i++) {
			str2keycode.put(key[i], javaKeyCode[i]);
		}
		// 屏蔽键
		for (int i = 0; i < ShieldKeyCode.length; i++) {
			ShieldKeyCodeMap.put(ShieldKeyCode[i],
					keycode2str.get(ShieldKeyCode[i]));
		}
	}

	// 判断是否为屏蔽键
	public static boolean isShieldKeyCode(Integer keyCode) {
		return ShieldKeyCodeMap.containsKey(keyCode);
	}

	// 获取keycode2str
	public static Map<Integer, String> getkeycode2str() {
		return keycode2str;
	}

	// 获取str2keycode
	public static Map<String, Integer> getstr2keycode() {
		return str2keycode;
	}

	// 得到哈希表keycode2str键值
	public static String getKey(Integer keyCode) {
		if (keyCode != null && (!keyCode.equals(""))) {
			return keycode2str.get(keyCode);
		}
		return null;
	}

	// 得到哈希表str2keycode键值
	public static Integer getJavaKeyCode(String keyStr) {
		if (! StringUtil.isBlank(keyStr)) {
			return str2keycode.get(keyStr.toUpperCase());
		}
		return -1;
	}
}