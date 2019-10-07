package com.flowbird.testutils;

import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldAccessManagement {

    public static void setFinalStatic(Object object, String fieldName, Object newValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = makeFieldVisible(object, fieldName);
        field.set(object, newValue);
    }

    @NonNull
    public static Field makeFieldVisible(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getField(fieldName);
        field.setAccessible(true);
        Field modifiers = field.getClass().getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        return field;
    }
}
