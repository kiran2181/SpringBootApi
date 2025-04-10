package com.fullstack.Controller;

import com.fullstack.Model.Customer;
import com.fullstack.exception.RecordNotFoundException;
import jakarta.validation.constraints.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

    List<Customer> listCust = Stream.of(new Customer(101,"Kiran","Masur",12345,85000),
            new Customer(102,"Ram","Karad",2345,45000),
            new Customer(103,"Omkar","Pune",3456,65000),
            new Customer(104,"Akash","Tirupati",4567,55000),
            new Customer(105,"Ramesh","Mumbai",5678,95000)).toList();

    @GetMapping("/findall")
    public ResponseEntity<List<Customer>> findAllCust()
    {
        return ResponseEntity.ok(listCust);
    }

    @GetMapping("/findbyid")
    public ResponseEntity<List<Customer>> findById(@RequestParam int custId)
    {
        return ResponseEntity.ok(listCust.stream().filter(cust->cust.getCustId()==custId).toList());
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Customer>> sortByName()
    {
        return ResponseEntity.ok(listCust.stream().sorted(Comparator.comparing(Customer::getCustName)).toList());
    }

    @PutMapping("/update/{custId}")
    public ResponseEntity<Customer> update(@PathVariable int custId,@RequestBody Customer customer) {

//     for(Customer customer1 : findAllCust().getBody())
//     {
//         if(customer1.getCustId() == custId)
//         {
//             customer1.setCustName(customer.getCustName());
//             customer1.setCustAddress(customer.getCustAddress());
//             customer1.setCustAccNo(customer.getCustAccNo());
//             customer1.setCustAccBal(customer.getCustAccBal());
//
//             return ResponseEntity.ok(customer1);
//         }
//
//     }
//        return ResponseEntity.notFound().build();

        List<Customer> custlist = findAllCust().getBody();

        Customer customer1 = custlist.stream().filter(cust->cust.getCustId() == custId).findFirst().orElseThrow(()->new RecordNotFoundException("Customer Id does not Exist"));

        customer1.setCustName(customer.getCustName());
        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustAccNo(customer.getCustAccNo());
        customer1.setCustAccBal(customer.getCustAccBal());

        return ResponseEntity.ok(customer1);
    }
}
