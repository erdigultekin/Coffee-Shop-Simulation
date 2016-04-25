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
import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.ss.usermodel.Row; 
import org.apache.poi.xssf.usermodel.XSSFSheet; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

/** * Sample Java program to read and write Excel file in Java using Apache POI * */ 
public class XLSXReaderWriter {
	File excel;
	FileInputStream fis;
	XSSFWorkbook book;
	XSSFSheet sheet;
	Iterator<Row> itr;
	Iterator<Cell> cellIterator;
	HashMap<String,Integer> excelShopRowMap;
	public XLSXReaderWriter(String filename){
		excelShopRowMap = new HashMap<String,Integer>();
		try {
			excel = new File(filename);
			fis = new FileInputStream(excel); 
			book = new XSSFWorkbook(fis); 
			sheet = book.getSheetAt(0); 
			
			itr = sheet.iterator(); 
			// Iterating over Excel file in Java 
			while (itr.hasNext()) { 
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
							Engine.sendCustomersToShops();
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
			Row row = itr.next();
			cellIterator = row.cellIterator();
			Shop shop = new Shop(cellIterator.next().getStringCellValue());
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

	public void shopWriter(){
		try {
			// writing data into XLSX file 
			Map<String, Object[]> newData = new HashMap<String, Object[]>(); 
			newData.put("7", new Object[] { 7d, "Sonya", "75K", "SALES", "Rupert" }); 
			newData.put("8", new Object[] { 8d, "Kris", "85K", "SALES", "Rupert" }); 
			newData.put("9", new Object[] { 9d, "Dave", "90K", "SALES", "Rupert" }); 
			Set<String> newRows = newData.keySet(); 
			int rownum = sheet.getLastRowNum(); 
			for (String key : newRows) { 
				Row row = sheet.createRow(rownum++); 
				Object[] objArr = newData.get(key); 
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
				} 
			} 
			// open an OutputStream to save written data into Excel file 
			FileOutputStream os = new FileOutputStream(excel); 
			book.write(os); System.out.println("Writing on Excel file Finished ..."); 
			// Close workbook, OutputStream and Excel file to prevent leak 
			os.close();
			book.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	public static void main(String[] args) {
		XLSXReaderWriter exceller = new XLSXReaderWriter("EngineTest.xlsx");
		
	}
//		try { 
//			File excel = new File("Employee.xlsx"); 
//			FileInputStream fis = new FileInputStream(excel); 
//			XSSFWorkbook book = new XSSFWorkbook(fis); 
//			XSSFSheet sheet = book.getSheetAt(0); 
//			Iterator<Row> itr = sheet.iterator(); 
//			// Iterating over Excel file in Java 
//			while (itr.hasNext()) { 
//				Row row = itr.next(); 
//				// Iterating over each column of Excel file 
//				Iterator<Cell> cellIterator = row.cellIterator(); 
//				while (cellIterator.hasNext()) { 
//					Cell cell = cellIterator.next();
//					switch (cell.getCellType()) { 
//					case Cell.CELL_TYPE_STRING: 
//						System.out.print(cell.getStringCellValue() + "\t"); 
//						break; 
//					case Cell.CELL_TYPE_NUMERIC: 
//						System.out.print(cell.getNumericCellValue() + "\t"); 
//						break; 
//					case Cell.CELL_TYPE_BOOLEAN: 
//						System.out.print(cell.getBooleanCellValue() + "\t"); 
//						break; 
//					default: 
//					} 
//				} 
//				System.out.println(""); 
//			} 
//			// writing data into XLSX file 
//			Map<String, Object[]> newData = new HashMap<String, Object[]>(); 
//			newData.put("7", new Object[] { 7d, "Sonya", "75K", "SALES", "Rupert" }); 
//			newData.put("8", new Object[] { 8d, "Kris", "85K", "SALES", "Rupert" }); 
//			newData.put("9", new Object[] { 9d, "Dave", "90K", "SALES", "Rupert" }); 
//			Set<String> newRows = newData.keySet(); 
//			int rownum = sheet.getLastRowNum(); 
//			for (String key : newRows) { 
//				Row row = sheet.createRow(rownum++); 
//				Object[] objArr = newData.get(key); 
//				int cellnum = 0; 
//				for (Object obj : objArr) { 
//					Cell cell = row.createCell(cellnum++); 
//					if (obj instanceof String) { 
//						cell.setCellValue((String) obj); } 
//					else if (obj instanceof Boolean) { 
//						cell.setCellValue((Boolean) obj); } 
//					else if (obj instanceof Date) { 
//						cell.setCellValue((Date) obj); } 
//					else if (obj instanceof Double) { 
//						cell.setCellValue((Double) obj); } 
//				} 
//			} 
//			// open an OutputStream to save written data into Excel file 
//			FileOutputStream os = new FileOutputStream(excel); 
//			book.write(os); System.out.println("Writing on Excel file Finished ..."); 
//			// Close workbook, OutputStream and Excel file to prevent leak 
//			os.close(); 
//			book.close(); 
//			fis.close(); 
//		} 
//		catch (FileNotFoundException fe) { 
//			fe.printStackTrace(); } 
//		catch (IOException ie) { 
//			ie.printStackTrace(); } 
//	} 
}	