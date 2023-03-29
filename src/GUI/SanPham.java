package GUI;

import BUS.SanPhamBUS;
import DAO.SanPhamDAO;
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
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SanPham extends JPanel implements ActionListener {

    PanelBorderRadius main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    DefaultTableModel tblModel;
    SanPhamBUS spBUS = new SanPhamBUS();
    ArrayList<DTO.SanPham> listSP = spBUS.getAll();

    Color BackgroundColor = new Color(240, 247, 250);
    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tableSanPham.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        tableSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14));
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Xuất xứ", "Giá nhập", "Giá bán", "Hình ảnh", "Mã đơn vị tính", "Mã loại hàng", "Mã khu vực"};
        tblModel.setColumnIdentifiers(header);
        tableSanPham.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableSanPham);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

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
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(20, 20));
        this.add(contentCenter, BorderLayout.CENTER);
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2));
        functionBar.setBorder(new EmptyBorder(2, 2, 2, 2));
        mainFunction = new MainFunction();
        mainFunction = new MainFunction(this);
        //Add Event MouseListener
        mainFunction.btnAdd.addActionListener(this);
        mainFunction.btnEdit.addActionListener(this);
        mainFunction.btnDetail.addActionListener(this);
        mainFunction.btnDelete.addActionListener(this);
        mainFunction.btnXuatExcel.addActionListener(this);
        mainFunction.btnNhapExcel.addActionListener(this);
        functionBar.add(mainFunction);

        search = new IntegratedSearch(new String[]{"Tất cả"});
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String txt = search.txtSearchForm.getText();
                listSP = spBUS.search(txt);
                loadDataTalbe(listSP);
            }

        });

        search.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search.txtSearchForm.setText("");
                listSP = spBUS.getAll();
                loadDataTalbe(listSP);
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

    public SanPham() {
        initComponent();
        tableSanPham.setDefaultEditor(Object.class, null);
        loadDataTalbe(listSP);
    }

    public void loadDataTalbe(ArrayList<DTO.SanPham> result) {
        tblModel.setRowCount(0);
        for (DTO.SanPham sp : result) {
            tblModel.addRow(new Object[]{
                sp.getMasp(), sp.getTensp(), sp.getXuatxu(), sp.getGianhap(), sp.getGiaban(), sp.getHinhanh(), sp.getMaDVT(), sp.getMaloaihang(), sp.getMakhuvuc()
            });
        }
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
                Sheet sheet = wb.createSheet("SẢN PHẨM");
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
        ArrayList<DTO.SanPham> listExcel = new ArrayList<DTO.SanPham>();
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
//                    int id = getAutoIncrement();
//                    String tenSP = excelRow.getCell(0).getStringCellValue();
//                    DTO.SanPham dv = new DTO.SanPham(id, tenLH);
//                    spBUS.getAll().add(dv);
//                    listExcel.add(dv);
                    tblModel.setRowCount(0);
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Lỗi đọc file");
            } catch (IOException ex) {
                System.out.println("Lỗi đọc file");
            }
        }

        for (DTO.SanPham sp : listExcel) {
            SanPhamDAO.getInstance().insert(sp);
        }
        loadDataTalbe(listExcel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btnAdd) {
            SanPhamDialog spDialog = new SanPhamDialog(this,owner,"Thêm sản phẩm mới",true,"create");
        } else if (e.getSource() == mainFunction.btnEdit) {
            int index = tableSanPham.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn đơn vị tính cần sửa");
            } else {
                SanPhamDialog spDialog = new SanPhamDialog(this, owner, "Chỉnh sửa sản phẩm", true, "update", listSP.get(index));
            }
        } else if (e.getSource() == mainFunction.btnDelete) {
            int index = tableSanPham.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa");
            } else {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa Sản phẩm :)!", "Xóa sản phẩm",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    JOptionPane.showMessageDialog(null, listSP.get(index));
                    spBUS.delete(listSP.get(index));
                    loadDataTalbe(listSP);
                }
            }
        } else if (e.getSource() == mainFunction.btnDetail) {
            int index = tableSanPham.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn loại hàng cần xem");
            } else {
                SanPhamDialog spDialog = new SanPhamDialog(this, owner, "Chi tiêt sản phẩm", true, "view", listSP.get(index));
            }
        }
        if (e.getSource() == mainFunction.btnXuatExcel) {
            exportExcel();
        }

        if (e.getSource() == mainFunction.btnNhapExcel) {
            importExcel();
        }
    }
}
