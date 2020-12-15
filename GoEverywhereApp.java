/*
 * File name: GoEverywhereApp.java
 *
 *Programmer: Michael Labuda
 *ULID: mllabud
 *
 *
 *
 *Class IT 178
 *
 *Lecture Section: 001
 *Lecture Instructor: Matsuda
 */
package edu.ilstu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * @author Michael Labuda
 * programs that runs flight data and returns info
 * 
 */
public class GoEverywhereApp extends JFrame
{
	private Container contents; 
	private JTextField nameText,dateText; 
	private JLabel nameLabel,airportLabel,dateLabel,destinationLabel,title,priceText,flightDets,flightNumber,seat,airportLabel2;
	private JLabel destinationLabel2,time,priceLabel,date2,name2;
	//private JTextField priceText;
	private JButton checkPrice,submit;
	private Panel westPanel,northPanel,centerPanel;
	private JComboBox<String> airportBox,destinationBox,seatBox;
	private JRadioButton economyButton,businessButton;
	private ButtonGroup buttonGroup;
	private String [] airportList = {"Chicago(CHI)","Vancouver(YVR)","Los Angeles(LAX)"};
	private String [] destinationList = {"Chicago(CHI)","Vancouver(YVR)","Los Angeles(LAX)"};
	private String [] economySeats = {"C1","C2","C3","C4","C5","C6","D1","D2","D3","D4","D5","D6"};
	private String [] businessSeats = {"A1","A2","A3","A4","B1","B2","B3","B4"};
	//private String airportSelection,destinationSelection;
	//private String price = "price";
	String airport;
	String destination;
	double price;
	String price2;
	
	AirplaneDeals airplaneDeals = new AirplaneDeals();
	
//	FlightData flightData = airplaneDeals.getTicket("CHI","LAX");
	
	public GoEverywhereApp()
	{
		super("Welcome to GoEverywhere");
		
		contents = getContentPane();
		contents.setLayout(new GridLayout(10,1));
		title = new JLabel("Welcome to GoEverywhere");
		nameLabel = new JLabel("Name");
		airportLabel = new JLabel("Airport");
		dateLabel = new JLabel("Date");
		destinationLabel = new JLabel("Destination");
		
		
		
		nameText = new JTextField(10);
		dateText = new JTextField(10);
		airportBox = new JComboBox<String>(airportList);
		airportBox.setMaximumRowCount(3);
		airportBox.setSelectedIndex(0);
		
		destinationBox = new JComboBox<String>(destinationList);
		destinationBox.setMaximumRowCount(3);
		destinationBox.setSelectedIndex(0);
		
		economyButton = new JRadioButton("Economy",true);
		businessButton = new JRadioButton("Business");
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(economyButton);
		buttonGroup.add(businessButton);
		
		seatBox = new JComboBox<String>(economySeats);
		seatBox.setSelectedIndex(0);
		
		checkPrice = new JButton("Check Price");
		priceText = new JLabel("    ");
		submit = new JButton("Submit");
		flightDets = new JLabel("Flight details: ");
		
		
		westPanel = new Panel();
		northPanel = new Panel();
		centerPanel = new Panel();
		
		
		northPanel.add(title);
		westPanel.add(nameLabel);
		westPanel.add(nameText);
		westPanel.add(airportLabel);
		westPanel.add(airportBox);
		westPanel.add(dateLabel);
		westPanel.add(dateText);
		westPanel.add(destinationLabel);
		westPanel.add(destinationBox);
		westPanel.add(economyButton);
		westPanel.add(businessButton);
		westPanel.add(seatBox);
		westPanel.add(checkPrice);
		westPanel.add(priceText);
		westPanel.add(submit);
		
		

		contents.add(northPanel,BorderLayout.NORTH);
		contents.add(westPanel,BorderLayout.WEST);
		
	
		RadioButtonHandler rbh = new RadioButtonHandler();
		economyButton.addActionListener(rbh);
		businessButton.addActionListener(rbh);
		
		ButtonHandler bh = new ButtonHandler();
		checkPrice.addActionListener(bh);
		
		ButtonHandler2 bh2 = new ButtonHandler2();
		submit.addActionListener(bh2);
		
		
		
		
		priceLabel = new JLabel("Price:$" + priceText);
		
	
		
	
		
		
		
		//contents.add(centerPanel,BorderLayout.CENTER);
		setSize(750,750);
		setVisible(true);
	}
	
	
	
	private class RadioButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) //stackoverflow
		{
			if(economyButton.isSelected())
			{
				seatBox.removeAllItems();
			for(String str : economySeats)
				seatBox.addItem(str);
			}
			else if(businessButton.isSelected())
			{
			seatBox.removeAllItems();
			
			for(String str : businessSeats)
				seatBox.addItem(str);
			}
		}
	}
	
	private class ButtonHandler implements ActionListener
	{
		
		public void actionPerformed(ActionEvent ae)
		{
			
			if(airportBox.getSelectedItem().equals("Chicago(CHI)"))
				airport = "CHI";
			else if(airportBox.getSelectedItem().equals("Vancouver(YVR)"))
				airport = "YVR";
			else if(airportBox.getSelectedItem().equals("Los Angeles(LAX)"))
				airport = "LAX";
			
			if(destinationBox.getSelectedItem().equals("Chicago(CHI)"))
				destination = "CHI";
			else if(destinationBox.getSelectedItem().equals("Vancouver(YVR)"))
				destination = "YVR";
			else if(destinationBox.getSelectedItem().equals("Los Angeles(LAX)"))
				destination = "LAX";
				
			price = airplaneDeals.getTicketPrice(airport, destination);
			price2 = Double.toString(price);
				
				
				priceText.setText("$" + price2);
			
				
				
		}
	}
	private class ButtonHandler2 implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			
			
			
			FlightData flightData = airplaneDeals.getTicket(airport,destination);
			airportLabel2 = new JLabel("Airport: " + airportBox.getSelectedItem().toString() + " to");
			destinationLabel2 = new JLabel(destinationBox.getSelectedItem().toString());
			date2 = new JLabel("Date:" + dateText.getText());
			time = new JLabel("Time" + flightData.getFlightTime());
			name2 = new JLabel(nameText.getText());
			flightNumber = new JLabel("Flight number:" + flightData.getFlightNumber());
			seat = new JLabel ("seat:" + seatBox.getSelectedItem().toString());
			centerPanel.add(flightDets);
			centerPanel.add(name2);
			centerPanel.add(flightNumber);
			centerPanel.add(seat);
			centerPanel.add(airportLabel2);
			centerPanel.add(destinationLabel2);
			centerPanel.add(date2);
			centerPanel.add(time);
			centerPanel.add(priceText);
			contents.add(centerPanel);
			
			
			
			
		}
	}
	public static void main(String[] args)
	{
		GoEverywhereApp app = new GoEverywhereApp();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

	
	
}
