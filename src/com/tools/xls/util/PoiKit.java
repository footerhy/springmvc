package com.tools.xls.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import com.tools.beans.BaseModel;
import com.tools.beans.GbDoc;
import com.tools.consts.utils.Consts;
import com.tools.log.util.LogUtils;
import com.google.common.base.Preconditions;

public class PoiKit {

	private static final int HEADER_ROW = 1;
	private static final int MAX_ROWS = 65536;

	private String sheetName = "new sheet";
	private int cellWidth = 8000;
	private int headerRow;
	private String[] headers = new String[] {};
	private String[] columns;
	private List<?> data;
	
	public PoiKit(List<?> data) {
		this.data = data;
	}
	public PoiKit() {
		
	}

	public static PoiKit with(List<?> data) {
		return new PoiKit(data);
	}

	public HSSFWorkbook export() {
		Preconditions.checkNotNull(headers, "headers can not be null");
		Preconditions.checkNotNull(columns, "columns can not be null");
		Preconditions.checkArgument(cellWidth >= 0, "cellWidth < 0");
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName);
		HSSFRow row = null;
		HSSFCell cell = null;
		if (headers.length > 0) {
			row = sheet.createRow(0);
			if (headerRow <= 0) {
				headerRow = HEADER_ROW;
			}
			headerRow = Math.min(headerRow, MAX_ROWS);
			for (int h = 0, lenH = headers.length; h < lenH; h++) {
				@SuppressWarnings("deprecation")
				Region region = new Region(0, (short) h, (short) headerRow - 1,
						(short) h);// 合并从第rowFrom行columnFrom�?
				sheet.addMergedRegion(region);// 到rowTo行columnTo的区�?
				// 得到�?��区域
				sheet.getNumMergedRegions();
				if (cellWidth > 0) {
					System.out.println("设置宽度来了�?!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!是多�?"+cellWidth);
					sheet.setColumnWidth(h, cellWidth);
				}
				HSSFFont font = wb.createFont();
				font.setColor(HSSFFont.COLOR_RED);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				HSSFCellStyle cellStyle = wb.createCellStyle();
				cellStyle.setFont(font);
				cell = row.createCell(h);
				cell.setCellStyle(cellStyle);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(headers[h]);
			}
		}
		if (data.size() == 0) {
			return wb;
		}
		for (int i = 0, len = data.size(); i < len; i++) {
			row = sheet.createRow(i + headerRow);
			Object obj = data.get(i);
			if (obj == null) {
				continue;
			}
			 System.out.println("==============================");
			if (obj instanceof Map) {
				 System.out.println("obj instanceof Map");
				processAsMap(columns, row, obj);
			} 
			else if (obj instanceof BaseModel) {
				 System.out.println("obj instanceof BaseModel");
				processAsModel(columns, row, obj);
			}
			// else if (obj instanceof Record) {
			//	processAsRecord(columns, row, obj);
			//}
		}
		return wb;
	}

	@SuppressWarnings("unchecked")
	private static void processAsMap(String[] columns, HSSFRow row, Object obj) {
		HSSFCell cell;
		Map<String, Object> map = (Map<String, Object>) obj;
		if (columns.length == 0) {// 未设置显示列，默认全�?
			Set<String> keys = map.keySet();
			int columnIndex = 0;
			for (String key : keys) {
				cell = row.createCell(columnIndex);
				cell.setCellValue(map.get(key) + "");
				columnIndex++;
			}
		} else {
			for (int j = 0, lenJ = columns.length; j < lenJ; j++) {
				cell = row.createCell(j);
				cell.setCellValue(map.get(columns[j]) + "");
			}
		}
	}

	private static void processAsModel(String[] columns, HSSFRow row, Object obj) {
		 HSSFCell cell;
		 JsonConfig jsonConfig = new JsonConfig();    
		 jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());    
		 JSONArray jsonArr = JSONArray.fromObject(obj , jsonConfig);
		 JSONObject jsonObj= jsonArr.getJSONObject(0);
		 JSONArray  jsonName=jsonObj.names();
		 System.out.println("jsonArr:"+jsonArr);
		 System.out.println("jsonObj:"+jsonObj);
		 System.out.println("jsonName:"+jsonName);
		 System.out.println("columns.length:"+columns.length);
			if (columns.length== 0) {// 未设置显示列，默认全�?
				int columnIndex = 0;
				for (int i=0;i< jsonName.size();i++) {
					cell = row.createCell(columnIndex);
					cell.setCellValue(jsonObj.get(jsonName.get(i)) + "");
					columnIndex++;
				}
			} else {
				for (int j = 0, lenJ = columns.length; j < lenJ; j++) {
					System.out.println("jsonObj.get(columns[j])"+jsonObj.get(columns[j]) + " " + columns[j]);
					cell = row.createCell(j);
					cell.setCellValue(jsonObj.get(columns[j]) + "");
				}
			}
	}

	private static void processAsRecord(String[] columns, HSSFRow row,
			Object obj) {
		/*
		HSSFCell cell;
		Record record = (Record) obj;
		Map<String, Object> map = record.getColumns();
		if (columns.length == 0) {// 未设置显示列，默认全�?
			record.getColumns();
			Set<String> keys = map.keySet();
			int columnIndex = 0;
			for (String key : keys) {
				cell = row.createCell(columnIndex);
				cell.setCellValue(record.get(key) + "");
				columnIndex++;
			}
		} else {
			for (int j = 0, lenJ = columns.length; j < lenJ; j++) {
				cell = row.createCell(j);
				cell.setCellValue(map.get(columns[j]) + "");
			}
		}
		*/
	}

	public PoiKit sheetName(String sheetName) {
		this.sheetName = sheetName;
		return this;
	}

	public PoiKit cellWidth(int cellWidth) {
		this.cellWidth = cellWidth;
		return this;
	}

	public PoiKit headerRow(int headerRow) {
		this.headerRow = headerRow;
		return this;
	}

	public PoiKit headers(String[] headers) {
		this.headers = headers;
		return this;
	}

	public PoiKit columns(String[] columns) {
		this.columns = columns;
		return this;
	}
	public String getPostfix(String path){
		String strExtName = "";
		int idx = path.lastIndexOf('.');
		if (idx > 0) strExtName = path.substring(idx).toLowerCase();
//		LogUtils.error("strExtName:"+strExtName);
		return strExtName;
	}

	
	    
	    /**
	     * read the Excel file
	     * @param path the path of the Excel file
	     * @return
	     * @throws IOException
	     */
	    public List<Map<String,Object>> readExcel(String path,GbDoc doc) throws IOException {
	    	String docPath=doc.getDoc_path();
			String fullDocPath=path+docPath;
			String DocName=doc.getDoc_name();//.c
//			LogUtils.error("docPath:"+docPath);
//			LogUtils.error("fullDocPath:"+fullDocPath);
//			LogUtils.error("DocName:"+DocName);
	        if (fullDocPath == null ||Consts.EMPTY.equals(fullDocPath)) {
	            return null;
	        } else {
	            String postfix = getPostfix(DocName);
	            if (!Consts.EMPTY.equals(postfix)) {
	                if (Consts.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
//	                	LogUtils.error("Consts.OFFICE_EXCEL_2003_POSTFIX:"+Consts.OFFICE_EXCEL_2003_POSTFIX);
	                    return readXls(fullDocPath);
	                } else if (Consts.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
//	                	LogUtils.error("Consts.OFFICE_EXCEL_2010_POSTFIX:"+Consts.OFFICE_EXCEL_2003_POSTFIX);
	                    return readXlsx(fullDocPath);
	                }else{
//	                	LogUtils.error(Consts.NOT_EXCEL_FILE+" not find file by "+postfix);
	                }
	            } else {
//	            	LogUtils.error(fullDocPath + Consts.NOT_EXCEL_FILE);
	            }
	        }
	        return null;
	    }

	    /**
	     * Read the Excel 2010
	     * @param path the path of the excel file
	     * @return
	     * @throws IOException
	     */
	    public List<Map<String,Object>> readXlsx(String path) throws IOException {
	        System.out.println(Consts.PROCESSING + path);
	        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(path);
	       // Student student = null;
	        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	        // Read the Sheet
	        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
	            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
	            if (xssfSheet == null) {
	                continue;
	            }
	            // Read the Row
	            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
	                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
	                if (xssfRow != null) {
	                	Map<String,Object> map=new HashMap<String,Object>();
//	                	LogUtils.error("2007列�?数量:"+xssfRow.getLastCellNum());
	                	for(int cellNum=0;cellNum<xssfRow.getLastCellNum();cellNum++){
		                    map.put("name_"+cellNum, xssfRow.getCell(cellNum));
	                	}
	                	list.add(map);
	                }
	            }
	        }
	        return list;
	    }

	    /**
	     * Read the Excel 2003-2007
	     * @param path the path of the Excel
	     * @return
	     * @throws IOException
	     */
	    public List<Map<String,Object>> readXls(String path) throws IOException {
	        System.out.println(Consts.PROCESSING + path);
	        InputStream is = new FileInputStream(path);
	        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
	        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	        // Read the Sheet
	        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
	            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	            if (hssfSheet == null) {
	                continue;
	            }
	            // Read the Row
	            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	                if (hssfRow != null) {
//	                	LogUtils.error("2003列�?数量:"+hssfRow.getLastCellNum());
	                	Map<String,Object> map=new HashMap<String,Object>();
	                	for(int cellNum=0;cellNum<hssfRow.getLastCellNum();cellNum++){
		                    map.put("name_"+cellNum, hssfRow.getCell(cellNum));
	                	}
	                	list.add(map);
	                }
	            }
	        }
	        return list;
	    }

	    @SuppressWarnings("static-access")
	    private String getValue(XSSFCell xssfRow) {
	        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
	            return String.valueOf(xssfRow.getBooleanCellValue());
	        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
	            return String.valueOf(xssfRow.getNumericCellValue());
	        } else {
	            return String.valueOf(xssfRow.getStringCellValue());
	        }
	    }

	    @SuppressWarnings("static-access")
	    private String getValue(HSSFCell hssfCell) {
	        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	            return String.valueOf(hssfCell.getBooleanCellValue());
	        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
	            return String.valueOf(hssfCell.getNumericCellValue());
	        } else {
	            return String.valueOf(hssfCell.getStringCellValue());
	        }
	    }
	
}
