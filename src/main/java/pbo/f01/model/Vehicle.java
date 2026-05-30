package pbo.f01.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "owner")
    private String owner;

    @Column(name = "type")
    private String type;

    // Relasi Banyak kendaraan ke 1 Area
    @ManyToOne
    @JoinColumn(name = "area_name")
    private ParkingArea parkingArea;

    public Vehicle() {}

    public Vehicle(String plateNumber, String owner, String type) {
        this.plateNumber = plateNumber;
        this.owner = owner;
        this.type = type;
    }

    public String getPlateNumber() { return plateNumber; }
    public String getOwner() { return owner; }
    public String getType() { return type; }
    public ParkingArea getParkingArea() { return parkingArea; }
    
    public void setParkingArea(ParkingArea parkingArea) {
        this.parkingArea = parkingArea;
    }
}