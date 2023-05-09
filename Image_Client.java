package com.socket;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Image_Client {

	public static void main(String[] args) throws IOException {
		
		//Create a new socket that connects to the server on localhost and port 5019
		Socket s = new Socket("localhost",5019);
		
		// write the image data to the server
		OutputStream outputStream = s.getOutputStream();
		
		//writes bytes to a stream after processing them in a buffer, which can improve the performance of writing to the underlying stream
		//The main advantage of using a BufferedOutputStream is that it reduces the number of times that data is actually written to the underlying stream. 
		//Instead of writing data one byte at a time, which can be very slow, BufferedOutputStream writes data in batches, which can be much faster.
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		System.out.println("Connected to server");
		
		//create graphical user interfaces (GUIs) and provides a standard set of features for displaying and managing components such as buttons, labels, and text fields
		//sets its title to "Client"
		JFrame jFrame = new JFrame("Client");
		
		//sets width size to 300 and height size to 400
		jFrame.setSize(300, 400);
		
		//it is used to close the jframe
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//it is used to create an image-based icon for use in a GUI application from an image file located at "C:\Users\Darshan V\Downloads\anjaneya.jpg"
		ImageIcon icon = new ImageIcon("C:\\Users\\Darshan V\\Downloads\\anjaneya.jpg");
		
		//resizes the ImageIcon to fit the size of the JFrame window. It first retrieves the Image object from the original ImageIcon using the getImage() method, 
		//then resizes it using the getScaledInstance() method
		ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(jFrame.getWidth(), jFrame.getHeight(), Image.SCALE_SMOOTH));
		
		//creates a new label object to display the image, with the scaledIcon object as its image source
		JLabel jLabel = new JLabel(scaledIcon);
		
		//creates a new JButton with the label "Send image to the server......"
		JButton button = new JButton("Send image to the server......");
		
		//it is added to the JFrame using the BorderLayout.CENTER layout constraint, which positions the label at the bottom of the frame.
		jFrame.add(jLabel, BorderLayout.CENTER);
		
		//it is added to the JFrame using the BorderLayout.SOUTH layout constraint, which positions the label at the bottom of the frame.
		jFrame.add(button, BorderLayout.SOUTH);
		
		//which makes it appear on the screen for the user.	
		jFrame.setVisible(true);
			
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					//retrieves the Image object associated with the ImageIcon "scaledIcon".
					Image image = scaledIcon.getImage();
					
					// creates a new BufferedImage object with the dimensions of the specified Image object, which is the scaledIcon in this case. 
					//It sets the type of the BufferedImage to TYPE_INT_RGB, which means it uses an integer pixel representation with red, green, and blue color components.
					BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),BufferedImage.TYPE_INT_RGB);
					
					//creates a graphics context for a buffered image and then draws the image on that graphics context starting at the top-left corner of the image (0,0) with no image observer. 
					//The drawImage() method is used to render the image.
					bufferedImage.getGraphics().drawImage(image, 0, 0, null);
					
					//writes the image data to the bufferedOutputStream in PNG format. 
					ImageIO.write(bufferedImage,"png", bufferedOutputStream);
				}
				catch (IOException event){
					event.printStackTrace();
				}	
			}
		});
	}
}
