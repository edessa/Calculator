import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * Handles user interface
 */

@SuppressWarnings("serial")									//Auto-generated
public class Window extends JFrame implements ActionListener, WindowListener{
	
	//FIELDS-------------------------------------------------------------
	
	private String equation = "";
	
	private Stack<String> entries = new Stack<String>(10);
	
	private Screen canvas;
	
	private JPanel main_panel = new JPanel();
	
	private JPanel screen_panel = new JPanel();
	private JPanel limits_panel = new JPanel();
	private JPanel number_panel = new JPanel(new GridLayout(7, 4));
	
	private JMenuBar menu_bar = new JMenuBar();
	
	//File menu setup
	private JMenu file_menu = new JMenu("File");
	private JMenuItem save_item =  new JMenuItem("Save");
	private JMenuItem exit_item =  new JMenuItem("Exit");
	
	//View menu setup
	private JMenu view_menu = new JMenu("View");
	private JMenuItem size_item =  new JMenuItem("Scale");
	
	//Help menu setup
	private JMenu help_menu = new JMenu("Help");
	private JMenuItem info_item =  new JMenuItem("About");
	
	private JLabel x_label = new JLabel("x");
	private JLabel y_label = new JLabel("y");
	
	private JTextField equation_line = new JTextField("y = ");
	
	//Screen size spinners
	private JTextField x_limit = new JTextField();
	private JTextField y_limit = new JTextField();

	
	//First number_panel row, left-right
	private JButton left_button = new JButton("(");
	private JButton right_button = new JButton(")");
	private JButton clear_button = new JButton("Clear");
	private	JButton last_button = new JButton("Last");
	
	//Second number_panel row, left-right
	private JButton sin_button = new JButton(Equation.SIN);
	private JButton cos_button = new JButton(Equation.COS);
	private JButton tan_button = new JButton(Equation.TAN);
	private JButton pwr_button = new JButton(Equation.PWR);

	//Third number_panel row, left-right
	private JButton sqrt_button = new JButton(Equation.SQRT);
	private JButton log_button = new JButton(Equation.LOG);
	private JButton ln_button = new JButton(Equation.LN);
	private JButton div_button = new JButton("/");
	
	//Fourth number_panel row, left-right
	private JButton n7_button = new JButton("7");
	private JButton n8_button = new JButton("8");
	private JButton n9_button = new JButton("9");
	private JButton mul_button = new JButton("*");
	
	//Fifth number_panel row, left-right
	private JButton n4_button = new JButton("4");
	private JButton n5_button = new JButton("5");
	private JButton n6_button = new JButton("6");
	private JButton sub_button = new JButton("-");

	//Sixth number_panel row, left-right
	private JButton n1_button = new JButton("1");
	private JButton n2_button = new JButton("2");
	private JButton n3_button = new JButton("3");
	private JButton add_button = new JButton("+");
	
	//Seventh number_panel row, left-right
	private JButton n0_button = new JButton("0");
	private JButton dec_button = new JButton(".");
	private JButton x_button = new JButton("x");
	private JButton equal_button = new JButton("=");
	
	//CONSTRUCTORS-------------------------------------------------------
	
