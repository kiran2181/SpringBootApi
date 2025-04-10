package com.fullstack.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Customer {

    private int custId;

    private String custName;

    private String custAddress;

    private long custAccNo;

    private double custAccBal;

}
