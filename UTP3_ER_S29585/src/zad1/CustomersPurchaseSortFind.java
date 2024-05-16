/**
 * @author Ekiert Rozalia S29585
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

public class CustomersPurchaseSortFind {

    private Comparator<Purchase> compareBy(String kategoria) {
        switch (kategoria) {
            case "Nazwiska":
                return Comparator.comparing(Purchase::getNazwisko)
                        .thenComparing(Purchase::getId);

            case "Koszty":
                return Comparator.comparingDouble(Purchase::getKoszt).reversed()
                        .thenComparing(Purchase::getId);

            default:
                return (entry1, entry2) -> 0;
        }
    }

    private ArrayList<Purchase> purchases = new ArrayList<>();

    public void showPurchaseFor(String id) {
        System.out.println("Klient "+id);
        int numId = Integer.parseInt(id.substring(1));;
        boolean found = false;

        for (Purchase purchase : this.purchases) {
            if (purchase.getId() == numId) {
                System.out.println(purchase);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Brak zakupów dla wybranego klienta");
        }
        System.out.println();
    }

    public void readFile(String fname) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String line;

            while ((line = br.readLine()) != null) {
                this.purchases.add(new Purchase(line));
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showSortedBy(String kategoria) {
        System.out.println(kategoria);

        this.purchases.stream().sorted(this.compareBy(kategoria)).forEach(p -> {
            System.out.print(p);
            if ("Koszty".equals(kategoria)) {
                System.out.print(" (koszt:" + p.getKoszt() + ")");
            }
            System.out.println();
        });

        System.out.println();
    }
}

