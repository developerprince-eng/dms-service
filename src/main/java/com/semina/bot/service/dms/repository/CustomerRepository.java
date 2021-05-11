package com.semina.bot.service.dms.repository;

import com.semina.bot.service.dms.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByCustomerID(String customerId);

    Customer findByMobilePhone(String mobilePhone);

    Customer save(Customer customer);

    void deleteByCustomerID(String customerId);

}
