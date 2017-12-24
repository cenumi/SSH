package com.c1yde3.ssh.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by wangyonghao8 on 2017/12/24.
 */
@Entity
public class Trans {
    private int id;
    private String number;
    private String depature;
    private String arrival;
    private String passby;
    private String ticket;
    private String day;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "number", nullable = false, length = 15)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "depature", nullable = false, length = 10)
    public String getDepature() {
        return depature;
    }

    public void setDepature(String depature) {
        this.depature = depature;
    }

    @Basic
    @Column(name = "arrival", nullable = false, length = 10)
    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    @Basic
    @Column(name = "passby", nullable = false, length = 1000)
    public String getPassby() {
        return passby;
    }

    public void setPassby(String passby) {
        this.passby = passby;
    }

    @Basic
    @Column(name = "ticket", nullable = true, length = 400)
    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Basic
    @Column(name = "day", nullable = true, length = 20)
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trans trans = (Trans) o;

        if (id != trans.id) return false;
        if (number != null ? !number.equals(trans.number) : trans.number != null) return false;
        if (depature != null ? !depature.equals(trans.depature) : trans.depature != null) return false;
        if (arrival != null ? !arrival.equals(trans.arrival) : trans.arrival != null) return false;
        if (passby != null ? !passby.equals(trans.passby) : trans.passby != null) return false;
        if (ticket != null ? !ticket.equals(trans.ticket) : trans.ticket != null) return false;
        if (day != null ? !day.equals(trans.day) : trans.day != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (depature != null ? depature.hashCode() : 0);
        result = 31 * result + (arrival != null ? arrival.hashCode() : 0);
        result = 31 * result + (passby != null ? passby.hashCode() : 0);
        result = 31 * result + (ticket != null ? ticket.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        return result;
    }
}
