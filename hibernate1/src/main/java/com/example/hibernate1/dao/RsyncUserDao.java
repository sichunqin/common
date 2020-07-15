package com.example.hibernate1.dao;

import com.example.hibernate1.domain.RsyncUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RsyncUserDao extends JpaRepository<RsyncUser,Long>{

}
