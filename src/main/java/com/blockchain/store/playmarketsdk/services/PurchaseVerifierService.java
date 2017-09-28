package com.blockchain.store.playmarketsdk.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.File;
import java.util.Date;

/**
 * Created by samsheff on 27/09/2017.
 */

public class PurchaseVerifierService extends IntentService {
    public PurchaseVerifierService() {
        super("PurchaseVerifierService");
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        if (shouldCheckIfPurchased()) {
            Log.d("PMSDK", "Will Verify Purchase");
        } else {
            Log.d("PMSDK", "Will Not Verify Purchase");

        }
    }

    public boolean shouldCheckIfPurchased() {
        long installTime = 0;
        try {
            installTime = getInstallTime();
            long time = System.currentTimeMillis();
            return time > installTime + 3600000;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }

    public long getInstallTime() throws PackageManager.NameNotFoundException {
        PackageManager pm = this.getPackageManager();

        PackageInfo packageInfo = pm.getPackageInfo(this.getPackageName(), PackageManager.GET_PERMISSIONS);
        return packageInfo.firstInstallTime;
    }
}
