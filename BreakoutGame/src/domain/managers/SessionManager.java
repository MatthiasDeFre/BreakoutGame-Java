


package domain.managers;

import domain.Session;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class SessionManager extends Manager<Session>
{

    protected SessionManager()
    {
        super();
    }
    public SessionManager(PersistenceController persistence)
    {
        setItems(FXCollections.observableArrayList(persistence.getAllOfType(Session.class)));
    }

}
