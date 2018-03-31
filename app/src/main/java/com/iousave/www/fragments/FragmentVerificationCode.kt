package com.iousave.www.fragments

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import com.iousave.www.R

class FragmentVerificationCode : Fragment() {
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_verification_code, null, false)
        context?.let { AlertDialog.Builder(it).setView(view).create().show() }
    }
}