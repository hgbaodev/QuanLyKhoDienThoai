package GUI.Component;

import BUS.NhomQuyenBUS;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

public class MainFunction extends JToolBar {

    public ButtonToolBar btnAdd, btnDelete, btnEdit, btnDetail, btnNhapExcel, btnXuatExcel, btnHuyPhieu;
    public JSeparator separator1;
    public HashMap<String, ButtonToolBar> btn = new HashMap<>();
    private NhomQuyenBUS nhomquyenBus = new NhomQuyenBUS();

    public MainFunction() {
        initComponent();
    }

    public MainFunction(int manhomquyen, String chucnang, String[] listBtn) {
        initData();
        initComponent(manhomquyen, chucnang, listBtn);
    }

    public void initData() {
        btn.put("create", new ButtonToolBar("THÊM", "add.svg", "create"));
        btn.put("delete", new ButtonToolBar("XÓA", "delete.svg", "delete"));
        btn.put("update", new ButtonToolBar("SỬA", "edit.svg", "update"));
        btn.put("cancel", new ButtonToolBar("HUỶ PHIẾU", "cancel.svg", "delete"));
        btn.put("detail", new ButtonToolBar("CHI TIẾT", "detail.svg", "view"));
        btn.put("import", new ButtonToolBar("NHẬP EXCEL", "import_excel.svg", "create"));
        btn.put("export", new ButtonToolBar("XUẤT EXCEL", "export_excel.svg", "view"));
        btn.put("phone", new ButtonToolBar("XEM DS", "phone.svg", "view"));
    }

    private void initComponent(int manhomquyen, String chucnang, String[] listBtn) {
        this.setBackground(Color.WHITE);
        this.setRollover(true);
        initData();
        for (String btnn : listBtn) {
            this.add(btn.get(btnn));
            if (!nhomquyenBus.checkPermisson(manhomquyen, chucnang, btn.get(btnn).getPermisson())) {
                btn.get(btnn).setEnabled(false);
            }
        }
    }

    private void initComponent() {
        this.setBackground(Color.WHITE);
        this.setRollover(true);

        btnAdd = new ButtonToolBar("THÊM", new FlatSVGIcon("./icon/add.svg"));
        this.add(btnAdd);

        btnDelete = new ButtonToolBar("XÓA", new FlatSVGIcon("./icon/delete.svg"));
        this.add(btnDelete);

        btnEdit = new ButtonToolBar("SỬA", new FlatSVGIcon("./icon/edit.svg"));
        this.add(btnEdit);

        btnDetail = new ButtonToolBar("CHI TIẾT", new FlatSVGIcon("./icon/detail.svg"));
        this.add(btnDetail);

        btnXuatExcel = new ButtonToolBar("XUẤT EXCEL", new FlatSVGIcon("./icon/import_excel.svg"));

        btnNhapExcel = new ButtonToolBar("NHẬP EXCEL", new FlatSVGIcon("./icon/export_excel.svg"));
        this.add(btnXuatExcel);
        this.add(btnNhapExcel);
    }
}
