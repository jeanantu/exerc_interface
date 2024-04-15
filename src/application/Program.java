package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrasilTaxService;
import model.services.RentalService;
import model.services.UsaTaxService;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel brother");
		System.out.print("Modelo do carro: ");
		String carModel = sc.nextLine();
		
		System.out.println("Retirada (dd/MM/yyyyhh:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		
		System.out.println("Retorno (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("Entre com o preço por hora: ");
		Double pricePerHour = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		Double pricePerDay = sc.nextDouble();
		
		System.out.print("Entre pais 1 para USA e 2 para Brasil: ");
		Integer pais = sc.nextInt();
		
		if (pais == 1) {
			RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new UsaTaxService());
			rentalService.processInvoice(cr);
		}else {
			RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrasilTaxService());
			rentalService.processInvoice(cr);
		}
			
		System.out.println("FATURA: ");
		System.out.println("Pagamento basico: "+cr.getInvoice().getBasicPayment());
		System.out.println("Imposto: "+cr.getInvoice().getTax());
		System.out.println("Pagamento total: "+cr.getInvoice().getTotalPayment());
		
		
		
		sc.close();
		
		

	}

}
