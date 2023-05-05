package GUI.Panel.ThongKe;

import BUS.ThongKeBUS;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Tran Nhat Sinh
 */
public final class ThongKe extends JPanel {

    JTabbedPane tabbedPane;
    JPanel tongquan, nhacungcap, khachhang, doanhthu;
    ThongKeTonKho nhapxuat;
    Color BackgroundColor = new Color(240, 247, 250);
    ThongKeBUS thongkeBUS = new ThongKeBUS();

    public ThongKe() {
        initComponent();
    }

    public void initComponent() {
        this.setLayout(new GridLayout(1, 1));
        this.setBackground(BackgroundColor);

        tongquan = new ThongKeTongQuan(thongkeBUS);
        nhapxuat = new ThongKeTonKho(thongkeBUS);
        khachhang = new ThongKeKhachHang(thongkeBUS);
        nhacungcap = new ThongKeNhaCungCap(thongkeBUS);
        doanhthu = new ThongKeDoanhThu(thongkeBUS);

        tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false);
        tabbedPane.addTab("Tổng quan", tongquan);
        tabbedPane.addTab("Tồn kho", nhapxuat);
        tabbedPane.addTab("Doanh thu", doanhthu);
        tabbedPane.addTab("Nhà cung cấp", nhacungcap);
        tabbedPane.addTab("Khách hàng", khachhang);

        this.add(tabbedPane);
    }
}
