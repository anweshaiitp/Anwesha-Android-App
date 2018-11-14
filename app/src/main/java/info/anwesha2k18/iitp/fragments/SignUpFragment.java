package info.anwesha2k18.iitp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import info.anwesha2k18.iitp.OnSignUpListener;
import info.anwesha2k18.iitp.R;
// import info.anwesha2k18.iitp.activities.RegisterActivity;
// import info.anwesha2k18.iitp.utils.Rotate;
// import info.anwesha2k18.iitp.utils.TextSizeTransition;
// import info.anwesha2k18.iitp.adapters.TextWatcherAdapter;
// import info.anwesha2k18.iitp.utils.VerticalTextView;

// public class SignUpFragment extends AuthFragment{

//     @BindViews(value = {R.id.email_input_edit,
//             R.id.password_input_edit,
//             R.id.confirm_password_edit})
//     protected List<TextInputEditText> views;
//     Bundle bundle;
//     @Override
//     public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//         EditText password1 = (EditText)view.findViewById(R.id.password_input_edit);
//         EditText password2 = (EditText)view.findViewById(R.id.confirm_password_edit);
//         EditText emailHolder = (EditText)view.findViewById(R.id.email_input_edit);
//         super.onViewCreated(view, savedInstanceState);
//         if(view!=null){
//             view.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_sign_up));
//             caption.setText(getString(R.string.sign_up_label));
// //             for(TextInputEditText editText:views){
//                 if(editText.getId()==R.id.password_input_edit){
//                     final TextInputLayout inputLayout= ButterKnife.findById(view,R.id.password_input);
//                     final TextInputLayout confirmLayout= ButterKnife.findById(view,R.id.confirm_password);
//                     Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
//                     inputLayout.setTypeface(boldTypeface);
//                     confirmLayout.setTypeface(boldTypeface);
//                     editText.addTextChangedListener(new TextWatcherAdapter(){
//                         @Override
//                         public void afterTextChanged(Editable editable) {
//                             inputLayout.setPasswordVisibilityToggleEnabled(editable.length()>0);
//                         }
//                     });
//                 }
//                 editText.setOnFocusChangeListener((temp,hasFocus)->{
//                     if(!hasFocus){
//                         boolean isEnabled=editText.getText().length()>0;
//                         editText.setSelected(isEnabled);
//                     }
//                 });
//             }
//             Toast errorPassToast = android.widget.Toast.makeText(getContext(), getResources().getString(R.string.error_password_match), Toast.LENGTH_LONG);
//             Toast  errorEmptyToast = android.widget.Toast.makeText(getContext(), getResources().getString(R.string.error_empty_field), Toast.LENGTH_LONG);
//             caption.setOnClickListener(v -> {
//                 String pass1 = password1.getText().toString();
//                 String pass2 = password2.getText().toString();
//                 String email = emailHolder.getText().toString();
//                 if(pass1.equals("") && pass2.equals("") && email.equals(""))
//                 {

//                 }
//                 else if(pass1.equals("") || pass2.equals("") || email.equals(""))
//                 {
//                     errorEmptyToast.show();
//                 }
//                 else if(pass1.equals(pass2))
//                 {
//                     android.content.Intent intent = new android.content.Intent(getContext(), RegisterActivity.class);
//                     bundle = new Bundle();
//                     bundle.putString("email",email);
//                     bundle.putString("password",pass1);
//                     intent.putExtras(bundle);
//                     startActivity(intent);
//                 }
//                 else if(!pass1.equals(pass2) && !pass2.equals(""))
//                 {
//                     errorPassToast.show();
//                 }
//             });
//             caption.setVerticalText(true);
//             foldStuff();
//             caption.setTranslationX(getTextPadding());
//         }
//     }


public class SignUpFragment extends Fragment implements OnSignUpListener {
    private static final String TAG = "SignUpFragment";
    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.sign_up_fragment, container, false);


        return inflate;
    }

    @Override
    public void signUp() {
        Toast.makeText(getContext(), "Sign up", Toast.LENGTH_SHORT).show();
    }
}
