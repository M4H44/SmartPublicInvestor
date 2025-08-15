package com.smartpublicinvestor.domain;

import java.util.*;

public class Portfolio {
    private final Map<String, Pozicia> pozicie = new LinkedHashMap<>();

    /** Nákup do portfólia. Vytvorí pozíciu, ak ešte neexistuje. */
    public Pozicia buy(EtfAktivum asset, double qty, double price, double fee) {
        String key = asset.getIsin();
        Pozicia p = pozicie.get(key);
        if (p == null) {
            p = new Pozicia(asset);
            pozicie.put(key, p);
        }
        p.buy(qty, price, fee);
        return p;
    }

    public Collection<Pozicia> getPozicie() {
        return Collections.unmodifiableCollection(pozicie.values());
    }

    public boolean isEmpty() { return pozicie.isEmpty(); }
}
