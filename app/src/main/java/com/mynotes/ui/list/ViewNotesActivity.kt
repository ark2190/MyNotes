package com.mynotes.ui.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import com.mynotes.R
import com.mynotes.core.arch.BaseFragment
import com.mynotes.dataclasses.Note

class ViewNotesActivity : AppCompatActivity() {

    @BindView(R.id.toolbar)
    lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        init()
    }

    private fun init() {
        ButterKnife.bind(this)
        setupToolbar()

        supportFragmentManager.beginTransaction().replace(R.id.flContent, ViewNotesFragment()).commit()
    }

    private fun setupToolbar() {
        mToolbar.setTitle(R.string.app_name)

        setSupportActionBar(mToolbar)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
