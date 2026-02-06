package com.klid.webapp.common;

import com.klid.webapp.common.properties.ThirdPartyProperty;
import com.klid.webapp.common.service.ThirdPartyCryptoService;
import lombok.RequiredArgsConstructor;
import me.totoku103.crypto.core.utils.ByteUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final ThirdPartyProperty thirdPartyProperty;

    @Bean
    public ThirdPartyCryptoService thirdPartyCryptoService() {
        final byte[] seedCbcKey = ByteUtils.fromHexString(thirdPartyProperty.getSeedCbcKey());
        final byte[] hMacKey = ByteUtils.fromHexString(thirdPartyProperty.getHmacKey());

        return new ThirdPartyCryptoService(seedCbcKey, hMacKey);
    }
}
