package mx.wedevelop.guernica.controllers;

import mx.wedevelop.guernica.controllers.ProductController;
import mx.wedevelop.guernica.models.Product;
import mx.wedevelop.guernica.models.User;
import mx.wedevelop.guernica.services.ProductService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

//import static org.hamcrest.Matchers.instanceOf;
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.hasProperty;
//import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by colorado on 27/02/17.
 */
public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        //Initialize mocks
        MockitoAnnotations.initMocks(this);
        //Setup
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testList() throws Exception {
        User user = new User();
        List<Product> productList = new ArrayList<>();

        productList.add(mockProduct(1));
        productList.add(mockProduct(2));
        productList.add(mockProduct(3));

        Mockito.when(productService.findAll()).thenReturn(productList);

        mockMvc.perform(MockMvcRequestBuilders.get("/product"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/list"))
                .andExpect(model().attribute("productList", hasSize(3)));
    }

    @Test
    public void testShow() throws Exception {
        Product product = mockProduct(1);

        Mockito.when(productService.findById(product.getId())).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/" + product.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("product/show"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/new"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testEdit() throws Exception {
        Product product = mockProduct(1);

        Mockito.when(productService.findById(product.getId())).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/" + product.getId() + "/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/new"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testCreate() throws Exception {
        Product product = mockProduct(1);

        when(productService.saveOrUpdate(any()))
                .thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/product")
            .param("id", product.getId() + "")
            .param("name", product.getName())
            .param("description", product.getDescription())
            .param("unitCost", product.getUnitCost() + ""))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/product/" + product.getId()))
            .andExpect(model().attribute("product", instanceOf(Product.class)))
            .andExpect(model().attribute("product", hasProperty("id", is(product.getId()))))
            .andExpect(model().attribute("product", hasProperty("name", is(product.getName()))))
            .andExpect(model().attribute("product", hasProperty("description", is(product.getDescription()))))
            .andExpect(model().attribute("product", hasProperty("unitCost", is(product.getUnitCost()))));

        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productService).saveOrUpdate(captor.capture());

        assertEquals(product.getId(), captor.getValue().getId());
        assertEquals(product.getName(), captor.getValue().getName());
        assertEquals(product.getDescription(), captor.getValue().getDescription());
        assertEquals(product.getUnitCost() + "", captor.getValue().getUnitCost() + "");
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 1;

        mockMvc.perform(MockMvcRequestBuilders.get("/product/" + id + "/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product"));

        verify(productService, times(1)).delete(id);
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
