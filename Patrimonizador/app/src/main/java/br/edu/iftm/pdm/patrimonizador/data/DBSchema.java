package br.edu.iftm.pdm.patrimonizador.data;

public class DBSchema {

    public static final class ImagemT {
        public static final String TABELA = "imagem";
        public static final String ID = "i_id";
        public static final String PATH = "i_path";
        public static final String PID = "i_pid";

        public static final String getCreationQuery() {
            // TODO (1) você deverá implementar a query de criação da tabela imagem
            return "";
        }
    }

    public static final class PatrimonioT {

        public static final String TABELA = "patrimonio";
        public static final String VIEWSELECTION = "p_get_patrimonio";

        public static final String ID = "p_id";
        public static final String CATEGORIA = "p_categoria";
        public static final String MARCA = "p_marca";
        public static final String ESTADO = "p_estado";
        public static final String VALOR = "p_valor";
        public static final String DESCRICAO = "p_descricao";
        public static final String TIMESTAMP = "p_timestamp";

        public static final String getCreationQuery() {
            // TODO (2) você deverá implementar a query de criação da tabela patrimonio
            return "";
        }

        public static final String getViewCreationQuery() {
            //TODO (3) você deverá implementar a query de criação de uma view de seleção para patrimonio.
            // Nesta view você deverá criar a selecão da relação patrimonio-imagem.
            return "";
        }
    }
}
