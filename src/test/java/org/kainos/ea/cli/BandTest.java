package org.kainos.ea.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BandTest {

    @Test
    void getterAndSetterBandTest() {
        Band band = new Band(0, "Test");

        int bandId = 1;
        String bandName = "Band";

        band.setBandId(bandId);
        band.setBandName(bandName);

        assertEquals(band.getBandId(), bandId);
        assertEquals(band.getBandName(), bandName);
    }
}
