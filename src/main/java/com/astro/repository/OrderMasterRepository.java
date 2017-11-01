package com.astro.repository;

import com.astro.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by astro on 2017/10/28.
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>{

    /*分页*/
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);




}
