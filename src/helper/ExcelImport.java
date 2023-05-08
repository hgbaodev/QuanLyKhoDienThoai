/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

/**
 *
 * @author andin
 */
import config.JDBCUtil;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelImport {
    public static <T> void importExcel(File file, T dto, String tableName, ArrayList<T> currentObjects) throws Exception {
        Connection con = (Connection) JDBCUtil.getConnection();
        try (FileInputStream fis = new FileInputStream(file); Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                // Skip header row
                if (row.getRowNum() == 0) {
                    continue;
                }
                // Map Excel row to DTO
                if(row.getRowNum()>0){
                    Row headerRow = sheet.getRow(0);
                    mapRowToDto(row, dto,headerRow);
                }
                // Check for duplicates
                if (containsDuplicate(dto, currentObjects)) {
                    continue;
                }
                // Insert DTO into database
                insertDtoIntoDatabase(dto, tableName, con);
            }
        }
        JDBCUtil.closeConnection(con);
    }

    private static <T> void mapRowToDto(Row row, T dto, Row headerRow) throws Exception {
        int i = 0;
        for (Cell cell : row) {            
            System.out.println(cell.getRowIndex() +":"+ cell.getColumnIndex());
            String head = getFieldNameFromHeader(headerRow, i).substring(0, 1).toUpperCase()+getFieldNameFromHeader(headerRow, i).substring(1);
            String setterName = "set" + head;
            Object value = getCellValue(cell);
            //Method []mt = dto.getClass().getMethods();
            Method setter = dto.getClass().getMethod(setterName, value.getClass());
            setter.invoke(dto, value);
            i++;           
        }

}
  

    private static String getFieldNameFromHeader(Row header, int col) {
        Cell cell = header.getCell(col);
        return cell.getStringCellValue().toLowerCase();
    }

    private static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return (int)Math.round(cell.getNumericCellValue());
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return null;
        }
    }

    private static <T> boolean containsDuplicate(T dto, ArrayList<T> currentObjects) {
        for (T obj : currentObjects) {
            if (dto.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    private static <T> void insertDtoIntoDatabase(T dto, String tableName, Connection connection)
            throws SQLException, Exception {
        // Build SQL query
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("INSERT INTO ").append(tableName).append(" (");
        // Get field names from DTO
        ArrayList<String> fieldNames = getFieldNames(dto);
        for (String fieldName : fieldNames) {
            sqlBuilder.append(fieldName).append(", ");
        }
        sqlBuilder.delete(sqlBuilder.length() - 2, sqlBuilder.length());
        sqlBuilder.append(") VALUES (");
        // Get field values from DTO
        ArrayList<Object> fieldValues = getFieldValues(dto);
        for (Object fieldValue : fieldValues) {
            sqlBuilder.append("?, ");
        }
        sqlBuilder.delete(sqlBuilder.length() - 2, sqlBuilder.length());
        sqlBuilder.append(")");
        String sql = sqlBuilder.toString();
        // Prepare statement and set parameters
        PreparedStatement statement = connection.prepareStatement(sql);
        int i = 1;
        for (Object fieldValue : fieldValues) {
            statement.setObject(i, fieldValue);
            i++;
        }
        // Execute statement
        statement.executeUpdate();
    }

    private static <T> ArrayList<String> getFieldNames(T dto) {
        ArrayList<String> fieldNames = new ArrayList<>();
        for (Field field : dto.getClass().getDeclaredFields()) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    private static <T> ArrayList<Object> getFieldValues(T dto) throws Exception {
        ArrayList<Object> fieldValues = new ArrayList<>();
        for (Field field : dto.getClass().getDeclaredFields()) {
            String getterName = "get" + field.getName().substring(0, 1).toUpperCase()
                    + field.getName().substring(1);
            Method getter = dto.getClass().getMethod(getterName);
            Object value = getter.invoke(dto);
            fieldValues.add(value);
        }
        return fieldValues;
    }
}
