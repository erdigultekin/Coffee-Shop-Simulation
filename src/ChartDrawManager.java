import java.awt.Color;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ChartDirector.ChartViewer;

public class ChartDrawManager {
	static JFrame frameBalance = new JFrame("Balances Chart ");
	static JFrame frameSales = new JFrame("Sales Chart ");
	static JFrame framePrices = new JFrame("Prices Chart ");
	static JFrame frameChart;
	static MultilineChartDrawer charterBalance = new MultilineChartDrawer(0);
	static MultilineChartDrawer charterSales = new MultilineChartDrawer(1);
	static MultilineChartDrawer charterPrices = new MultilineChartDrawer(2);
	static MultilineChartDrawer charter;
	static int widthBalance;
	static int heightBalance;
	static int widthSales;
	static int heightSales;
	static int widthPrice;
	static int heightPrice;
	
	static JFrame frameBalanceDonut = new JFrame("Balances Donut Chart ");
	static JFrame frameSalesDonut = new JFrame("Sales Donut Chart ");
	static JFrame frameIncomesDonut = new JFrame("Incomes Donut Chart ");
	static JFrame frameChartDonut;
	static DonutChartDrawer charterBalanceDonut = new DonutChartDrawer(2);
	static DonutChartDrawer charterSalesDonut = new DonutChartDrawer(0);
	static DonutChartDrawer charterIncomeDonut = new DonutChartDrawer(1);
	static DonutChartDrawer charterDonut;
	static int widthBalanceDonut;
	static int heightBalanceDonut;
	static int widthSalesDonut;
	static int heightSalesDonut;
	static int widthIncomeDonut;
	static int heightIncomeDonut;
	
	public static void init(){
		widthBalance = 800;
		heightBalance = 600;
		widthSales = 800;
		heightSales = 600;
		widthPrice = 800;
		heightPrice = 600;
		widthBalanceDonut=760;
		heightBalanceDonut=320;
		widthSalesDonut=760;
		heightSalesDonut=320;
		widthIncomeDonut=760;
		heightIncomeDonut=320;
	}

	public static void drawLineCharts(){

		for(int i=0;i<3;i++){

			//Instantiate an instance of this chartDraw module
			charter = new MultilineChartDrawer(i);
			System.out.println("Charter initialized..");
			//Create and set up the main window
			int width;
			int height;
			if(i==0){
				frameChart=frameBalance;
				charter = charterBalance;
				width=widthBalance;
				height=heightBalance;
			}else if(i==1){
				frameChart=frameSales;
				charter=charterSales;
				width=widthSales;
				height=heightSales;
			}else{
				frameChart=framePrices;
				charter=charterPrices;
				width=widthPrice;
				height=heightPrice;
			}

			for(WindowListener wl : frameChart.getWindowListeners()){
				frameChart.removeWindowListener(wl);
				frameChart.remove(frameChart.getContentPane());
				JPanel panel = new JPanel();
				frameChart.setContentPane(panel);
			}

			for(ComponentListener cl : frameChart.getComponentListeners()){
				frameChart.removeComponentListener(cl);
			}

			frameChart.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					e.getWindow().dispose();} });
			frameChart.getContentPane().setBackground(Color.white);
			System.out.println("Frame created.");
			// Create the chart and put them in the content pane
			ChartViewer viewer = new ChartViewer();
			System.out.println("Viewer created.");

			charter.createChart(viewer,width,height);
			System.out.println("Chart Created.");
			frameChart.getContentPane().add(viewer);

			// Display the window
			
			frameChart.pack();
			frameChart.setVisible(true);
			System.out.println("Frame is visible now");
			// Resize handler
			frameChart.addComponentListener(new java.awt.event.ComponentAdapter() {
				public void componentResized(java.awt.event.ComponentEvent evt) {
					JFrame tmp = (JFrame)evt.getSource();
					if (tmp.getTitle().equals("Balances Chart ")) {
						charter=charterBalance;
						widthBalance=tmp.getContentPane().getWidth();
						heightBalance=tmp.getContentPane().getHeight();
					}
					else if (tmp.getTitle().equals("Sales Chart ")) {
						charter=charterSales;
						widthSales=tmp.getWidth();
						heightSales=tmp.getHeight();
					}
					else {
						charter = charterPrices;
						widthPrice=tmp.getWidth();
						heightPrice=tmp.getHeight();
					}

					if (tmp.getWidth() < 250 || tmp.getHeight() < 250){
						charter.createChart(viewer, 250, 250);
						tmp.setSize(250, 250);
					}else{
						charter.createChart(viewer, tmp.getWidth(), tmp.getHeight());
					}
				}
			});

		}
	}
	
	public static void drawDonutCharts(){

		for(int i=0;i<3;i++){

			//Instantiate an instance of this demo module
			DonutChartDrawer donutChartDrawer = new DonutChartDrawer(i);

			int width;
			int height;
			if(i==0){
				frameChartDonut=frameSalesDonut;
				charterDonut=charterSalesDonut;
				width=widthSalesDonut;
				height=heightSalesDonut;
			}else if(i==1){
				frameChartDonut=frameIncomesDonut;
				charterDonut=charterIncomeDonut;
				width=widthIncomeDonut;
				height=heightIncomeDonut;
			}else{
				frameChartDonut=frameBalanceDonut;
				charterDonut = charterBalanceDonut;
				width=widthBalanceDonut;
				height=heightBalanceDonut;
			}

			for(WindowListener wl : frameChartDonut.getWindowListeners()){
				frameChartDonut.removeWindowListener(wl);
				frameChartDonut.remove(frameChartDonut.getContentPane());
				JPanel panel = new JPanel();
				frameChartDonut.setContentPane(panel);
			}

			for(ComponentListener cl : frameChartDonut.getComponentListeners()){
				frameChartDonut.removeComponentListener(cl);
			}
			frameChartDonut.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {e.getWindow().dispose();} });
			frameChartDonut.getContentPane().setBackground(Color.white);

			// Create the chart and put them in the content pane
			ChartViewer viewer = new ChartViewer();
			donutChartDrawer.createChart(viewer, 480, 360);
			frameChartDonut.getContentPane().add(viewer);

			// Display the window
			frameChartDonut.pack();
			frameChartDonut.setVisible(true);
			
			frameChartDonut.addComponentListener(new java.awt.event.ComponentAdapter() {
				public void componentResized(java.awt.event.ComponentEvent evt) {
					JFrame tmp = (JFrame)evt.getSource();
					if (tmp.getTitle().equals("Balances Donut Chart ")) {
						charterDonut=charterBalanceDonut;
						widthBalanceDonut=tmp.getContentPane().getWidth();
						heightBalanceDonut=tmp.getContentPane().getHeight();
					}
					else if (tmp.getTitle().equals("Sales Donut Chart ")) {
						charterDonut=charterSalesDonut;
						widthSalesDonut=tmp.getWidth();
						heightSalesDonut=tmp.getHeight();
					}
					else {
						charterDonut = charterIncomeDonut;
						widthIncomeDonut=tmp.getWidth();
						heightIncomeDonut=tmp.getHeight();
					}

					if (tmp.getWidth() < 250 || tmp.getHeight() < 250){
						charterDonut.createChart(viewer, 250, 250);
						tmp.setSize(250, 250);
					}else{
						charterDonut.createChart(viewer, tmp.getWidth(), tmp.getHeight());
					}
				}
			});

		}
	}
}
