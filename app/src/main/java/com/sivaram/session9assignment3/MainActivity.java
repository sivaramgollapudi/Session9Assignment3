package com.sivaram.session9assignment3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Declare Contacts ListView and Contacts Object
    ListView contactsListView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declare Contacts ArrayList
        contactsList = new ArrayList<>();
        // TypeCast ListView Object
        contactsListView = (ListView) findViewById(R.id.contactsListView);

        // Assign Values to Contacts ArrayList
        contactsList.add(new Contacts("Android","+60161234567"));
        contactsList.add(new Contacts("IOS","+60111234567"));
        contactsList.add(new Contacts("Windows","+60121234567"));
        contactsList.add(new Contacts("Linux","+60171234567"));
        contactsList.add(new Contacts("Unix","+60181234567"));

        // Declare Adapter Class Object and Asssign Contacts ArrayList
        ContactsCustomAdapter contactsCustomAdapter = new ContactsCustomAdapter(getApplicationContext(),contactsList);

        // Set Adapter to List View
        contactsListView.setAdapter(contactsCustomAdapter);

        // Register Context Menu for List View
        registerForContextMenu(contactsListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // Set Header Text
        menu.setHeaderTitle(R.string.context_menu_title);

        // Icon
        menu.setHeaderIcon(R.mipmap.ic_launcher);

        // Add Menu Items to Context Menu
        menu.add("Call");
        menu.add("Send SMS");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // Retrieve Context Menu Info.
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        // Get Contacts Item Based on Selected Menu and Position of ListView
        Contacts contact = contactsList.get(info.position);

        // Create an Intent Object
        Intent i = new Intent();

        // Check Whether Clicked Menu Item is Call/SMS
        if (item.getTitle().equals("Call")) {
            // If Selected Menu Item is Call, Start Call Activity
            i.setAction(Intent.ACTION_DIAL);
            i.setData(Uri.parse("tel:" + contact.getPhoneNumber()));

            // Toast on Menu Item Click
            Toast.makeText(this, "Dialing ... " + contact.getPhoneNumber(), Toast.LENGTH_SHORT).show();
        }
        else if (item.getTitle().equals("Send SMS")) {

            // Set SMS Intent
            i.setAction(Intent.ACTION_VIEW);
            i.setData(Uri.parse("sms:" + contact.getPhoneNumber()));

            // Toast on Menu Item Click
            Toast.makeText(this, "Composing Text Message ... " + contact.getPhoneNumber(), Toast.LENGTH_SHORT).show();
        }

        // Start Activity
        startActivity(i);
        return true;

    }
}
