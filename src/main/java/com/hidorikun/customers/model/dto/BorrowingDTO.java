package com.hidorikun.customers.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingDTO {
    private Long id;
    private Long bookId;
    private Long customerId;
    private Date borrowedOn;
    private Date returnedOn;
}
