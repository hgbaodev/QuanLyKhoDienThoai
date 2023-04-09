package BUS;

import DAO.DanhMucSanPhamDAO;
import DTO.SanPhamDTO;
import java.util.ArrayList;

public class DanhMucSanPhamBUS {

    private final DanhMucSanPhamDAO spDAO = new DanhMucSanPhamDAO();
    private ArrayList<SanPhamDTO> listSP = new ArrayList<>();
    public DanhMucSanPhamBUS() {
        listSP = spDAO.selectAll();
    }

    public ArrayList<SanPhamDTO> getAll() {
        return this.listSP;
    }

    public SanPhamDTO getByIndex(int index) {
        return this.listSP.get(index);
    }
    
    public SanPhamDTO getByMaSP(int masp) {
        int vitri = -1;
        int i = 0;
        while(i <= this.listSP.size() && vitri == -1) {
            if(this.listSP.get(i).getMadanhmuc() == masp) {
                vitri = i;
            } else i++;
        }
        return this.listSP.get(vitri);
    }

    public int getIndexByMaSP(int masanpham) {
        int i = 0;
        int vitri = -1;
        while (i < this.listSP.size() && vitri == -1) {
            if (listSP.get(i).getMadanhmuc() == masanpham) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(SanPhamDTO lh) {
        boolean check = spDAO.insert(lh) != 0;
        if (check) {
            this.listSP.add(lh);
        }
        return check;
    }

    public Boolean delete(SanPhamDTO lh) {
        boolean check = spDAO.delete(Integer.toString(lh.getMadanhmuc())) != 0;
        if (check) {
            this.listSP.remove(lh);
        }
        return check;
    }

    public Boolean update(SanPhamDTO lh) {
        boolean check = spDAO.update(lh) != 0;
        if (check) {
            this.listSP.set(getIndexByMaSP(lh.getMadanhmuc()), lh);
        }
        return check;
    }

    public ArrayList<SanPhamDTO> getByMakhuvuc(int makv) {
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        for (SanPhamDTO i : this.listSP) {
            if (i.getKhuvuckho()== makv) {
                result.add(i);
            }
        }
        return result;
    }

    public ArrayList<SanPhamDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        for (SanPhamDTO i : this.listSP) {
            if (Integer.toString(i.getMadanhmuc()).toLowerCase().contains(text) || i.getTendanhmuc().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }
}
