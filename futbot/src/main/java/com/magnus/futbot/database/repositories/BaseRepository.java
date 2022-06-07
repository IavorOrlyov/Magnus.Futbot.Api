package com.magnus.futbot.database.repositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public abstract class BaseRepository<T> {

        protected final MongoDatabase database;

        public BaseRepository() {
                ConnectionString connectionString = new ConnectionString(
                                "mongodb+srv://FidoDidoo100:<password>@fbcluster.rdkdn.mongodb.net/?retryWrites=true&w=majority");
                MongoClientSettings settings = MongoClientSettings.builder()
                                .applyConnectionString(connectionString)
                                .serverApi(ServerApi.builder()
                                                .version(ServerApiVersion.V1)
                                                .build())
                                .build();
                MongoClient mongoClient = MongoClients.create(settings);
                database = mongoClient.getDatabase("futbot-java");
        }

        protected abstract MongoCollection<T> getCollection();
}
