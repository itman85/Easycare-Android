package vn.easycare.layers.ui.presenters;

import android.content.Context;

import java.util.List;

import vn.easycare.R;
import vn.easycare.layers.services.IWSResponse;
import vn.easycare.layers.services.IWebServiceModel;
import vn.easycare.layers.services.WSError;
import vn.easycare.layers.ui.components.data.GCMItemData;
import vn.easycare.layers.ui.components.data.LoginItemData;
import vn.easycare.layers.ui.components.data.base.IBaseItemData;
import vn.easycare.layers.ui.models.base.IBaseModel;
import vn.easycare.layers.ui.models.base.ILoginModel;
import vn.easycare.layers.ui.models.LoginModel;
import vn.easycare.layers.ui.presenters.base.ILoginPresenter;
import vn.easycare.layers.ui.views.ILoginView;

/**
 * Created by phannguyen on 12/7/14.
 */
public class LoginPresenterImpl  implements ILoginPresenter, IBaseModel.IResponseUIDataCallback{
    ILoginView iView;
    ILoginModel iModel;
    Context mContext;
    public LoginPresenterImpl(ILoginView loginView, Context context){
        iView = loginView;
        iModel = new LoginModel(context,this);
        mContext = context;
    }
    @Override
    public void DoAuthenticateUser(String email, String password) {
       /* String validAccInfo = iModel.ValidateAccountInfo(email, password);
        if (validAccInfo != null && !validAccInfo.isEmpty()) {
            iModel.getLoginAuthentication(email, password);
        } else {
            iView.ShowIncorrectAccountInfoMessage(validAccInfo);
        }*/
        iModel.getLoginAuthentication(email, password);
    }

    @Override
    public void DoRegisterDeviceId(String registrationDeviceId) {
        iModel.registerDeviceIdGCM(registrationDeviceId);
    }

    @Override
    public void init(ILoginView view) {

    }


    @Override
    public <T extends IBaseItemData> void onResponseOK(T itemData, Class<T>... itemDataClass) {
        if(itemDataClass[0].equals(LoginItemData.class)){
            iView.LoginOK("");
        }else if(itemDataClass[0].equals(GCMItemData.class)){
            iView.RegisterGCMIdOK("");
        }

    }

    @Override
    public <T extends IBaseItemData> void onResponseOK(List<T> itemDataList, Class<T>... itemDataClass) {

    }

    @Override
    public void onResponseFail(String message,String functionTitle) {
        iView.LoginFail(message);
    }

    @Override
    public void onUnauthorized() {
        iView.UnauthorizedProcessing();
    }
}
