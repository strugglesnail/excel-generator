package com.wtf.excel.export.resolver;


import com.wtf.excel.export.param.PropertyParameter;

public interface PropertyArgumentResolver {

    // 是否支持参数
    boolean supportsProperty(PropertyParameter parameter);

    // 解析字段参数
    Object resolverProperty(PropertyParameter parameter);

    // 设置标题、表头
    void resolverHeader(PropertyParameter parameter);

}
