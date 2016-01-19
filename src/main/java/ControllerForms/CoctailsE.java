package ControllerForms;

public class CoctailsE {
    public static void main(String[] args) {
        Coctails coctails = new CoctailsBuilder()
                .buildName("B52")
                .buildType(0)
                .buildPrice(10.1)
                .build();
        System.out.println(coctails);
    }
}

class Coctails {
    String name;
    double price;

    @Override
    public String toString() {
        return name + " " + " " + price;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPrice(Double price) {
        this.price = price;
    }
}

class CoctailsBuilder {
    String nameb = "Default";
    Integer typeb = 0;
    double priceb = 0.0;

    CoctailsBuilder buildName(String nameb) {
        this.nameb = nameb;
        return this;
    }

    CoctailsBuilder buildType(Integer typeb) {
        this.typeb = typeb;
        return this;
    }

    CoctailsBuilder buildPrice(Double priceb) {
        this.priceb = priceb;
        return this;
    }

    Coctails build() {
        Coctails coctails = new Coctails();
        coctails.setName(nameb);
        coctails.setPrice(priceb);
        return coctails;
    }
}