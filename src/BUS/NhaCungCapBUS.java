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

    public boolean delete(NhaCungCapDTO ncc) {
        boolean check = NccDAO.delete(Integer.toString(ncc.getMancc())) != 0;
        if (check) {
            this.listNcc.remove(ncc);
        }
        return check;
    }

    public boolean update(NhaCungCapDTO ncc) {
        boolean check = NccDAO.update(ncc) != 0;
        if (check) {
            this.listNcc.remove(ncc);
        }
        return check;
    }

    public ArrayList<NhaCungCapDTO> search(String txt, String type) {
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
        txt = txt.toLowerCase();
        switch (type) {
            case "all" -> {
                for (NhaCungCapDTO i : listNcc) {
                    if (Integer.toString(i.getMancc()).contains(txt) || i.getTenncc().contains(txt) || i.getDiachi().contains(txt) || i.getEmail().contains(txt) || i.getSdt().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "mancc" -> {
                for (NhaCungCapDTO i : listNcc) {
                    if (Integer.toString(i.getMancc()).contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "tenncc" -> {
                for (NhaCungCapDTO i : listNcc) {
                    if (i.getTenncc().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "diachi" -> {
                for (NhaCungCapDTO i : listNcc) {
                    if (i.getDiachi().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "sodienthoai" -> {
                for (NhaCungCapDTO i : listNcc) {
                    if (i.getSdt().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "email" -> {
                for (NhaCungCapDTO i : listNcc) {
                    if (i.getEmail().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
        }
        return result;
    }

}
