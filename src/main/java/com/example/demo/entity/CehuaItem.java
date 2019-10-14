package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author zly
 * @since 2019-08-02
 */
@Data
@SuppressWarnings("serial")
public class CehuaItem implements Serializable {
	@Excel(name = "id")
	private int id;
	@Excel(name = "type")
	private Integer type;
	@Excel(name = "des")
	private String des;
	@Excel(name = "add_time", format ="yyyy-MM-dd HH:mm:ss",width = 20)
	private Date addTime;
	@Excel(name = "modify_time",format ="yyyy-MM-dd HH:mm:ss",width = 20)
	private Date modifyTime= null;
	@Override
	public String toString() {
		return "CehuaItem [id=" + id + ", type=" + type + ", des=" + des + ", addTime=" + addTime + ", modifyTime="
				+ modifyTime + "]";
	};

	
}
