/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietQuyenDAO;
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
    private final ChiTietQuyenDAO chitietquyenDAO = new ChiTietQuyenDAO();
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
            this.addChiTietQuyen(ctquyen);
        }
        return check;
    }

    public boolean delete(NhomQuyenDTO nqdto) {
        boolean check = nhomquyenDAO.delete(Integer.toString(nqdto.getManhomquyen())) != 0;
        if (check) this.listNhomQuyen.remove(nqdto);
        return check;
    }
    
    public ArrayList<ChiTietQuyenDTO> getChiTietQuyen(String manhomquyen){
        return chitietquyenDAO.selectAll(manhomquyen);
    }
    
    public boolean addChiTietQuyen(ArrayList<ChiTietQuyenDTO> listctquyen) {
        boolean check = chitietquyenDAO.insert(listctquyen) != 0;
        return check;
    }
}
