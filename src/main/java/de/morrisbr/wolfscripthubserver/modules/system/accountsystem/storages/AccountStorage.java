package de.morrisbr.wolfscripthubserver.modules.system.accountsystem.storages;

import com.google.gson.Gson;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.model.Filters;
import de.morrisbr.wolfscripthubserver.Main;
import de.morrisbr.wolfscripthubserver.modules.system.accountsystem.account.Account;
import de.morrisbr.wolfscripthubserver.database.MongoManager;
import io.javalin.http.Context;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.mindrot.jbcrypt.BCrypt;

public class AccountStorage {

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

        Document account = new Document("_id", main.getMongoDatabase().getCollection("Accounts").countDocuments())
                    .append("account", new Gson().toJson(acc));
        main.getMongoDatabase().getCollection("Accounts").insertOne(account);
        System.out.println(main.getMongoDatabase().getCollection("Accounts").countDocuments());

    }

    public void registerAccount(Account acc) {
        acc.setId(main.getMongoDatabase().getCollection("Accounts").countDocuments());
        System.out.println(main.getMongoDatabase().getCollection("Accounts").countDocuments());
        Document account = new Document("_id", main.getMongoDatabase().getCollection("Accounts").countDocuments()).append("account", new Gson().toJson(acc));
        main.getMongoDatabase().getCollection("Accounts").insertOne(account);
    }

    //{
    //	"_id": "MorrisBr",
    //		"name": "MorrisBr",
    //		"password": "MorrisBr"
    //}

    public Document getAccountDocument(Long id) {
        if(isAccountExist(id)) {
            Bson filter = Filters.eq("_id", id);
            Document document = (Document) main.getMongoDatabase().getCollection("Accounts").find(filter).first();
            return document == null ? null : document;
        }
        return null;
    }




    public Account getAccount(Long id) {
        if(isAccountExist(id)) {
            Account account = new Gson().fromJson(getAccountDocument(id).get("account").toString(), Account.class);
            return account;
        }
        return null;
    }

    public boolean isAccountExist(Long id) {
        //return mongodbManager.getDatabaseUtil().documentExists("_id", username);

        Bson filter = Filters.eq("_id", id);
        Document document = (Document) main.getMongoDatabase().getCollection("Accounts").find(filter).first();
        return document != null;
    }

    public boolean isLoginValid(String username, String password) {



        if(isAccountExist(id)) {
            String pass = getAccount(username).getPassword();

            if(BCrypt.checkpw(password, pass)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
