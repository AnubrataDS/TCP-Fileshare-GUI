
import javax.swing.JFileChooser;
import javax.swing.UIManager;

public class FileSelector {
	JFileChooser chooser ;
	public  FileSelector() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		chooser = new JFileChooser();
		//chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Select file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
	
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
			System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
		} else {
			System.out.println("No Selection ");
		}
	}
	public static void main(String args[])
	{
		new FileSelector();
	}
}