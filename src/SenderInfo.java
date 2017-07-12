import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;

public class SenderInfo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtTryingToConnect;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SenderInfo frame = new SenderInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public SenderInfo() {
		setTitle("Receive a file");
		setResizable(false);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		setSize(450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSelectFolder = new JButton("Select folder");
		btnSelectFolder.setBounds(10, 11, 93, 23);
		contentPane.add(btnSelectFolder);
		
		textField = new JTextField();
		textField.setBackground(SystemColor.control);
		textField.setBounds(108, 12, 326, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblSendersIpAddress = new JLabel("Sender's IP :");
		lblSendersIpAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblSendersIpAddress.setBounds(10, 45, 93, 14);
		contentPane.add(lblSendersIpAddress);
		
		textField_1 = new JTextField();
		textField_1.setBounds(108, 43, 326, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.setBounds(108, 70, 146, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Abort");
		btnCancel.setBounds(264, 70, 170, 23);
		contentPane.add(btnCancel);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 135, 326, 125);
		contentPane.add(textPane);
		
		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setBounds(10, 104, 46, 23);
		contentPane.add(lblStatus);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(345, 237, 89, 23);
		contentPane.add(btnBack);
		
		JTextPane txtpnAbortAndTry = new JTextPane();
		txtpnAbortAndTry.setBackground(SystemColor.control);
		txtpnAbortAndTry.setText("Abort and try another IP if it takes too long to connect");
		txtpnAbortAndTry.setBounds(346, 104, 88, 122);
		contentPane.add(txtpnAbortAndTry);
		
		txtTryingToConnect = new JTextField();
		txtTryingToConnect.setText("Trying to connect ...");
		txtTryingToConnect.setBackground(SystemColor.control);
		txtTryingToConnect.setBounds(55, 104, 281, 20);
		contentPane.add(txtTryingToConnect);
		txtTryingToConnect.setColumns(10);
		setVisible(true);
	}
}
