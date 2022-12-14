package org.setu.recipeApp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.setu.recipeApp.R
import org.setu.recipeApp.activities.*
import org.setu.recipeApp.databinding.FragmentLoginBinding
import timber.log.Timber

//https://firebase.google.com/docs/auth/android/start
class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private const val TAG = "CustomAuthActivity"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        Timber.plant(Timber.DebugTree())

        Timber.i("Login Activity started...")

        binding.btnLogin.setOnClickListener() { it ->
            if (binding.emailAddress.text.toString().isEmpty()) {
                Snackbar
                    .make(it,"Enter an Email Address", Snackbar.LENGTH_LONG)
                    .show()
                binding.emailAddress.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailAddress.text.toString()).matches()) {
                Snackbar
                    .make(it,"Enter a Valid Email Address", Snackbar.LENGTH_LONG)
                    .show()
                binding.emailAddress.requestFocus()
                return@setOnClickListener
            }

            if (binding.password.text.toString().isEmpty()) {
                Snackbar
                    .make(it,"Enter a Password", Snackbar.LENGTH_LONG)
                    .show()
                binding.password.requestFocus()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(binding.emailAddress.text.toString(), binding.password.text.toString())
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithCustomToken:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                        Toast.makeText(
                            requireActivity(), "Sign in failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            Toast.makeText(activity, "Sign In Successful", Toast.LENGTH_LONG).show()
            startActivity(Intent(activity, MenuActivity::class.java))
        } else {
            Toast.makeText(requireActivity(), "Sign In Unsuccessful", Toast.LENGTH_LONG).show()
        }
    }

    //https://stackoverflow.com/questions/44583834/firebase-how-to-check-if-user-is-logged-in
    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser != null){
            reload();
        }
    }

    private fun reload() {
        Toast.makeText(activity, "Sign In Successful", Toast.LENGTH_LONG).show()
        startActivity(Intent(activity, MenuActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}