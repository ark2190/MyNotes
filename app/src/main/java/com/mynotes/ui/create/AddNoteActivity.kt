package com.mynotes.ui.create

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import com.mynotes.R
import com.mynotes.core.arch.BaseFragment
import com.mynotes.dataclasses.Note

class AddNoteActivity : AppCompatActivity(), AddNoteActivityCallbacks {

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
        inflateFragment(AddNoteFragment(), false)
    }

    private fun setupToolbar() {
        mToolbar.setTitle(R.string.label_add_new_note)

        setSupportActionBar(mToolbar)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun inflateFragment(fragment: BaseFragment<*, *>, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction.addToBackStack(fragment.javaClass.simpleName)
        }
        transaction.replace(R.id.flContent, fragment)
        transaction.commit()
    }

    override fun onNoteAdded(note: Note) {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true;
        }
        return false
    }
}
