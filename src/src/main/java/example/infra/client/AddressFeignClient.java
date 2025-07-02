package example.infra.client;


import example.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "addressFeignClient", url="http://localhost:8090")
public interface AddressFeignClient {

    @GetMapping("/address")
    List<Address> searchAddresses(@RequestParam("clientId") Long clientId);
}
