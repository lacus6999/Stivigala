package com.stivigala.wolt.controller.courier;

import com.stivigala.wolt.dbo.delivery.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class CourierDeliveryController {

    private final DeliveryRepository deliveryRepository;
    private final CourierService courierService;

    @GetMapping("/courier/{id}")
    public String showDeliveryDetailsPage(Model model, @PathVariable String id) {
        model.addAttribute("delivery", deliveryRepository.findById(Integer.parseInt(id)));

        return "/courier/deliveryDetailsPage";
    }

    @PostMapping("/courier/delivery/setDeliveryStatus")
    public String setDeliveryStatus(HttpServletRequest request) {
        courierService.setDeliveryStatus(request);

        return "redirect:/courier/getMainCourierSite/";
    }
}
