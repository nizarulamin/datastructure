import java.util.*;
import java.util.ArrayList;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class MainGC{
    public static void main(String[]a){
        Scanner in = new Scanner(System.in);
        DecimalFormat df2 = new DecimalFormat("0.00");

        LinkedList gcList = new LinkedList();

        String brand, model, dateSold;
        double price, totalSales = 0;

        GraphicCard gc;

        System.out.print("How many graphic cards sold?: "); //input amount of graphic cards
        int n = in.nextInt();
        in.nextLine();
        //input graphic card details
        for(int i=0; i<n; i++){
            System.out.print("\nEnter brand name: ");
            brand = in.nextLine();

            System.out.print("Enter model name: ");
            model = in.nextLine();

            System.out.print("Enter price: ");
            price = in.nextDouble();

            in.nextLine(); //to allow string input after numerical input

            System.out.print("Enter date sold (dd/mm/yyyy): ");
            dateSold = in.nextLine();

            gc = new GraphicCard(brand, model, price, dateSold);

            gcList.insertAtBack(gc);
        }

        //to find total sales of graphic cards' sold in 2020
        Object obj = gcList.getFirst();

        int countGC = 0;
        while(obj != null){
            GraphicCard g = (GraphicCard)obj;

            if(g.getDateSold().substring(6,10).equals("2020")){
                totalSales += g.getPrice();
                countGC++;
            }

            obj = gcList.getNext();
        }
        System.out.print("\nTotal sales of graphic cards' sold in 2020: RM" + df2.format(totalSales));

        //to find average sales of graphic card sold in 2020
        double avg = totalSales/countGC;
        System.out.print("\nAverage sales of graphic cards' sold in 2020: RM" + df2.format(avg));

        //to find the most expensive graphic card sold
        Object objMax = gcList.getFirst();
        GraphicCard gcMax = (GraphicCard)objMax;

        double priceMax = gcMax.getPrice();

        Object obj2 = gcList.getNext();

        while(obj2 != null){
            GraphicCard gc2 = (GraphicCard)obj2;

            if(gc2.getPrice() > priceMax)
                priceMax = gc2.getPrice();

            obj2 = gcList.getNext();
        }
        System.out.print("\nThe most expensive graphic card sold: RM" + df2.format(priceMax));

        //to find the cheapest graphic card sold
        Object objMin = gcList.getFirst();
        GraphicCard gcMin = (GraphicCard)objMin;

        double priceMin = gcMin.getPrice();

        Object obj3 = gcList.getNext();

        while(obj3 != null){
            GraphicCard gc3 = (GraphicCard)obj3;

            if(gc3.getPrice() < priceMin)
                priceMin = gc3.getPrice();

            obj3 = gcList.getNext();
        }
        System.out.print("\nThe most cheapest graphic card sold: RM" + df2.format(priceMin));

        //copy LinkedList to ArrayList
        ArrayList gcAList = new ArrayList();

        Object gcCopy = gcList.getFirst();

        int index = 0;
        while(gcCopy != null){
            gcAList.add(index, gcCopy);
            gcCopy = gcList.getNext();
            index++;
        }
        //remove an element in the array list
        System.out.print("\n\nEnter brand and model name you want to remove:");

        System.out.print("\nBrand: ");
        String removeB = in.nextLine();

        System.out.print("Model: ");
        String removeM = in.nextLine();

        int findIndex = 0;
        for(int i=0; i<gcAList.size(); i++){
            obj = gcAList.get(i);
            GraphicCard g = (GraphicCard)obj;

            if(g.getBrand().equals(removeB) && (g.getModel().equals(removeM)))
                findIndex = i;
            break;  
        }

        Object remove = gcAList.remove(findIndex);
        GraphicCard rem= (GraphicCard)remove;

        System.out.print("\n" + rem.getBrand() + " " + rem.getModel() + " " + "has been removed.");

        String newList="";
        for(int i=0; i<gcAList.size(); i++){
            obj = gcAList.get(i);
            GraphicCard output= (GraphicCard)obj;
            newList += output.toString();
        }
        System.out.print("\n\nUpdated list:" + newList);

        //search for graphic card(s) that sold in the first quarter of 2021 *Q1 = Jan-Mar
        boolean found = false;

        String output = "";
        for(int i=0; i<gcAList.size(); i++){
            obj = gcAList.get(i);
            GraphicCard g = (GraphicCard)obj;
            if(g.getDateSold().substring(3,10).equals("01/2021") ||
            g.getDateSold().substring(3,10).equals("02/2021")||
            g.getDateSold().substring(3,10).equals("03/2021")){
                found = true;
                output += g.toString();
            }
        }

        if(found)
            JOptionPane.showMessageDialog(null, "Graphic card(s) that sold in the Q1 2021:" + output);
        else
            JOptionPane.showMessageDialog(null,"No graphic card sold in Q1 2021.");
    }
}