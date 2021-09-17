import java.util.ArrayList;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.Scanner;

public class GraphicCardApp {
    public static void main(String[] a) throws IOException{
        Scanner in = new Scanner(System.in);
        DecimalFormat df2 = new DecimalFormat("0.00");

        LinkedList gcList = new LinkedList();
        GraphicCard gc;

        Queue orderQ = new Queue();
        Queue shopeeQ = new Queue();
        Queue qTemp = new Queue();

        Stack lazadaS = new Stack();
        Stack sTemp = new Stack();

        int invoice;
        String brand, model, memory, dateOrdered, platform, status;
        double price;

        System.out.println("WELCOME TO EASY TECH INVENTORY SYSTEM");
        System.out.println("'Your One Stop Graphic Card Centre'");

        boolean run = true;
        while (run) {
            System.out.println("\nPlease choose one of this options:");
            System.out.println(
                "\n1-Store\n2-Delete\n3-Search\n4-Shopee Order List\n5-Lazada Order List\n6-Calculation Information\n7-Exit");
            int select = in.nextInt();
            String dec = "";

            switch (select) {
                case 1: // store
                    // input graphic card details
                    System.out.print("\nHow many graphic card(s) do you want to store?: ");
                    int n = in.nextInt();

                    System.out.print("\n\nPlease fill all the details:");

                    for (int i = 0; i < n; i++) {
                        System.out.print("\n\nEnter invoice number: ");
                        invoice = in.nextInt();

                        in.nextLine(); // to allow string input after numerical input

                        System.out.print("Enter brand name: ");
                        brand = in.nextLine();

                        System.out.print("Enter model name: ");
                        model = in.nextLine();

                        System.out.print("Enter price: RM ");
                        price = in.nextDouble();

                        in.nextLine(); // to allow string input after numerical input

                        System.out.print("Enter date ordered (dd/mm/yyyy): ");
                        dateOrdered = in.nextLine();

                        System.out.print("Enter platform: ");
                        platform = in.nextLine();

                        System.out.print("Enter item status: ");
                        status = in.nextLine();

                        gc = new GraphicCard(invoice, brand, model, price, dateOrdered, platform, status);                        
                        orderQ.enqueue(gc);
                    }
                    // separate order to shopee queue and lazada stack
                    Object obj2;
                    while (!orderQ.isEmpty()) {
                        obj2 = orderQ.dequeue();

                        GraphicCard gc2 = new GraphicCard();
                        gc2 = (GraphicCard) obj2;

                        if (gc2.getPlatform().equalsIgnoreCase("Shopee")){
                            shopeeQ.enqueue(gc2);
                        }
                        else if (gc2.getPlatform().equalsIgnoreCase("Lazada")){
                            lazadaS.push(gc2);
                        }
                    }
                    break;
                case 2: // delete
                    int remove;
                    boolean running = true;

                    while(running){
                        System.out.print("\nData in which platform that you want to remove? (S-Shopee/L-Lazada)");
                        System.out.print("\nEnter 0 to exit to main menu: ");
                        dec = in.next();

                        String newList = "";
                        Object objR;
                        GraphicCard gcR = new GraphicCard();

                        if (dec.equalsIgnoreCase("S")) {
                            System.out.print("\nEnter invoice number that you want to remove: ");
                            remove = in.nextInt();

                            while (!shopeeQ.isEmpty()) {
                                objR = shopeeQ.dequeue();

                                gcR = (GraphicCard) objR;
                                if (gcR.getInvoice() != remove){
                                    qTemp.enqueue(gcR);
                                    newList += gcR.toString();
                                }
                            }
                            while (!qTemp.isEmpty()) {
                                objR = qTemp.dequeue();
                                shopeeQ.enqueue(objR);
                            }
                            System.out.print("\nUPDATED SHOPEE LIST: " + newList);
                            break;
                        } 
                        else if (dec.equalsIgnoreCase("L")) {
                            System.out.print("\nEnter invoice number that you want to remove");
                            remove = in.nextInt();

                            while (!lazadaS.isEmpty()) {
                                objR = lazadaS.pop();

                                gcR = (GraphicCard) objR;
                                if (gcR.getInvoice() != remove){
                                    sTemp.push(gcR);
                                    newList += gcR.toString();
                                }
                                while (!sTemp.isEmpty()) {
                                    objR = sTemp.pop();
                                    lazadaS.push(objR);
                                }
                                System.out.print("\nUPDATED LAZADA LIST: " + newList);
                                break;
                            }
                        }
                        else if (dec.equalsIgnoreCase("0"))
                            running = false;
                        else
                            System.out.print("\nSorry, invalid input. Please enter either S or L only.");
                    }
                    break;
                case 3: // search
                    int searchInvoice;
                    boolean found = false;
                    String output = "";

                    System.out.print("\n**NOTE THAT YOU CAN ONLY SEARCH BY USING INVOICE NUMBER**");
                    System.out.print("\nEnter invoice number: ");
                    searchInvoice = in.nextInt();

                    Object searchQ, searchS; // searchQ - search in Queue; searchS - search in Stack

                    while (!shopeeQ.isEmpty()) {
                        // dequeue and pop to get the data
                        searchQ = shopeeQ.dequeue();

                        // casting
                        GraphicCard gcQ = new GraphicCard();
                        gcQ = (GraphicCard) searchQ;

                        if (gcQ.getInvoice() == searchInvoice) {
                            found = true;
                            output = gcQ.toString();
                        }
                        qTemp.enqueue(gcQ);
                    }
                    while (!qTemp.isEmpty()) {
                        searchQ = qTemp.dequeue();
                        shopeeQ.enqueue(searchQ);
                    }
                    while(!lazadaS.isEmpty()){
                        // dequeue and pop to get the data
                        searchS = lazadaS.pop();

                        // casting
                        GraphicCard gcS = new GraphicCard();
                        gcS = (GraphicCard) searchS;

                        if (gcS.getInvoice() == searchInvoice) {
                            found = true;
                            output = gcS.toString();
                        }
                        sTemp.push(gcS);
                    }
                    while (!sTemp.isEmpty()) {
                        searchS = sTemp.pop();
                        lazadaS.push(searchS);
                    }
                    if (found)
                        JOptionPane.showMessageDialog(null, "Search found!" + output);
                    else
                        JOptionPane.showMessageDialog(null,
                            "Sorry, there is no graphic card details with such invoice number.");
                    break;
                case 4: // view shopee order list
                    Object objQ;
                    System.out.print("\nShopee Order List:");

                    while (!shopeeQ.isEmpty()) {
                        objQ = shopeeQ.dequeue();
                        System.out.println(objQ.toString());
                        qTemp.enqueue(objQ);
                    }
                    while (!qTemp.isEmpty()) {
                        objQ = qTemp.dequeue();
                        shopeeQ.enqueue(objQ);
                    }
                    break;
                case 5: // view lazada order list
                    Object objS;
                    System.out.print("\nLazada Order List:");

                    while (!lazadaS.isEmpty()) {
                        objS = lazadaS.pop();
                        System.out.println(objS.toString());
                        sTemp.push(objS);
                    }
                    while (!sTemp.isEmpty()) {
                        objS = sTemp.pop();
                        lazadaS.push(objS);
                    }
                    break;
                case 6: // calculation information
                    // to find total sales of graphic cards' sold in Shopee
                    double TSshopee = 0.0, TSlazada = 0.0;
                    int countGC = 0;
                    Object objTSS = new Object();
                    while (!shopeeQ.isEmpty()) {
                        objTSS = shopeeQ.dequeue();
                        GraphicCard g = (GraphicCard) objTSS;

                        if (g.getPlatform().equalsIgnoreCase("Shopee")) {
                            TSshopee += g.getPrice();
                            countGC++;
                        }
                        qTemp.enqueue(objTSS);
                    }
                    while (!qTemp.isEmpty()) {
                        objTSS = qTemp.dequeue();
                        shopeeQ.enqueue(objTSS);
                    }
                    System.out.print("\nTotal sales of graphic cards' sold in Shopee: RM" + df2.format(TSshopee));

                    // to find total sales of graphic cards' sold in Lazada
                    Object objTSL = new Object();
                    while (!lazadaS.isEmpty()) {
                        objTSL = lazadaS.pop();
                        GraphicCard g = (GraphicCard) objTSL;

                        if (g.getPlatform().equalsIgnoreCase("Lazada")) {
                            TSlazada += g.getPrice();
                            countGC++;
                        }
                        sTemp.push(objTSL);
                    }
                    while (!sTemp.isEmpty()) {
                        objTSL = sTemp.pop();
                        lazadaS.push(objTSL);
                    }
                    System.out.print("\nTotal sales of graphic cards' sold in Lazada: RM" + df2.format(TSlazada));

                    // to find average sales of graphic card sold
                    double avg = (TSshopee + TSlazada) / countGC;
                    System.out.print("\nAverage sales of graphic cards' sold: RM" + df2.format(avg));
                    
                    // to find expensive and cheapest graphic card sold
                    Queue findMaxMin = new Queue();
                    Object obj;
                    
                    // transfer all data from shopee queue and lazada stack into one queue findMaxMin
                    while(!shopeeQ.isEmpty()){
                        obj = shopeeQ.dequeue();
                        findMaxMin.enqueue(obj);
                        qTemp.enqueue(obj);
                    }
                    while(!qTemp.isEmpty()){
                        obj = qTemp.dequeue();
                        shopeeQ.enqueue(obj);
                    }
                    
                    while(!lazadaS.isEmpty()){
                        obj = lazadaS.pop();
                        findMaxMin.enqueue(obj);
                        sTemp.push(obj);
                    }
                    while(!sTemp.isEmpty()){
                        obj = sTemp.pop();
                        lazadaS.push(obj);
                    }
     
                    Object obj4;
                    Object objMax = findMaxMin.getFront();
                    GraphicCard gcMax = (GraphicCard) objMax;
                    
                    Object objMin = findMaxMin.getFront();
                    GraphicCard gcMin = (GraphicCard) objMin;
                    
                    double priceMax = gcMax.getPrice();
                    double priceMin = gcMin.getPrice();
                    while (!findMaxMin.isEmpty()) {
                        obj4 = findMaxMin.dequeue();
    
                        GraphicCard gc4 = (GraphicCard) obj4;

                        if (gc4.getPrice() > priceMax)
                            priceMax = gc4.getPrice();
                        else if(gc4.getPrice() < priceMin)
                            priceMin = gc4.getPrice();
                    
                        qTemp.enqueue(gc4);
                    }
                    while (!qTemp.isEmpty()) {
                        obj4 = qTemp.dequeue();
                        findMaxMin.enqueue(obj4);
                    }
                    System.out.print("\nExpensive graphic card sold: RM" + df2.format(priceMax));
                    System.out.println("\nCheapest graphic card sold: RM" + df2.format(priceMin));
                    break;
                case 7:
                    run = false;
                    break;
                default:
                    System.out.print("\nSorry, you have entered invalid input. Please enter 1-7 only.");
            }
        }
    }
}
