package com.example.controller;

import com.example.dto.DtoAddress;
import com.example.dto.DtoAddressIU;

import java.util.List;

public interface IAddressController {

    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
    public List<DtoAddress> getAllAddress();

    public DtoAddress findAddressById(Long id);

    public boolean deleteAddress(Long id);
    public DtoAddress updateAddress(Long id,DtoAddressIU dtoAddressIU);
}
