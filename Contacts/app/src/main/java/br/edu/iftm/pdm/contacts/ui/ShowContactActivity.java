package br.edu.iftm.pdm.contacts.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import br.edu.iftm.pdm.contacts.R;
import br.edu.iftm.pdm.contacts.model.Contact;

public class ShowContactActivity extends AppCompatActivity {

    public static final String CONTACT_KEY = "ShowContactActivity.CONTACT";
    private TextView txtFirstNameOutput;
    private TextView txtLastNameOutput;
    private TextView txtEmailOutput;
    private TextView txtPhoneNumberOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        this.txtFirstNameOutput = findViewById(R.id.txtFirstNameOutput);
        this.txtLastNameOutput = findViewById(R.id.txtLastNameOutput);
        this.txtEmailOutput = findViewById(R.id.txtEmailOutput);
        this.txtPhoneNumberOutput = findViewById(R.id.txtPhoneNumberOutput);

        Contact contact = getIntent().getParcelableExtra(CONTACT_KEY);

        this.txtFirstNameOutput.setText(contact.getFirstName());
        this.txtLastNameOutput.setText(contact.getLastName());
        this.txtEmailOutput.setText(contact.getEmail());
        this.txtPhoneNumberOutput.setText(contact.getPhoneNumber());
    }
}
