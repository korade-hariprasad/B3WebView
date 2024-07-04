package sumagoscope.madipt.b3webview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etUrl;
    WebView webView;
    ImageButton btnGo, btnNext, btnPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUrl = findViewById(R.id.etUrl);
        btnGo = findViewById(R.id.btnGo);
        webView = findViewById(R.id.webView);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);

        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("https://www.google.com");

        btnGo.setOnClickListener(v->{
            webView.loadUrl(etUrl.getText().toString());
        });

        btnPrevious.setOnClickListener(v->{
            if(webView.canGoBack()){
                btnPrevious.setEnabled(true);
                webView.goBack();
            }else{
                btnPrevious.setEnabled(false);
            }
        });

        btnNext.setOnClickListener(v->{
            if(webView.canGoForward()){
                btnNext.setEnabled(true);
                webView.goForward();
            }
            else btnNext.setEnabled(false);
        });
    }

    class MyWebViewClient extends WebViewClient {

        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            super.doUpdateVisitedHistory(view, url, isReload);
            Toast.makeText(MainActivity.this, url, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            //Toast.makeText(view.getContext(), "Loading Started", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            etUrl.setText(url);
        }
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
        webView.goBack();
        else super.onBackPressed();
    }
}