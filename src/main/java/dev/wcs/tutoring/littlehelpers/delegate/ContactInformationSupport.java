package dev.wcs.tutoring.littlehelpers.delegate;

import lombok.Data;

@Data
public class ContactInformationSupport implements ContactInformation {

    private String firstName;
    private String lastName;
    private String phoneNr;

    @Override
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

}
