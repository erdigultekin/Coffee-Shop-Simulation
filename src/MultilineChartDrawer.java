import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import ChartDirector.*;

public class MultilineChartDrawer
{
	XYChart c;
	String title = "";

	int identifier;

	HashMap<String,ArrayList<Double>> dataSource;

	String[] titles = {"Chart of Balances","Chart of Sales"
			,"Chart of Prices","Chart of Utility 1"
			,"Chart of Utility 2","Chart of Utility 3"};

	String[] yAxisTitles = {"Balances","Sales","Prices"
			,"Utility1s","Utility2s","Utility3s"};

	String[] frameTitles={"Balance Chart of Day "+Teacher.day,"Sales Chart of Day "+Teacher.day
			,"Prices Chart of Day "+Teacher.day,"Utility 1 Chart of Day "+Teacher.day
			,"Utility 2 Chart of Day "+Teacher.day,"Utility 3 Chart of Day "+Teacher.day};


	public MultilineChartDrawer(int chartIdentifier){
		identifier=chartIdentifier;
		if(identifier==0) dataSource = Engine.shopBalances;
		else if(identifier==1) dataSource = Engine.shopSales;
		else if(identifier==2) dataSource = Engine.shopPrices;
		else if(identifier==3) dataSource = Engine.shopU1s;
		else if(identifier==4) dataSource = Engine.shopU2s;
		else if(identifier==5) dataSource = Engine.shopU3s;

	}

	//Name of demo program
	public String toString() { return frameTitles[identifier]; }

	//Number of charts produced in this demo
	public int getNoOfCharts() { return 1; }

	//Main code for creating charts
	public void createChart(ChartViewer viewer)
	{
		String[] shopNames = new String[dataSource.size()];
		// The data for the line chart
		double[][] data = new double[dataSource.size()][Teacher.day+1];

		int i=0;
		for(String shopName : dataSource.keySet()){
			int j=0;
			for(Double d : dataSource.get(shopName)){
				data[i][j] = d;
				j++;
			}
			shopNames[i]= shopName;
			i++;
		}
		// The labels for the line chart
		String[] labels = new String[Teacher.day+1];
		for(int day=0;day<=Teacher.day;day++){
			labels[day]=""+day;
		}
		System.out.println("Data sets created");
		// Create an XYChart object of size 600 x 300 pixels, with a light blue (EEEEFF) background,
		// black border, 1 pxiel 3D border effect and rounded corners
		this.c = new XYChart(800, 600, 0xeeeeff, 0x000000, 1);
		c.setRoundedFrame();

		// Set the plotarea at (55, 58) and of size 520 x 195 pixels, with white background. Turn on
		// both horizontal and vertical grid lines with light grey color (0xcccccc)
		c.setPlotArea(82, 88, 650, 400, 0xffffff, -1, -1, 0xcccccc, 0xcccccc);

		// Add a legend box at (50, 30) (top of the chart) with horizontal layout. Use 9pt Arial
		// Bold font. Set the background and border color to Transparent.
		c.addLegend(75, 45, false, "Arial Bold", 9).setBackground(Chart.Transparent);

		// Add a title box to the chart using 15pt Times Bold Italic font, on a light blue (CCCCFF)
		// background with glass effect. white (0xffffff) on a dark red (0x800000) background, with
		// a 1 pixel 3D border.
		c.addTitle(titles[identifier], "Times New Roman Bold Italic", 15
				).setBackground(0xccccff, 0x000000, Chart.glassEffect());

		// Add a title to the y axis
		c.yAxis().setTitle(yAxisTitles[identifier]);

		// Set the labels on the x axis.
		c.xAxis().setLabels(labels);

		// Display 1 out of 3 labels on the x-axis.
		//c.xAxis().setLabelStep(3);

		// Add a title to the x axis
		c.xAxis().setTitle("Days");

		// Add a line layer to the chart
		LineLayer layer = c.addLineLayer2();

		// Set the default line width to 2 pixels
		layer.setLineWidth(2);

		// Add the three data sets to the line layer. For demo purpose, we use a dash line color for
		// the last line
		for(int k=0;k<shopNames.length;k++){
			layer.addDataSet(data[k], colorer(k), shopNames[k]);
		}

		// Output the chart
		viewer.setChart(c);

		//include tool tip for the chart
		viewer.setImageMap(c.getHTMLImageMap("clickable", "",
				"title='[{dataSetName}] Day {xLabel}: ${value} '"));

	}

	public int colorer(int n){
		if(n==0){
			return 0x00FF0000;
		}else if(n==1){
			return 0x0000FF00;
		}else if(n==2){
			return 0x000000FF;
		}else if(n==3){
			return 0x00FFFF00;
		}else if(n==4){
			return 0x00FF00FF;
		}else if(n==5){
			return 0x0000FFFF;
		}else if(n==6){
			return c.dashLineColor(0x3333ff, Chart.DashLine);
		}else if(n==7){
			return 0x003084dd;
		}else if(n==8){
			return 0x00e19632;
		}else if(n==9){
			return 0x00c87b5c;
		}else if(n==10){
			return 0x00000000;
		}else if(n==11){
			return c.dashLineColor(0x00000000, Chart.DashLine);
		}else if(n==12){
			return 0x00670d0f;
		}else if(n==13){
			return 0x0083c3bb;
		}else if(n==14){
			return 0x00b23f81;
		}else if(n==15){
			return 0x00053f01;
		}else if(n==16){
			return 0x00d9abe8;
		}else if(n==17){
			return 0x000a7f65;
		}else if(n==18){
			return 0x006d5e66;
		}else if(n==19){
			return 0x00f33d01;
		}else{
			return 0x00000000;
		}
	}

	//Allow this module to run as standalone program for easy testing
	public static void main(String[] args)
	{
		//Instantiate an instance of this demo module
		MultilineChartDrawer charter = new MultilineChartDrawer(0);

		//Create and set up the main window
		JFrame frame = new JFrame(charter.toString());
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);} });
		frame.getContentPane().setBackground(Color.white);

		// Create the chart and put them in the content pane
		ChartViewer viewer = new ChartViewer();
		charter.createChart(viewer);
		frame.getContentPane().add(viewer);

		// Display the window
		frame.pack();
		frame.setVisible(true);
	}
}
