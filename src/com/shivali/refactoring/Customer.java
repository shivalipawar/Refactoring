package com.shivali.refactoring;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();

            //show figures for this rental
            //determine amounts for each line at method getCharge()
            result += "\t" + each.getMovie().getTitle()+ "\t" +
                    String.valueOf(each._movie._priceCode.getCharge(each.getDaysRented())) + "\n";
        }
        //add footer lines
        result += "Amount owed is " + String.valueOf(getTotalCharge()) +
                "\n";
        result += "You earned " + String.valueOf(getFrequentRenterPoints())
                +
                " frequent renter points";
        return result;
    }

    public String htmlStatement() {
        Enumeration rentals = _rentals.elements();
        String result = "<H1> Rental Record for <EM> " + getName() + "</EM></H1><P>\n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();

            //show figures for this rental
            //determine amounts for each line at method getCharge()
            result += "\t" + each.getMovie().getTitle()+ "\t" +
                    String.valueOf(each._movie._priceCode.getCharge(each.getDaysRented())) + "<BR>\n";
        }
        //add footer lines
        result += "<P>Amount owed is <EM>" + String.valueOf(getTotalCharge()) +
                "</EM></H1><P>\n";
        result += "You earned <EM>" + String.valueOf(getFrequentRenterPoints())
                +
                " </EM>frequent renter points<P>";
        return result;
    }

    private int getFrequentRenterPoints() {
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            frequentRenterPoints += each.getFrequentRenterPoints();
        }
        return frequentRenterPoints;
    }

    private double getTotalCharge() {
        double totalAmount = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            totalAmount += each._movie._priceCode.getCharge(each.getDaysRented());
        }
        return totalAmount;
    }


}
