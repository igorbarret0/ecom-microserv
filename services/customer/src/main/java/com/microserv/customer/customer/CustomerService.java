package com.microserv.customer.customer;

import com.microserv.customer.customer.exceptions.CustomerNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public String createCustomer(CustomerRequest request) {

        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {

        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Cannot update customer: No customer found with the provided ID: " + request.id()
                ));

        mergerCustomer(customer, request);
        customerRepository.save(customer);
    }

   public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findAll().stream()
                .map(customerMapper::toCustomerResponse)
                .collect(Collectors.toList());
   }

   public boolean existsById(String id) {

        return customerRepository.findById(id)
                .isPresent(); // True or False
   }

   public CustomerResponse findById(String id) {

        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cannot find customer: No customer found with the provided ID: " + id));

        return customerMapper.toCustomerResponse(customer);
   }

   public void deleteCustomer(String id) {

        customerRepository.deleteById(id);
   }

    private void mergerCustomer(Customer customer, CustomerRequest request) {

        if (StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
        }

        if (StringUtils.isNotBlank(request.lastName())) {
            customer.setLastName(request.lastName());
        }

        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }

       if (request.address() != null) {
           customer.setAddress(request.address());
       }

    }

}
