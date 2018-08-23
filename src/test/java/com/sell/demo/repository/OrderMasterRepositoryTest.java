package com.sell.demo.repository;

import com.sell.demo.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID="110110";

    @Test
    public void saveTest(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("1234");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("12345678901");
        orderMaster.setBuyerAddress("家属区");
        orderMaster.setBuyerOpenId(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.3));
        OrderMaster result=repository.save(orderMaster);
        Assert.assertNotNull(result);
    }
    @Test
    public  void findByBuyerOpenId() throws Exception{

        PageRequest request=new PageRequest(0,3);
        Page<OrderMaster> result=repository.findByBuyerOpenId(OPENID,request);
        System.out.println(result.getTotalElements());
        Assert.assertNotEquals(0,result);

    }
}