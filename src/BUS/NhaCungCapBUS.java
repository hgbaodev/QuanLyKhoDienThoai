/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class NhaCungCapBUS {

    private final NhaCungCapDAO NccDAO = new NhaCungCapDAO();
    private ArrayList<NhaCungCapDTO> listNcc = new ArrayList<>();

    public NhaCungCapBUS() {
        this.listNcc = NccDAO.selectAll();
    }

    public ArrayList<NhaCungCapDTO> getAll() {
        return this.listNcc;
    }

    public NhaCungCapDTO getByIndex(int index) {
        return this.listNcc.get(index);
    }

    public boolean add(NhaCungCapDTO ncc) {
        boolean check = NccDAO.insert(ncc) != 0;
        if (check) {
            this.listNcc.add(ncc);
        }
        return check;
    }

    public boolean delete(NhaCungCapDTO ncc, int index) {
        boolean check = NccDAO.delete(Integer.toString(ncc.getMancc())) != 0;
        if (check) {
            this.listNcc.remove(index);
        }
        return check;
    }

    public boolean update(NhaCungCapDTO ncc) {
        boolean check = NccDAO.update(ncc) != 0;
        if (check) {
            this.listNcc.set(getIndexByMaNCC(ncc.getMancc()), ncc);
        }
        return check;
    }

    public int getIndexByMaNCC(int mancc) {
        int i = 0;
        int vitri = -1;
        while (i < this.listNcc.size() && vitri == -1) {
            if (listNcc.get(i).getMancc() == mancc) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public ArrayList<NhaCungCapDTO> search(String txt, String type) {
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
        txt = txt.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (NhaCungCapDTO i : listNcc) {
                    if (Integer.toString(i.getMancc()).contains(txt) || i.getTenncc().contains(txt) || i.getDiachi().contains(txt) || i.getEmail().contains(txt) || i.getSdt().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Mã nhà cung cấp" -> {
                for (NhaCungCapDTO i : listNcc) {
                    if (Integer.toString(i.getMancc()).contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Tên nhà cung cấp" -> {
                for (NhaCungCapDTO i : listNcc) {
                    if (i.getTenncc().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Địa chỉ" -> {
                for (NhaCungCapDTO i : listNcc) {
                    if (i.getDiachi().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Số điện thoại" -> {
                for (NhaCungCapDTO i : listNcc) {
                    if (i.getSdt().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Email" -> {
                for (NhaCungCapDTO i : listNcc) {
                    if (i.getEmail().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
        }
        return result;
    }

    public String[] getArrTenNhaCungCap() {
        int size = listNcc.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listNcc.get(i).getTenncc();
        }
        return result;
    }

    public String getTenNhaCungCap(int mancc) {
        return this.listNcc.get(getIndexByMaNCC(mancc)).getTenncc();
    }

    public NhaCungCapDTO findCT(ArrayList<NhaCungCapDTO> ncc, String tenncc) {
        NhaCungCapDTO p = null;
        int i = 0;
        while (i < ncc.size() && p == null) {
            if (ncc.get(i).getTenncc().equals(tenncc)) {
                p = ncc.get(i);
            } else {
                i++;
            }
        }
        return p;
    }
}
