package com.example.demo.controller;

import com.example.demo.entity.Car;
import com.example.demo.service.ICarService;
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
@RequestMapping("/car")
public class CarController {

    @Autowired
    private ICarService carService;

    // Create a new car
    @PostMapping
    public boolean createCar(@RequestBody Car car) {
        Car temp  = getCarById(car.getId());
        if (temp != null) {
            return false;
        }

        return carService.save(car);
    }

    // Get a car by ID
    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Integer id) {
        return carService.getById(id);
    }

    // Get all cars
    @GetMapping
    public List<Car> getAllCars() {
        return carService.list();
    }

    @GetMapping("/user/{id}")
    public List<Car> getUserAllCars(@PathVariable Integer id) {
        List<Car> cars = getAllCars();
        cars.removeIf(car -> !car.getUserid().equals(id));
        return cars;
    }

    // Update an existing car
    @PutMapping("/{id}")
    public boolean updateCar(@PathVariable Integer id, @RequestBody Car car) {
        car.setId(id);
        return carService.updateById(car);
    }

    // Delete a car by ID
    @DeleteMapping("/{id}")
    public boolean deleteCar(@PathVariable Integer id) {
        return carService.removeById(id);
    }
}