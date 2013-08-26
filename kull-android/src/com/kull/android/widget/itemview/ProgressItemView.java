/*
 * Copyright (C) 2010 Cyril Mottier (http://www.cyrilmottier.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kull.android.widget.itemview;


import com.kull.android.RHelper;
import com.kull.android.widget.item.Item;
import com.kull.android.widget.item.ProgressItem;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;



/**
 * View representation of the {@link ProgressItem}.
 * 
 * @author Cyril Mottier
 */
public class ProgressItemView extends FrameLayout implements ItemView {

    private ProgressBar mProgressBar;
    private TextView mTextView;

    public ProgressItemView(Context context) {
        this(context, null);
    }

    public ProgressItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void prepareItemView() {
        mProgressBar = (ProgressBar) findViewById(RHelper.id.kull_progress_bar);
        mTextView = (TextView) findViewById(RHelper.id.kull_text);
    }

    public void setObject(Item object) {
        final ProgressItem item = (ProgressItem) object;
        mProgressBar.setVisibility(item.isInProgress ? View.VISIBLE : View.GONE);
        mTextView.setText(item.text);
    }

}
