package GUI.Panel;

import BUS.NhanVienBUS;
import DAO.DonViTinhDAO;
import DAO.NhanVienDAO;
import DTO.DonViTinhDTO;
import DTO.NhanVienDTO;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NhanVien extends JPanel {

   public JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    NhanVienBUS nvBus = new NhanVienBUS(this);
    PanelBorderRadius box1, box2, main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableNhanVien;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    JLabel lbl1, lblImage;
    ArrayList<DTO.NhanVienDTO> listnv = nvBus.getAll();

    Color BackgroundColor = new Color(240, 247, 250);
    private DefaultTableModel tblModel;

    private void initComponent() {
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
        functionBar.setPreferredSize(new Dimension(0, 140));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(20, 20, 20, 20));

        mainFunction = new MainFunction();
        functionBar.add(mainFunction);

        search = new IntegratedSearch(new String[]{"Tất cả","Họ tên","Email"});
        functionBar.add(search);
        contentCenter.add(functionBar, BorderLayout.NORTH);

       
        mainFunction.btnAdd.addActionListener(nvBus);
        mainFunction.btnDelete.addActionListener(nvBus);
        mainFunction.btnDetail.addActionListener(nvBus);
        mainFunction.btnEdit.addActionListener(nvBus);
        mainFunction.btnNhapExcel.addActionListener(nvBus);
        mainFunction.btnXuatExcel.addActionListener(nvBus);
        search.btnReset.addActionListener(nvBus);
        search.cbxChoose.addActionListener(nvBus);
        search.txtSearchForm.getDocument().addDocumentListener(new NhanVienBUS(search.txtSearchForm,this));
        
        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);

        tableNhanVien = new JTable();
        scrollTableSanPham = new JScrollPane();

        tableNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14));
        tableNhanVien = new JTable();
        tableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"MNV","Họ tên","Giới tính","Ngày Sinh","SDT","Email"};
        
        tblModel.setColumnIdentifiers(header);
        tableNhanVien.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableNhanVien);
        main.add(scrollTableSanPham);
    }
    
    

    public NhanVien() {
        initComponent();
        tableNhanVien.setDefaultEditor(Object.class, null);
        loadDataTalbe(listnv);
    }
    
    public int getRow(){
        return tableNhanVien.getSelectedRow();
    }
    
    public DTO.NhanVienDTO getNhanVien(){
        return listnv.get(tableNhanVien.getSelectedRow());
    }
    
    
    
    public void loadDataTalbe(ArrayList<DTO.NhanVienDTO> list) {
        tblModel.setRowCount(0);
        for (DTO.NhanVienDTO nhanVien : list) {
            tblModel.addRow(new Object[]{
                nhanVien.getManv(),nhanVien.getHoten(),nhanVien.getGioitinh()==1?"Nam":"Nữ",nhanVien.getNgaysinh(),nhanVien.getSdt(),nhanVien.getEmail()
            });
        }
    }
    
    

}
