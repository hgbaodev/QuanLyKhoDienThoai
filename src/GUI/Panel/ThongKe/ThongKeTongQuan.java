package GUI.Panel.ThongKe;

import BUS.ThongKeBUS;
import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DAO.SanPhamDAO;
import GUI.Component.itemTaskbar;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ThongKeTongQuan extends JPanel {

    ThongKeBUS thongkebus;
    JPanel jp_top, top_center;
    itemTaskbar[] listitem;
    String[][] getSt = {
        {"Sản phẩm hiện có trong kho", "productt.svg", Integer.toString(SanPhamDAO.getInstance().selectAll().size())},
        {"Khách từ trước đến nay", "stafff.svg", Integer.toString(KhachHangDAO.getInstance().selectAll().size())},
        {"Nhân viên đang hoạt động", "customerr.svg", Integer.toString(NhanVienDAO.getInstance().selectAll().size())}};

    public ThongKeTongQuan(ThongKeBUS thongkebus) {
        this.thongkebus = thongkebus;
        initComponent();
    }

    private void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        jp_top = new JPanel();
        jp_top.setLayout(new GridLayout(1, 3, 20, 0));
        jp_top.setOpaque(false);
        jp_top.setBorder(new EmptyBorder(10, 10, 10, 10));
        jp_top.setPreferredSize(new Dimension(0, 120));
        this.add(jp_top, BorderLayout.NORTH);
        
        listitem = new itemTaskbar[getSt.length];
        for (int i = 0; i < getSt.length; i++) {
            listitem[i] = new itemTaskbar(getSt[i][1], getSt[i][2], getSt[i][0], 0);
            jp_top.add(listitem[i]);
        }
        
    }
}
