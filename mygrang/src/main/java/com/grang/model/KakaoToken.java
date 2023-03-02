package com.grang.model;

import lombok.Data;

@Data
public class KakaoToken {
    public String access_token;
    public String token_type;
    public String refresh_token;
    public int expires_in;
    public String scope;
    public int refresh_token_expires_in;
}
