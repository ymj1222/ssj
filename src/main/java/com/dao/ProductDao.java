package com.dao;

import com.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer>, JpaSpecificationExecutor {
 Product getProductByCode(Long code);

 @Modifying
 @Query("update Product  set name= ?1,price= ?2,type= ?3,brandCode= ?4,size= ?5,sellValue= ?6,marketValue= ?7,color= ?8,induction= ?9,agentCode=?11 where code= ?10")
 void updateSave(String name, Float price, Long type, Long brandcode, String size, Float sell, Float market, String color, String induction, Long code,Long agent);
 @Modifying
 @Query("update Product set auditStatus= ?1 where code= ?2")
 void updateStatus(Integer status, Long code);
 @Query("select p from Product p where p.auditStatus=3 and p.isEffective=1 and p.code in(select w.productCode from Warehouse w where w.amount > 0)")
 List<Product> selecttoqsale();
 @Query("select p from Product p where p.type= ?1 and p.auditStatus = 3 and p.isEffective=1 and p.code in(select w.productCode from Warehouse w where w.amount > 0)")
 List<Product> selecttoproject(Long type);
 @Modifying
 @Query("update Product set shelftime= ?1,isEffective= ?2 where code= ?3")
 void updateShelftime(Date time, Integer isE, Long code);
 @Query("select p from Product p where  p.auditStatus = 3 and p.name like '%'||?1||'%' and p.isEffective=1 and p.code in(select w.productCode from Warehouse w where w.amount > 0)")
 List<Product> selecttoproduct(String name);
 @Modifying
 @Query(value = "update Products set agent_Code= null,agent_id = null,is_Effective=0 where agent_id= ?1",nativeQuery = true)
 void down(int code);
 List<Product> getCodeByAuditStatusAndIsEffective(Integer status, Integer isE);
}
