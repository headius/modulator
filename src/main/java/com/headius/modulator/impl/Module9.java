package com.headius.modulator.impl;

/**
 * Created by headius on 10/18/17.
 */
public class Module9 implements com.headius.modulator.Module {
    private final java.lang.Module module;

    public Module9(java.lang.Module module) {
        this.module = module;
    }

    public boolean isOpen(String pn) {
        return module.isOpen(pn);
    }

    public boolean isExported(String pn) {
        return module.isExported(pn);
    }
}
