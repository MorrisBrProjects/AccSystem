package de.morrisbr.wolfscripthubserver.modules;

import java.util.ArrayList;
import java.util.HashMap;

public class ModuleService {

    private ArrayList<Module> modules;

    public ModuleService() {
        this.modules = new ArrayList<>();
    }

    public void register(Module module) {
        module.onLoad();
    }


    public Module get(String name) {
        for (Module module : modules) {
            if(module.getName().equals(name)) {
                return module;
            }
        }
        return null;
    }

}
