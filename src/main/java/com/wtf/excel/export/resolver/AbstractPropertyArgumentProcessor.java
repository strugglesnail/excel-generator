package com.wtf.excel.export.resolver;


import com.wtf.tool.util.excel.export.param.PropertyParameter;

public abstract class AbstractPropertyArgumentProcessor {

    protected abstract void setCell(ArgumentParameter parameter);
    protected abstract void setHeader(PropertyParameter parameter);
    protected abstract void setTitle(PropertyParameter parameter);


    public static class ArgumentParameter {

    }

}
