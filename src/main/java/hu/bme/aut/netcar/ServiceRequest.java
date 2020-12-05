package hu.bme.aut.netcar;

//import org.springframework.data.geo.Coord;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer SRID;


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date publicationTime) {
        this.startTime = publicationTime;
    }

    @Temporal(TemporalType.TIME)
    private Date startTime;



    private Date finishTime;

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
        this.startTime = new Date(c.getTimeInMillis());
    }

    public ServiceRequest(Integer driver, Integer passenger, Coord destination, Integer payment){
        this.driverID = driver;
        this.passengerID = passenger;
        this.destinationPos = destination;
        this.sRstatus = SRstatus.PENDING;
        this.payment = payment;

            Calendar c = Calendar.getInstance();
            this.startTime = new Date(c.getTimeInMillis());


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

    public Integer getSRID() {
        return SRID;
    }

    public void setSRID(Integer SRID) {
        this.SRID = SRID;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }



}