	public Window(){
		super("Graphing Calculator");
		setSize(400, 525);
		setResizable(false);
		setJMenuBar(menu_bar);
		
		//Ensure menu visibility
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		
		//Initialize menu bar setup
		menu_bar.add(file_menu);
		menu_bar.add(view_menu);
		menu_bar.add(help_menu);
		
		//Initialize file menu setup
		file_menu.add(save_item);
		file_menu.addSeparator();
		file_menu.add(exit_item);
		exit_item.addActionListener(this);
		
		//Initialize view menu setup
		view_menu.add(size_item);
		size_item.addActionListener(this);
		
		//Initialize help menu setup
		help_menu.add(info_item);
		info_item.addActionListener(this);

		equation_line.setEditable(false);
		equation_line.setHorizontalAlignment(JTextField.CENTER);
		equation_line.setMaximumSize(new Dimension(400, 100));
		
		operatorsEnabled(false);
		
		screen_panel.add(x_label);
		
		limits_panel.setLayout(new BoxLayout(limits_panel, BoxLayout.Y_AXIS));
		
		main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
		
		main_panel.add(y_label);
		main_panel.add(screen_panel);
		main_panel.add(equation_line);
		main_panel.add(number_panel);
		
		add(main_panel);
		addWindowListener(this);
		
		limits_panel.add(x_limit);
		limits_panel.add(y_limit);
		
		//Initialize first number_panel row, left-right
		number_panel.add(left_button);
			left_button.addActionListener(this);
		number_panel.add(right_button);
			right_button.addActionListener(this);
			right_button.setEnabled(false);
		number_panel.add(clear_button);
			clear_button.addActionListener(this);
		number_panel.add(last_button);
			last_button.addActionListener(this);
			last_button.setEnabled(false);

		//Initialize second number_panel row, left-right
		number_panel.add(sin_button);
			sin_button.addActionListener(this);
		number_panel.add(cos_button);
			cos_button.addActionListener(this);
		number_panel.add(tan_button);
			tan_button.addActionListener(this);
		number_panel.add(pwr_button);
			pwr_button.addActionListener(this);

		//Initialize third number_panel row, left-right
		number_panel.add(sqrt_button);
			sqrt_button.addActionListener(this);
		number_panel.add(log_button);
			log_button.addActionListener(this);
		number_panel.add(ln_button);
			ln_button.addActionListener(this);
		number_panel.add(div_button);
			div_button.addActionListener(this);

		//Initialize fourth number_panel row, left-right
		number_panel.add(n7_button);
			n7_button.addActionListener(this);
		number_panel.add(n8_button);
			n8_button.addActionListener(this);
		number_panel.add(n9_button);
			n9_button.addActionListener(this);
		number_panel.add(mul_button);
			mul_button.addActionListener(this);

		//Initialize fifth number_panel row, left-right
		number_panel.add(n4_button);
			n4_button.addActionListener(this);
		number_panel.add(n5_button);
			n5_button.addActionListener(this);
		number_panel.add(n6_button);
			n6_button.addActionListener(this);
		number_panel.add(sub_button);
			sub_button.addActionListener(this);

		//Initialize sixth number_panel row, left-right
		number_panel.add(n1_button);
			n1_button.addActionListener(this);
		number_panel.add(n2_button);
			n2_button.addActionListener(this);
		number_panel.add(n3_button);
			n3_button.addActionListener(this);
		number_panel.add(add_button);
			add_button.addActionListener(this);

		//Initialize seventh number_panel row, left-right
		number_panel.add(n0_button);
			n0_button.addActionListener(this);
		number_panel.add(dec_button);
			dec_button.addActionListener(this);
		number_panel.add(x_button);
			x_button.addActionListener(this);
		number_panel.add(equal_button);
			equal_button.addActionListener(this);

		//Data collection
		collectData();
		
		//Open license if applicable
		loadLicense();
		
		//Open window
		setVisible(true);
	}

	//METHODS------------------------------------------------------------

