package com.headius.modulator;

import com.headius.modulator.impl.Module9;
import com.headius.modulator.impl.ModuleDummy;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;

/**
 * Created by headius on 10/18/17.
 */
public class Modulator {
    public static Module getModule(Class cls) {
        if (JAVA_NINE) {
            return new Module9(cls.getModule());
        }
        return new ModuleDummy();
    }

    public static void addOpens​(Class<?> ownerClass, String pn, Class<?> otherClass) {
        getModule(ownerClass).addOpens(pn, getModule(otherClass));
    }

    public static <T extends AccessibleObject & Member> boolean trySetAccessible(T accessibleMember) {
        return trySetAccessible(accessibleMember.getDeclaringClass(), accessibleMember, null);
    }

    public static <T extends AccessibleObject & Member> boolean trySetAccessible(T accessibleMember, Class<?> modClass) {
        return trySetAccessible(accessibleMember.getDeclaringClass(), accessibleMember, modClass);
    }

    private static boolean trySetAccessible(Class<?> declaringClass, AccessibleObject accessibleObject, Class<?> modClass) {
        if (accessibleObject.isAccessible()) return true;

        Module module = getModule(declaringClass);

        return modClass == null ?
                checkOpen(module, declaringClass, accessibleObject) :
                    checkOpen(module, declaringClass, accessibleObject, getModule(modClass));
    }

    private static boolean checkOpen(final Module module, Class<?> declaringClass, AccessibleObject accessibleObject) {
        try {
            if (module.isOpen(declaringClass.getPackage().getName())) {
                accessibleObject.setAccessible(true);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkOpen(final Module module, Class<?> declaringClass, AccessibleObject accessibleObject, final Module other) {
        try {
            if (module.isOpen(declaringClass.getPackage().getName(), other)) {
                accessibleObject.setAccessible(true);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static final boolean JAVA_NINE;

    static {
        boolean j9;
        try {
            Class.forName("java.lang.Module");
            j9 = true;
        } catch (Exception e) {
            j9 = false;
        }
        JAVA_NINE = j9;
    }
}
