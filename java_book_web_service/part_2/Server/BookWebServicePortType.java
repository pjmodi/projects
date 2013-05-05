
public interface BookWebServicePortType  extends java.rmi.Remote {
    String[] getBooks(String author) throws java.rmi.RemoteException;
    String getISBN(String title) throws java.rmi.RemoteException;
    String[] getAuthorsByTitle(String title) throws java.rmi.RemoteException; 
    String getPublisherByTitle(String title) throws java.rmi.RemoteException;
    String getEditionByTitle(String title) throws java.rmi.RemoteException;
    String getPriceByTitle(String title) throws java.rmi.RemoteException;
    String getPublicationYearByTitle(String title) throws java.rmi.RemoteException;

	String getTitle(String isbn) throws java.rmi.RemoteException;
    String[] getTitles(String[] isbn) throws java.rmi.RemoteException;
    String[] getAuthorsByISBN(String isbn) throws java.rmi.RemoteException;
    String getPublisherByISBN(String isbn) throws java.rmi.RemoteException;
    String getEditionByISBN(String isbn) throws java.rmi.RemoteException;
    String getPriceByISBN(String isbn) throws java.rmi.RemoteException;
    String getPublicationYearByISBN(String isbn) throws java.rmi.RemoteException;
}
