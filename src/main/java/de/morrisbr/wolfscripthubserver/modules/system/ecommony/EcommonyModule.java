package de.morrisbr.wolfscripthubserver.modules.system.ecommony;

import de.morrisbr.wolfscripthubserver.modules.Module;
import de.morrisbr.wolfscripthubserver.modules.system.ecommony.services.EcommonyService;

public class EcommonyModule extends Module {

    private EcommonyService ecommonyService;

    @Override
    public void onInit() {
        this.ecommonyService = new EcommonyService();
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    public EcommonyService getEcommonyService() {
        return ecommonyService;
    }
}
