package model.services;

public class UsaTaxService implements TaxService{
	public Double tax(double amount) {
		if (amount <= 300.0) {
			return amount * 0.50;
		} else {
			return amount * 0.25;
		}
	}
}
