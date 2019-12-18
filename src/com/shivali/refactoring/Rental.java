package com.shivali.refactoring;

class Rental {
    Movie _movie;
    private int _daysRented;
    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }
    public int getDaysRented() {
        return _daysRented;
    }
    public Movie getMovie() {
        return _movie;
    }

    int getFrequentRenterPoints() {
        // add frequent renter points and return at least 1 and for bonus 2.
        // add bonus for a two day new release rental
        return _movie.getFrequentRenterPonts(this._daysRented);
    }

    double getCharge() {
        return _movie._priceCode.getCharge(_daysRented);
    }
}
