package com.smartpublicinvestor.domain;

/**
 * Reprezentuje jedno ETF v katalógu.
 */
public class EtfAktivum {
    private final String isin;   // napr. "IE00BK5BQT80"
    private final String nazov;  // napr. "Vanguard FTSE All-World UCITS ETF"
    private final String mena;   // "EUR" / "USD" / "CZK" / "GBP"

    public EtfAktivum(String isin, String nazov, String mena) {
        if (isin == null || isin.isBlank()) {
            throw new IllegalArgumentException("ISIN nesmie byť prázdny");
        }
        this.isin = isin.trim().toUpperCase();
        this.nazov = nazov == null ? "" : nazov.trim();
        this.mena  = mena == null ? "" : mena.trim().toUpperCase();
    }

    public String getIsin()  { return isin; }
    public String getNazov() { return nazov; }
    public String getMena()  { return mena; }

    @Override
    public String toString() {
        return isin + " | " + nazov + " | " + mena;//vypis
    }

    // Identita podľa ISIN (case-insensitive)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EtfAktivum other)) return false;
        return isin.equalsIgnoreCase(other.isin);
    }

    @Override
    public int hashCode() {
        return isin.toUpperCase().hashCode();
    }
}
