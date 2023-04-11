/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Panel;

import BUS.MauSacBUS;
import BUS.NhaCungCapBUS;
import DAO.MauSacDAO;
import DTO.ThuocTinhSanPham.MauSacDTO;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import GUI.Component.PanelBorderRadius;
import GUI.Dialog.ThuocTinhSanPham.MauSacDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MauSac extends JPanel implements ActionListener, ItemListener {
    PanelBorderRadius main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableMauSac;
    JScrollPane scrollTable;
    MainFunction mainFunction;
    IntegratedSearch search;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    Color BackgroundColor = new Color(240, 247, 250);
    DefaultTableModel tblModel;
    public MauSacBUS msBUS = new MauSacBUS();
    public ArrayList<MauSacDTO> listmausac = msBUS.getAll();

    private void initComponent() {
        //Set model table
        tableMauSac = new JTable();
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã màu", "Tên màu"};
        tblModel.setColumnIdentifiers(header);
        tableMauSac.setModel(tblModel);
        scrollTable.setViewportView(tableMauSac);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tableMauSac.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);

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

        search = new IntegratedSearch(new String[]{"Tất cả", "Mã màu", "Tên màu"});
        search.cbxChoose.addItemListener(this);
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                //listmausac = msBUS.search(txt, type);
                loadDataTable(listmausac);
            }
        });

        search.btnReset.addActionListener(this);
        functionBar.add(search);
        contentCenter.add(functionBar, BorderLayout.NORTH);
        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);
        main.add(scrollTable);
    }

    public MauSac() {
        initComponent();
        tableMauSac.setDefaultEditor(Object.class, null);
        loadDataTable(listmausac);
    }

    public void loadDataTable(ArrayList<MauSacDTO> result) {
        tblModel.setRowCount(0);
        for (MauSacDTO ncc : result) {
            tblModel.addRow(new Object[]{
                ncc.getMamau(), ncc.getTenmau()
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
    public void exportExcel() {
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();
            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("NHÀ CUNG CẤP");
                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tableMauSac.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tableMauSac.getColumnName(i));
                }

                for (int j = 0; j < tableMauSac.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < tableMauSac.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (tableMauSac.getValueAt(j, k) != null) {
                            cell.setCellValue(tableMauSac.getValueAt(j, k).toString());
                        }

                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
                JOptionPane.showMessageDialog(this, "Xuất file excel thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file");
        }
    }

    public void importExcel() {
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJTableImport = null;
        ArrayList<MauSacDTO> listExcel = new ArrayList<MauSacDTO>();
        JFileChooser jf = new JFileChooser();
        int result = jf.showOpenDialog(null);
        jf.setDialogTitle("Open file");
        Workbook workbook = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = jf.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelJTableImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);
                    int id = MauSacDAO.getInstance().getAutoIncrement();
                    String tenmau = excelRow.getCell(0).getStringCellValue();
                    msBUS.add(new MauSacDTO(id, tenmau));
                    tblModel.setRowCount(0);
                    loadDataTable(listmausac);
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Lỗi đọc file");
            } catch (IOException ex) {
                System.out.println("Lỗi đọc file");
            }
        }
//        for (MauSacDTO ncc : listExcel) {
//            MauSacDAO.getInstance().insert(ncc);
//        }
        loadDataTable(listmausac);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btnAdd) {
            MauSacDialog msDialog = new MauSacDialog(this, owner, "Thêm màu sắc", true, "create");
        } else if (e.getSource() == mainFunction.btnEdit) {
            int index = tableMauSac.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn màu sắc cần sửa");
            } else {
                MauSacDialog msDialog = new MauSacDialog(this, owner, "Chỉnh sửa màu sắc", true, "update", listmausac.get(index));
            }
        } else if (e.getSource() == mainFunction.btnDelete) {
            int index = tableMauSac.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn màu sắc cần xóa");
            } else {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa màu sắc!", "Xóa màu sắc",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    msBUS.delete(listmausac.get(index), index);
                    loadDataTable(listmausac);
                }
            }
        } else if (e.getSource() == mainFunction.btnDetail) {
            int index = tableMauSac.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn màu sắc cần xem");
            } else {
                MauSacDialog nccDialog = new MauSacDialog(this, owner, "Chi tiết màu sắc", true, "view", listmausac.get(index));
            }
        } else if (e.getSource() == search.btnReset) {
            search.txtSearchForm.setText("");
            listmausac = msBUS.getAll();
            loadDataTable(listmausac);
        }
        else if(e.getSource()==mainFunction.btnNhapExcel){
            importExcel();
        }
        else if(e.getSource()==mainFunction.btnXuatExcel){
            exportExcel();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String type = (String) search.cbxChoose.getSelectedItem();
        String txt = search.txtSearchForm.getText();
        //listmausac = msBUS.search(txt, type);
        loadDataTable(listmausac);
    }
}
