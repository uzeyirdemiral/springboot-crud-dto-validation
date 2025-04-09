package com.example.services.impl;

import com.example.dto.DtoAddress;
import com.example.dto.DtoAddressIU;
import com.example.dto.DtoCustomer;
import com.example.entites.Address;
import com.example.entites.Customer;
import com.example.repository.AddressRepository;
import com.example.repository.CustomerRepository;
import com.example.services.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
        DtoAddress dtoAddress = new DtoAddress();
        Address address = new Address();
        Optional<Customer> optional = customerRepository.findById(dtoAddressIU.getCustomer().getId());
        if (optional.isPresent()) {
            BeanUtils.copyProperties(dtoAddressIU, address);
            Customer customer = optional.get();
            address.setCustomer(customer);
            customer.setAddress(address);
            Address dbAddress = addressRepository.save(address);
            BeanUtils.copyProperties(dbAddress, dtoAddress);
            DtoCustomer dtoCustomer = new DtoCustomer();
            dtoCustomer.setId(dbAddress.getCustomer().getId());
            dtoCustomer.setName(dbAddress.getCustomer().getName());


            dtoAddress.setCustomer(dtoCustomer);
            customerRepository.save(customer);
        }


        return dtoAddress;
    }

    @Override
    public List<DtoAddress> getAllAddress() {
        List<DtoAddress> dtoAddressList = new ArrayList<>();

        List<Address> addressList = addressRepository.findAll();
        for (Address address : addressList) {
            DtoAddress dtoAddress = new DtoAddress();
            BeanUtils.copyProperties(address, dtoAddress);
            DtoCustomer dtoCustomer = new DtoCustomer();
            dtoCustomer.setId(address.getCustomer().getId());
            dtoCustomer.setName(address.getCustomer().getName());
//            dtoCustomer.setDtoAddress(dtoAddress);
            dtoAddress.setCustomer(dtoCustomer);
            dtoAddressList.add(dtoAddress);
        }

        return dtoAddressList;
    }

    @Override
    public DtoAddress findAddressById(Long id) {
        DtoAddress dtoAddress = new DtoAddress();
        DtoCustomer dtoCustomer = new DtoCustomer();
        Optional<Address> optional = addressRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        Address address = optional.get();
        BeanUtils.copyProperties(address, dtoAddress);

        dtoCustomer.setId(address.getCustomer().getId());
        dtoCustomer.setName(address.getCustomer().getName());

        dtoAddress.setCustomer(dtoCustomer);

        return dtoAddress;
    }

    @Override
    public boolean deleteAddress(Long id) {
        Optional<Address> optional = addressRepository.findById(id);
        if (optional.isPresent()) {
            Address address = optional.get();


            // Addresi kullananan müşteri varsa ilişki koparılır
            Optional<Customer> customerOptional = customerRepository.findByAddress(address);
            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();
                customer.setAddress(null); // ilişki kopar
                customerRepository.save(customer); // müşteri güncellenir
            }
            addressRepository.delete(address);
            return true;
        }
        return false;
    }

//    @Override
//    public DtoAddress updateAddress(Long id, DtoAddressIU dtoAddressIU) {
//        DtoAddress dtoAddress = new DtoAddress();
//        Optional<Customer> customerOptional = customerRepository.findById(dtoAddressIU.getCustomer().getId());
//        Customer customer = customerOptional.get();
//        customer.setName(dtoAddressIU.getCustomer().getName());
//        customer.setId(dtoAddressIU.getCustomer().getId());
//
//        Optional<Address> optionalAddress = addressRepository.findById(id);
//        if (optionalAddress.isPresent()) {
//            Address address = optionalAddress.get();
//            address.setDescription(dtoAddressIU.getDescription());
//            address.setCustomer(customer);
//            customer.setAddress(address);
//
//            Address dbAddress = addressRepository.save(address);
//
//
//            BeanUtils.copyProperties(dbAddress, dtoAddress);
//            DtoCustomer dtoCustomer = new DtoCustomer();
//            dtoCustomer.setId(dbAddress.getCustomer().getId());
//            dtoCustomer.setName(dbAddress.getCustomer().getName());
//            dtoCustomer.setDtoAddress(dtoAddress);
//            dtoAddress.setCustomer(dtoCustomer);
//        }
//
//        return dtoAddress;
//    }
@Override
public DtoAddress updateAddress(Long id, DtoAddressIU dtoAddressIU) {
    DtoAddress dtoAddress = new DtoAddress();

    Optional<Address> optionalAddress = addressRepository.findById(id);
    if (optionalAddress.isPresent()) {
        Address address = optionalAddress.get();

        // Yeni müşteri set ediliyorsa ve farklıysa kontrol et
        Optional<Customer> customerOptional = customerRepository.findById(dtoAddressIU.getCustomer().getId());
        if (customerOptional.isEmpty()) {
            return null; // ya da throw new RuntimeException("Customer not found");
        }

        Customer customer = customerOptional.get();

        // Adres güncellemesi
        address.setDescription(dtoAddressIU.getDescription());

        // Müşteriyle ilişki kuruluyor
        address.setCustomer(customer);

        // Eğer çift yönlü ilişki varsa müşteri tarafını da güncelle
        if (customer.getAddress() == null || !customer.getAddress().getId().equals(address.getId())) {
            customer.setAddress(address);
        }

        Address dbAddress = addressRepository.save(address);

        // DTO dönüşümü
        BeanUtils.copyProperties(dbAddress, dtoAddress);
        DtoCustomer dtoCustomer = new DtoCustomer();
        dtoCustomer.setId(dbAddress.getCustomer().getId());
        dtoCustomer.setName(dbAddress.getCustomer().getName());
//        dtoCustomer.setDtoAddress(dtoAddress);
        dtoAddress.setCustomer(dtoCustomer);

        return dtoAddress;
    }

    return dtoAddress;
}

}
