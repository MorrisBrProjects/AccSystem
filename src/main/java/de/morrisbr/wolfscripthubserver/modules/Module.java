package de.morrisbr.wolfscripthubserver.modules;

public class Module {

    private String name;

    public Module(String name) {
        this.name = name;
    }

    public Module() {
        onInit();
    }
    public void onInit(){}
    public void onLoad(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
