package create_PDF;
import java.io.*;
import java.util.Scanner;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.renderer.CanvasRenderer;
import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
//import com.itextpdf.text.Rectangle;

class MyCanvasRenderer extends CanvasRenderer {
protected boolean full = false;
MyCanvasRenderer(Canvas canvas) {
super(canvas);
}
@Override
public void addChild(IRenderer renderer) {
super.addChild(renderer);
full = Boolean.TRUE.equals(getPropertyAsBoolean(Property.FULL));
}

public boolean isFull() {
return full;
}
}

public class left {
	public static void main(String args[]) throws IOException
	{
		System.out.println("Enter:");
		Scanner sc=new Scanner(System.in);
		String name=sc.next();
		
	    String path="/Users/charu/Desktop/resume_pdf/";
	    path=path+name;
	    // Open PDF document in write mode
	    PdfWriter pdfWriter = new PdfWriter(path);
	    PdfDocument pdf = new PdfDocument(pdfWriter);
	    
	    PdfPage page = pdf.addNewPage();
	    PdfCanvas pdfCanvas = new PdfCanvas(page);
	    
	    Rectangle rectangle = new Rectangle(0, 0, PageSize.A4.getWidth(), PageSize.A4.getHeight());
	    Canvas canvas = new Canvas(pdfCanvas, pdf, rectangle);
	    MyCanvasRenderer renderer = new MyCanvasRenderer(canvas);
	    canvas.setRenderer(renderer);
	    PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
	    PdfFont bold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
	    Text title =new Text("The Strange Case of Dr. Jekyll and Mr. Hydegefjgegefbefhe\nhgdfuegyfhuefbdjcjegdfuhegf").setFont(bold);
	    Text author = new Text("Robert Louis Stevenson").setFont(font);
	    Paragraph p = new Paragraph().add(title).add(" by ").add(author);
	    canvas.add(p);
	    pdf.close();
	    
	    // Create document to add new elements
	   // Document document = new Document(pdf);
	    //document.add(p);
	}
}
