package DTO;

/**
 *
 * @author robot
 */
public class PhieuKiemKeDTO {
    private int maphieukiemke;
    private int nguoitao;

    public PhieuKiemKeDTO(int maphieukiemke, int nguoitao) {
        this.maphieukiemke = maphieukiemke;
        this.nguoitao = nguoitao;
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

    @Override
    public String toString() {
        return "PhieuKiemKeDTO{" + "maphieukiemke=" + maphieukiemke + ", nguoitao=" + nguoitao + '}';
    }
}
