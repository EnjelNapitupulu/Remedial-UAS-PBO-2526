package pbo.f01.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parking_area")
public class ParkingArea {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "allowed_type")
    private String allowedType;

    // Relasi 1 Area punya banyak kendaraan. 
    // fetch = EAGER artinya saat area dipanggil, data kendaraannya ikut ditarik.
    @OneToMany(mappedBy = "parkingArea", fetch = FetchType.EAGER)
    @OrderBy("plateNumber ASC")
    private List<Vehicle> vehicles = new ArrayList<>();

    public ParkingArea() {}

    public ParkingArea(String name, int capacity, String allowedType) {
        this.name = name;
        this.capacity = capacity;
        this.allowedType = allowedType;
    }

    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public String getAllowedType() { return allowedType; }
    public List<Vehicle> getVehicles() { return vehicles; }
}