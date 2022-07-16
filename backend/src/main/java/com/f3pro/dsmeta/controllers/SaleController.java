package com.f3pro.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.f3pro.dsmeta.entities.Sale;
import com.f3pro.dsmeta.service.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {
	@Autowired
	public SaleService saleService;
	
	@GetMapping
	public List<Sale> findSales(){
		return saleService.findSales();
		
	}

}
