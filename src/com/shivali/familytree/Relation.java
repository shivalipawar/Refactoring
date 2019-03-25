package com.shivali.familytree;

public enum Relation {
    PaternalUncle,
    MaternalUncle,
    PaternalAunt,
    MaternalAunt,
    SisterInLaw,
    BrotherInLaw,
    Son,
    Daughter,
    Siblings;

    public String getEnumAsString(Relation relationName) {
        return relationName.name();
    }
}
