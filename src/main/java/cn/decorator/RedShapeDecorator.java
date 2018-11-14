package cn.decorator;

import cn.decorator.shape.Shape;

/**
 * RedShapeDecorator class
 *
 * @author Administrator
 * @date
 *
 * 对shape类进行一次包装，扩展原来的draw方法，使功能更加强大
 *
 */
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape shape){
        super.ShapeDecorator(shape);
    }

    @Override
    public void draw() {
        //shape控制形状
        shape.draw();
        //在draw上又装饰了一层颜色
        setRedBorder(shape);
    }

    private void setRedBorder(Shape shape){
        System.out.println("Border Color: Red");
    }
}
