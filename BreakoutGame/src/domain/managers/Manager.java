


package domain.managers;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceController;
import java.lang.Class;
import javafx.collections.transformation.FilteredList;

public abstract class Manager<T extends domain.managers.IManageable>
{
    /**
     * @param selected
     */
    private T selected;
    
     private final Class<T> type;
    
    /**
    * @param items
    */
    private ObservableList<T> items;
    private FilteredList<T> itemsFiltered;
    private PersistenceController persistenceController;
    protected Manager(Class<T> type, PersistenceController persistenceController)
    {
      //  setItems(FXCollections.observableArrayList());
     
        this.persistenceController = persistenceController;
        this.type = type;
    }
    /**
     * @return an unmodifiable copy of {@code items}
     */
    public ObservableList<T> getItems()
    {
        return FXCollections.unmodifiableObservableList(items);
    }
    
    public FilteredList<T> getFilteredItems() {
        return itemsFiltered;
    }
    /**
     * @return the selected item
     */
    public <T> T getSelected()
    {
        return (T) selected;
    }
    public PersistenceController getPersistenceController() {
        return persistenceController;
    }
    public void setItems(ObservableList newItems)
    {
        items = newItems;
        itemsFiltered = new FilteredList<>(items, p -> true);
        
    }
    
    public void setSelected(T item)
    {
        selected = item;
    }
    public void save(T object) {
        persistenceController.persistObject(type, object);
    }
    public void delete(T object) {
        persistenceController.deleteObject(type, object);
    }
}
