package de.morrisbr.wolfscripthubserver.database;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import de.morrisbr.wolfscripthubserver.Main;
import de.morrisbr.wolfscripthubserver.config.WebConfig;
import org.bson.Document;

import java.lang.reflect.Type;
import java.util.Map;

public class MongoManager {

	private Main main;
	private final DatabaseUtil databaseUtil;

	public MongoManager(Main main) {
		this.main = main;
		databaseUtil = new DatabaseUtil(main);
	}

	public static MongoManager getInstance(Main main) {
		return new MongoManager(main);
	}

	public MongoDatabase getDatabase() {
		MongoClient mongoClient = new MongoClient(new MongoClientURI(WebConfig.getMongodb()));
		//System.out.println("Connected to: " + mongoClient.getDatabase("WolfScriptHub").getName());
		return mongoClient.getDatabase("FerestZWeb");
	}

	@SuppressWarnings("unchecked")
	public void addObjectToCollection(Object objectOne, Type type, String collectionName, Map<String, Object> toJson) {
		String json = (new Gson()).toJson(objectOne, type);
		@SuppressWarnings("unused")
		Map<String, Object> document = (Map<String, Object>) (new Gson()).fromJson(json, Map.class);
		main.getMongoDatabase().getCollection(collectionName).insertOne(new Document(toJson));
	}

	public DatabaseUtil getDatabaseUtil() {
		return databaseUtil;
	}
}
