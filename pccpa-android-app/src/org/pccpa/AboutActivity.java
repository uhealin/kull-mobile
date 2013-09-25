package org.pccpa;

import org.pccpa.widget.MenuActionGrid;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;



import greendroid.app.GDActivity;

public class AboutActivity extends BaseFragmentActivity {

	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setActionBarContentView(R.layout.about);
        this.setContentView(R.layout.about);
        final TextView aboutText = (TextView) findViewById(R.id.about);
        aboutText.setMovementMethod(LinkMovementMethod.getInstance());
        String content="<p>本应用纯粹是林工个人业余兴趣研发，并不代表公司立场!</p><p>由于本人水平有限，偶尔有闪退现象请见谅，界面比较简陋，也请不要见怪</p>";
        content+="<p>本程序源码已开发，并在持续进展中，欢迎学习交流</p>";
        content+="<p>github:<a href='https://github.com/smartken/pccpa-mobile'>https://github.com/smartken/pccpa-mobile</a></p>";
        aboutText.setText(Html.fromHtml(content));
        //MenuActionGrid.renderTo(this);
    }
}
