package com.example.controller.impl;

import com.example.controller.IAddressController;
import com.example.dto.DtoAddress;
import com.example.dto.DtoAddressIU;
import com.example.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/api/address")
public class AddressControllerImpl implements IAddressController {

    @Autowired
    private IAddressService addressService;

    @PostMapping("/save")
    @Override
    public DtoAddress saveAddress(@RequestBody DtoAddressIU dtoAddressIU) {
        return addressService.saveAddress(dtoAddressIU);
    }


    @GetMapping("/list")
    @Override
    public List<DtoAddress> getAllAddress() {
        return addressService.getAllAddress();
    }

    @GetMapping(path = "/list/{id}")
    @Override
    public DtoAddress findAddressById(@PathVariable(name = "id") Long id) {
        return addressService.findAddressById(id);
    }


    @DeleteMapping("/delete/{id}")
    @Override
    public boolean deleteAddress(@PathVariable(name = "id") Long id) {
        return addressService.deleteAddress(id);
    }


    @PutMapping("/update/{id}")
    @Override
    public DtoAddress updateAddress(@PathVariable(name = "id") Long id, @RequestBody DtoAddressIU dtoAddressIU) {
        return addressService.updateAddress(id, dtoAddressIU);
    }
}
