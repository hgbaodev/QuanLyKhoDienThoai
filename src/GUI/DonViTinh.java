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
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class DonViTinh extends JPanel {

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
        // Set sự kiện
        mainFunction.btnAdd.addActionListener((ActionEvent evt) -> {
            DonViTinhDialog dvtDialog = new DonViTinhDialog(this, owner, "Thêm đơn vị tính", true, "create");
        });
        mainFunction.btnEdit.addActionListener((ActionEvent evt) -> {
            if (tableSanPham.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn đơn vị tính cần sửa");
            } else {
                DonViTinhDialog dvtDialog = new DonViTinhDialog(this, owner, "Chỉnh sửa đơn vị tính", true, "update",getDVTtoTb(),getId());
            }
        });

        mainFunction.btnDetail.addActionListener((ActionEvent evt) -> {
            JOptionPane.showMessageDialog(null, "Xen chi tiết dvt");
        });

        mainFunction.btnDelete.addActionListener((ActionEvent evt) -> {
            if (tableSanPham.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn đơn vị tính cần xóa");
            } else {
               
                
            }
        });

        functionBar.add(mainFunction);

        search = new IntegratedSearch();
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
        System.out.println(getAutoIncrement());
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

    public int getAutoIncrement() {
        int index = 1;
        for (DTO.DonViTinh donViTinh : listDv) {
            if (donViTinh.getMaDVT() == index) {
                index++;
            }
        }
        return index;
    }

    public void addDVT(DTO.DonViTinh dvt) {
        listDv.add(dvt);
        loadDataTalbe();
    }
    
    public String getDVTtoTb(){
        int row = tableSanPham.getSelectedRow();
        return tableSanPham.getModel().getValueAt(row, 1).toString();
    }
    
    public String getId(){
        int row = tableSanPham.getSelectedRow();
        return tableSanPham.getModel().getValueAt(row, 0).toString();
    }
}
