


package util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


public class PDFGenerator 
{
    private float leftOffset;
    private float rightOffset;
    private float lineGap;
    
    private PDType1Font font;
    private float fontSize;
    
    private PDDocument document;

    public PDFGenerator()
    {
       setFont(PDType1Font.TIMES_ROMAN);
        setFontSize(12);
        setRightOffset(30);
        setLeftOffset(new PDPage().getMediaBox().getHeight()- 20);
    }
    

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public final void setLeftOffset(float leftOffset)
    {
        this.leftOffset = leftOffset;
    }

    public final void setRightOffset(float rightOffset)
    {
        this.rightOffset = rightOffset;
    }

    public void setLineGap(float lineGap)
    {
        this.lineGap = lineGap;
    }

    public final void setFont(PDType1Font font)
    {
        this.font = font;
    }

    public final void setFontSize(float fontSize)
    {
        this.fontSize = fontSize;
    }    
    //</editor-fold>
    
    public void generateGroupPathDocument(String filename)  //+ Group + Path
    {
        document = new PDDocument();
        
        //Single page?
        PDPage page = new PDPage();
        
        //add page to document
        document.addPage(page);
        
        try
        {
            try (PDPageContentStream stream = new PDPageContentStream(document, page))
            {
                stream.beginText();
                stream.setFont(font, fontSize);
                
                //TODO: procedure om een pad op dit bestand te zetten
                
                stream.newLineAtOffset(rightOffset,leftOffset);
                stream.showText("Test text");
                
                
                stream.endText();
            }
            
            document.save(filename);
            document.close();
            
        } catch (IOException ex)
        {
            Logger.getLogger(PDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
