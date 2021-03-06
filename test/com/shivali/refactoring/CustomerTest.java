package com.shivali.refactoring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

    Rental rental , rental1, rental2, rental3;
    Customer customer;

    @Before
    public void initialize(){
        rental = new Rental(new Movie("X-Men",Movie.REGULAR),2);
        rental1 = new Rental(new Movie("Cindrella",Movie.CHILDRENS),5);
        rental2 = new Rental(new Movie("Hulk",Movie.NEW_RELEASE),1);
        rental3 = new Rental(new Movie("BeautyAndBeast",Movie.NEW_RELEASE),1);
        customer = new Customer("Seema");
    }

    @Test
    public void statementShouldPrintCorrectRentalAmount(){
        customer.addRental(rental);
        customer.addRental(rental1);
        customer.addRental(rental2);
        customer.addRental(rental3);
        String result = "Rental Record for Seema"+"\n\t"+"X-Men\t2.0"+"\n\t"+"Cindrella	4.5"+"\n\t"+"Hulk	3.0"+"\n\t"+"BeautyAndBeast\t3.0"+"\n"+"Amount owed is 12.5"+"\n"+"You earned 4 frequent renter points";
        Assert.assertEquals(result,customer.statement());
    }

    @Test
    public void htmlStatementShouldPrintCorrectRentalAmountAndStatementFormat(){
        customer.addRental(rental);
        customer.addRental(rental1);
        customer.addRental(rental2);
        customer.addRental(rental3);
        String result = "<H1> Rental Record for <EM> Seema</EM></H1><P>\n" +
                "\tX-Men\t2.0<BR>\n" +
                "\tCindrella\t4.5<BR>\n" +
                "\tHulk\t3.0<BR>\n" +
                "\tBeautyAndBeast\t3.0<BR>\n" +
                "<P>Amount owed is <EM>12.5</EM></H1><P>\n" +
                "You earned <EM>4 </EM>frequent renter points<P>";
        Assert.assertEquals(result,customer.htmlStatement());
    }
}