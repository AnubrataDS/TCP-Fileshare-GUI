import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sender extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFileName;
	private JLabel lblStatus ;
	private JTextPane textPaneLog ;
	private SenderBackEnd senderCode ; 
	private Thread updater;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sender frame = new Sender("","");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sender(String pane,String fileName) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		senderCode = new SenderBackEnd(fileName,this);
		setResizable(false);
		setTitle("Sending file");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 642, 321);
		setSize(640, 320);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFileName = new JLabel("File Name : ");
		lblFileName.setBounds(10, 23, 61, 14);
		contentPane.add(lblFileName);
		
		textFieldFileName = new JTextField(fileName);
		textFieldFileName.setEditable(false);
		textFieldFileName.setBounds(75, 20, 551, 20);
		contentPane.add(textFieldFileName);
		textFieldFileName.setColumns(10);
		
		JLabel lblCurrentStatus = new JLabel("Status :-");
		lblCurrentStatus.setBounds(10, 48, 90, 14);
		contentPane.add(lblCurrentStatus);
		
		textPaneLog = new JTextPane();
		textPaneLog.setEditable(false);
		textPaneLog.setBounds(10, 73, 375, 208);
		contentPane.add(textPaneLog);
		
		lblStatus = new JLabel("Waiting for connection...");
		lblStatus.setBounds(75, 48, 313, 14);
		contentPane.add(lblStatus);
		
		JButton btnCancel = new JButton("Cancel ");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new SelectFile(fileName);
			}
		});
		btnCancel.setBounds(395, 241, 231, 40);
		contentPane.add(btnCancel);
		
		JTextPane textPaneIP = new JTextPane();
		textPaneIP.setBounds(395, 73, 231, 157);
		contentPane.add(textPaneIP);
		textPaneIP.setText(pane);
		
		JLabel lblLocalIpAddresses = new JLabel("Local IP addresses and interface names :-");
		lblLocalIpAddresses.setBounds(395, 51, 231, 14);
		contentPane.add(lblLocalIpAddresses);
		
		setVisible(true);
	}
	
	void setStatus(String str)
	{
		lblStatus.setText(str);
	}
	
	void addLog(String str)
	{
		String msg = textPaneLog.getText()+"\n"+str;
		textPaneLog.setText(msg);
	}
}
