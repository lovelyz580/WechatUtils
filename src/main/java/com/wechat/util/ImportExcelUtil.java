package com.wechat.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 导入Excel
 * 
 * 解析工具
 */

public class ImportExcelUtil {
	
	private static final int DEFAULT_START_LINE = 0;
	private static final int DEFAULT_COUNT = 20000;
	// private final Logger logger = LoggerFactory.getLogger(ImportExcelUtil.class);

	/**
	 * 解析文件
	 * 
	 * @param inputStream 文件流
	 * @param tableNumber 第tableNumber个工作表格(从0开始)
	 * @param classObj 解析的实体类对象
	 * @param isExcel2003 是否为Excel2003
	 * @param startLine 开始解析行数
	 * @param maxLine 最大行数
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> convertSheetToList(InputStream inputStream, int tableNumber, Class<T> classObj, 
			boolean isExcel2003, int startLine, int maxLine) throws Exception {
		List<T> list = new ArrayList<T>();
		
		// 根据版本选择创建Workbook的方式 
		Workbook wb;
		if (isExcel2003) {
			wb = new HSSFWorkbook(inputStream);
		} else {
			wb = new XSSFWorkbook(inputStream);
		}
		
		if (null != wb) {
			Sheet sheet = wb.getSheetAt(tableNumber); // 获取第tableNumber个工作表格
			int count = sheet.getLastRowNum(); // 表格行数

			// 表格行数相关操作
			if (startLine < 0) {
				startLine = DEFAULT_START_LINE;
			}
			if (maxLine == 0) {
				maxLine = DEFAULT_COUNT;
			}
			if (count > maxLine) {
				throw new Exception("导入失败，Excel数据控制在" + maxLine + "条之内！");
			}
			
			// 遍历Excel表格并将每一行中的数据转换成对象
			for (int i = startLine; i <= count; i++) {
				Row row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				T obj = convertLineToObj(classObj, row);
				if (obj == null) {
					continue;
				}
				list.add(obj);
			}
		}
		return list;
	}

	/**
	 * 行遍历
	 * 
	 * @param classObj 解析的实体类对象
	 * @param row 行数
	 * @return
	 * @throws Exception
	 */
	private static <T> T convertLineToObj(Class<T> classObj, Row row) throws Exception {
		T obj = classObj.newInstance();
		Field[] fields = classObj.getDeclaredFields();
		for (Field field: fields) {
			ExcelImport annotation = field.getAnnotation(ExcelImport.class);
			if (annotation != null && row.getLastCellNum() >= annotation.columnIndex()) {
				// 每行对应的单元格遍历
				Cell cell = row.getCell(annotation.columnIndex());
				if (cell == null) {
					// throw new Exception("请使用正确的Excel模板！");
					break;
				}
				field.setAccessible(true);
				field.set(obj, getCellValue(cell));
			}
		}
		return obj;
	}

	private static Object getCellValue(Cell cell) {
		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			String data;
			if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {   
                Date theDate = cell.getDateCellValue();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                data = sdf.format(theDate);
            } else {
            	DecimalFormat df = new DecimalFormat("0");
            	data = df.format(cell.getNumericCellValue());
            }
			return data;
		}
		
		// 个别特殊单元格解析
		// 赡养老人支出
		// 本年度月扣除金额
		if (cell.toString().length() > 16) {
			if ("IF(C2=\"是\",2000,".equals(cell.toString().substring(0, 15))) {
				if ("IF(C2=\"是\",2000,\" \")".equals(cell.toString())) {
					return "2000";
				}
			}
		}
		
		// 赡养老人支出
		// 出生日期
		if (cell.toString().length() > 22) {
			if ("\"居民身份证\",TEXT((".equals(cell.toString().substring(6, 20))) {
				return "居民身份证";
			}
		}
		
		return String.valueOf(cell.getStringCellValue());
	}

	/**
	 * 测试数据
	 */
//	public static void main(String[] args) {
//		try {
//			 FileInputStream in = new FileInputStream("/Users/vobile_lzl/Downloads/tvids(2).xlsx");
//			 List<TvIdPresent> list = convertSheetToList(in, TvIdPresent.class, false, 1);
//			 System.out.println("list=" + list);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}


/*
	public static List readExcel(String filePath) throws Exception {
//        List集合用于存excel 数据
		List<String[]> lists = new ArrayList<>();
//        创建一个文件
		File file = new File(filePath);
//        创建一个字节输入流对象
		FileInputStream input = new FileInputStream(file);
		Workbook book = null;
		book = WorkbookFactory.create(input);
//        获取工作薄的个数
		int sheetCount = book.getNumberOfSheets();

		Sheet sheet = null;
		for (int i = 0; i < sheetCount; i++) { //循环工作薄的个数
			sheet = book.getSheetAt(i);
//            获取工作薄的最后一行行数
			int lastRowNum = sheet.getLastRowNum();
			for (int j = 0; j < lastRowNum; j++) {
				Row row = sheet.getRow(j);
				if (row == null) continue;
//                获取最后一列的列数
				int lastcellNum = row.getLastCellNum();
				String[] strings = new String[row.getPhysicalNumberOfCells()];
				for (int k = 0, indexes = 0; k <= lastcellNum; k++) {
					Cell cell = row.getCell(k);
					if (cell == null) continue;
					cell.setCellType(CellType.STRING);
					String cellValue = cell.getStringCellValue();
					System.out.println(cellValue);
					//                    将数据存入list
					strings[indexes++] = cellValue;


				}
				lists.add(strings);


			}


		}
		return lists;


	}*/


}