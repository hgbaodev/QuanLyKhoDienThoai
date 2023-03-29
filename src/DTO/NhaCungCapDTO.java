/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

public class NhaCungCapDTO {
    private int mancc;
    private String tenncc;
    private String diachi;
    private String email;
    private String sdt;

    public NhaCungCapDTO() {
    }

    public NhaCungCapDTO(int mancc, String tenncc, String diachi, String email, String sdt) {
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.diachi = diachi;
        this.email = email;
        this.sdt = sdt;
    }

    public int getMancc() {
        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public String getTenncc() {
        return tenncc;
    }

    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.mancc;
        hash = 67 * hash + Objects.hashCode(this.tenncc);
        hash = 67 * hash + Objects.hashCode(this.diachi);
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + Objects.hashCode(this.sdt);
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
        final NhaCungCapDTO other = (NhaCungCapDTO) obj;
        return true;
    }

    @Override
    public String toString() {
        return "NhaCungCap{" + "mancc=" + mancc + ", tenncc=" + tenncc + ", diachi=" + diachi + ", email=" + email + ", sdt=" + sdt + '}';
    }
    
}
