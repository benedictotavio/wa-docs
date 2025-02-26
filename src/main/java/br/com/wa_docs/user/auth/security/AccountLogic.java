package br.com.wa_docs.user.auth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.BadCredentialsException;

import jakarta.servlet.http.HttpServletRequest;

public class AccountLogic {
    public Map<String, String> getClientInfo(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String remoteHost = request.getRemoteHost();
        String remoteUser = request.getRemoteUser();
        String contentType = request.getHeader("content-type");
        String userAgent = request.getHeader("user-agent");

        try {
            Map<String, String> clientInfo = new HashMap<>();
            clientInfo.put("remoteAddr", remoteAddr);
            clientInfo.put("remoteHost", remoteHost);
            clientInfo.put("remoteUser", remoteUser);
            clientInfo.put("contentType", contentType);
            clientInfo.put("userAgent", userAgent);
            return clientInfo;
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }
}