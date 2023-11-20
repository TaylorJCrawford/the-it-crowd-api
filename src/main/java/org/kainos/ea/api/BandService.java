package org.kainos.ea.api;

import org.kainos.ea.cli.Band;
import org.kainos.ea.client.ActionFailedException;
import org.kainos.ea.client.DoesNotExistException;
import org.kainos.ea.db.BandDao;
import org.kainos.ea.db.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

public class BandService {
    private final DatabaseConnector databaseConnector;
    private final BandDao bandDao;

    public BandService(BandDao bandDao, DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
        this.bandDao = bandDao;
    }

    public List<Band> getBands() throws ActionFailedException, SQLException {
        return bandDao.getBands(databaseConnector.getConnection());
    }

    public Band getBandById(int id) throws ActionFailedException, DoesNotExistException, SQLException {
        return bandDao.getBandById(databaseConnector.getConnection(), id);
    }
}
