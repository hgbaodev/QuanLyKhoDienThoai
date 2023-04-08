package GUI.Panel;

import BUS.DonViTinhBUS;
import BUS.KhuVucKhoBUS;
import BUS.LoaiHangBUS;
import GUI.Dialog.SanPhamDialog;
import BUS.SanPhamBUS;
import DTO.DonViTinhDTO;
import DTO.KhuVucKhoDTO;
import DTO.LoaiHangDTO;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import helper.Formater;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public final class SanPham extends JPanel implements ActionListener {

    PanelBorderRadius main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    DefaultTableModel tblModel;
    public SanPhamBUS spBUS = new SanPhamBUS();
    public ArrayList<DTO.SanPhamDTO> listSP = spBUS.getAll();

    Color BackgroundColor = new Color(240, 247, 250);

    public String getTenDVT(int maDVT) {
        String tendvt = "";
        DonViTinhBUS dvtbus = new DonViTinhBUS();
        ArrayList<DonViTinhDTO> listDVT = dvtbus.getAll();
        for (int i = 0; i < listDVT.size(); i++) {
            if (maDVT == listDVT.get(i).getMaDVT()) {
                tendvt = listDVT.get(i).getTenDVT();
            }
        }
        return tendvt;
    }

    public String getTenloaihang(int maloaihang) {
        String tenlh = "";
        LoaiHangBUS lhBUS = new LoaiHangBUS();
        ArrayList<LoaiHangDTO> listLH = lhBUS.getAll();
        for (int i = 0; i < listLH.size(); i++) {
            if (maloaihang == listLH.get(i).getMaloaihang()) {
                tenlh = listLH.get(i).getTenloaihang();
            }
        }
        return tenlh;
    }

    public String getTenkhuvuc(int makhuvuc) {
        String tenkv = "";
        KhuVucKhoBUS kvkbus = new KhuVucKhoBUS();
        ArrayList<KhuVucKhoDTO> listKVK = kvkbus.getAll();
        for (int i = 0; i < listKVK.size(); i++) {
            if (makhuvuc == listKVK.get(i).getMakhuvuckho()) {
                tenkv = listKVK.get(i).getTenkhuvuc();
            }
        }
        return tenkv;
    }

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã SP", "Tên sản phẩm", "Xuất xứ", "Giá nhập", "Giá xuất", "Đơn vị tính", "Loại hàng", "Khu vực kho"};
        tblModel.setColumnIdentifiers(header);
        tableSanPham.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableSanPham);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tableSanPham.getColumnModel();
        for (int i = 0; i < 8; i++) {
            if(i != 1) columnModel.getColumn(i).setCellRenderer(centerRenderer);
        }
        tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(500);
        tableSanPham.setFocusable(false);
        tableSanPham.setDefaultEditor(Object.class, null);

        initPadding();

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
                listSP = spBUS.search(txt);
                loadDataTalbe(listSP);
            }

        });

        search.btnReset.addActionListener((ActionEvent e) -> {
            search.txtSearchForm.setText("");
            listSP = spBUS.getAll();
            loadDataTalbe(listSP);
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
        loadDataTalbe(listSP);
    }

    public void loadDataTalbe(ArrayList<DTO.SanPhamDTO> result) {
        tblModel.setRowCount(0);
        for (DTO.SanPhamDTO sp : result) {
            tblModel.addRow(new Object[]{
                sp.getMasp(), sp.getTensp(), sp.getXuatxu(), Formater.FormatVND(sp.getGianhap()), Formater.FormatVND(sp.getGiaxuat()), getTenDVT(sp.getMaDVT()), getTenloaihang(sp.getMaloaihang()), getTenkhuvuc(sp.getMakhuvuc())
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
            SanPhamDialog spDialog = new SanPhamDialog(this, owner, "Thêm sản phẩm mới", true, "create");
        } else if (e.getSource() == mainFunction.btnEdit) {
            int index = tableSanPham.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần sửa");
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
    }

    private void initPadding() {
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
    }

}
