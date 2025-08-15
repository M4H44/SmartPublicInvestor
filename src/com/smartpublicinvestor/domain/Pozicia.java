package com.smartpublicinvestor.domain;

public class Pozicia {
    private final EtfAktivum asset;
    private double quantity;   // držané kusy
    private double avgPrice;   // priemerná nákupná cena na kus (v mene ETF)

    public Pozicia(EtfAktivum asset) {
        if (asset == null) throw new IllegalArgumentException("asset nesmie byť null");
        this.asset = asset;
        this.quantity = 0.0;
        this.avgPrice = 0.0;
    }

    /** Nakúpi qty kusov za price/kus, s celkovým poplatkom fee. */
    public void buy(double qty, double price, double fee) {
        if (qty <= 0 || price <= 0) throw new IllegalArgumentException("qty a price musia byť > 0");
        if (fee < 0) throw new IllegalArgumentException("fee nesmie byť záporný");

        double oldValue = quantity * avgPrice;
        double newValue = qty * price + fee;  // fee započítame do nákladov
        quantity += qty;
        avgPrice = (oldValue + newValue) / quantity;
    }

    public EtfAktivum getAsset() { return asset; }
    public double getQuantity()  { return quantity; }
    public double getAvgPrice()  { return avgPrice; }

    @Override public String toString() {
        return asset.getIsin() + " | qty=" + quantity + ", avg=" +
                String.format("%.2f", avgPrice) + " " + asset.getMena();
    }
}
