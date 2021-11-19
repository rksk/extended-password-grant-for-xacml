package org.wso2.custom.sample.grant.password;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.apimgt.keymgt.ScopesIssuer;
import org.wso2.carbon.identity.oauth2.IdentityOAuth2Exception;
import org.wso2.carbon.identity.oauth2.token.OAuthTokenReqMessageContext;
import org.wso2.carbon.identity.oauth2.token.handlers.grant.PasswordGrantHandler;

/**
 * Modified version of password grant type to support both APIM and IS scope validation features.
 */
public class ExtendedPasswordGrantHandlerForXACML extends PasswordGrantHandler {

    private static Log log = LogFactory.getLog(ExtendedPasswordGrantHandlerForXACML.class);

    @Override
    public boolean validateScope(OAuthTokenReqMessageContext tokReqMsgCtx) throws IdentityOAuth2Exception {
        boolean iamScopeValidationResult = super.validateScope(tokReqMsgCtx);
        boolean apimScopeValidationResult = ScopesIssuer.getInstance().setScopes(tokReqMsgCtx);
        if (log.isDebugEnabled()) {
            log.debug("IS Scope Validation Result: " + iamScopeValidationResult +
                    ", APIM Scope Validation Result: " + apimScopeValidationResult);
        }
        return iamScopeValidationResult && apimScopeValidationResult;
    }
}
