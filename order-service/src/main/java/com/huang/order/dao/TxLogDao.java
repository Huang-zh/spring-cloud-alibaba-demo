package com.huang.order.dao;

import com.huang.entity.TxLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Huang.zh
 * @date 2020/8/20 17:01
 * @Description: 事务持久层抽象
 */
public interface TxLogDao extends JpaRepository<TxLog,String> {
}
