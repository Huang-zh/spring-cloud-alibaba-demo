package com.huang.product.service.impl;

import com.huang.entity.Product;
import com.huang.product.dao.ProductDao;
import com.huang.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Huang.zh
 * @date 2020/8/14 9:50
 * @Description: 商品服务具体实现
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Optional<Product> findByID(Integer id) {
        return productDao.findById(id);
    }
}
