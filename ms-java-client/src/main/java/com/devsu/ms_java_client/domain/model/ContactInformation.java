package com.devsu.ms_java_client.domain.model;

public record ContactInformation(String address, String phone) {

    public static ContactInformation empty() {
        return new ContactInformation(null, null);
    }
}
