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
            if (listKVK.get(i).getMakhuvuc() == makhuvuc) {
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
        boolean check = kvkDAO.delete(Integer.toString(kvk.getMakhuvuc())) != 0;
        if (check) {
            this.listKVK.remove(index);
        }
        return check;
    }

    public boolean update(KhuVucKhoDTO kvk) {
        boolean check = kvkDAO.update(kvk) != 0;
        if (check) {
            this.listKVK.set(getIndexByMaKVK(kvk.getMakhuvuc()), kvk);
        }
        return check;
    }

    public int getIndexByMaKVK(int makvk) {
        int i = 0;
        int vitri = -1;
        while (i < this.listKVK.size() && vitri == -1) {
            if (listKVK.get(i).getMakhuvuc() == makvk) {
                vitri = i;
                break;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public ArrayList<KhuVucKhoDTO> search(String txt, String type) {
        ArrayList<KhuVucKhoDTO> result = new ArrayList<>();
        txt = txt.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (KhuVucKhoDTO i : listKVK) {
                    if (Integer.toString(i.getMakhuvuc()).contains(txt) || i.getTenkhuvuc().toLowerCase().contains(txt)){
                        result.add(i);
                    }
                }
            }
            case "Mã khu vực kho" -> {
                for (KhuVucKhoDTO i : listKVK) {
                    if (Integer.toString(i.getMakhuvuc()).contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Tên khu vực kho" -> {
                for (KhuVucKhoDTO i : listKVK) {
                    if (i.getTenkhuvuc().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
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
    
    public String getTenKhuVuc(int makhuvuc) {
        return this.listKVK.get(this.getIndexByMaKVK(makhuvuc)).getTenkhuvuc();
    }
}