	//precondition: ActionEvent e is thrown by some object
	//postcondition: Corresponding action or method is called upon
	public void actionPerformed(ActionEvent e){
		Object s = e.getSource();
		
		//Exit menu item action
		if(s == exit_item){
			saveData(true);
		}
		
		//View menu item action
		else if(s == size_item){
			resizeScreen();
		}
		
		//Info menu item action
		else if(s == info_item){
			loadInfo();
		}
		
		//Left parenthesis button action
		else if(s == left_button){
			equation += "(";
			constantsEnabled(true);
			numbersEnabled(true);
			operatorsEnabled(false);
			left_button.setEnabled(false);
			right_button.setEnabled(true);
		}
		
		//Right parenthesis button action
		else if(s == right_button){
			equation += ")";
			constantsEnabled(false);
			numbersEnabled(false);
			operatorsEnabled(true);
			left_button.setEnabled(true);
			right_button.setEnabled(false);
			
			pwr_button.setEnabled(false);
		}
		
		//Clear button action
		else if(s == clear_button){
			equation = "";
			constantsEnabled(true);
			numbersEnabled(true);
			operatorsEnabled(false);
			left_button.setEnabled(true);
			right_button.setEnabled(false);
			
			canvas.getGraphics().clearRect(0, 0, 400, 200);
			canvas.paint(canvas.getGraphics());
		}
		
		//Last entry button action
		else if(s == last_button){
			constantsEnabled(false);
			numbersEnabled(false);
			operatorsEnabled(false);
			left_button.setEnabled(false);
			right_button.setEnabled(false);
			
			canvas.getGraphics().clearRect(0, 0, 400, 200);
			canvas.paint(canvas.getGraphics());

			equation = entries.pop();
			calculateGraph();
			
			if(entries.isEmpty()){last_button.setEnabled(false);}
		}
		
		//SIN button action
		else if(s == sin_button){
			equation += Equation.SIN;
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(false);
		}
		
		//COS button action
		else if(s == cos_button){
			equation += Equation.COS;
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(false);
		}
		
		//TAN button action
		else if(s == tan_button){
			equation += Equation.TAN;
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(false);
		}
		
		// ^ button action
		else if(s == pwr_button){
			equation += Equation.PWR;
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(false);
		}
		
		//SQRT button action
		else if(s == sqrt_button){
			equation += Equation.SQRT;
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(false);
		}
		
		//LOG button action
		else if(s == log_button){
			equation += Equation.LOG;
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(false);
		}

		//LN action button
		else if(s == ln_button){
			equation += Equation.LN;
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(false);
		}

		// / button action
		else if(s == div_button){
			equation += " / ";
			constantsEnabled(true);
			numbersEnabled(true);
			operatorsEnabled(false);
		}

		//7 button action
		else if(s == n7_button){
			equation += "7";
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(true);
			
			x_button.setEnabled(false);
		}

		//8 button action
		else if(s == n8_button){
			equation += "8";
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(true);
			
			x_button.setEnabled(false);
		}
		
		//9 button action
		else if(s == n9_button){
			equation += "9";
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(true);
			
			x_button.setEnabled(false);
		}

		// * button action
		else if(s == mul_button){
			equation += " * ";
			constantsEnabled(true);
			numbersEnabled(true);
			operatorsEnabled(false);
		}
		
		//4 button action
		else if(s == n4_button){
			equation += "4";
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(true);
			
			x_button.setEnabled(false);
		}

		//5 button action
		else if(s == n5_button){
			equation += "5";
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(true);
			
			x_button.setEnabled(false);
		}
		
		//6 button action
		else if(s == n6_button){
			equation += "6";
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(true);
			
			x_button.setEnabled(false);
		}
		
		// - button action
		else if(s == sub_button){
			equation += " - ";
			constantsEnabled(true);
			numbersEnabled(true);
			operatorsEnabled(false);
		}
		
		//1 button action
		else if(s == n1_button){
			equation += "1";
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(true);
			
			x_button.setEnabled(false);
		}
		
		//2 button action
		else if(s == n2_button){
			equation += "2";
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(true);
			
			x_button.setEnabled(false);
		}

		//3 button action
		else if(s == n3_button){
			equation += "3";
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(true);
			
			x_button.setEnabled(false);
		}

		// + button action
		else if(s == add_button){
			equation += " + ";
			constantsEnabled(true);
			numbersEnabled(true);
			operatorsEnabled(false);
		}
		
		//0 button action
		else if(s == n0_button){
			equation += "0";
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(true);
			
			x_button.setEnabled(false);
		}
		
		// . button action
		else if(s == dec_button){
			equation += ".";
			constantsEnabled(false);
			numbersEnabled(true);
			operatorsEnabled(false);
			
			x_button.setEnabled(false);
		}
		
		//x button action
		else if(s == x_button){
			equation += "x";
			constantsEnabled(false);
			numbersEnabled(false);
			operatorsEnabled(true);
		}
		
		// = button action
		else if(s == equal_button){
			constantsEnabled(false);
			numbersEnabled(false);
			operatorsEnabled(false);
			left_button.setEnabled(false);
			right_button.setEnabled(false);
			
			entries.shove(equation);
			calculateGraph();
		}
		
		//Reset the text field
		equation_line.setText("y = " + equation);
	}
	
