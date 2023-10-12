import Customer.Customer;
import Enums.Category;
import Enums.States;
import Interfaces.CategoriesLambda;
import Interfaces.LambdaInterface;
import Order.Order;
import Product.Product;

import javax.print.MultiDocPrintService;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;
import java.util.stream.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


       //Creo Le liste da popolare e gli array per categorie e stati.
        List<Product> products = new ArrayList<Product>(){};
        List<Order> orders = new ArrayList<Order>(){};

        Category[] categories = new Category[]{Category.BOOKS,Category.BABY,Category.BOYS};

        String[] states = new String[]{States.SPEDITO.toString(),States.PROCESSATO.toString(),States.ARRIVATO.toString()};
        List<Product> moreThan100PriceBooksList = new ArrayList<Product>(){};


        //Creo le lambda functions per filtrare gli stati dei vari prodotti
        CategoriesLambda isBook = product -> "BOOKS".equals(product.getCategory());
        CategoriesLambda isBabies = product -> "BABY".equals(product.getCategory());
        CategoriesLambda isBoys = product -> "BOYS".equals(product.getCategory());


        //creo nuovi Customer e li inserisco in un array di tipo Customer[] in modo statico
        Customer customer1 = new Customer("Rolando",5);
        Customer customer2 = new Customer("Gerry",2);
        Customer customer3 = new Customer("Scotti",3);
        Customer customer4 = new Customer("Salsa",2);
        Customer customer5 = new Customer("Pomodoro",5);

        Customer[] customers =new Customer[5];
        customers[0]=customer1;
        customers[1]=customer2;
        customers[2]=customer3;
        customers[3]=customer4;
        customers[4]=customer5;

        //Inizio un ciclo for
        for( int i =0; i<=200;i++){

            //Ad ogni iterazione del ciclo creo un prod di tipo Prodotto e lo aggiungo alla lista products.
            double randomDouble = 50 + Math.random() * (150 - 50);
            Random random = new Random();
            int randomValue = random.nextInt(3);
            Product prod = new Product("prodotto"+i, categories[randomValue].toString(),randomDouble);
            products.add(prod);

            //Se sono alla ventesima iterazione entro in questo if
           if(i>194){
               //Creo un altro ciclo
               for(int a =0;a<5;a++){
                   //Ad ogni iterazione del ciclo creo un order di tipo Order e lo aggiungo alla lista orders.
                   LocalDate randomDate = generateRandomDate(LocalDate.of(2020, 1, 9),
                       LocalDate.of(2021, 10, 20));
                   LocalDate todayDate = LocalDate.now();
                   String state="";
                   //Setto lo stato dell'ordine in base al valore delle variabili dichiarate sopra e inizializzate nel metodo
                   // generateRandomDate(LocalDate a,LocalDate b)
                   if(randomDate.isBefore(todayDate)||randomDate.equals(todayDate)){
                       state= states[2];
                   }else if(randomDate.isAfter(todayDate)){
                       state= states[0];
                   }

                   //Assegno un cliente diverso ad ogni ordine dall'array di Customer.

                   Customer customer = customers[a];
                   //creo una Lista di prodotti con categoria BABY uso uno stream() con un filter più una lambda function per ritornare solo i prodotti con categoria BABY
                   //List<Product> babyProducts = products.stream().filter(isBabies).collect(Collectors.toList());
                   List<Product> alwaysDifferentProducts =new ArrayList<>();
                   switch (a){
                       case 0:
                           for (int even = 0; even < products.size()-1; even++) {
                               if (even % 2 == 0) {
                                   alwaysDifferentProducts.add(products.get(i));
                               }
                           }
                           break;
                       case 1:
                           for (int odd = 0; odd < products.size()-1; odd++) {
                               if (odd % 2 != 0) {
                                   alwaysDifferentProducts.add(products.get(i));
                               }
                           }
                               break;
                       case 2:
                           for (int threeN = 0; threeN < products.size()-1; threeN++) {
                               if (threeN % 3 != 0) {
                                   alwaysDifferentProducts.add(products.get(i));
                               }
                           }
                           break;
                       case 3:
                           for (int three = 0; three < products.size()-1; three++) {
                               if (three % 3 == 0) {
                                   alwaysDifferentProducts.add(products.get(i));
                               }
                           }
                           break;
                       case 4:
                           for (int five = 0; five < products.size()-1; five++) {
                               if (five % 5 == 0) {
                                   alwaysDifferentProducts.add(products.get(i));
                               }
                           }
                           break;

                   }
                   Order order = new Order(state,randomDate,randomDate,alwaysDifferentProducts,customer);
                   orders.add(order);
               }

               //Per ogni ord della lista orders printo tramite i getters id,state,orderDate,DeliveryDate,Customer,Products
               for (Order ord : orders) {
                   System.out.println("ID: " + ord.getId());
                   System.out.println("State: " + ord.getStatus());
                   System.out.println("Order Date: " + ord.getOrderDate());
                   System.out.println("Delivery Date: " + ord.getDeliveryDate());
                   System.out.println("Customer: " + ord.getCustomer().getName());
                   List<Product> prods = ord.getProducts();
                   System.out.println("Products in the Order:");
                   //per ogni prodotto all'interno della List products printo name,category e in base a quest'ultima il prezzo scontato o meno del 10%
                   for (Product product : prods) {
                       System.out.println("Product Name: " + product.getName());
                       System.out.println("Product Category: " + product.getCategory());
                       if(product.getCategory().equals("BOYS")){
                           double price =product.getPrice()-( product.getPrice()*10/100);
                           System.out.println("Product Price with discount: " + price);
                       }else{
                           System.out.println("Product Price: " + product.getPrice());
                       }
                   }

               }
           }

        }


        //creo la lambda con la classe Predicate
        Predicate<Product> isGreaterThan100 = p -> p.getPrice() > 100;
        Predicate<Customer> isTies2 = c -> c.getTies().equals(2);


        //Creo productStream di tipo Stream che mi permette di filtrare i products in base alle lambda.
        Stream<Product> productStream = products.stream().filter(isBook.and(isGreaterThan100));




        moreThan100PriceBooksList = productStream.toList();

        moreThan100PriceBooksList.forEach(product -> {
            System.out.println("Prodotto: " + product.getName() + ", Prezzo: " + product.getPrice());
        });


        //Ottengo una lista di clienti con solo 2 ties e gli ordini fra le date descritte nella traccia
        List<Product> orderedProducts = orders.stream()
            .filter(order -> order.getCustomer().getTies() == 2)
            .filter(order -> order.getOrderDate().isAfter(LocalDate.of(2021, 2, 1))
                && order.getOrderDate().isBefore(LocalDate.of(2021, 4, 1)))
            .flatMap(order -> order.getProducts().stream())
            .toList();

        // Ciclo gli orderedProducts
        for (Product product : orderedProducts) {
            System.out.println("Prodotto con customer ties 2 e nel range di date : " + product);
        }


        //Esercizio 1 Giovedì
        //Creo una lambda Expression che dato un user, mi ritorna user e ordini

        Map<String, List<Order>> ordiniPerCliente = orders.stream().collect(Collectors.groupingBy(order -> order.getCustomer().getName()));
        Map<Customer, Double> totaleVenditePerCliente;


        ordiniPerCliente.forEach((customer,ordini)-> System.out.println("Customer: " + customer + " id degli ordini :" + ordini.toString()));
        ordiniPerCliente.forEach((customer,ordini)-> System.out.println
            ("Cliente: " + customer + ", Totale vendite: "+ ordini.stream().flatMap(order -> order.getProducts().stream()).mapToDouble(product -> product.getPrice()).sum()));


    }


   //Ritorno una data in un range preciso di date passate come parametro
    public static LocalDate generateRandomDate(LocalDate startDate, LocalDate endDate) {
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startDate.toEpochDay(), endDate.toEpochDay());
        return LocalDate.ofEpochDay(randomEpochDay);
    }
}
