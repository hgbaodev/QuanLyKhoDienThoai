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
    public NhomQuyenBUS nhomquyenBUS = new NhomQuyenBUS();
    public ArrayList<NhomQuyenDTO> listnhomquyen = nhomquyenBUS.getAll();
    ;

    Color BackgroundColor = new Color(240, 247, 250);

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
        mainFunction.btnAdd.addActionListener(this);
        mainFunction.btnEdit.addActionListener(this);

        search = new IntegratedSearch(new String[]{"Tất cả"});
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);

        tblNhomQuyen = new JTable();
        tblNhomQuyen.setDefaultEditor(Object.class, null);
        scrollTable = new JScrollPane();
        tblNhomQuyen.setFont(new java.awt.Font("Segoe UI", 0, 14));
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
        main.add(scrollTable);
    }

    public PhanQuyen() {
        initComponent();
        loadDataTalbe(listnhomquyen);
    }

    public void loadDataTalbe(ArrayList<NhomQuyenDTO> result) {
        System.out.println(result);
        tblModel.setRowCount(0);
        for (NhomQuyenDTO ncc : result) {
            tblModel.addRow(new Object[]{
                ncc.getManhomquyen(), ncc.getTennhomquyen()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btnAdd) {
            PhanQuyenDialog pq = new PhanQuyenDialog(this, owner, "Thêm nhóm quyền", true, "create");
        } else if (e.getSource() == mainFunction.btnEdit) {
            int index = tblNhomQuyen.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhóm quyền cần sửa");
            } else {
                PhanQuyenDialog nccDialog = new PhanQuyenDialog(this, owner, "Chỉnh sửa nhóm quyền", true, "update", listnhomquyen.get(index));
            }
        }
    }
}
