package com.example.company_1.service;

import com.example.company_1.entity.Address;
import com.example.company_1.payload.AddressDto;
import com.example.company_1.payload.ApiResponse;
import com.example.company_1.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public ApiResponse getAllAddress(){
        List<Address> addressList = addressRepository.findAll();
        return new ApiResponse("Barcha addresslar:",true,addressList);
    }

    public ApiResponse getAddressById(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            Address address = optionalAddress.get();
            return new ApiResponse("Address ma`lumotlari:",true,address);
        }else {
            return new ApiResponse("Bunday address mavjud emas",false);
        }
    }

    public ApiResponse deleteAddress(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            addressRepository.deleteById(id);
            return new ApiResponse("Address o`chirildi",true);
        }else {
            return new ApiResponse("Bunday address mavjud emas",false);
        }
    }

    public ApiResponse saveNewAddress(AddressDto addressDto){
        Address address=new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return new ApiResponse("Address ma`lumotlari saqlandi",true);
    }

    public ApiResponse editAddress(Integer id,AddressDto addressDto){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            Address address = optionalAddress.get();
            address.setStreet(addressDto.getStreet());
            address.setHomeNumber(addressDto.getHomeNumber());
            addressRepository.save(address);
            return new ApiResponse("Address ma`lumotlari o`zgartirildi.",true);
        }else {
            return new ApiResponse("Bunday address mavjud emas",false);
        }
    }
}
