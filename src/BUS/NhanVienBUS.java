/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import GUI.Panel.NhanVien;
import GUI.Dialog.NhanVienDialog;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author robot
 */
public class NhanVienBUS implements ActionListener, DocumentListener {

    public GUI.Panel.NhanVien nv;
    private JTextField textField;
    public ArrayList<DTO.NhanVienDTO> listNv = NhanVienDAO.getInstance().selectAll();

    public NhanVienBUS() {
    }
    
    public NhanVienBUS(NhanVien nv) {
        this.nv = nv;
    }

    public NhanVienBUS(JTextField textField, NhanVien nv) {
        this.textField = textField;
        this.nv = nv;
    }

    public ArrayList<DTO.NhanVienDTO> getAll() {
        return this.listNv;
    }
    
    public int getIndexById(int manv) {
        int i = 0;
        int vitri = -1;
        int size = this.listNv.size();
        while(i < size && vitri == -1) {
            if(this.listNv.get(i).getManv() == manv) {
                vitri = i;
            } else i++;
        }
        return vitri;
    }
    
    public String getNameById(int manv) {
        return this.listNv.get(getIndexById(manv)).getHoten();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();
        System.out.println("Bạn đang nhấn nút " + btn);

        switch (btn) {
            case "THÊM" -> {
                NhanVienDialog nvthem = new NhanVienDialog(this, nv.owner, true, "Thêm nhân viên", "create");
            }
            case "SỬA" -> {
                int index = nv.getRow();
                if (index < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần sửa");
                } else {
                    NhanVienDialog nvsua = new NhanVienDialog(this, nv.owner, true, "Sửa nhân viên", "update", nv.getNhanVien());
                }
            }
            case "XÓA" -> {
                if (nv.getRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa");
                } else {
                    System.out.println(nv.getNhanVien());
                    deleteNv(nv.getNhanVien());
                }
            }
            case "CHI TIẾT" -> {
                if (nv.getRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa");
                } else {
                    NhanVienDialog nvsua = new NhanVienDialog(this, nv.owner, true, "Xem nhân viên", "detail", nv.getNhanVien());
                }
            }
            case "NHẬP EXCEL" -> {
                
            }
            case "XUẤT EXCEL" -> {
                String[] header = new String[]{"MãNV","Tên nhân viên","Email nhân viên","Số điên thoại","Giới tính","Ngày sinh"};
                exportExcel(listNv,header);
            }

        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String text = textField.getText();
        if(text.length() == 0){
            nv.loadDataTalbe(listNv);
        } else {
            ArrayList<NhanVienDTO> listSearch = search(text);
            searchLoadTable(listSearch);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String text = textField.getText();
        if(text.length() == 0){
            nv.loadDataTalbe(listNv);
        } else {
            ArrayList<NhanVienDTO> listSearch = search(text);
            searchLoadTable(listSearch);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
//        System.out.println("Text field changed: " + textField.getText());
    }

    public void insertNv(NhanVienDTO nv) {
        listNv.add(nv);
    }
    
    public void updateNv(int index, NhanVienDTO nv){
        listNv.set(index, nv);
    }

    public int getIndex() {
        return nv.getRow();
    }

    public void deleteNv(NhanVienDTO nv) {
        System.out.println("Vi trí của nhân viên: " + listNv.indexOf(nv));
        NhanVienDAO.getInstance().delete(nv.getManv() + "");
        listNv.removeIf(n -> (n.getManv() == nv.getManv()));
        loadTable();
    }

    public void loadTable() {
        nv.loadDataTalbe(listNv);
    }
    
    public void searchLoadTable(ArrayList<NhanVienDTO> list){
        nv.loadDataTalbe(list);
    }
    
    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public void exportExcel(ArrayList<NhanVienDTO> list, String[] header) {
        try {
            if(list.size()>0){
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(nv.owner);
            File saveFile = jFileChooser.getSelectedFile();
            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("Nhân viên");
                
                writeHeader(header,sheet,0);
                int rowIndex = 1;
                for (NhanVienDTO nv : list) {
                    Row row = sheet.createRow(rowIndex++);
                    writeNhanVien(nv,row);
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void writeHeader(String[] list, Sheet sheet, int rowIndex) {
        CellStyle cellStyle = createStyleForHeader(sheet);
        Row row = sheet.createRow(rowIndex);
        Cell cell ;
        for(int i = 0;i < list.length;i++){
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list[i]);
            sheet.autoSizeColumn(i);
        }
    }
    
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman"); 
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color
 
        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
    
    private static void writeNhanVien(NhanVienDTO nv, Row row) {
        CellStyle cellStyleFormatNumber = null;
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");
             
            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
        Cell cell = row.createCell(0);
        cell.setCellValue(nv.getManv());
 
        cell = row.createCell(1);
        cell.setCellValue(nv.getHoten());
 
        cell = row.createCell(2);
        cell.setCellValue(nv.getEmail());
 
        cell = row.createCell(3);
        cell.setCellValue(nv.getSdt());
         
        cell = row.createCell(4);
        cell.setCellValue(nv.getGioitinh()==1?"Nam":"Nữ");
        
        cell = row.createCell(5);
        cell.setCellValue(""+nv.getNgaysinh());
    }

//    public void importExcel() {
//        File excelFile;
//        FileInputStream excelFIS = null;
//        BufferedInputStream excelBIS = null;
//        XSSFWorkbook excelJTableImport = null;
//        ArrayList<DTO.DonViTinhDTO> listExcel = new ArrayList<DTO.DonViTinhDTO>();
//        JFileChooser jf = new JFileChooser();
//        int result = jf.showOpenDialog(null);
//        jf.setDialogTitle("Open file");
//        Workbook workbook = null;
//        if (result == JFileChooser.APPROVE_OPTION) {
//            try {
//                excelFile = jf.getSelectedFile();
//                excelFIS = new FileInputStream(excelFile);
//                excelBIS = new BufferedInputStream(excelFIS);
//                excelJTableImport = new XSSFWorkbook(excelBIS);
//                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
//                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
//                    XSSFRow excelRow = excelSheet.getRow(row);
////                    int id = getAutoIncrement();
//                    String tenDvt = excelRow.getCell(0).getStringCellValue();
////                    DTO.DonViTinhDTO dv = new DTO.DonViTinhDTO(id, tenDvt);
////                    dvtBUS.getAll().add(dv);
////                    listExcel.add(dv);
//                    tblModel.setRowCount(0);
//                }
//            } catch (FileNotFoundException ex) {
//                System.out.println("Lỗi đọc file");
//            } catch (IOException ex) {
//                System.out.println("Lỗi đọc file");
//            }
//        }
//
//        for (DTO.DonViTinhDTO donViTinh : listExcel) {
//            MauSacSanPhamDAO.getInstance().insert(donViTinh);
//        }
//        loadDataTalbe(listdvt);
//    }
    public ArrayList<NhanVienDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        for(NhanVienDTO i : this.listNv) {
           if(i.getHoten().toLowerCase().contains(text) || i.getEmail().toLowerCase().contains(text)
                   || i.getSdt().toLowerCase().contains(text)){
               result.add(i);
           }
        }
        return result;
    }
    
}
