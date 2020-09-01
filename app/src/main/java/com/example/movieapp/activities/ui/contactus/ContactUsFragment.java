package com.example.movieapp.activities.ui.contactus;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movieapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactUsFragment extends Fragment {
    @BindView(R.id.contactus_subjectText)
    EditText subject;
    @BindView(R.id.contactus_bodyText)
    EditText body;
    @BindView(R.id.contactus_submitButton)
    Button submit;

    private ContactUsViewModel mViewModel;

    public static ContactUsFragment newInstance() {
        return new ContactUsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_us_fragment, container, false);
        subject = view.findViewById(R.id.contactus_subjectText);
        body = view.findViewById(R.id.contactus_bodyText);
        submit = view.findViewById(R.id.contactus_submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validate()) {
                    onSubmitFailed();
                    return;
                }

                onSubmitSuccess();
                Intent gmailIntent = new Intent();
                gmailIntent.setAction(Intent.ACTION_SENDTO);
                gmailIntent.setType("message/rfc822");
                gmailIntent.setData(Uri.parse("mailto:"));
                String [] list_of_emails = new String [1];
                list_of_emails[0] = "movies@movies.com";
                gmailIntent.putExtra(Intent.EXTRA_EMAIL, list_of_emails);
                gmailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                gmailIntent.putExtra(Intent.EXTRA_TEXT, body.getText().toString());
                startActivity(gmailIntent);


            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ContactUsViewModel.class);
        // TODO: Use the ViewModel
    }

    public void onSubmitSuccess() {
        Toast.makeText(getActivity().getBaseContext(), "All good :)", Toast.LENGTH_LONG).show();
        submit.setEnabled(true);
    }

    public void onSubmitFailed() {
        Toast.makeText(getActivity().getBaseContext(), "Something went wrong, message was not sent", Toast.LENGTH_LONG).show();

        submit.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        if (subject.getText().toString().isEmpty()) {
            subject.setError("Email Subject is missing");
            valid = false;
        } else {
            subject.setError(null);
        }
        if (body.getText().toString().isEmpty()) {
            body.setError("Email Body is missing");
            valid = false;
        } else {
            body.setError(null);
        }
        return valid;
    }
}