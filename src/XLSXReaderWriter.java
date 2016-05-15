import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.sql.Date; 
import java.util.HashMap; 
import java.util.Iterator; 
import java.util.Map; 
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumns;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleInfo; 

public class XLSXReaderWriter {
	File excel;
	FileInputStream fis;
	FileOutputStream fos;
	XSSFWorkbook book;
	XSSFSheet sheet;
	Iterator<Row> itr;
	Iterator<Cell> cellIterator;
	HashMap<String,Integer> excelShopRowMap;
	public static int runCount = 1;

	public XLSXReaderWriter(String filename) throws InterruptedException{
		excelShopRowMap = new HashMap<String,Integer>();
		try {
			excel = new File(filename);
			fis = new FileInputStream(excel); 
			book = new XSSFWorkbook(fis); 
			sheet = book.getSheetAt(0); 

			itr = sheet.iterator(); 
			// Iterating over Excel file in Java 
			while (itr.hasNext()) {
				Thread.sleep(100);
				Row row = itr.next(); 
				// Iterating over each column of Excel file 
				cellIterator = row.cellIterator(); 
				while (cellIterator.hasNext()) { 
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) { 
					case Cell.CELL_TYPE_STRING: 
						if(cell.getStringCellValue().equals("TABLE(3x3)")){
							System.out.println("Declaring function parameters...");
							declareUtilityParameters(1);
						}else if(cell.getStringCellValue().equals("coffee max")){
							System.out.println("Declaring max values...");
							declareMaxValues();
						}else if(cell.getStringCellValue().equals("Shop Name")){
							declareShops();
							for(int i=0;i<runCount;i++){
								if(Teacher.day==0){
									Teacher.day=1;
								}else{
									Teacher.day++;
								}
								Engine.sendCustomersToShops();
								printTestResults("TestResults"+i+".xlsx");
							}
							return;
						}
						break; 
					case Cell.CELL_TYPE_NUMERIC: 
						System.out.print(cell.getNumericCellValue() + "\t"); 
						break; 
					case Cell.CELL_TYPE_BOOLEAN: 
						System.out.print(cell.getBooleanCellValue() + "\t"); 
						break; 
					default: 
					} 
				} 
				System.out.println(""); 

			}
			System.out.println("Latest row#: "+sheet.getLastRowNum());


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void declareShops() {
		
		try {
			while(itr.hasNext()){
				Row row = itr.next();
				cellIterator = row.cellIterator();
				String shopName = cellIterator.next().getStringCellValue();
				if(shopName.equals("")){
					break;
				}
				Shop shop = new Shop(shopName);
				shop.balance = cellIterator.next().getNumericCellValue();
				double cups = cellIterator.next().getNumericCellValue();
				double coffee = cellIterator.next().getNumericCellValue();
				double milk = cellIterator.next().getNumericCellValue();
				double sugar = cellIterator.next().getNumericCellValue();
				shop.inventory = new Inventory((int)cups,(int) coffee,(int) milk,(int) sugar);
				double price = cellIterator.next().getNumericCellValue();
				coffee = cellIterator.next().getNumericCellValue();
				milk = cellIterator.next().getNumericCellValue();
				sugar = cellIterator.next().getNumericCellValue();
				shop.recipe = new Recipe(coffee, milk, sugar, price);
				shop.dailySales=0;
				Engine.shopMap.put(shop.name, shop);
				excelShopRowMap.put(shop.name, row.getRowNum());
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void declareMaxValues() {
		Row row = itr.next();
		cellIterator = row.cellIterator();
		Model.coffeeMax = cellIterator.next().getNumericCellValue();
		System.out.println("Coffee Max : "+Model.coffeeMax);
		Model.sugarMax = cellIterator.next().getNumericCellValue();
		System.out.println("Sugar Max : "+Model.sugarMax);
		Model.milkMax = cellIterator.next().getNumericCellValue();
		System.out.println("Milk Max : "+Model.milkMax);
	}

	private void declareUtilityParameters(int n) {
		Row row = itr.next();
		cellIterator = row.cellIterator();
		cellIterator.next();
		double v1 = cellIterator.next().getNumericCellValue();
		double v2 = cellIterator.next().getNumericCellValue();
		double v3 = cellIterator.next().getNumericCellValue();
		if(n==1){
			Model.betaOne = v1;
			Model.alphaOne = v2;
			Model.probabilityOne = v3;
			declareUtilityParameters(2);
		}else if(n==2){
			Model.betaTwo = v1;
			Model.alphaTwo = v2;
			Model.probabilityTwo = v3;
			declareUtilityParameters(3);
		}else if(n==3){
			Model.betaThree = v1;
			Model.alphaThree = v2;
			Model.probabilityThree = v3;
			arrangeProbabilities();
		}

	}

	private void arrangeProbabilities() {
		double sumProbs = Model.probabilityOne+Model.probabilityTwo+Model.probabilityThree;
		Model.probabilityOne /= sumProbs;
		Model.probabilityTwo /= sumProbs;
		Model.probabilityThree = 1.0 - Model.probabilityOne - Model.probabilityTwo;
	}

	public static void printTestResults(String filename){
		try {

			Workbook wb = new XSSFWorkbook();
			XSSFSheet sheet = (XSSFSheet) wb.createSheet();

			Map<String,Object[]> data = new HashMap<String,Object[]>();

			int i = 2;
			data.put("1", new Object[] {"Shop Name","Balance","Inventory-cups"
					,"Inventory-coffee(kg)","Inventory-milk(lt)","Inventory-sugar(kg)"
					,"Price","Recipe-coffe(gr)","Recipe-milk(ml)","Recipe-sugar(gr)"
					,"Q1","Q2","Q3","QTotal","Utility1","Utility2","Utility3","Daily Sales"});
			for(Shop s : Engine.shopMap.values()){
				data.put(""+i, new Object[] {s.name,s.balance,s.inventory.cups,s.inventory.coffee
						,s.inventory.milk,s.inventory.sugar,s.recipe.price,s.recipe.coffee
						,s.recipe.milk,s.recipe.sugar,Model.calculateQ1(s),Model.calculateQ2(s)
						,Model.calculateQ3(s),Model.calculateQuality(s),Model.calculateU1(s)
						,Model.calculateU2(s),Model.calculateU3(s),s.dailySales});
				i++;
			}

			int rownum = 1;

			Set<String> newRows = data.keySet(); 

			while (rownum < data.size()+1) {
				String key = ""+rownum;
				Row row = sheet.createRow(rownum++); 
				Object[] objArr = data.get(key); 
				int cellnum = 0; 
				for (Object obj : objArr) { 
					Cell cell = row.createCell(cellnum++); 
					if (obj instanceof String) { 
						cell.setCellValue((String) obj); } 
					else if (obj instanceof Boolean) { 
						cell.setCellValue((Boolean) obj); } 
					else if (obj instanceof Date) { 
						cell.setCellValue((Date) obj); } 
					else if (obj instanceof Double) { 
						cell.setCellValue((Double) obj); }
					else if (obj instanceof Integer){
						Double d = (Integer)obj + 0.0;
						cell.setCellValue(d);
					}
				} 
			}

			FileOutputStream fileOut = new FileOutputStream(filename);
			wb.write(fileOut);
			fileOut.close();
			wb.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		XLSXReaderWriter exceller = new XLSXReaderWriter("EngineTest.xlsx");
	}
}	