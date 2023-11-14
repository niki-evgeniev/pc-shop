package com.example.pcproject.Service;

import com.example.pcproject.models.bindingModels.LoginUserBindingModel;
import com.example.pcproject.models.bindingModels.RegisterUserBindingModel;

public interface UserService {
    boolean registerUser(RegisterUserBindingModel registerUserBindingModel);

    boolean login(LoginUserBindingModel loginUserBindingModel);
}
