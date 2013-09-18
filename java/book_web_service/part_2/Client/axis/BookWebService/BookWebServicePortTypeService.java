/**
 * BookWebServicePortTypeService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package axis.BookWebService;

public interface BookWebServicePortTypeService extends javax.xml.rpc.Service {
    public java.lang.String getBookWebServiceAddress();

    public axis.BookWebService.BookWebServicePortType getBookWebService() throws javax.xml.rpc.ServiceException;

    public axis.BookWebService.BookWebServicePortType getBookWebService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
