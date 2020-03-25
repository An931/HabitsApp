package com.example.habitsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class HabitsPageFragment :Fragment(){
//    val ARG_PAGE = "ARG_PAGE"
//    private var mPage = 0

    companion object{
        fun newInstance():HabitsPageFragment = HabitsPageFragment()
    }

//    public static PageFragment newInstance(int page) {
//        Bundle args = new Bundle();
//        args.putInt(ARG_PAGE, page);
//        PageFragment fragment = new PageFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        if (getArguments() != null) {
//            mPage = getArguments()?.getInt(ARG_PAGE) ?: 0;
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.habits_page_fragment, container, false)
//        TextView textView = (TextView) view
//        textView.setText("Fragment #" + mPage)
    }
}
