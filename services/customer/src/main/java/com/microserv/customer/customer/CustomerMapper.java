package com.microserv.customer.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {

        if (request == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setId(request.id());
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setAddress(request.address());

        return customer;
    }

    public CustomerResponse toCustomerResponse(Customer customer) {

        if (customer == null) {
            return null;
        }

        CustomerResponse customerResponse = new CustomerResponse(customer.getId(), customer.getFirstName(),
                customer.getLastName(), customer.getEmail(), customer.getAddress());

        return customerResponse;
    }

}
