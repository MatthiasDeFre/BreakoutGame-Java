


package domain.managers;

import domain.Group;
import domain.Session;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class SessionManager extends Manager<Session>
{

    protected SessionManager()
    {
          super(Session.class, new PersistenceController());
    }
    public SessionManager(PersistenceController persistence)
    {
          super(Session.class, persistence);
        setItems(FXCollections.observableArrayList(persistence.getAllOfType(Session.class)));
    }

}
