package com.example.hibernate1.domain;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.lang.annotation.Target;

@Entity
@Table(name="Base_Rsync")
public class Rsync {
    @Id
    @Column(name="id" )
    private long id;
    @Column(name="name")
    private String ipAddress;
    @Column(name="rsyncport")
    private String rsyncPort;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getRsyncPort() {
        return rsyncPort;
    }

    public void setRsyncPort(String rsyncPort) {
        this.rsyncPort = rsyncPort;
    }

    @Override
    public String toString() {
        return "Rsync{" +
                "id=" + id +
                ", ipAddress='" + ipAddress + '\'' +
                ", rsyncPort='" + rsyncPort + '\'' +
                '}';
    }
}
