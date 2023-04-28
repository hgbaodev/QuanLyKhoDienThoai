package GUI.Panel;

import BUS.TaiKhoanBUS;
import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import GUI.Main;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import GUI.Dialog.ListNhanVien;
import GUI.Dialog.TaiKhoanDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TaiKhoan extends JPanel implements ActionListener, ItemListener {

    PanelBorderRadius main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableTaiKhoan;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    public JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    Color BackgroundColor = new Color(240, 247, 250);
    DefaultTableModel tblModel;
    public TaiKhoanBUS taiKhoanBus = new TaiKhoanBUS();
    ArrayList<TaiKhoanDTO> listTk = taiKhoanBus.getTaiKhoanAll();

    private Main m;

    public TaiKhoan(Main m) {
        this.m = m;
        initComponent();
        loadTable(listTk);
    }

    private void initComponent() {
        tableTaiKhoan = new JTable();
        tableTaiKhoan.setDefaultEditor(Object.class, null);
        scrollTableSanPham = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"MaNV", "Tên đăng nhập", "Nhóm quyền", "Trạng thái"};
        tblModel.setColumnIdentifiers(header);
        tableTaiKhoan.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableTaiKhoan);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        tableTaiKhoan.setDefaultRenderer(Object.class, centerRenderer);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableTaiKhoan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableTaiKhoan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        // pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4 chỉ để thêm contentCenter ở giữa mà contentCenter không bị dính sát vào các thành phần khác
        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 10));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 10));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(10, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(10, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        // functionBar là thanh bên trên chứa các nút chức năng như thêm xóa sửa, và tìm kiếm
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] action = {"create", "update", "delete", "detail", "import", "export"};
        mainFunction = new MainFunction(m.user.getManhomquyen(), "taikhoan", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }

        functionBar.add(mainFunction);
        search = new IntegratedSearch(new String[]{"Tất cả", "Mã nhân viên", "Username"});
        search.cbxChoose.addItemListener(this);
        functionBar.add(search);
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                listTk = taiKhoanBus.search(txt, type);
                loadTable(listTk);
            }
        });
        contentCenter.add(functionBar, BorderLayout.NORTH);

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
//        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);

        main.add(scrollTableSanPham);
    }

    public void loadTable(ArrayList<TaiKhoanDTO> list) {
        tblModel.setRowCount(0);
        for (TaiKhoanDTO taiKhoanDTO : list) {
            int tt = taiKhoanDTO.getTrangthai();
            String trangthaiString = "";
            switch (tt) {
                case 1 -> {
                    trangthaiString = "Hoạt động";
                }
                case 0 -> {
                    trangthaiString = "Ngưng hoạt động";
                }
                case -1 -> {
                    trangthaiString = "Đã xóa";
                }
            }
            tblModel.addRow(new Object[]{
                taiKhoanDTO.getManv(), taiKhoanDTO.getUsername(), taiKhoanBus.getNhomQuyenDTO(taiKhoanDTO.getManhomquyen() - 1).getTennhomquyen(), trangthaiString
            });
        }
//        tblModel.setRowCount(0);
//        for (DTO.ThuongHieuDTO lh : result) {
//            tblModel.addRow(new Object[]{
//                lh.getMathuonghieu(), lh.getTenthuonghieu()
//            });
//        }
    }

    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public int getRowSelected() {
        int index = tableTaiKhoan.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản");
        }
        return index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            ListNhanVien listNV = new ListNhanVien(this, owner, "Chọn tài khoản", true);
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = getRowSelected();
            if (index != -1) {
                TaiKhoanDialog add = new TaiKhoanDialog(this, owner, "Cập nhật tài khoản", true, "update", listTk.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa tài khoản :)!", "Xóa xóa tài khoản",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    TaiKhoanDAO.getInstance().delete(index + "");
                    TaiKhoanDTO tkDelete = listTk.get(index);
                    tkDelete.setTrangthai(-1);
                    taiKhoanBus.updateAcc(index, tkDelete);
                    loadTable(taiKhoanBus.getTaiKhoanAll());
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                TaiKhoanDialog add = new TaiKhoanDialog(this, owner, "Thêm tài khoản", true, "view", listTk.get(index));
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String type = (String) search.cbxChoose.getSelectedItem();
        String txt = search.txtSearchForm.getText();
        listTk = taiKhoanBus.search(txt, type);
        loadTable(listTk);
    }

}
