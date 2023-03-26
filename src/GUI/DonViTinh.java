package GUI;

import DAO.DonViTinhDAO;
import component.IntegratedSearch;
import component.MainFunction;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import component.PanelBorderRadius;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DonViTinh extends JPanel implements MouseListener {

    PanelBorderRadius main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    // Khai báo array líst danh sách
    ArrayList<DTO.DonViTinh> listDv = DonViTinhDAO.getInstance().selectAll();
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);

    Color BackgroundColor = new Color(240, 247, 250);
    private DefaultTableModel tblModel;

    private void initComponent() {
        //Set model table
        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tableSanPham.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        tableSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14));
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã đơn vị tính", "Tên đơn vị tính"};
        tblModel.setColumnIdentifiers(header);
        tableSanPham.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableSanPham);

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        // pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4 chỉ để thêm contentCenter ở giữa mà contentCenter không bị dính sát vào các thành phần khác
        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 20));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 20));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(20, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(20, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(20, 20));
        this.add(contentCenter, BorderLayout.CENTER);

        // functionBar là thanh bên trên chứa các nút chức năng như thêm xóa sửa, và tìm kiếm
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2));
        functionBar.setBorder(new EmptyBorder(2, 2, 2, 2));

        mainFunction = new MainFunction(this);
        //Add Event MouseListener
        mainFunction.btnAdd.addMouseListener(this);
        mainFunction.btnEdit.addMouseListener(this);
        mainFunction.btnDetail.addMouseListener(this);
        mainFunction.btnDelete.addMouseListener(this);
        mainFunction.btnXuatExcel.addMouseListener(this);
        mainFunction.btnNhapExcel.addMouseListener(this);

        functionBar.add(mainFunction);

        search = new IntegratedSearch(new String[]{"Tất cả"});
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                searchDonVi();
            }
        });
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);

        main.add(scrollTableSanPham);

    }

    public DonViTinh() {
        initComponent();
        tableSanPham.setDefaultEditor(Object.class, null);
        loadDataTalbe();

    }

    // Function load data table
    public void loadDataTalbe() {
        tblModel.setRowCount(0);
        for (DTO.DonViTinh donViTinh : listDv) {
            tblModel.addRow(new Object[]{
                donViTinh.getMaDVT(), donViTinh.getTenDVT()
            });
        }
    }

    public void loadSearch(ArrayList<DTO.DonViTinh> result) {
        tblModel.setRowCount(0);
        for (DTO.DonViTinh donViTinh : result) {
            tblModel.addRow(new Object[]{
                donViTinh.getMaDVT(), donViTinh.getTenDVT()
            });
        }
    }

    public int getAutoIncrement() {
        int index = 1;
        for (DTO.DonViTinh donViTinh : listDv) {
            if (donViTinh.getMaDVT() == index) {
                index++;
            }
        }
        return index;
    }

    public String getDVTtoTb() {
        int row = tableSanPham.getSelectedRow();
        return tableSanPham.getModel().getValueAt(row, 1).toString();
    }

    public String getId() {
        int row = tableSanPham.getSelectedRow();
        return tableSanPham.getModel().getValueAt(row, 0).toString();
    }

    public void addDVT(DTO.DonViTinh dvt) {
        listDv.add(dvt);
        loadDataTalbe();
    }

    public void updateDVT(DTO.DonViTinh dvt) {
        System.out.println(dvt);
        for (DTO.DonViTinh donViTinh : listDv) {
            if (donViTinh.getMaDVT() == dvt.getMaDVT()) {
                donViTinh.setTenDVT(dvt.getTenDVT());
            }
        }
        loadDataTalbe();
    }

    public void deleteDVT(DTO.DonViTinh dvt) {
        int index = -1;
        for (int i = 0; i < listDv.size(); i++) {
            if (listDv.get(i).getMaDVT() == dvt.getMaDVT()) {
                index = i;
            }
        }
        listDv.remove(index);
        loadDataTalbe();
    }

    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void exportExcel() {
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();
            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("Đơn vị tính");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tableSanPham.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tableSanPham.getColumnName(i));
                }

                for (int j = 0; j < tableSanPham.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < tableSanPham.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (tableSanPham.getValueAt(j, k) != null) {
                            cell.setCellValue(tableSanPham.getValueAt(j, k).toString());
                        }

                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importExcel() {
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJTableImport = null;
        ArrayList<DTO.DonViTinh> listExcel = new ArrayList<DTO.DonViTinh>();
        JFileChooser jf = new JFileChooser();
        int result = jf.showOpenDialog(null);
        jf.setDialogTitle("Open file");
        Workbook workbook = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = jf.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelJTableImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);
                    int id = getAutoIncrement();
                    String tenDvt = excelRow.getCell(0).getStringCellValue();
                    DTO.DonViTinh dv = new DTO.DonViTinh(id, tenDvt);
                    listDv.add(dv);
                    listExcel.add(dv);
                    tblModel.setRowCount(0);
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Lỗi đọc file");
            } catch (IOException ex) {
                System.out.println("Lỗi đọc file");
            }
        }

        for (DTO.DonViTinh donViTinh : listExcel) {
            DonViTinhDAO.getInstance().insert(donViTinh);
        }
        loadDataTalbe();
    }

    public void searchDonVi() {
        String content_search = search.txtSearchForm.getText();
        if (content_search.length() != 0) {
            ArrayList<DTO.DonViTinh> result = new ArrayList<>();
            for (var dv : listDv) {
                if (dv.getTenDVT().toLowerCase().contains(content_search.toLowerCase())) {
                    result.add(dv);
                }
            }
            loadSearch(result);
        } else {
            loadDataTalbe();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == mainFunction.btnAdd) {
            System.out.println("Da bat duoc su kien");
            DonViTinhDialog dvtDialog = new DonViTinhDialog(this, owner, "Thêm đơn vị tính", true, "create");
        } else if (e.getSource() == mainFunction.btnEdit) {
            if (tableSanPham.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn đơn vị tính cần sửa");
            } else {
                DonViTinhDialog dvtDialog = new DonViTinhDialog(this, owner, "Chỉnh sửa đơn vị tính", true, "update", getDVTtoTb(), getId(), 1);
            }
        } else if (e.getSource() == mainFunction.btnDelete) {
            if (tableSanPham.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn đơn vị tính cần xóa");
            } else {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa đơn vị tính :)!", "Xóa đơn vị tính",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    DonViTinhDAO.getInstance().delete(getId());
                    DTO.DonViTinh d = new DTO.DonViTinh(Integer.parseInt(getId()), getDVTtoTb());
                    deleteDVT(d);
                }
            }
        } else if (e.getSource() == mainFunction.btnDetail) {
            if (tableSanPham.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn đơn vị tính cần sửa");
            } else {
                DonViTinhDialog dvtDialog = new DonViTinhDialog(this, owner, "Chi tiêt đơn vị tính", true, "update", getDVTtoTb(), getId(), 2);
            }
        }
        if (e.getSource() == mainFunction.btnXuatExcel) {
            exportExcel();
        }

        if (e.getSource() == mainFunction.btnNhapExcel) {
            importExcel();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static class ProductForm {

        public ProductForm() {
        }
    }
}
