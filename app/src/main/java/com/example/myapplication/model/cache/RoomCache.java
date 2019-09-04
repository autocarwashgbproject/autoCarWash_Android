package com.example.myapplication.model.cache;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class RoomCache {

    private Database database;

    public RoomCache(Database database) {
        this.database = database;
    }

    public Completable addWashes(List<Wash> washes) {
        return Completable.fromAction(() -> {
            database.getWash().insert(washes);
        });

    }

    public Single<List<Wash>> getWashes() {
        return Single.create(emitter -> {
            List<Wash> washes = database.getWash().getAll();
            if (washes.isEmpty()) {
                emitter.onError(new RuntimeException("List of washes is empty"));
            } else {
                emitter.onSuccess(washes);
            }
        }).subscribeOn(Schedulers.io())
                .cast((Class<List<Wash>>) (Class) List.class);

    }
}
