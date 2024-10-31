package com.example.demo.controller;

import com.example.demo.entity.Fix;
import com.example.demo.entity.User;
import com.example.demo.service.IFixService;
import com.example.demo.service.IUserService;
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
@RequestMapping("/fix")
public class FixController {

    @Autowired
    private IFixService fixService;
    @Autowired
    private IUserService userService;

    // Create a new fix
    @PostMapping
    public boolean createFix(@RequestBody Fix fix) {
        User user = userService.getById(fix.getUserid());
        fix.setPhone(user.getPhone());
        return fixService.save(fix);
    }

    // Get a fix by ID
    @GetMapping("/{id}")
    public Fix getFixById(@PathVariable Integer id) {
        return fixService.getById(id);
    }

    // Get all fixes
    @GetMapping
    public List<Fix> getAllFixes() {
        return fixService.list();
    }

    @GetMapping("/user/{id}")
    public List<Fix> getUserAllFixes(@PathVariable Integer id) {
        List<Fix> fixes = getAllFixes();
        fixes.removeIf(fix -> !fix.getUserid().equals(id));
        return fixes;
    }

    // Update an existing fix
    @PutMapping("/{id}")
    public boolean updateFix(@PathVariable Integer id, @RequestBody Fix fix) {
        fix.setId(id);
        return fixService.updateById(fix);
    }
    @PutMapping("/process/{id}")
    public boolean updateFixprocess(@PathVariable Integer id) {
        Fix fix = fixService.getById(id);
        fix.setStatus("已处理");
        return fixService.updateById(fix);
    }

    // Delete a fix by ID
    @DeleteMapping("/{id}")
    public boolean deleteFix(@PathVariable Integer id) {
        return fixService.removeById(id);
    }
}