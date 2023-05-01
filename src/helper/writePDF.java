/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import BUS.DungLuongRamBUS;
import BUS.DungLuongRomBUS;
import BUS.MauSacBUS;
import DAO.ChiTietPhieuNhapDAO;
import DAO.ChiTietPhieuXuatDAO;
import DAO.ChiTietSanPhamDAO;
import DAO.KhachHangDAO;
import DAO.NhaCungCapDAO;
import DAO.NhanVienDAO;
import DAO.PhienBanSanPhamDAO;
import DAO.PhieuNhapDAO;
import DAO.PhieuXuatDAO;
import DAO.SanPhamDAO;
import DAO.TaiKhoanDAO;
import DTO.ChiTietPhieuDTO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.ChiTietSanPhamDTO;
import DTO.PhienBanSanPhamDTO;
import DTO.PhieuNhapDTO;
import DTO.PhieuXuatDTO;
import DTO.SanPhamDTO;
import com.itextpdf.text.Chunk;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

public class writePDF {

    DecimalFormat formatter = new DecimalFormat("###,###,###");
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");
    Document document = new Document();
    FileOutputStream file;
    JFrame jf = new JFrame();
    FileDialog fd = new FileDialog(jf, "Xuất pdf", FileDialog.SAVE);
    Font fontData;
    Font fontTitle;
    Font fontHeader;
    
