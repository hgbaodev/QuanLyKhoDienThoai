package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;

public class KhachHangBUS {

    private final KhachHangDAO khDAO = new KhachHangDAO();
    public ArrayList<KhachHangDTO> listKhachHang = new ArrayList<>();

    public KhachHangBUS() {
        listKhachHang = khDAO.selectAll();
    }

    public ArrayList<KhachHangDTO> getAll() {
        return this.listKhachHang;
    }

    public KhachHangDTO getByIndex(int index) {
        return this.listKhachHang.get(index);
    }

    public int getIndexByMaDV(int makhachhang) {
        int i = 0;
        int vitri = -1;
        while (i < this.listKhachHang.size() && vitri == -1) {
            if (listKhachHang.get(i).getMaKH() == makhachhang) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(KhachHangDTO kh) {
        boolean check = khDAO.insert(kh) != 0;
        if (check) {
            this.listKhachHang.add(kh);
        }
        return check;
    }

    public Boolean delete(KhachHangDTO kh) {
        boolean check = khDAO.delete(Integer.toString(kh.getMaKH())) != 0;
        if (check) {
            this.listKhachHang.remove(getIndexByMaDV(kh.getMaKH()));
        }
        return check;
    }

    public Boolean update(KhachHangDTO kh) {
        boolean check = khDAO.update(kh) != 0;
        if (check) {
            this.listKhachHang.set(getIndexByMaDV(kh.getMaKH()), kh);
        }
        return check;
    }

    public ArrayList<KhachHangDTO> search(String text, String type) {
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (KhachHangDTO i : this.listKhachHang) {
                    if (Integer.toString(i.getMaKH()).toLowerCase().contains(text) || i.getHoten().toLowerCase().contains(text) || i.getDiachi().toLowerCase().contains(text) || i.getSdt().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Mã khách hàng" -> {
                for (KhachHangDTO i : this.listKhachHang) {
                    if (Integer.toString(i.getMaKH()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Tên khách hàng" -> {
                for (KhachHangDTO i : this.listKhachHang) {
                    if (i.getHoten().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Địa chỉ" -> {
                for (KhachHangDTO i : this.listKhachHang) {
                    if (i.getDiachi().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Số điện thoại" -> {
                for (KhachHangDTO i : this.listKhachHang) {
                    if (i.getSdt().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
        }

        return result;
    }

    public String getTenKhachHang(int makh) {
        String name = "";
        for (KhachHangDTO khachHangDTO : listKhachHang) {
            if (khachHangDTO.getMaKH() == makh) {
                name = khachHangDTO.getHoten();
            }
        }
        return name;
    }

    public String[] getArrTenKhachHang() {
        int size = listKhachHang.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listKhachHang.get(i).getHoten();
        }
        return result;
    }

    public KhachHangDTO selectKh(int makh) {
        return khDAO.selectById(makh + "");
    }

}
