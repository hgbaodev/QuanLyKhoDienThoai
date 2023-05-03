package DTO;

import java.sql.Timestamp;

/**
 *
 * @author robot
 */
public class PhieuKiemKeDTO {
    private int maphieukiemke;
    private int nguoitao;
    private Timestamp thoigiantao;

    public PhieuKiemKeDTO(int maphieukiemke, int nguoitao) {
        this.maphieukiemke = maphieukiemke;
        this.nguoitao = nguoitao;
    }
    
    public PhieuKiemKeDTO(int maphieukiemke, int nguoitao, Timestamp thoigiantao) {
        this.maphieukiemke = maphieukiemke;
        this.nguoitao = nguoitao;
        this.thoigiantao = thoigiantao;
    }

    public int getMaphieukiemke() {
        return maphieukiemke;
    }

    public void setMaphieukiemke(int maphieukiemke) {
        this.maphieukiemke = maphieukiemke;
    }

    public int getNguoitao() {
        return nguoitao;
    }

    public void setNguoitao(int nguoitao) {
        this.nguoitao = nguoitao;
    }

    public Timestamp getThoigiantao() {
        return thoigiantao;
    }

    public void setThoigiantao(Timestamp thoigiantao) {
        this.thoigiantao = thoigiantao;
    }

    @Override
    public String toString() {
        return "PhieuKiemKeDTO{" + "maphieukiemke=" + maphieukiemke + ", nguoitao=" + nguoitao + ", thoigiantao=" + thoigiantao + '}';
    }
}
