package com.smartpublicinvestor;

import com.smartpublicinvestor.domain.EtfAktivum;
import com.smartpublicinvestor.domain.Portfolio;
import com.smartpublicinvestor.domain.Pozicia;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {

    private static final Scanner sc = new Scanner(System.in);
    private static final List<EtfAktivum> etfKatalog = new ArrayList<>();
    private static final Portfolio portfolio = new Portfolio();

    public static void main(String[] args) {
        System.out.println("=== Smart Public Investor v0.2 ===");
        while (true) {
            vypisMenu();
            String volba = sc.nextLine().trim();
            switch (volba) {
                case "1" -> zobrazOProjekte();
                case "2" -> pridajEtf();
                case "3" -> zobrazEtf();
                case "4" -> kupitDoPortfolia();
                case "5" -> zobrazPortfolio();
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
        System.out.println("4) Kúpiť do portfólia");
        System.out.println("5) Zobraziť portfólio");
        System.out.println("0) Koniec");
        System.out.print("Zvoľ akciu: ");
    }

    private static void zobrazOProjekte() {
        System.out.println("""
                Cieľ: plánovanie pravidelného investovania (DCA) do ETF,
                evidencia poplatkov a simulácia vývoja. Neskôr odporúčanie brokera.
                """);
    }

    // ===== KATALÓG ETF =====

    private static void pridajEtf() {
        System.out.print("Zadaj ISIN: ");
        String isin = sc.nextLine().trim().toUpperCase(Locale.ROOT);
        if (isin.isEmpty()) { System.out.println("ISIN nesmie byť prázdny."); return; }

        if (najdiEtf(isin) != null) {
            System.out.println("ETF s týmto ISIN už existuje.");
            return;
        }

        System.out.print("Zadaj názov ETF: ");
        String nazov = sc.nextLine().trim();
        if (nazov.isEmpty()) { System.out.println("Názov nesmie byť prázdny."); return; }

        System.out.print("Zadaj menu (EUR/USD/CZK/GBP): ");
        String mena = sc.nextLine().trim().toUpperCase(Locale.ROOT);
        if (!(mena.equals("EUR") || mena.equals("USD") || mena.equals("CZK") || mena.equals("GBP"))) {
            System.out.println("Nepodporovaná mena.");
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
        System.out.println("--- ETF v katalógu ---");
        for (int i = 0; i < etfKatalog.size(); i++) {
            System.out.println((i + 1) + ") " + etfKatalog.get(i));
        }
    }

    private static EtfAktivum najdiEtf(String isin) {
        for (EtfAktivum e : etfKatalog) {
            if (e.getIsin().equalsIgnoreCase(isin)) return e;
        }
        return null;
    }

    // ===== PORTFÓLIO =====

    private static void kupitDoPortfolia() {
        if (etfKatalog.isEmpty()) {
            System.out.println("Najprv si pridaj aspoň jedno ETF do katalógu (voľba 2).");
            return;
        }
        System.out.print("Zadaj ISIN ETF: ");
        String isin = sc.nextLine().trim().toUpperCase(Locale.ROOT);
        EtfAktivum asset = najdiEtf(isin);
        if (asset == null) {
            System.out.println("ETF s týmto ISIN nie je v katalógu. Najprv ho pridaj.");
            return;
        }

        double qty  = nacitajDouble("Množstvo (kusy): ");
        double price = nacitajDouble("Cena za kus: ");
        double fee   = nacitajDouble("Poplatok (celkom): ");

        try {
            Pozicia p = portfolio.buy(asset, qty, price, fee);
            System.out.println("OK. Aktuálna pozícia: " + p);
        } catch (IllegalArgumentException ex) {
            System.out.println("Chyba: " + ex.getMessage());
        }
    }

    private static void zobrazPortfolio() {
        if (portfolio.isEmpty()) {
            System.out.println("Portfólio je prázdne.");
            return;
        }
        System.out.println("--- Portfólio ---");
        for (Pozicia p : portfolio.getPozicie()) {
            System.out.println(p);
        }
    }

    // Pomocná metóda na číselný vstup (podporí aj čiarku)
    private static double nacitajDouble(String prompt) {
        System.out.print(prompt);
        String raw = sc.nextLine().trim().replace(',', '.');
        return Double.parseDouble(raw);
    }
}
