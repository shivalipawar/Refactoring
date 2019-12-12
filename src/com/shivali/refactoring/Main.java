package com.shivali.refactoring;

public class Main {

    public static void main(String[] args) {
        Rental rental = new Rental(new Movie("X-Men",Movie.REGULAR),2);
        Rental rental1 = new Rental(new Movie("Cindrella",Movie.CHILDRENS),5);
        Rental rental2 = new Rental(new Movie("Hulk",Movie.NEW_RELEASE),1);
        Rental rental3 = new Rental(new Movie("BeautyAndBeast",Movie.NEW_RELEASE),1);
        Customer customer = new Customer("Seema");
        customer.addRental(rental);
        customer.addRental(rental1);
        customer.addRental(rental2);
        customer.addRental(rental3);
        System.out.println("Statement is : "+customer.statement());
    }

}
