package com.headius.modulator.impl;

import com.headius.modulator.Module;

/**
 * Created by headius on 10/18/17.
 */
public class ModuleDummy implements Module {
    public boolean isOpen(String pn) {
        return true;
    }

    public boolean isExported(String pn) {
        return true;
    }

    public void addOpens(String pn, Module other) {
        return;
    }
}
