package com.headius.modulator;

public interface Module {
    boolean isOpen(String pn);
    boolean isOpen(String pn, Module other);
    boolean isExported(String pn);
    void addOpens(String pn, Module other);
}
