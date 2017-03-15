package com.softgroup.common.token.impl.test;

import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.springframework.stereotype.Component;

/**
 * Created by anton on 15.03.17.
 */
@Component
public class TokenHandlerTest {

    private AesKey key = new AesKey(ByteUtil.randomBytes(16));



}
