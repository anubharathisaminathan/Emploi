package create_PDF;

import java.io.*;
import java.util.Scanner;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;



public class ResumeTemplate {
	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println("Enter:");
		Scanner sc=new Scanner(System.in);
		String name=sc.next();
		
	    String path="/Users/charu/Desktop/resume_pdf/";
	    path=path+name;
	    // Open PDF document in write mode
	    PdfWriter pdfWriter = new PdfWriter(path);
	    PdfDocument pdfDocument = new PdfDocument(pdfWriter);
	    
	    //Rectangle layout = new Rectangle(PageSize.A4);
	    //layout.setBackgroundColor(BaseColor.PINK);
	    

	    // Create document to add new elements
	    Document document = new Document(pdfDocument);
	    

	    // Create Paragraph
	    Paragraph paragraph = new Paragraph("Hello Itext7");
	    paragraph.add("\n");
	    paragraph.add("My first PDF document in iText7.");
	    String a="Dr. Jekyll and Mr. Hyde";
	    Text title2 = new Text(a).setBold();
//	    Paragraph p = new Paragraph().add(title2);
//	    document.add(p);
//	    

	    // Add Paragraph to document
	    document.add(paragraph);

	    // Close document
	    document.close();
	    sc.close();
	  }

}
