package hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdFragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdAdapter.EdColorPickerAdapter;


public class EdTextEditorDialogFragment extends DialogFragment {
    public static final String GEXTRA_COLOR_CODE = "extra_color_code";
    public static final String GEXTRA_INPUT_TEXT = "extra_input_text";
    public static final String TAG = "EMTextEditorDialogFragment";
    TextView gAddTextDoneTextView;
    EditText gAddTextEditText;
    int gColorCode;
    InputMethodManager gInputMethodManager;
    TextEditor gTextEditor;


    public interface TextEditor {
        void onDone(String str, int i);
    }

    public static EdTextEditorDialogFragment show(AppCompatActivity appCompatActivity, String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putString(GEXTRA_INPUT_TEXT, str);
        bundle.putInt(GEXTRA_COLOR_CODE, i);
        EdTextEditorDialogFragment eMTextEditorDialogFragment = new EdTextEditorDialogFragment();
        eMTextEditorDialogFragment.setArguments(bundle);
        eMTextEditorDialogFragment.show(appCompatActivity.getSupportFragmentManager(), TAG);
        return eMTextEditorDialogFragment;
    }

    public static EdTextEditorDialogFragment show(AppCompatActivity appCompatActivity) {
        return show(appCompatActivity, "", ContextCompat.getColor(appCompatActivity, R.color.txt_color));
    }

    @Override 
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(-1, -1);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.dg_text_editor, viewGroup, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.gAddTextEditText = (EditText) view.findViewById(R.id.add_text_edit_text);
        this.gInputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method");
        this.gAddTextDoneTextView = (TextView) view.findViewById(R.id.add_text_done_tv);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.add_text_color_picker_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        recyclerView.setHasFixedSize(true);
        EdColorPickerAdapter eMColorPickerAdapter = new EdColorPickerAdapter(getActivity());
        eMColorPickerAdapter.setOnColorPickerClickListener(new EdColorPickerAdapter.OnColorPickerClickListener() {
            @Override
            public void onColorPickerClickListener(int i) {
                EdTextEditorDialogFragment.this.gColorCode = i;
                EdTextEditorDialogFragment.this.gAddTextEditText.setTextColor(i);
            }
        });
        recyclerView.setAdapter(eMColorPickerAdapter);
        this.gAddTextEditText.setText(getArguments().getString(GEXTRA_INPUT_TEXT));
        int i = getArguments().getInt(GEXTRA_COLOR_CODE);
        this.gColorCode = i;
        this.gAddTextEditText.setTextColor(i);
        this.gAddTextDoneTextView.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view2) {
                EdTextEditorDialogFragment.this.gInputMethodManager.hideSoftInputFromWindow(view2.getWindowToken(), 0);
                EdTextEditorDialogFragment.this.dismiss();
                String obj = EdTextEditorDialogFragment.this.gAddTextEditText.getText().toString();
                if (TextUtils.isEmpty(obj) || EdTextEditorDialogFragment.this.gTextEditor == null) {
                    return;
                }
                EdTextEditorDialogFragment.this.gTextEditor.onDone(obj, EdTextEditorDialogFragment.this.gColorCode);
            }
        });
    }

    public void setOnTextEditorListener(TextEditor textEditor) {
        this.gTextEditor = textEditor;
    }
}
