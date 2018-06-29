package e.widlyne.todoapp;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {


    ListView myList;
    Button btnSave;
    EditText edtAddItem;
    ArrayList<String> listItem;
    ArrayAdapter<String> todoAdapter;
    SharedPreferences savedList;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList =  findViewById(R.id.myList);
        btnSave = findViewById(R.id.btnSave);
        edtAddItem = findViewById(R.id.edtItemAdd);
        fm = getSupportFragmentManager();
        listItem = new ArrayList<>();
        todoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listItem);
        myList.setAdapter(todoAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Edit item
                EditItem e  = EditItem.newInstance(i, listItem.get(i));
                e.show(fm,"LOL");
            }
        });

        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                listItem.remove(i);
                todoAdapter.notifyDataSetChanged();
                return false;
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(edtAddItem.getText().toString())){
                    listItem.add(edtAddItem.getText().toString());
                    todoAdapter.notifyDataSetChanged();
                    edtAddItem.setText(" ");
                }
            }
        });

    }

    public void replaceText(int i, String t){
        listItem.add(i,t);
        todoAdapter.notifyDataSetChanged();
    }
}
