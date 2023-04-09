package GUI.Panel;

import GUI.Dialog.ThuongHieuDialog;
import BUS.ThuongHieuBUS;
import DAO.ThuongHieuDAO;
import DTO.ThuongHieuDTO;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
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

public final class ThuongHieu extends JPanel implements ActionListener{

    PanelBorderRadius main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    JTable tableLoaiHang;
    JScrollPane scrollTableLoaiHang;
    MainFunction mainFunction;
    IntegratedSearch search;
    DefaultTableModel tblModel;
    public ThuongHieuBUS lhBUS = new ThuongHieuBUS();
    public ArrayList<ThuongHieuDTO> listLH = lhBUS.getAll();

    Color BackgroundColor = new Color(240, 247, 250);
    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        tableLoaiHang = new JTable();
        scrollTableLoaiHang = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã thương hiệu", "Tên thương hiệu"};
        tblModel.setColumnIdentifiers(header);
        tableLoaiHang.setModel(tblModel);
        scrollTableLoaiHang.setViewportView(tableLoaiHang);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableLoaiHang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableLoaiHang.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

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
        
        // functionBar là thanh bên trên chứa các nút chức năng như thêm xóa sửa, và tìm kiếm
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        
        mainFunction = new MainFunction();
        mainFunction = new MainFunction();
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
            @Override
            public void keyReleased(KeyEvent e) {
                String txt = search.txtSearchForm.getText();
                listLH = lhBUS.search(txt);
                loadDataTalbe(listLH);
            }
        });

        search.btnReset.addActionListener((ActionEvent e) -> {
            search.txtSearchForm.setText("");
            listLH = lhBUS.getAll();
            loadDataTalbe(listLH);
        });
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);

        main.add(scrollTableLoaiHang);


    }

    public ThuongHieu() {
        initComponent();
        tableLoaiHang.setDefaultEditor(Object.class, null);
        loadDataTalbe(listLH);
    }
    public void loadDataTalbe(ArrayList<ThuongHieuDTO> result) {
        tblModel.setRowCount(0);
        for (DTO.ThuongHieuDTO lh : result) {
            tblModel.addRow(new Object[]{
                lh.getMathuonghieu(), lh.getTenthuonghieu()
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
            ThuongHieuDialog lhDialog = new ThuongHieuDialog(this, owner, "Thêm thương hiệu", true, "create");
        } else if (e.getSource() == mainFunction.btnEdit) {
            int index = tableLoaiHang.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn đơn vị tính cần sửa");
            } else {
                System.out.println(listLH.get(index));
                ThuongHieuDialog lhDialog = new ThuongHieuDialog(this, owner, "Chỉnh sửa thương hiệu", true, "update", listLH.get(index));
            }
        } else if (e.getSource() == mainFunction.btnDelete) {
            int index = tableLoaiHang.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn thương hiệu cần xóa");
            } else {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa thương hiệu :)!", "Xóa thương hiệu",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    JOptionPane.showMessageDialog(null, "Đã xóa "+listLH.get(index).getTenthuonghieu());
                    lhBUS.delete(listLH.get(index));
                    loadDataTalbe(listLH);
                }
            }
        } else if (e.getSource() == mainFunction.btnDetail) {
            int index = tableLoaiHang.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn thương hiệu cần xem");
            } else {
                ThuongHieuDialog lhDialog = new ThuongHieuDialog(this, owner, "Chi tiêt thương hiệu", true, "view", listLH.get(index));
            }
        }
    }
}
