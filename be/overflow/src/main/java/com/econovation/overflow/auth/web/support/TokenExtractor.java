package com.econovation.overflow.auth.web.support;

import javax.servlet.http.HttpServletRequest;

public interface TokenExtractor {
	String extract(HttpServletRequest request);
}
