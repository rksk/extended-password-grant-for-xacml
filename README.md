# extended-password-grant-for-xacml

This is a modified version of password grant type to support both APIM and IS scope validation features for IS-KM product. Because WSO2 APIM is having its own scope validation logic (applicable to IS-KM also) and XACML policies can not be applied during password grant flow.

### Step to deploy
- Compile the source code with maven
- Copy the org.wso2.custom.sample.grant.password-1.0.0.jar found in target directory to <ISKM_HOME>/repository/components/lib directory
- Add the following to deployment.toml
```
[oauth.grant_type.password]
grant_handler = "org.wso2.custom.sample.grant.password.ExtendedPasswordGrantHandlerForXACML"
```
- Restart WSO2 ISKM
