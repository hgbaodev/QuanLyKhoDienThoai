/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhomQuyenDAO;
import DAO.TaiKhoanDAO;
import DTO.NhomQuyenDTO;
import DTO.TaiKhoanDTO;
import GUI.Panel.TaiKhoan;
import java.util.ArrayList;

/**
 *
 * @author robot
 */
public class TaiKhoanBUS {
    private ArrayList<TaiKhoanDTO> listTaiKhoan;
    private ArrayList<NhomQuyenDTO> listNhomQuyen;
    
    public TaiKhoanBUS(){
        this.listTaiKhoan  = TaiKhoanDAO.getInstance().selectAll();
        this.listNhomQuyen = NhomQuyenDAO.getInstance().selectAll();
    }
    
    public ArrayList<TaiKhoanDTO> getTaiKhoanAll(){
        return listTaiKhoan;
    }
    
    public TaiKhoanDTO getTaiKhoan(int index){
        return listTaiKhoan.get(index);
    }
    
    public NhomQuyenDTO getNhomQuyenDTO(int index){
        System.out.println("Size:"+listNhomQuyen.size());
        return listNhomQuyen.get(index);
    }
    
    public void addAcc(TaiKhoanDTO tk){
        listTaiKhoan.add(tk);
    }
    
    public void updateAcc(int index, TaiKhoanDTO tk){
        listTaiKhoan.set(index, tk);
    }
    
    public void deleteAcc(int manv){
        
    }
    
}
