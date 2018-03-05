


package domain.managers;

import domain.Student;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class StudentManager extends Manager<Student>
{

    protected StudentManager()
    {
         super(Student.class, new PersistenceController());
    }
    
    public StudentManager(PersistenceController persistence)
    {
          super(Student.class, persistence);
        setItems(FXCollections.observableArrayList(persistence.getAllOfType(Student.class)));
    }
    
    public void addStudent(Student object)
    {
        ((Student) getSelected()).getId();
        System.out.println(((Student) getSelected()).getId());
        getPersistenceController().persistObject(Student.class, getSelected());
    }
    
    public void removeStudent(Student student)
    {
        
    }
    
    @Override
    public void setSelected(Student student)
    {
        super.setSelected(student);
    }
    
}
