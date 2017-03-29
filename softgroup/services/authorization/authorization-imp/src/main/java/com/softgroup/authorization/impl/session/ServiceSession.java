package com.softgroup.authorization.impl.session;

import com.google.common.cache.Cache;
import org.springframework.stereotype.Component;
import com.google.common.cache.CacheBuilder;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by anton on 29.03.17.
 */

@Component
public class ServiceSession {

    private Map<String, SessionData> sessionMap;

    private static final Integer LIVE_SESSION = 1000;

    public ServiceSession(){
        Cache<String, SessionData> sessionDataCache = CacheBuilder.newBuilder().expireAfterWrite(LIVE_SESSION, TimeUnit.SECONDS).build();

        sessionMap = sessionDataCache.asMap();
    }

    public SessionData addSession(String phone, String deviceID, String localeCode){
        SessionData sessionData = new SessionData(phone,deviceID,localeCode);

        sessionMap.put(sessionData.getUuid(),sessionData);
        return sessionData;
    }

    public void endSession(String uuid){
        sessionMap.remove(uuid);
    }

}
