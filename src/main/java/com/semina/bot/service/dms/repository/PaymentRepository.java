package com.semina.bot.service.dms.repository;

import com.semina.bot.service.dms.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByPayerMobilePhone(String mobilePhone);

    Payment save(Payment payment);

    @Modifying
    @Query(value = "INSERT INTO payment_table_vehicles (payment_paymentid, vehicles_vehicleid) VALUES (:paymentId, :vehicleId)", nativeQuery = true)
    void saveInForeign(@Param("paymentId") String paymentId, @Param("vehicleId") String vehicleId);

    void deleteByPayerMobilePhone(String mobilePhone);


}
