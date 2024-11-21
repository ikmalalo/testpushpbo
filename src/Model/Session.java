/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ikmal
 */
public class Session {
     private static Mahasiswa mahasiswa = null;

    public static void setMahasiswa(Mahasiswa mhs) {
        mahasiswa = mhs;
    }

    public static Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public static void clear() {
        mahasiswa = null; 
    }
}
