/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.LoaiHangDAO;
import DTO.LoaiHangDTO;
import java.util.ArrayList;

/**
 *
 * @author 84907
 */
public class LoaiHangBUS {
    private final LoaiHangDAO lhDAO = new LoaiHangDAO();
    private ArrayList<LoaiHangDTO> listLH = new ArrayList<>();

    public LoaiHangBUS() {
        listLH=lhDAO.selectAll();
    }
    public ArrayList<LoaiHangDTO>getAll(){
        return this.listLH;
    }
    public LoaiHangDTO getByIndex(int index){
        return this.listLH.get(index);
    }
     public int getIndexByMaLH(int maloaihang){
         int i = 0;
        int vitri = -1;
        while(i < this.listLH.size() && vitri == -1) {
            if(listLH.get(i).getMaloaihang()== maloaihang) {
                vitri = i;
            } else i++;
        }
        return vitri;
     }
     public Boolean add(String name) {
        LoaiHangDTO lh = new LoaiHangDTO(lhDAO.getAutoIncrement(),name);
        boolean check = lhDAO.insert(lh) != 0;
        if(check) this.listLH.add(lh);
        return check;
    }
    public Boolean delete(LoaiHangDTO lh) {
        boolean check = lhDAO.delete(Integer.toString(lh.getMaloaihang())) != 0;
        if(check) this.listLH.remove(lh);
        return check;
    }
    
    public Boolean update(LoaiHangDTO lh) {
        boolean check = lhDAO.update(lh) != 0;
        if(check) this.listLH.set(getIndexByMaLH(lh.getMaloaihang()), lh);
        return check;
    }
    public ArrayList<LoaiHangDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<LoaiHangDTO> result = new ArrayList<>();
        for(LoaiHangDTO i : this.listLH) {
            if(Integer.toString(i.getMaloaihang()).toLowerCase().contains(text) || i.getTenloaihang().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }
}
