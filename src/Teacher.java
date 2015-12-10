
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class Teacher {
	static DefaultListModel playerList;
	public static boolean readyCheck;
	public static int day;
	private JFrame frame;

	public Teacher(){
		//teacherInterface.java dakiler
		playerList = new DefaultListModel();
		day = 1;
		frame = new JFrame();
		frame.setBounds(100, 100, 460, 370);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblCoffeeShopSimulation = new JLabel("Coffee Shop Simulation Management Console");
		lblCoffeeShopSimulation.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCoffeeShopSimulation.setBounds(14, 11, 420, 20);
		frame.getContentPane().add(lblCoffeeShopSimulation);

		JList connectedPlayersList = new JList(playerList);
		connectedPlayersList.setBounds(34, 76, 171, 208);
		frame.getContentPane().add(connectedPlayersList);

		JLabel lblConnectedPlayers = new JLabel("Connected Players");
		lblConnectedPlayers.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConnectedPlayers.setBounds(34, 51, 120, 20);
		frame.getContentPane().add(lblConnectedPlayers);

		JButton runButton = new JButton("Run Simulation");
		runButton.setBounds(164, 295, 119, 23);
		frame.getContentPane().add(runButton);

		JList playerDataList = new JList();
		playerDataList.setBounds(225, 76, 181, 208);
		frame.getContentPane().add(playerDataList);

		JLabel lblDataStatus = new JLabel("Data Status");
		lblDataStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataStatus.setBounds(223, 54, 76, 14);
		frame.getContentPane().add(lblDataStatus);

		JLabel lblDay = new JLabel("Day:");
		lblDay.setForeground(Color.RED);
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDay.setBounds(360, 41, 46, 14);
		frame.getContentPane().add(lblDay);

		JLabel dayLabel = new JLabel("1");
		dayLabel.setBounds(409, 41, 46, 14);
		frame.getContentPane().add(dayLabel);

		frame.setVisible(true);

		runButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Engine.sendCustomersToShops();

				Engine.readyCheck = true;
				Teacher.readyCheck = true;

				day++;
				
				dayLabel.setText(""+day);

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Engine.readyCheck = false;
				Teacher.readyCheck = false;
			
				Engine.clearPlaylist();
			}
		});

	}


}
