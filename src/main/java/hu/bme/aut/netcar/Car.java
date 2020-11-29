package hu.bme.aut.netcar;

import javax.persistence.*;


@Entity
public class Car {
    @Id
    @Column(name = "user_id")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String brand, model, serial, pic;
    private Boolean hasBoot;
    private Integer seats, placeInBoot, freeSeats, freePlace;


    public Car() {

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }
    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    public Boolean getHasBoot() {
        return hasBoot;
    }
    public void setHasBoot(Boolean hasBoot) {
        this.hasBoot = hasBoot;
    }

    public Integer getSeats() {
        return seats;
    }
    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getPlaceInBoot() {
        return placeInBoot;
    }
    public void setPlaceInBoot(Integer placeInBoot) {
        this.placeInBoot = placeInBoot;
    }

    public Integer getFreeSeats() {
        return freeSeats;
    }
    public void setFreeSeats(Integer freeSeats) {
        this.freeSeats = freeSeats;
    }

    public Integer getFreePlace() {
        return freePlace;
    }
    public void setFreePlace(Integer freePlace) {
        this.freePlace = freePlace;
    }
}
