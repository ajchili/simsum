package com.kirinpatel;

import java.io.File;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import net.sourceforge.tess4j.*;

public class Main extends JFrame {
	
	final static Converter converter = new Converter();
	final JFileChooser fileChooser = new JFileChooser();
	File input, output;
	
	public Main() {
		super("simsum");
		setSize(new Dimension(400, 150));
		setResizable(false);
		setLayout(new GridLayout(3, 1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel inputPanel = new JPanel(new BorderLayout());
		JButton inputButton = new JButton("Input file/folder");
		JTextField inputField = new JTextField();
		JScrollPane inputScroll = new JScrollPane(inputField);
		inputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		inputButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				
				if (fileChooser.showOpenDialog(Main.this) == JFileChooser.APPROVE_OPTION) {
		            input = fileChooser.getSelectedFile();
		            inputField.setText(input.getAbsolutePath());
				}
			}
		});
		inputPanel.add(inputButton, BorderLayout.WEST);
		inputPanel.add(inputScroll, BorderLayout.CENTER);
		add(inputPanel);
		
		JPanel outputPanel = new JPanel(new BorderLayout());
		JButton outputButton = new JButton("Output folder");
		JTextField outputField = new JTextField();
		JScrollPane outputScroll = new JScrollPane(outputField);
		outputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		outputButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				
				if (fileChooser.showOpenDialog(Main.this) == JFileChooser.APPROVE_OPTION) {
		            output = fileChooser.getSelectedFile();
		            outputField.setText(output.getAbsolutePath());
				}
			}
		});
		outputPanel.add(outputButton, BorderLayout.WEST);
		outputPanel.add(outputScroll, BorderLayout.CENTER);
		add(outputPanel);
		
		JPanel optionPanel = new JPanel(new GridLayout(1, 2));
		JButton tessButton = new JButton("Convert file(s)");
		tessButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
	            input = new File(inputField.getText());
	            output = new File(outputField.getText());
	            
				if (input != null) {
					converter.addFolderToQueue(input);
				}
			}
		});
		optionPanel.add(tessButton);
		add(optionPanel);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
    }
	
	static class Converter implements Runnable {
		
		private Thread thread;
		private ArrayList<File> files = new ArrayList<>();
		
		public void addFileToQueue(File file) {
			files.add(file);
			thread = new Thread(this);
			thread.start();
		}
		
		public void addFolderToQueue(File file) {
			if (file.isDirectory()) {
				
			} else {
				addFileToQueue(file);
			}
		}
		
		public void stop() {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			for (File file : files) {
				System.out.println("Converting files...");
		        ITesseract instance = new Tesseract();  // JNA Interface Mapping

		        try {
		            String result = instance.doOCR(file);
		            System.out.println(result);
		        } catch (TesseractException te) {
		            System.err.println(te.getMessage());
		        }
			}
			
			files.clear();
			stop();
		}
	}
}
