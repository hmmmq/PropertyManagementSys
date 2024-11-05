package com.example.demo.controller;
import com.example.demo.entity.Contract;
import com.example.demo.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xxx
 * @since 2024-11-05
 */
@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private IContractService contractService;

    // Create a new contract
    @PostMapping
    public boolean createContract(@RequestBody Contract contract) {
        Contract temp = getContractById(contract.getId());
        if (temp != null) {
            return false;
        }
        return contractService.save(contract);
    }

    // Get a contract by ID
    @GetMapping("/{id}")
    public Contract getContractById(@PathVariable Integer id) {
        return contractService.getById(id);
    }

    // Update an existing contract
    @PutMapping("/{id}")
    public boolean updateContract(@PathVariable Integer id, @RequestBody Contract contract) {
        contract.setId(id);
        return contractService.updateById(contract);
    }

    // Delete a contract by ID
    @DeleteMapping("/{id}")
    public boolean deleteContract(@PathVariable Integer id) {
        return contractService.removeById(id);
    }

    @GetMapping
    public List<Contract> getAllContracts() {
        return contractService.list();
    }

    @GetMapping("/user/{id}")
    public List<Contract> getUserAllContracts(@PathVariable Integer id) {
        List<Contract> Contracts = getAllContracts();
        Contracts.removeIf(Contract -> !Contract.getSignUserid().equals(id));
        return Contracts;
    }

}