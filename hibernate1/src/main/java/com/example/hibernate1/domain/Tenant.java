package com.example.hibernate1.domain;

import javax.persistence.*;

@Entity
@Table(name="base_tenant")
public class Tenant {
    @Id
    @Column(name="id")
    private long id;

    @Column(name="code")
    private String tenantId;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Column(name="databasename")
    private String dataBaseName;

    @Column(name="isenabled")
    private boolean isEnabled;


    @Column(name="usernum")
    private int maxUserNum;

    @ManyToOne(targetEntity = DataBaseServer.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "databaseid",referencedColumnName = "id")
    private DataBaseServer dataBaseServer;

    public DataBaseServer getDataBaseServer() {
        return dataBaseServer;
    }

    public void setDataBaseServer(DataBaseServer dataBaseServer) {
        this.dataBaseServer = dataBaseServer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }


    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public int getMaxUserNum() {
        return maxUserNum;
    }

    public void setMaxUserNum(int maxUserNum) {
        this.maxUserNum = maxUserNum;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id=" + id +
                ", dataBaseName='" + dataBaseName + '\'' +
                ", isEnabled=" + isEnabled +

                ", maxUserNum=" + maxUserNum +
                ", databaseserver=" + dataBaseServer.toString() +
                '}';
    }

    //jdbc:sqlserver://192.168.70.40:1433;Databasename=DaXinJavaBS_test2020060401
    public String getUrl(){

        return "jdbc:sqlserver://" + dataBaseServer.getIpAddress() + ";Databasename=" + dataBaseName;
    }
}
