package spring.lookup;


/**
 * FruitPlate class
 *
 * @author Administrator
 * @date
 */
public class FruitPlate {

    private Fruit fruit;

    public void setFruit(Fruit _fruid) {
        this.fruit = _fruid;
    }

    public Fruit getFruit() {
        return this.fruit;
    }


    public Apple getApple() {
        return null;
    }
}
