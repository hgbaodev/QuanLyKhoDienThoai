/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhuVucKhoDAO;
import DTO.KhuVucKhoDTO;
import java.util.ArrayList;

public class KhuVucKhoBUS {

    private final KhuVucKhoDAO kvkDAO = new KhuVucKhoDAO();
    private ArrayList<KhuVucKhoDTO> listKVK = new ArrayList<>();

    public KhuVucKhoBUS getInstance() {
        return new KhuVucKhoBUS();
    }
    
    public KhuVucKhoBUS() {
        listKVK = kvkDAO.selectAll();
    }

    public ArrayList<KhuVucKhoDTO> getAll() {
        return this.listKVK;
    }

    public KhuVucKhoDTO getByIndex(int index) {
        return this.listKVK.get(index);
    }

    public int getIndexByMaLH(int makhuvuc) {
        int i = 0;
        int vitri = -1;
        while (i < this.listKVK.size() && vitri == -1) {
            if (listKVK.get(i).getMakhuvuckho() == makhuvuc) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public boolean add(KhuVucKhoDTO kvk) {
        boolean check = kvkDAO.insert(kvk) != 0;
        if (check) {
            this.listKVK.add(kvk);
        }
        return check;
    }

    public boolean delete(KhuVucKhoDTO kvk, int index) {
        boolean check = kvkDAO.delete(Integer.toString(kvk.getMakhuvuckho())) != 0;
        if (check) {
            this.listKVK.remove(index);
        }
        return check;
    }

    public boolean update(KhuVucKhoDTO kvk) {
        boolean check = kvkDAO.update(kvk) != 0;
        if (check) {
            this.listKVK.set(getIndexByMaNCC(kvk.getMakhuvuckho()), kvk);
        }
        return check;
    }

    public int getIndexByMaNCC(int makvk) {
        int i = 0;
        int vitri = -1;
        while (i < this.listKVK.size() && vitri == -1) {
            if (listKVK.get(i).getMakhuvuckho() == makvk) {
                vitri = i;
                break;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public ArrayList<KhuVucKhoDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<KhuVucKhoDTO> result = new ArrayList<>();
        for (KhuVucKhoDTO i : this.listKVK) {
            if (Integer.toString(i.getMakhuvuckho()).toLowerCase().contains(text) || i.getTenkhuvuc().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }
    
    public String[] getArrTenKhuVuc() {
        int size = listKVK.size();
        String[] result = new String[size];
        for(int i = 0; i < size; i++) {
            result[i] = listKVK.get(i).getTenkhuvuc();
        }
        return result;
    }
}
