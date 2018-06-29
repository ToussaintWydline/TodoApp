package e.widlyne.todoapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EditItem extends DialogFragment {

    View root;
    EditText edtModify;
    Button btnSave;
    public static int position;
    public static String text;
    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.edit, null);
        edtModify = root.findViewById(R.id.edtModify);
        btnSave = root.findViewById(R.id.btnSave);
        mainActivity = (MainActivity) getActivity();
        try{
            edtModify.setText(text);
        }catch (Exception e){
            e.printStackTrace();
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(edtModify.getText().toString())){
                    mainActivity.replaceText(position, edtModify.getText().toString());
                    dismiss();
                }else{
                    dismiss();
                }
            }
        });

        return root;
    }


    public void setPosition(int i){
        position = i;
    }

    public void setText(String t){
        text = t;
    }

    public static EditItem newInstance(int i, String s){
        EditItem e = new EditItem();
        e.setPosition(i);
        e.setText(s);
        return e;
    }
}
