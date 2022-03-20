package br.edu.iftm.pdm.patrimonizador.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.edu.iftm.pdm.patrimonizador.model.Patrimonio;

public class PatrimonioDAOSingleton {
    private static PatrimonioDAOSingleton INSTANCE;
    private DBHelper dbHelper;

    private PatrimonioDAOSingleton() {
        this.dbHelper = new DBHelper();
    }

    public static PatrimonioDAOSingleton getINSTANCE() {
        if(INSTANCE == null)
            INSTANCE = new PatrimonioDAOSingleton();
        return INSTANCE;
    }

    public long addPatrimonio(Patrimonio patrimonio) {

        //TODO (8) Neste método você deverá implementar a inserção de patrimônios
        // e imagens no banco.

        return 0;
    }

    public void deletePatrimonio(long id) {
        //TODO (9) Implementar um método capaz de fazer a deleção de um patrimônio no banco SQLite.
    }

    public void deletePatrimonios(ArrayList<Long> idList) {
        for(long id : idList) {
            this.deletePatrimonio(id);
        }
    }

    public Patrimonio getPatrimonio(long id) {
        //TODO (10) Você deverá implementar um método capaz de buscar por um patrimônio
        // de acordo com o seu id e retornar todos os seus dados.
        return null;
    }

    public ArrayList<Patrimonio> getAllPatrimonios() {

        //TODO(11) Neste método você irá implementar o resgate da informação de todos os
        // patrimônios do banco sqlite. Você deverá utilizar a VIEWSELECTION.

        return null;
    }

    public void updateEstado(long id, String estado) {
        //TODO(12) você deverá implementar um método que altere a informação de estado
        // do objeto por meio da informação de seu ID.
    }
}
