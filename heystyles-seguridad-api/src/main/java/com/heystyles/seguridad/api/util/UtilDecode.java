package com.heystyles.seguridad.api.util;

import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;

public final class UtilDecode {

    private UtilDecode() {
    }

    public static String decode(String url) {
        try {
            return UriUtils.decode(url, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            //throw new BadRequestException();
        }
        return "";
    }

}
