package database;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by fvillela on 5/23/17.
 */

public class RealmDatabaseManager {
    private RealmConfiguration realmConfiguration;
    private Realm realm;

    public RealmDatabaseManager() {
        this.realmConfiguration = new RealmConfiguration.Builder().build();
        this.realm = Realm.getInstance(this.realmConfiguration);
    }

    public void saveOrUpdate(final RealmObject realmObject, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {
        this.realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {

            }
        }, onSuccess, onError);
    }

    public void saveOrUpdateAll(final List<RealmObject> realmObjects, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {
        this.realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {

            }
        }, onSuccess, onError);
    }

    public void delete(final Class realmClass, final String fieldName, final String value, Realm.Transaction.OnSuccess onSuccess, final Realm.Transaction.OnError onError) {
        this.realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {

            }
        }, onSuccess, onError);
    }

    public void deleteAllFromClass(final Class realmClass, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {
        this.realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {

            }
        }, onSuccess, onError);
    }

    public RealmObject get(final Class realmClass, String fieldName, String value) {
        return null;
    }

    public RealmResults getAll(final Class realmClass) {
        return null;
    }
}
