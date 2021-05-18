package com.stivigala.wolt.controller.courier;

import com.stivigala.wolt.dbo.delivery.DeliveryStatus;
import com.stivigala.wolt.dbo.delivery.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class CourierService {

    private final DeliveryRepository deliveryRepository;

    public void setDeliveryStatus(HttpServletRequest request) {
        deliveryRepository.findById(Integer.parseInt(request.getParameter("deliveryId"))).ifPresent(delivery -> {
            if (request.getParameter("status").equals(DeliveryStatus.IN_DELIVERY.toString())) {
                delivery.setStatus(DeliveryStatus.IN_DELIVERY);
            } else if (request.getParameter("status").equals(DeliveryStatus.DELIVERED.toString())) {
                delivery.setStatus(DeliveryStatus.DELIVERED);
            }
            deliveryRepository.save(delivery);
        });
    }
}
