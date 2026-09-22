package ru.otus.java.basic.homework19;

public class App {
    public static void main(String[] args) {

        Box<Apple> boxApples = new Box<>();
        Box<Orange> boxOranges = new Box<>();
        Box<Fruit> boxFruits = new Box<>();

        try {
            boxApples.addFruit(new Apple(120.41));
            boxApples.addFruit(new Apple(220.41));

            boxOranges.addFruit(new Orange(250.41));
            boxOranges.addFruit(new Orange(140.25));

            boxFruits.addFruit(new Apple(95.25));
            boxFruits.addFruit(new Orange(40.10));
        }
        catch (NullPointerException e) {
            System.out.println("Null ex:" + e.getMessage());
        }
        catch (IllegalArgumentException e) {
            System.out.println("IllegalArg ex:" + e.getMessage());
        }

        System.out.printf("Коробка с яблоками %n%s%n", boxApples);
        System.out.printf("Коробка с апельсинами %n%s%n", boxOranges);
        System.out.printf("Коробка с фруктами %n%s%n", boxFruits);

        System.out.println(boxApples.compare(boxOranges) ? "boxes are equal" : "boxes are not equal");

        boxApples.transferToAnotherBox(boxFruits);
        System.out.printf("Коробка с фруктами %n%s%n", boxFruits);

    }
}
