/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DonViTinhDAO;
import DTO.DonViTinhDTO;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class DonViTinhBUS {
    private final DonViTinhDAO dvtDAO = new DonViTinhDAO();
    private ArrayList<DonViTinhDTO> listDvt = new ArrayList<>();

    public DonViTinhBUS() {
        listDvt = dvtDAO.selectAll();
    }
    
    public ArrayList<DonViTinhDTO> getAll(){
        return this.listDvt;
    }
    
    public DonViTinhDTO getByIndex(int index){
        return this.listDvt.get(index);
    }
    
    public int getIndexByMaDV(int madonvi){
        int i = 0;
        int vitri = -1;
        while(i < this.listDvt.size() && vitri == -1) {
            if(listDvt.get(i).getMaDVT() == madonvi) {
                vitri = i;
            } else i++;
        }
        return vitri;
    }
    
    public Boolean add(DonViTinhDTO dvt) {
        boolean check = dvtDAO.insert(dvt) != 0;
        if(check) this.listDvt.add(dvt);
        return check;
    }
    
    public Boolean delete(DonViTinhDTO dvt) {
        boolean check = dvtDAO.delete(Integer.toString(dvt.getMaDVT())) != 0;
        if(check) this.listDvt.remove(dvt);
        return check;
    }
    
    public Boolean update(DonViTinhDTO dvt) {
        boolean check = dvtDAO.update(dvt) != 0;
        if(check) this.listDvt.set(getIndexByMaDV(dvt.getMaDVT()), dvt);
        return check;
    }
    
    public ArrayList<DonViTinhDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<DonViTinhDTO> result = new ArrayList<>();
        for(DonViTinhDTO i : this.listDvt) {
            if(Integer.toString(i.getMaDVT()).toLowerCase().contains(text) || i.getTenDVT().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }
}
