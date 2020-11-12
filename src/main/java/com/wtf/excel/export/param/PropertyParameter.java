package com.wtf.excel.export.param;

import com.wtf.excel.util.AnnotationUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
/**
 * @author strugglesnail
 * @date 2020/11/12 9:10
 * @desc 属性参数
 */
public class PropertyParameter<T> {

    private WorkbookParameter workbookParameter;

    // 处理的属性
    private Field field;


    private Field[] fields;

    // 单元格所属的行
    private Row row;

    // 目标对象
    private T target;

    // 字段的所属类类型
    private Class<?> containingClass;

    // 字段转换器
//    private Converter converter;

    // 字段注解
    private Annotation[] fieldAnnotations;


    public PropertyParameter(WorkbookParameter workbookParameter) {
        this(workbookParameter, null, null, null);
    }


    public PropertyParameter(WorkbookParameter workbookParameter, Row row, Field field, T target) {
        this.workbookParameter = workbookParameter;
        this.field = field;
        this.fields = workbookParameter.getBeanParameter().getFields();
        this.row = row;
        this.target = target;
        this.containingClass = target == null ? null : target.getClass();
        this.fieldAnnotations = field == null ? fields[0].getDeclaredAnnotations() : field.getDeclaredAnnotations();
//        this.converter = field == null ? field.getDeclaredAnnotation()
    }

    // 判断是否有指定的注解类型
    public <A extends Annotation> boolean hasPropertyAnnotation(Class<A> annotationType) {
        return this.getPropertyAnnotation(annotationType) != null;
    }

    // 获取指定的注解类型
    public <T extends Annotation> T getPropertyAnnotation(Class<T> annotationType) {
        Annotation[] propertyAnns = this.fieldAnnotations;
//        System.out.println(Arrays.toString(propertyAnns));
        for (Annotation propertyAnn : propertyAnns) {
            if (annotationType.isInstance(propertyAnn)) {
                return AnnotationUtils.getAnnotation(propertyAnn, annotationType);
            }
        }
        return null;
    }

    public Workbook getWorkbook() {
        return workbookParameter.getWorkbook();
    }

    public Field getField() {
        return field;
    }

    public Field[] getFields() {
        return fields;
    }

    public Class<?> getContainingClass() {
        return containingClass;
    }


    public Annotation[] getFieldAnnotations() {
        return fieldAnnotations;
    }

    public Row getRow() {
        return row;
    }

    public T getTarget() {
        return target;
    }

    public WorkbookParameter getWorkbookParameter() {
        return workbookParameter;
    }
}
