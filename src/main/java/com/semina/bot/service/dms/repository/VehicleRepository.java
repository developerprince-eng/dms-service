package com.semina.bot.service.dms.repository;

import com.semina.bot.service.dms.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle findByVehicleID(String vehicleId);

    Vehicle findByVehicleVin(String vehicleVin);

    @Modifying
    @Query(value = "INSERT INTO customer_table_vehicles (customer_table_customerid, vehicles_vehicleid) VALUES (:customerId, :vehicleId)", nativeQuery = true)
    void saveInForeign(@Param("customerId") String customerId,@Param("vehicleId") String vehicleId);

    Vehicle save(Vehicle vehicle);

    void deleteByVehicleID(String vehicleId);
}
