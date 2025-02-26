package br.com.wa_docs.user.auth.security;

public class GetIpLocation {
    private String ipAddress;

    public GetIpLocation(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    // // Get location by ip address = https://api.iplocation.net/?ip=177.152.82.18
    public Object getLocation() {
        return null;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
