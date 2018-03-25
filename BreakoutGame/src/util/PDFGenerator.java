


package util;

import domain.Assignment;
import domain.BoBAction;
import domain.BoBGroup;
import domain.Session;
import domain.Student;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import rst.pdfbox.layout.elements.ControlElement;
import rst.pdfbox.layout.elements.Document;
import rst.pdfbox.layout.elements.Paragraph;
import rst.pdfbox.layout.text.BaseFont;


public class PDFGenerator 
{
    private Document doc;
    
    private PDType1Font font;
    private float fontSize;
    private String exception;

    public PDFGenerator()
    {
        setFont(PDType1Font.TIMES_ROMAN);
        setFontSize(12);
    }
    
//<editor-fold defaultstate="collapsed" desc="Setters">
    public final void setFont(PDType1Font font)
    {
        this.font = font;
    }

    public final void setFontSize(float fontSize)
    {
        this.fontSize = fontSize;
    }    
//</editor-fold>

    
    public void createSessionDocument(Session session) throws IOException
    {
        createDocument();
        generateIndexParagraph(session);
        List<BoBGroup> groups =  session.getGroups();
        for (BoBGroup group : groups)
        {
            System.out.println(group);
            try
            {
                this.generateDocumentForGroup(group);
            } catch (IOException ex)
            {
                exception = ex.getMessage();
            }
        }
        if(exception!=null)
        {
            throw new IOException(exception);
        }
        saveDocument(session.getName()+".pdf");
    }
    
    private void generateIndexParagraph(Session session) throws IOException
    {
        
        List<String> actionStrings = session.getBox().getActions().stream().map(a -> a.getName()).collect(Collectors.toList());
        List<String> groupnames = session.getGroups().stream().map(gr -> gr.getName()).collect(Collectors.toList());
        
        StringBuilder actionBuilder = new StringBuilder();
        actionBuilder.append("*Acties voor de sessie:*\n");
        //Must use old java lambdas not working
        for (BoBAction groupname :  session.getBox().getActions())
        {
              actionBuilder.append("-+").append(groupname.getName()).append("\n");
        }
      //  actionStrings.forEach(action -> actionBuilder.append("-+").append(action).append("\n"));
        actionBuilder.append("\n\n");
        
        StringBuilder groupNamesBuilder = new StringBuilder();
        groupNamesBuilder.append("*Groepen voor de sessie:*\n");
        for (BoBGroup groupname : session.getGroups())
        {
            groupNamesBuilder.append("-+").append(groupname.getName()).append("\n");
        }
     //   groupnames.stream().forEach(grnm -> groupNamesBuilder.append("-+").append(grnm).append("\n"));
        
        Paragraph indexParagraph = new Paragraph();
        
        try
        {
            
             // indexParagraph.addMarkup("*Sessie:*" + session.getName() + "\n\n", fontSize, BaseFont.Times);
          //  indexParagraph.addMarkup(actionBuilder.toString(), fontSize, font, font, font, font);
           // indexParagraph.addMarkup(groupNamesBuilder.toString(), fontSize, font, font, font, font);
            indexParagraph.addMarkup("*Sessie:*" + session.getName() + " vindt plaats op " + session.getActivationDate() + "\n\n", fontSize, font, font, font, font);
            indexParagraph.addMarkup(actionBuilder.toString(), fontSize, font, font, font, font);
            indexParagraph.addMarkup(groupNamesBuilder.toString(), fontSize, font, font, font, font);
        } catch (IOException ex)
        {
            throw new IOException("Indexpagina voor de sessie kon niet worden gemaakt");
        }
        doc.add(indexParagraph);
        doc.add(ControlElement.NEWPAGE);
    }
    
    private void generateDocumentForGroup(BoBGroup group) throws IOException
    {
        Paragraph title = new Paragraph();
        List<String> students = group.getStudents().stream()
                .map(student -> String.format("%s, %s", student.getLastName(),student.getFirstName()))
                .collect(Collectors.toList());
        StringBuilder memberBuilder = new StringBuilder();
        for (Student student : group.getStudents())
        {
            memberBuilder.append("-+").append(String.format("%s %s", student.getLastName(), student.getFirstName())).append("\n");
        }
       
       // students.forEach(student -> memberBuilder.append("-+").append(student).append("\n"));
        
        try{
            title.addMarkup("*Groep:*" + group.getName() + "\n\n", fontSize, font, font, font, font);
            title.addMarkup("*Leden:*\n", fontSize, font, font, font, font);
            title.addMarkup(memberBuilder.toString(), fontSize, font, font, font, font);
            doc.add(title);
            doc.add(ControlElement.NEWPAGE);
        }
        catch(IOException ex)
        {
            throw new IOException("De index voor groep "+ group.getName() + "kon niet worden weggeschreven naar het document");
        }
        /*deze catches staan apart, omwille van de mogelijke inhoud vd exception, 
        bv een fout bij de index of een fout bij andere content zoals het pad 
        */
        
        try{
            writeGroupPathToDocument(group);
        }catch(IOException ex)
        {
            throw new IOException("Het pad van groep " + group.getName() + " kon niet worden weggeschreven");
        }
    }
    
//<editor-fold defaultstate="collapsed" desc="Create/Save Document">
    private void createDocument()
    {
        doc = new Document(40,60,40,60);
    }
    private void saveDocument(String filename) throws IOException
    {
        if(!filename.endsWith(".pdf"))
        {
            filename = filename + ".pdf";
        }
        
        try{
            OutputStream file = new FileOutputStream(filename);
            doc.save(file);
        } catch (FileNotFoundException ex)
        {
            throw new IOException("Bestand niet opgeslaan");
        } catch (IOException ex)
        {
            throw new IOException("Bestand niet opgeslaan");
        }
        
    }    
//</editor-fold>

    private void writeGroupPathToDocument(BoBGroup group) throws IOException
    {
        Paragraph titleParagraph = new Paragraph();
        titleParagraph.addMarkup("*Pad:*\n\n", fontSize, font, font, font, font);
        doc.add(titleParagraph);
        List<Assignment> assignments = group.getPath().getAssignments();
        for (Assignment assignment : assignments)
        {
           System.out.println("test");
            Paragraph assignmentParagraph = new Paragraph();
            StringBuilder assignmentBuilder = new StringBuilder();
            assignmentBuilder.append("Oefening ").append(assignment.getReferenceNr()).append(".").append("").append(assignment.getExercise().getName()).append("\n");
            try{
                assignmentParagraph.addMarkup(assignmentBuilder.toString(), fontSize, font, font, font, font);
                assignmentParagraph.addMarkup("Groepsbewerking:\n", fontSize, font, font, font, font);
                assignmentParagraph.addMarkup(assignment.getGroupOperation().getDescription() +"\n\n", fontSize, font, font, font, font);
                assignmentParagraph.addMarkup("Antwoord:\n", fontSize, font, font, font, font);
                assignmentParagraph.addMarkup(assignment.getAnwser()+"\n\n", fontSize, font, font, font, font);
                assignmentParagraph.addMarkup("Code:" + assignment.getAccessCode() + "\n\n", fontSize, font, font, font, font);
                doc.add(assignmentParagraph);
            }
            catch(IOException ex)
            {
                exception = "Het pad van groep " +group.getName() + "kona niet worden weggeschreven";
            } 
        }
        if(exception!=null)
        {
            throw new IOException(exception);
        }
        doc.add(ControlElement.NEWPAGE);
    }

}
