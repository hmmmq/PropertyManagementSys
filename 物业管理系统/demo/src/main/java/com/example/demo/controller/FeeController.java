package com.example.demo.controller;

import com.example.demo.entity.Car;
import com.example.demo.entity.Fee;
import com.example.demo.service.IFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author demo
 * @since 2024-10-21
 */
@RestController
@RequestMapping("/fee")
public class FeeController {

    @Autowired
    private IFeeService feeService;

    // Create a new fee
    @PostMapping
    public boolean createFee(@RequestBody Fee fee) {
        return feeService.save(fee);
    }

    // Get a fee by ID
    @GetMapping("/{id}")
    public Fee getFeeById(@PathVariable Integer id) {
        return feeService.getById(id);
    }

    // Get all fees
    @GetMapping
    public List<Fee> getAllFees() {
        return feeService.list();
    }

    @GetMapping("/user/{id}")
    public List<Fee> getUserAllFees(@PathVariable Integer id) {
        List<Fee> fees = getAllFees();
        fees.removeIf(fee -> !fee.getUserid().equals(id));
        return fees;
    }

    // Update an existing fee
    @PutMapping("/{id}")
    public boolean updateFee(@PathVariable Integer id, @RequestBody Fee fee) {
        fee.setId(id);
        return feeService.updateById(fee);
    }

    // Delete a fee by ID
    @DeleteMapping("/{id}")
    public boolean deleteFee(@PathVariable Integer id) {
        return feeService.removeById(id);
    }
}