	//precondition:
	//postcondition: Submits to Equation to evaluate solutions
	private void calculateGraph(){
		try{
			canvas.drawGraph(equation);
			canvas.update(canvas.getGraphics());
			
			if(entries.size() > 1){last_button.setEnabled(true);}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Equation is not valid!", "Error", JOptionPane.ERROR_MESSAGE);
			entries.pop();
		}
	}
	
	//precondition:
	//postcondition: Loads last screen dimensions
	private void collectData(){

		//Attempt to load screen data
		try{
			Scanner s = new Scanner(new File("data" + File.separator + "dim.bin"));
			
			double[] x_values = {Double.parseDouble(s.nextLine()), 
					Double.parseDouble(s.nextLine())};
			double[] y_values = {Double.parseDouble(s.nextLine()), 
					Double.parseDouble(s.nextLine())};
			canvas = new Screen(370, 200, x_values, y_values);
			
			s.close();
			
			x_limit.setText(Double.toString(x_values[1]));
			y_limit.setText(Double.toString(y_values[1]));
		}
		
		//If attempt fails, load default values
		catch(FileNotFoundException f){
			canvas = new Screen(370, 200, new double[]{-10, 10}, new double[]{-10, 10});
			
			x_limit.setText(Double.toString(10.0));
			y_limit.setText(Double.toString(10.0));
		}
		catch(Exception e){}
		
		screen_panel.add(canvas);
	}
		
	//precondition:
	//postcondition: Constant buttons disabled
	private void constantsEnabled(boolean b){
		sin_button.setEnabled(b);
		cos_button.setEnabled(b);
		tan_button.setEnabled(b);
		sqrt_button.setEnabled(b);
		log_button.setEnabled(b);
		ln_button.setEnabled(b);
	}
	
