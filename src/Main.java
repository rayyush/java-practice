import java.util.HashSet;
import java.util.Objects;
import java.util.*;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        //interface
        Vehicle vehicle = new Car(); // this allows loose coupling and reusability, the method of interface can only be used
        Car car = new Car();// strictly ties methods of the car class, can also access methods of the car class, not in interface
        car.start();
        vehicle.start();
        car.noise();
        Vehicle v2 = new Car();
        ((Car)v2).noise();

        //static
        Parent obj = new Child();
        obj.show();
        Child obj2 = new Child();
        obj2.show();
        System.out.println(obj.a);
        Child obj1 = new Child();
        System.out.println(obj1.a);
        Parent obj3 = new Child();
        obj3.a = 500;
        System.out.println("Child.a: " + Child.a);
        System.out.println("Parent.a: " + Parent.a);

        //interfaces vs abstract class
        Bird bird = new Bird();
        bird.fly();
        bird.breathe();
        bird.makeSound();

        //this vs super
        P p = new C();
        System.out.println(p.a1);

        //deep copy vs shallow copy
        Address addr = new Address("Delhi");
        Student s1 = new Student("Ayush", addr);

        Student s2 = (Student) s1.clone();
        s2.name = "Ravi";
        s2.address.city = "Mumbai";
        s1.printData();
        s2.printData();

        //equals() and hashCode()
        Stu stu1 = new Stu("Ayush", 1);
        Stu stu2 = new Stu("Ayush", 1);

        System.out.println(stu1 == stu2);
        HashSet<Stu> set = new HashSet<>();
        set.add(stu1);
        set.add(stu2);

        System.out.println(set.size());
    }
}

//INTERFACE IMPLEMENTATION
interface Vehicle{
    void start();
}

class Car implements Vehicle{
    public void start(){
        System.out.println("Implementing start in child class");
    }

    public void noise(){
        System.out.println("Creating a noise");
    }
}

//STATIC METHODS
//cannot be overridden
//static methods are resolved at compile time
//they belong to the class not the object
//same for static variables
class Parent{
    static int a = 90;
    public static void show(){
        System.out.println("Parent class show");
    }
}

class Child extends Parent{
    static int a = 100;
    public static void show(){
        System.out.println("Child class show");
    }
}
//even though object is of child class, the parent class's show is invoked



//INTERFACES vs ABSTRACT CLASS
//interfaces allow us to have multiple inheritance
interface Flyable{
    void fly();
}

abstract class Animal{
    abstract void makeSound();
    void breathe(){
        System.out.println("Breathing...");
    }
}

class Bird extends Animal implements Flyable{
    void makeSound(){
        System.out.println("Bird's sound");
    }
    public void fly(){
        System.out.println("Bird flying");
    }
}

//ENCAPSULATION
//In java, its implemented using private variables and public getter, setter methods


//this vs super
// this refers to the current object of the class
// super refers to the parent class object
// if reference type is of P and object is of C, we need to have show in both P and C, and variables are resolved according to the reference type not the object type, so according to current call in the main method, p.a1, gives 10, even though object is of C
// super is also used to call the parent's class constructor explicitly, just write super();


class P{
    int a1 = 10;
}

class C extends P{
    int a1 = 20;

    void show(){
        System.out.println(a1);
    }
}

//deep copy vs shallow copy
class Address{
    String city;
    public Address(String city){
        this.city = city;
    }

    //for shallow copy, this function is not required
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}

class Student implements Cloneable{
    String name;
    Address address;

    public Student(String name, Address address){
        this.name = name;
        this.address = address;
    }

    public Object clone() throws CloneNotSupportedException{

        //deep copy
        Student cloned = (Student) super.clone();
        cloned.address = new Address(this.address.city);
        return cloned;

        //shallow copy
        //return super.clone();

    }

    void printData(){
        System.out.println(name + "from" + address.city);
    }
}

//final keyword
//values -> cant be changed (constant)
//methods -> cant be overridden
//classes -> cant be inherited

//finally
//always executes, used with a try catch block
//often used to close resources, db, files etc

//finalize
//method called by garbage collector
//used to clean up resources
//deprecated in java 9, but good to know
//called before object is garbage collected

//hashcode()
//returns an integer representation of the object
//used in hashing ds

//IMPORTANT
//if equals() is overridden, we must also, override hashcode(), as two objects which are equal, must have the same hashcode
//but if hashcode of two objects is same, they may or may not be equal


//Implementation of override equals and hashcode
class Stu{
    String name;
    int rollno;
    public Stu(String name, int rollno){
        this.name = name;
        this.rollno = rollno;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        Stu other = (Stu) obj;
        return this.rollno == other.rollno && this.name.equals(other.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, rollno);
    }
}