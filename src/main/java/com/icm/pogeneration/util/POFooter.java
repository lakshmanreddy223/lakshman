package com.icm.pogeneration.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.org.apache.bcel.internal.generic.POP;

public class POFooter extends PdfPageEventHelper{
	Font font = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
	 
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        try {
        Image logo = Image.getInstance(getClass().getClassLoader().getResource("logo.png"));
		logo.scaleAbsoluteHeight(30);
		logo.scaleAbsoluteWidth(100);
		//document.add(logo);
		Chunk chunk = new Chunk(logo, -45, -35);
		
		Font myContentStyle=new Font();
	    myContentStyle.setStyle("bold");
	    myContentStyle.setSize(8);
		
		Phrase header = new Phrase(chunk);
        
        Phrase footer1 = new Phrase(POProperties.getCompanyname(), new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLUE));
        
        Phrase footer2 = new Phrase(POProperties.getCompanyname2(), font);
        
        Phrase footer3 = new Phrase(POProperties.getCin(),font);
        
        Phrase footer4 = new Phrase();
        Chunk footertextbold = new Chunk("Reg Office: ", myContentStyle); 
        Chunk footertext = new Chunk(POProperties.getAddress1(), font); 
        footer4.add(footertextbold);
        footer4.add(footertext);
        
        Phrase footer5 = new Phrase(POProperties.getAddress2(),font);
        
        int r = 0;

        if(POProperties.getCompanyname2().equals("null")) {
        	r = 10;
        }
        
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                header,
                (document.right()),
                document.top()+80, 0);
      
	    ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
	                footer1,
	                (document.right() - document.left()) / 2 + document.leftMargin(),
	                document.bottom()-20, 0);

	    if(!POProperties.getCompanyname2().equals("null")) {

		    ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
		                footer2,
		                (document.right() - document.left()) / 2 + document.leftMargin(),
		                document.bottom()-(30-r), 0);
	    }

        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                footer3,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom()-(40-r), 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                footer4,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom()-(50-r), 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                footer5,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom()-(60-r), 0);
        }catch(Exception e) {
        	
        }
    }
}
