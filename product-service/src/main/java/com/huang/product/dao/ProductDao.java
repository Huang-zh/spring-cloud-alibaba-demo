package com.huang.product.dao;

import com.huang.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Huang.zh
 * @date 2020/8/14 9:45
 * @Description: 商品持久层抽象
 */
public interface ProductDao extends JpaRepository<Product,Integer> {

}
