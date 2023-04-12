/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThuocTinhSanPham;

/**
 *
 * @author 84907
 */
public class XuatXuDTO {
    private int maxuatxu;
    private String tenxuatxu;
    public XuatXuDTO(){
        
    }

    public XuatXuDTO(int maxuatxu, String tenxuatxu) {
        this.maxuatxu = maxuatxu;
        this.tenxuatxu = tenxuatxu;
    }

    public int getMaxuatxu() {
        return maxuatxu;
    }

    public void setMaxuatxu(int maxuatxu) {
        this.maxuatxu = maxuatxu;
    }

    public String getTenxuatxu() {
        return tenxuatxu;
    }

    public void setTenxuatxu(String tenxuatxu) {
        this.tenxuatxu = tenxuatxu;
    }

    @Override
    public String toString() {
        return "XuatXuDTO{" + "maxuatxu=" + maxuatxu + ", tenxuatxu=" + tenxuatxu + '}';
    }
    
}
