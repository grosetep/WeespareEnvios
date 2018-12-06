package com.estrategiamovilmx.sales.weespareenvios.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.estrategiamovilmx.sales.weespareenvios.items.UserItem;
import com.estrategiamovilmx.sales.weespareenvios.notifications.MyFirebaseMessagingService;
import com.estrategiamovilmx.sales.weespareenvios.tools.Constants;

public class ReplyActivity extends AppCompatActivity {
    public static final String flow_notification = "notification";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static Intent getReplyMessageIntent(Context context, int notifyId, int messageId, UserItem user) {
        Log.d(flow_notification,"getReplyMessageIntent..."+user.toString());
        Intent intent = new Intent(context, OrdersDeliverActivity.class);

        intent.setAction(MyFirebaseMessagingService.REPLY_ACTION);
        Log.d(flow_notification,"getReplyMessageIntent...ok");
        return intent;
    }
}
