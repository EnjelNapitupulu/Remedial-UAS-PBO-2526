package pbo.f01;

import pbo.f01.model.ParkingArea;
import pbo.f01.model.Vehicle;

/**
 * Driver class utama
 * Nama: Enjel Ayuti Napitupulu
 * Nim: 12S24056
 */

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // BARIS INI YANG TADI KETINGGALAN: Menyalakan koneksi ke database
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("parkit-pu");
        EntityManager em = emf.createEntityManager();
        
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            
            // Opsional: Ketik 'exit' untuk berhenti nge-run
            if (line.equalsIgnoreCase("exit")) break;

            String[] tokens = line.split("#");
            String command = tokens[0];

            // TUGAS 3: Register Entities
            if (command.equals("area-add")) {
                em.getTransaction().begin();
                ParkingArea area = new ParkingArea(tokens[1], Integer.parseInt(tokens[2]), tokens[3]);
                em.persist(area); 
                em.getTransaction().commit();

            } else if (command.equals("vehicle-add")) {
                em.getTransaction().begin();
                Vehicle vehicle = new Vehicle(tokens[1], tokens[2], tokens[3]);
                em.persist(vehicle); 
                em.getTransaction().commit();

            // TUGAS 4: Assigning Vehicle
            } else if (command.equals("park")) {
                em.getTransaction().begin();
                Vehicle vehicle = em.find(Vehicle.class, tokens[1]);
                ParkingArea area = em.find(ParkingArea.class, tokens[2]);

                if (vehicle != null && area != null) {
                    if (vehicle.getType().equals(area.getAllowedType())) {
                        int currentOccupancy = area.getVehicles().size();
                        if (currentOccupancy < area.getCapacity()) {
                            vehicle.setParkingArea(area);
                            em.merge(vehicle); 
                            
                            if (!area.getVehicles().contains(vehicle)) {
                                area.getVehicles().add(vehicle);
                            }
                        }
                    }
                }
                em.getTransaction().commit();

            // TUGAS 5: Display All
            } else if (command.equals("display-all")) {
                TypedQuery<ParkingArea> query = em.createQuery(
                    "SELECT a FROM ParkingArea a ORDER BY a.name ASC", 
                    ParkingArea.class
                );
                List<ParkingArea> areas = query.getResultList();

                for (ParkingArea area : areas) {
                    int occupancy = area.getVehicles().size();
                    System.out.println(area.getName() + " " + area.getAllowedType() + " " + area.getCapacity() + "|" + occupancy);
                    
                    for (Vehicle v : area.getVehicles()) {
                        System.out.println(v.getPlateNumber() + " " + v.getOwner() + " " + v.getType());
                    }
                }
            }
        }

        scanner.close();
        em.close();
        emf.close();
    }
}