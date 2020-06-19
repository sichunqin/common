package com.example.hibernate1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="base_database")
public class DataBaseServer {
    @Id
    @Column(name="id")
    private long id;

    @Column(name="name")
    private String ipAddress;

    @Column(name="port")
    private int port;

    @Column(name="uid")
    private String uid;

    @Column(name="pwd")
    private String password;

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

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DataBaseServer{" +
                "id=" + id +
                ", ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                ", uid='" + uid + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
