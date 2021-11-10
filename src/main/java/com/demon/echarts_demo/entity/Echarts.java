package com.demon.echarts_demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("echarts_demo")
public class Echarts {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer count;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMt+8")
    private Date date;
}
