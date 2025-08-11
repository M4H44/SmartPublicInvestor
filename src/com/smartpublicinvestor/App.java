package com.smartpublicinvestor;

import java.util.Scanner;

/**
 * Smart Public Investor – konzolová appka (verzia 0.1).
 * Zatiaľ iba jednoduché menu na ukážku štruktúry programu.
 */
public class App {

    // Jeden spoločný Scanner pre celú triedu App.
    // private  -> prístupný len v tejto triede
    // static   -> môžeme ho používať v statickej main() bez vytvárania objektu App
    // final    -> odkaz na Scanner sa už nebude meniť
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Vstupný bod programu – odtiaľto sa všetko spúšťa.
     */
    public static void main(String[] args) {
        // Úvodná hlavička programu (iba informatívny výpis)
        System.out.println("=== Smart Public Investor v0.1 ===");

        // Nekonečný cyklus zobrazujúci menu, kým používateľ nezvolí ukončenie (0)
        while (true) {
            // Vypíš možnosti do konzoly volame metodu
            vypisMenu();

            // Prečítaj celý riadok od používateľa a odstran prazdne znaky zo začiatku/konca
            String volba = sc.nextLine().trim();

            // Rozhodovanie podľa zadanej voľby (moderný switch s '->', bez fall-through-netreba break)
            switch (volba) {
                case "1" -> zobrazOProjekte();        // ak zadá "1", vypíš info o projekte
                case "0" -> {                         // ak zadá "0", ukonči program
                    System.out.println("Dovidenia.");
                    return;                           // return v main() = koniec programu
                }
                default -> System.out.println("Neplatná voľba, skús znova."); // iné vstupy
            }
            // po vykonaní case "1" alebo default sa cyklus opakuje a menu sa zobrazí znova
        }
    }

    /**
     * Metóda vypíše jednoduché menu možností pre používateľa.
     */
    private static void vypisMenu() {
        System.out.println();
        System.out.println("1) O projekte"); // ukáže základný popis toho, čo appka robí
        System.out.println("0) Koniec");     // ukončenie programu
        System.out.print("Zvoľ akciu: ");    // kurzor ostane na riadku – čaká na vstup
    }

    /**
     * Metóda vypíše popis cieľa projektu – zatiaľ informačne.
     */
    private static void zobrazOProjekte() {
        System.out.println("""
                Cieľ: plánovanie pravidelného investovania (DCA) do ETF,
                evidencia poplatkov a simulácia vývoja.
                Postupne pribudne odporúčanie brokera podľa profilu používateľa.
                """);
    }
}
