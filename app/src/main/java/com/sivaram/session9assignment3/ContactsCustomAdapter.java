package com.sivaram.session9assignment3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 27/09/2017.
 */

public class ContactsCustomAdapter extends BaseAdapter {

    // Declare Context And List of Contacts Objects
    Context context;
    List<Contacts> contactsList;

    // Contacts Adapter Calss Constructor
    public ContactsCustomAdapter(Context context, List<Contacts> contactsList) {
        this.context = context;
        this.contactsList = contactsList;
    }

    // Override Methods of BaseAdapter
    @Override
    public int getCount() {
        return contactsList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        // Retrieve View By Activity ID, which allows to access View With in the Activity
        View contactsCustomView = view.inflate(context,R.layout.contact_custom_list,null);

        // Typecast Contact Name And Number TextView
        TextView txtContactName = (TextView) contactsCustomView.findViewById(R.id.txtContactName);
        TextView txtContactPhoneNumber = (TextView) contactsCustomView.findViewById(R.id.txtPhoneNumber);

        // Assign Contact Name And Number From Contacts Objects Array
        txtContactName.setText(contactsList.get(position).getContactName());
        txtContactPhoneNumber.setText(contactsList.get(position).getPhoneNumber());

        // Return View
        return contactsCustomView;
    }
}
