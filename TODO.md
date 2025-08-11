# TODO – SmartPublicInvestor

## MVP (jadro) – začiatok
- [ ] Trieda **EtfAktivum** (ISIN, názov, mena) + 	oString(), equals(), hashCode()
- [ ] Trieda **Pozicia** (asset, množstvo, priemerná nákupná cena) + uy(qty, price, fee)
- [ ] **Portfolio** (ISIN -> Pozicia) + uy(isin, qty, price, fee)
- [ ] **CLI**: pridať ETF, zobraziť ETF, kúpiť do portfólia, zobraziť portfólio
- [ ] **FeeModel** (fixed + % + min) + jednoduché nastavenie v CLI
- [ ] Uloženie/načítanie **JSON** (ETF, portfólio)
- [ ] **README**: čo appka robí + ukážkový beh

## DCA simulácia – ďalej
- [ ] **ContributionPlan** (mesačný vklad, alokácie %)
- [ ] **Simulator**: mesačný nákup, poplatky, fixná ročná miera (napr. 7 % p.a.)
- [ ] **Report**: investované, hodnota, zisk %, odhadovaný CAGR
- [ ] Export **CSV** reportu

## Odporúčanie brokera (offline)
- [ ] data/brokers.json – 3–5 brokerov (štruktúra + dočasné hodnoty)
- [ ] **UserProfile** (krajina, mena účtu, vklad, preferencie)
- [ ] **RuleBasedScoring** (váhy, vysvetlenie „prečo“ pri výsledku)

## Kvalita a infra
- [ ] **Javadoc** k hlavným triedam
- [ ] **Unit testy**: FeeModel, Simulator (základy)
- [ ] Upratať balíčky: cli/, domain/, engine/, io/
"@


- [x] Dnes: pridaný plán prác (TODO.md)
