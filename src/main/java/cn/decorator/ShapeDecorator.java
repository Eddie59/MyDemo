package cn.decorator;

import cn.decorator.shape.Shape;

/**
 * ShapeDecorator class
 *
 * @author Administrator
 * @date
 *
 * 装饰者，对shap接口进行了一次包装，不额外添加任何方法属性，主要为了后面的装饰
 *
 */
public abstract class ShapeDecorator implements Shape {

    protected Shape shape;

    public void ShapeDecorator(Shape shape) {
        this.shape=shape;
    }

    @Override
    public void draw() {
        shape.draw();
    }
}
