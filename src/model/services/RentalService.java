package model.services;

import java.time.Duration;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {
	private Double pricePerDay;
	private Double pricePerHour;
	
	private TaxService taxService;

	public RentalService(Double pricePerDay, Double pricePerHour, TaxService taxService) {
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
		this.taxService = taxService;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}
	
	public void processInvoice(CarRental carRental) {	
		// pegando diferenca entre datas entrada/saida e convertendo em minutos
		double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
		// calculando minutos em horas
		double hours = minutes / 60.0;
		double basicPayments;
		
		if(hours <= 12.0) {
			// funcao math.ceil joga resultado pra cima, no exemplo 4.16 para 5.0
			basicPayments = pricePerHour * Math.ceil(hours);
		}else {
			// calculo em dias
			basicPayments = pricePerDay *  Math.ceil(hours / 24.0);
		}
		
		//Double tax = taxService.tax(basicPayments);
		double tax = taxService.tax(basicPayments); 
		
		carRental.setInvoice(new Invoice(basicPayments, tax));
	}
	
	
}
