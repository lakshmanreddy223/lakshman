package com.icm.pogeneration.util;
 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
 
/**
 * 
 * 
 * THIS class used for PO Generation Document Format testing
 * @author raju
 *
 */
public class TextFooter {
 
    class MyFooter extends PdfPageEventHelper {
        Font ffont = new Font(Font.FontFamily.HELVETICA, 5, Font.NORMAL);
        
        
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            try {
            	Image logo = Image.getInstance(getClass().getClassLoader().getResource("logo.png"));
				logo.scaleAbsoluteHeight(30);
				logo.scaleAbsoluteWidth(100);
				//document.add(logo);
				Chunk chunk = new Chunk(logo, -45, -35);
				 
            Phrase header = new Phrase(chunk);
                
            Phrase footer1 = new Phrase("Niyogin Fintech Limited", new Font(Font.FontFamily.UNDEFINED, 5, Font.NORMAL, BaseColor.BLUE));
            footer1.setLeading(2); 
            Phrase footer2 = new Phrase("(erstwhile M3 Global Finance Limited)",ffont);
            
            Phrase footer3 = new Phrase("(CIN L65910MH1988PLC239746)",ffont);
            
            Phrase footer4 = new Phrase("Reg Office: 307 3rd Floor, Marathon Icon, Off Ganpatrao Kadam Marg, Lower Parel (West), Mumbai -400",ffont);
            
            Phrase footer5 = new Phrase("013 Tel: 022 6251 4646 | email : info@niyogin.in | Website : www.niyogin.in",ffont);
            
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    header,
                    (document.right()),
                    document.top()+80, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    footer1,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom()-60, 0);
                      ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    footer2,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom()-50, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    footer3,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom()-40, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    footer4,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom()-30, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    footer5,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom()-20, 0);
       
} catch (Exception e) {
				
			}
        }
    }
 
    public static final String DEST = "/home/raju/page_footer.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException, ParseException {
    	
    	ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    	File file = new File(classLoader.getResource("abc.txt").getFile());
    	 
    	//File is found
    	System.out.println("File Found : " + file.exists());
    	System.out.println("File Found : " + file.getPath());
    	   /*Format format = com.ibm.icu.text.NumberFormat.getNumberInstance(new Locale("en", "IN"));
    	    String str = format.format(61313154463.65775);
    	    String amount  = "32423423.94";
    	
    	    System.out.println("-----1-----"+str);
    	    System.out.println("----2-----"+getAmountFormat(amount));
    	    System.out.println("----3-----"+getPatternFormat("32423423.94"));


    	    
    	    SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
    	    SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
    	    Date date = format1.parse("05/01/1999");
    	    System.out.println(CustomDate.getPODate("12/29/2018"));*/
    	    
    	    
      /*  File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TextFooter().createPdf(DEST);*/
    
    }
 /// Get Amount In Pattern
    public static String getAmountFormat(String amount) {
    	Format format = com.ibm.icu.text.NumberFormat.getNumberInstance(new Locale("en", "IN"));
    	return format.format(Double.parseDouble(amount)).split("\\.")[0];
     }
    
  //Get Rate & Quantity In Pattern
    public static String getPatternFormat(String amount) {
   	Format format = com.ibm.icu.text.NumberFormat.getNumberInstance(new Locale("en", "IN"));
   	return format.format(new Integer(String.valueOf((int)(Double.parseDouble(amount)))));
    }
    
    public void createPdf(String filename) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        document.setMargins(20, 20, 100, 100);
        document.setMarginMirroring(false);
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        MyFooter event = new MyFooter();
        writer.setPageEvent(event);
        // step 3
        document.open();
        Paragraph para = new Paragraph("PO Number : "+"38/2017-2018");
    	para.setAlignment(Element.ALIGN_LEFT);
    	para.setIndentationLeft(385);
    	para.setSpacingBefore(50);
    	document.add(para);
    	
    	Paragraph para1 = new Paragraph("PO Date : "+"June 28th, 2018");
    	para1.setAlignment(Element.ALIGN_LEFT);
    	para1.setIndentationLeft(385);
    	para1.setSpacingAfter(25);
    	document.add(para1);
    	
    	Font myContentStyle=new Font();
	    myContentStyle.setStyle("bold");
	    
	    Chunk blueText = new Chunk("Raju : ", myContentStyle); 
	    
        Paragraph termandcondition = new Paragraph();
        termandcondition.add(blueText);
        termandcondition.add("1. Please return the duly accepted duplicate copy of this Purchase Order. If the duplicate copy is not received within 15 days then the PO will be treated as accepted by you.\n" + 
    			"	2. Please quote the PO number in all your communication, challans and bills. \n" + 
    			"	3. Send your bill in duplicate along with receipt challan at the below mentioned address. \n" + 
    			"	4. If delivery is not made as per delivery schedule, the company reserves the right to cancel this PO. \n" + 
    			"	5. Payment shall not be made if the goods/ services are not as per PO or if their quality is sub-standard. \n" + 
    			"	6. The company reserves the right to deduct/statutory levies such as TDS as per prevailing laws. \n" + 
    			"	7. The PO is subject to Mumbai jurisdiction. \n" + 
    			"	8. Invoice to be submitted within 30 days from the date of delivery or else payment will be put on hold.");
    	termandcondition.setAlignment(Element.ALIGN_LEFT);
    	termandcondition.setSpacingBefore(40);
    	termandcondition.setSpacingAfter(80);
    	document.add(termandcondition);
    	Paragraph termandcondition1 = new Paragraph("1. Please return the duly accepted duplicate copy of this Purchase Order. If the duplicate copy is not received within 15 days then the PO will be treated as accepted by you.\n" + 
    			"	2. Please quote the PO number in all your communication, challans and bills. \n" + 
    			"	3. Send your bill in duplicate along with receipt challan at the below mentioned address. \n" + 
    			"	4. If delivery is not made as per delivery schedule, the company reserves the right to cancel this PO. \n" + 
    			"	5. Payment shall not be made if the goods/ services are not as per PO or if their quality is sub-standard. \n" + 
    			"	6. The company reserves the right to deduct/statutory levies such as TDS as per prevailing laws. \n" + 
    			"	7. The PO is subject to Mumbai jurisdiction. \n" + 
    			"	8. Invoice to be submitted within 30 days from the date of delivery or else payment will be put on hold.");
    	termandcondition1.setAlignment(Element.ALIGN_LEFT);
    	termandcondition1.setSpacingAfter(80);
    	document.add(termandcondition1);
    	Paragraph termandcondition2 = new Paragraph("1. Please return the duly accepted duplicate copy of this Purchase Order. If the duplicate copy is not received within 15 days then the PO will be treated as accepted by you.\n" + 
    			"	2. Please quote the PO number in all your communication, challans and bills. \n" + 
    			"	3. Send your bill in duplicate along with receipt challan at the below mentioned address. \n" + 
    			"	4. If delivery is not made as per delivery schedule, the company reserves the right to cancel this PO. \n" + 
    			"	5. Payment shall not be made if the goods/ services are not as per PO or if their quality is sub-standard. \n" + 
    			"	6. The company reserves the right to deduct/statutory levies such as TDS as per prevailing laws. \n" + 
    			"	7. The PO is subject to Mumbai jurisdiction. \n" + 
    			"	8. Invoice to be submitted within 30 days from the date of delivery or else payment will be put on hold.");
    	termandcondition2.setAlignment(Element.ALIGN_LEFT);
    	termandcondition2.setSpacingAfter(80);
    	document.add(termandcondition2);
    	Paragraph termandcondition3 = new Paragraph("1. Please return the duly accepted duplicate copy of this Purchase Order. If the duplicate copy is not received within 15 days then the PO will be treated as accepted by you.\n" + 
    			"	2. Please quote the PO number in all your communication, challans and bills. \n" + 
    			"	3. Send your bill in duplicate along with receipt challan at the below mentioned address. \n" + 
    			"	4. If delivery is not made as per delivery schedule, the company reserves the right to cancel this PO. \n" + 
    			"	5. Payment shall not be made if the goods/ services are not as per PO or if their quality is sub-standard. \n" + 
    			"	6. The company reserves the right to deduct/statutory levies such as TDS as per prevailing laws. \n" + 
    			"	7. The PO is subject to Mumbai jurisdiction. \n" + 
    			"	8. Invoice to be submitted within 30 days from the date of delivery or else payment will be put on hold.");
    	termandcondition3.setAlignment(Element.ALIGN_LEFT);
    	termandcondition3.setSpacingAfter(80);
    	document.add(termandcondition3);
    	Paragraph termandcondition4 = new Paragraph("1. Please return the duly accepted duplicate copy of this Purchase Order. If the duplicate copy is not received within 15 days then the PO will be treated as accepted by you.\n" + 
    			"	2. Please quote the PO number in all your communication, challans and bills. \n" + 
    			"	3. Send your bill in duplicate along with receipt challan at the below mentioned address. \n" + 
    			"	4. If delivery is not made as per delivery schedule, the company reserves the right to cancel this PO. \n" + 
    			"	5. Payment shall not be made if the goods/ services are not as per PO or if their quality is sub-standard. \n" + 
    			"	6. The company reserves the right to deduct/statutory levies such as TDS as per prevailing laws. \n" + 
    			"	7. The PO is subject to Mumbai jurisdiction. \n" + 
    			"	8. Invoice to be submitted within 30 days from the date of delivery or else payment will be put on hold.");
    	termandcondition4.setAlignment(Element.ALIGN_LEFT);
    	termandcondition4.setSpacingBefore(40);
    	termandcondition4.setSpacingAfter(80);
    	document.add(termandcondition4);
    	Paragraph termandcondition5 = new Paragraph("1. Please return the duly accepted duplicate copy of this Purchase Order. If the duplicate copy is not received within 15 days then the PO will be treated as accepted by you.\n" + 
    			"	2. Please quote the PO number in all your communication, challans and bills. \n" + 
    			"	3. Send your bill in duplicate along with receipt challan at the below mentioned address. \n" + 
    			"	4. If delivery is not made as per delivery schedule, the company reserves the right to cancel this PO. \n" + 
    			"	5. Payment shall not be made if the goods/ services are not as per PO or if their quality is sub-standard. \n" + 
    			"	6. The company reserves the right to deduct/statutory levies such as TDS as per prevailing laws. \n" + 
    			"	7. The PO is subject to Mumbai jurisdiction. \n" + 
    			"	8. Invoice to be submitted within 30 days from the date of delivery or else payment will be put on hold.");
    	termandcondition5.setAlignment(Element.ALIGN_LEFT);
    	termandcondition5.setSpacingAfter(80);
    	document.add(termandcondition5);
    	Paragraph termandcondition6 = new Paragraph("1. Please return the duly accepted duplicate copy of this Purchase Order. If the duplicate copy is not received within 15 days then the PO will be treated as accepted by you.\n" + 
    			"	2. Please quote the PO number in all your communication, challans and bills. \n" + 
    			"	3. Send your bill in duplicate along with receipt challan at the below mentioned address. \n" + 
    			"	4. If delivery is not made as per delivery schedule, the company reserves the right to cancel this PO. \n" + 
    			"	5. Payment shall not be made if the goods/ services are not as per PO or if their quality is sub-standard. \n" + 
    			"	6. The company reserves the right to deduct/statutory levies such as TDS as per prevailing laws. \n" + 
    			"	7. The PO is subject to Mumbai jurisdiction. \n" + 
    			"	8. Invoice to be submitted within 30 days from the date of delivery or else payment will be put on hold.");
    	termandcondition6.setAlignment(Element.ALIGN_LEFT);
    	termandcondition6.setSpacingAfter(80);
    	document.add(termandcondition6);
    	Paragraph termandcondition7 = new Paragraph("1. Please return the duly accepted duplicate copy of this Purchase Order. If the duplicate copy is not received within 15 days then the PO will be treated as accepted by you.\n" + 
    			"	2. Please quote the PO number in all your communication, challans and bills. \n" + 
    			"	3. Send your bill in duplicate along with receipt challan at the below mentioned address. \n" + 
    			"	4. If delivery is not made as per delivery schedule, the company reserves the right to cancel this PO. \n" + 
    			"	5. Payment shall not be made if the goods/ services are not as per PO or if their quality is sub-standard. \n" + 
    			"	6. The company reserves the right to deduct/statutory levies such as TDS as per prevailing laws. \n" + 
    			"	7. The PO is subject to Mumbai jurisdiction. \n" + 
    			"	8. Invoice to be submitted within 30 days from the date of delivery or else payment will be put on hold.");
    	termandcondition7.setAlignment(Element.ALIGN_LEFT);
    	termandcondition7.setSpacingAfter(80);
    	document.add(termandcondition7);
        // step 5
        document.close();
    }
}