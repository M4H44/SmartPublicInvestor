package com.smartpublicinvestor;

import com.smartpublicinvestor.domain.EtfAktivum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Smart Public Investor – konzolová appka (verzia 0.1).
 */
public class App {

    private static final Scanner sc = new Scanner(System.in);

    // Jednoduchý in-memory katalóg ETF (na MVP stačí)
    private static final List<EtfAktivum> etfKatalog = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Smart Public Investor v0.1 ===");
        while (true) {
            vypisMenu();
            String volba = sc.nextLine().trim();
            switch (volba) {
                case "1" -> zobrazOProjekte();
                case "2" -> pridajEtf();
                case "3" -> zobrazEtf();
                case "0" -> { System.out.println("Dovidenia."); return; }
                default -> System.out.println("Neplatná voľba, skús znova.");
            }
        }
    }

    private static void vypisMenu() {
        System.out.println();
        System.out.println("1) O projekte");
        System.out.println("2) Pridať ETF");
        System.out.println("3) Zobraziť všetky ETF");
        System.out.println("0) Koniec");
        System.out.print("Zvoľ akciu: ");
    }

    private static void zobrazOProjekte() {
        System.out.println("""
                Cieľ: plánovanie pravidelného investovania (DCA) do ETF,
                evidencia poplatkov a simulácia vývoja.
                Postupne pribudne odporúčanie brokera podľa profilu používateľa.
                """);
    }

    private static void pridajEtf() {
        System.out.print("Zadaj ISIN: ");
        String isin = sc.nextLine().trim().toUpperCase();
        if (isin.isEmpty()) {
            System.out.println("ISIN nesmie byť prázdny.");
            return;
        }
        // kontrola duplicity podľa ISIN
        boolean existuje = etfKatalog.stream().anyMatch(e -> e.getIsin().equalsIgnoreCase(isin));
        if (existuje) {
            System.out.println("ETF s týmto ISIN už existuje.");
            return;
        }

        System.out.print("Zadaj názov ETF: ");
        String nazov = sc.nextLine().trim();
        if (nazov.isEmpty()) {
            System.out.println("Názov nesmie byť prázdny.");
            return;
        }

        System.out.print("Zadaj menu (EUR/USD/CZK/GBP): ");
        String mena = sc.nextLine().trim().toUpperCase();
        if (!(mena.equals("EUR") || mena.equals("USD") || mena.equals("CZK") || mena.equals("GBP"))) {
            System.out.println("Nepodporovaná mena. Skús EUR, USD, CZK alebo GBP.");
            return;
        }

        etfKatalog.add(new EtfAktivum(isin, nazov, mena));
        System.out.println("ETF pridané.");
    }

    private static void zobrazEtf() {
        if (etfKatalog.isEmpty()) {
            System.out.println("Zoznam ETF je prázdny.");
            return;
        }
        System.out.println("--- ETF v zozname ---");
        for (int i = 0; i < etfKatalog.size(); i++) {
            System.out.println((i + 1) + ") " + etfKatalog.get(i));
        }
    }
}
