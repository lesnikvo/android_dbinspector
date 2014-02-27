package im.dino.dbview.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.List;

import im.dino.dbview.R;
import im.dino.dbview.adapters.TablePageAdapter;

/**
 * Created by dino on 24/02/14.
 */
public class TableFragment extends Fragment {

    private static final String KEY_DATABASE = "database_name";

    private static final String KEY_TABLE = "table_name";

    private String mDatabaseName;

    private String mTableName;

    private TableLayout mTableLayout;

    public static TableFragment newInstance(String databaseName, String tableName) {

        Bundle args = new Bundle();
        args.putString(KEY_DATABASE, databaseName);
        args.putString(KEY_TABLE, tableName);

        TableFragment tf = new TableFragment();
        tf.setArguments(args);

        return tf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mDatabaseName = getArguments().getString(KEY_DATABASE);
            mTableName = getArguments().getString(KEY_TABLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_table, null);
        mTableLayout = (TableLayout) view.findViewById(R.id.table_layout);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().getActionBar().setTitle(mTableName);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

        // TODO get table content and display it
        TablePageAdapter adapter = new TablePageAdapter(getActivity(), mDatabaseName, mTableName);

        List<TableRow> rows = adapter.getStructure();

        for (TableRow row : rows) {
            mTableLayout.addView(row);
        }
    }
}
