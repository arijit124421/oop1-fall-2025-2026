package LAB;

public class TASK2 {
    public static void main(String[] args) {
        double Orginalprice = 250;
        double discountpercent = 15;

        double discountamount = (discountpercent / 100) * Orginalprice;

        double finalprice = Orginalprice - discountamount;
        System.out.println("Orginal price:" + Orginalprice);

        System.out.println("Discount:" + discountamount);
        System.out.println("Final Price;" + finalprice);

    }

}
