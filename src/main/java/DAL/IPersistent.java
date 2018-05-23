package DAL;

import ACQ.IDatabaseService;
import ACQ.IHttp;

public interface IPersistent {
    /**
     * Get an http client
     * @return IHttp http client
     */
    IHttp getHttp();

    IDatabaseService getDatabaseService();
}