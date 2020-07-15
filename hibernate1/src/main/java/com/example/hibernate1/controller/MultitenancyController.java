package com.example.hibernate1.controller;

import com.example.hibernate1.dao.TenantDao;
import com.example.hibernate1.domain.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class MultitenancyController {

    @Autowired
    TenantDao tenantDao;
    @RequestMapping("/tenant/rsync/{id}")
    public Map GetTenantById(@PathVariable("id") String tenantId){
        Tenant tenant = tenantDao.findByTenantId(tenantId);
        Map map = new HashMap();
        map.put("FilePath",tenant.getFilePath());
        return map;
    }
    @RequestMapping("/tenants")
    public List<Tenant> GetAll(){
        return tenantDao.findByIsEnabled(true);
    }

    @RequestMapping("/tenant/{id}")
    public Map GetTenant(@PathVariable("id") String tenantId){
        Tenant tenant = tenantDao.findByTenantId(tenantId);
        Map map = new HashMap();
        map.put("FilePath",tenant.getFilePath());
        return map;
    }
    @RequestMapping("/tenant/abc")
    public String GetAbc(){

        return "abc";
    }


}
