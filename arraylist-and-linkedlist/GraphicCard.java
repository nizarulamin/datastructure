public class GraphicCard{
    private String brand;
    private String model;
    private double price;
    private String dateSold; // dd/mm/yyyy

    public GraphicCard(){
        brand = "NA";
        model = "NA";
        price = 0.0;
        dateSold = "dd/mm/yyyy";
    }

    //normal constructor
    public GraphicCard(String brand, String model, double price, String dateSold){
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.dateSold = dateSold;
    }

    //accessor
    public String getBrand(){return brand;}
    public String getModel(){return model;}
    public double getPrice(){return price;}
    public String getDateSold(){return dateSold;}

    //mutator
    public void setData(String brand, String model, double price, String dateSold){
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.dateSold = dateSold;
    }

    public String toString(){
        return "\n\nBrand: " + brand + 
                "\nModel: " + model + 
                "\nPrice: RM" + price +
                "\nDate sold: " + dateSold;
    }
}