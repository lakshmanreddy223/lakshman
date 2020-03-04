package com.icm.pogeneration.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.Format;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import com.icm.pogeneration.model.Allocation;
import com.icm.pogeneration.model.PODownload;
import com.icm.pogeneration.model.POGeneration;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class POGenerateUtil {
	
/**
 * @Raju Vishwakarma
 * 
 * @category TO Generate PODocument
 * @param poGeneration
 * @throws FileNotFoundException
 * @throws DocumentException
 */
public void generate(POGeneration poGeneration) throws FileNotFoundException, DocumentException {
	Document document = new Document();
	document.setMargins(30, 30, 100, 100);
    document.setMarginMirroring(false);
	Font myContentStyle=new Font();
    myContentStyle.setStyle("bold");
    myContentStyle.setFamily("Calibri");
    
	// Create table header
	PdfPTable table = new PdfPTable(new float[] { 5, 20, 7, 7, 7,10 });
	table.setWidthPercentage(100);
	table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(new Phrase("Sr No.", myContentStyle));
	table.addCell(new Phrase("Description", myContentStyle));
	table.addCell(new Phrase("Qty", myContentStyle));
	table.addCell(new Phrase("Rate", myContentStyle));
	table.addCell(new Phrase("Period", myContentStyle));
	table.addCell(new Phrase("Amount", myContentStyle));
	table.setHeaderRows(1);
	ArrayList<Allocation> allocation = poGeneration.getAllocationList();
	ArrayList<String> payment_terms = poGeneration.getPayment_terms();
	Iterator<String> itr2=payment_terms.iterator();  

	Iterator<Allocation> itr=allocation.iterator();  
	// Insert table value
	for (int i=1;i<allocation.size()+1;i++){
		Allocation st=(Allocation)itr.next();
	    table.addCell(""+i);
		table.addCell(st.description);
		table.addCell(getAmountFormat(st.getQuantity()));
		table.addCell(getAmountFormat(st.getRate()));
		table.addCell(st.getPeriod());
		table.addCell(getAmountFormat(st.getAmount()));
	}
	// Insert total amount row
	for (int i=1;i<2;i++){
		table.addCell(""+"");
		table.addCell("");
		table.addCell("");
		table.addCell("");
		table.addCell(new Phrase("Total", myContentStyle));
		table.addCell(new Phrase(getAmountFormat(poGeneration.getTotalamount()), myContentStyle));		
	}
	// Increase table cell padding
	for (int i=1;i<(allocation.size()+2);i++){
		PdfPCell[] cells = table.getRow(i-1).getCells();		
		for (int j=0;j<cells.length;j++){
			cells[j].setPaddingBottom(7);
		}
	}
	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(getStoragePath()+"/"+poGeneration.getFileName()));
	POFooter event = new POFooter();
	writer.setPageEvent(event);
	document.open();
	// Generate PO right section
	Paragraph para = new Paragraph("PO Number : "+poGeneration.getPonumber());
	para.setAlignment(Element.ALIGN_LEFT);
	para.setIndentationLeft(350);
	para.setSpacingBefore(10);
	document.add(para);
	
	Paragraph para1 = new Paragraph("PO Date : "+CustomDate.getPODate(poGeneration.getDate()));
	para1.setAlignment(Element.ALIGN_LEFT);
	para1.setIndentationLeft(350);
	para1.setSpacingAfter(25);
	document.add(para1);
	
	/*Paragraph para2 = new Paragraph("PO Type : "+poGeneration.getPotype());
	para2.setAlignment(Element.ALIGN_LEFT);
	para2.setIndentationLeft(340);
	para2.setSpacingAfter(25);
	document.add(para2);*/
	
	// Generate PO Left section 
	Paragraph to = new Paragraph("To,");
	to.setAlignment(Element.ALIGN_LEFT);
	document.add(to);
	
	Paragraph name = new Paragraph(poGeneration.getName());
	name.setAlignment(Element.ALIGN_LEFT);
	document.add(name);
	
	/*Paragraph address = new Paragraph(poGeneration.getAddress());
	address.setAlignment(Element.ALIGN_LEFT);
	address.setSpacingAfter(25);
	document.add(address);*/
	
	Paragraph citynpin = new Paragraph(poGeneration.getCity()+", "+poGeneration.getPincode());
	citynpin.setAlignment(Element.ALIGN_LEFT);
	document.add(citynpin);
	
//	Paragraph pincode = new Paragraph(poGeneration.getPincode());
//	pincode.setAlignment(Element.ALIGN_LEFT);
//	document.add(pincode);
	
	Paragraph state = new Paragraph(poGeneration.getState());
	state.setAlignment(Element.ALIGN_LEFT);
	state.setSpacingAfter(25);
	document.add(state);
	
	Paragraph content1 = new Paragraph("Dear Sir/Madam,\n" + 
			"\n" + 
			"Kindly provide the below mentioned Goods/Services as per your quotation, subject to the terms & conditions given below. ");
	content1.setAlignment(Element.ALIGN_LEFT);
	content1.setSpacingAfter(25);
	document.add(content1);
	
	
	document.add(table);

	
//	Paragraph content_company_stamp1 = new Paragraph(POProperties.getCompanystamp1() +
	Paragraph content_company_stamp1 = new Paragraph( 
		"",myContentStyle);
	content_company_stamp1.setAlignment(Element.ALIGN_RIGHT);
	content_company_stamp1.setSpacingBefore(50);
	content_company_stamp1.setSpacingAfter(50); 
	document.add(content_company_stamp1);

	
	
	// SIGNATURE MESSAGE Paragraph
	Paragraph content2 = new Paragraph(POProperties.getSignature_message() + 
			"",myContentStyle);
	content2.setAlignment(Element.ALIGN_CENTER);
	content2.setSpacingBefore(50);
	content2.setSpacingAfter(50); 
	document.add(content2);
	
	// commented as per request due to signature is not required.
//	Paragraph content2 = new Paragraph(POProperties.getCompanystamp() + 
//			"",myContentStyle);
//	content2.setAlignment(Element.ALIGN_RIGHT);
//	content2.setSpacingBefore(50);
//	content2.setSpacingAfter(50); 
//	document.add(content2);
	
	Paragraph content3 = new Paragraph();
	content3.setAlignment(Element.ALIGN_LEFT);
	Chunk chunk = setBoldContent();
    content3.add(chunk);
    content3.add("Exclusive");
    content3.setSpacingAfter(100);
	document.add(content3); // commented by raju--------------------1 remove t&c
	
	/*  Term & Condition Section  */
	Font termStyle=new Font();
	termStyle.setStyle("bold");
	termStyle.setFamily("Calibri");
	termStyle.setStyle("underline");
	Paragraph content4 = new Paragraph("Terms & Conditions:",termStyle);
	content4.setAlignment(Element.ALIGN_CENTER);
	content4.setSpacingAfter(20);
	document.add(content4); // commented by raju--------------------1 remove t&c
	
	Paragraph termandcondition = new Paragraph(POProperties.getTermandcondition());
	termandcondition.setAlignment(Element.ALIGN_LEFT);
	termandcondition.setSpacingAfter(20);
	document.add(termandcondition); // commented by raju--------------------1 remove t&c
	
	// Payment Terms Heading
	Font termStyle1=new Font();
	termStyle1.setStyle("bold");
	termStyle1.setFamily("Calibri");
	termStyle1.setStyle("underline");
	
	Paragraph content5 = new Paragraph("Payment Terms:",termStyle1);
	content5.setAlignment(Element.ALIGN_CENTER);
	content5.setSpacingBefore(25);
	content5.setSpacingAfter(20);
	document.add(content5);
	
	StringBuffer payment_t_string = new StringBuffer("");
	for(int i=1;i<payment_terms.size()+1;i++) {
		StringBuffer point = new StringBuffer();
		point.append(i+". ");
		String p_t = itr2.next();
		point.append(p_t+".\n");
		payment_t_string.append(point);
		
	}
	
	// Payment term content
	System.out.println(payment_t_string.toString());
	Paragraph content6 = new Paragraph(payment_t_string.toString());
	content5.setSpacingBefore(20);
	content6.setAlignment(Element.ALIGN_LEFT);
	document.add(content6);
	
	
	document.close();	
	System.out.println("Done");
  }

 private Chunk setBoldContent() {
	Font myContentStyle=new Font();
	myContentStyle.setFamily("Calibri");
    myContentStyle.setStyle("bold");
	Chunk blueText = new Chunk("*Taxes : ", myContentStyle);      
	return blueText;
 }
 
 // Get po document storage folder path 
 public String getStoragePath() {
	File file = new File(PODownload.getPath());
	if(!file.exists()) {
		file.mkdirs();
	}
	return PODownload.getPath();
 }
 
 // Get Amount, Rate & Quantity In Pattern
 public String getAmountFormat(String amount) {
	Format format = com.ibm.icu.text.NumberFormat.getNumberInstance(new Locale("en", "IN"));
	return format.format(Double.parseDouble(amount)).split("\\.")[0];
 }
 
}