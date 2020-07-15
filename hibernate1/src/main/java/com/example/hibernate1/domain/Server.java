package com.example.hibernate1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="base_server")
public class Server{
    @Id
    @Column(name="id")
    private long id;
    @Column(name="code")
    private String ip;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    @Override
    public String toString() {
        return "Server{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                '}';
    }
}
