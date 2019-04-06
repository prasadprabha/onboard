package com.cloud9.onboard.camunda.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.cloud9.onboard.camunda.request.StartProcessWithFormRequest;
import com.cloud9.onboard.camunda.response.ProcessDefinitionResponse;
import com.cloud9.onboard.camunda.response.StartProcessWithFormResponse;
import com.cloud9.onboard.pojo.FieldValue;

public interface ProcessService {
	
	public StartProcessWithFormResponse startProcessWithForm(StartProcessWithFormRequest request,String processDefinitionId);
	public Map<String,FieldValue> getStartFormVariables(String processDefinitionId);
	List<ProcessDefinitionResponse> getProcessDefinitions();
	public static  void sendmail(String receiverMailId, String name) {
		
		  try{ Properties props = new Properties(); props.put("mail.smtp.auth",
		  "true"); props.put("mail.smtp.starttls.enable", "true");
		  props.put("mail.smtp.host", "smtp.gmail.com"); props.put("mail.smtp.port",
		  "587");
		  
		  Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		  protected PasswordAuthentication getPasswordAuthentication() { return new
		  PasswordAuthentication("sharad199994@gmail.com", "roy_1994"); } }); String
		  messageContent="Welcome "+name.toUpperCase(); Message msg = new
		  MimeMessage(session); msg.setFrom(new
		  InternetAddress("sharad199994@gmail.com", false));
		  
		  msg.setRecipients(Message.RecipientType.TO,
		  InternetAddress.parse(receiverMailId)); msg.setSubject("On-boarding");
		  msg.setContent("On-boarding", "text/html"); msg.setSentDate(new Date());
		  
		  MimeBodyPart messageBodyPart = new MimeBodyPart();
		  messageBodyPart.setContent(messageContent, "text/html");
		  
		  Multipart multipart = new MimeMultipart();
		  multipart.addBodyPart(messageBodyPart); msg.setContent(multipart);
		  Transport.send(msg); } catch(Exception e){
		  
		  }
		 
	}
}
