package create_PDF;

import java.io.*;
import java.util.Scanner;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.text.Document;
//import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Paragraph;

public class Background {
	public static void main(String[] args) throws FileNotFoundException, DocumentException {

	    //Create layout and set background
	    Rectangle layout = new Rectangle(PageSize.A4);
	    layout.setBackgroundColor(BaseColor.PINK);
	    
	    Document document = new Document(layout); 
	    PdfWriter.getInstance(document, new FileOutputStream("pink.pdf"));
	    
	    //Open the document before adding any content
	    document.open();
	    
	    Paragraph paragraph = new Paragraph("BE THE CODER");
	    document.add(paragraph);
	    
	    //Close the document
	    document.close();
	  }

}
