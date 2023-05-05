
package DTO.ThongKe;

import java.util.Objects;

public class ThongKeNhaCungCapDTO {
    int mancc;
    String tenncc;
    int soluong;
    double tongtien;
    
    public ThongKeNhaCungCapDTO(){}

    public ThongKeNhaCungCapDTO(int mancc, String tenncc, int soluong, double tongtien) {
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.soluong = soluong;
        this.tongtien = tongtien;
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

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.mancc;
        hash = 97 * hash + Objects.hashCode(this.tenncc);
        hash = 97 * hash + this.soluong;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.tongtien) ^ (Double.doubleToLongBits(this.tongtien) >>> 32));
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
        final ThongKeNhaCungCapDTO other = (ThongKeNhaCungCapDTO) obj;
        return true;
    }

    @Override
    public String toString() {
        return "ThongKeNhaCungCapDTO{" + "mancc=" + mancc + ", tenncc=" + tenncc + ", soluong=" + soluong + ", tongtien=" + tongtien + '}';
    }
    
    
    
}
