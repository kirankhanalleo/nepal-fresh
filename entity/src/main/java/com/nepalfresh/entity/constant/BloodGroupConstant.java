package com.nepalfresh.entity.constant;

import java.util.Arrays;
import java.util.List;

public enum BloodGroupConstant {

    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-");

    private final String value;

    BloodGroupConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static BloodGroupConstant getBloodGroupConstantByValue(String name) {
        for (BloodGroupConstant bloodGroup : BloodGroupConstant.values()) {
            if (bloodGroup.name().equalsIgnoreCase(name)) {
                return bloodGroup;
            }
        }
        throw new IllegalArgumentException("Invalid Blood Group: " + name);
    }

    public static List<String> getAllBloodGroups() {
        return Arrays.stream(BloodGroupConstant.values())
                .map(BloodGroupConstant::getValue)
                .toList();
    }


}

