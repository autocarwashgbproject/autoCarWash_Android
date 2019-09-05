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
        })
                .subscribeOn(Schedulers.io());

    }

    public Single<List<Wash>> getWashes() {
        return Single.create(emitter -> {
            List<Wash> washes = database.getWash().getAll();
                emitter.onSuccess(washes);
        }).subscribeOn(Schedulers.io())
                .cast((Class<List<Wash>>) (Class) List.class);

    }
}
