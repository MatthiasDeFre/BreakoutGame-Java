


package domain.managers;

import domain.Exercise;
import domain.Student;
import javafx.collections.FXCollections;
import javax.persistence.EntityManager;
import persistence.JPAUtil;
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
    
    public void addStudent(Student student)
    {
        EntityManager entityManager=JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    @Override
    public void save(Student student)
    {
        getPersistenceController().setPersistMode(getManagerMode());
        ((Student) getSelected()).copy(student);
        System.out.println(((Student) getSelected()).getId());
        super.save(student);
        
    }
    
    @Override
    public void setSelected(Student student)
    {
        super.setSelected(student);
    }
    
}
