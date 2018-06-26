package com.tictapps.realm_poc.helper;

import android.content.Context;
import android.widget.Toast;
import com.tictapps.realm_poc.model.User;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


public class RealmHelper {

    private Realm realm;
    private RealmResults<User> realmResult;
    private Context context;

    public RealmHelper(Context context) {
        realm = Realm.getDefaultInstance();
        this.context = context;
    }

    public void addUser(String name, String surname) {
        User user = new User();
        user.setId((int) (System.currentTimeMillis() / 1000));
        user.setName(name);
        user.setSurname(surname);

        realm.beginTransaction();
        realm.copyToRealm(user);
        realm.commitTransaction();

        showToast(name + " added.");
    }

    public List<User> findAllUsers() {
        realmResult = realm.where(User.class).findAll();
        realmResult.sort("id", Sort.DESCENDING);
        return realm.copyFromRealm(realmResult);
    }

    public void updateUser(int id, String name, String surname) {
        realm.beginTransaction();
        User user = realm.where(User.class).equalTo("id", id).findFirst();
        user.setName(name);
        user.setSurname(surname);
        realm.commitTransaction();

        showToast(name + " updated.");
    }

    public void deleteData(int id){
        RealmResults<User> dataResults = realm.where(User.class).equalTo("id",id).findAll();
        realm.beginTransaction();
        dataResults.deleteAllFromRealm();
        realm.commitTransaction();
    }

    private void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }
}