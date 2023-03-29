package GUI;

import DTO.KhachHangDTO;
import DAO.KhachHangDAO;
import BUS.KhachHangBUS;
import component.IntegratedSearch;
import component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import component.PanelBorderRadius;
import dialog.KhachHangDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableCellRenderer;

public class KhachHang extends JPanel implements ActionListener {

    PanelBorderRadius box1, box2, main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    IntegratedSearch search;
    JLabel lbl1, lblImage;
    DefaultTableModel tblModel;
    public KhachHangBUS khachhangBUS = new KhachHangBUS();
    public ArrayList<KhachHangDTO> listkh = khachhangBUS.getAll();

    Color BackgroundColor = new Color(240, 247, 250);

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
        String[] header = new String[]{"Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại"};
        tblModel.setColumnIdentifiers(header);
        tableSanPham.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableSanPham);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

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

        mainFunction = new MainFunction(this);
        functionBar.add(mainFunction);

//        //Add Event MouseListener
        mainFunction.btnAdd.addActionListener(this);
        mainFunction.btnEdit.addActionListener(this);
        mainFunction.btnDetail.addActionListener(this);
        mainFunction.btnDelete.addActionListener(this);
        mainFunction.btnXuatExcel.addActionListener(this);
        mainFunction.btnNhapExcel.addActionListener(this);

        search = new IntegratedSearch(new String[]{"Tất cả"});
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String txt = search.txtSearchForm.getText();
                listkh = khachhangBUS.search(txt);
                loadDataTalbe(listkh);
            }
        });

        search.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search.txtSearchForm.setText("");
                listkh = khachhangBUS.getAll();
                loadDataTalbe(listkh);
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

    public KhachHang() {
        initComponent();
        tableSanPham.setDefaultEditor(Object.class, null);
        loadDataTalbe(listkh);
    }

    public void loadDataTalbe(ArrayList<KhachHangDTO> result) {
        tblModel.setRowCount(0);
        for (DTO.KhachHangDTO khachHang : result) {
            tblModel.addRow(new Object[]{
                khachHang.getMaKH(), khachHang.getHoten(), khachHang.getDiachi(), khachHang.getSdt()
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btnAdd) {
            KhachHangDialog khDialog = new KhachHangDialog(this, owner, "Thêm khách hàng", true, "create");
        } else if (e.getSource() == mainFunction.btnEdit) {
            int index = tableSanPham.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần sửa");
            } else {
                KhachHangDialog khDialog = new KhachHangDialog(this, owner, "Chỉnh sửa khách hàng", true, "update", listkh.get(index));

            }
        } else if (e.getSource() == mainFunction.btnDelete) {
            int index = tableSanPham.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần xóa");
            } else {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa khách hàng ?", "Xóa khách hàng",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    khachhangBUS.delete(listkh.get(index));
                    loadDataTalbe(listkh);
                }
            }
        } else if (e.getSource() == mainFunction.btnDetail) {
            int index = tableSanPham.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần xem");
            } else {
                KhachHangDialog khDialog = new KhachHangDialog(this, owner, "Xem khách hàng", true, "view", listkh.get(index));

            }
        }
        if (e.getSource() == mainFunction.btnXuatExcel) {
//            exportExcel();
        }

        if (e.getSource() == mainFunction.btnNhapExcel) {
//            importExcel();
        }
    }
}
