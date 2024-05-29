package com.works.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Currency {

    private String CurrencyName;
    private String ForexBuying;
    private String ForexSelling;

}
