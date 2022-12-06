package create_PDF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
//import com.itextpdf.text.Rectangle;
import com.itextpdf.kernel.geom.Rectangle;

public class Half_pink_no_bullets {
	static void pdfGenerate(float w,float h,Document document) throws IOException //changed PdfDocument to Document
	{
		int count=1;
		//Rectangle one = new Rectangle(w,h);
		//Document document = new Document(pdfDocument,new com.itextpdf.kernel.geom.PageSize(one));
		
        //document.setPageSize(new com.itextpdf.kernel.geom.PageSize(one));
		
	    float offSet = 36;
		float gutter = 23;
		float columnWidth = (w - offSet * 2) / 2 - gutter;
		float columnHeight = h - offSet * 2;
		Rectangle[] columns = {
		new Rectangle(offSet, offSet, columnWidth, columnHeight-110),
		new Rectangle(
		offSet + columnWidth + gutter, offSet, columnWidth, columnHeight)};
		document.setRenderer(new ColumnDocumentRenderer(document, columns));
		document.setTextAlignment(TextAlignment.JUSTIFIED).setHyphenation(new HyphenationConfig("en", "uk", 3, 3));
		
		/*for background color*/
		final String background="/Users/charu/Desktop/resume_pdf/pink.png";
		Image img1 = new Image(ImageDataFactory.create(background), 0, 0, w);
		img1.setHeight(h);
		document.add(img1);
		/*background*/
		Paragraph p,p1,p2;
		p1=new Paragraph();
		p2=new Paragraph();
		String line,work="",line2="";
		AreaBreak nextArea = new AreaBreak(AreaBreakType.NEXT_AREA);
		
		final String pic="/Users/charu/Desktop/resume_pdf/pp.jpg";
		
		File file=new File("/Users/charu/Desktop/resume_pdf/details2.txt");
		Image img = new Image(ImageDataFactory.create(pic), offSet, columnHeight-60,100);
		document.add(img);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		Style normal = new Style();
		PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
		normal.setFont(font).setFontSize(14).setBold();
		
		while ((line = br.readLine()) != null) {
			
			if(count==1)
			{
				Text t=new Text(line).addStyle(normal);
				p1=new Paragraph().add(t);
				count=2;
			}
			else
			{
				p2=new Paragraph().add(line);
				count=1;
			}
			
			if(count==1 && !line.equals("NIL"))
			{
				document.add(p1);
				document.add(p2);
			}
			
			if(line.equals("WORK EXPERIENCE"))
			{
				work=br.readLine();
				break;
			}
//			p = new Paragraph(line);
			
	
			/*if(line.equals("Our primary aim is to convey the ideas that have emerged over the past fifty years of AI"))
			{
				document.add(nextArea);
			}*/
//			if (line.isEmpty()) {
//				document.add(nextArea);
//			 }
//			document.add(p);
		}
		document.add(nextArea);
		if(!work.equals("NIL"))
		{
			Text t=new Text(line).addStyle(normal);
			p = new Paragraph().add(t);
			document.add(p);
			document.add(new Paragraph(work));
		}
		
		while((line = br.readLine()) != null)
		{
			
			if(line.equals("ACHIEVEMENTS") || line.equals("SKILLS") || line.equals("AREAS OF INTEREST")
					|| line.equals("HOBBIES"))
			{
				line2 = br.readLine();
				if(line2.equals("NIL"))
					continue;
				Text t=new Text(line).addStyle(normal);
				p = new Paragraph().add(t);
				document.add(p);
				document.add(new Paragraph(line2));
			}
			else
			{
				p = new Paragraph(line);
				document.add(p);
			}
		}
		//document.close();
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Enter:");
		Scanner sc=new Scanner(System.in);
		String name=sc.next();
		//int count=1;

		String path="/Users/charu/Desktop/resume_pdf/";
	    path=path+name;
	    // Open PDF document in write mode
	    PdfWriter pdfWriter = new PdfWriter(path);
	    PdfDocument pdfDocument = new PdfDocument(pdfWriter);
	    
	    float width=PageSize.A4.getWidth();
	    float height=PageSize.A4.getHeight();
	    
	    //extra
	    Rectangle one = new Rectangle(width,height);
	    Document document = new Document(pdfDocument,new com.itextpdf.kernel.geom.PageSize(one));
	    
	    pdfGenerate(width,height,document); //pdfdocument to document
	    //document.close();
	    //extra
	    while(pdfDocument.getNumberOfPages()>1)
	    {
	    	height=height+200;
	    	pdfWriter = new PdfWriter(path);//extra
		    pdfDocument = new PdfDocument(pdfWriter);//extra
	    	one = new Rectangle(width,height);//extra
	    	document = new Document(pdfDocument,new com.itextpdf.kernel.geom.PageSize(one));//extra
			pdfGenerate(width,height,document);
	    }
		sc.close();
	    document.close();
	}


}
