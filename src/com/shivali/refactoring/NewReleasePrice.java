package com.shivali.refactoring;

public class NewReleasePrice extends Price {
    @Override
    int getPrice() {
        return Movie.NEW_RELEASE;
    }

    double getCharge(int daysRented) {
        return daysRented * 3;
    }
}
