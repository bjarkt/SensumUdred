package DAL;

import ACQ.IHttp;
import DAL.database.IDatabaseService;

public interface IPersistent {
    /**
     * Get an http client
     * @return IHttp http client
     */
    IHttp getHttp();

    IDatabaseService getDatabaseService();
}