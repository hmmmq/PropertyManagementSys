package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.service.IAdminService;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @PostMapping("/login")
    public Admin login(@RequestBody Admin admin) {
        Admin adminById = getAdminById(admin.getId());
        if (adminById != null && adminById.getPassword().equals(admin.getPassword())) {
            return adminById;
        } else {
            return null;
        }
    }

    // Create a new admin
    @PostMapping
    public boolean createAdmin(@RequestBody Admin admin) {
        return adminService.save(admin);
    }

    // Get an admin by ID
    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Integer id) {
        return adminService.getById(id);
    }

    // Get all admins
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.list();
    }

    // Update an existing admin
    @PutMapping("/{id}")
    public boolean updateAdmin(@PathVariable Integer id, @RequestBody Admin admin) {
        return adminService.updateById(admin);
    }

    // Delete an admin by ID
    @DeleteMapping("/{id}")
    public boolean deleteAdmin(@PathVariable Integer id) {
        return adminService.removeById(id);
    }
}