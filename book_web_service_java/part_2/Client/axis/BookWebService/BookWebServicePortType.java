/**
 * BookWebServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package axis.BookWebService;

public interface BookWebServicePortType extends java.rmi.Remote {
    public java.lang.String[] getBooks(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String getISBN(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String[] getAuthorsByTitle(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String getPublisherByTitle(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String getEditionByTitle(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String getPriceByTitle(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String getPublicationYearByTitle(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String[] getTitles(java.lang.String[] in0) throws java.rmi.RemoteException;
    public java.lang.String[] getAuthorsByISBN(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String getPublisherByISBN(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String getEditionByISBN(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String getPriceByISBN(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String getPublicationYearByISBN(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String getTitle(java.lang.String in0) throws java.rmi.RemoteException;
}
