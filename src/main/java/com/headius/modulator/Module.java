package com.headius.modulator;

public interface Module {
    boolean isOpen(String pn);
    boolean isExported(String pn);
}
