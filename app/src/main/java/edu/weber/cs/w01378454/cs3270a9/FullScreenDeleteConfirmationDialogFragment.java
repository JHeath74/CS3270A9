package edu.weber.cs.w01378454.cs3270a9;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import edu.weber.cs.w01378454.cs3270a9.DB.AppDatabase;
import edu.weber.cs.w01378454.cs3270a9.DB.Course;


public class FullScreenDeleteConfirmationDialogFragment extends DialogFragment
{
    private View root;

    private TextInputLayout Id;
    private TextInputLayout Name;
    private TextInputLayout Course_name;
    private TextInputLayout Course_code;
    private TextInputLayout StartAt;
    private TextInputLayout EndAt;


    public FullScreenDeleteConfirmationDialogFragment()
    {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        {
            setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_Dialog_FullScreen);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_full_screen_delete_confirmation_dialog, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onResume() {super.onResume();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setWindowAnimations(R.style.AppTheme_DialogAnimation);

       /* androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.are_you_sure);
        builder.setMessage(R.string.delete_warning);

                builder.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();*/

        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireDialog().getWindow().setWindowAnimations(R.style.AppTheme_DialogAnimation);

        Toolbar toolbar = view.findViewById(R.id.toolBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });



    }
}