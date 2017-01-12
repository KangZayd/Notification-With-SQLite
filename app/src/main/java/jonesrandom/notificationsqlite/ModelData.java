package jonesrandom.notificationsqlite;

public class ModelData {

    int Id;
    String Tittle;
    String Message;

    public ModelData() {
    }

    public ModelData(int Ids,String tittle, String message) {
        Id = Ids;
        Tittle = tittle;
        Message = message;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
