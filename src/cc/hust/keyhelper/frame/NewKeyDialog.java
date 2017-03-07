/**
 * @author Xewee.Zhiwei.Wang
 * @version 2013-4-28 下午5:06:50
 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
 */
package cc.hust.keyhelper.frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cc.hust.keyhelper.config.KeySetConfig;
import cc.hust.keyhelper.global.win.JIntellitypeHelper;
import cc.hust.keyhelper.global.win.KeyCombination;
import cc.hust.keyhelper.util.KeyCodeMap;
import cc.hust.keyhelper.util.R;
import cc.hust.keyhelper.util.StringUtil;

/**
 * 
 * @author Xewee.Zhiwei.Wang
 * @version 2013-4-28 下午5:06:50
 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
 */
public class NewKeyDialog extends JDialog implements ActionListener, KeyListener, FocusListener {
	private static final long serialVersionUID = 1L;
	private MainFrame parent;
	private JTextField srcTextField;
	private JTextField destTextField;
	private JButton okButton;
	private JButton cancelButton;
	private JCheckBox handCheckBox;
	private JLabel helpLabel;
	
	public NewKeyDialog(MainFrame parent) {
		this.parent = parent;
		
		setResizable(false);
		setModal(true);
		setTitle("添加改键");
		getContentPane().setLayout(null);
		setSize(340, 140);
		setLocation(parent.getLocation().x + parent.getWidth() / 2, parent.getLocation().y + parent.getHeight() / 2);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation( (screenSize.width - getSize().width) / 2, (screenSize.height - getSize().height) / 2);
		
		JLabel noticeLabel = new JLabel("注：原键=>改键 意为 按下改键触发原键");
		noticeLabel.setForeground(Color.RED);
		noticeLabel.setBounds(10, 10, 314, 15);
		getContentPane().add(noticeLabel);
		
		JLabel srcKeyLabel = new JLabel("原键:");
		srcKeyLabel.setBounds(10, 39, 36, 15);
		getContentPane().add(srcKeyLabel);
		
		srcTextField = new JTextField();
		srcTextField.setBounds(49, 36, 91, 21);
		getContentPane().add(srcTextField);
		srcTextField.setColumns(10);
		srcTextField.setToolTipText("原始键 - 可以为组合键");
		srcTextField.addKeyListener(this);
		srcTextField.addFocusListener(this);
		srcTextField.requestFocus(); 
		
		JLabel toLabel = new JLabel(" ==>");
		toLabel.setBounds(150, 39, 27, 15);
		getContentPane().add(toLabel);
		
		JLabel destKeyLabel = new JLabel("改键:");
		destKeyLabel.setBounds(187, 39, 36, 15);
		getContentPane().add(destKeyLabel);
		
		destTextField = new JTextField();
		destTextField.setBounds(233, 36, 91, 21);
		getContentPane().add(destTextField);
		destTextField.setToolTipText("改键 - 可以为组合键");
		destTextField.setColumns(10);
		destTextField.addKeyListener(this);
		destTextField.addFocusListener(this);
		
		
		cancelButton = new JButton("取消");
		cancelButton.setBounds(258, 79, 66, 23);
		cancelButton.addActionListener(this);
		getContentPane().add(cancelButton);
		
		okButton = new JButton("确定");
		okButton.setBounds(187, 79, 66, 23);
		okButton.addActionListener(this);
		getContentPane().add(okButton);
		
		handCheckBox = new JCheckBox("手动模式");
		handCheckBox.setToolTipText("勾选后手动配置按键");
		handCheckBox.setBounds(10, 79, 97, 23);
		handCheckBox.addActionListener(this);
		getContentPane().add(handCheckBox);
		
		helpLabel = new JLabel("");
		helpLabel.setBounds(113, 79, 27, 23);
		getContentPane().add(helpLabel);
		helpLabel.setIcon(new ImageIcon(R.HELP_PIC_PATH));
		helpLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					Runtime.getRuntime().exec("cmd /c start https://github.com/hustcc/KeyHelper");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			public void mouseEntered(MouseEvent e) {
				helpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		setIconImage(Toolkit.getDefaultToolkit().createImage(R.LOGO_PIC_PATH));
	}
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == okButton) {
			if (StringUtil.isBlank(srcTextField.getText())) {
				JOptionPane.showMessageDialog(this, "原键不能为空，请设置后再试！", "WARNING", JOptionPane.WARNING_MESSAGE);
				srcTextField.requestFocus();
				return;
			}
			else if (StringUtil.isBlank(destTextField.getText())) {
				JOptionPane.showMessageDialog(this, "改键不能为空，请设置后再试！", "WARNING", JOptionPane.WARNING_MESSAGE);
				destTextField.requestFocus();
				return;
			}
			else if (KeySetConfig.instance().existKey(parent.getChooseKeySetCombo().getSelectedItem(), srcTextField.getText(), destTextField.getText())) {
				JOptionPane.showMessageDialog(this, "改键方案中已经存在映射到【" + destTextField.getText() + "】的改键，请重新设置！", "WARNING", JOptionPane.WARNING_MESSAGE);
				destTextField.requestFocus();
				return;
			}
			String[] keys = KeyCombination.splitKeyCombination(srcTextField.getText());
			for (int i = 0; i < keys.length; i++) {
				if (KeyCodeMap.getJavaKeyCode(keys[i]) == null) {
					JOptionPane.showMessageDialog(this, "按键是一个不存在的按键获组合按键！", "WARNING", JOptionPane.WARNING_MESSAGE);
					srcTextField.requestFocus();
					srcTextField.selectAll();
					return;
				}
			}
			
			keys = KeyCombination.splitKeyCombination(destTextField.getText());
			for (int i = 0; i < keys.length; i++) {
				if (KeyCodeMap.getJavaKeyCode(keys[i]) == null) {
					JOptionPane.showMessageDialog(this, "改键是一个不存在的按键获组合按键！", "WARNING", JOptionPane.WARNING_MESSAGE);
					destTextField.requestFocus();
					destTextField.selectAll();
					return;
				}
			}
			
//			if (KeyCodeMap.getJavaKeyCode(destTextField.getText()) == null) {
//				JOptionPane.showMessageDialog(this, "改键是一个不存在的按键", "WARNING", JOptionPane.WARNING_MESSAGE);
//				destTextField.requestFocus();
//				destTextField.selectAll();
//				return;
//			}
			//加到内存中
			KeySetConfig.instance().addKey(parent.getChooseKeySetCombo().getSelectedItem(), srcTextField.getText(), destTextField.getText());
			//显示在table中
			parent.getTableModel().setModelData(KeySetConfig.instance().getKeyMap(parent.getChooseKeySetCombo().getSelectedItem()));
			//如果正在监听，监听这个键
			if (parent.isListening()) {
				JIntellitypeHelper.instance(parent).registe(srcTextField.getText(), destTextField.getText());
			}
			//关闭添加按键的窗口
			this.setVisible(false);
			this.dispose();
		}
		else if (source == cancelButton) {
			this.setVisible(false);
			this.dispose();
		}
		else if (source == handCheckBox) {
			if (handCheckBox.isSelected()) {
				srcTextField.removeKeyListener(this);
				destTextField.removeKeyListener(this);
			}
			else {
				srcTextField.addKeyListener(this);
				destTextField.addKeyListener(this);
			}
		}
	}
	public void keyPressed(KeyEvent e) {
		if (KeyCodeMap.isShieldKeyCode(e.getKeyCode())){//屏蔽键，如Tab、ESC键
			((JTextField)e.getSource()).setText(KeyCombination.getKeyCombination(e, true));
			e.consume();//注销键盘事件
		}      		
		else {
			((JTextField)e.getSource()).setText(KeyCombination.getKeyCombination(e, true));
		}
	}
	public void keyReleased(KeyEvent e) {
		//按下Print Screen鍵不触发 keyPressed方法，只調用keyReleased						
		if(e.getKeyCode() == KeyEvent.VK_PRINTSCREEN){
			((JTextField)e.getSource()).setText(KeyCombination.getKeyCombination(e, e.getSource() != destTextField));
		}
	}
	public void keyTyped(KeyEvent e) {
		
	}
	public void focusGained(FocusEvent e) {
		if (handCheckBox.isSelected()) {
			((JTextField)e.getSource()).getInputMap().setParent(new JTextField().getInputMap(JComponent.WHEN_FOCUSED));//屏蔽JTextField默认的键盘事件
			((JTextField)e.getSource()).enableInputMethods (true);//关闭控件输入法的方法
			((JTextField)e.getSource()).setFocusTraversalKeysEnabled(true);
		}
		else {
			((JTextField)e.getSource()).getInputMap().setParent(null);//屏蔽JTextField默认的键盘事件
			((JTextField)e.getSource()).enableInputMethods (false);//关闭控件输入法的方法
			((JTextField)e.getSource()).setFocusTraversalKeysEnabled(false);
		}
	}
	public void focusLost(FocusEvent e) {
		
	}
}
