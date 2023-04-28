package GUI.Panel;

import BUS.NhomQuyenBUS;
import DTO.NhomQuyenDTO;
import GUI.Dialog.PhanQuyenDialog;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import GUI.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class PhanQuyen extends JPanel implements ActionListener {

    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    PanelBorderRadius box1, box2, main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tblNhomQuyen;
    JScrollPane scrollTable;
    MainFunction mainFunction;
    IntegratedSearch search;
    DefaultTableModel tblModel;
    Main m;
    public NhomQuyenBUS nhomquyenBUS = new NhomQuyenBUS();
    public ArrayList<NhomQuyenDTO> listnhomquyen = nhomquyenBUS.getAll();

    Color BackgroundColor = new Color(240, 247, 250);

    private void initComponent() {
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
        contentCenter.setLayout(new BorderLayout(10, 20));
        this.add(contentCenter, BorderLayout.CENTER);

        // functionBar là thanh bên trên chứa các nút chức năng như thêm xóa sửa, và tìm kiếm
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] action = {"create", "update", "delete", "detail", "import", "export"};
        mainFunction = new MainFunction(m.user.getManhomquyen(), "khuvuckho", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction);

        search = new IntegratedSearch(new String[]{"Tất cả"});
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
//        main.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentCenter.add(main, BorderLayout.CENTER);

        tblNhomQuyen = new JTable();
        tblNhomQuyen.setDefaultEditor(Object.class, null);
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã nhóm quyền", "Tên nhóm quyền"};
        tblModel.setColumnIdentifiers(header);
        tblNhomQuyen.setModel(tblModel);
        scrollTable.setViewportView(tblNhomQuyen);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tblNhomQuyen.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(0).setPreferredWidth(2);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setPreferredWidth(300);
        tblNhomQuyen.setFocusable(false);
        main.add(scrollTable);
    }

    public PhanQuyen(Main m) {
        this.m = m;
        initComponent();
        loadDataTalbe(listnhomquyen);
    }

    public void loadDataTalbe(ArrayList<NhomQuyenDTO> result) {
        tblModel.setRowCount(0);
        for (NhomQuyenDTO ncc : result) {
            tblModel.addRow(new Object[]{
                ncc.getManhomquyen(), ncc.getTennhomquyen()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            PhanQuyenDialog pq = new PhanQuyenDialog(this, owner, "Thêm nhóm quyền", true, "create");
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = this.getRowSelected();
            if (index >= 0) {
                PhanQuyenDialog nccDialog = new PhanQuyenDialog(this, owner, "Chỉnh sửa nhóm quyền", true, "update", listnhomquyen.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = this.getRowSelected();
            if (index >= 0) {
                PhanQuyenDialog nccDialog = new PhanQuyenDialog(this, owner, "Chi tiết nhóm quyền", true, "view", listnhomquyen.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = this.getRowSelected();
            if (index >= 0) {
                int input = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn xóa nhà cung cấp!", "Xóa nhà cung cấp",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    System.out.println(listnhomquyen.get(index));
                    nhomquyenBUS.delete(listnhomquyen.get(index));
                    loadDataTalbe(listnhomquyen);
                }
            }
        }
    }

    public int getRowSelected() {
        int index = tblNhomQuyen.getSelectedRow();
        if (tblNhomQuyen.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhóm quyền");
            return -1;
        }
        return index;
    }
}
