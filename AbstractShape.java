package Lab;

abstract class Shape {

    abstract void calculateArea();

    void displayShape(){

        System.out.println("This is a shape");
         

    }

}

class Rectangle extends Shape{

    double length;
    double width;

Rectangle(double length,double width){

    this.length= length;
    this.width=  width ;
}
 
@Override
void calculateArea(){

    double area = length*width;
    System.out.println("Area of the rectangle is : " + area + " m^2 ");
    }

}

class Circle extends Shape{
   
    double radius;

    Circle(double radius){
        this.radius= radius;
    }

     @Override
      void calculateArea(){

        double area = 3.1416*radius*radius;
        System.out.println("Area of circle: " + area + " m^2 ");
 
    }

}

public class AbstractShape {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(4,20);
        Shape circle = new Circle(5);
        rectangle.displayShape();
        rectangle.calculateArea();

        circle.displayShape();
        circle.calculateArea();

        
        
    }
    
}
