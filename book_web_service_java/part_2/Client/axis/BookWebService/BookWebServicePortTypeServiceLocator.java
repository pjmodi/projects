/**
 * BookWebServicePortTypeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package axis.BookWebService;

public class BookWebServicePortTypeServiceLocator extends org.apache.axis.client.Service implements axis.BookWebService.BookWebServicePortTypeService {

    public BookWebServicePortTypeServiceLocator() {
    }


    public BookWebServicePortTypeServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BookWebServicePortTypeServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BookWebService
    private java.lang.String BookWebService_address = "http://swedishchef.cs.umn.edu:8080/axis/BookWebService.jws";

    public java.lang.String getBookWebServiceAddress() {
        return BookWebService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BookWebServiceWSDDServiceName = "BookWebService";

    public java.lang.String getBookWebServiceWSDDServiceName() {
        return BookWebServiceWSDDServiceName;
    }

    public void setBookWebServiceWSDDServiceName(java.lang.String name) {
        BookWebServiceWSDDServiceName = name;
    }

    public axis.BookWebService.BookWebServicePortType getBookWebService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BookWebService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBookWebService(endpoint);
    }

    public axis.BookWebService.BookWebServicePortType getBookWebService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            axis.BookWebService.BookWebServiceSoapBindingStub _stub = new axis.BookWebService.BookWebServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getBookWebServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBookWebServiceEndpointAddress(java.lang.String address) {
        BookWebService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (axis.BookWebService.BookWebServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                axis.BookWebService.BookWebServiceSoapBindingStub _stub = new axis.BookWebService.BookWebServiceSoapBindingStub(new java.net.URL(BookWebService_address), this);
                _stub.setPortName(getBookWebServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BookWebService".equals(inputPortName)) {
            return getBookWebService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://axis/BookWebService", "BookWebServicePortTypeService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://axis/BookWebService", "BookWebService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BookWebService".equals(portName)) {
            setBookWebServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
