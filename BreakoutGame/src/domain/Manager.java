


package domain;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public abstract class Manager<T extends IManageable>
{
    /**
     * @param selected
     */
    private T selected;
    /**
    * @param items
    */
    private ObservableList<T> items;
    
    protected Manager()
    {
        setItems(FXCollections.observableArrayList());
    }
    /**
     * @return an unmodifiable copy of {@code items}
     */
    public List<T> getItems()
    {
        return FXCollections.unmodifiableObservableList(items);
    }
    /**
     * @return the selected item
     */
    public <T> T getSelected()
    {
        return (T) selected;
    }
    
    public void setItems(ObservableList newItems)
    {
        items = newItems;
    }
    
    public void setSelected(T item)
    {
        selected = item;
    }
    
}
