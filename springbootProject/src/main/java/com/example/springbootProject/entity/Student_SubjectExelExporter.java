package com.example.springbootProject.entity;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Student_SubjectExelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Students_Subjects> students_subjectsList;

    public Student_SubjectExelExporter(List<Students_Subjects> listUsers) {
        this.students_subjectsList = listUsers;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("ข้อมูลการลงทะเบียน");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "รหัสวิชา", style);
        createCell(row, 1, "ชื่อวิชา", style);
        createCell(row, 2, "เวลาเรียน", style);
        createCell(row, 3, "อาจารย์ที่สอน", style);
        createCell(row, 4, "นักเรียน", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Students_Subjects obj : students_subjectsList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, obj.getSubject().getSubject_id().toString(), style);
            createCell(row, columnCount++, obj.getSubject().getSubject_name(), style);
            createCell(row, columnCount++, obj.getSubject().getStart_date().toString()+" - "+obj.getSubject().getEnd_date(), style);
            createCell(row, columnCount++, obj.getSubject().getTeacher().getFirst_name()+"  "+obj.getSubject().getTeacher().getLast_name(), style);
            createCell(row, columnCount++, obj.getStudent().getFirst_name()+"  "+obj.getStudent().getLast_name(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}

