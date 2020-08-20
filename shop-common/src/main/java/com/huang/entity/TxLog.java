package com.huang.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Huang.zh
 * @date 2020/8/20 16:59
 * @Description: 事务日志实体类
 */
@Entity(name = "shop_txlog")
@Data
public class TxLog {
    @Id
    private String txLogId;
    private String content;
    private Date date;
}

