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

    private String brand, model, serial, picUrl;
    boolean hasBoot;
    Integer seat, placeInBoot, freeSeat, freePlace;


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

    public String getPicUrl() {
        return picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public boolean hasBoot() {
        return hasBoot;
    }
    public void setHasBoot(boolean hasBoot) {
        this.hasBoot = hasBoot;
    }

    public Integer getSeat() {
        return seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Integer getPlaceInBoot() {
        return placeInBoot;
    }
    public void setPlaceInBoot(int placeInBoot) {
        this.placeInBoot = placeInBoot;
    }

    public Integer getFreeSeat() {
        return freeSeat;
    }
    public void setFreeSeat(int freeSeat) {
        this.freeSeat = freeSeat;
    }

    public Integer getFreePlace() {
        return freePlace;
    }
    public void setFreePlace(int freePlace) {
        this.freePlace = freePlace;
    }
}
