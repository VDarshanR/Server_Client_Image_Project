 package com.socket;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Image_Server {

	public static void main(String[] args) throws IOException {
		
		//create graphical user interfaces (GUIs) and provides a standard set of features for displaying and managing components such as buttons, labels, and text fields
		//sets its title to "Server"
		JFrame jFrame = new JFrame("Server");
		
		//sets width size to 300 and height size to 400
		jFrame.setSize(300, 400);
		
		//it is used to close the jframe
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//it is created with the text "Waiting for image from client"
		JLabel jLabel1 = new JLabel("Waiting for image from client");
		
		//it is added to the JFrame using the BorderLayout.SOUTH layout constraint, which positions the label at the bottom of the frame.
		jFrame.add(jLabel1, BorderLayout.SOUTH);
		
		//which makes it appear on the screen for the user.
		jFrame.setVisible(true);
		
		//ServerSocket is created on port 5019 and waiting for an incoming connection from a client
		ServerSocket serverSocket = new ServerSocket(5019);
		
		//accepts a connection from a client on the server socket. It blocks the execution of the code until a client connects to the server socket
		//once a client connects, it returns a new Socket object that represents the connection to the client
		//this new Socket object can be used to communicate with the client over the established connection
		Socket s = serverSocket.accept();
		
		// read the image data sent by the client.
		InputStream inputStream = s.getInputStream();
		
		//buffer the image data, which can improve performance by reducing the number of times the underlying input stream is accessed
		BufferedInputStream  bufferedInputStream = new  BufferedInputStream(inputStream);
		
		//used to buffer the image data, which can improve performance by reducing the number of times the underlying input stream is accessed
		//The ImageIO.read() method reads the image data from the input stream and decodes it into a BufferedImage format that can be processed and displayed in Java.
		BufferedImage bufferedImage = ImageIO.read(bufferedInputStream);
		System.out.println("Read buffered image..." + bufferedImage.getHeight());
		
		//close the bufferedInputStream and Socket
		bufferedInputStream.close();
		s.close();
		
		//After reading the image sent from the client, the server creates a JLabel called label 
		//that contains an ImageIcon created from the bufferedImage
		JLabel label = new JLabel(new ImageIcon(bufferedImage));
		
		//jLabel1 is updated with below text after image received from the client to the server
		jLabel1.setText("Image Received");
		
		//it is added to the JFrame using the BorderLayout.CENTER layout constraint, which positions the label at the bottom of the frame.
		jFrame.add(label,BorderLayout.CENTER);
	}
}
