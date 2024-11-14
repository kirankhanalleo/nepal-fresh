package com.nepalfresh.app.mapper;

import com.nepalfresh.common.constant.StatusConstant;
import com.nepalfresh.common.dto.request.SignUpRequest;
import com.nepalfresh.common.repo.StatusRepository;
import com.nepalfresh.entity.Cart;
import com.nepalfresh.entity.Customer;
import com.nepalfresh.repository.cart.CartRepository;
import com.nepalfresh.repository.customer.CustomerRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class SignUpMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected StatusRepository statusRepository;
    @Autowired
    protected CartRepository cartRepository;

    public Customer mapToSignUp(SignUpRequest signUpRequest){
        Customer customer = new Customer();
        customer.setFullName(signUpRequest.getFullName());
        customer.setEmail(signUpRequest.getEmail());
        customer.setUsername(signUpRequest.getEmail());
        customer.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        customer.setActive(true);
        customer.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
        customer.setRegisteredDate(new Date());
        Customer newCustomer = customerRepository.save(customer);
        Cart cart = new Cart();
        cart.setCustomer(newCustomer);
        cart.setCreatedAt(Timestamp.from(Instant.now()));
        cart.setUpdatedAt(Timestamp.from(Instant.now()));
        cartRepository.save(cart);
        return customer;
    }
}
