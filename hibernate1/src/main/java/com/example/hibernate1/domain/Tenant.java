package com.example.hibernate1.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="base_tenant")
public class Tenant {
    @Id
    @Column(name="id")
    private long id;
    @Column(name="code")
    private String tenantId;
    @Column(name="databasename")
    private String dataBaseName;
    @Column(name="isenabled")
    private boolean isEnabled;
    @Column(name="usernum")
    private int maxUserNum;
    @Column(name="endtime")
    private Date endTime;
    @Column(name="uploadlimit")
    private int uploadBandwidthLimit;
    @Column(name="filepath")
    private String filePath;
    @ManyToOne(targetEntity = DataBaseServer.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "databaseid",referencedColumnName = "id")
    private DataBaseServer dataBaseServer;
    @ManyToOne(targetEntity = Folder.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "folderid", referencedColumnName = "id")
    private Folder folder;
    @ManyToOne(targetEntity = Rsync.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "rsyncid", referencedColumnName = "id")
    private Rsync rsync;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public Rsync getRsync() {
        return rsync;
    }

    public void setRsync(Rsync rsync) {
        this.rsync = rsync;
    }

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
                "dataBaseName='" + dataBaseName + '\'' +
                ", isEnabled=" + isEnabled +
                ", maxUserNum=" + maxUserNum +
                ", endTime=" + endTime +
                ", uploadBandwidthLimit=" + uploadBandwidthLimit +
                ", filePath='" + filePath + '\'' +
                ", dataBaseServer=" + dataBaseServer +
                ", folder=" + folder +
                ", rsync=" + rsync +
                '}';
    }

    //jdbc:sqlserver://192.168.70.40:1433;Databasename=DaXinJavaBS_test2020060401
    public String getUrl(){

        return "jdbc:sqlserver://" + dataBaseServer.getIpAddress() + ";Databasename=" + dataBaseName;
    }
}
