package My.SymbolTable;

/**
 * Created by yellow-umbrella on 02/05/17.
 */
public class Main {

    public static void main(String args[]) {

        LinkedSymbolTable<Integer, String> st = new LinkedSymbolTable<>();

        st.put(1, "Renan");
        st.put(2, "Victor");
        st.put(3, "Luciane");
        st.put(4, "Eraldo");
        st.put(5, "Vitor");

        for(String name: st)
            System.out.println(name);

    }

}
