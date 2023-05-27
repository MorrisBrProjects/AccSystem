package de.morrisbr.wolfscripthubserver;

import com.mongodb.client.MongoDatabase;
import de.morrisbr.wolfscripthubserver.modules.ModuleService;
import de.morrisbr.wolfscripthubserver.modules.system.accountsystem.account.Account;
import de.morrisbr.wolfscripthubserver.modules.system.accountsystem.storages.AccountStorage;
import de.morrisbr.wolfscripthubserver.config.WebConfig;
import de.morrisbr.wolfscripthubserver.database.MongoManager;
import de.morrisbr.wolfscripthubserver.modules.system.friendsystem.FriendSystemModule;
import de.morrisbr.wolfscripthubserver.session.SessionData;
import de.morrisbr.wolfscripthubserver.session.SessionService;
import de.morrisbr.wolfscripthubserver.webserver.RouteRole;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.rendering.template.JavalinJte;
import org.eclipse.jetty.server.session.DefaultSessionCache;
import org.eclipse.jetty.server.session.FileSessionDataStore;
import org.eclipse.jetty.server.session.SessionCache;
import org.eclipse.jetty.server.session.SessionHandler;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    private MongoManager mongoManager = new MongoManager(this);
    private MongoDatabase mongoDatabase = mongoManager.getDatabase();
    private SessionService sessionService = new SessionService();
    private AccountStorage accountStorage = new AccountStorage(mongoManager, this);
    private ModuleService moduleService = new ModuleService();

    public static void main(String[] args) {
        WebConfig.load();
        new Main().start();
    }

    public void start() {

        moduleService.register(new FriendSystemModule());

        Javalin app = Javalin.create().start(80);
        app._conf.addStaticFiles("resources", Location.EXTERNAL);
        app._conf.sessionHandler(() -> fileSessionHandler());
        app._conf.accessManager((handler, ctx, routeRoles) -> {
            SessionData sessionData = sessionService.getSessionData(ctx);
            if (sessionData == null) {
                sessionData = new SessionData();
                sessionData.setSessionToken(ctx.req.getSession().getId());
                sessionData.setRole(RouteRole.NOT_LOGIN);
                sessionService.getSessionDataHashMap().put(sessionData.getSessionToken(), sessionData);
            }
            if (sessionService.isSessionDataExist(ctx)) {
                if (routeRoles.isEmpty() || routeRoles.contains(sessionData.getRole())) {
                    handler.handle(ctx);
                } else {
                    switch (sessionData.getRole()) {
                        case IS_LOGIN:
                            ctx.status(200).result("Sucesss").redirect("/");
                            break;
                        case NOT_LOGIN:
                            ctx.status(401).result("Unauthorized").redirect("/login");
                            break;
                    }
                }
            }
        });

        DirectoryCodeResolver codeResolver = new DirectoryCodeResolver(Paths.get("resources"));
        TemplateEngine engine = TemplateEngine.create(codeResolver, ContentType.Html);
        engine.createPrecompiled(Path.of("jte-classes"), ContentType.Html);
        JavalinJte.configure(engine);


        app.get("/login", ctx -> {
            ctx.render("login.jte");
        }, RouteRole.NOT_LOGIN);

        app.post("/login", ctx -> {
            if (accountStorage.isLoginValid(ctx.formParam("username"), ctx.formParam("password"))) {
                Account account = accountStorage.getAccount(ctx.formParam("username"));
                ctx.status(200).result("Ok");
                SessionData sessionData = new SessionData();
                sessionData.setSessionToken(ctx.req.getSession().getId());
                sessionData.setRole(RouteRole.IS_LOGIN);
                sessionData.setAccount(account);
                sessionService.getSessionDataHashMap().put(sessionData.getSessionToken(), sessionData);
            } else {
                ctx.status(401).result("incorect");
            }
        }, RouteRole.NOT_LOGIN);


        app.get("/register", ctx -> {
            ctx.render("register.jte");
        }, RouteRole.NOT_LOGIN);

        app.post("/register", ctx -> {
            if (accountStorage.isAccountExist(ctx.formParam("username"))) {
                ctx.status(401).result("Username already exist!");
            } else {
                if (ctx.formParam("username").length() >= 5 && ctx.formParam("password").length() >= 5) {
                    accountStorage.registerAccount(ctx.formParam("username"), ctx.formParam("password"), ctx);
                    ctx.status(200).result("Ok");
                } else {
                    ctx.status(403).result("No right length!");
                }
            }
        }, RouteRole.NOT_LOGIN);

        app.get("/", ctx -> {
            ctx.result("gdfg");
        }, RouteRole.IS_LOGIN);

        app.get("/logout", ctx -> {
            sessionService.getSessionDataHashMap().remove(ctx.req.getSession().getId());
            fileSessionHandler().removeSession(ctx.req.getSession().getId(), true);
            ctx.removeCookie("JSESSIONID");
            ctx.redirect("/login");
        }, RouteRole.IS_LOGIN);

    }


    static SessionHandler fileSessionHandler() {
        SessionHandler sessionHandler = new SessionHandler();
        SessionCache sessionCache = new DefaultSessionCache(sessionHandler);
        sessionCache.setSessionDataStore(fileSessionDataStore());
        sessionHandler.setSessionCache(sessionCache);
        sessionHandler.setHttpOnly(true);
        // make additional changes to your SessionHandler here
        return sessionHandler;
    }

    static FileSessionDataStore fileSessionDataStore() {
        FileSessionDataStore fileSessionDataStore = new FileSessionDataStore();
        File baseDir = new File(System.getProperty("java.io.tmpdir"));
        File storeDir = new File(baseDir, "javalin-session-store");
        storeDir.mkdir();
        fileSessionDataStore.setStoreDir(storeDir);
        return fileSessionDataStore;
    }

    public MongoManager getMongoManager() {
        return mongoManager;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
}