/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhoHangDAO;
import DTO.KhoHang;
import java.util.ArrayList;

public class KhoHangBUS {
    private final KhoHangDAO khoDAO = new KhoHangDAO();
    private ArrayList<KhoHang> listKho = new ArrayList<>();
    
    public KhoHangBUS() {
        listKho=khoDAO.selectAll();
    }
    
    public ArrayList<KhoHang>getAll(){
        return this.listKho;
    }
    
    public KhoHang getByIndex(int index){
        return this.listKho.get(index);
    }
    
     public int getIndexByMaKho(int makho){
         int i = 0;
        int vitri = -1;
        while(i < this.listKho.size() && vitri == -1) {
            if(listKho.get(i).getMakhohang()== makho) {
                vitri = i;
            } else i++;
        }
        return vitri;
     }
     
     public Boolean add(KhoHang kho) {
        boolean check = khoDAO.insert(kho) != 0;
        if(check) this.listKho.add(kho);
        return check;
    }
     
    public Boolean delete(KhoHang kho) {
        boolean check = khoDAO.delete(Integer.toString(kho.getMakhohang())) != 0;
        if(check) this.listKho.remove(kho);
        return check;
    }
    
    public Boolean update(KhoHang kho) {
        boolean check = khoDAO.update(kho) != 0;
        if(check) this.listKho.set(getIndexByMaKho(kho.getMakhohang()), kho);
        return check;
    }
    public ArrayList<KhoHang> search(String text) {
        text = text.toLowerCase();
        ArrayList<KhoHang> result = new ArrayList<>();
        for(KhoHang i : this.listKho) {
            if(Integer.toString(i.getMakhohang()).toLowerCase().contains(text) || i.getTenkhohang().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }
}
