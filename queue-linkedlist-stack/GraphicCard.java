public class GraphicCard{
    private int invoice;
    private String brand;
    private String model;
    private String platform; // lazada or shopee
    private double price;
    private String dateOrdered; // dd/mm/yyyy
    private String status; // Processing/Shipped/In Transit/Delivered

    public GraphicCard(){
        invoice = 0000;
        brand = "NA";
        model = "NA";
        price = 0.0;
        dateOrdered = "dd/mm/yyyy";
        platform = "NA";
        status = "NA";
    }

    //normal constructor
    public GraphicCard(int invoice, String brand, String model,  double price, String dateOrdered, String platform, String status){
        this.invoice = invoice;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.dateOrdered = dateOrdered;
        this.platform = platform;
        this.status = status;
    }

    //accessor
    public int getInvoice(){return invoice;}
    public String getBrand(){return brand;}
    public String getModel(){return model;}
    public double getPrice(){return price;}
    public String getDateOrdered(){return dateOrdered;}
    public String getPlatform(){return platform;}
    public String getStatus(){return status;}

    //mutator
    public void setData(int invoice, String brand, String model, double price, String dateOrdered, String platform, String status){
        this.invoice = invoice;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.dateOrdered = dateOrdered;
        this.platform = platform;
        this.status = status;
    }

    public String toString(){
        return "\n\nInvoice Number: " + invoice +
                "\nBrand: " + brand + 
                "\nModel: " + model + 
                "\nPrice: RM" + price +
                "\nDate Ordered: " + dateOrdered +
                "\nPlatform: " + platform +
                "\nStatus: " + status;
    }
}