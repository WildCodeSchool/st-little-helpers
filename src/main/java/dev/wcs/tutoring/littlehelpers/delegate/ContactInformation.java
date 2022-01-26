package dev.wcs.tutoring.littlehelpers.delegate;

interface ContactInformation {

    String getFirstName();
    void setFirstName(String firstName);

    String getLastName();
    void setLastName(String lastName);

    String getFullName();

    String getPhoneNr();
    void setPhoneNr(String phoneNr);

}
