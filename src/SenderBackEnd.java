import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.ServerSocket;
import java.net.Socket;

public class SenderBackEnd {

	private String[] status = { "Waiting for connection...", "Connected , waiting to start transfer", "Transferring ",
			"Transfer complete !" };;
	private int statusIndex;
	private ServerSocket servSock;
	private Socket sock;
	private File sendFile;
	private Thread sendThread;
	private boolean running;
	private float percentage ;
	public SenderBackEnd(String fileName, Sender window) {
		// TODO Auto-generated constructor stub
		running = true;
		statusIndex = 0;
		percentage = 0;
		sendFile = new File(fileName);
		String fname = fileName.substring(fileName.lastIndexOf("\\")+1);
		System.out.println("fname : " + fname);
		String size ;
		if(sendFile.length()<1000)
			size = sendFile.length()+" bytes";
		else if(sendFile.length()<1000000)
			size = (sendFile.length()/1000)+" kb";
		else if(sendFile.length()<1000000000)
			size = (sendFile.length()/1000000)+" mb";
		else 
			size = (sendFile.length()/1000000000)+" gb";
		System.out.println("Size : "+size);
		try {
			servSock = new ServerSocket(8192);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendThread = new Thread("Sending") {
			public void run() {
				while (running) {
					try {
						sock = servSock.accept();
						statusIndex = 1;
						sendInfo("#f" + fname);
						sendInfo("#s" + size);
						statusIndex = 2;
						sendFile();
						statusIndex = 3;

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
	}

	public void sendFile() throws IOException {
		DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
		FileInputStream fis = new FileInputStream(sendFile);
		BufferedInputStream bis = new BufferedInputStream(fis);

		byte[] buffer = new byte[4096];

		while (bis.read(buffer) > 0) {
			dos.write(buffer);
			percentage+=(4096.00/sendFile.length());
		}

		fis.close();
		dos.close();
	}

	public void sendInfo(String info) throws IOException {
		OutputStream os = sock.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(info);
		bw.flush();
		bw.close();
	}

	protected String getStatus() {
		if(statusIndex!=2)
		return status[statusIndex];
		else
		return status[statusIndex]+percentage+"%";
	}
}
