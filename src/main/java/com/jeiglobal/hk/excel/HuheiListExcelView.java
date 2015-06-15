package com.jeiglobal.hk.excel;

import java.net.*;
import java.util.*;

import javax.servlet.http.*;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.servlet.view.document.*;

import com.jeiglobal.hk.domain.manageInfo.*;
/**
 * 휴회자 리스트 엑셀 다운로드
 * @author JSY
 *
 */
public class HuheiListExcelView extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String userAgent = request.getHeader("User-Agent");
		String fileName = "huheiList.xls";
		
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
		columnName.add("회원번호");
		columnName.add("회원명");
		columnName.add("상태");
		columnName.add("과목");
		columnName.add("생년월일");
		columnName.add("학년");
		columnName.add("퇴회일자");
		columnName.add("전화번호");
		columnName.add("주소");
		columnName.add("퇴회사유");
		columnName.add("진행과목");
		
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFSheet sheet = createFirstSheet(workbook);
		createColumnLabel(sheet, cellStyle, columnName);
		
		List<HuheiMemberInfo> dataList = (List<HuheiMemberInfo>)model.get("dataList");
		for(int i=0; i <= dataList.size()-1; i++){
		 createPageRow(sheet, dataList.get(i), i, cellStyle);
		}
	}
	/**
	 * 엑셀 한 행 만들기
	 * @param sheet
	 * @param huheiMemberInfo
	 * @param rowNum
	 * @param cellStyle
	 */
	private void createPageRow(HSSFSheet sheet, HuheiMemberInfo huheiMemberInfo, int rowNum, HSSFCellStyle cellStyle) {
		// TODO Auto-generated method stub
		 HSSFRow row = sheet.createRow(rowNum + 1);
		  
		  HSSFCell cell = row.createCell(0);
		  cell.setCellValue(rowNum + 1);
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(1);
		  cell.setCellValue(huheiMemberInfo.getEmpKey()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(2);
		  cell.setCellValue(huheiMemberInfo.getEmpName()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(3);
		  cell.setCellValue(huheiMemberInfo.getmKey()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(4);
		  cell.setCellValue(huheiMemberInfo.getmFstName()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(5);
		  cell.setCellValue(huheiMemberInfo.getStateName()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(6);
		  cell.setCellValue(huheiMemberInfo.getSubj()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(7);
		  cell.setCellValue(huheiMemberInfo.getBirthYMD()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(8);
		  cell.setCellValue(huheiMemberInfo.getGradeName()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(9);
		  cell.setCellValue(huheiMemberInfo.getHuheiYMD()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(10);
		  cell.setCellValue(huheiMemberInfo.getTel()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(11);
		  cell.setCellValue(huheiMemberInfo.getAddr()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(12);
		  cell.setCellValue(huheiMemberInfo.getHuheiSayuName()); 
		  cell.setCellStyle(cellStyle);
		  cell = row.createCell(13);
		  cell.setCellValue(huheiMemberInfo.getJin()); 
		  cell.setCellStyle(cellStyle);
	}
	/**
	 * 컬럼 라벨 만들기
	 * @param sheet
	 * @param cellStyle
	 * @param columnName
	 */
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
	/**
	 * 엑셀 시트 생성
	 * @param workbook
	 * @return
	 */
	private HSSFSheet createFirstSheet(HSSFWorkbook workbook) {
		// TODO Auto-generated method stub
		HSSFSheet sheet = workbook.createSheet();
		  workbook.setSheetName(0, "휴회자 리스트");
		  sheet.setColumnWidth(0, 256*5);
		  sheet.setColumnWidth(1, 256*10);
		  sheet.setColumnWidth(2, 256*20);
		  sheet.setColumnWidth(3, 256*15);
		  sheet.setColumnWidth(4, 256*20);
		  sheet.setColumnWidth(5, 256*8);
		  sheet.setColumnWidth(6, 256*8);
		  sheet.setColumnWidth(7, 256*15);
		  sheet.setColumnWidth(8, 256*20);
		  sheet.setColumnWidth(9, 256*15);
		  sheet.setColumnWidth(10, 256*15);
		  sheet.setColumnWidth(11, 256*40);
		  sheet.setColumnWidth(12, 256*30);
		  sheet.setColumnWidth(13, 256*20);
		  sheet.setDefaultRowHeightInPoints(20);
		  return sheet;
	}

}
