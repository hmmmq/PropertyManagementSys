package com.example.demo.controller;

import com.example.demo.entity.House;
import com.example.demo.service.IHouseService;
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
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private IHouseService houseService;

    // Create a new house
    @PostMapping
    public boolean createHouse(@RequestBody House house) {
        House test = getHouseById(house.getId());
        if (test!=null){
            return false;
        }
        return houseService.save(house);
    }

    // Get a house by ID
    @GetMapping("/{id}")
    public House getHouseById(@PathVariable Integer id) {
        return houseService.getById(id);
    }

    // Get all houses
    @GetMapping
    public List<House> getAllHouses() {
        return houseService.list();
    }

    @GetMapping("/user/{id}")
    public List<House> getUserAllHouses(@PathVariable Integer id) {
        List<House> houses = getAllHouses();
        houses.removeIf(house -> !house.getOwnerid().equals(id));
        return houses;
    }

    // Update an existing house
    @PutMapping("/{id}")
    public boolean updateHouse(@PathVariable Integer id, @RequestBody House house) {
        house.setId(id);
        return houseService.updateById(house);
    }

    // Delete a house by ID
    @DeleteMapping("/{id}")
    public boolean deleteHouse(@PathVariable Integer id) {
        return houseService.removeById(id);
    }
}