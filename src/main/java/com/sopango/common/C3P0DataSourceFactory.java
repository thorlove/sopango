package com.sopango.common;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 *
 * @author thb
 * @date 2017-7-13 上午10:21:55
 * @since  V1.0
 */
public class C3P0DataSourceFactory extends UnpooledDataSourceFactory {
	public C3P0DataSourceFactory() {
		this.dataSource = new ComboPooledDataSource();
	}
}
