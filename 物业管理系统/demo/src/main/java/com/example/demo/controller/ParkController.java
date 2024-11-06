package com.example.demo.controller;

import com.example.demo.entity.Park;
import com.example.demo.service.IParkService;
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
@RequestMapping("/park")
public class ParkController {

    @Autowired
    private IParkService parkService;

    // Create a new park
    @PostMapping
    public boolean createPark(@RequestBody Park park) {
        Park temp = getParkById(park.getId());
        if (temp != null) {
            return false;
        }
        return parkService.save(park);
    }

    // Get a park by ID
    @GetMapping("/{id}")
    public Park getParkById(@PathVariable Integer id) {
        return parkService.getById(id);
    }

    // Get all parks
    @GetMapping
    public List<Park> getAllParks() {
        return parkService.list();
    }

    // Update an existing park
    @PutMapping("/{id}")
    public boolean updatePark(@PathVariable Integer id, @RequestBody Park park) {
        park.setId(id);
        return parkService.updateById(park);
    }

    // Delete a park by ID
    @DeleteMapping("/{id}")
    public boolean deletePark(@PathVariable Integer id) {
        return parkService.removeById(id);
    }

    @GetMapping("/user/{id}")
    public List<Park> getUserAllParks(@PathVariable Integer id) {
        List<Park> parks = getAllParks();
        parks.removeIf(park -> !park.getUserid().equals(id));
        return parks;
    }
}