/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhomQuyenDAO;
import DTO.ChiTietQuyenDTO;
import DTO.NhomQuyenDTO;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class NhomQuyenBUS {

    private final NhomQuyenDAO nhomquyenDAO = new NhomQuyenDAO();
    public ChiTietQuyenBUS chitietquyenBUS = new ChiTietQuyenBUS();
    private ArrayList<NhomQuyenDTO> listNhomQuyen;

    public NhomQuyenBUS() {
        this.listNhomQuyen = nhomquyenDAO.selectAll();
    }
    
    public ArrayList<NhomQuyenDTO> getAll() {
        return this.listNhomQuyen;
    }
    
    public NhomQuyenDTO getByIndex(int index) {
        return this.listNhomQuyen.get(index);
    }

    public boolean add(String nqdto, ArrayList<ChiTietQuyenDTO> ctquyen) {
        NhomQuyenDTO nq = new NhomQuyenDTO(nhomquyenDAO.getAutoIncrement(),nqdto);
        boolean check = nhomquyenDAO.insert(nq) != 0;
        if (check) {
            this.listNhomQuyen.add(nq);
            this.chitietquyenBUS.add(ctquyen);
        }
        return check;
    }

    public boolean update(NhomQuyenDTO nqdto, int index) {
        boolean check = nhomquyenDAO.update(nqdto) != 0;
        if (check) {
            this.listNhomQuyen.set(index, nqdto);
        }
        return check;
    }

    public boolean delete(NhomQuyenDTO nqdto, int index) {
        boolean check = nhomquyenDAO.update(nqdto) != 0;
        if (check) {
            this.listNhomQuyen.remove(index);
        }
        return check;
    }
}
