package com.shivali.refactoring;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title;
    Price _priceCode;
    public Movie(String title, int priceCode) {
        _title = title;
        setPriceCode(priceCode);
    }
    public int getPriceCode() {
        return _priceCode.getPrice();
    }
    public void setPriceCode(int arg) {
        switch (arg) {
            case REGULAR:
                _priceCode = new RegularPrice();
                break;
            case CHILDRENS:
                _priceCode = new ChildrenPrice();
                break;
            case NEW_RELEASE:
                _priceCode = new NewReleasePrice();
                break;
            default:
                throw new IllegalArgumentException("Price code not present");
        }
    }
    public String getTitle (){
        return _title;
    };

    int getFrequentRenterPonts(int daysRented) {
        if ((_priceCode.getPrice() == NEW_RELEASE)
                &&
                daysRented > 1)
            return 2;
        else
            return 1;
    }
}

