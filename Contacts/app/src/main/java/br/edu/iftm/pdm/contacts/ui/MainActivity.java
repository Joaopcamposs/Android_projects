package br.edu.iftm.pdm.contacts.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import br.edu.iftm.pdm.contacts.R;
import br.edu.iftm.pdm.contacts.data.ContactDAOSingleton;
import br.edu.iftm.pdm.contacts.ui.list_adapters.ContactAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView lvContact;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // adiciona-se um ListView no activity_main.xml
        this.lvContact = findViewById(R.id.lvContacts);

        // Criação do adapter associado a lista de dados dos Contacts;
        this.contactAdapter = new ContactAdapter(ContactDAOSingleton.getINSTANCE().getContacts());

        // Associação do adapter ao ListView
        this.lvContact.setAdapter(this.contactAdapter);
        this.lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent seeContactIntent = new Intent(getBaseContext(), ShowContactActivity.class);
                seeContactIntent.putExtra(ShowContactActivity.CONTACT_KEY,
                            ContactDAOSingleton.getINSTANCE().getContacts().get(i)
                        );
                startActivity(seeContactIntent);
            }
        });
    }
}