	//precondition:
	//postcondition: Opens dialog of program information
	private void loadInfo(){
		JTextArea t = new JTextArea("Author: Eric Nguyen\n" +
	    		"Version: 1.1\n" +
	    		"License: Proprietary\nSystem: " + 
	    		System.getProperty("os.name") + " " + 
	    		System.getProperty("os.version") + "\n" +
	    		"\u00a92009-2013. All rights reserved.", 5, 15);
	    t.setEditable(false);
	    
        JOptionPane.showMessageDialog(this, t, "About", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//precondition:
	//postcondition: Attempts to load license, determines output for license
	private void loadLicense(){
		File f = new File("data" + File.separator + "lic.bin");
		
		//If the license file exists
		if(f.exists()){
			try{
				Scanner s = new Scanner(f);
				String c = s.next();
				s.close();
				
				//If the user has replaced the file
				if(!c.equals("ILWMTIT")){
					JOptionPane.showMessageDialog(this, "This license is invalid.",	
							"License Error", JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
			}
			catch(Exception e){}
		}
		
		//If the file does not exits
		else{
			
			//Setup the EULA text
			JTextArea text = new JTextArea(15, 25);
			text.setEditable(false);
			text.setLineWrap(true);
			text.setWrapStyleWord(true);
			
			//Open dialog
			String[] o = {"Accept", "Deny"};
			int i = JOptionPane.showOptionDialog(this, new JScrollPane(text), 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, o, o[1]);
			
			//If the user clicks ACCEPT
			if(i == JOptionPane.YES_OPTION){
				try{
					PrintStream p = new PrintStream(f);
					p.print("ILWMTIT");
					p.close();
				}
				catch(Exception e){}
			}
			//If the user clicks DENY or exits
			else{
				System.exit(0);
			}
		}
	}
	
	//precondition:
	//postcondition: Number buttons disabled
	private void numbersEnabled(boolean b){
		x_button.setEnabled(b);
		dec_button.setEnabled(b);
		n0_button.setEnabled(b);
		n1_button.setEnabled(b);
		n2_button.setEnabled(b);
		n3_button.setEnabled(b);
		n4_button.setEnabled(b);
		n5_button.setEnabled(b);
		n6_button.setEnabled(b);
		n7_button.setEnabled(b);
		n8_button.setEnabled(b);
		n9_button.setEnabled(b);
	}
	
	//precondition:
	//postcondition: Operator buttons disabled
	private void operatorsEnabled(boolean b){
		pwr_button.setEnabled(b);
		div_button.setEnabled(b);
		mul_button.setEnabled(b);
		sub_button.setEnabled(b);
		add_button.setEnabled(b);
	}
	
	//precondition: 
	//postcondition: Exports the stack and dimensions data, then exits
	private void saveData(boolean x){
		
		//Save the stack data
		try{
			PrintStream s = new PrintStream(new File("data" + File.separator + "dat.bin"));
			while(!entries.isEmpty()){
				s.println(entries.pop());
			}
			s.close();
		}
		catch(Exception e){}
		
		if(x){
			System.exit(0);
		}
	}
	
	//precondition:
	//postcondition:
	private void resizeScreen(){
		int i = JOptionPane.showConfirmDialog(this, limits_panel, "Resolution", JOptionPane.OK_CANCEL_OPTION);
		try{
			
			//If the user enacts changes and clicks OK
			if(i == JOptionPane.OK_OPTION){
				canvas.setDomain(new double[]{-Double.parseDouble(x_limit.getText()), 
						Double.parseDouble(x_limit.getText())});
				canvas.setRange(new double[]{-Double.parseDouble(y_limit.getText()), 
						Double.parseDouble(y_limit.getText())});
				
				canvas.getGraphics().clearRect(0, 0, 400, 200);
				canvas.paint(canvas.getGraphics());
			}
			
			//If the user closes the dialog or clicks CANCEL
			else{
				x_limit.setText(Double.toString(canvas.getDomain()[1]));
				y_limit.setText(Double.toString(canvas.getRange()[1]));
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Only numbers from 0 to 1000!", "Error", JOptionPane.ERROR_MESSAGE);
			resizeScreen();
		}
		if(equation != ""){calculateGraph();}
	}
	
	//precondition:
	//postcondition:
	public void windowActivated(WindowEvent arg0){}
	
	//precondition:
	//postcondition:
	public void windowClosed(WindowEvent arg0){}
	
	//precondition:	Interface is closing due to user action
	//postcondition: Calls the saveData method
	public void windowClosing(WindowEvent arg0){
		saveData(true);
	}
	
	//precondition:
	//postcondition:
	public void windowDeactivated(WindowEvent arg0){}

	//precondition:
	//postcondition: Re-enables double buffering
	public void windowDeiconified(WindowEvent arg0){
		main_panel.setDoubleBuffered(true);
		number_panel.setDoubleBuffered(true);
	}
	
	//precondition:
	//postcondition: Disables double buffering while minimized 
	public void windowIconified(WindowEvent arg0){
		main_panel.setDoubleBuffered(false);
		number_panel.setDoubleBuffered(false);
	}
	
	//precondition:
	//postcondition:
	public void windowOpened(WindowEvent arg0){}
}
