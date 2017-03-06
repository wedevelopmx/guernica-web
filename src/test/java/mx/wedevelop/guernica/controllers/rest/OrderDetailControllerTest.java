package mx.wedevelop.guernica.controllers.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import mx.wedevelop.guernica.models.Order;
import mx.wedevelop.guernica.models.OrderDetail;
import mx.wedevelop.guernica.models.Product;
import mx.wedevelop.guernica.services.OrderService;
import mx.wedevelop.guernica.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by colorado on 6/03/17.
 */
public class OrderDetailControllerTest {
    public static final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8")
            );

    @InjectMocks
    private OrderDetailController orderDetailController;

    @Mock
    private OrderService orderService;

    @Mock
    private ProductService productService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        //Initialize mocks
        MockitoAnnotations.initMocks(this);
        //Setup
        mockMvc = MockMvcBuilders.standaloneSetup(orderDetailController).build();
    }

    @Test
    public void testCatalog() throws Exception {
        List<Product> productList = mockProductList();

        Mockito.when(productService.findAll()).thenReturn(productList);

        mockMvc.perform(MockMvcRequestBuilders.get("/catalog"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].name", is("Product #1")))
            .andExpect(jsonPath("$[0].description", is("Description #1")))
            .andExpect(jsonPath("$[0].unitCost", is(10.0)));

        verify(productService, times(1)).findAll();
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testFindOrder() throws Exception {
        Order order = mockOrder(1);

        Mockito.when(orderService.findById(order.getId())).thenReturn(order);

        mockMvc.perform(MockMvcRequestBuilders.get("/find-order/{id}", order.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.id", is(order.getId())))
            .andExpect(jsonPath("$.orderDetails", hasSize(3)))
            .andExpect(jsonPath("$.orderDetails[0].id", is(order.getOrderDetails().get(0).getId())))
            .andExpect(jsonPath("$.orderDetails[0].quantity", is(order.getOrderDetails().get(0).getQuantity())))
            .andExpect(jsonPath("$.orderDetails[0].product.name", is(order.getOrderDetails().get(0).getProduct().getName())))
            .andExpect(jsonPath("$.orderDetails[1].id", is(order.getOrderDetails().get(1).getId())))
            .andExpect(jsonPath("$.orderDetails[2].id", is(order.getOrderDetails().get(2).getId())));

        verify(orderService, times(1)).findById(order.getId());
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void testCreateOrder() throws Exception {
        Order order = mockOrder(1);

        Mockito.when(orderService.findById(order.getId())).thenReturn(order);

        mockMvc.perform(MockMvcRequestBuilders.get("/create-order"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(order.getId())))
                .andExpect(jsonPath("$.orderDetails", hasSize(3)))
                .andExpect(jsonPath("$.orderDetails[0].id", is(order.getOrderDetails().get(0).getId())))
                .andExpect(jsonPath("$.orderDetails[0].quantity", is(order.getOrderDetails().get(0).getQuantity())))
                .andExpect(jsonPath("$.orderDetails[0].product.name", is(order.getOrderDetails().get(0).getProduct().getName())))
                .andExpect(jsonPath("$.orderDetails[1].id", is(order.getOrderDetails().get(1).getId())))
                .andExpect(jsonPath("$.orderDetails[2].id", is(order.getOrderDetails().get(2).getId())));

        verify(orderService, times(1)).findById(order.getId());
        verifyNoMoreInteractions(orderService);
    }

    private Order mockOrder(Integer id) {
        List<Product> productList = mockProductList();
        Random random = new Random();
        Order order = new Order();
        order.setId(id);

        for(Product product : productList) {
            order.addOrderDetail(mockOrderDetail(product, random.nextInt(10) + 1));
        }

        return order;
    }

    private OrderDetail mockOrderDetail(Product product, Integer quantity) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetail.setQuantity(quantity);
        return orderDetail;
    }

    private List<Product> mockProductList() {
        List<Product> productList = new ArrayList<>();

        productList.add(mockProduct(1));
        productList.add(mockProduct(2));
        productList.add(mockProduct(3));

        return productList;
    }

    private Product mockProduct(Integer id) {
        Product product = new Product();
        product.setId(id);
        product.setName("Product #" + id);
        product.setDescription("Description #" + id);
        product.setUnitCost(10 * id);

        return product;
    }
}
