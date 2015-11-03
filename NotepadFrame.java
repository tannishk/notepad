/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Tannishk
 */
package notepad;

/*
 * Create Menu for Notepad
 */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;

public class NotepadFrame extends Frame{
	String msg = " ";
	CheckboxMenuItem wordWrap, statusBar;
	JTextArea text;
	
	NotepadFrame(String title){
		super(title);
		MenuBar mbar = new MenuBar();
		setMenuBar(mbar);
		
		//File Menu
		Menu file = new Menu("File");
		MenuItem item1, item2, item3, item4, item5, item6, item7, item8, item9;
		
		file.add(item1 = new MenuItem("New"));
		file.add(item2 = new MenuItem("Open..."));
		file.add(item4 = new MenuItem("Save As..."));
		file.add(item8 = new MenuItem("-"));
		file.add(item9 = new MenuItem("Exit"));
		
		mbar.add(file);
		
		//Edit Menu
		Menu edit = new Menu("Edit");
		MenuItem item10, item11, item12, item13, item14,
			item15, item16, item17, item18, item19, item20,
			item21, item22, item23;
		
		edit.add(item10 = new MenuItem("Undo"));
		edit.add(item11 = new MenuItem("-"));
		edit.add(item12 = new MenuItem("Cut"));
		edit.add(item13 = new MenuItem("Copy"));
		edit.add(item14 = new MenuItem("Paste"));
		edit.add(item15 = new MenuItem("Delete"));
		edit.add(item16 = new MenuItem("-"));
		edit.add(item20 = new MenuItem("Go To..."));
		edit.add(item21 = new MenuItem("-"));
		edit.add(item22 = new MenuItem("Select All"));
		edit.add(item23 = new MenuItem("Time/Date"));
		
		
		mbar.add(edit);
		
		//Format Menu		
		Menu format = new Menu("Format");
		MenuItem item24;
		
		wordWrap = new CheckboxMenuItem("Word Wrap");
		format.add(wordWrap);
		
		format.add(item24 = new MenuItem("Font..."));
		
		mbar.add(format);
		
		//View Menu
		Menu view = new Menu("View");
		
		statusBar = new CheckboxMenuItem("Status Bar");
		view.add(statusBar);
		
		mbar.add(view);
		
		//Help
		Menu help = new Menu("Help");
		MenuItem item25, item26, item27;
		
		help.add(item25 = new MenuItem("View Help"));
		help.add(item26 = new MenuItem("-"));
		help.add(item27 = new MenuItem("About Notepad"));
		
		mbar.add(help);
		
		
		text = new JTextArea();
		add(text);
		
		MyMenuHandler handler = new MyMenuHandler(this);
		
		item1.addActionListener(handler);
		item2.addActionListener(handler);
		item4.addActionListener(handler);
		item8.addActionListener(handler);
		item9.addActionListener(handler);
		item10.addActionListener(handler);
		item11.addActionListener(handler);
		item12.addActionListener(handler);
		item13.addActionListener(handler);
		item14.addActionListener(handler);
		item15.addActionListener(handler);
		item16.addActionListener(handler);
		item20.addActionListener(handler);
		item21.addActionListener(handler);
		item22.addActionListener(handler);
		item23.addActionListener(handler);
		item24.addActionListener(handler);
		item25.addActionListener(handler);
		item26.addActionListener(handler);
		item27.addActionListener(handler);
		
		wordWrap.addItemListener(handler);
		statusBar.addItemListener(handler);
		
		MyWindowAdapter adapter = new MyWindowAdapter(this);
		addWindowListener(adapter);
	}

}

class MyWindowAdapter extends WindowAdapter{
	NotepadFrame notepadFrame;
	
	public MyWindowAdapter(NotepadFrame notepadFrame){
		this.notepadFrame = notepadFrame;
	}
	
	public void windowClosing(WindowEvent we){
		notepadFrame.setVisible(false);
	}
}

class MyMenuHandler implements ActionListener, ItemListener{
	NotepadFrame notepadFrame;
	
	public MyMenuHandler(NotepadFrame notepadFrame){
		this.notepadFrame = notepadFrame;
	}

	public void actionPerformed(ActionEvent ae){
		String msg = "You selected ";
		String arg = (String)ae.getActionCommand();
		if(arg.equals("New")){
			notepadFrame.text.setText(" ");
		}
		else if(arg.equals("Open...")){
			actOpen();
		}
		else if(arg.equals("Save As...")){
			actSave();
		}
		else if(arg.equals("Exit")){
			
			//Exit
			notepadFrame.dispose();
		}
		else if(arg.equals("Undo"))
			msg += "Undo";
		else if(arg.equals("Cut"))
			msg += "Cut";
		else if(arg.equals("Copy"))
			msg += "Copy";
		else if(arg.equals("Paste"))
			msg += "Paste";
		else if(arg.equals("Delete"))
			msg += "Delete";
		else if(arg.equals("Go To..."))
			msg += "Go To...";
		else if(arg.equals("Select All"))
			msg += "Select All";
		else if(arg.equals("Time/Date"))
			msg += "Time/Date";
		else if(arg.equals("Word Wrap"))
			msg += "Word Wrap";
		else if(arg.equals("Font"))
			msg += "Font";
		else if(arg.equals("Status Bar"))
			msg += "Status Bar";
		else if(arg.equals("View Help"))
			msg += "View Help";
		else if(arg.equals("About Notepad")){
			
		}
		
		
	}


	//Save Text
	private void actSave() {
		JFileChooser save = new JFileChooser();
		
		int option = save.showSaveDialog(notepadFrame);
		
		if(option == JFileChooser.APPROVE_OPTION){
			File fileName = new File(save.getSelectedFile() + ".txt");
			BufferedWriter out = null;
			try{
				out = new BufferedWriter(new FileWriter(fileName));
				notepadFrame.text.write(out);
				
			} catch (IOException e){
				e.printStackTrace();
			}finally{
				if(out != null){
					try{
						out.close();
					}catch(IOException e){
						
					}
				}
			}
		
		}
		
	}

	//Open Text
	private void actOpen() {
		JFileChooser open = new JFileChooser();
		
		int option = open.showOpenDialog(notepadFrame);
		File file = null;
		
		if(option == JFileChooser.APPROVE_OPTION){
			 file = open.getSelectedFile();
		}
			
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			while(line != null){
				notepadFrame.text.append(line + "\n");
				line = in.readLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public void itemStateChanged(ItemEvent ie){
		notepadFrame.repaint();
	}
}
