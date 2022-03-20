package br.edu.iftm.pdm.contacts.ui.list_adapters;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.iftm.pdm.contacts.R;
import br.edu.iftm.pdm.contacts.model.Contact;

/*
 * um wrapper de adapter para ser usado com ListView
 * BaseAdapter provê alguns métodos essenciais para
 * a construção da nossa lista de forma prática.
 */
public class ContactAdapter extends BaseAdapter {

    private ArrayList<Contact> contacts;

    // tecnica para aumentar a performace do uso do ListView
    // inclui o uso uma classe que irá manter a referência para
    // os objetos internos da view. Chamamos esta estrutura de
    // ViewHolder. Veja o uso no metodo @getView.
    private static class ViewHolder {
        TextView txtNameInfo;
        TextView txtPhoneInfo;
    }

    public ContactAdapter(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return this.contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return this.contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.view_contact, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.txtNameInfo = view.findViewById(R.id.txtNameInfo);
            viewHolder.txtPhoneInfo = view.findViewById(R.id.txtPhoneInfo);
            // viewHolder eh instanciado como uma estrutura extra e atribuido como Tag
            // da view.
            view.setTag(viewHolder);
        }
        else {
            // caso a view ja exista a sua ViewHolder e resgatada
            viewHolder = (ViewHolder) view.getTag();
        }

        Contact contact = this.contacts.get(i);

        // aqui acontece o processo de "binding" ou reciclagem das views.
        viewHolder.txtNameInfo.setText(contact.getName());
        viewHolder.txtPhoneInfo.setText(contact.getPhoneNumber());

        return view;
    }
}
