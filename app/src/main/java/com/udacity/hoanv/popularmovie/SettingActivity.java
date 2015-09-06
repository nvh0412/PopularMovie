package com.udacity.hoanv.popularmovie;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created by HoaNV on 9/6/15.
 */
public class SettingActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Influence view into preference object.
        addPreferencesFromResource(R.xml.pref_general);
        //Binding preference
        bindPreference(findPreference(getString(R.string.pref_sort_by_key)));
    }

    public void bindPreference(Preference preference){

        preference.setOnPreferenceChangeListener(this);

        onPreferenceChange(preference, PreferenceManager
                .getDefaultSharedPreferences(preference.getContext())
                .getString(preference.getKey(), getString(R.string.pref_sort_by_popular)));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        String value = o.toString();

        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int index = listPreference.findIndexOfValue(value);
            if (index >= 0) {
                preference.setSummary(listPreference.getEntries()[index]);
            }
        } else {
            preference.setSummary(value);
        }

        return true;
    }
}
