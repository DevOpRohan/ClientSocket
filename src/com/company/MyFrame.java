package com.company;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener {

    JButton execBtn = new JButton("Execute");
    JButton browseBtn = new JButton("Browse");
    JTextField ipTxt = new JTextField(15);
    JTextField portTxt = new JTextField(5);
    JTextArea code = new JTextArea(35, 70);
    JTextArea output = new JTextArea(35, 70);

    public MyFrame() {
        setTitle("My C Compiler.....");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponets();
    }

    private void initComponets() {
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("IP:"));
        topPanel.add(ipTxt);
        topPanel.add(new JLabel("Port:"));
        topPanel.add(portTxt);
        execBtn.addActionListener(this);
        topPanel.add(execBtn);
        browseBtn.addActionListener(this);
        topPanel.add(browseBtn);
        add(topPanel, BorderLayout.NORTH);
        code.setBorder(BorderFactory.createEtchedBorder());
        code.setText("//Write your C program here");
        output.setBorder(BorderFactory.createEtchedBorder());
        output.setText("Output of your C program \n");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(code);
        bottomPanel.add(output);
        add(bottomPanel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == execBtn) {
            //Code Area
            String Code = code.getText();

            //Get Ip Address
            String ip = ipTxt.getText();

            //Get Port
            int port = Integer.parseInt(portTxt.getText());
            Client client = new Client(ip, port, Code);

            String result = String.valueOf(client.output);

            //Output Area
            output.setText(result);
        }

        if (e.getSource() == browseBtn) {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();

            //if file is of .c extension then write it in the CodeArea
            if (f.getName().endsWith(".c")) {
                //Read the file
                //And write it in code area
                try {
                    BufferedReader br = new BufferedReader(new FileReader(f));
                    String str = "";
                    while ((str = br.readLine()) != null) {
                        code.append(str + "\n");
                    }
                    br.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                //Display error message in pop up
                JOptionPane.showMessageDialog(null, "Please select a .c file");
            }
        }
    }
}

