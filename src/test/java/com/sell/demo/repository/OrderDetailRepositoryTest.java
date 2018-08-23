package com.sell.demo.repository;

import com.sell.demo.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("1234567");
        orderDetail.setOrderId("1234");
        orderDetail.setProductIcon("http://");
        orderDetail.setProductName("粥");
        orderDetail.setProductId("123");
        orderDetail.setProductPrice(new BigDecimal(2.51));
        orderDetail.setProductQuantity(3);
        OrderDetail result=repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrOrderId() {

        List<OrderDetail> orderDetails=repository.findByOrderId("1234");
        System.out.println(orderDetails);
        Assert.assertNotNull(orderDetails);

    }
}