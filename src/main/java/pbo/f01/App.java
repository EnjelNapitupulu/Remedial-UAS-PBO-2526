package pbo.f01;

import java.util.Scanner;

/**
 * Driver class utama
 * Nama: [Nama Anda]
 * Nim: [NIM Anda]
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Membaca input baris per baris hingga tidak ada token atau teks lagi
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            
            if (input == null || input.trim().isEmpty()) {
                break;
            }

            // TODO: Mahasiswa mengimplementasikan logika parsing string berbasis karakter '#'
            // serta mengeksekusi fitur: area-add, vehicle-add, park, dan display-all
            
        }

        scanner.close();
    }
}