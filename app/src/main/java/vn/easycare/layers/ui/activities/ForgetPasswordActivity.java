package vn.easycare.layers.ui.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import vn.easycare.R;
import vn.easycare.layers.services.WSDataSingleton;
import vn.easycare.layers.ui.base.BaseActivity;
import vn.easycare.layers.ui.components.CommonHeader;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.DialogUtil;

/**
 * Created by phan on 12/15/2014.
 */
public class ForgetPasswordActivity extends BaseActivity implements CommonHeader.IOnHeaderClickListener{
    final String REQUEST_NEW_PASS_URL = "http://edev.easycare.vn/api/v1/users/request_new_password?email=%s";
    EditText edtUserEmail;
    private CommonHeader mCommonHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
        setContentView(R.layout.activity_forget_password);
        //getActionBar().setTitle(R.string.forget_password_title);

        View headerView = findViewById(R.id.header);
        mCommonHeader = new CommonHeader(headerView);
        mCommonHeader.hideMenuButton();
        mCommonHeader.setOnHeaderClickListener(this);

        View submitLayout = findViewById(R.id.submitLayout);
        edtUserEmail = (EditText)findViewById(R.id.etxUsername);
        submitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestNewPassword(edtUserEmail.getText().toString());
                //volleyTest();
                //Toast.makeText(ForgetPasswordActivity.this, "Send clicked", Toast.LENGTH_SHORT).show();
            }
        });
        // Apply font
        View rootLayout = findViewById(R.id.forgetPassLayout);
        AppFnUtils.applyFontForTextViewChild(rootLayout);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private void sendRequestNewPassword(final String email){
        StringRequest sr = new StringRequest(Request.Method.GET, String.format(REQUEST_NEW_PASS_URL,email), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtil.createInformDialog(ForgetPasswordActivity.this, getResources().getString(R.string.title_forget_pass), ForgetPasswordActivity.this.getResources().getString(R.string.forget_password_message_ok),
                        new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();
               // Toast.makeText(ForgetPasswordActivity.this, ForgetPasswordActivity.this.getResources().getString(R.string.forget_password_message_ok), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                String errMessage = "Yêu cầu mật khẩu mới thất bại. Lỗi: ";
                if(response != null && response.data != null) {
                    String errorResponse = new String(response.data);
                    try {
                        JSONObject jsonObj = new JSONObject(errorResponse);
                        JSONObject jsonErrorObj = (JSONObject)jsonObj.get("errors");
                        if(jsonErrorObj!=null){
                            Iterator<?> keys = jsonErrorObj.keys();
                            while( keys.hasNext() ){
                                String key = (String)keys.next();
                                errMessage+=jsonErrorObj.getString(key)+"\r\n";
                            }
                        }
                    } catch (JSONException e) {
                        errMessage += errorResponse;

                    }
                }
                else{
                    errMessage += error.getMessage();
                }

                DialogUtil.createInformDialog(ForgetPasswordActivity.this, getResources().getString(R.string.title_forget_pass), errMessage,
                        new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();
            }
        }){
            @Override
            protected Map<String,String> getParams(){

              /*  Map<String,String> params = new HashMap<String, String>();
                params.put("email",email);
                return params;*/
                return null;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        WSDataSingleton.getInstance(this).getRequestQueue().add(sr);
    }

    private void volleyTest(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://edev.easycare1.vn/api/v1/users/login?email=bacsihieugmail.com&password=laohac";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(ForgetPasswordActivity.this, response.substring(0,500), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String json = null;

                NetworkResponse response = error.networkResponse;
                if(response != null && response.data != null){
                    switch(response.statusCode){
                        case 400:
                            json = new String(response.data);
                            Toast.makeText(ForgetPasswordActivity.this, json, Toast.LENGTH_SHORT).show();
                            break;
                    }
                    //Additional cases
                }

            }

        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onMenuClicked() {

    }

    @Override
    public void onBack() {
        finish();
    }
}
