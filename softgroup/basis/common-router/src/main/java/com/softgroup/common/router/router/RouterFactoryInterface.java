package com.softgroup.common.router.router;

import com.softgroup.common.protocol.Request;
import com.softgroup.common.router.api.Handler;

/**
 * Created by anton on 28.03.17.
 */
public interface RouterFactoryInterface<T extends Handler>  {

   String getKey(Request<?> msg);

   T getHandler(Request msg);
}
