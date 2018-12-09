package clients.factory;

import utils.MiddlewareConstants;

public enum ClientType {
    AXIS(MiddlewareConstants.AXIS),
    REST(MiddlewareConstants.REST),
    CXF(MiddlewareConstants.CXF);

    private final String name;

    ClientType(final String name) { this.name = name; }

    public String getName() { return this.name; }

}
