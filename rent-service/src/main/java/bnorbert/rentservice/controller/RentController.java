package bnorbert.rentservice.controller;

import bnorbert.rentservice.service.RentService;
import bnorbert.rentservice.transfer.Response;
import bnorbert.rentservice.transfer.SimpleResponse;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@CrossOrigin
@RestController
@RequestMapping("/services")
public class RentController {

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping(value = "/rents/{id}")
    public Response getRent(@PathVariable long id, Boolean checkbox) throws ExecutionException, InterruptedException {

        if(!checkbox) {
            return new SimpleResponse(rentService.getRentId(id));
        }else{
            return rentService.findDetailResponse(id);
        }
    }


}


