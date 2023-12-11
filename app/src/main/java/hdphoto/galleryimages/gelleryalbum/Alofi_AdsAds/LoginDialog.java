package hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import hdphoto.galleryimages.gelleryalbum.R;

public class LoginDialog extends DialogFragment {
    EditText hostUrlEditText;


    private LoginConfirmationInterface loginConfirmationInterface;
    public LoginDialog() {
    }

    public static LoginDialog newInstance() {
        LoginDialog frag = new LoginDialog();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ct_status_dialog_login_layout, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        SharedPreferences prefs = ((MainApp) getActivity().getApplication()).getPrefs();
        hostUrlEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
        if (ctx instanceof LoginConfirmationInterface) {
            loginConfirmationInterface = (LoginConfirmationInterface) ctx;
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        loginConfirmationInterface = null;
    }

    public interface LoginConfirmationInterface {
        void setLoginParams(String hostUrl, String carrierId);
        void loginUser();
    }
}
