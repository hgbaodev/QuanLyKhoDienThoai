package DTO.ThongKe;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author robot
 */
public class ThongKeTungNgayTrongThangDTO{
    private Date ngay;
    private int chiphi;
    private int doanhthu;
    private int loinhuan;

    public ThongKeTungNgayTrongThangDTO(Date ngay, int chiphi, int doanhthu, int loinhuan) {
        this.ngay = ngay;
        this.chiphi = chiphi;
        this.doanhthu = doanhthu;
        this.loinhuan = loinhuan;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getChiphi() {
        return chiphi;
    }

    public void setChiphi(int chiphi) {
        this.chiphi = chiphi;
    }

    public int getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(int doanhthu) {
        this.doanhthu = doanhthu;
    }

    public int getLoinhuan() {
        return loinhuan;
    }

    public void setLoinhuan(int loinhuan) {
        this.loinhuan = loinhuan;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.ngay);
        hash = 29 * hash + this.chiphi;
        hash = 29 * hash + this.doanhthu;
        hash = 29 * hash + this.loinhuan;
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
        final ThongKeTungNgayTrongThangDTO other = (ThongKeTungNgayTrongThangDTO) obj;
        if (this.chiphi != other.chiphi) {
            return false;
        }
        if (this.doanhthu != other.doanhthu) {
            return false;
        }
        if (this.loinhuan != other.loinhuan) {
            return false;
        }
        return Objects.equals(this.ngay, other.ngay);
    }

    @Override
    public String toString() {
        return "ThongKeTungNgayTrongThangDTO{" + "ngay=" + ngay + ", chiphi=" + chiphi + ", doanhthu=" + doanhthu + ", loinhuan=" + loinhuan + '}';
    }
    
    
    
}
