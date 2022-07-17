package com.f3pro.dsmeta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.f3pro.dsmeta.entities.Sale;
import com.f3pro.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {
	@Autowired
	private SaleRepository saleRepository;

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;

	public void sendSms(Long SaleId) {
		Sale sale = saleRepository.findById(SaleId).get();
		var date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();
		String msg = "O Vendedor: " + sale.getSellerName() + " Foi destaque em " + date  + 
				"com um total de R$ : " + String.format("%.2f", sale.getAmount())   ;

		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, msg).create();

		System.out.println(message.getSid());
	}
}