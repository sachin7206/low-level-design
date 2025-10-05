package decorator;

import entities.Coffee;

public abstract class CoffeeDecorator extends Coffee {
    Coffee decoratedCoffee;
    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public void addCondiments() {
        decoratedCoffee.addCondiments();
    }
}
