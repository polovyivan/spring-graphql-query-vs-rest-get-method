package com.polovyi.ivan.controller;

import com.polovyi.ivan.dto.CustomerResponse;
import com.polovyi.ivan.dto.PurchaseTransactionResponse;
import com.polovyi.ivan.service.CustomerService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Component
public record CustomerGraphQLController(CustomerService customerService) implements GraphQLQueryResolver {

    public String hello() {
        return "Hello, world!";
    }

    public List<CustomerResponse> customers(
           String fullName,
           String phoneNumber,
           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAt, String paymentType) {
        return customerService.getAllCustomersWithFilters(fullName, phoneNumber, createdAt, paymentType);
    }

    public CustomerResponse customer(@NotNull String customerId) {
        return customerService.getCustomersById(customerId);
    }

    public List<PurchaseTransactionResponse> customerTransactions(@NotNull String customerId) {
        return customerService.getAllCustomerPurchaseTransactions(customerId);
    }

    public PurchaseTransactionResponse customerTransaction(@NotNull String customerId,
            @NotNull String purchaseTransactionId) {
        return customerService.getCustomerPurchaseTransactionsById(customerId, purchaseTransactionId);
    }

}
