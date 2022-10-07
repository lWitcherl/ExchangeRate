package com.sikoraton.exchangerate.specisications;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class SearchCriteria {
    private String key;
    private String value;
    private List<String> list;

    public SearchCriteria(String key) {
        this.key = key;
    }
}
