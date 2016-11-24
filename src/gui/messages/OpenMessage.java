package gui.messages;

/**
 *  Komunikat o potrzebie otwarcia pliku, którego ścieżka dostępu jest podana w zmiennej wewnątrz klasy.
 *  Klasa rozszerz klasę abstrakcyjną Message.
 */
public class OpenMessage extends Message {
    public String filePath;
    
    public OpenMessage(String filePath){
        this.filePath = filePath;
    }
}
