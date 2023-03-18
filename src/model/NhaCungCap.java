package model;

import java.util.Objects;

/**
 *
 * @author Tran Nhat Sinh
 */
public class NhaCungCap {

    private int manhacungcap;
    private String tennhacungcap;
    private String diachi;
    private String email;
    private String sodienthoai;

    public NhaCungCap() {
    }

    public NhaCungCap(int manhacungcap, String tennhacungcap, String diachi, String email, String sodienthoai) {
        this.manhacungcap = manhacungcap;
        this.tennhacungcap = tennhacungcap;
        this.diachi = diachi;
        this.email = email;
        this.sodienthoai = sodienthoai;
    }

    public int getManhacungcap() {
        return manhacungcap;
    }

    public void setManhacungcap(int manhacungcap) {
        this.manhacungcap = manhacungcap;
    }

    public String getTennhacungcap() {
        return tennhacungcap;
    }

    public void setTennhacungcap(String tennhacungcap) {
        this.tennhacungcap = tennhacungcap;
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

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.manhacungcap;
        hash = 67 * hash + Objects.hashCode(this.tennhacungcap);
        hash = 67 * hash + Objects.hashCode(this.diachi);
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + Objects.hashCode(this.sodienthoai);
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
        final NhaCungCap other = (NhaCungCap) obj;
        if (this.manhacungcap != other.manhacungcap) {
            return false;
        }
        if (!Objects.equals(this.tennhacungcap, other.tennhacungcap)) {
            return false;
        }
        if (!Objects.equals(this.diachi, other.diachi)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.sodienthoai, other.sodienthoai);
    }

    @Override
    public String toString() {
        return "NhaCungCap{" + "manhacungcap=" + manhacungcap + ", tennhacungcap=" + tennhacungcap + ", diachi=" + diachi + ", email=" + email + ", sodienthoai=" + sodienthoai + '}';
    }

    
}
