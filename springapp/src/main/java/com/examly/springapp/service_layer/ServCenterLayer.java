package com.examly.springapp.service_layer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.model.ServiceCenter;
import com.examly.springapp.repository.ServiceCenterRepository;

@Service
public class ServCenterLayer {
    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    public void createServCenter(ServiceCenter serviceCenter) {
        serviceCenterRepository.save(serviceCenter);
    }

    public ServiceCenter updateServiceCenter(Long id, ServiceCenter serviceDetails) {
        if (serviceCenterRepository.findBySid(id).isEmpty()) {
            throw new ResourceNotFoundException("Service Center not exist with id: " + id);
        }
        ServiceCenter serviceCenter = serviceCenterRepository.findById(id).get();

        serviceCenter.setName(serviceDetails.getName());
        serviceCenter.setPhone(serviceDetails.getPhone());
        serviceCenter.setAddress(serviceDetails.getAddress());
        serviceCenter.setImg_url(serviceDetails.getImg_url());
        serviceCenter.setMailId(serviceDetails.getMailId());
        serviceCenter.setDescription(serviceDetails.getDescription());
        return serviceCenterRepository.save(serviceCenter);
    }

    public void deleteServiceCenter(Long id) {
        if (serviceCenterRepository.findBySid(id).isEmpty()) {
            throw new ResourceNotFoundException("Service Center not exist with id: " + id);
        }
        ServiceCenter serviceCenter = serviceCenterRepository.findById(id).get();

        serviceCenterRepository.delete(serviceCenter);

    }

    public ServiceCenter getById(Long id) {
        if (serviceCenterRepository.findBySid(id).isEmpty()) {
            throw new ResourceNotFoundException("Service Center not exist with id: " + id);
        }
        ServiceCenter serviceCenter = serviceCenterRepository.findBySid(id).get();

        return serviceCenter;
    }
}
