package cc.hust.keyhelper.global.win;

public class KeySwitchBean {
	public String srcKey;
	public String destKey;
	public KeySwitchBean() {
		this("", "");
	}
	public KeySwitchBean(String srcKey, String destKey) {
		this.srcKey = srcKey;
		this.destKey = destKey;
	}
	public String toString() {
		return this.srcKey + "=>" + this.destKey;
	}
}
