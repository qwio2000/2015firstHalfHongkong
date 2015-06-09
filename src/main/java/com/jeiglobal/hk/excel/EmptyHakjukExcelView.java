package com.jeiglobal.hk.excel;

import java.net.*;
import java.util.*;

import javax.servlet.http.*;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.servlet.view.document.*;

import com.jeiglobal.hk.domain.manageInfo.*;

public class EmptyHakjukExcelView extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String userAgent = request.getHeader("User-Agent");
		String fileName = "emptyHakjuk.xls";
		
		if(userAgent.indexOf("MSIE") > -1){
		 fileName = URLEncoder.encode(fileName, "utf-8");
		}else{
		 fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
		}
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");


		List<String> columnName = new ArrayList<String>();
		columnName.add("번호");
		columnName.add("교실번호");
		columnName.add("교실명");
		columnName.add("관리요일");
		columnName.add("회원번호");
		columnName.add("회원명");
		columnName.add("과목");
		columnName.add("최종진도(년/월/주)");
		columnName.add("최종진도");
		
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFSheet sheet = createFirstSheet(workbook);
		createColumnLabel(sheet, cellStyle, columnName);
		
		List<EmptyHakjukInfo> dataList = (List<EmptyHakjukInfo>)model.get("dataList");
		for(int i=0; i <= dataList.size()-1; i++){
		 createPageRow(sheet, dataList.get(i), i, cellStyle);
		}
	}

	private void createPageRow(HSSFSheet sheet, EmptyHakjukInfo emptyHakjukInfo, int rowNum, HSSFCellStyle cellStyle) {
		// TODO Auto-generated method stub
		 HSSFRow row = sheet.createRow(rowNum + 1);
		  
		  HSSFCell cell = row.createCell(0);
		  cell.setCellValue(rowNum + 1);
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(1);
		  cell.setCellValue(emptyHakjukInfo.getsKey()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(2);
		  cell.setCellValue(emptyHakjukInfo.getsName()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(3);
		  cell.setCellValue(emptyHakjukInfo.getYoilNM()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(4);
		  cell.setCellValue(emptyHakjukInfo.getMkey()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(5);
		  cell.setCellValue(emptyHakjukInfo.getmFstName()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(6);
		  cell.setCellValue(emptyHakjukInfo.getSubj()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(7);
		  cell.setCellValue((emptyHakjukInfo.getFinalYMW()!=null&&!emptyHakjukInfo.getFinalYMW().isEmpty())?
						  emptyHakjukInfo.getFinalYMW().substring(0, 4)+"/"+emptyHakjukInfo.getFinalYMW().substring(4, 6)
						  +"/"+emptyHakjukInfo.getFinalYMW().subSequence(6, 7):""); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(8);
		  cell.setCellValue(emptyHakjukInfo.getFinalJindo()); 
		  cell.setCellStyle(cellStyle);
	}

	private void createColumnLabel(HSSFSheet sheet, HSSFCellStyle cellStyle, List<String> columnName) {
		// TODO Auto-generated method stub
		HSSFRow firstRow = sheet.createRow(0);
		  
		HSSFCell cell = null;
		for (int i = 0; i < columnName.size(); i++) {
			cell = firstRow.createCell(i);
			cell.setCellValue(columnName.get(i));
			cell.setCellStyle(cellStyle);
		}
	}

	private HSSFSheet createFirstSheet(HSSFWorkbook workbook) {
		// TODO Auto-generated method stub
		HSSFSheet sheet = workbook.createSheet();
		  workbook.setSheetName(0, "학적 미발생 회원");
		  sheet.setColumnWidth(0, 256*5);
		  sheet.setColumnWidth(1, 256*10);
		  sheet.setColumnWidth(2, 256*20);
		  sheet.setColumnWidth(3, 256*10);
		  sheet.setColumnWidth(4, 256*15);
		  sheet.setColumnWidth(5, 256*20);
		  sheet.setColumnWidth(6, 256*8);
		  sheet.setColumnWidth(7, 256*20);
		  sheet.setColumnWidth(8, 256*15);
		  sheet.setDefaultRowHeightInPoints(20);
		  return sheet;
	}

}
