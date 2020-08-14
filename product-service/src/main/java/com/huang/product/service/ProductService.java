package com.huang.product.service;

import com.huang.entity.Product;

import java.util.Optional;

/**
 * @author Huang.zh
 * @date 2020/8/14 9:49
 * @Description: 商品服务层抽象
 */
public interface ProductService {

    Optional<Product> findByID(Integer id);
}
