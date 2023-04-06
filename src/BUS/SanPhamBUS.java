/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.util.ArrayList;

public class SanPhamBUS  {
     private final SanPhamDAO spDAO = new SanPhamDAO();
    private ArrayList<SanPhamDTO> listSP = new ArrayList<>();

    public SanPhamBUS() {
        listSP=spDAO.selectAll();
    }
    public ArrayList<SanPhamDTO>getAll(){
        return this.listSP;
    }
    public SanPhamDTO getByIndex(int index){
        return this.listSP.get(index);
    }
     
     public int getIndexByMaLH(int maloaihang){
         int i = 0;
        int vitri = -1;
        while(i < this.listSP.size() && vitri == -1) {
            if(listSP.get(i).getMaloaihang()== maloaihang) {
                vitri = i;
            } else i++;
        }
        return vitri;
     }
     public Boolean add(SanPhamDTO lh) {
        boolean check = spDAO.insert(lh) != 0;
        if(check) this.listSP.add(lh);
        return check;
    }
    public Boolean delete(SanPhamDTO lh) {
        boolean check = spDAO.delete(Integer.toString(lh.getMaloaihang())) != 0;
        if(check) this.listSP.remove(lh);
        return check;
    }
    
    public Boolean update(SanPhamDTO lh) {
        boolean check = spDAO.update(lh) != 0;
        if(check) this.listSP.set(getIndexByMaLH(lh.getMaloaihang()), lh);
        return check;
    }
    public ArrayList<SanPhamDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        for(SanPhamDTO i : this.listSP) {
            if(Integer.toString(i.getMasp()).toLowerCase().contains(text) || i.getTensp().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }
}
