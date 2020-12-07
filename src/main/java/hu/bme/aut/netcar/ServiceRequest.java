package hu.bme.aut.netcar;

//import org.springframework.data.geo.Coord;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer srid;


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String publicationTime) {
        this.startTime = publicationTime;
    }


    private String startTime;
    private String finishTime;

    private Integer payment;
    private Integer driverID;
    private Integer passengerID;

    public SRstatus getsRstatus() {
        return sRstatus;
    }

    public void setsRstatus(SRstatus sRstatus) {
        this.sRstatus = sRstatus;
    }

    private SRstatus sRstatus;

    @Column(name = "pont")
   // @Type(type = "org.springframework.data.geo.Coord")
    private Coord destinationPos;


    public ServiceRequest() {
        this.driverID = 99999999;
        this.passengerID = 999999;
        this.destinationPos = new Coord(1324234234234.23,4324234234.56);
        this.payment = 65545454;
        this.sRstatus = SRstatus.PENDING;
        Calendar c = Calendar.getInstance();
        this.startTime = new Date(c.getTimeInMillis()).toString();
    }

    public ServiceRequest(Integer driver, Integer passenger, Coord destination, Integer payment){
        this.driverID = driver;
        this.passengerID = passenger;
        this.destinationPos = destination;
        this.sRstatus = SRstatus.PENDING;
        this.payment = payment;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Calendar c = Calendar.getInstance();
        this.startTime = sdf.format(new Date(c.getTimeInMillis()));
        this.finishTime = "";
    }

    public Integer getDriverID() {
        return driverID;
    }

    public void setDriverID(Integer driverID) {
        this.driverID = driverID;
    }

    public Integer getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(Integer passengerID) {
        this.passengerID = passengerID;
    }

    public Coord getDestinationPos() {
        return destinationPos;
    }

    public void setDestinationPos(Coord destinationPos) {
        this.destinationPos = destinationPos;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Integer getSrid() {
        return srid;
    }

    public void setSrid(Integer SRID) {
        this.srid = SRID;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }



}
