
public class Main{
	
	//precondition:
	//postcondition: Initializes user interface  
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){new Window();}
		});
	}
}
