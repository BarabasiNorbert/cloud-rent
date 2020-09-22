package bnorbert.rentservice.controller;

import bnorbert.rentcloud.customer.transfer.CustomerResponse;
import bnorbert.rentcloud.fees.AdditionalFee;
import bnorbert.rentcloud.rent.Rent;
import bnorbert.rentcloud.unit.Unit;
import bnorbert.rentservice.service.RentService;
import bnorbert.rentservice.transfer.DetailResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RentController.class)
class RentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentService mockRentService;

    @Test
    void testGetRentDetailResponse() throws Exception {

        when(mockRentService.getRentId(1L)).thenReturn(new Rent());

        final Rent rent = new Rent();
        rent.setRentId(1L);
        rent.setCustomerId(1L);
        rent.setUnitId(1L);
        rent.setAdditionalFeeId(1L);
        rent.setPrice(1500d);
        rent.setCreatedDate(null);
        rent.setTerminationDate(null);

        final Unit unit = new Unit();
        unit.setId(1L);
        unit.setUnitNumber(106L);

        final CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(1L);
        customerResponse.setFirstName("John");
        customerResponse.setLastName("Doe");

        final DetailResponse detailResponse = new DetailResponse(rent, customerResponse, new AdditionalFee(), unit);
        when(mockRentService.findDetailResponse(1L)).thenReturn(detailResponse);

        final MockHttpServletResponse response = mockMvc.perform(get("/services/rents/{id}", 1)
                .param("checkbox", "true")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"rent\":{\"rentId\":1,\"customerId\":1,\"additionalFeeId\":1,\"unitId\":1,\"price\":1500.0,\"createdDate\":null,\"terminationDate\":null}," +
                "\"customerResponse\":{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"roles\":[]}," +
                "\"additionalFee\":{\"id\":null,\"dogFee\":0.0,\"catFee\":0.0,\"dogRent\":0.0,\"catRent\":0.0,\"adminFee\":0.0,\"gymMembershipPrice\":0.0,\"assignedGarageParking\":0.0}," +
                "\"unit\":{\"id\":1,\"unitNumber\":106}}");
    }


    @Test
    void testGetRentId() throws Exception {

        final Rent rent = new Rent();
        rent.setRentId(1L);
        rent.setCustomerId(1L);
        rent.setUnitId(1L);
        rent.setAdditionalFeeId(1L);
        rent.setPrice(1500d);
        rent.setCreatedDate(null);
        rent.setTerminationDate(null);

        when(mockRentService.getRentId(1L)).thenReturn(rent);

        final MockHttpServletResponse response = mockMvc.perform(get("/services/rents/{id}", 1)
                .param("checkbox", "false")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"rent\":{\"rentId\":1,\"customerId\":1,\"additionalFeeId\":1,\"unitId\":1,\"price\":1500.0,\"createdDate\":null,\"terminationDate\":null}}");
    }

   
}
