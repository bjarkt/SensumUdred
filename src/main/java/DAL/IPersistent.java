package DAL;

import ACQ.IDatabaseService;
import ACQ.IHttp;

public interface IPersistent {
    /**
     * Get an http client
     * @return IHttp http client
     */
    IHttp getHttp();

    /**
     * Get the database service.
     * @return database service
     */
    IDatabaseService getDatabaseService();
}