package com.example.api;

import com.example.domain.Customer;
import com.example.json.CustomerJson;
import com.example.repository.CustomerRepository;
import com.example.service.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerServiceImpl customerService;

    @MockBean
    private CustomerRepository customerRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void IDを指定し顧客情報取得() throws Exception{
        Customer customer = new Customer(1, "Daisuke","Kasahara" );
        given(this.customerService.findOne(1))
                .willReturn(customer);

        this.mvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Daisuke"))
                .andExpect(jsonPath("$.lastName").value("Kasahara"));

    }

    @Test
    public void 全顧客の情報取得() throws Exception{
        Customer customer1 = new Customer(1, "Daisuke", "Kasahara");
        Customer customer2 = new Customer(2, "Sayuri", "Hukui");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);

        given(this.customerService.findAll())
                .willReturn(customerList);

        this.mvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("Daisuke"))
                .andExpect(jsonPath("$[0].lastName").value("Kasahara"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].firstName").value("Sayuri"))
                .andExpect(jsonPath("$[1].lastName").value("Hukui"));
    }

    @Test
    public void 新規顧客情報登録() throws Exception{
        CustomerJson requestCustomerJson = new CustomerJson(null, "Daisuke","Kasahara" );
        String requestJson = mapper.writeValueAsString(requestCustomerJson);

        CustomerJson responseCustomerJson = new CustomerJson(1, "Daisuke", "Kasahara");
        String responseJson = mapper.writeValueAsString(responseCustomerJson);

        Customer customer = new Customer(1, "Daisuke", "Kasahara");
        given(this.customerService.create(any(Customer.class)))
                .willReturn(customer);

        this.mvc.perform(post("/api/customers").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                //レスポンスのJsonと、テスト用に作成したJsonとをそのまま比較するやり方。
                .andExpect(content().json(responseJson));
                //以下は、レスポンスのJsonから、１つずつデータを取り出して比較するやり方。
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.firstName").value("Daisuke"))
//                .andExpect(jsonPath("$.lastName").value("Kasahara"));

    }

    @Test
    public void IDを指定して顧客情報更新() throws Exception{
        CustomerJson requestCustomerJson = new CustomerJson(null, "Daisuke", "Kasahara");
        String requestjson = mapper.writeValueAsString(requestCustomerJson);

        CustomerJson responseCustomerJson = new CustomerJson(1,"Daisuke", "Kasahara");
        String responseJson = mapper.writeValueAsString(responseCustomerJson);

        Customer customer = new Customer(1,"Daisuke", "Kasahara");
        given(this.customerService.update(any(Customer.class)))
                .willReturn(customer);

        this.mvc.perform(put("/api/customers/1").contentType(MediaType.APPLICATION_JSON).content(requestjson))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    @Test
    public void IDを指定してユーザ削除() throws Exception{

        this.mvc.perform(delete("/api/customers/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(customerService, only()).delete(1);



    }




}
