package co.com.ingeneo.prueba.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import co.com.ingeneo.prueba.entities.Delivery;
import co.com.ingeneo.prueba.repositories.DeliveryRepository;

@RestController
public class DeliveryController {
  
  private final DeliveryRepository deliveryRepository;

  DeliveryController(DeliveryRepository deliveryRepository) {
    this.deliveryRepository = deliveryRepository;
  }

  @PostMapping("/new-delivery")
  Delivery newDelivery(@RequestBody Delivery delivery) {
    return deliveryRepository.save(delivery);
  }

}
