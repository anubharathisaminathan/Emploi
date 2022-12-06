package create_PDF;
//trying out background colours
import java.io.*;
import java.util.Scanner;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.io.image.ImageDataFactory;
//import com.itextpdf.text.Rectangle;
import com.itextpdf.kernel.geom.Rectangle;

public class Renderercopy {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("Enter:");
		Scanner sc=new Scanner(System.in);
		String name=sc.next();
		
	    String path="/Users/charu/Desktop/resume_pdf/";
	    path=path+name;
	    // Open PDF document in write mode
	    PdfWriter pdfWriter = new PdfWriter(path);
	    PdfDocument pdfDocument = new PdfDocument(pdfWriter);
	   
	    Document document = new Document(pdfDocument);
	
	    float offSet = 36;
		float gutter = 23;
		float columnWidth = (PageSize.A4.getWidth() - offSet * 2) / 2 - gutter;
		float columnHeight = PageSize.A4.getHeight() - offSet * 2;
		Rectangle[] columns = {
		new Rectangle(offSet, offSet, columnWidth, columnHeight),
		new Rectangle(
		offSet + columnWidth + gutter, offSet, columnWidth, columnHeight)};
		document.setRenderer(new ColumnDocumentRenderer(document, columns));
		document.setTextAlignment(TextAlignment.JUSTIFIED).setHyphenation(new HyphenationConfig("en", "uk", 3, 3));
		
		Paragraph p;
		String line;
		AreaBreak nextArea = new AreaBreak(AreaBreakType.NEXT_AREA);
		
		final String pic="/Users/charu/Desktop/resume_pdf/gw.png";
		
		File file=new File("/Users/charu/Desktop/resume_pdf/details2.txt");
		Image img = new Image(ImageDataFactory.create(pic), 0, 0, PageSize.A4.getWidth());
		document.add(img);
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		while ((line = br.readLine()) != null) {
			p = new Paragraph(line);
			
			/*if(line.equals("Our primary aim is to convey the ideas that have emerged over the past fifty years of AI"))
			{
				document.add(nextArea);
			}*/
//			if (line.isEmpty()) {
//				document.add(nextArea);
//			 }
			document.add(p);
		}
		
		document.close();
		sc.close();
	}
	

}

