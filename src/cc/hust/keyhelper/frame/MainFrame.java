package cc.hust.keyhelper.frame;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import cc.hust.keyhelper.config.Config;
import cc.hust.keyhelper.config.KeySetConfig;
import cc.hust.keyhelper.global.win.JIntellitypeHelper;
import cc.hust.keyhelper.util.R;
import cc.hust.keyhelper.util.StringUtil;

import com.melloware.jintellitype.JIntellitype;
/**
 * 主界面
 * @author Xewee.Zhiwei.Wang
 * @version 2013-1-29 上午9:59:55
 * @Contract wzwahl36@QQ.com or https://github.com/hustcc
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MainFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable table;//改键明细的table
	private KeyTableModel tableModel;
	private JComboBox chooseKeySetCombo;//方案选择下拉框
	JButton newKeySetButton;//新建改键方案
	JButton newKeyButton;//新建改键
	JButton delKeyButton;//删除改键
	JButton delKeySetButton;//删除方案
	JButton startButton;//开始监听改键（电脑全屏或者war运行中，且不是输入模式）
//	JLabel emailLabel;
	JLabel helpLabel;
	private boolean isListening = false;
	/**
	 * Launch the application.
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		try {
			if (JIntellitype.checkInstanceAlreadyRunning("JIntellitype Test Application")) {
				System.exit(1);
			}
			if (!JIntellitype.isJIntellitypeSupported()) {
				System.exit(1);
			}
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(new PrintWriter(new File("error.log")));
			System.exit(1);
		}
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("KeyHelper - hustcc");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 295, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		chooseKeySetCombo = new JComboBox(KeySetConfig.instance().getKeySetArray());
		chooseKeySetCombo.setBounds(10, 10, 176, 21);
		chooseKeySetCombo.addActionListener(this);
		contentPane.add(chooseKeySetCombo);
		
		newKeySetButton = new JButton("新建方案");
		newKeySetButton.setToolTipText("新建方案");
		newKeySetButton.setBounds(196, 10, 83, 23);
		newKeySetButton.addActionListener(this);
		contentPane.add(newKeySetButton);
		
		newKeyButton = new JButton("新增改键");
		newKeyButton.setToolTipText("新增改键");
		newKeyButton.setBounds(10, 41, 83, 23);
		newKeyButton.addActionListener(this);
		contentPane.add(newKeyButton);
		
		delKeyButton = new JButton("删除改键");
		delKeyButton.setToolTipText("删除改键");
		delKeyButton.setBounds(103, 41, 83, 23);
		delKeyButton.addActionListener(this);
		contentPane.add(delKeyButton);
		
		delKeySetButton = new JButton("删除方案");
		delKeySetButton.setToolTipText("删除方案");
		delKeySetButton.setBounds(196, 41, 83, 23);
		delKeySetButton.addActionListener(this);
		contentPane.add(delKeySetButton);
		
		tableModel = new KeyTableModel(KeySetConfig.instance().getKeyMap(chooseKeySetCombo.getSelectedItem()));
		table = new JTable(tableModel);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(10, 74, 269, 302);
		contentPane.add(scrollpane);
		
		startButton = new JButton();
		startButton.setIcon(new ImageIcon(R.POWER_OFF_PIC_PATH));
		startButton.setText("Start");
		startButton.setBounds(196, 386, 83, 23);
		startButton.setToolTipText("按Alt + Z切换监听状态.");
		startButton.addActionListener(this);
		contentPane.add(startButton);
		
//		emailLabel = new JLabel();
//		emailLabel.setIcon(new ImageIcon(R.EMAIL_PIC_PATH));
//		emailLabel.setBounds(10, 386, 160, 23);
//		emailLabel.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				try {
//					Runtime.getRuntime().exec("cmd /c start https://github.com/hustcc/KeyHelper");
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}
//			public void mouseEntered(MouseEvent e) {
//				emailLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
//			}
//		});
//		contentPane.add(emailLabel);
		
		helpLabel = new JLabel();
		helpLabel.setIcon(new ImageIcon(R.HELP_PIC_PATH));
		helpLabel.setBounds(173, 386, 20, 23);
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
		contentPane.add(helpLabel);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("windows closing, save data.");
				Config.instance().lastKeySet = chooseKeySetCombo.getSelectedItem() != null ? chooseKeySetCombo.getSelectedItem().toString() : "";
				Config.instance().lastLocationX = getLocation().x;
				Config.instance().lastLocationY = getLocation().y;
				Config.instance().saveToXml();
				KeySetConfig.instance().saveToXml();
				JIntellitype.getInstance().cleanUp();
				super.windowClosing(e);
			}
		});
		JIntellitypeHelper.instance(this).initJIntellitype();
		//个性化配置
		chooseKeySetCombo.setSelectedItem(Config.instance().lastKeySet);
		chooseKeySetCombo.setToolTipText(String.valueOf(chooseKeySetCombo.getSelectedItem()));
		setLocation(Config.instance().lastLocationX, Config.instance().lastLocationY);
		setIconImage(Toolkit.getDefaultToolkit().createImage(R.LOGO_PIC_PATH));
	}
	
	public void actionPerformed(ActionEvent e) {
		Object srcObject = e.getSource();
		if (srcObject == newKeySetButton) {
			String keySet = JOptionPane.showInputDialog("请输入改键方案的名称:");
			if(keySet != null) {
				if (StringUtil.isBlank(keySet)) {
					JOptionPane.showMessageDialog(this, "改键方案的名称不能为空，请重试！", "提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (KeySetConfig.instance().existKeySet(keySet)) {
					JOptionPane.showMessageDialog(this, "改键方案的名称已经【" + keySet + "】存在，请重试！", "提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				KeySetConfig.instance().addKeySet(keySet);
				chooseKeySetCombo.addItem(keySet);
				chooseKeySetCombo.setSelectedItem(keySet);
			}
		}
		else if (srcObject == newKeyButton) {
			if (chooseKeySetCombo.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "不存在改键方案，请先添加改键方案！", "WARNING", JOptionPane.WARNING_MESSAGE);
				return;
			}
			new NewKeyDialog(this).setVisible(true);
		}
		else if (srcObject == delKeyButton) {
			int row = table.getSelectedRow();
			//合法的选择
			if (row >= 0) {
				int result = JOptionPane.showConfirmDialog(this, "确定删除改键 【" +table.getValueAt(row, 0) + "=>" + table.getValueAt(row, 1) +"】 ?","提示",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION){
					//取消监听
					if (isListening) {
						JIntellitypeHelper.instance(this).unregiste(table.getValueAt(row, 1).toString());
					}
					//删除内存数据
					KeySetConfig.instance().delKey(chooseKeySetCombo.getSelectedItem(), table.getValueAt(row, 1).toString());
					//重绘表格
					tableModel.setModelData(KeySetConfig.instance().getKeyMap(chooseKeySetCombo.getSelectedItem()));
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "请选择要删除的改键", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		else if (srcObject == delKeySetButton) {
			String selectedtItem = String.valueOf(chooseKeySetCombo.getSelectedItem());
			//确定是否删除当前下拉列表的选项
			int result = JOptionPane.showConfirmDialog(this, "确定删除改键方案 【"+selectedtItem+"】 ?","提示",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION){ 
				KeySetConfig.instance().delKeySet(chooseKeySetCombo.getSelectedItem());
				chooseKeySetCombo.removeItem(chooseKeySetCombo.getSelectedItem());
			}
		}
		else if (srcObject == startButton) {
			//正在监听，取消监听
			if (isListening) {
				startButton.setIcon(new ImageIcon(R.POWER_OFF_PIC_PATH));
				startButton.setText("Start");
				isListening = false;
				JIntellitypeHelper.instance(this).unregisteAll();
			}
			//开始监听
			else {
				startButton.setIcon(new ImageIcon(R.POWER_ON_PIC_PATH));
				startButton.setText("Stop");
				isListening = true;
				JIntellitypeHelper.instance(this).registe(KeySetConfig.instance().getKeyMap(chooseKeySetCombo.getSelectedItem()));
			}
		}
		else if (srcObject == chooseKeySetCombo) {
			chooseKeySetCombo.setToolTipText(String.valueOf(chooseKeySetCombo.getSelectedItem()));
			tableModel.setModelData(KeySetConfig.instance().getKeyMap(chooseKeySetCombo.getSelectedItem()));
			//如果正在运行，更换监听
			if (isListening) {
				JIntellitypeHelper.instance(this).unregisteAll();
				JIntellitypeHelper.instance(this).registe(KeySetConfig.instance().getKeyMap(chooseKeySetCombo.getSelectedItem()));
			}
		}
	}

	public synchronized boolean isListening() {
		return isListening;
	}
	public void setListening(boolean isListening) {
		this.isListening = isListening;
	}
	public JButton getStartButton() {
		return startButton;
	}
	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public KeyTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(KeyTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public JComboBox getChooseKeySetCombo() {
		return chooseKeySetCombo;
	}

	public void setChooseKeySetCombo(JComboBox chooseKeySetCombo) {
		this.chooseKeySetCombo = chooseKeySetCombo;
	}
	
}
