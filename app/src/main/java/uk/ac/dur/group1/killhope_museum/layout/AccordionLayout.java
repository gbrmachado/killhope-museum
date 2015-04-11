package uk.ac.dur.group1.killhope_museum.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import uk.ac.dur.group1.killhope_museum.R;


public class AccordionLayout {

    private static final String ACCORDION_DOWN_TEXT = "\u25BC";
    private static final String AC_UP = "\u25b2";

    private final LinearLayout contained;
    private final Context context;

    private List<View> content = new ArrayList<>();
    private List<Button> buttons = new ArrayList<>();

    public void addTo(LinearLayout l)
    {
        l.addView(contained);
    }

    public AccordionLayout(Context context) {
        this.contained = new LinearLayout(context);
        this.context = context;
        this.contained.setOrientation(LinearLayout.VERTICAL);
    }

    public AccordionLayout(Context context, AttributeSet attrs) {
        this.contained = new LinearLayout(context, attrs);
        this.context = context;
    }

    public AccordionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this.contained = new LinearLayout(context, attrs, defStyleAttr);
        this.context = context;
    }

    public AccordionLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this.contained = new LinearLayout(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    private WebView createWebViewFor(String content)
    {
        WebView ret = new WebView(context);
        ret.getSettings().setDefaultTextEncodingName("utf-8");
        ret.getSettings().setJavaScriptEnabled(true);
        ret.setBackgroundColor(0x00000000);   //set the transparent background

        ret.addJavascriptInterface(new GlossaryLoaderJavascriptInterface(this.context), "Android");
        //WARNING: There's a bug in Android which will mean that UTF-8 characters won't be displayed
        //In a webview, unless the charset is also set in the MIME type.
        ret.loadData(content, "text/html; charset=utf-8", "UTF-8");
        if(j != 0)
            ret.setVisibility(View.GONE);
        return ret;
    }

    int j = 0;

    private Button createButton()
    {
        Button b = new Button(this.context);
        b.setBackgroundResource(R.drawable.accordion_rock_list);
        b.setText(ACCORDION_DOWN_TEXT);
        b.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams LP = new LinearLayout.LayoutParams(0,0);
        LP.width = LinearLayout.LayoutParams.MATCH_PARENT;
        LP.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        LP.bottomMargin=2;
        b.setLayoutParams(LP);

        b.setOnClickListener(new CL(j++));

        return b;
    }

    private class CL implements View.OnClickListener {

        private final int i;

        public CL(int i)
        {
            this.i = i;
        }

        @Override
        public void onClick(View v)
        {
            toggleSection(i);
        }
    }

    public void AddWebViewSection(String content)
    {
        WebView webView = createWebViewFor(content);
        Button b = createButton();


        this.content.add(webView);
        this.buttons.add(b);

        this.contained.addView(b);
        this.contained.addView(webView);

    }

    public void toggleSection(int ordinal)
    {
        boolean wasVisible = content.get(ordinal).getVisibility() != View.GONE;

        int newVisibility = wasVisible ? View.GONE : View.VISIBLE;
        String newText = wasVisible ? ACCORDION_DOWN_TEXT : AC_UP;

        content.get(ordinal).setVisibility(newVisibility);
        buttons.get(ordinal).setText(newText);
    }

    public void openSection(int ordinal)
    {
        HideAll();
        toggleSection(ordinal);
    }

    public void HideAll()
    {
        for(Button b : buttons)
            b.setText(ACCORDION_DOWN_TEXT);
        for(View v : content)
            v.setVisibility(View.GONE);

    }

}
