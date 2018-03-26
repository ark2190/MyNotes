package com.mynotes.ui.widgets

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import com.mynotes.utils.gone
import com.mynotes.utils.visible

/**
 * Created by Anurag on 25-03-2018.
 */
class EmptyStateRecyclerView : RecyclerView {

    private var mEmptyView: View? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    fun setEmptyView(emptyView: View) {
        this.mEmptyView = emptyView
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(mDataObserver)
        mDataObserver.onChanged()
    }

    private val mDataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            updateUI()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            updateUI()
        }

        override fun onChanged() {
            updateUI()
        }
    }

    private fun updateUI() {
        if (adapter != null) {
            if (adapter.itemCount > 0) {
                mEmptyView?.gone()
                visible()
            } else {
                mEmptyView?.visible()
                gone()
            }
        }
    }

    fun hideEmptyView() {
        mEmptyView?.gone()
    }
}