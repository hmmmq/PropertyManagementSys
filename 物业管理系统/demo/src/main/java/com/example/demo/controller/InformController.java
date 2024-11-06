package com.example.demo.controller;

import com.example.demo.entity.Inform;
import com.example.demo.entity.Inform;
import com.example.demo.service.IInformService;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/inform")
public class InformController {

    @Autowired
    private IInformService informService;
    @Autowired
    private IUserService userService;

    // Create a new inform
    @PostMapping
    public boolean createInform(@RequestBody Inform inform) {
        //找用户
        if (userService.getById(inform.getUserid()) == null) {
            return false;
        }
        return informService.save(inform);
    }

    // Get an inform by ID
    @GetMapping("/{id}")
    public Inform getInformById(@PathVariable Integer id) {
        return informService.getById(id);
    }

    // Get all informs
    @GetMapping
    public List<Inform> getAllInforms() {
        return informService.list();
    }

    // Update an existing inform
    @PutMapping("/{id}")
    public boolean updateInform(@PathVariable Integer id, @RequestBody Inform inform) {
        inform.setId(id);
        return informService.updateById(inform);
    }

    // Delete an inform by ID
    @DeleteMapping("/{id}")
    public boolean deleteInform(@PathVariable Integer id) {
        return informService.removeById(id);
    }

    @GetMapping("/user/{id}")
    public List<Inform> getUserAllInforms(@PathVariable Integer id) {
        List<Inform> Informs = getAllInforms();
        Informs.removeIf(Inform -> !Inform.getUserid().equals(id));
        return Informs;
    }

}