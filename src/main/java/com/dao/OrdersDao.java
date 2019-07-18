package com.dao;

import com.entity.Orders;
import com.entity.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao extends
        JpaRepository<Orders, Integer>,
        JpaSpecificationExecutor<Orders> {

    /**
     * 收货
     * @param isconfirmreceipt
     * @param receivingTime
     * @param code
     */
    @Modifying
    @Query(value = "update Orders p set p.isconfirmreceipt = :isconfirmreceipt ,p.receivingTime = :receivingTime where p.code = :code")
    void updateIs(@Param("isconfirmreceipt") Integer isconfirmreceipt,@Param("receivingTime") String receivingTime, @Param("code") String code);

    /**
     * 到货时间
     * @param deliveryTime
     * @param logisticsNumber
     */
    @Modifying
    @Query(value = "update Orders p set p.deliveryTime = :deliveryTime  where p.logisticsNumber = :logisticsNumber")
    void updateDeliveryTimeByLogisticsNumber(@Param("deliveryTime") String deliveryTime, @Param("logisticsNumber") String logisticsNumber);

    /**
     * 发货
     * @param isOutOfStock
     * @param logisticsNumber
     * @param code
     */
    @Modifying
    @Query(value = "update Orders p set p.isOutOfStock = :isOutOfStock,p.logisticsNumber = :logisticsNumber where p.code = :code")
    void ordersOut(@Param("isOutOfStock") String isOutOfStock,@Param("logisticsNumber") String logisticsNumber, @Param("code") String code);

    /**
     * 订单取消
     * @param orderState
     * @param code
     */
    @Modifying
    @Query(value = "update Orders p set p.orderState = :orderState where p.code = :code")
    void ordersCanel(@Param("orderState") int orderState, @Param("code") String code);

    Page<Orders> findByConsigneeAndUsersCodeAndIsOutOfStockContaining(String consignee,String usersCode,String isOutOfStock, Pageable pageable);

    Page<Orders> findByConsigneeAndUsersCodeContaining(String consignee,String usersCode,Pageable pageable);

    Page<Orders> findByUsersCodeAndIsOutOfStockContaining(String usersCode,String isOutOfStock, Pageable pageable);

    Page<Orders> findByUsersCodeContaining(String usersCode, Pageable pageable);

    List<Orders> getByUsersCode(String usersCode);

    /**
     * 得到订单未发货数据条数
     * @param isOutOfStock
     * @param pageable
     * @return
     */
    Page<Orders> findByIsOutOfStockContaining(String isOutOfStock, Pageable pageable);
    /**
     * 得到要修改的数据
     *
     * @param code
     * @return
     */
    Orders getByCode(String code);

    @Modifying
    @Query(value = "update Orders p set p.amount = :amount,p.receivingAddress = :receivingAddress,p.phone = :phone,p.consignee = :consignee where p.code = :code")
    void updateSave(@Param("amount") int amount,@Param("receivingAddress") String receivingAddress,@Param("phone") String phone,@Param("consignee") String consignee, @Param("code") String code);
}
