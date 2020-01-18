package com.epsilon.lx.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data //getter、setter等
@NoArgsConstructor  //无参构造函数
@AllArgsConstructor  //全参构造函数
@Accessors(chain = true)//如果为true生成的set方法返回this，为false生成的set方法是void类型
//微服务需要实现序列化接口
public class Dept implements Serializable {

    private Long id;
    private String name;
    //private String db_source; //来自哪个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同的数据库中
}
