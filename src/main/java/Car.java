public class Car {
    int year;
    String model;

    public Car(String car, int howOld) {
        model = car;
        year = howOld;
    }

    public static void main(String[] args) {
        Car car1 = new Car("Mustang", 1952);
        Car car2 = new Car ("trtrMitya", 1980);
        System.out.println(car1.model + " " + car1.year);
        System.out.println(car2.model + " " + car2.year);



    }

}
