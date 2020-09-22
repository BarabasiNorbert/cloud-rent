package bnorbert.rentservice.service;

import bnorbert.rentcloud.customer.transfer.CustomerResponse;
import bnorbert.rentcloud.fees.AdditionalFee;
import bnorbert.rentcloud.rent.Rent;
import bnorbert.rentcloud.unit.Unit;
import bnorbert.rentservice.exeption.ResourceNotFoundException;
import bnorbert.rentservice.hystrix.CommonHysctrixCommand;
import bnorbert.rentservice.repository.RentRepository;
import bnorbert.rentservice.transfer.DetailResponse;
import com.netflix.hystrix.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@Slf4j
public class RentService {

    private final RentRepository rentRepository;

    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @LoadBalanced
    @Bean
    RestTemplate getRestTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HystrixCommand.Setter setter;

    public Rent getRentId(long id){
        log.info("Retrieving rent {}", id);
        return rentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Rent" + id + "not found"));
    }

    @Transactional
    public DetailResponse findDetailResponse(long id) throws ExecutionException, InterruptedException{

        Rent rent = getRentId(id);
        CustomerResponse customerResponse = getCustomer(rent.getCustomerId());
        AdditionalFee fee = getFee(rent.getAdditionalFeeId());
        Unit unit = getUnit(rent.getUnitId());

        return new DetailResponse(rent, customerResponse, fee, unit);

    }


    private CustomerResponse getCustomer(long customerId) throws ExecutionException, InterruptedException {

        CommonHysctrixCommand<CustomerResponse> customerCommonHysctrixCommand = new CommonHysctrixCommand<CustomerResponse>(setter,()->
                restTemplate.getForObject("http://customer/services/customers/" + customerId, CustomerResponse.class), CustomerResponse::new);

        Future<CustomerResponse> customerFuture = customerCommonHysctrixCommand.queue();

        return customerFuture.get();
    }


    private AdditionalFee getFee(long feeId) throws ExecutionException, InterruptedException {

        CommonHysctrixCommand<AdditionalFee> feeCommonHysctrixCommand = new CommonHysctrixCommand<AdditionalFee>(setter, () ->
                restTemplate.getForObject("http://fee/services/fees/" + feeId, AdditionalFee.class), AdditionalFee::new);

        Future<AdditionalFee> feeFuture = feeCommonHysctrixCommand.queue();

        return feeFuture.get();

    }

    private Unit getUnit(long unitId) throws ExecutionException, InterruptedException {

        CommonHysctrixCommand<Unit> unitCommonHysctrixCommand = new CommonHysctrixCommand<Unit>(setter,()->
                restTemplate.getForObject("http://unit/services/units/" + unitId, Unit.class), Unit::new);

        Future<Unit> unitFuture = unitCommonHysctrixCommand.queue();

        return unitFuture.get();


    }

}
