package com.example.pizzariadonmortandello;


import android.os.Parcel;
import android.os.Parcelable;

public class Pizza  implements Parcelable {
    private String nome;
    private double preco;
    private String ingredientes;

    public Pizza(String nome, double preco, String ingredientes) {
        this.nome = nome;
        this.preco = preco;
        this.ingredientes = ingredientes;
    }

    public Pizza() {
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public String toString() {
        String text =  nome +"- R$"+ preco+"\n"+"------------- \n" + ingredientes + "\n\n==================\n";

        return text;
    }

    protected Pizza(Parcel in) {
        nome = in.readString();
        preco = in.readDouble();
        ingredientes = in.readString();
    }

    public static final Creator<Pizza> CREATOR = new Creator<Pizza>() {
        @Override
        public Pizza createFromParcel(Parcel in) {
            return new Pizza(in);
        }

        @Override
        public Pizza[] newArray(int size) {
            return new Pizza[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeDouble(preco);
        dest.writeString(ingredientes);
    }
}
