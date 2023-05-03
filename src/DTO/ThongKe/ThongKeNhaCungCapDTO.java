
package DTO.ThongKe;

import java.util.Objects;

public class ThongKeNhaCungCapDTO {
    int mancc;
    String tenncc;
    int ky;
    int soluong;
    double tongtien;
    
    public ThongKeNhaCungCapDTO(){}
    
    public ThongKeNhaCungCapDTO(int mancc, String tenncc, int ky, int soluong, double tongtien) {
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.ky = ky;
        this.soluong = soluong;
        this.tongtien = tongtien;
    }

    public int getMancc() {
        return mancc;
    }

    public String getTenncc() {
        return tenncc;
    }

    public int getKy() {
        return ky;
    }

    public int getSoluong() {
        return soluong;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
    }

    public void setKy(int ky) {
        this.ky = ky;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.mancc;
        hash = 31 * hash + Objects.hashCode(this.tenncc);
        hash = 31 * hash + this.ky;
        hash = 31 * hash + this.soluong;
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.tongtien) ^ (Double.doubleToLongBits(this.tongtien) >>> 32));
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
        if (this.mancc != other.mancc) {
            return false;
        }
        if (this.ky != other.ky) {
            return false;
        }
        if (this.soluong != other.soluong) {
            return false;
        }
        if (Double.doubleToLongBits(this.tongtien) != Double.doubleToLongBits(other.tongtien)) {
            return false;
        }
        return Objects.equals(this.tenncc, other.tenncc);
    }

    @Override
    public String toString() {
        return "ThongKeNhaCungCapDTO{" + "mancc=" + mancc + ", tenncc=" + tenncc + ", ky=" + ky + ", soluong=" + soluong + ", tongtien=" + tongtien + '}';
    }
    
    
}
