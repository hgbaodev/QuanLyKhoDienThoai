/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author 84907
 */
public class SanPham {
    private String masp;
    private String tensp;
    private String xuatxu;
    private float gianhap;
    private float giaxuat;
    private String url_img;

    public SanPham() {
    }

    public SanPham(String masp, String tensp, String xuatxu, float gianhap, float giaxuat, String url_img) {
        this.masp = masp;
        this.tensp = tensp;
        this.xuatxu = xuatxu;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat;
        this.url_img = url_img;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getXuatxu() {
        return xuatxu;
    }

    public void setXuatxu(String xuatxu) {
        this.xuatxu = xuatxu;
    }

    public float getGianhap() {
        return gianhap;
    }

    public void setGianhap(float gianhap) {
        this.gianhap = gianhap;
    }

    public float getGiaxuat() {
        return giaxuat;
    }

    public void setGiaxuat(float giaxuat) {
        this.giaxuat = giaxuat;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.masp);
        hash = 13 * hash + Objects.hashCode(this.tensp);
        hash = 13 * hash + Objects.hashCode(this.xuatxu);
        hash = 13 * hash + Float.floatToIntBits(this.gianhap);
        hash = 13 * hash + Float.floatToIntBits(this.giaxuat);
        hash = 13 * hash + Objects.hashCode(this.url_img);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SanPham other = (SanPham) obj;
        return true;
    }

    @Override
    public String toString() {
        return "SanPham{" + "masp=" + masp + ", tensp=" + tensp + ", xuatxu=" + xuatxu + ", gianhap=" + gianhap + ", giaxuat=" + giaxuat + ", url_img=" + url_img + '}';
    }
    
}
