package dev.wcs.tutoring.littlehelpers.delegate;

import lombok.experimental.Delegate;

public class User implements ContactInformation {

    @Delegate(types = {ContactInformation.class})
    private final ContactInformationSupport contactInformation = new ContactInformationSupport();

}
