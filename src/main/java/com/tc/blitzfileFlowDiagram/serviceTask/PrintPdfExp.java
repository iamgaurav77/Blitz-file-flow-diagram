package com.tc.blitzfileFlowDiagram.serviceTask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

@Component("PrintPdfExp")
public class PrintPdfExp implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		 Double contriamount = (Double) execution.getVariable("expamount");
		 String name = (String) execution.getVariable("name");
	     String password = (String) execution.getVariable("password");
	     String dropDown = (String) execution.getVariable("typeOfTransactions");
	     Boolean csv = (Boolean) execution.getVariable("csv");
	     
		 PDDocument document = new PDDocument();
		 PDPage firstPage = new PDPage();
		 document.addPage(firstPage);
		 
		 PDPageContentStream contentStream = new PDPageContentStream(document,firstPage);
		 contentStream.beginText();
		 contentStream.setFont(PDType1Font.TIMES_ROMAN, 18);
		 contentStream.setLeading(16.0f);
		 
		 contentStream.newLineAtOffset(25, firstPage.getTrimBox().getHeight()-25);
		 
         String join = "";
		 
		 if(contriamount >= 500) {
		      join = " 1";	 
		 }
		 else {
			 join = " 2";
		 }
		 
		 String ff = "Name is :- " + name;
		 String ss = "Type of transiction :-  " + dropDown + join;
		 String tt = "Amount is :- " + contriamount;
		 
		 contentStream.showText(ff);
		 contentStream.newLine();
		 
		 contentStream.showText(ss);
		 contentStream.newLine();
		 
		 contentStream.showText(tt);
		 contentStream.newLine();
		 
		 if(csv == true) {
			 String path = "C:\\PDF\\file.csv";
			 String line = "";
			 BufferedReader br = new BufferedReader(new FileReader(path));
			 while((line = br.readLine()) != null) {
				 String[] values = line.split(",");
				 
				 String temp0 = values[0];
				 temp0 = temp0.replaceAll("[^a-zA-Z0-9]", "");  
				 temp0 = temp0.replaceAll("[-+^]*", "");
				 
				 String temp1 = values[1];
				 temp1 = temp1.replaceAll("[^a-zA-Z0-9]", "");  
				 temp1 = temp1.replaceAll("[-+^]*", "");
				 
				 String temp2 = values[2];
				 temp2 = temp2.replaceAll("[^a-zA-Z0-9]", "");  
				 temp2 = temp2.replaceAll("[-+^]*", "");
				 
				 String temp = temp0 + "   " + temp1 + "   " + temp2;
				 
				 contentStream.showText(temp);
				 contentStream.newLine();
			 }
		 }
		 
		 contentStream.endText();
		 contentStream.close();
		 
		 Date date = (Date) Calendar.getInstance().getTime();  
         DateFormat dateFormat = new SimpleDateFormat("yyyy mm dd hh mm ss");  
         String strDate = dateFormat.format(date);  
        
		 
		 document.save("C:\\PDF\\expense\\" + strDate +".pdf");
		 document.close();
	    
	}

}
