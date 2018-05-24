package BLL.getter.address_getter;

import ACQ.IAddress;

public interface IGetAddress {

    /**
     * Get address information from cpr number
     *
     * @param cpr
     * @return address
     */
    IAddress getAddress(String cpr);
}
