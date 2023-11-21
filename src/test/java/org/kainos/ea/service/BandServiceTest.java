package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.BandService;
import org.kainos.ea.cli.Band;
import org.kainos.ea.client.ActionFailedException;
import org.kainos.ea.client.DoesNotExistException;
import org.kainos.ea.db.BandDao;
import org.kainos.ea.db.DatabaseConnector;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BandServiceTest {
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    BandDao bandDao = Mockito.mock(BandDao.class);

    BandService bandService = new BandService(bandDao, databaseConnector);

    Connection conn;

    @Test
    void getBands_shouldReturnBands_whenDaoReturnsBands() throws ActionFailedException, SQLException {
        Band band1 = new Band(1,"Manager");
        Band band2 = new Band(2,"Manager");
        Band band3 = new Band(3,"Manager");

        List<Band> bands = new ArrayList<>();
        bands.add(band1);
        bands.add(band2);
        bands.add(band3);

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(bandDao.getBands(conn)).thenReturn(bands);

        List<Band> result = bandService.getBands();

        assertEquals(result, bands);
    }

    @Test
    void getBands_shouldThrowActionFailedException_whenDaoThrowsActionFailedException() throws ActionFailedException, SQLException {

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);

        Mockito.when(bandDao.getBands(conn)).thenThrow(ActionFailedException.class);

        assertThrows(ActionFailedException.class, () -> {
            bandService.getBands();
        });
    }

    @Test
    void getBandById_shouldReturnBand_whenDaoReturnsBand() throws ActionFailedException, DoesNotExistException, SQLException {
        int id = 1;
        Band band = new Band(1,"Manager");

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(bandDao.getBandById(conn, id)).thenReturn(band);

        Band result = bandService.getBandById(id);

        assertEquals(result, band);
    }
}
