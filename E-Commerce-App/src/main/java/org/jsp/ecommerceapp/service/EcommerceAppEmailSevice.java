package org.jsp.ecommerceapp.service;

import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import static org.jsp.ecommerceapp.util.ApplicationConstants.VERIFY_LINK;
import static org.jsp.ecommerceapp.util.ApplicationConstants.VERIFY_LINK1;
@Service
public class EcommerceAppEmailSevice {

	@Autowired
	private JavaMailSender javaMailSender;

	public String sendWelcomeMail(Merchant merchant,HttpServletRequest request) {
		String siteUrl=request.getRequestURL().toString();
		String url=siteUrl.replace(request.getServletPath(), "");
		String actual_url=url + VERIFY_LINK + merchant.getToken();
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper= new MimeMessageHelper(message);
		try {
			helper.setTo(merchant.getEmail());
			helper.setText(actual_url);
			helper.setSubject("ACTIVATION MAIL");
			javaMailSender.send(message);
			return "VERIFICATION MAIL HAS BEEN SENT";
		} catch (MessagingException e) {
			e.printStackTrace();
			return "CANNOT SEND VERIFICATION MAIL";
		}
	}



	public String sendWelcomeMail(User user,HttpServletRequest request) {
		String siteUrl=request.getRequestURL().toString();
		String url=siteUrl.replace(request.getServletPath(), "");
		String actual_url=url + VERIFY_LINK1 + user.getToken();
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper= new MimeMessageHelper(message);
		try {
			helper.setTo(user.getEmail());
			helper.setText(actual_url);
			helper.setSubject("ACTIVATION MAIL");
			javaMailSender.send(message);
			return "VERIFICATION MAIL HAS BEEN SENT";
		} catch (MessagingException e) {
			e.printStackTrace();
			return "CANNOT SEND VERIFICATION MAIL";
		}
	}

}
