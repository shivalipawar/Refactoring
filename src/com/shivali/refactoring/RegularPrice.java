package com.shivali.refactoring;

public class RegularPrice extends Price {
    @Override
    int getPrice() {
        return Movie.REGULAR;
    }

    double getCharge(int daysRented) {
        double result = 2 ;
        if (daysRented > 2)
            result += (daysRented - 2) * 1.5;
        return result;
    }
}
