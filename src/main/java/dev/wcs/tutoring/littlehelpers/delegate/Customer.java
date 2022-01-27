package dev.wcs.tutoring.littlehelpers.delegate;

import lombok.experimental.Delegate;

public class Customer implements ContactInformation {

    @Delegate(types = {ContactInformation.class})
    private final ContactInformationSupport contactInformation = new ContactInformationSupport();

}
