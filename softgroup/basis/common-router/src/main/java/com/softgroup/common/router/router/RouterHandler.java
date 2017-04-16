package com.softgroup.common.router.router;

import com.softgroup.common.protocol.Request;
import com.softgroup.common.router.api.Handler;

public interface RouterHandler extends Handler {

	String getRouteKey(final Request<?> msg);
}
