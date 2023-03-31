package GUI;

import BUS.NhaCungCapBUS;
import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import component.IntegratedSearch;
import component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import component.PanelBorderRadius;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class NhaCungCap extends JPanel implements ActionListener {

    PanelBorderRadius main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableNhaCungCap;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    Color BackgroundColor = new Color(240, 247, 250);
    DefaultTableModel tblModel;
    NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    ArrayList<NhaCungCapDTO> listncc = nccBUS.getAll();

    private void initComponent() {
        //Set model table
        tableNhaCungCap = new JTable();
        scrollTableSanPham = new JScrollPane();
        tableNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        tableNhaCungCap.setFont(new java.awt.Font("Segoe UI", 0, 14));
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã NCC", "Tên nhà cung cấp", "Địa chỉ", "Email", "Số điện thoại"};
        tblModel.setColumnIdentifiers(header);
        tableNhaCungCap.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableNhaCungCap);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tableNhaCungCap.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(0).setPreferredWidth(2);
        columnModel.getColumn(2).setPreferredWidth(300);
        columnModel.getColumn(4).setCellRenderer(centerRenderer);

        
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

        mainFunction = new MainFunction();
        //Add Event MouseListener
        mainFunction.btnAdd.addActionListener(this);
        mainFunction.btnEdit.addActionListener(this);
        mainFunction.btnDetail.addActionListener(this);
        mainFunction.btnDelete.addActionListener(this);
        mainFunction.btnXuatExcel.addActionListener(this);
        mainFunction.btnNhapExcel.addActionListener(this);
        functionBar.add(mainFunction);

        search = new IntegratedSearch(new String[]{"Tất cả", "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Email", "Số điện thoại"});
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String txt = search.txtSearchForm.getText();
                listncc = nccBUS.search(txt, "all");
                loadDataTalbe(listncc);
            }
        });

        search.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search.txtSearchForm.setText("");
                listncc = nccBUS.getAll();
                loadDataTalbe(listncc);
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

    public NhaCungCap() {
        initComponent();
        tableNhaCungCap.setDefaultEditor(Object.class, null);
        loadDataTalbe(listncc);
    }

    public void loadDataTalbe(ArrayList<NhaCungCapDTO> result) {
        tblModel.setRowCount(0);
        for (NhaCungCapDTO ncc : result) {
            tblModel.addRow(new Object[]{
                ncc.getMancc(), ncc.getTenncc(), ncc.getDiachi(), ncc.getEmail(), ncc.getSdt()
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
            NhaCungCapDialog dvtDialog = new NhaCungCapDialog(this, owner, "Thêm nhà cung cấp", true, "create");
        } else if (e.getSource() == mainFunction.btnEdit) {
            int index = tableNhaCungCap.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp cần sửa");
            } else {
                NhaCungCapDialog nccDialog = new NhaCungCapDialog(this, owner, "Chỉnh sửa nhà cung cấp", true, "update", listncc.get(index));
            }
        } else if (e.getSource() == mainFunction.btnDelete) {
            int index = tableNhaCungCap.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp cần xóa");
            } else {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa nhà cung cấp!", "Xóa nhà cung cấp",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    nccBUS.delete(listncc.get(index), index);
                    loadDataTalbe(listncc);
                }
            }
        } else if (e.getSource() == mainFunction.btnDetail) {
            int index = tableNhaCungCap.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp cần xem");
            } else {
                NhaCungCapDialog nccDialog = new NhaCungCapDialog(this, owner, "Chi tiết nhà cung cấp", true, "view", listncc.get(index));
            }
        }
    }

}
