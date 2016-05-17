import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import ChartDirector.*;

public class DonutChartDrawer
{
	XYChart c;
	String title = "";

	int identifier;
	int width;
	int height;

	String[] titles = {"Chart of Balances","Chart of Sales"
			,"Chart of Incomes"};

	String[] yAxisTitles = {"Balances","Sales","Incomes"};

	public DonutChartDrawer(int identifier){
		this.identifier=identifier;
		this.width=width;
		this.height=height;
	}

	//Name of demo program
	public String toString() { return "Donut Chart of"+yAxisTitles[identifier]; }

	//Number of charts produced in this demo
	public int getNoOfCharts() { return 1; }

	//Main code for creating charts
	public void createChart(ChartViewer viewer, int w, int h)
	{
		width = w-40;
		height = h-40;
		// The data for the pie chart
		double[] data = new double[Engine.shopMap.size()];

		// The labels for the pie chart
		String[] labels = new String[Engine.shopMap.size()];

		int index = 0;

		for(Shop shop : Engine.shopMap.values()){
			if(identifier==0){
				data[index] = shop.dailySales;
			}else if(identifier==1){
				data[index] = shop.dailySales*shop.recipe.price;
			}else if(identifier==2){
				data[index] = shop.balance;
			}
			labels[index] = shop.name;
			index++;
		}

		// Create a PieChart object of size 600 x 320 pixels. Set background color to brushed
		// silver, with a 2 pixel 3D border. Use rounded corners of 20 pixels radius.
		PieChart c = new PieChart(width, height, Chart.brushedSilverColor(), Chart.Transparent, 2);
		c.setRoundedFrame(0xffffff, 20);

		// Add a title using 18pt Times New Roman Bold Italic font. #Set top/bottom margins to 8
		// pixels.
		TextBox title = c.addTitle("Donut Chart Demonstration", "Times New Roman Bold Italic", 18);
		title.setMargin2(0, 0, 8, 8);

		// Add a 2 pixels wide separator line just under the title
		c.addLine(10, title.getHeight(), c.getWidth() - 11, title.getHeight(), Chart.LineColor, 2);

		// Set donut center at (160, 175), and outer/inner radii as 110/55 pixels
		c.setDonutSize((width-210)/2, height/2, (height-120)/2, (height-120)/4);

		// Set the pie data and the pie labels
		c.setData(data, labels);

		// Use ring shading effect for the sectors
		c.setSectorStyle(Chart.RingShading);

		// Use the side label layout method, with the labels positioned 16 pixels from the donut
		// bounding box
		c.setLabelLayout(Chart.SideLayout, 16);

		// Show only the sector number as the sector label
		c.setLabelFormat("{={sector}+1}");

		// Set the sector label style to Arial Bold 10pt, with a dark grey (444444) border
		c.setLabelStyle("Arial Bold", 10).setBackground(Chart.Transparent, 0x444444);

		// Add a legend box, with the center of the left side anchored at (330, 175), and using 10pt
		// Arial Bold Italic font
		LegendBox b = c.addLegend(width-210, 80, true, "Arial Bold Italic", 10);
		b.setAlignment(Chart.TopLeft);

		// Set the legend box border to dark grey (444444), and with rounded conerns
		b.setBackground(Chart.Transparent, 0x444444);
		b.setRoundedCorners();

		// Set the legend box margin to 16 pixels, and the extra line spacing between the legend
		// entries as 5 pixels
		b.setMargin(16);
		b.setKeySpacing(0, 5);

		// Set the legend text to show the sector number, followed by a 120 pixels wide block
		// showing the sector label, and a 40 pixels wide block showing the percentage
		b.setText(
				"<*block,valign=top*>{={sector}+1}.<*advanceTo=22*><*block,width=80*>{label}<*/*>" +
				"<*block,width=40,halign=right*>{percent}<*/*>%");

		// Output the chart
		viewer.setChart(c);

		//include tool tip for the chart
		viewer.setImageMap(c.getHTMLImageMap("clickable", "",
				"title='{label}: {value} ({percent}%)'"));
	}

	//Allow this module to run as standalone program for easy testing
	public static void main(String[] args)
	{
		//Instantiate an instance of this demo module
		DonutChartDrawer donutChartDrawer = new DonutChartDrawer(0);

		//Create and set up the main window
		JFrame frame = new JFrame(donutChartDrawer.toString());
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {e.getWindow().dispose();} });
		frame.getContentPane().setBackground(Color.white);

		// Create the chart and put them in the content pane
		ChartViewer viewer = new ChartViewer();
		donutChartDrawer.createChart(viewer, 480, 360);
		frame.getContentPane().add(viewer);

		// Display the window
		frame.pack();
		frame.setVisible(true);
	}
}