import java.awt.EventQueue;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectFile extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<String> addresses , interfaces;
	private JTextField txtNoFileSelected;
	private String sendFile ;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectFile frame = new SelectFile();
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
	public SelectFile(String name) {
		sendFile = name ;
		setTitle("Sending file");
		setResizable(false);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		addresses = new ArrayList<String>();
		interfaces = new ArrayList<String>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		setSize(450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane textPane = new JTextPane();
		JButton btnSelectFile = new JButton("Select file");
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = FileSelector();
				txtNoFileSelected.setText(fileName);
			}
		});
		btnSelectFile.setBounds(10, 11, 89, 23);
		contentPane.add(btnSelectFile);
		
		txtNoFileSelected = new JTextField();
		txtNoFileSelected.setBackground(SystemColor.control);
		txtNoFileSelected.setText(" No file selected");
		txtNoFileSelected.setBounds(109, 12, 325, 20);
		contentPane.add(txtNoFileSelected);
		txtNoFileSelected.setColumns(10);
		
		JLabel lblYourPossibleLocal = new JLabel("Your local IP addresses and their interface names :-");
		lblYourPossibleLocal.setBounds(10, 45, 279, 19);
		contentPane.add(lblYourPossibleLocal);
		
		JButton btnReady = new JButton("Ready");
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(sendFile.equals("null"))
				{
					//new ErrorPopUp("No File Selected");
					JOptionPane.showMessageDialog(null, "Please select a file and then click \"Ready\"", "Error : No file selected", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					dispose();
					new Sender(textPane.getText(),sendFile);
				}
			}
		});
		btnReady.setBounds(299, 45, 135, 32);
		contentPane.add(btnReady);
		
		JTextPane txtpnMakeSureThe = new JTextPane();
		txtpnMakeSureThe.setBackground(SystemColor.control);
		txtpnMakeSureThe.setEditable(false);
		txtpnMakeSureThe.setText("Hit ready after selecting a file to send.\n\nMake sure the receiver enters the correct IP address.\n\nA bit of trial-and-error may be required.");
		txtpnMakeSureThe.setBounds(299, 90, 135, 139);
		contentPane.add(txtpnMakeSureThe);
		
		textPane.setBounds(10, 75, 279, 185);
		contentPane.add(textPane);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FirstWindow();
			}
		});
		btnNewButton.setBounds(299, 237, 135, 23);
		contentPane.add(btnNewButton);
		try {
			for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())) {
				for (InetAddress address : Collections.list(ni.getInetAddresses())) {
					if (address instanceof Inet4Address) {
						addresses.add(address.getHostAddress());
						interfaces.add(ni.getDisplayName());
						textPane.setText(textPane.getText()+"\n"+address.getHostAddress()+" : "+ni.getDisplayName()+"\n");
					}
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setVisible(true);
	}
	public String FileSelector() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JFileChooser chooser = new JFileChooser();
		//chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Select file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
	
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			//System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
			//System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
			sendFile = chooser.getSelectedFile().toString() ;
			return sendFile;
		} else {
			return(" No file selected ");
		}
	}
}