    DungLuongRomBUS romBus = new DungLuongRomBUS();
    DungLuongRamBUS ramBus = new DungLuongRamBUS();
    MauSacBUS mausacBus = new MauSacBUS();
    public writePDF() {
        try {
            fontData = new Font(BaseFont.createFont("lib/Roboto/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 9, Font.NORMAL);
            fontTitle = new Font(BaseFont.createFont("lib/Roboto/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 25, Font.NORMAL);
            fontHeader = new Font(BaseFont.createFont("lib/Roboto/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 11, Font.NORMAL);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(writePDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void chooseURL(String url) {
        try {
            document.close();
            document = new Document();
            file = new FileOutputStream(url);
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Khong tim thay duong dan file " + url);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Khong goi duoc document !");
        }
    }

    public void setTitle(String title) {
        try {
            Paragraph pdfTitle = new Paragraph(new Phrase(title, fontTitle));
            pdfTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(pdfTitle);
            document.add(Chunk.NEWLINE);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }

    private String getFile(String name) {
        fd.pack();
        fd.setSize(800, 600);
        fd.validate();
        Rectangle rect = jf.getContentPane().getBounds();
        double width = fd.getBounds().getWidth();
        double height = fd.getBounds().getHeight();
        double x = rect.getCenterX() - (width / 2);
        double y = rect.getCenterY() - (height / 2);
        Point leftCorner = new Point();
        leftCorner.setLocation(x, y);
        fd.setLocation(leftCorner);
        fd.setFile(name + ".pdf");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("null")) {
            return null;
        }
        return url;
    }

    public void writePhieuNhap(int mapn) {
        String url = "";
        try {
            fd.setTitle("In phiếu nhập");
            fd.setLocationRelativeTo(null);
            url = getFile(mapn+"");
            if (url == null) {
                return;
            }
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();

            setTitle("THÔNG TIN PHIẾU NHẬP");

            PhieuNhapDTO pn = PhieuNhapDAO.getInstance().selectById(mapn+"");

            Chunk glue = new Chunk(new VerticalPositionMark());// Khoang trong giua hang
            Paragraph para1 = new Paragraph();
            para1.setFont(fontData);
            para1.add("Mã phiếu: " + pn.getMaphieu());
            para1.add("\nThời gian tạo: " + formatDate.format(pn.getThoigiantao()));
            para1.setIndentationLeft(40);
            Paragraph para2 = new Paragraph();
            para2.setPaddingTop(30);
            para2.setFont(fontData);
            int manv=pn.getManguoitao();
            para2.add(String.valueOf("Người tạo: " + NhanVienDAO.getInstance().selectById(String.valueOf(manv)).getHoten()));
            para2.add(String.valueOf("\nNhà cung cấp: " + NhaCungCapDAO.getInstance().selectById(pn.getManhacungcap()+"").getTenncc()));
            para2.setIndentationLeft(40);
            document.add(para1);
            document.add(para2);
            document.add(Chunk.NEWLINE);//add hang trong de tao khoang cach

            PdfPTable pdfTable = new PdfPTable(5);
            pdfTable.setWidths(new float[]{30f,25f,15f,15f,18f});
            PdfPCell cell;

            pdfTable.addCell(new PdfPCell(new Phrase("Tên sản phẩm", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Cấu hình", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Giá nhập", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Số lượng", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tổng tiền", fontHeader)));

            for (int i = 0; i < 5; i++) {
                cell = new PdfPCell(new Phrase(""));
                pdfTable.addCell(cell);
            }

            //Truyen thong tin tung chi tiet vao table
            for (ChiTietPhieuNhapDTO ctp : ChiTietPhieuNhapDAO.getInstance().selectAll(mapn+"")) {
                SanPhamDTO sp = new SanPhamDAO().selectByPhienBan(ctp.getMaphienbansp()+"");
                pdfTable.addCell(new PdfPCell(new Phrase(sp.getTensp(), fontData)));
                PhienBanSanPhamDTO pbsp = new PhienBanSanPhamDAO().selectById(ctp.getMaphienbansp());
                pdfTable.addCell(new PdfPCell(new Phrase(romBus.getKichThuocById(pbsp.getRom()) + "GB - "
                    + ramBus.getKichThuocById(pbsp.getRam()) + "GB - " + mausacBus.getTenMau(pbsp.getMausac()),fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(formatter.format(ctp.getDongia()) + "đ", fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(String.valueOf(ctp.getSoluong()), fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(formatter.format(ctp.getSoluong()* ctp.getDongia()) + "đ", fontData)));
            }

            document.add(pdfTable);
            document.add(Chunk.NEWLINE);

            Paragraph paraTongThanhToan = new Paragraph(new Phrase("Tổng thành tiền: " + formatter.format(pn.getTongTien()) + "đ", fontHeader));
            paraTongThanhToan.setIndentationLeft(300);
            
            document.add(paraTongThanhToan);
            document.close();
            JOptionPane.showMessageDialog(null, "Ghi file thành công " + url);
            openFile(url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        }
    }
    
    public void writePhieuXuat(int mapx) {
        String url = "";
        try {
            fd.setTitle("In phiếu xuất");
            fd.setLocationRelativeTo(null);
            url = getFile(mapx+"");
            if (url == null) {
                return;
            }
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();

            setTitle("THÔNG TIN PHIẾU XUẤT");

            PhieuXuatDTO px = PhieuXuatDAO.getInstance().selectById(mapx+"");

            Chunk glue = new Chunk(new VerticalPositionMark());// Khoang trong giua hang
            Paragraph para1 = new Paragraph();
            para1.setFont(fontData);
            para1.add("Mã phiếu: " + px.getMaphieu());
            para1.add("\nThời gian tạo: " + formatDate.format(px.getThoigiantao()));
            para1.setIndentationLeft(40);
            Paragraph para2 = new Paragraph();
            para2.setPaddingTop(30);
            para2.setFont(fontData);
            int manv=px.getManguoitao();
            para2.add(String.valueOf("Người tạo: " + NhanVienDAO.getInstance().selectById(String.valueOf(manv)).getHoten()));
            para2.add(String.valueOf("\nKhách hàng: " + KhachHangDAO.getInstance().selectById(px.getMakh()+"").getHoten()));
            para2.setIndentationLeft(40);
            document.add(para1);
            document.add(para2);
            document.add(Chunk.NEWLINE);//add hang trong de tao khoang cach

            PdfPTable pdfTable = new PdfPTable(5);
            pdfTable.setWidths(new float[]{30f,25f,15f,15f,18f});
            PdfPCell cell;

            pdfTable.addCell(new PdfPCell(new Phrase("Tên sản phẩm", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Cấu hình", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Giá xuất", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Số lượng", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tổng tiền", fontHeader)));

            for (int i = 0; i < 5; i++) {
                cell = new PdfPCell(new Phrase(""));
                pdfTable.addCell(cell);
            }

            //Truyen thong tin tung chi tiet vao table
            for (ChiTietPhieuDTO ctp : ChiTietPhieuXuatDAO.getInstance().selectAll(mapx+"")) {
                SanPhamDTO sp = new SanPhamDAO().selectByPhienBan(ctp.getMaphienbansp()+"");
                pdfTable.addCell(new PdfPCell(new Phrase(sp.getTensp(), fontData)));
                PhienBanSanPhamDTO pbsp = new PhienBanSanPhamDAO().selectById(ctp.getMaphienbansp());
                pdfTable.addCell(new PdfPCell(new Phrase(romBus.getKichThuocById(pbsp.getRom()) + "GB - "
                    + ramBus.getKichThuocById(pbsp.getRam()) + "GB - " + mausacBus.getTenMau(pbsp.getMausac()),fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(formatter.format(ctp.getDongia()) + "đ", fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(String.valueOf(ctp.getSoluong()), fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(formatter.format(ctp.getSoluong()* ctp.getDongia()) + "đ", fontData)));
            }

            document.add(pdfTable);
            document.add(Chunk.NEWLINE);

            Paragraph paraTongThanhToan = new Paragraph(new Phrase("Tổng thành tiền: " + formatter.format(px.getTongTien()) + "đ", fontHeader));
            paraTongThanhToan.setIndentationLeft(300);
            
            document.add(paraTongThanhToan);
            document.close();
            JOptionPane.showMessageDialog(null, "Ghi file thành công " + url);
            openFile(url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        }
    }

    private void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }



}

