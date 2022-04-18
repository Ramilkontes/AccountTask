package com.local.accounttask.dto;

import com.local.accounttask.models.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String value;

    public static TokenDto from(Token token) {
        return new TokenDto(token.getValue());
    }
}
