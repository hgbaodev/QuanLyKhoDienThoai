/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author robot
 */
public class Taikhoan {
    private String email;
    private String hoten;
    private String matkhau;
    private int trangthai;
    private String makhohang;
    private String manhomquyen;
    
    public Taikhoan(){
        
    }

    public Taikhoan(String email, String hoten, String matkhau, int trangthai, String makhohang, String manhomquyen) {
        this.email = email;
        this.hoten = hoten;
        this.matkhau = matkhau;
        this.trangthai = trangthai;
        this.makhohang = makhohang;
        this.manhomquyen = manhomquyen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getMakhohang() {
        return makhohang;
    }

    public void setMakhohang(String makhohang) {
        this.makhohang = makhohang;
    }

    public String getManhomquyen() {
        return manhomquyen;
    }

    public void setManhomquyen(String manhomquyen) {
        this.manhomquyen = manhomquyen;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", hoten=" + hoten + ", matkhau=" + matkhau + ", trangthai=" + trangthai + ", makhohang=" + makhohang + ", manhomquyen=" + manhomquyen + '}';
    }
    
}
