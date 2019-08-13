package com.example.myapplication.preferences;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceViewHolder;

import com.example.myapplication.R;

public class AppLangPreference extends ListPreference {

    private TextView txt;

    public AppLangPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public AppLangPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AppLangPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWidgetLayoutResource(R.layout.language);
    }

    public AppLangPreference(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        txt = (TextView) holder.findViewById(R.id.language_summary_txt_id);
        txt.setText(getEntry());

    }

    private void setSummaryText(CharSequence s) {
        txt.setText(s);
        notifyChanged();
    }

    @Override
    public void setSummary(CharSequence summary) {
        setSummaryText(summary);
    }
}
