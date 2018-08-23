package com.sell.demo.controller;

import com.sell.demo.Util.ResultVOUtil;
import com.sell.demo.VO.ProductInfoVO;
import com.sell.demo.VO.ProductVO;
import com.sell.demo.VO.ResultVO;
import com.sell.demo.dataobject.ProductCategory;
import com.sell.demo.dataobject.ProductInfo;
import com.sell.demo.service.CategoryService;
import com.sell.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
@Slf4j

public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //查询类目（一次性查询）
        /**1.传统方法
         List<Integer> categoryTypeList = new ArrayList<>();
         for(ProductInfo productInfo:productInfoList){
         categoryTypeList.add(productInfo.getCategoryType());
         }
         */
        //2.lambda
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());


        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //数据拼装

        //3
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {

            //2
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    //1
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    //最内层封装
                    productInfoVOList.add(productInfoVO);

                }
            }
            productVO.setProductInfoVOList(productInfoVOList);



            //第二层封装
            productVOList.add(productVO);
        }


//        //最外层封装
//        ResultVO resultVO = new ResultVO();
//        resultVO.setData(productVOList);
//        resultVO.setCode(0);
//        resultVO.setMsg("成功");
        return ResultVOUtil.success(productVOList);
    }

}
