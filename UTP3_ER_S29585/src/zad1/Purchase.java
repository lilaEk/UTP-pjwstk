/**
 * @author Ekiert Rozalia S29585
 */

package zad1;


public class Purchase {

    String[] tab;
    int id;
    String nazwisko;
    String produkt;
    Double cena;
    Double ilosc;

    public int getId() {
        return id;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Double getKoszt(){
        return cena*ilosc;
    }

    public Purchase(String purchase) {
        tab = purchase.split(";");

        this.id = Integer.parseInt(tab[0].substring(1));
        this.nazwisko = tab[1];
        this.produkt = tab[2];
        this.cena = Double.parseDouble(tab[3]);
        this.ilosc = Double.parseDouble(tab[4]);
    }

    public String toString() {
        return (String.format("c%05d", id) + ";" + nazwisko + ";" + produkt + ";" + cena + ";" + ilosc);
    }
}
