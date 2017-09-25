package com.techexample.example01.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

/**
 * Currency class
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency implements Comparable<Currency> {

    private String id;

    private String name;

    private String symbol;

    private Integer rank;

    @Setter(onMethod = @__({@JsonProperty("price_usd")}))
    private Double priceUsd;

    @Setter(onMethod = @__({@JsonProperty("price_btc")}))
    private Double priceBtc;

    @Setter(onMethod = @__({@JsonProperty("24h_volume_usd")}))
    private Double twentyFourHourVolumeUsd;

    @Setter(onMethod = @__({@JsonProperty("market_cap_usd")}))
    private Double marketCapUsd;

    @Setter(onMethod = @__({@JsonProperty("available_supply")}))
    private Double availableSupplyUsd;

    @Setter(onMethod = @__({@JsonProperty("total_supply")}))
    private Double totalSupply;

    @Setter(onMethod = @__({@JsonProperty("percent_change_1h")}))
    private Double percentChangeOneHour;

    @Setter(onMethod = @__({@JsonProperty("percent_change_24h")}))
    private Double percentChangeTwentyFourHour;

    @Setter(onMethod = @__({@JsonProperty("percent_change_7d")}))
    private Double percentChangeSevenDays;

    @Setter(onMethod = @__({@JsonProperty("last_updated")}))
    private Long lastUpdated;

    @Builder
    public static Currency build(String id, String name, String symbol, int rank) {
        Currency currency = new Currency();
        currency.id = id;
        currency.name = name;
        currency.symbol = symbol;
        currency.rank = rank;
        return currency;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Currency{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", symbol='").append(symbol).append('\'');
        sb.append(", rank='").append(rank).append('\'');
        sb.append(", priceUsd=").append(priceUsd);
        sb.append(", priceBtc=").append(priceBtc);
        sb.append(", twentyFourHourVolumeUsd=").append(twentyFourHourVolumeUsd);
        sb.append(", marketCapUsd=").append(marketCapUsd);
        sb.append(", availableSupplyUsd=").append(availableSupplyUsd);
        sb.append(", totalSupply=").append(totalSupply);
        sb.append(", percentChangeOneHour=").append(percentChangeOneHour);
        sb.append(", percentChangeTwentyFourHour=").append(percentChangeTwentyFourHour);
        sb.append(", percentChangeSevenDays=").append(percentChangeSevenDays);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Currency other) {
        if (other == null) {
            return 1;
        }
        return name.compareTo(other.name);
    }
}
