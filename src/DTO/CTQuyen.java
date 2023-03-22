/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class CTQuyen {
    private int maquyen;
    private int manhomquyen;
    private int check;

    public CTQuyen() {
    }

    public CTQuyen(int maquyen, int manhomquyen, int check) {
        this.maquyen = maquyen;
        this.manhomquyen = manhomquyen;
        this.check = check;
    }

    public int getMaquyen() {
        return maquyen;
    }

    public void setMaquyen(int maquyen) {
        this.maquyen = maquyen;
    }

    public int getManhomquyen() {
        return manhomquyen;
    }

    public void setManhomquyen(int manhomquyen) {
        this.manhomquyen = manhomquyen;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.maquyen;
        hash = 53 * hash + this.manhomquyen;
        hash = 53 * hash + this.check;
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
        final CTQuyen other = (CTQuyen) obj;
        return true;
    }

    @Override
    public String toString() {
        return "CTQuyen{" + "maquyen=" + maquyen + ", manhomquyen=" + manhomquyen + ", check=" + check + '}';
    }
    
}
