package de.morrisbr.wolfscripthubserver.modules.system.accountsystem.storages;

import com.google.gson.Gson;
import com.mongodb.CursorType;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.model.Filters;
import de.morrisbr.wolfscripthubserver.Main;
import de.morrisbr.wolfscripthubserver.modules.system.accountsystem.account.Account;
import de.morrisbr.wolfscripthubserver.database.MongoManager;
import io.javalin.http.Context;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mindrot.jbcrypt.BCrypt;

public class AccountStorage {

    private static final @NotNull Gson GSON = new Gson();

    private MongoManager mongodbManager;
    private Main main;

    public AccountStorage(MongoManager mongodbManager, Main main) {
        this.mongodbManager = mongodbManager;
        this.main = main;
    }

    public void registerAccount(String username, String password, Context ctx) {

        Account acc = new Account();
        acc.setUsername(username);
        acc.setPassword(password);
        acc.setId(main.getMongoDatabase().getCollection("Accounts").countDocuments());

        final String json = GSON.toJson(acc);
        final Document document = Document.parse(json);
        this.main.getMongoDatabase().getCollection("accounts").insertOne(document);
    }

    //{
    //	"_id": "MorrisBr",
    //		"name": "MorrisBr",
    //		"password": "MorrisBr"
    //}
/*
    public Document getAccountDocument(Long id) {
        if (isAccountExist(id)) {
            Bson filter = Filters.eq("_id", id);
            Document document = (Document) main.getMongoDatabase().getCollection("Accounts").find(filter).first();
            return document == null ? null : document;
        }
        return null;
    }*/


    public @Nullable Account getAccount(Long id) {
        FindIterable<Document> cursor = this.main.getMongoDatabase().getCollection("accounts")
                .find(Filters.eq("_id", id))
                .cursorType(CursorType.NonTailable);

        Document document = cursor.first();
        return document != null ? GSON.fromJson(document.toJson(), Account.class) : null;
    }

    public boolean isAccountExist(Long id) {
        return this.main.getMongoDatabase().getCollection("accounts").countDocuments(Filters.eq("_id", id), new CountOptions().limit(1)) > 0L;
    }

    public boolean isLoginValid(String username, String password) {


        if (isAccountExist(id)) {
            String pass = getAccount(username).getPassword();

            if (BCrypt.checkpw(password, pass)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
